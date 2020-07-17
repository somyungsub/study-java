package chap05;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.jupiter.api.Assertions.*;

class Chap05Test {

  @Test
  @DisplayName("문자열 연결 연산 내부 이터레이터")
  public void iterator() {

  }

  @Test
  @DisplayName("5.3 블로킹큐")
  public void 블로킹큐() throws InterruptedException {

    File[] roots = {
        new File("src/main"),
        new File("src/test"),
        new File("../..")
    };
    BlockingQueue<File> queue = new LinkedBlockingQueue<>(10000);
    FileFilter fileFilter = new FileFilter() {
      @Override
      public boolean accept(File pathname) {
        return true;
      }
    };

    // 3개
    for (File root : roots) {
      new Thread(new FileCrawler(queue, fileFilter, root)).start();
    }

    Thread.sleep(3000);
    System.out.println("=============");
    System.out.println(queue.size());
    System.out.println("=============");

    for (int i = 0; i < 100; i++) {
      new Thread(new Indexer(queue)).start();
    }

  }

}