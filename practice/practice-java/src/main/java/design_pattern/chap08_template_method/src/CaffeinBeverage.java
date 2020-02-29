package design_pattern.chap08_template_method.src;

public abstract class CaffeinBeverage {

    // 템플릿메서드 패턴 -> 틀(알고리즘) 형태 제공
    final public void prepareRecipe() {
        boilWater();
        brew();
        pourInCup();
        addCondiments();
    }

    public abstract void brew();

    public abstract void addCondiments();

    void boilWater() {
        System.out.println("물 끓이는 중!");
    }

    void pourInCup() {
        System.out.println("컵에 따르는 중");
    }


}
