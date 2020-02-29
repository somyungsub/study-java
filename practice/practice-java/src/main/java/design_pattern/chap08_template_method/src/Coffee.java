package design_pattern.chap08_template_method.src;

public class Coffee extends CaffeinBeverage {

    @Override
    public void brew() {
        System.out.println("필터를 통해서 커피를 우려내는 중");
    }

    @Override
    public void addCondiments() {
        System.out.println("설탕과 우유를 추가하는 중");
    }


    //    void prepareRecipe() {
//        boilWater();
//        brew();
//        pourInCup();
//        addCondiments();
//    }


//    public void addCondiments() {
//        System.out.println("설탕과 우유를 추가하는 중");
//    }
//
//
//    public void brew() {
//        System.out.println("필터를 통해서 커피를 우려내는 중");
//    }

//    void boilWater() {
//        System.out.println("물 끓이는 중");
//    }
//
//    void pourInCup() {
//        System.out.println("컵에 따르는 중..");
//    }

}
