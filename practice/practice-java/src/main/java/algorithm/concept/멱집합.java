package algorithm.concept;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 멱집합 {

  /*
    부분집합
    {a,b,c,d}
    - 1. a 포함 하지 않은 나머지 나열
    - 2. 1의 모든 부분집합에서 a를 추가한 집합 나열
   */

  public int powerSet(int[] S) {
    if (S.length == 0) {
      return 0;
    } else {
      int t = S[0];
      // 모든 부분 집합을 반환 할 수 있는 ... powerSet(S-S[0]);
      // 출력
    }

    return 0;
  }

  /*
     {2,3,4}의 모든 부분집합에 {1}를 추가한 집합들을 나열하려면
     - {3,4}의 모든 부분집합들에 {1}를 추가한 집합들을 나열하고
     - {3,4}의 모든 부분집합에 {1,2}를 추가한 집합들을 나열한다
   */
  List<String> list = new ArrayList<>();
  public void powerSet(int[] P, int[] S) {
    if (S.length == 0) {
      System.out.println(Arrays.toString(P));
    } else {
      int t = S[0];
      int[] ints = Arrays.copyOf(P, P.length + 1);
      ints[ints.length - 1] = t;

      powerSet(P, Arrays.copyOfRange(S,1, S.length));             // t를 포함하지 않은 집합 모두 출력
      powerSet(ints, Arrays.copyOfRange(S,1, S.length));     // t를 반드시 포함하는 부분 집합들
    }
  }

//  private char data[] = {'a', 'b', 'c', 'd', 'e', 'f'};
  private char data[] = {'a', 'b', 'c', 'd'};
  private int n = data.length;
  private boolean[] include = new boolean[n];

  // k-> 트리상 현재 나의 위치 표현
  public void powerSet2(int k) {
    if (k == n) { // 내 위치가 리프노드 라면 (현재, 여기에서 마지막 리프노드)
      for (int i = 0; i < n; i++) {
        if (include[i]) {
          System.out.print(data[i] + " ");
        }
      }
      System.out.println();
      return;
    }

    // 왼쪽 (포함하지 않는)
    include[k] = false;
    powerSet2(k + 1);

    // 오른쪽 (포함하는)
    include[k] = true;
    powerSet2(k + 1);
  }

}
