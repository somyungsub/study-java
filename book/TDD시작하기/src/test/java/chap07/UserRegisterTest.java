package chap07;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserRegisterTest {
  private UserRegister userRegister;
  private StubWeakPasswordChecker stubWeakPasswordChecker= new StubWeakPasswordChecker();
  private MemoryUserRepository fakeRepository = new MemoryUserRepository();

  @BeforeEach
  void setUp() {
    userRegister = new UserRegister(stubWeakPasswordChecker, fakeRepository);
  }

  @Test
  @DisplayName("약한 암호면 가입 실패")
  public void weakPassword() {
    stubWeakPasswordChecker.setWeak(true);
    assertThrows(WeakPasswordException.class, () -> {
      userRegister.register("id", "pw", "email");
    });
  }

  @Test
  @DisplayName("이미 같은 ID존재 -> 가입실패")
  public void dupleIdExists() {
    fakeRepository.save(new User("id", "pw1", "email@email.com"));

    assertThrows(DupIdException.class, () -> {
      userRegister.register("id", "pw2", "email");
    });
  }

  @Test
  @DisplayName("같은 ID 없으면 -> 가입성공 확인")
  public void no_dupId_register_success() {
    userRegister.register("id", "pw", "email");

    User saveUser = fakeRepository.findById("id");
    assertEquals("id", saveUser.getId());
    assertEquals("email", saveUser.getEmail());

  }
}
