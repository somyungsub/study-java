package thejava.mockito.member;

import thejava.mockito.domain.Member;

import java.util.Optional;

public interface MemberService {
  Optional<Member> findById(Long memberId) throws MemberNotFoundException;
}
