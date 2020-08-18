public class 캡슐화연습 {
  private PasswordEncoder passwordEncoder;

  public 캡슐화연습(PasswordEncoder passwordEncoder) {
    this.passwordEncoder = passwordEncoder;
  }


  public AuthResult authenticate(String id, String pw) {
    Member mem = findOne(id);

    if (mem == null) {
      return AuthResult.NO_MATCH;
    }

    if (mem.getVerificationEmailStatus() != 2) {
      return AuthResult.NO_EMAIL_VERIFIED;
    }

    if (passwordEncoder.isPasswordValid(mem.getPassword(), pw, mem.getId())) {
      return AuthResult.SUCCESS;
    }

    return AuthResult.NO_MATCH;
  }


  public AuthResult authenticateCapsule(String id, String pw) {
    Member mem = findOne(id);

    if (mem == null) {
      return AuthResult.NO_MATCH;
     }

    if (!mem.isEmailVerified()) {
      return AuthResult.NO_EMAIL_VERIFIED;
    }

    if (passwordEncoder.isPasswordValid(mem.getPassword(), pw, mem.getId())) {
      return AuthResult.SUCCESS;
    }

    return AuthResult.NO_MATCH;
  }

  private Member findOne(String id) {
    return null;
  }

}
