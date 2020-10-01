package chapter_06;

public class Twoot {

  private final String id;
  private final String userId;
  private final String content;

  public Twoot(String id, String userId, String content) {
    this.id = id;
    this.userId = userId;
    this.content = content;
  }

  public String getId() {
    return id;
  }

  public String getUserId() {
    return userId;
  }

  public String getContent() {
    return content;
  }

  public String getSenderId() {
    return "";
  }
}
