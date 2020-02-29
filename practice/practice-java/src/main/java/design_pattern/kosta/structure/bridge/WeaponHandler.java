package design_pattern.kosta.structure.bridge;

public interface WeaponHandler {
  void handle();
}

class Warrior implements WeaponHandler{

  // new warrier(new Weapon()).handel() -> weapon 기능처리

  private Weapon weapon;

  public Warrior(Weapon weapon) {
    this.weapon = weapon;
  }

  @Override
  public void handle() {
    this.weapon.attack();
  }
}

class Smith implements WeaponHandler{

  private Weapon weapon;
  public Smith(Weapon weapon) {
    this.weapon = weapon;
  }

  @Override
  public void handle() {
    this.weapon.repair();
  }
}
