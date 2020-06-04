package chap07;

public class SpyEmailNotifier implements EmailNotifier {

  private String email;
  private boolean called;

  @Override
  public String getEmail() {
    return this.email;
  }

  @Override
  public boolean isCalled() {
    return called;
  }

  @Override
  public void sendRegisterEmail(String email) {
    this.email = email;
    this.called = true;
  }
}
