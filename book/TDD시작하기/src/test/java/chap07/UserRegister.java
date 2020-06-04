package chap07;

public class UserRegister {

  private final StubWeakPasswordChecker passwordChecker;

  public UserRegister(StubWeakPasswordChecker passwordChecker) {
    this.passwordChecker = passwordChecker;
  }

  public void register(String id, String pw, String email) {
    throw new WeakPasswordException("에러발생");
  }
}
