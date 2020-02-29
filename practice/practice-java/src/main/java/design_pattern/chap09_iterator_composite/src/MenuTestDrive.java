package design_pattern.chap09_iterator_composite.src;

public class MenuTestDrive {
    public static void main(String[] args) {
        Waitress waitress = new Waitress(new PancakeHouseMenu(), new DinerMenu());
        waitress.printMenu();
    }
}
