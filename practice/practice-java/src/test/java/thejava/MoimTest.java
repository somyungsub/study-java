package thejava;

import org.junit.Assert;
import org.junit.Test;

public class MoimTest {

  @Test
  public void test() {
    Moim moim = new Moim();
    moim.maxNumberOfAttendees = 100;
    moim.numberOfEnrollment= 10;
    Assert.assertFalse(moim.isEnrollmentFull());

  }

}