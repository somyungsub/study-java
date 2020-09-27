package chapter_06;

import java.util.List;
import java.util.stream.Stream;

public class User {
  private final String id;
  private final List<User> users;
  private boolean loggedOn;

  public User() {
    this(null, null);
  }

  public User(String id, List<User> users) {
    this.id = id;
    this.users = users;
  }

  public String getId() {
    return id;
  }

  public List<User> getUsers() {
    return users;
  }

  public boolean isLoggedOn() {
    return loggedOn;
  }

  public Stream<User> followers() {
    return users.stream();
  }

  public void receiveTwoot(Twoot twoot) {

  }
}
