package chap11;


import java.util.Set;

/*
  Thread Safe
  - 필드 2개 이상 사용
  - 락 분할
 */
public class ServerStatus {
  private final Set<String> users;
  private final Set<String> queries;

  public ServerStatus(Set<String> users, Set<String> queries) {
    this.users = users;
    this.queries = queries;
  }

  public void addUser(String u) {

    // 쓰레드 단위가 아닌 사용 객체 단위로 락
    synchronized (users) {
      users.add(u);
    }
  }

  public void addQueries(String q) {

    // 쓰레드 단위가 아닌 사용 객체 단위로 락
    synchronized (queries) {
      queries.add(q);
    }
  }

  public Set<String> getQueries() {
    synchronized (queries) {
      return queries;
    }
  }

  public Set<String> getUsers() {
    synchronized (users) {
      return users;
    }
  }
}
