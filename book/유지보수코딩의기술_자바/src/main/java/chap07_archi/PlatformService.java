package chap07_archi;

public class PlatformService {
  CloudServerFactory factory;
  public void service(String factoryName) {
    if ("-azure".equals(factoryName)) {
      factory = new AzureCloudServerFactory();
    } else {
      factory = new AWSCloudServerFactory();
    }

    CloudServer computeServer = factory.launchComputeServer();
    CloudServer databaseServer = factory.launchDatabaseServer();

    doSomething();
    // 할일
  }

  private void doSomething() {

  }
}
