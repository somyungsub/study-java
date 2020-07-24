package chap06_관심사를모듈로분리;

import java.util.List;

public class UserServiceCoupling {

  // 1. 사용자 관련
  public User loadUser(String userId) {
    return null;
  }

  public boolean doesUserExist(String userId) {
    return false;
  }

  public User changeUserInfo(UserInfo userInfo) {
    return null;
  }


  // 2. 알림 기능
  public List<NotificationType> getNotificationTypes(User user) {
    return null;
  }

  public void registerForNotifications(User user, NotificationType type) {

  }

  public void unregisterForNotifications(User user, NotificationType type) {

  }

  // 3. 사용자 검색 및 블럭
  public List<User> searchUsers(UserInfo userInfo) {
    return null;
  }

  public void blockUser(User user) {
    
  }

  public List<User> getAllBlockedUsers() {
    return null;
  }


}
