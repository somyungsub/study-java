# 객체지향
- 비용

## 비용
- LOC(lines of cost)

- 실행시간 구하기 ms  

```java
long start = System.currentTimeMillis();
...
long end = System.currentTimeMillis();
long elapsed = end - start;
```
- 실행시간 -> nano 초로 바꿔보자!
```java
long start = System.nanoTime();
...
long end = System.nanoTime();
long elapsedNano = end - start;
```

> 군데 군데 작성된 이코드를... 바꾸는데 시간이 오래걸리게 됨..

## 단순한 예
- 여러줄 .. 한 메서드 내에 여러가지 내용이 담김..
- 버그투성이가 됨
- 추가된 내용을 그코드내에 바로 추가 시켜서 종속성 등...수정 및 추가작업을 통해 버그를 만들어냄

## 주요 원인
- 코드 분석 시간 증가
- 코드 변경 시간 증가

## 소프트웨어의 가치 : 변화
- 변화에 잘 대응해야 한다
- `keep being useful in a changing world` - Jessica Kerr
  - 소프트웨어는 시간이 흐를수록 변경 됨. 유저가 있다면 계속 업데이트
  - 소스코드 추가 및 변경에 유연하고, 시간이 줄일 수 있는 유지보수성 유지
  - 시대가 바뀌면 발 맞춰 변화해야 하지! 그대로 있다면 망한 소프트웨어다 
- **낮은 비용으로 변화 할 수 있도록 해야한다**
  - 패러다임 : 객체지향, 함수형, 리액티브, ...
  - 코드 설계, 아키텍처 : DRY, TDD, SOLID, DDD, 클린아키, 클린코드, MSA, ...
  - 업무 프로세스 / 문화 : 애자일, DevOps, ...

## 객체 지향과 비용
- 캡슐화 + 다형성(추상화)


## 객체
- 절차 vs 객체지향 
- 절차 : 동일한 코드의 중복, 복잡성 상승, 가독성 저하 
- 객체지향 : 데이터, 메서드를 통해 연결 및 호출, 모듈화 
- 기능명세 : 메서드를 이용해서 기능구현 

## 캡슐화
- 데이터 + 관련기능 묶기
- 기능 구현은 외부에 감추는것. 구현에 사용된 데이터의 상세 내용을 외부에 감춤
- 정보은닉 의미 포함
- 외부에 영향 없이 객체 내부 구현. 변경 가능
 
```java
if(acc.getMembership() == REQULAR && acc.getExpDate().isAfter(now()){
  // ... 정회원 기능
}
// 몇년 이상 ... 잘사용하다가.... 일부 기능 추가 (정회원 1개월할인 등)

if(acc.getServiceDate().isAfter(fiveYearAgo) && acc.getExpDate().isAfter(now())
   || acc.getServiceDate().isBefore(fiveYearAgo) && addMonth(acc.getExpDate().isAfter(now())){
  
  // ... 정회원 기능 
}
```
- 요구사항 변경 -> 데이터 구조/사용에 변화를 발생 시킴 
- 데이터를 사용하는 코드의 수정 발생 
- 절차지향 ... 또 기능 추가? 또변경, 수정 
- Date -> LocalDateTime으로 변경 -> 연쇄적 변경이...
- 기능을 제공하고, 구현상세를 감춤
  - 예를들면, hasRegularPermission() 기능 제공 -> 제공하는 쪽에서 구현, 외부에서는 이 기능을 알지 못하게
  - **변경 -> 제공하는 hasRegularPermission() 내부만 변경하면 됨**
  - 캡슐화를 하면, 연쇄적인 변경 전파를 최소화 
  - 의미있는 구현(가독성)

### 캡슐화를 위한 규칙
#### `Tell, Don't Ask` (데이터 달라하지 말고, 해달라고 하기)
- 너가좀 해줘. 데이터 주면 내가 할게가 아님. 너가 다 처리해서 나한테 좀 줘
```java
if(acc.getMembership() == REGULAR){
  // ..정회원기능
}
// 에서
if(acc.hasRegularPermission()){
  // ..정회원기능
} 
```
- 정회원 자격이 있는지 궁금해... 너가좀 해줘라 -> **hasRegularPermission()**

#### Demeter's Law
- 메서드에서 생성한 객체의 메서드만 호출
- 파라미터로 받은 객체의 메서드만 호출
- 필드로 참조하는 객체의 메서드만 호출

```
acc.getExpDate().isAfter(now) 
-> 
acc.isExpired()
```
--- 
``` 
Date date = acc.getExpDate();
date.isAfter(now);
->
acc.isValid(now)
```
---
- 연속(체인)해서 호출하던 부분을 -> **하나의 메서드를 호출하는 방식으로 작성**

### 캡슐화 정리
- 기능의 구현을 외부에 감춤
- 캡슐화를 통해 기능을 사용하는 코드에 영햐을 주지 않고(또는 최소화), 내부 구현을 변강할 수 있는 유연함을 제공할 수 있음

### 캡슐화 연습

- 연습 1
```java
public class 캡슐화연습 {
  private PasswordEncoder passwordEncoder;

  public 캡슐화연습(PasswordEncoder passwordEncoder) {
    this.passwordEncoder = passwordEncoder;
  }

  public AuthResult authenticate(String id, String pw) {
    Member mem = findOne(id);

    if (mem == null) {
      return AuthResult.NO_MATCH;
    }

    if (mem.getVerificationEmailStatus() != 2) {
      return AuthResult.NO_EMAIL_VERIFIED;
    }

    if (passwordEncoder.isPasswordValid(mem.getPassword(), pw, mem.getId())) {
      return AuthResult.SUCCESS;
    }

    return AuthResult.NO_MATCH;
  }

  private Member findOne(String id) {
    return null;
  }

}
```

- 캡슐화 적용 -> `데이터 달라는 부분`
```
if(mem.getVerificationEmailStatus() != 2){} 
->
if(!mem.isEmailVerified()) {}
```

- 연습 2
마틴파울러 - 리팩토링 출처 

```java
public class Rental {
  private Movie move;
  private int daysRented;
  
  public int getFrequentRenterPoints(){
    if(movie.getPriceCode() == Movie.NEW_RELEASE && daysRented > 1){
      return 2;
    } else {
      return 1;
    }
  }
}
```
- 캡슐화 적용 -> `데이터 달라는 부분`
```
if(movie.getPriceCode() == Movie.NEW_RELEASE && daysRented > 1){}
->
return getFrequentRenterPoints(daysRented);
```

```java
public class Rental {
  private Movie move;
  private int daysRented;
  
  // 데이터를 구현해서 내놔!
  public int getFrequentRenterPoints(){
    return movie.getFrequentRenterPoints(daysRented);
  }
}

// Movie 일부
public class Movie{
  // ....
  private int priceCode;
  private final int NEW_RELEASE = 2;
  public int getFrequentRenterPoints(int daysRented){
    return priceCode == NEW_RELEASE && daysRented > 1 ? 2 : 1; 
  }
}
```

- 연습 3
```java
public void verifyEmail(String token) {
  Member mem = findByToken(toekn);
  if(mem.getVerificatoinEmails() == 2){
    throw new AleadyVerifiedException();
  } else {
    mem.setVerificationEmailStatus(2);
  }
}
```

- 적용후 
```java
public void verifyEmail(String token) {
  Member mem = findByToken(toekn);
  mem.verifyEmail();
}

public class Member{
  private int verificationEmailStatus;
  
  public void verifyEmail(){
    if(isEmailVerified()){
      throw new AleadyVerifiedException();
    }
    this.verificationEmailStatus = 2;
  }
  
  public boolean isEmailVerified() {
    return mem.getVerificatoinEmails() == 2
  }
}
```



