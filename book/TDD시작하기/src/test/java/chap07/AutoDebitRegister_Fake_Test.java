package chap07;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AutoDebitRegister_Fake_Test {
//  private AutoDebitRegister register;
//  private StubCardNumberValidator cardNumberValidator;
//  private MemoryAutoDebitInfoRepository repository;
//
//  @BeforeEach
//  void setUp() {
//    cardNumberValidator = new StubCardNumberValidator();
//    repository = new MemoryAutoDebitInfoRepository();
//    register = new AutoDebitRegister_Fake_Test(cardNumberValidator, repository);
//  }
//
//  @Test
//  @DisplayName("아이디 중복 확인")
//  public void already_register_info_updated() {
//    repository.save(new AutoDebitInfo("user1", "111222333444", LocalDateTime.now()));
//
//    AutoDebitReq req = new AutoDebitReq("user1", "123456789012");
//    RegisterResult result = this.register.register(req);
//
//    AutoDebitInfo saved = repository.findOne("user1");
//    assertEquals("123456789012", saved.getCardNumber());
//  }
}
