package chapter_05;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class BusinessRuleEngineTest {

  @Test
  @DisplayName("초기화 하지 않음?")
  public void shouldHaveNoRulesInitially(){
    final BusinessRuleEngine engine = new BusinessRuleEngine();
    assertEquals(0, engine.count());
  }

  @Test
  @DisplayName("s")
  public void shouldAddTwoActions(){
    final BusinessRuleEngine engine = new BusinessRuleEngine();

    engine.addAction(() -> {});
    engine.addAction(() -> {});

    assertEquals(2, engine.count());
  }

  @Test
  @DisplayName("5-5모킹으로 Action객체 상호작용 검증하기")
  public void shouldExecuteOneAction(){
    final BusinessRuleEngine engine = new BusinessRuleEngine();
    final Action mockAction = mock(Action.class);

    engine.addAction(mockAction);
    engine.run();

    verify(mockAction).perform();
  }
}