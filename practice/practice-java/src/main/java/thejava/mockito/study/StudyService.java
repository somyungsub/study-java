package thejava.mockito.study;

import thejava.mockito.domain.Member;
import thejava.mockito.domain.Study;
import thejava.mockito.member.MemberService;

import java.util.Optional;

public class StudyService {
  private final MemberService memberService;

  private final StudyRepository studyRepository;

  public StudyService(MemberService memberService, StudyRepository studyRepository) {
    assert memberService != null;
    assert studyRepository != null;

    this.memberService = memberService;
    this.studyRepository = studyRepository;
  }

  public Study createNewStudy(Long memberId, Study study) {
    Optional<Member> member = memberService.findById(memberId);
    study.setOwner(member.orElseThrow(()-> new IllegalArgumentException("Member doesn't exist for id : " + memberId)));
    return studyRepository.save(study);
  }
}
