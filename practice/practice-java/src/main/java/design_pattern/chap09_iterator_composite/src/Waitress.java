package design_pattern.chap09_iterator_composite.src;

public class Waitress {
    PancakeHouseMenu pancakeHouseMenu;
    DinerMenu dinerMenu;

    public Waitress(PancakeHouseMenu pancakeHouseMenu, DinerMenu dinerMenu) {
        this.pancakeHouseMenu = pancakeHouseMenu;
        this.dinerMenu = dinerMenu;
    }

    public void printMenu() {

        System.out.println("===== 메뉴 \n ---- \n 아침메뉴");
        printMenu(pancakeHouseMenu.iterator());

        System.out.println("\n 점심메뉴");
        printMenu(dinerMenu.iterator());

    }

    private void printMenu(Iterator iterator) {
        while (iterator.hasNext()) {
            MenuItem next = (MenuItem) iterator.next();
            System.out.println(next);
        }
    }
}
