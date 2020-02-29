package design_pattern.kosta.behavior.chain;

public abstract class Boundary {

  // 최대 최소값를 저장 할 수 있는
  protected int upper;
  protected int lower;

  public Boundary(int upper, int lower) {
    this.upper = upper;
    this.lower = lower;
  }

  // 다음 체인 저장 장소 필요
  protected Boundary nested = null;

  public void setNested(Boundary nested) {
    this.nested = nested;
  }

  // request 처리부분 : 실제로 받아서 처리하는 부분
  public void action(int value) {
    if (isInBoudary(value)) {
      individualAction(value);
    } else if (this.nested != null) {
      this.nested.action(value);
    } else {
      individualAction(value);
    }
  }

//  protected abstract boolean isInBoudary(int value);

  private boolean isInBoudary(int value) {
    if (value >= lower && value <= upper) {
      return true;
    }
    return false;
  }
  protected abstract void individualAction(int value);


}


class NormalVoltage extends Boundary{

  public NormalVoltage(int upper, int lower) {
    super(upper, lower);
  }

  @Override
  protected void individualAction(int value) {
    System.out.println("NOmaol");
//    super.nested.action(value);
  }
}

class WarningVoltage extends Boundary {
  public WarningVoltage(int upper, int lower) {
    super(upper, lower);
  }

  @Override
  protected void individualAction(int value) {
    System.out.println("Warn");
  }

}

class FaultVoltage extends Boundary {
  public FaultVoltage(int upper, int lower) {
    super(upper, lower);
  }

  @Override
  protected void individualAction(int value) {
    System.out.println("Fault");
  }
}



