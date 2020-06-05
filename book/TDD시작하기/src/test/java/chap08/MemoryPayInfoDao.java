package chap08;

import java.util.Collections;
import java.util.List;

public class MemoryPayInfoDao implements PayInfoDao {
  @Override
  public void insert(PayInfo payInfo) {
    System.out.println("payInfo = " + payInfo);
  }

  @Override
  public List<PayInfo> getAll() {
    return Collections.emptyList();
  }
}
