package chap01;


import io.reactivex.Flowable;

public class Main {
  public static void main(String[] args) {

    // 구독시 마다 같은 값 출력 -> 이미 생성된 값을 출력할 뿐! (즉시실행 관점)
    Flowable<Long> flowable = Flowable.just(System.currentTimeMillis());
    flowable.subscribe(System.out::println);
    flowable.subscribe(System.out::println);
    flowable.subscribe(System.out::println);
    flowable.subscribe(System.out::println);
    flowable.subscribe(System.out::println);

    System.out.println("===========================");

    // 구독시 마다 다른 값 출력 -> 실행 시점에 해당 함수정의를 실행하게 되므로.. (지연실행 관점)
    Flowable flowable1 = Flowable.fromCallable(System::currentTimeMillis);
    flowable1.subscribe(System.out::println);
    flowable1.subscribe(System.out::println);
    flowable1.subscribe(System.out::println);
    flowable1.subscribe(System.out::println);
    flowable1.subscribe(System.out::println);

    System.out.println("===========================");

    // 생성 -> 필터링(filter) -> 변환(map - 재생성)
    Flowable<Integer> flowable2 = Flowable.just(1, 2, 3, 4, 5, 6, 7, 8, 9)
            .filter(i -> i % 2 == 0)
            .map(i -> i * 100);

    // 소비
    flowable2.subscribe(o -> System.out.println("o = " + o));
    flowable2.subscribe(o -> System.out.println("o = " + o));
  }
}
