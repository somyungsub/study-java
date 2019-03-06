package practice.oop1;

/*
    메뉴(커피) 정보
 */
public enum Coffee {
    AMERICANO("Americano", 1500),
    CAPPUCCINO("Cappuccino", 2000),
    CARAMEL_MAKI("CaramelMaki", 2500),
    ESPRESSO("Espresso", 2500),
//    COLD_BREW("ColdBrew", 2000),
//    SMOODY("Smoody", 3000),
    ;

    private String coffeeName;
    private int coffeePrice;

    Coffee(String coffeeName, int coffeePrice) {
        this.coffeeName = coffeeName;
        this.coffeePrice = coffeePrice;
    }

    public int getPrice() {
        return coffeePrice;
    }

    public String getCoffeeName() {
        return coffeeName;
    }

}
