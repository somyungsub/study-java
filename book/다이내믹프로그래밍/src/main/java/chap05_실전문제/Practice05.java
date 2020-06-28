package chap05_실전문제;

/*
  연습문제
 */
public class Practice05 {

  /*
    연습문제 5-1 좌표 경로수
   */
  public int 좌표경로수5_1(int x, int y) {
    int[][] arr = new int[y + 1][x + 1];

    // x -> 1열
    for (int i = 1; i <= x; i++) {
      arr[0][i] = 1;
    }

    // y -> 1행
    for (int i = 1; i <= y; i++) {
      arr[i][0] = 1;
    }

    // 나머지
    for (int i = 1; i <= y; i++) {
      for (int j = 1; j <= x; j++) {
        arr[i][j] = arr[i - 1][j] + arr[i][j - 1];
      }
    }

    return arr[y][x];
  }

  /*
     연습문제 5-2 좌표경로 차단 추가 -> 인풋 애매해서 보류
   */
  public int 좌표경로차단5_2(int x, int y, int[][] breakPath) {

    // 차단 경로 breakPath
    int[][] arr = new int[y + 1][x + 1];

    // x -> 1열
    for (int i = 1; i <= x; i++) {
      arr[0][i] = 1;
    }

    // y -> 1행
    for (int i = 1; i <= y; i++) {
      arr[i][0] = 1;
    }

    // 나머지
    for (int i = 1; i <= y; i++) {
      for (int j = 1; j <= x; j++) {
        arr[i][j] = arr[i - 1][j] + arr[i][j - 1];
      }
    }

    return arr[y][x];
  }

  /*
    연습문제 5-3 대각선 추가 경로
   */

  public int 대각선추가5_3_예제2_재귀(int m, int n) {
    if (m == 0 && n == 0) {
      return 1;
    }

    if (m == 0 || n == 0) {
      return 1;
    }

    return 대각선추가5_3_예제2_재귀(m - 1, n) + 대각선추가5_3_예제2_재귀(m, n - 1) + 대각선추가5_3_예제2_재귀(m - 1, n - 1);
  }

  public int 대각선추가5_3_예제2_DP(int m, int n) {
    int[][] arr = new int[m + 1][n + 1];

    for (int i = 0; i <= m; i++) {
      arr[i][0] = 1;
    }

    for (int j = 0; j <= n; j++) {
      arr[0][j] = 1;
    }

    // 나머지
    for (int i = 1; i <= m; i++) {
      for (int j = 1; j <= n; j++) {
        arr[i][j] = arr[i - 1][j - 1] + arr[i - 1][j] + arr[i][j - 1];
      }
    }

    return arr[m][n];
  }

}
