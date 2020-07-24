package chap07_archi;

public class AWSCloudServerFactory implements CloudServerFactory {
  @Override
  public CloudServer launchComputeServer() {
    return new AWSComputeServer();
  }

  @Override
  public CloudServer launchDatabaseServer() {
    return new AWSDatabaseServer();
  }

  @Override
  public CloudServer createCloudStorage(long size) {
    return new AWSCloudStorage(size);
  }
}
