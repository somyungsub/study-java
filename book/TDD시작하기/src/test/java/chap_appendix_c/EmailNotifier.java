package chap_appendix_c;

public interface EmailNotifier {
  String getEmail();

  boolean isCalled();

  void sendRegisterEmail(String email);
}
