package chapter_05;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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

}