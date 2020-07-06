package chap03;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.servlet.ServletException;
import java.io.IOException;
import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

class VolatileCachedFactorizerTest {

  @Test
  @DisplayName("volatile 테스트")
  public void volatile_test() throws ServletException, IOException {
    VolatileCachedFactorizer volatileCachedFactorizer = new VolatileCachedFactorizer();
    volatileCachedFactorizer.service(null, null);
    volatileCachedFactorizer.service(null, null);

    System.out.println(volatileCachedFactorizer.getCache());
  }

}