package algorithm.progammers.kit;

import java.util.Arrays;

public class DFSBFS {
  int[][] adj;
  boolean[] visited;
  int count = 0;


  public int 타겟넘버(int[] numbers, int target) {
    adj = new int[numbers.length][2];
    visited = new boolean[adj.length];

    Arrays.fill(visited, false);

    for (int i = 0; i < adj.length; i++) {
      adj[i][0] = numbers[i];
      adj[i][1] = -numbers[i];
    }

    dfsAll(target);
    return 0;
  }

  public void dfsAll(int target) {
    for (int i = 0; i < adj.length; i++) {
      if (!visited[i]) {
        dfs(i, target);
      }
    }
  }

  private void dfs(int here, int target) {
    visited[here] = true;

    for (int i = 0; i < adj[here].length; i++) {
      int value = adj[here][i];
      if (!visited[value]) {
        dfs(i, target);
      }
    }
  }

  public int 타겟넘버2(int[] numbers, int target) {
    int answer = 0;
    answer = dfs2(numbers, 0, 0, target);
    return answer;
  }

  int dfs2(int[] numbers, int n, int sum, int target) {
    if(n == numbers.length) {
      if(sum == target) {
        return 1;
      }
      return 0;
    }
    return dfs2(numbers, n + 1, sum + numbers[n], target) + dfs2(numbers, n + 1, sum - numbers[n], target);
  }
}
