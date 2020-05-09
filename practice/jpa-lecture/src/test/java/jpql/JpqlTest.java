package jpql;

import jpql.MemberJpql;
import jpql.MemberJpqlType;
import jpql.ProductJpql;
import jpql.TeamJpql;
import org.junit.jupiter.api.*;
import practice.jpashop.domain.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Collection;
import java.util.List;

class JpqlTest {
  static EntityManagerFactory emf;

  EntityManager em;

  @BeforeAll
  static void setUpAll() {
    emf = Persistence.createEntityManagerFactory("hello");
  }

  @AfterAll
  static void closeAll() {
    emf.close();
  }

  @BeforeEach
  void setUpEach() {
    em = emf.createEntityManager();
    em.getTransaction().begin();
  }

  @AfterEach
  void closeEach() {
    em.close();
    em.getTransaction().commit();
  }

  @Test
  @DisplayName("JPQL 기초")
  public void jpql() {
    final List<Member> resultList = em.createQuery(
            "select m from Member m where m.username like '%kim%'", Member.class
    ).getResultList();

    System.out.println("resultList = " + resultList);
  }

  @Test
  @DisplayName("Criteria 기초")
  public void criteria() {
    CriteriaBuilder cb = em.getCriteriaBuilder();
    final CriteriaQuery<Member> query = cb.createQuery(Member.class);
    final Root<Member> from = query.from(Member.class);
    final CriteriaQuery<Member> cq = query.select(from).where(cb.equal(from.get("username"), "kim"));
    final List<Member> resultList = em.createQuery(cq).getResultList();
    System.out.println("resultList = " + resultList);
  }


  @Test
  @DisplayName("QueryDSL 기초")
  public void queryDSL() {

  }

  @Test
  @DisplayName("Native Query 기초")
  public void nativeQuery() {
    final List resultList = em.createNativeQuery("select member_id, city, street from member").getResultList();
    System.out.println("resultList = " + resultList);
  }

  @Test
  @DisplayName("Spring JDBC, JDBC 직접 작성시")
  public void jdbc() {
    System.out.println("==== 수동 플러시 시점 필요 ====");
    System.out.println("JDBC는 JPA가 아니므로 별개의 영역으로 관리 됨 -> em.flush ");
  }


  @Test
  @DisplayName("paging ")
  public void paging() {

    for (int i = 0; i < 100; i++) {
      MemberJpql memberJpql = new MemberJpql();
      memberJpql.setUsername("sss");
      memberJpql.setAge(i);
      em.persist(memberJpql);
    }

    em.flush();
    em.clear();

    final List<MemberJpql> resultList = em.createQuery("select m from MemberJpql m order by m.age desc ", MemberJpql.class)
            .setFirstResult(0)
            .setMaxResults(20)
            .getResultList();

    System.out.println("resultList = " + resultList);
    resultList.forEach(memberJpql1 -> {
      System.out.println("memberJpql1 = " + memberJpql1.getAge());
    });

  }

  @Test
  @DisplayName(" inner join ")
  public void join() {

    TeamJpql teamJpql = new TeamJpql();
    teamJpql.setName("Team!");

    MemberJpql memberJpql = new MemberJpql();
    memberJpql.setUsername("Team!");
    memberJpql.setAge(30);
    memberJpql.changeTeam(teamJpql);

    ProductJpql productJpql = new ProductJpql();
    productJpql.setName("Team!");

    em.persist(productJpql);
    em.persist(teamJpql);
    em.persist(memberJpql);

    em.flush();
    em.clear();

    /*
        ** 1:M -> @OneToMany 에 fetch를 Lazy로 잡지 않을 경우 셀렉트문 한번 더 나감 **
        select
            teamjpql0_.TEAM_ID as team_id1_29_0_,
            teamjpql0_.name as name2_29_0_
        from
            TeamJpql teamjpql0_
        where
            teamjpql0_.TEAM_ID=?
     */

//    final String sql = "select m from MemberJpql m inner join m.teamJpql";
//    final String sql = "select m from MemberJpql m inner join m.teamJpql t where t.name = :teamName"; // inner 생략 가능
//    final String sql = "select m from MemberJpql m left outer join m.teamJpql t";         // outer는 생략 가능
//    final String sql = "select m from MemberJpql m, TeamJpql t where m.username = t.name";  // 카티션 곱
//    final String sql = "select m from MemberJpql m left join m.teamJpql t on t.name='Team!' ";  // left 조인 -> on 절 -> and 절
//    final String sql = "select m from MemberJpql m left join TeamJpql  t on t.name='Team!' ";  // left 조인 -> on 절 -> and 절
    final String sql = "select m from MemberJpql m left join ProductJpql t on t.name='Team!' ";  // left 조인 -> on 절 -> and 절


    final List<MemberJpql> resultList = em.createQuery(sql, MemberJpql.class)
//            .setParameter("teamName", "Team!")
            .getResultList();

    System.out.println("resultList = " + resultList.size());

    resultList.forEach(memberJpql1 -> {
      System.out.println("memberJpql1 = " + memberJpql1.getAge());
    });

  }

  @Test
  @DisplayName(" 서브쿼리 ")
  public void sub_query() {

    TeamJpql teamJpql = new TeamJpql();
    teamJpql.setName("Team!");

    MemberJpql memberJpql = new MemberJpql();
    memberJpql.setUsername("Team!");
    memberJpql.setAge(30);
    memberJpql.changeTeam(teamJpql);

    ProductJpql productJpql = new ProductJpql();
    productJpql.setName("Team!");

    em.persist(productJpql);
    em.persist(teamJpql);
    em.persist(memberJpql);

    em.flush();
    em.clear();

    // from  서브쿼리는  안됨 -> 약점, -> 조인으로 풀수 있으면 풀고, 안되면 쿼리 2번 실행 등으로 해결 필요, 네이티브 쿼리 해결
    // 그럼에도 네이티브를 쓰지 않는 이유...
    final String sql = "select (select m from MemberJpql m1) as avgAve from MemberJpql m join TeamJpql t on m.username = 'Team!'";


    final List<MemberJpql> resultList = em.createQuery(sql, MemberJpql.class)
//            .setParameter("teamName", "Team!")
            .getResultList();

    System.out.println("resultList = " + resultList.size());

    resultList.forEach(memberJpql1 -> {
      System.out.println("memberJpql1 = " + memberJpql1.getAge());
    });

  }

  @Test
  @DisplayName(" JPQL 타입 ")
  public void jpql_type() {

    TeamJpql teamJpql = new TeamJpql();
    teamJpql.setName("Team!");

    MemberJpql memberJpql = new MemberJpql();
    memberJpql.setUsername("Team!");
    memberJpql.setAge(30);
    memberJpql.changeTeam(teamJpql);
    memberJpql.setMemberJpqlType(MemberJpqlType.ADMIN);

    ProductJpql productJpql = new ProductJpql();
    productJpql.setName("Team!");

    em.persist(productJpql);
    em.persist(teamJpql);
    em.persist(memberJpql);

    em.flush();

    em.clear();
//    final String sql = "select m.username, 'HELLO', true from MemberJpql m";
    final String sql = "select m.username, 'HELLO', true from MemberJpql m where m.memberJpqlType =:userType";


    final List<Object[]> resultList = em.createQuery(sql)
            .setParameter("userType", MemberJpqlType.ADMIN)
            .getResultList();

    System.out.println("resultList = " + resultList.size());

    resultList.forEach(objects -> {
      System.out.println("objects[0] = " + objects[0]);
      System.out.println("objects[1] = " + objects[1]);
      System.out.println("objects[2] = " + objects[2]);

    });
  }

  @Test
  @DisplayName(" JPQL 상속 ")
  public void jpql_type_extends() {

    // DTYPE = "B"  -> 쿼리문 확인
    em.createQuery("select i from Item i where type(i) = Book ", Item.class).getResultList();
  }
  
  
  @Test
  @DisplayName(" JPQL - case ")
  public void case_condition() {

    TeamJpql teamJpql = new TeamJpql();
    teamJpql.setName("TeamA");

    MemberJpql memberJpql = new MemberJpql();
    memberJpql.setUsername("ssosso");
    memberJpql.setAge(30);
    memberJpql.changeTeam(teamJpql);
    memberJpql.setMemberJpqlType(MemberJpqlType.ADMIN);

    em.persist(teamJpql);
    em.persist(memberJpql);

    em.flush();
    em.clear();

    String sql = "select " +
            "case when m.age <= 10 then '학생요금'" +
            "     when m.age >= 60 then '경로요금'" +
            "     else '일반요금' " +
            "end " +
            "from MemberJpql m";

    List<String> resultList = em.createQuery(sql, String.class).getResultList();


    String sql2 = "select " +
            "case t.name " +
            "   when 'TeamA' then '인센110' " +
            "   when 'TeamB' then '인센120' " +
            "   else '인센105' " +
            "end " +
            "from TeamJpql t";
    final List<String> resultList1 = em.createQuery(sql2, String.class).getResultList();
    System.out.println("resultList1 = " + resultList1);
  }

  @Test
  @DisplayName(" JPQL - nullif, coalesce ")
  public void case_condition2() {

    TeamJpql teamJpql = new TeamJpql();
    teamJpql.setName("TeamA");

    MemberJpql memberJpql = new MemberJpql();
    memberJpql.setAge(30);
    memberJpql.changeTeam(teamJpql);
    memberJpql.setMemberJpqlType(MemberJpqlType.ADMIN);

    em.persist(teamJpql);
    em.persist(memberJpql);

//    em.flush();
//    em.clear();

    // 이름없는 사람
    String sql = "select coalesce(m.username, '이름 없는 사람') from MemberJpql m";
    List<String> resultList = em.createQuery(sql, String.class).getResultList();
    System.out.println("resultList = " + resultList);

    // null
    memberJpql.setUsername("관리자");
    String sql2 = "select nullif(m.username, '관리자') from MemberJpql m";
    final List<String> resultList1 = em.createQuery(sql2, String.class).getResultList();
    System.out.println("resultList1 = " + resultList1);

    // ssosso
    memberJpql.setUsername("ssosso");
    final List<String> resultList2 = em.createQuery(sql2, String.class).getResultList();
    System.out.println("resultList2 = " + resultList2);
  }

  @Test
  @DisplayName(" JPQL 기본함수 -  ")
  public void jpql_function() {

    TeamJpql teamJpql = new TeamJpql();
    teamJpql.setName("TeamA");

    MemberJpql memberJpql = new MemberJpql();
    memberJpql.setUsername("1234566");
    memberJpql.setAge(30);
    memberJpql.changeTeam(teamJpql);
    memberJpql.setMemberJpqlType(MemberJpqlType.ADMIN);

    em.persist(teamJpql);
    em.persist(memberJpql);

    em.flush();
    em.clear();

    // 이름없는 사람
//    String sql = "select 'a' || 'b' from MemberJpql  m";
//    String sql = "select concat('a','b') from MemberJpql  m";
//    String sql = "select substring(m.username, 2,3) from MemberJpql  m";
//    List<String> resultList = em.createQuery(sql, String.class).getResultList();

//    String sql = "select locate('de','abcdefg') from MemberJpql  m";  // 찾은 문자열 위치
    String sql = "select size(t.memberJpqls) from TeamJpql t";  //  컬렉션 크기
    List<Integer> resultList = em.createQuery(sql, Integer.class).getResultList();


    System.out.println("resultList = " + resultList);

  }

  @Test
  @DisplayName(" 경로탐색 - 명시적,묵시적 조인  ")
  public void inner_join() {

    TeamJpql teamJpql = new TeamJpql();
    teamJpql.setName("TeamA");

    MemberJpql memberJpql = new MemberJpql();
    memberJpql.setUsername("1234566");
    memberJpql.setAge(30);
    memberJpql.changeTeam(teamJpql);
    memberJpql.setMemberJpqlType(MemberJpqlType.ADMIN);

    em.persist(teamJpql);
    em.persist(memberJpql);

    em.flush();
    em.clear();

    System.out.println("=======================================================");

    String sql = "select m.username from MemberJpql m";         //  상태필드
    String sql2 = "select m.teamJpql from MemberJpql m";                      // 연관필드 : 묵시점 조인
    String sql3 = "select t.memberJpqls from TeamJpql t";                     // 컬렉션 : 더이상 참조가 불가
    String sql4 = "select t.memberJpqls.size from TeamJpql t";                // 컬렉션 : size 정도
    String sql5 = "select m.username from TeamJpql t join t.memberJpqls m";   // 명시적 join으로 -> 탐색 가능하게 하기

    List<String> resultList = em.createQuery(sql, String.class).getResultList();
    List<TeamJpql> resultList2 = em.createQuery(sql2, TeamJpql.class).getResultList();
    Collection resultList3 = em.createQuery(sql3, Collection.class).getResultList();
    List<Integer> resultList4 = em.createQuery(sql4, Integer.class).getResultList();
    List<String> resultList5 = em.createQuery(sql5, String.class).getResultList();


    System.out.println("resultList = " + resultList);
    System.out.println("resultList2 = " + resultList2);
    System.out.println("resultList3 = " + resultList3);
    System.out.println("resultList4 = " + resultList4);
    System.out.println("resultList5 = " + resultList5);

  }

  @Test
  @DisplayName("\uD83C\uDF1F 경로탐색 - 페치조인 (실무에서 엄청 많이 사용하고 중요) ")
  public void fetch_join() {

    TeamJpql teamJpql = new TeamJpql();
    teamJpql.setName("TeamA");
    em.persist(teamJpql);

    TeamJpql teamJpql2 = new TeamJpql();
    teamJpql2.setName("TeamB");
    em.persist(teamJpql2);

    MemberJpql memberJpql = new MemberJpql();
    memberJpql.setUsername("회원1");
    memberJpql.setTeamJpql(teamJpql);
    em.persist(memberJpql);

    MemberJpql memberJpql2 = new MemberJpql();
    memberJpql2.setUsername("회원2");
    memberJpql2.setTeamJpql(teamJpql);
    em.persist(memberJpql2);

    MemberJpql memberJpql3 = new MemberJpql();
    memberJpql3.setUsername("회원3");
    memberJpql3.setTeamJpql(teamJpql2);
    em.persist(memberJpql3);


    em.flush();
    em.clear();

    System.out.println("=======================================================");


    // 총 쿼리 3번 (전체 셀렉 1, 팀 조회 2)... 최악의 경우  : N + 1 [회원쿼리1, 팀(연관,즉시든 지연이든 발생) N번(팀연관갯수만큼) 발생]
//    String sql = "select m from MemberJpql m";

    /*
      해결방안
      - fetch 조인
      - member 조인할 때, team도 조회 하고 싶어 -> 즉시로딩과 동일, 그러나 동적으로 내맘대로 할 수 있다!
     */

    // 회원-팀 연관 페치조인 : 멤버조인 -> 한번에 페치로 팀을 1번에 들고와  -> 이미 영속화가 된 데이터를(진짜 커밋된) 들고와서 셀렉트를 해오게됨
    // 지연로딩을 해도 fetch 조인이 우선순위 높음
    String sql = "select m from MemberJpql m join fetch m.teamJpql"; // inner 조인으로 쿼리 1번 나감

    List<MemberJpql> resultList = em.createQuery(sql, MemberJpql.class).getResultList();
    resultList.forEach(member -> {
      // 페치조인안할 경우 다음과 같이 실행됨
      // 회원 1 -> 지연 로딩 셀렉트 (팀A)
      // 회원 2 -> 1차캐시 사용 (팀A)
      // 회원 3 -> 지연로딩 셀렉트 (팀B)
      System.out.println(member.getUsername() + ", "+member.getTeamJpql().getName());
    });

  }

  @Test
  @DisplayName("\uD83C\uDF1F 경로탐색 - 컬렉션 페치조인 (이것도 중요, 1:N -> Team 기준에서 볼 때) ")
  public void collection_fetch_join() {

    TeamJpql teamJpql = new TeamJpql();
    teamJpql.setName("TeamA");
    em.persist(teamJpql);

    TeamJpql teamJpql2 = new TeamJpql();
    teamJpql2.setName("TeamB");
    em.persist(teamJpql2);

    MemberJpql memberJpql = new MemberJpql();
    memberJpql.setUsername("회원1");
    memberJpql.setTeamJpql(teamJpql);
    em.persist(memberJpql);

    MemberJpql memberJpql2 = new MemberJpql();
    memberJpql2.setUsername("회원2");
    memberJpql2.setTeamJpql(teamJpql);
    em.persist(memberJpql2);

    MemberJpql memberJpql3 = new MemberJpql();
    memberJpql3.setUsername("회원3");
    memberJpql3.setTeamJpql(teamJpql2);
    em.persist(memberJpql3);


    em.flush();
    em.clear();

    System.out.println("=======================================================");

    // 팀으로 페치 조인
    /*
      문제
      - 1:N 조인 -> 중복 출력 됨... 디비에서 데이터를  회원갯수만큼 들고옴.. 문제
      - JPA에서 할 수 있는 .. 미리알 수 있는 방법이 없다
      - 불필요한 경우, 명시적으로 해결 -> distinct로 제거
        1. sql distinct 추가
        2. 애플리케이션에서 제거
     */
//    String sql = "select t from TeamJpql t";  // 팀갯수 2
    String sql2 = "select t from TeamJpql t join fetch t.memberJpqls";  // 3 (데이터중복)
    List<TeamJpql> resultList2 = em.createQuery(sql2, TeamJpql.class).getResultList();
    resultList2.forEach(team ->  {
      System.out.println(team.getName()+", " + team.getMemberJpqls());

      team.getMemberJpqls().forEach(memberJpql1 -> {
        System.out.println("->memberJpql1 = " + memberJpql1); // 중복 출력됨 팀 A에 대해서.. 회원 2명이 팀A 소속
      });
    });


    System.out.println("========================distinct===============================");

    // JPA -> distinct 엔티티 입장에서 중복 제거
    String sql3 = "select distinct t from TeamJpql t join fetch t.memberJpqls";
    List<TeamJpql> resultList3 = em.createQuery(sql3, TeamJpql.class).getResultList();
    resultList3.forEach(team ->  {
      System.out.println(team.getName()+", " + team.getMemberJpqls());
      team.getMemberJpqls().forEach(memberJpql1 -> {
        System.out.println("->memberJpql1 = " + memberJpql1);
      });
    });
  }

  @Test
  @DisplayName("\uD83C\uDF1F 페치 조인의 특징과 한계 ")
  public void collection_fetch_join2() {

    TeamJpql teamJpql = new TeamJpql();
    teamJpql.setName("TeamA");
    em.persist(teamJpql);

    TeamJpql teamJpql2 = new TeamJpql();
    teamJpql2.setName("TeamB");
    em.persist(teamJpql2);

    MemberJpql memberJpql = new MemberJpql();
    memberJpql.setUsername("회원1");
    memberJpql.setTeamJpql(teamJpql);
    em.persist(memberJpql);

    MemberJpql memberJpql2 = new MemberJpql();
    memberJpql2.setUsername("회원2");
    memberJpql2.setTeamJpql(teamJpql);
    em.persist(memberJpql2);

    MemberJpql memberJpql3 = new MemberJpql();
    memberJpql3.setUsername("회원3");
    memberJpql3.setTeamJpql(teamJpql2);
    em.persist(memberJpql3);


    em.flush();
    em.clear();

    System.out.println("=======================================================");

    // fetch join -> 별칭 쓰지 말기, 필요한 경우만 사용
//    String sql = "select t from TeamJpql t join fetch t.memberJpqls"; // -> 데이터를 다들고옴.. 페이징 하지 않음... 조심해야됨

    // 아래 loop 에서 N+1 문제 발생, 셀렉트 할 때마다  select 쿼리 실행됨 -> @BatchSize를 통해 즉시로딩화 가능
    String sql2 = "select t from TeamJpql t";

    List<TeamJpql> resultList = em.createQuery(sql2, TeamJpql.class)
        .setFirstResult(0)
        .setMaxResults(2)
        .getResultList();

    System.out.println("resultList.size() = " + resultList.size());

    resultList.forEach(team ->  {
      System.out.println("team = " + team.getName() + " | members="+team.getMemberJpqls().size());
      System.out.println(team.getName()+", " + team.getMemberJpqls());

      team.getMemberJpqls().forEach(memberJpql1 -> {
        System.out.println("->memberJpql1 = " + memberJpql1); // 중복 출력됨 팀 A에 대해서.. 회원 2명이 팀A 소속
      });
    });

  }

  @Test
  @DisplayName("다형성 쿼리 - 별로 중요하진 않음 ")
  public void extends_query() {

    BookItem bookItem = new BookItem();
    bookItem.setAuthor("ssosso");
    bookItem.setPrice(10000);
    bookItem.setName("JPA");
    em.persist(bookItem);

    MovieItem movieItem = new MovieItem();
    movieItem.setActor("김다미");
    movieItem.setPrice(15000);
    movieItem.setName("이태원클라쓰");
    em.persist(movieItem);

    em.flush();
    em.clear();

    System.out.println("=======================================================");
//    String sql = "select i from Item i where type(i) in (BookItem , MovieItem )";
    String sql = "select i from Item i where treat(i as BookItem).author = 'ssosso'";

    final List<Item> resultList = em.createQuery(sql, Item.class).getResultList();
    System.out.println("resultList = " + resultList);
    resultList.forEach(item -> {
      System.out.println("======== START =======");
      System.out.println("item.getName() = " + item.getName());
      System.out.println("item.getPrice() = " + item.getPrice());
    });
  }

  @Test
  @DisplayName("엔티티 직접사용 - 기본키를 사용 ")
  public void entity_direct_primary() {

    TeamJpql teamJpql = new TeamJpql();
    teamJpql.setName("TeamA");
    em.persist(teamJpql);

    TeamJpql teamJpql2 = new TeamJpql();
    teamJpql2.setName("TeamB");
    em.persist(teamJpql2);

    MemberJpql memberJpql = new MemberJpql();
    memberJpql.setUsername("회원1");
    memberJpql.setTeamJpql(teamJpql);
    em.persist(memberJpql);

    MemberJpql memberJpql2 = new MemberJpql();
    memberJpql2.setUsername("회원2");
    memberJpql2.setTeamJpql(teamJpql);
    em.persist(memberJpql2);

    MemberJpql memberJpql3 = new MemberJpql();
    memberJpql3.setUsername("회원3");
    memberJpql3.setTeamJpql(teamJpql2);
    em.persist(memberJpql3);

    em.flush();
    em.clear();

    System.out.println("========================= 1 ==============================");
    String sql = "select count(m) from MemberJpql m";


    List<Long> resultList = em.createQuery(sql, Long.class)
        .getResultList();
    System.out.println("resultList = " + resultList);

    System.out.println("======================== 2 ===============================");
    String sql2 = "select m from MemberJpql m join fetch m.teamJpql t where m =: member";
    List<MemberJpql> resultList2 = em.createQuery(sql2, MemberJpql.class)
        .setParameter("member", memberJpql2)
        .getResultList();
    System.out.println("resultList2 = " + resultList2);

    System.out.println("======================== 3 ===============================");
    String sql3 = "select m from MemberJpql m join fetch m.teamJpql t where m.id =: memberId";
    List<MemberJpql> resultList3 = em.createQuery(sql3, MemberJpql.class)
        .setParameter("memberId", memberJpql3.getId())
        .getResultList();
    System.out.println("resultList3 = " + resultList3);

  }

  @Test
  @DisplayName("엔티티 직접사용 - 외래키 사용 ")
  public void entity_direct_foreign() {

    TeamJpql teamJpql = new TeamJpql();
    teamJpql.setName("TeamA");
    em.persist(teamJpql);

    TeamJpql teamJpql2 = new TeamJpql();
    teamJpql2.setName("TeamB");
    em.persist(teamJpql2);

    MemberJpql memberJpql = new MemberJpql();
    memberJpql.setUsername("회원1");
    memberJpql.setTeamJpql(teamJpql);
    em.persist(memberJpql);

    MemberJpql memberJpql2 = new MemberJpql();
    memberJpql2.setUsername("회원2");
    memberJpql2.setTeamJpql(teamJpql);
    em.persist(memberJpql2);

    MemberJpql memberJpql3 = new MemberJpql();
    memberJpql3.setUsername("회원3");
    memberJpql3.setTeamJpql(teamJpql2);
    em.persist(memberJpql3);

    em.flush();
    em.clear();

    TeamJpql findTeam = em.find(TeamJpql.class, teamJpql.getId());

    System.out.println("========================= 연관된 외래키 값 사용 1 ==============================");
    String sql = "select m from MemberJpql m where m.teamJpql =: teamJqpl";

    List<MemberJpql> resultList = em.createQuery(sql, MemberJpql.class)
        .setParameter("teamJqpl", findTeam)
        .getResultList();
    System.out.println("resultList = " + resultList);

    System.out.println("========================= 연관된 외래키 값 사용 2 ==============================");
    String sql2 = "select m from MemberJpql m where m.teamJpql.id =: teamId";

    List<MemberJpql> resultList2 = em.createQuery(sql2, MemberJpql.class)
        .setParameter("teamId", findTeam.getId())
        .getResultList();
    System.out.println("resultList2 = " + resultList2);

  }

  @Test
  @DisplayName("Named 쿼리 사용 - 애노테이션")
  public void named_query_annotation() {

    TeamJpql teamJpql = new TeamJpql();
    teamJpql.setName("TeamA");
    em.persist(teamJpql);

    TeamJpql teamJpql2 = new TeamJpql();
    teamJpql2.setName("TeamB");
    em.persist(teamJpql2);

    MemberJpql memberJpql = new MemberJpql();
    memberJpql.setUsername("회원1");
    memberJpql.setTeamJpql(teamJpql);
    em.persist(memberJpql);

    MemberJpql memberJpql2 = new MemberJpql();
    memberJpql2.setUsername("회원2");
    memberJpql2.setTeamJpql(teamJpql);
    em.persist(memberJpql2);

    MemberJpql memberJpql3 = new MemberJpql();
    memberJpql3.setUsername("회원3");
    memberJpql3.setTeamJpql(teamJpql2);
    em.persist(memberJpql3);

    em.flush();
    em.clear();


    System.out.println("========================= 네임드쿼리 1 ==============================");

    List<MemberJpql> resultList = em.createNamedQuery("Member.findByUsername", MemberJpql.class)
        .setParameter("username", memberJpql.getUsername())
        .getResultList();

    System.out.println("resultList = " + resultList);

    System.out.println("========================= 네임드쿼리 2 ==============================");

    List<MemberJpql> resultList2 = em.createNamedQuery("Member.findByUsername", MemberJpql.class)
        .setParameter("username", memberJpql2.getUsername())
        .getResultList();

    System.out.println("resultList2 = " + resultList2);

  }
  @Test
  @DisplayName("Named 쿼리 사용 - XML")
  public void named_query_xml() {

    TeamJpql teamJpql = new TeamJpql();
    teamJpql.setName("TeamA");
    em.persist(teamJpql);

    TeamJpql teamJpql2 = new TeamJpql();
    teamJpql2.setName("TeamB");
    em.persist(teamJpql2);

    MemberJpql memberJpql = new MemberJpql();
    memberJpql.setUsername("회원1");
    memberJpql.setTeamJpql(teamJpql);
    em.persist(memberJpql);

    MemberJpql memberJpql2 = new MemberJpql();
    memberJpql2.setUsername("회원2");
    memberJpql2.setTeamJpql(teamJpql);
    em.persist(memberJpql2);

    MemberJpql memberJpql3 = new MemberJpql();
    memberJpql3.setUsername("회원3");
    memberJpql3.setTeamJpql(teamJpql2);
    em.persist(memberJpql3);

    em.flush();
    em.clear();


    System.out.println("========================= 네임드쿼리 XML 1 ==============================");
    List<MemberJpql> resultList = em.createNamedQuery("Member.XML.findByUsername", MemberJpql.class)
        .setParameter("username", memberJpql.getUsername())
        .getResultList();

    System.out.println("resultList = " + resultList);

    System.out.println("========================= 네임드쿼리 XML 2 ==============================");
    List<MemberJpql> resultList2 = em.createNamedQuery("Member.XML.findByUsername", MemberJpql.class)
        .setParameter("username", memberJpql2.getUsername())
        .getResultList();

    System.out.println("resultList2 = " + resultList2);

    System.out.println("========================= 네임드쿼리 XML 3 ==============================");
    final List<Long> countList = em.createNamedQuery("Member.XML.count", Long.class).getResultList();
    System.out.println("countList = " + countList);

  }

}
