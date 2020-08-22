package test.json;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class JsonTestTest {

  JsonTest jsonTest;

  @BeforeEach
  public void init() {
    jsonTest = new JsonTest();
  }

  @Test
  @DisplayName("json")
  public void 테스트() {
    try (BufferedReader br =
             Files.newBufferedReader(Paths.get("src/main/resources", "/test.json"))) {
      jsonTest.parse(br);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Test
  @DisplayName("중복")
  public void 테스트2() {
    try (BufferedReader br =
             Files.newBufferedReader(Paths.get("src/main/resources", "/test.dup.json"))) {
      jsonTest.parse(br);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Test
  @DisplayName("정렬")
  public void 갯수내림차순_후_이름내림차순_정렬() {
    try (BufferedReader br =
             Files.newBufferedReader(Paths.get("src/main/resources", "/test.dup.json"))) {
      jsonTest.parse(br);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


}