package chap_appendix_c;


public class UserRegister {

  private EmailNotifier emailNotifier;

  public UserRegister(EmailNotifier emailNotifier) {
    this.emailNotifier = emailNotifier;
  }

  public void register(String id, String pw, String email) {
    emailNotifier.sendRegisterEmail(email);
  }
}
