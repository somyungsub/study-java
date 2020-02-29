package design_pattern.chap07_adapter_facade.src;

public class DuckTestDrive {
    public static void main(String[] args) {
        MallardDuck duck = new MallardDuck();
        WildTurkey turkey = new WildTurkey();
        Duck turkeyAdapter = new TurkeyAdapter(turkey);


        System.out.println("The Turkey says...");
        turkey.gobble();
        turkey.fly();


        System.out.println("\n The Duck says...");
        testDuck(duck);

        System.out.println("\n The TurkeyAdapter says...");
        testDuck(turkeyAdapter);


        System.out.println("\n The DuckAdapter says...");
        Turkey duckAdapter = new DuckAdapter(duck);
        duckAdapter.gobble();
        duckAdapter.fly();




    }

    private static void testDuck(Duck duck) {
        duck.quack();
        duck.fly();
    }

}
