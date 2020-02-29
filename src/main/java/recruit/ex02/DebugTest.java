package recruit.ex02;

import org.junit.Test;

public class DebugTest {
    private int n = 1;

    public void first() {
        int n = 10, m = 20;
        this.n = n + m;
        System.out.println("first 메소드 로컬변수 : " + n);
        System.out.println("first 메소드 인스턴스 변수: " + n);
        second(5);
        String s = null;



    }

    public int second(int n) {
        int m = 2;
        for (m = m; m < 5; m++) {
            System.out.println("m = " + m);
        }
        this.n = n + m;
        System.out.println("second 메소드 로컬변수 : " + n);
        System.out.println("second 메소드 인스턴스 변수: " + n);
        System.out.println("adasdasdasa" + n);
        return n + 10;
    }

    public int twoSum(int a, int b) {
        return a + b;
    }


}
