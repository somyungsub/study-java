package chap08;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

class PaySyncTest {

  private PayInfoDao dao = new MemoryPayInfoDao();

  @Test
  @DisplayName("의존성주입 -> 대역사용")
  public void di() {
    PaySync paySync = new PaySync();
    paySync.setPayInfoDao(dao);     // 대역으로 의존성 주입대체 -> 의존성 없애기
//    paySync.setFilePath();

    List<PayInfo> list = dao.getAll();

  }

}