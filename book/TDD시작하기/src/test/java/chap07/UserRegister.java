package chap07;

public class UserRegister {

  private final StubWeakPasswordChecker passwordChecker;
  private UserRepository fakeRepository;

  public UserRegister(StubWeakPasswordChecker passwordChecker) {
    this.passwordChecker = passwordChecker;
  }

  public UserRegister(StubWeakPasswordChecker passwordChecker, MemoryUserRepository fakeRepository) {
    this.passwordChecker = passwordChecker;
    this.fakeRepository = fakeRepository;
  }

  public void register(String id, String pw, String email) {
    if (passwordChecker.checkPasswordWeak(pw)) {
      throw new WeakPasswordException("에러발생");
    }
    User user = fakeRepository.findById(id);
    if (user != null) {
      throw new DupIdException();
    }

    fakeRepository.save(new User(id, pw, email));
  }
}
