package chap07;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserRegisterMockOvercaseTest {
  private UserRegister userRegister;
  private UserRegister userRegister2;
  private UserRepository mockRepository = Mockito.mock(UserRepository.class); // 모킹
  private WeakPasswordChecker mockPasswordChecker = Mockito.mock(WeakPasswordChecker.class);
  private EmailNotifier mockEmailNotifier = Mockito.mock(EmailNotifier.class);

  private UserRepository fakeRepository = new MemoryUserRepository();

  @BeforeEach
  void setUp() {
    userRegister = new UserRegister(mockPasswordChecker, mockRepository, mockEmailNotifier);
    userRegister2 = new UserRegister(mockPasswordChecker, fakeRepository, mockEmailNotifier);
  }

  @Test
  @DisplayName("중복아이디 없이 가입성공 - 모의객체 과다사용")
  public void user_register_over() {
    userRegister.register("id", "pw", "email");

    ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
    BDDMockito.then(mockRepository).should().save(captor.capture());

    User savedUser = captor.getValue();
    assertEquals("id", savedUser.getId());
    assertEquals("email", savedUser.getEmail());
  }


  @Test
  @DisplayName("중복아이디 없이 가입성공-가짜구현")
  public void user_register_fake() {
    userRegister2.register("id", "pw", "email");

    User savedUser = fakeRepository.findById("id");
    assertEquals("id", savedUser.getId());
    assertEquals("email", savedUser.getEmail());
  }
}
