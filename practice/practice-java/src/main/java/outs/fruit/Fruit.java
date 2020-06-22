package outs.fruit;

/*
  과일 표현하는 클래스(객체)
 */
public class Fruit {
  private String name;  // 과일이름
  private int price;    // 과일가격
  private int stock;    // 과일재고

  public Fruit() {
  }

  public Fruit(String name, int price, int stock) {
    this.name = name;
    this.price = price;
    this.stock = stock;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public int getStock() {
    return stock;
  }

  public void setStock(int stock) {
    this.stock = stock;
  }
}
