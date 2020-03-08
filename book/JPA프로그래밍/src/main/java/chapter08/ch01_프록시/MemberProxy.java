package chapter08.ch01_프록시;

public class MemberProxy extends Member {

  Member target = null; // 실제 엔티티 참조

  public String getName() {
    if (target == null) {
      // 2. 초기화 요청
      // 3. DB 조회
      // 4. 실제 엔티티 생성 및 참조 보관
//      this.target = ..;
    }
    // 5. target.getName()
    return target.getUsername();
  }
}
