package design_pattern.chap09_iterator_composite.src;

import java.util.ArrayList;

public class PancakeHouseMenu {

    ArrayList<MenuItem> menuItems;

    public PancakeHouseMenu() {
        menuItems = new ArrayList<>();
        // Item add
        addItem("K&B 팬케이크 세트", "스크램블 에그와 토스트 곁들임", true, 2.99);
        addItem("레귤러 팬케이크 세트", "달걀후라이와 소시지가 곁들여짐", false, 2.99);
        addItem("블루베리 팬케이크", "신선한 블루베리와 블루베리 시럽으로 만듬", true, 3.49);
        addItem("와플", "와플 존맛탱~노량진 티라미슈와플 존맛탱~", true, 3.59);
    }

    public void addItem(String name, String description, boolean vegetarian, double price) {
        MenuItem menuItem = new MenuItem(name, description, vegetarian, price);
        menuItems.add(menuItem);
    }

//    public ArrayList getMenuItems() {
//        return menuItems;
//    }

    // Iterator 적용
    public Iterator iterator() {
        return new PancakeHouseIterator(menuItems);
//        return menuItems.iterator();
    }


}
