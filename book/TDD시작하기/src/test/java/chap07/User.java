package chap07;

public class User {

  private final String id;
  private final String password;
  private final String email;

  public User(String id, String password, String email) {
    this.id = id;
    this.password = password;
    this.email = email;
  }

  public String getId() {
    return id;
  }

  public String getPassword() {
    return password;
  }

  public String getEmail() {
    return email;
  }

  @Override
  public String toString() {
    return "User{" +
        "id='" + id + '\'' +
        ", password='" + password + '\'' +
        ", email='" + email + '\'' +
        '}';
  }
}
