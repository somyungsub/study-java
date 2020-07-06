package chap03;

import javax.servlet.*;
import java.io.IOException;
import java.math.BigInteger;

public class VolatileCachedFactorizer extends GenericServlet implements Servlet {

  private volatile OneValueCache cache = new OneValueCache(null, new BigInteger[0]);
  private volatile BigInteger count = new BigInteger("1", 10);
//  private volatile OneValueCache cache = new OneValueCache(null, null);

  @Override
  public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
    BigInteger i = extractFromRequest(req);
    BigInteger[] factors = cache.getLastFactors(i);

    if (factors == null) {
      factors = factor(i);
      cache = new OneValueCache(i.add(new BigInteger("1", 10)), factors);
    }
  }

  private BigInteger[] factor(BigInteger i) {
    return new BigInteger[]{i};
  }


  private BigInteger extractFromRequest(ServletRequest req) {
    return count;
  }

  public OneValueCache getCache() {
    return cache;
  }
}
