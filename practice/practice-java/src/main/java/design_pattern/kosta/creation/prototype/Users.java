package design_pattern.kosta.creation.prototype;

import java.util.ArrayList;
import java.util.List;

public class Users implements Cloneable {

  // TODO
  private List userList;

//  private int a;


  public Users(List<Users> userList) {
    this.userList = userList;
  }

  public void load() {
    userList = new ArrayList<>();
    userList.add("park");
    userList.add("park2");
    userList.add("park3");
    userList.add("park4");
    userList.add("park5");

  }

  public List getUserList() {
    return userList;
  }


  public Object clone() throws CloneNotSupportedException {
//    return super.clone();
    List temp = new ArrayList();
    for (Object o : this.userList) {
      temp.add((String)o);
    }
    return new Users(temp);
  }

}


