package design_pattern.kosta.structure.proxy;

public class Proxy implements IService {

  @Override
  public String runSomething() {
    System.out.println("proxy를 통해 호출되었다");
    IService service = new Service();

    // 대행 ~! , 캐싱 로깅 보안?
    return service.runSomething();
  }
}
