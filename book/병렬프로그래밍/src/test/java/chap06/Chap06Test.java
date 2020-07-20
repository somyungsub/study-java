package chap06;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class Chap06Test {

  @Test
  @DisplayName("ExecutorCompletionService")
  public void test() throws IOException {
    ExecutorCompletionServiceClass executor = new ExecutorCompletionServiceClass();
    executor.start();
  }

}