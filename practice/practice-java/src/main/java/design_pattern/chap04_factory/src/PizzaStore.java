package design_pattern.chap04_factory.src;


/*
    생성자
 */
public abstract class PizzaStore {

//    private SimplePizzaFactory factory;
//
//    public PizzaStore(SimplePizzaFactory factory) {
//        this.factory = factory;
//    }

    public Pizza orderPizza(String type) {
//        Pizza pizza = factory.createPizza(type);
        Pizza pizza = createPizza(type);

        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();

        return pizza;
    }

    public abstract Pizza createPizza(String type);
}
