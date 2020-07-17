package chap05;

import java.io.File;
import java.util.concurrent.BlockingQueue;

public class Indexer implements Runnable {
  private final BlockingQueue<File> queue;

  public Indexer(BlockingQueue<File> queue) {
    this.queue = queue;
  }

  @Override
  public void run() {
    try{
      while (true) {
        indexFile(queue.take());  // 소비
      }
    }catch(InterruptedException e){
      Thread.currentThread().interrupt();
    }

  }

  public void indexFile(File file) {
    System.out.println("file = " + file);
  }
}
