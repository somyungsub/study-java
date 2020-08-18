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
--- 

## 다형성과 추상화

### 다형성
- 여러(poly) 모습(morph)을 갖는것 (Polymorphism)
- 객체지향에서는 한객체가 여러타입을 갖는 것
  - 즉 한 객체가 여러타입의 기능을 제공, 하위타입은 상위타입도 됨 

### 추상화
- 데이터나 프로세스 등을 의미가 비슷한 개념이나 의미 있는 표현으로 정의하는 과정
- 두가지 방식의 추상화
  1. 특정한 성질
  2. 공통 성질(일반화)
  - ex) Money -> 통화, 금액 등
- 서로 다른 구현 추상화
  - SCP로 파일 업로드, HTTP로 데이터 전송, DB 테이블 삽입 -> 추상화 -> 데이터 푸쉬 요청 
  - 타입 추상화 -> 여러 구현 클래스를 대표하는 `상위타입 도출`. 추상화 - 구현 관계는 타입 `상속으로 연결`

#### 추상타입의 사용
- 추상타입을 이용

```
Notifier notifier = getNotifier();
notifier.notify(someNoti); 
```
- **추상 타입은 구현을 감춤** -> 기능의 구현이 아닌 의도를 더 잘 드러냄


#### 추상 타입 사용에 따른 이점 -> `유연함` 
- 콘크리트 클래스를 직접 사용하면...

> 기존 처음은 SMS 문자발송에 대한 취소 발송
```java
private SmsSender smsSender;
public void cancel(String ono) {
  // .. 주문취소 처리
  smsSender.sendSms(...); 
}
```

> SMS 문자발송 + kakao 톡 발송 (추가)
```java
private SmsSender smsSender;
private KakaoPush kakaoPush;

public void cancel(String ono) {
  // .. 주문취소 처리
  if(pushEnamled){
    kakaoPush.pushTalk(...);
  }
  smsSender.sendSms(...); 
}
```

> SMS 문자발송 + kakao 톡 발송 + 메일 발송(추가)
```java
private SmsSender smsSender;
private KakaoPush kakaoPush;
private MailService mailSvc;

public void cancel(String ono) {
  // .. 주문취소 처리
  if(pushEnamled){
    kakaoPush.pushTalk(...);
  } else {
    smsSender.sendSms(...);
  }
  mailSvc.sendMail(...);
}
```

> 요구 사항 변경에 따라 코드가 계속 변경됨.  
> **내용은 전부 발송이라는 부분이 동일**  
> `추상화가 필요한 시점!!`
> 이점 : 변경에 대해 유연. 비즈니스 로직(주문취소발송)은 변경되지 않고, 인터페이스의 내용만 변경하면 그대로 적용이됨

```java
public void cancel(String ono){
  // 주문취소 처리
  Notifier notifier = NotifierFactory.instance().getNotifier(...);
  notifier.notify(...);
}

public interface NotifierFactory {
  Notifer getNotifier(...);
  NotifierFactory instance(){
    return new DefaultNotifierFactory();
  }
}

public class DefaultNotifierFactory() implements NotifierFactory {
  public Notifier getNotifier(...){
    if(pushEnabled){
      return new KaKaoNotifier();
    } else{
      return new SmsNotifier();
    }
  } 
}
```

### 추상화는 의존 대상이 변경하는 시점에 적용
- 무턱대고 남발하는 것은 안좋다
- 추상화 -> 추상 타입 증가 -> 복잡도 증가로 귀결 됨
  - 아직 존재하지 않는 기능에 대한 이른 추상화는 주의 -> 잘못된 추상화 가능성, 복잡도만 증가 시킴 
  - `실제 변경/확장이 발생할 때 추상화 시도!!` -> 리팩토링을 하는 거지 

### 추상화를 잘하려면 
- 구현을 한 이유가 무엇 때문인지 !! `비즈니스에 대한 정확한 이해와 생각`과 파악이 중요
- ex) 통지하는거군! 이메일 발송통지, 문자발송통지, 카카오톡, 또 다른게 생길 수도 있겠군?? -> `추상화!`


### 추상화 예시
- 기능예시
  1. 클라우드 파일 통합 관리 기능 개발
  2. 대상 클라우드 : 드랍박스, 박스, 원드라이브, 구글드라이브 ...
  3. 주요기능 : 각 클라우드의 파일 목록 조회, 다운로드, 업로드, 삭제, 검색

- 추상화 하지 않은 코드 vs 추상화 한 코드 
  1. 목록조회
  2. 파일 다운로드
  3. 그외 기타 기능들
  
#### 추상화 되지 않은 코드
- 모두 비슷하게 구현될 예정 .. `if ~ else` 등으로 구현 되겠지?
- 클라우드가 추가 된다면...? `else if`로 추가 되겠지??...
- LOC도 늘어나고, 유자보수 비용도 늘어나고 ... 흠;;
- 코드 구조가 길어지고 복잡해짐
  - 새로운 클라우드 추가? 관련 기능 모든 메서드(목록조회, 파일업로드 ...)에 if 블록 복붙 추가 필요...
  - 중첩 if~else는 복잡도가 배로 증가
  - if~else가 많을수록 진척 더딤. (신중모드... 수정에 대한 자신감 하락...) 
- 관련 코드가 여러곳에 분산됨
  - 한 클라우드 처리와 관련된 코드가 여러 메서드에 흩어지게 됨
- **결과적으로... 가독성과 분석속도가 저하**
  - 코드 추가에 따른 비용이 증가
  - 실수하기 쉽고, 버그발생이 쉬우며, 디버깅 또한 만만치 않음.. if else 계 브리크 포인트 설정 갯수 등....

#### 추상화를 해보자
- 드랍박스, 박스, 구글클라우드, 원드라이브 -> `클라우트 파일시스템 설계!`
   
#### 추상팩토리 패턴 적용 
1. 파일시스템 구현
2. 추상팩토리
3. 제품

- 추상화 타입을 사용(다형성이용)
- 파라미터, 반환 타입을 상위 타입으로 사용 -> **하위 타입을 전달만 하면 코드 수정없이 작동할 수 있게 구현**
- 제품추가가 필요하다면? 구현을 통해 추가만 시키면 끝! -> `이것이 바로 OCP`
- `추가되더라도 다른 코드는 변경이 없어야 한다.`

--- 

## 상속보단 조립



  

