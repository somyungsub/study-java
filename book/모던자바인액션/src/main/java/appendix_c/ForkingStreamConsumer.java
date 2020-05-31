package appendix_c;

import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Future;
import java.util.function.Consumer;

public class ForkingStreamConsumer<T> implements Consumer<T>, Results {
  static final Object END_OF_STREAM = new Object();
  private final List<BlockingQueue<T>> queues;

  private final Map<Object, Future<?>> actions;

  public ForkingStreamConsumer(List<BlockingQueue<T>> queues, Map<Object, Future<?>> actions) {
    this.queues = queues;
    this.actions = actions;
  }

  @Override
  public void accept(T t) {
    queues.forEach(q -> q.add(t));
  }

  void finish() {
    accept((T) END_OF_STREAM);
  }

  @Override
  public <R> R get(Object key) {
    try {
      return ((Future<R>) actions.get(key)).get();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
