package effective.chap02_객체생성과파괴;

/*
  1. 생성자 대신 팩터리 메서드를 고려하라
 */
public class Item1 {
  public static Boolean valueOf(boolean b) {
    return b ? Boolean.TRUE : Boolean.FALSE;
  }

}
