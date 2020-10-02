package chapter_06;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ExecuteAroundPattern {

  private Connection conn;

  public <R> R extract(final String sql, final Extractor<R> extractor) throws IllegalAccessException {
    try (var stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
      stmt.clearParameters();
      return extractor.run(stmt);
    } catch (SQLException e) {
      throw new IllegalAccessException(e.getMessage());
    }
  }
}
