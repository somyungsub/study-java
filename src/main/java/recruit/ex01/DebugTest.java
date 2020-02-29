package recruit.ex01;

public class DebugTest {

    public static void main(String[] args) {
        Debug test = new Debug();
        test.first();
    }

    private static class Debug {

        private int n = 1;

        public void first() {
            int n = 10, m = 20;
            this.n = n + m;
            System.out.println("first 메소드 로컬변수 : " + n);
            System.out.println("first 메소드 인스턴스 변수: " + this.n);
            second(5);
        }

        public void second(int n) {
            int m = 0;

            for (; m < 5; m++) {
                System.out.println("m = " + m);
            }

            this.n = n + m;
            System.out.println("second 메소드 로컬변수 : " + n);
            System.out.println("second 메소드 인스턴스 변수: " + this.n);

            third();



            four();

        }
        public void third() {
//            String s = "abc";
            String s = null;
            for (int m = 0; m < 5; m++) {
                System.out.println(m + s.toString());
            }
        }

        private void four() {
            System.out.println("four called");
            System.out.println("four called");
        }

    }
}
