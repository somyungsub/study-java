package design_pattern.chap11_proxy.src2;

import java.lang.reflect.Proxy;

public class MatchMakingTestDrive {
  public static void main(String[] args) {
    MatchMakingTestDrive test = new MatchMakingTestDrive();
    test.drive();
  }

  private void drive() {
    PersonBean joe = getPersonFromDatabase("Joe Javabean");
    PersonBean ownerProxy = getOwnerProxy(joe);
    System.out.println("ownerProxy.getName() = " + ownerProxy.getName());
    ownerProxy.setInterests("bowling, Go~");
    System.out.println(" Interests set from owner proxy ");

    try {
      ownerProxy.setHotOrNotRating(10);
    } catch (Exception e) {
      System.out.println("Can't set rating from owner proxy");
    }

    System.out.println("ownerProxy.getHotOrNotRating() = " + ownerProxy.getHotOrNotRating());

    PersonBean nonOwnerProxy = getNonOwnerProxy(joe);
    System.out.println("nonOwnerProxy.getName() = " + nonOwnerProxy.getName());
    try {
      nonOwnerProxy.setInterests("bowling, Go~");
    } catch (Exception e) {
      System.out.println("Can't set interests from non owner proxy");
    }

    nonOwnerProxy.setHotOrNotRating(3);
    System.out.println("Rating set from non owner proxy");
    System.out.println("nonOwnerProxy.getHotOrNotRating() = " + nonOwnerProxy.getHotOrNotRating());

  }

  private PersonBean getPersonFromDatabase(String name) {
    PersonBean personBean = new PersonBeanImpl();
    personBean.setName(name);
    personBean.setHotOrNotRating(7);
    personBean.setGender("male");
    return personBean;
  }

  private PersonBean getNonOwnerProxy(PersonBean person) {
    return (PersonBean) Proxy.newProxyInstance(
        person.getClass().getClassLoader(),
        person.getClass().getInterfaces(),
        new NonOwnerInvocationHandler(person));
  }

  PersonBean getOwnerProxy(PersonBean person) {
    return (PersonBean) Proxy.newProxyInstance(
        person.getClass().getClassLoader(),
        person.getClass().getInterfaces(),
        new OwnerInvocationHandler(person));
  }
}
