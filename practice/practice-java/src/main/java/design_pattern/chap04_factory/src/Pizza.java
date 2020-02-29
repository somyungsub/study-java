package design_pattern.chap04_factory.src;

/*
    제품
 */
public abstract class Pizza {

    String name;
//    String dough;
//    String sauce;
//    List toppings = new ArrayList();

    Dough dough;
    Sauce sauce;
    Veggies[] veggies;
    Cheese cheese;
    Pepperoni pepperoni;
    Clams clam;

    //    public void prepare() {
//    }

    // 피자만들기 위한 선준비
    abstract void prepare();

    public void bake() {
    }

    public void cut() {
    }

    public void box() {
    }

    public void setName(String name){
        this.name = name;
    }
}
