package design_pattern.kosta.creation.singleton.simple;

public class Singleton {

  // TODO
  private static Singleton singleton = new Singleton();

//  private static Singleton singleton;


  private Singleton() {
    System.out.println("생성자 호출");
  }


  /**
   * 생성자가 private 이므로 new Singleton() 안됨
   * 싱글톤이므로 객체의 인스턴스가 한개만 존재하기를 기대합니다.
   * @return Singleton : 자기자신
   */

  // 멀티환경
  public static Singleton getInstance() {
    if (singleton == null) {
      synchronized (Singleton.class) {
        if (singleton == null)
        singleton = new Singleton();
      }
    }
    return singleton;
  }

//  public synchronized static Singleton getInstance() {
//    if (singleton == null) {
//      singleton = new Singleton();
//    }
//    return singleton;
//  }

//  public static Singleton getInstance() {
//
//    if (singleton == null) {
//      singleton = new Singleton();
//    }
//    return singleton;
//  }


  public void doSomething() {
    System.out.println("Something is Done.");
  }
}

