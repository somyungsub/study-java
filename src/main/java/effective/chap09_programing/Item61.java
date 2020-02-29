package effective.chap09_programing;

import java.util.Arrays;
import java.util.List;

public class Item61 {
  public static void main(String[] args) {


    System.out.println(DownView.DOWN_VIEW_DELETE.toString());
    System.out.println(DownView.DOWN_VIEW_FILEPATH);
    System.out.println(DownView.DOWN_VIEW_OUTFILENAME);
    System.out.println(DownView.DOWN_VIEW_REALFILENAME);

    String real = "file:c:/abc/dd.xml";
    String real2 = "file:../../dd.xml";

    boolean match = Arrays.stream(new String[]{"../", "./", "\\", "..\\"}).anyMatch(s -> real.contains(s));
    boolean match2 = Arrays.stream(new String[]{"../", "./", "\\", "..\\"}).anyMatch(s -> real2.contains(s));
    System.out.println("match = " + match);
    System.out.println("match2 = " + match2);


    System.out.println(nvl(null, "02"));
    System.out.println(nvl("01", "02"));
    System.out.println(nvl(1, "02"));
    System.out.println(nvl(10.5, "0222"));
    System.out.println(nvl(Integer.valueOf(10), "155"));
    System.out.println(nvl(Integer.valueOf(10), Integer.valueOf(500)));
    System.out.println(nvl(Integer.valueOf(10), "155").getClass().getTypeName());
    System.out.println(nvl(null, "155").getClass().getTypeName());


    System.out.println(" ================= ");
    System.out.println(nvl2(null, "02"));
    System.out.println("" + nvl2("01", "02"));
    System.out.println(""+nvl2("01", "02"));
    System.out.println(nvl2("01", "02"));
    System.out.println(nvl2(1, "02"));
    System.out.println(nvl2(10.5, "0222"));
    System.out.println(nvl2(Integer.valueOf(10), "155"));
    System.out.println(nvl2(Integer.valueOf(10), Integer.valueOf(500)));
    System.out.println(nvl2(Integer.valueOf(10), "155").getClass().getTypeName());
    System.out.println(nvl2(null, "155").getClass().getTypeName());


    Integer it = (Integer) nvl2(Integer.valueOf(10), Integer.valueOf(100));
    Integer it2 = nvl(null, Integer.valueOf(105));

    System.out.println("it2 = " + it2);
    System.out.println("it = " + it);


//    List<String> list = new ArrayList<>();
    List<String> list = List.of("A", "B");
    list.iterator().forEachRemaining(s -> {
              System.out.println("s = " + s);
              System.out.println(" ddddd ");
            }
    );


  }

  private static <T> T nvl(T t1, T t2) {
    T t = t1 == null ? t2 : t1;
    return t;
  }

  private static Object nvl2(Object t1, Object t2) {
    return t1 == null ? t2 : t1;
  }

  private enum DownView {
    DOWN_VIEW_FILEPATH("_FILEPATH"),
    DOWN_VIEW_REALFILENAME("_REALFILENAME"),
    DOWN_VIEW_OUTFILENAME("_OUTFILENAME"),
    DOWN_VIEW_DELETE("_DELETE"),
    ;

    private final String value;
    DownView(String value){
      this.value = value;
    }

//    public String getValue() {
//      return value;
//    }
  }
}
