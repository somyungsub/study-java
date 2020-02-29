package design_pattern.kosta.creation.pool;

import java.util.ArrayList;
import java.util.List;

public class ObjectPool<T> {

  // TODO
  private List<T> availablePool;
  private List<T> inUsePool;

  private int maxPoolSize = 30;

  public ObjectPool(Class<T> classObj) {
    createPool(classObj, this.maxPoolSize);
  }

  public ObjectPool(Class<T> classObj, int maxPoolSize) {
    this.maxPoolSize = maxPoolSize;
    createPool(classObj, maxPoolSize);
  }

  public void createPool(Class<T> classObj, int maxPoolSize) {
    this.maxPoolSize = maxPoolSize;
    this.availablePool = new ArrayList<T>();
    this.inUsePool = new ArrayList<T>();
    for (int i = 0; i < maxPoolSize; ++i) {
      try {
        this.availablePool.add(classObj.newInstance());
      } catch (InstantiationException e) {
        e.printStackTrace();
      } catch (IllegalAccessException e) {
        e.printStackTrace();
      }
    }
  }


  public T getObject() {
    if (this.availablePool.size() == 0) {
      throw new PoolSizeOutException("사용할 객체가 없음");
    }

    T instance = this.availablePool.get(0);
    availablePool.remove(0);
    inUsePool.add(instance);

    return instance;

  }

  public boolean invalidate(T obj) {
    return inUsePool.contains(obj);
  }

}
