package chap07;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TimedRun {

  private static final ScheduledExecutorService cancelExec = new ScheduledThreadPoolExecutor(10);

  public static void timedRun(final Runnable r,
                              long timeout, TimeUnit timeUnit) throws InterruptedException {
    class RethrowableTask implements Runnable {
      private volatile Throwable t;
      @Override
      public void run() {
        try{
          r.run();
        } catch (Throwable t) {
          this.t = t;
        }
      }

      void rethrow() {
        if (t != null) {
//          throw launderThrowable(t);
        }
      }
    }

    RethrowableTask task = new RethrowableTask();
    final Thread taskThread = new Thread(task);

    taskThread.start();
    cancelExec.schedule(() -> {
      taskThread.interrupt();
    }, timeout, timeUnit);

    taskThread.join(timeUnit.toMillis(timeout));
    task.rethrow();

  }

}
