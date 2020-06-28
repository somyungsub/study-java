package chap05_실전문제;

/*
  연습문제
 */
public class Practice05 {

  public int 좌표경로수(int x, int y) {
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
}
