package design_pattern.chap09_iterator_composite.src;

public class DinerMenu {
    static final int MAX_SIZE = 6;
    int numberOfItems = 0;
    MenuItem[] menuItems;

    public DinerMenu() {
        menuItems = new MenuItem[MAX_SIZE];
        addItem("채식주의자용 BLT", "채식주의자용 BLT 묘사~~ ", true, 2.99);
        addItem("BLT", "BLT 묘사~~ ", false, 2.99);
        addItem("오늘의 스프", "감자 샐러드 곁들인 오늘의 스프", false, 3.99);
        addItem("핫도그", "핫도그는 존맛탱!~~~명랑핫도그 ~", false, 3.05);
    }

    public void addItem(String name, String description, boolean vegetarian, double price) {

        if (numberOfItems >= MAX_SIZE) {
            System.out.println("죄송합니다. 메뉴가 꽉찼습니다. 더이상 추가할 수 없습니다.");
            return;
        }

        MenuItem menuItem = new MenuItem(name, description, vegetarian, price);
        menuItems[numberOfItems] = menuItem;
        numberOfItems++;
    }

//    public MenuItem[] getMenuItems() {
//        return menuItems;
//    }

    // 커스텀 Iterator 적용
    public Iterator iterator() {
        return new DinerMenuIterator(menuItems);
    }


}
