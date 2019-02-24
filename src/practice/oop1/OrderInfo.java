package practice.oop1;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.*;

/*
    주문한 메뉴 관련 정보 추출
 */
public class OrderInfo {
    private List<Coffee> coffeeList = new ArrayList<>();

    // 메뉴 추가하기
    public void add(Coffee coffee) {
        coffeeList.add(coffee);
    }


    // 메뉴 주문 정보 가공하여 제공
    public Map<Coffee, Long> getMenuInfo() {
        return coffeeList.stream().collect(groupingBy(coffee -> coffee, counting()));
    }

    public int getTotalPrice() {
        return coffeeList.stream().collect(summingInt(coffee -> coffee.getPrice()));
    }

}
