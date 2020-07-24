package chap07_archi;

public interface CloudServerFactory {

  CloudServer launchComputeServer();

  CloudServer launchDatabaseServer();

  CloudServer createCloudStorage(long size);

}
