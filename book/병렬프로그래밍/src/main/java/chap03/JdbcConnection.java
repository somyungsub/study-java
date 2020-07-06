package chap03;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConnection {

  private static String DB_URL;

  // 스레드 한정 상태 유지
  // ThreadLocal 활용 -> 스레드별로 연결객체를 얻도록 함
  private static ThreadLocal<Connection> connectionHolder = new ThreadLocal<>() {
    public Connection initialValue() {
      try {
        return DriverManager.getConnection(DB_URL);
      } catch (SQLException throwables) {
        throwables.printStackTrace();
      }
      return null;
    }
  };

  public static Connection getConnection() {
    return connectionHolder.get();
  }
}
