package thejava.mockito.study;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoSession;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import thejava.mockito.domain.Member;
import thejava.mockito.domain.Study;
import thejava.mockito.member.MemberNotFoundException;
import thejava.mockito.member.MemberService;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;


// mockito-junit-jupiter 라이브러리에서 제공
@ExtendWith(MockitoExtension.class)
class StudyServiceTest {
// 이 애노테이션만으로 인스턴스가 생성되지는 않음 -> Class에 @ExtendWith 선언 필요
  @Mock
  MemberService memberService;

  @Mock
  StudyRepository studyRepository;

  @Test
  @DisplayName("테스트")
  void test1() {
    MemberService memberService = Mockito.mock(MemberService.class);
    StudyRepository studyRepository = Mockito.mock(StudyRepository.class);
    StudyService studyService = new StudyService(memberService, studyRepository);
    assertNotNull(studyService);
  }

  @Test
  @DisplayName("테스트2")
  void test2(@Mock MemberService memberS,
             @Mock StudyRepository studyRepo) {

    StudyService studyService = new StudyService(memberS, studyRepo);
    assertNotNull(studyService);
  }

  @Test
  @DisplayName("스터디 생성")
  void createStudyService(@Mock MemberService memberService,
                           @Mock StudyRepository studyRepository) {

    StudyService studyService = new StudyService(memberService, studyRepository);
    assertNotNull(studyService);

    // given
    Member member = new Member();
    member.setId(1L);
    member.setEmail("ssosso");

    // when & then
    when(memberService.findById(1L)).thenReturn(Optional.of(member));

    final Optional<Member> byId = memberService.findById(1L);
    assertEquals("ssosso", byId.get().getEmail());

//    when(memberService.findById(1L)).thenThrow(new RuntimeException());

    // 예외를 던져라 , 멤버서비스의 벨리데이트 호출 시(1L)
    doThrow(new IllegalArgumentException()).when(memberService).validate(1L);
    assertThrows(IllegalArgumentException.class, () -> {
      memberService.validate(1L);
    });

    memberService.validate(2L); // memberService.validate(1L) 호출시 예외발생함

  }

  @Test
  @DisplayName("스터디 생성2")
  void createStudyService2(@Mock MemberService memberService,
                          @Mock StudyRepository studyRepository) {

    StudyService studyService = new StudyService(memberService, studyRepository);
    assertNotNull(studyService);

    // given
    Member member = new Member();
    member.setId(1L);
    member.setEmail("ssosso@gmail.com");

    // when & then
    when(memberService.findById(any()))
            .thenReturn(Optional.of(member))
            .thenThrow(new RuntimeException())
            .thenReturn(Optional.empty())
    ;

    final Optional<Member> byId =
            memberService.findById(1L);

    assertEquals("ssosso@gmail.com", byId.get().getEmail());
    assertThrows(RuntimeException.class, () -> {
      memberService.findById(2L);
    });

    assertEquals(Optional.empty(), memberService.findById(3L));
  }
  @Test
  @DisplayName("스터디 실제생성")
  void createStudyService3(@Mock MemberService memberService,
                          @Mock StudyRepository studyRepository) {

    StudyService studyService = new StudyService(memberService, studyRepository);
    assertNotNull(studyService);

    // given - member
    Member member = new Member();
    member.setId(1L);
    member.setEmail("ssosso@gmail.com");

    // when & then
    when(memberService.findById(1L)).thenReturn(Optional.of(member));
    final Optional<Member> byId = memberService.findById(1L);
    assertEquals("ssosso@gmail.com", byId.get().getEmail());

    // given - study
    Study study = new Study(10, "Test~~");

    // when
    when(studyRepository.save(study)).thenReturn(study);
//    studyService.createNewStudy(1L, study);
//    assertEquals(member, study.getMember());

    final Study newStudy = studyService.createNewStudy(1L, study);
    assertNotNull(newStudy);
    assertEquals(member, newStudy.getMember());
    assertEquals(member, study.getMember());
  }

  @Test
  @DisplayName("스터디 실제생성 - 파라미터 없이")
  void createStudyService4() {

    StudyService studyService = new StudyService(memberService, studyRepository);
    assertNotNull(studyService);

    // given - member
    Member member = new Member();
    member.setId(1L);
    member.setEmail("ssosso@gmail.com");

    // when & then
    when(memberService.findById(1L)).thenReturn(Optional.of(member));
    final Optional<Member> byId = memberService.findById(1L);
    assertEquals("ssosso@gmail.com", byId.get().getEmail());

    // given - study
    Study study = new Study(10, "Test~~");

    // when
    when(studyRepository.save(study)).thenReturn(study);

    final Study newStudy = studyService.createNewStudy(1L, study);
    assertNotNull(newStudy);
    assertEquals(member, newStudy.getMember());
    assertEquals(member, study.getMember());
  }

  @Test
  @DisplayName("mock 객체 확인")
  void mock_confirm() {

    StudyService studyService = new StudyService(memberService, studyRepository);
    assertNotNull(studyService);

    // given - member
    Member member = new Member();
    member.setId(1L);
    member.setEmail("ssosso@gmail.com");

    Study study = new Study(10, "Test~!!");

    // when & then
    when(memberService.findById(1L)).thenReturn(Optional.of(member));
    when(studyRepository.save(study)).thenReturn(study);

    studyService.createNewStudy(1L, study);

    assertEquals(member, study.getMember());

    // 어떤 액션도 일어나면안됨...
//    verifyNoMoreInteractions(memberService);  // 아래 실행으로 인해 에러남

    // 호출 1번된지 확인
    verify(memberService, times(1)).notify(study);
    verify(memberService, times(1)).notify(member);

    // 호출 순서 확인
    final InOrder inOrder = inOrder(memberService);

    // study -> member 순서로 호출되는지 확인
    inOrder.verify(memberService).notify(study);
    inOrder.verify(memberService).notify(member);

    // 이시점 이후 어떤 액션도 일어나면안됨...
    verifyNoMoreInteractions(memberService);
    verify(studyRepository, timeout(3000)).save(study);   // 3초 이내에 실행되어야함
    verify(memberService, timeout(3000)).notify(member);  // 3초 이내에 실행되어야함


    // 절대 호출되면 안되는거 확인
//    verify(memberService, never()).validate(any());

//    verify(memberService, times(1)).notify(any());
//    memberService.notify();
  }

  @Test
  @DisplayName("BDD 스타일 적용")
  void mock_bdd() {

    // given - member
    StudyService studyService = new StudyService(memberService, studyRepository);
    assertNotNull(studyService);

    Member member = new Member();
    member.setId(1L);
    member.setEmail("ssosso@gmail.com");

    Study study = new Study(10, "Test~!!");

    given(memberService.findById(1L)).willReturn(Optional.of(member));
    given(studyRepository.save(study)).willReturn(study);

    // when
    studyService.createNewStudy(1L, study);

    // then
    assertEquals(member, study.getMember());
    then(memberService).should(times(1)).notify(study);
//    then(memberService).shouldHaveNoMoreInteractions();
//    then(memberService).shouldHaveNoInteractions();

  }

  @Test
  @DisplayName("BDD 연습문제")
  void mock_practice() {

    // Given
    StudyService studyService = new StudyService(memberService, studyRepository);
    Study study = new Study(10, "Mock 연습문제");
    assertNull(study.getStartDate());
    assertEquals(StudyStatus.DRAFT, study.getStatus());

    given(studyRepository.save(study)).willReturn(study);

    // When
    final Study openStudy = studyService.openStudy(study);

    // Then
    assertEquals(StudyStatus.OPENED, openStudy.getStatus());  // OPEN 변경확인
    assertNotNull(openStudy.getStartDate());
    then(memberService).should().notify(study);

  }

}