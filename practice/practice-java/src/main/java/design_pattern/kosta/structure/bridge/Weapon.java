package design_pattern.kosta.structure.bridge;

public interface Weapon {
  void attack();

  void repair();
}

class Bow implements Weapon {

  @Override
  public void attack() {
    System.out.println("활 공격");
  }

  @Override
  public void repair() {
    System.out.println("활 수리");

  }
}

class Sword implements Weapon {

  @Override
  public void attack() {
    System.out.println("검 공격");
  }

  @Override
  public void repair() {
    System.out.println("검 수");
  }
}

class Gun implements Weapon {
  @Override
  public void attack() {
    System.out.println("총 공격");
  }

  @Override
  public void repair() {
    System.out.println("총 수리");
  }
}
