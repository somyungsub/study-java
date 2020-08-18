public class Member {

  private int verificationEmailStatus;
  private String id;
  private String password;

  public int getVerificationEmailStatus() {
    return 0;
  }

  public boolean isEmailVerified() {
    return verificationEmailStatus == 2;
  }

  public String getId() {
    return id;
  }

  public String getPassword() {
    return password;
  }
}
