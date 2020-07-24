package chap07_archi;

public class AzureCloudStorage implements CloudServer {

  private final long size;

  public AzureCloudStorage(long size) {
    this.size = size;
  }
}
