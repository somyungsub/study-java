package chap07_archi;

public class AWSCloudStorage implements CloudServer {

  private final long size;

  public AWSCloudStorage(long size) {
    this.size = size;
  }
}
