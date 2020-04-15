package thejava.mockito.study;

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

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
        // mockito-junit-jupiter 라이브러리에서 제공
class StudyServiceTest {

  @Mock // 이 애노테이션만으로 인스턴스가 생성되지는 않음 -> Class에 @ExtendWith 선언 필요
          MemberService memberService;

  @Mock
  StudyRepository studyRepository;

  @Test
  void createStudyService() {
//    MemberService memberService = Mockito.mock(MemberService.class);
//    StudyRepository studyRepository = Mockito.mock(StudyRepository.class);
    StudyService studyService = new StudyService(memberService, studyRepository);
    assertNotNull(studyService);
  }

  @Test
  void createStudyService2(@Mock MemberService memberS,
                           @Mock StudyRepository studyRepo) {

    StudyService studyService = new StudyService(memberS, studyRepo);
    assertNotNull(studyService);
  }

}