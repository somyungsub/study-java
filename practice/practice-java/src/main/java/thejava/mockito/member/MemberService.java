package thejava.mockito.member;

import thejava.mockito.domain.Member;
import thejava.mockito.domain.Study;

import java.util.Optional;

public interface MemberService {
  Optional<Member> findById(Long memberId) throws MemberNotFoundException;

  void validate(long l);

  void notify(Study save);

  void notify(Member member);
}
