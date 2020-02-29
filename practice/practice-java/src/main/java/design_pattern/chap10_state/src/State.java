package design_pattern.chap10_state.src;

public interface State {

    void insertQuarter();   // 동전 투입

    void ejectQuarter();    // 동전 반환

    void turnCrank();       // 손잡이돌리기?

    void dispense();        // 알맹이 반환?
}
