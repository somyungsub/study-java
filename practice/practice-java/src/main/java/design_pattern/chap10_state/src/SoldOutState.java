package design_pattern.chap10_state.src;

public class SoldOutState implements State {

    GumballMachine gumballMachine;

    public SoldOutState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    @Override
    public void insertQuarter() {
        System.out.println("알맹이가 다 팔렸습니다");
    }

    @Override
    public void ejectQuarter() {
        System.out.println("알맹이가 다 팔렸습니다");
    }

    @Override
    public void turnCrank() {
        System.out.println("알맹이가 다 팔렸습니다");
    }

    @Override
    public void dispense() {
        System.out.println("알맹이가 다 팔렸습니다");
    }
}
