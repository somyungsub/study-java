package chapter_05;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class BusinessRuleEngineTest {

  private Facts facts;

  @BeforeEach
  void setUp() {
    facts = new Facts();
  }

  @Test
  @DisplayName("초기화 하지 않음?")
  public void shouldHaveNoRulesInitially(){
    final BusinessRuleEngine engine = new BusinessRuleEngine(facts);
    assertEquals(0, engine.count());
  }

  @Test
  @DisplayName("s")
  public void shouldAddTwoActions(){
    final BusinessRuleEngine engine = new BusinessRuleEngine(facts);

    engine.addAction(fActs -> {});
    engine.addAction(facts -> {
      final String jobTitle = facts.getFact("jobTitle");
      if ("CEO".equals(jobTitle)) {
        final String name = facts.getFact("name");
        // mail send
        // Mailer.sendEmail("sales@company.com", "Relevant customer : " + name);
      }
    });


    assertEquals(2, engine.count());
  }

  @Test
  @DisplayName("5-5모킹으로 Action객체 상호작용 검증하기")
  public void shouldExecuteOneAction(){
    final BusinessRuleEngine engine = new BusinessRuleEngine(facts);
    final Action mockAction = mock(Action.class);

    engine.addAction(mockAction);
    engine.run();

    verify(mockAction).perform(facts);
  }

}