package design_pattern.chap04_factory.src;

public class NYPizzaIngredientFactory implements PizzaIngredientFactory {

    private NYPizzaIngredientFactory() {
    }

    @Override
    public Dough createDough() {
        return null;
    }

    @Override
    public Sauce createSauce() {
        return null;
    }

    @Override
    public Cheese createCheese() {
        return null;
    }

    @Override
    public Veggies[] createVeggies() {
        return new Veggies[0];
    }

    @Override
    public Pepperoni createPepperoni() {
        return null;
    }

    @Override
    public Clams createClam() {
        return null;
    }

    public static PizzaIngredientFactory getInstance() {
        return LazyHolder.INSTANCE;
    }

    private static class LazyHolder {
        public static final PizzaIngredientFactory INSTANCE = new NYPizzaIngredientFactory();
    }
}
