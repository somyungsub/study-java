package design_pattern.chap10_state.src;

public class NoQuarterState implements State {

    GumballMachine gumballMachine;


    public NoQuarterState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    @Override
    public void insertQuarter() {
        System.out.println("동전을 넣었습니다");
        gumballMachine.setState(gumballMachine.getHasQuarterState());   // 동전이 투입된 상태 저장
    }

    @Override
    public void ejectQuarter() {
        System.out.println("동전을 넣어주세요");
    }

    @Override
    public void turnCrank() {
        System.out.println("동전을 넣어주세요");
    }

    @Override
    public void dispense() {
        System.out.println("동전을 넣어주세요");
    }
}
