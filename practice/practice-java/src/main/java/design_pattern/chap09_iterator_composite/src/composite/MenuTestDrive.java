package design_pattern.chap09_iterator_composite.src.composite;

public class MenuTestDrive {
    public static void main(String[] args) {

        int a = 3;
        System.out.println("~a = " + ~a);

        MenuComponent pancakeHouseMenu = new Menu("팬케이크 하우스 메뉴", "아침메뉴");
        MenuComponent dinerMenu = new Menu("객체마을 식당 메뉴", "점심메뉴");
        MenuComponent cafeMenu = new Menu("카페 메뉴", "저녁메뉴");
        MenuComponent dessertMenu = new Menu("디저트 메뉴", "디저트를 즐겨보세요~!");
        MenuComponent allMenus = new Menu("전체 메뉴", "전체 메뉴");

        allMenus.add(pancakeHouseMenu);
        allMenus.add(dinerMenu);
        allMenus.add(cafeMenu);

        // 메뉴추가
        // 아침메뉴
        pancakeHouseMenu.add(new MenuItem("K&B 팬케이크 세트", "스크램블 에그와 토스트 곁들임", true, 2.99));
        pancakeHouseMenu.add(new MenuItem("레귤러 팬케이크 세트", "달걀후라이와 소시지가 곁들여짐", false, 2.99));
        pancakeHouseMenu.add(new MenuItem("블루베리 팬케이크", "신선한 블루베리와 블루베리 시럽으로 만듬", true, 3.49));
        pancakeHouseMenu.add(new MenuItem("와플", "와플 존맛탱~노량진 티라미슈와플 존맛탱~", true, 3.59));

        // 점심메뉴
        dinerMenu.add(new MenuItem("채식주의자용 BLT", "채식주의자용 BLT 묘사~~ ", true, 2.99));
        dinerMenu.add(new MenuItem("BLT", "BLT 묘사~~ ", false, 2.99));
        dinerMenu.add(new MenuItem("오늘의 스프", "감자 샐러드 곁들인 오늘의 스프", false, 3.99));
        dinerMenu.add(new MenuItem("핫도그", "핫도그는 존맛탱!~~~명랑핫도그 ~", false, 3.05));

        //저녁메뉴

        dinerMenu.add(new MenuItem(
                "파스타",
                "마리나라 소스 스파게티, 효모빵도 드립니다",
                true,
                3.89
        ));
        dinerMenu.add(dessertMenu);

        dessertMenu.add(new MenuItem(
                "애플파이",
                "바삭바삭한 크러스트에 바닐라 아이스크림이 얹혀 있는 애플파이",
                true,
                1.59
        ));

        Waitress waitress = new Waitress(allMenus);
        waitress.printMenu();

    }
}
