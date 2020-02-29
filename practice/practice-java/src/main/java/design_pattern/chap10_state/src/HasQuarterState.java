package design_pattern.chap10_state.src;

public class HasQuarterState implements State {

    GumballMachine gumballMachine;

    public HasQuarterState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    @Override
    public void insertQuarter() {
        System.out.println("동전은 한개만 넣어주세요");
    }

    @Override
    public void ejectQuarter() {
        System.out.println("동전이 반환됩니다");
        gumballMachine.setState(gumballMachine.getNoQuarterState());    // 동전이 반환된 상태 저장
    }

    @Override
    public void turnCrank() {
        System.out.println("손잡이를 돌리셨습니다");
        gumballMachine.setState(gumballMachine.getSoldState()); // 손잡이를 돌리는순간 알맹이가 반환되므로 판매가 된상태를 저장
    }

    @Override
    public void dispense() {
        System.out.println("알맹이가 나갈수가 없습니다");
    }
}
