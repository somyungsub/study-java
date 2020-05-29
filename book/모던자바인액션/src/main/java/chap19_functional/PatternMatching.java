package chap19_functional;

import java.util.function.Function;
import java.util.function.Supplier;

public class PatternMatching {
  public static <T> T myIf(boolean b, Supplier<T> truecase, Supplier<T> falsecase) {
    return b ? truecase.get() : falsecase.get();
  }

//  public static <T> T patternMatchExpr(
//          Expr e,
//          TriFunction<String, Expr, Expr, T> binopcase,
//          Function<Integer, T> numcase,
//          Supplier<T> defaultcase) {
//
//    return
//            (e instanceof BinOp) ?
//                    binopcase.apply(((BinOp)e).opname, ((BinOp)e).left, ((BinOp)e).right) :
//                    (e instanceof Number) ?
//                            numcase.apply(((Number)e).val) :
//                            defaultcase.get();
//  }
}
