package design_pattern.kosta.behavior.template;

public abstract class Car {


  // template 메서드
  public void playWithOwner() {
    System.out.println("시동켜기");
    System.out.println("사이드 브레이크 해제");


    // 위임하고 싶은 함수들 필요
    play();         // 필수
    runSomething(); // 옵션 : hook 메서드, override 해도 무방


  }


  // 하위에서 구현 : 위임
  protected abstract void play();


  // hook 메서드
  protected void runSomething() {
    System.out.println("내가 수행 MyCar: Break");
  }
}
