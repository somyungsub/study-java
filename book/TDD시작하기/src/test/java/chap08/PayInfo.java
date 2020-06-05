package chap08;

public class PayInfo {

  private String data1;
  private String data2;
  private int data3;

  public PayInfo(String data1, String data2, int data3) {
    this.data1 = data1;
    this.data2 = data2;
    this.data3 = data3;
  }

  public PayInfo() {
  }

  public String getData1() {
    return data1;
  }

  public void setData1(String data1) {
    this.data1 = data1;
  }

  public String getData2() {
    return data2;
  }

  public void setData2(String data2) {
    this.data2 = data2;
  }

  public int getData3() {
    return data3;
  }

  public void setData3(int data3) {
    this.data3 = data3;
  }
}
