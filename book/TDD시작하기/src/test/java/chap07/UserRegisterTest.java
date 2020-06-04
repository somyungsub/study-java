package chap07;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserRegisterTest {
  private UserRegister userRegister;

  private StubWeakPasswordChecker stubWeakPasswordChecker= new StubWeakPasswordChecker();

  @BeforeEach
  void setUp() {
    userRegister = new UserRegister(stubWeakPasswordChecker);
  }

  @Test
  @DisplayName("약한 암호면 가입 실패")
  public void weakPassword() {
    stubWeakPasswordChecker.setWeak(true);
    assertThrows(WeakPasswordException.class, () -> {
      userRegister.register("id", "pw", "email");
    });
  }
}
