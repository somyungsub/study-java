package chap05_단위인터페이스는작게;

public class MailExample {

  public void buildAndSendMail(MailMan m, String firstName, String lastName, String division, String subject,
                               MailFont font, String message1, String message2, String message3) {
    // 여러가지 일을 한다!
  }

  public void buildAndSendMail(MailMan m, MailAddress mailAddress, MailBody mailBody) {
    Mail mail = new Mail(mailAddress, mailBody);
    m.sendMail(mail);
  }

  private class Mail {

    private final MailAddress mailAddress;
    private final MailBody mailBody;

    public Mail(MailAddress mailAddress, MailBody mailBody) {
      this.mailAddress = mailAddress;
      this.mailBody = mailBody;
    }
  }

  private class MailBody {
    String subject;
    MailMessage message;

    public MailBody(String subject, MailMessage message) {
      this.subject = subject;
      this.message = message;
    }
  }

  private class MailAddress {
    private final String mailId;
    private MailAddress(String firstName, String lastName, String division) {
      this.mailId = firstName.charAt(0) + "." + lastName.substring(0, 7)
          + "@"
          + division.substring(0, 5) + ".compa.ny";
    }
  }



}
