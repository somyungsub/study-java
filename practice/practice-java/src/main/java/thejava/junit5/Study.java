package thejava.junit5;

public class Study {
  private StudyStatus status = StudyStatus.DRAFT;

  private int limit;

  public Study() {
  }

  public Study(int limit) {
    if (limit < 0) {
      throw new IllegalArgumentException("limit는 0보다 커야한다.");
    }
    this.limit = limit;
  }

  public StudyStatus getStatus() {
    return this.status;
  }

  public int getLimit() {
    return limit;
  }
}
