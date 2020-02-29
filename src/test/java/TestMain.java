import org.junit.Test;

import java.util.stream.Stream;

public class TestMain {
    @Test
    public void test1() {
        long start = System.currentTimeMillis();
        Long sum = Stream.iterate(1L, i -> i + 1)
                .limit(1000000).parallel().reduce(0L, Long::sum);
        long end = System.currentTimeMillis();
        System.out.println("total : " + (end - start));
        System.out.println("value : " + sum);

    }

    @Test
    public void test2() {
        int sum = 0;
        long start = System.currentTimeMillis();
        for (int i = 1; i <= 1000000; i++) {
            sum += i;
        }
        long end = System.currentTimeMillis();
        System.out.println("total : " + (end - start));
        System.out.println("value : " + sum);
    }
}
