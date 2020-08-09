package test.basic;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class TestJava {

  @Test
  @DisplayName("set")
  public void set() {
    Set<String> set = new HashSet<>();
    set.add("b");
    set.add("a");
    set.add("2");
    set.add("d");

    System.out.println("set = " + set);

    Set<String> linked = new LinkedHashSet<>();
    linked.add("b");
    linked.add("a");
    linked.add("2");
    linked.add("d");

    System.out.println("linked = " + linked);


    Set<String> treeSet = new TreeSet<>();
    treeSet.add("b");
    treeSet.add("a");
    treeSet.add("2");
    treeSet.add("d");

    System.out.println("treeSet = " + treeSet);

  }

  @Test
  @DisplayName("AtomicInteger")
  public void atomic() {
    AtomicInteger atomicInteger = new AtomicInteger(10);
    System.out.println("atomicInteger.get() = " + atomicInteger.get());
  }

  private static Hashtable<String, Integer> ht = new Hashtable<>();
  private static HashMap<String, Integer> hm = new HashMap<>();
  private static HashMap<String, Integer> hmSyn = new HashMap<>();
  private static Map<String, Integer> hmSyn2 = Collections.synchronizedMap(new HashMap<>());
  private static ConcurrentHashMap<String, Integer> chm = new ConcurrentHashMap<>();

  @Test
  @DisplayName("map-멀티쓰레드")
  public void map() {

    ExecutorService es = Executors.newFixedThreadPool(10);

    for( int j = 0 ; j < 10; j++ ){
      es.execute(new Runnable() {
        @Override
        public void run() {
          for( int i = 0; i < 5000; i++ ){

            String key = String.valueOf(i);

            ht.put(key, i);
            hm.put(key, i);
            chm.put(key, i);
            hmSyn2.put(key, i);

            synchronized (hmSyn) {
              hmSyn.put(key, i);
            }
          }
        }
      });
    }

    es.shutdown();
    try {
      es.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    System.out.println("Hashtable size is "+ ht.size());
    System.out.println("HashMap size is "+ hm.size());
    System.out.println("ConcurrentHashMap size is "+ chm.size());
    System.out.println("HashMap(synchronized) size is "+ hmSyn.size());
    System.out.println("synchronizedMap size is "+ hmSyn2.size());

  }

  @Test
  @DisplayName("copyOf")
  public void copyOf(){
    String[] str = {"A", "B", "C"};
    String[] copyOf = Arrays.copyOf(str, str.length + 1);
    copyOf[copyOf.length - 1] = "new!!";
    for (String s : str) {
      System.out.println("s = " + s);
    }
    System.out.println("========================");

    for (String s : copyOf) {
      System.out.println("s = " + s);
    }
    System.out.println("========================");

    str = copyOf;
    for (String s : str) {
      System.out.println("s = " + s);
    }
  }

  @Test
  @DisplayName("bit")
  public void bit(){
    int i = Integer.bitCount(10);
    System.out.println("i = " + i);

    String string = Integer.toBinaryString(20);
    System.out.println("string = " + string);

  }

  @Test
  @DisplayName("배열출력")
  public void arrayPrint(){
    int[] a = {1, 2, 3, 4, 5};
    System.out.println(Arrays.toString(a));

    int[][] b = {{1, 2}, {3, 4}, {5, 6}};
    System.out.println(Arrays.toString(b));
    System.out.println(Arrays.deepToString(b));

    int[][][] c = {{{1, 2}, {1, 2}}, {{5, 4}, {7, 8}}};
    System.out.println(Arrays.deepToString(c));

  }

  @Test
  @DisplayName("Math")
  public void max() {
    int max = Math.max(0, 0);
    System.out.println("max = " + max);
  }
}
