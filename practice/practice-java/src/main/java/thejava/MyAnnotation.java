package thejava;

import java.lang.annotation.*;

/*
  @Retention
   - 유지기간
   - RetentionPolicy.CLASS (기본값)
 */
@Retention(RetentionPolicy.RUNTIME)
//@Target({ElementType.TYPE, ElementType.FIELD})  // 붙일 수 있는 곳 명시
@Inherited  // 상속
public @interface MyAnnotation {

  String name() default "ssosso";  // 기본값 선언 가능

  int number() default 100;         // 기본값 잉 없으면 선언안할시 에러남

  String value(); // 프로퍼티 생략이 가능


}
