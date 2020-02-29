package design_pattern.chap04_factory.src;

public class ChicagoStyleCheesePizza extends Pizza {

    PizzaIngredientFactory pizzaIngredientFactory;

    public ChicagoStyleCheesePizza(PizzaIngredientFactory pizzaIngredientFactory) {
        this.pizzaIngredientFactory = pizzaIngredientFactory;
    }

    @Override
    void prepare() {
        // 준비하기 작업
        System.out.println("Preparing .... name = " + super.name);
        super.dough = pizzaIngredientFactory.createDough();
        dough.getDoughInfo();
        super.sauce = pizzaIngredientFactory.createSauce();
        super.cheese = pizzaIngredientFactory.createCheese();
        super.clam = pizzaIngredientFactory.createClam();
    }
}
