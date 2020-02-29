package effective.chap08_method;

import java.util.Optional;
import java.util.stream.Stream;

public class Item55 {
  private static String mm;
  public static void main(String[] args) {
    mm = new String();
    Item55 dd = new Item55();
    // isPresent method
    ProcessHandle ph = ProcessHandle.current();
    Optional<ProcessHandle> processHandle = ph.parent();
    System.out.println(processHandle.isPresent() ? String.valueOf(processHandle.get().pid()) : "N/A");  // 기존코드
    System.out.println(ph.parent().map(h->String.valueOf(h)).orElse("N/A"));  // java 8

    Stream<Optional<String>> optionalStream = Stream.of(Optional.of("ABCDEFG"));
    System.out.println(optionalStream.filter(Optional::isPresent).map(Optional::get));
//    System.out.println(optionalStream.flatMap(Optional::stream));

    System.out.println("?????");

  }
}
