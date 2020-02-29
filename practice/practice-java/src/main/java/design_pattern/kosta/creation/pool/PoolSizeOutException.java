package design_pattern.kosta.creation.pool;

public class PoolSizeOutException extends RuntimeException{
  public PoolSizeOutException(String msg) {
    super(msg);
  }
}
