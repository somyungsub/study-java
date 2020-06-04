package chap07;

public class UserRegister {

  private final WeakPasswordChecker passwordChecker;
  private UserRepository fakeRepository;
  private EmailNotifier emailNotifier;

  public UserRegister(WeakPasswordChecker passwordChecker) {
    this.passwordChecker = passwordChecker;
  }

  public UserRegister(WeakPasswordChecker passwordChecker, MemoryUserRepository fakeRepository) {
    this.passwordChecker = passwordChecker;
    this.fakeRepository = fakeRepository;
  }

  public UserRegister(WeakPasswordChecker passwordChecker, MemoryUserRepository fakeRepository, EmailNotifier emailNotifier) {
    this.passwordChecker = passwordChecker;
    this.fakeRepository = fakeRepository;
    this.emailNotifier = emailNotifier;

  }

  public void register(String id, String pw, String email) {
    if (passwordChecker.checkPasswordWeak(pw)) {
      System.out.println("약한암호 에러");
      throw new WeakPasswordException("에러발생");
    }

    User user = fakeRepository.findById(id);
    if (user != null) {
      throw new DupIdException();
    }
    fakeRepository.save(new User(id, pw, email));
    emailNotifier.sendRegisterEmail(email);
  }
}
