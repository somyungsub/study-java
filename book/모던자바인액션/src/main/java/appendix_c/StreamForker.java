package appendix_c;

import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.Function;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class StreamForker<T> {
  private final Stream<T> stream;
  private final Map<Object, Function<Stream<T>, ?>> forks = new HashMap<>();

  public StreamForker(Stream<T> stream) {
    this.stream = stream;
  }

  public StreamForker<T> fork(Object key, Function<Stream<T>, ?> f) {
    forks.put(key, f);
    return this;
  }

  public Results getResults() {

    ForkingStreamConsumer<T> consumer = build();
    try {
      stream.sequential().forEach(consumer);
    } finally {
      consumer.finish();
    }

    return consumer;

  }

  private ForkingStreamConsumer<T> build() {
    List<BlockingQueue<T>> queues = new ArrayList<>();

    Map<Object, Future<?>> actions = forks.entrySet().stream().reduce(
        new HashMap<Object, Future<?>>(),
        (map, e) -> {
          map.put(e.getKey(),
              getOperationResult(queues, e.getValue()));
          return map;
        },
        (m1, m2) -> {
          m1.putAll(m2);
          return m1;
        }
    );

    return new ForkingStreamConsumer<>(queues, actions);
  }

  private Future<?> getOperationResult(List<BlockingQueue<T>> queues, Function<Stream<T>,?> f) {
    BlockingQueue<T> queue = new LinkedBlockingQueue<>();
    queues.add(queue);
    Spliterator<T> spliterator = new BlockingQueueSpliterator<>(queue);
    Stream<T> source = StreamSupport.stream(spliterator, false);
    return CompletableFuture.supplyAsync(() -> f.apply(source));
  }
}
