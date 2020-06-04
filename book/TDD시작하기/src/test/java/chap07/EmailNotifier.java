package chap07;

public interface EmailNotifier {
  String getEmail();

  boolean isCalled();

  void sendRegisterEmail(String email);
}
