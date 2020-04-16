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
    study.setMember(member.orElseThrow(()-> new IllegalArgumentException("Member doesn't exist for id : " + memberId)));


    final Study save = studyRepository.save(study);

    memberService.notify(save);
    memberService.notify(member.get());

    return save;
  }
}
