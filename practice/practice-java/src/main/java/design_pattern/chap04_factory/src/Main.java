package design_pattern.chap04_factory.src;

public class Main {
    public static void main(String[] args) {
        PizzaStore pizzaStore = new ChicagoPizzaStore();
//        pizzaStore.createPizza("cheese");
        pizzaStore.orderPizza("cheese");
    }
}
