package design_pattern.chap08_template_method.src;

public class Tea extends CaffeinBeverage {
    @Override
    public void brew() {
        System.out.println("차를 우려내는 중");
    }

    @Override
    public void addCondiments() {
        System.out.println("레몬추출을 추가 하는중 ");
    }


    //    void prepareRecipe() {
//        boilWater();
//        brew();
//        pourInCup();
//        addCondiments();
//    }


//    public void addCondiments() {
//        System.out.println("레몬추출을 추가 하는중 ");
//    }
//
//    public void brew() {
//        System.out.println("차를 우려내는 중");
//    }

//    void pourInCup() {
//        System.out.println("컵에 따르는 중..");
//    }
//
//    void boilWater() {
//        System.out.println("물 끓이는 중");
//    }
}
