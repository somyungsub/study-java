package algorithm.progammers;

import java.util.Stack;

public class KaKao2019DevWinterInternShip {


  public int crane(int[][] board, int[] moves) {
    int answer = 0;
    Stack<Integer> stack = new Stack<>();

    for (int move : moves) {
      for (int j = 0; j < board.length; j++) {
        int doll = board[j][move - 1];
        if (doll != 0) {
          if (!stack.isEmpty() && doll == stack.peek()) {
            answer += 2;
            Integer pop = stack.pop();
          } else {
            stack.add(doll);
          }
          board[j][move - 1] = 0;
          break;
        }
      }
    }

    return answer;
  }
}
