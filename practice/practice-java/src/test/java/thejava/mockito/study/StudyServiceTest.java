package thejava.mockito.study;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;


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
//    studyService.createNewStudy(1L, study);
//    assertEquals(member, study.getMember());

    final Study newStudy = studyService.createNewStudy(1L, study);
    assertNotNull(newStudy);
    assertEquals(member, newStudy.getMember());
    assertEquals(member, study.getMember());
  }

}