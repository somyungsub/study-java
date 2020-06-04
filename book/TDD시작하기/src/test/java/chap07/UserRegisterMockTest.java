package chap07;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserRegisterMockTest {
  private UserRegister userRegister;
  private WeakPasswordChecker mockPasswordChecker = Mockito.mock(WeakPasswordChecker.class);
  private MemoryUserRepository fakeRepository = new MemoryUserRepository();
  private EmailNotifier mockEmailNotifier = Mockito.mock(EmailNotifier.class);

  @BeforeEach
  void setUp() {
    userRegister = new UserRegister(mockPasswordChecker, fakeRepository, mockEmailNotifier);
  }

  @Test
  @DisplayName("약한 암호 -> 가입실패")
  public void weakPassword() {
    BDDMockito.given(mockPasswordChecker.checkPasswordWeak("pw"))
        .willReturn(true);

    assertThrows(WeakPasswordException.class, () -> {
      userRegister.register("id", "pw", "email");
    });
  }

  @Test
  @DisplayName("강한 암호 -> 가입")
  public void strongPassword() {
    BDDMockito.given(mockPasswordChecker.checkPasswordWeak("pw!123!321"))
        .willReturn(false);

    userRegister.register("id", "pw!123!321", "email");
    User user = fakeRepository.findById("id");
    System.out.println("user = " + user);
  }

  @Test
  @DisplayName("회원가임시 암호 검사 수행함")
  public void checkPassword() {
    userRegister.register("id", "pw", "email");

    BDDMockito.then(mockPasswordChecker)
        .should()
        .checkPasswordWeak(BDDMockito.anyString());

  }

  @Test
  @DisplayName("가입하면 메일을 전송함")
  public void whenRegisterThenSendMail() {

    // given
    userRegister.register("id", "pw", "email@email.com");

    // when
    ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);


    // then
    BDDMockito.then(mockEmailNotifier)
        .should().sendRegisterEmail(captor.capture());

    String realEmail = captor.getValue();
    assertEquals("email@email.com", realEmail);
  }


//  private StubCardValidator stubCardValidator = new StubCardValidator();
//  private AutoDebitRegister register = new AutoDebitRegister(stubCardValidator);
//
//  @Test
//  @DisplayName("유효하지 않은 카드 정보")
//  public void invalidCardNumber() {
//    stubCardValidator.setInvalidNo("11223344");
//
//    AutoDebitReq req = new AutoDebitReq("user1", "11223344");
//    RegisterResult result = register.register(req);
//
//    assertEquals(VALID, result.getValidity());
//
//  }

}
