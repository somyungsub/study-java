package design_pattern.kosta.behavior.mediator;

import java.util.ArrayList;

public class ApplicationMediator implements Mediator {

  private ArrayList<Colleague> colleagues;


  public ApplicationMediator() {
    this.colleagues = new ArrayList<>();
  }

  public void addColleague(Colleague colleague) {
    this.colleagues.add(colleague);
  }



  @Override
  public void send(String message, Colleague origin) {

    // 등록된 colleague에서 메세지를 전달
    for (Colleague colleague : colleagues) {
      if (colleague == origin) {
        colleague.receive(message);
      }
    }
  }

}
