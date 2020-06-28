package chap05_실전문제;

public class Code05 {

  /*
    5.1 최소 교정 비용 구하기
   */
  public int editDistance_5_1_재귀(String str1, String str2) {
    if (str1.isEmpty()) {
      return str2.length();
    }
    if (str2.isEmpty()) {
      return str1.length();
    }

    if (str1.charAt(0) == str2.charAt(0)) {
      return editDistance_5_1_재귀(str1.substring(1), str2.substring(1));
    }

    int delete = 0;
    int update = 0;
    int insert = 0;

    // 삭제 후 최소교정비용 재귀
    delete = editDistance_5_1_재귀(str1.substring(1), str2);

    // 치환 후 최소교정비용 재귀
    update = editDistance_5_1_재귀(str1.substring(1), str2.substring(1));

    // 삽입 후 최소교정비용 재귀
    insert = editDistance_5_1_재귀(str1, str2.substring(1));

    // 세 연산 이후 최소 값 + 1
    return getMinimum(delete, update, insert) + 1;
  }

  public int editDistance_5_1_DP(String str1, String str2, int m, int n) {

    int[][] EditD = new int[m + 1][n + 1];

    // 1. 맨 위행 채우기
    for (int j = 0; j <= n; j++) {
      EditD[0][j] = j;
    }

    // 2. 맨 왼쪽열 채우기
    for (int i = 0; i <= m; i++) {
      EditD[i][0] = i;
    }

    // 3. 나머지
    for (int i = 1; i <= m; i++) {
      for (int j = 1; j <= n; j++) {

        if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
          // 1. 두 글자가 같다면
          EditD[i][j] = EditD[i - 1][j - 1];
        } else {
          // 2. 두 글자가 다르다면 : 3연산의 최소값 + 1
          EditD[i][j] = getMinimum(
              EditD[i][j - 1],      // 삽입
              EditD[i - 1][j - 1],  // 치환
              EditD[i - 1][j]       // 삭제
          ) + 1;
        }
      }
    }

    return EditD[m][n];
  }

  private int getMinimum(int delete, int update, int insert) {
    return Math.min(Math.min(delete, update), insert);
  }


  /*
    5.2 직사각형 총경로 수 구하기
   */
  public int numOfPaths_5_2_재귀(int m, int n) {

    // 종료 조건
    if (m == 0 && n == 0) {
      return 0;
    }

    // 첫 번째 행 or 첫 번째 열
    if (m == 0 || n == 0) {
      return 1;
    }

    // 위 접근, 왼쪽 접근
    return numOfPaths_5_2_재귀(m - 1, n) + numOfPaths_5_2_재귀(m, n - 1);
  }

  public int numOfPaths_5_2_DP(int m, int n) {
    int[][] arr = new int[m + 1][n + 1];


    // 1. 1열
    for (int i = 1; i <= m; i++) {
      arr[i][0] = 1;
    }

    // 2. 1행
    for (int j = 1; j <= n; j++) {
      arr[0][j] = 1;
    }

    // 3. 나머지
    for (int i = 1; i <= m; i++) {
      for (int j = 1; j <= n; j++) {
        arr[i][j] = arr[i - 1][j] + arr[i][j - 1];
      }
    }

    return arr[m][n];
  }
}
