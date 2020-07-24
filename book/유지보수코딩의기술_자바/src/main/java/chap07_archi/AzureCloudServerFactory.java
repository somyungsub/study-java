package chap07_archi;

public class AzureCloudServerFactory implements CloudServerFactory {
  @Override
  public CloudServer launchComputeServer() {
    return new AzureComputeServer();
  }

  @Override
  public CloudServer launchDatabaseServer() {
    return new AzureDatabaseServer();
  }

  @Override
  public CloudServer createCloudStorage(long size) {
    return new AzureCloudStorage(size);
  }
}
