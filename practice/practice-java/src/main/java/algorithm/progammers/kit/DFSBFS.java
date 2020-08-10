package algorithm.progammers.kit;

import java.util.*;

public class DFSBFS {
  int[][] adj;
  boolean[] visited;
  int count = 0;


  public int 타겟넘버(int[] numbers, int target) {
    int nodeSize = (int)Math.pow(2, numbers.length+1) - 1;
//    adj = new int[numbers.length][2];
    adj = new int[nodeSize][nodeSize];
    visited = new boolean[nodeSize];


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
      int sum = 0;
      if (!visited[i]) {
        dfs(i, target, sum);
      }
    }
  }

  private void dfs(int here, int target, int sum) {
    visited[here] = true;

    for (int i = 0; i < adj[here].length; i++) {
      int value = adj[here][i];
      if (adj.length - 1 != here) {
        dfs(here + 1, target, sum + value);
      } else {
        System.out.println("sum = " + sum);
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

  public int 네트워크(int n, int[][] computers) {


    return 0;
  }


  class Node {
    String next;
    int edge;

    public Node(String next, int edge) {
      this.next = next;
      this.edge = edge;
    }

    @Override
    public String toString() {
      return "Node{" +
          "next='" + next + '\'' +
          ", edge=" + edge +
          '}';
    }
  }

  public int 단어변환(String begin, String target, String[] words) {
    int n = words.length, ans = 0;

    Queue<Node> q = new LinkedList<>();

    boolean[] visit = new boolean[n];
    q.add(new Node(begin, 0));

    while(!q.isEmpty()) {
      Node cur = q.poll();
      if (cur.next.equals(target)) {
        ans = cur.edge;
        break;
      }

      for (int i=0; i<n; i++) {
        if (!visit[i] && isNext(cur.next, words[i])) {
          visit[i] = true;
          q.add(new Node(words[i], cur.edge + 1));
        }
      }

      System.out.println("q = " + q);
    }

    return ans;
  }

  private boolean isNext(String cur, String n) {
    int cnt = 0;
    for (int i=0; i<n.length(); i++) {
      if (cur.charAt(i) != n.charAt(i)) {
        if (++ cnt > 1)
          return false;
      }
    }

    return true;
  }





  private List<List<String>> getNode(List<List<String>> begin, String[] words) {
    if (begin.size() == words.length) {
      return begin;
    }
    List<String> list = new ArrayList<>();

    for (List<String> strings : begin) {
      for (int i = 0; i < strings.size(); i++) {
        String s = strings.get(i);

        for (int j = 0; j < s.length(); j++) {
          char[] chars = s.toCharArray();
          for (int k = 0; k < words.length; k++) {
            chars[j] = words[k].charAt(j);
            String trans = String.valueOf(chars);
            System.out.println("trans = " + trans);
            if (!trans.equals(s) && trans.equals(words[k])) {
              list.add(trans);
            }
          }
        }

      }
    }

    begin.add(list);
    if (list.size() != 0) {
      getNode(begin, words);
    }

    return begin;
  }


}
