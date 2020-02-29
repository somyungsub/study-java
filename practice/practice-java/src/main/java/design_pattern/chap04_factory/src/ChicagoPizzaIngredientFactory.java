package design_pattern.chap04_factory.src;

public class ChicagoPizzaIngredientFactory implements PizzaIngredientFactory {


    @Override
    public Dough createDough() {
        return new ThinCrustDough();
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

    // 싱글턴 구현
    public static PizzaIngredientFactory getInstance() {
        return LazyHolder.INSTANCE;
    }

    private static class LazyHolder {
        public static final ChicagoPizzaIngredientFactory INSTANCE = new ChicagoPizzaIngredientFactory();
    }

}
