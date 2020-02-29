package design_pattern.chap04_factory.src;

public class NYPizzaStore extends PizzaStore {

    // 피자 생성 코드
    @Override
    public Pizza createPizza(String type) {

        PizzaIngredientFactory ingredientFactory = NYPizzaIngredientFactory.getInstance();  // 싱글턴 대상

        if (type.equals("cheese")) {
            return new CheesePizza(ingredientFactory);
        } else if (type.equals("veggie")) {
            return new NYStyleVeggiePizza();
        } else if (type.equals("clam ")) {
            return new NYStyleClamPizza();
        } else if (type.equals("pepperoni")) {
            return new NYStylePepperoniPizza();
        } else {
            return null;
        }
    }
}

