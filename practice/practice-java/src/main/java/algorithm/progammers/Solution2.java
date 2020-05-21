package algorithm.progammers;

public class Solution2 {

  public void test2() {
    System.out.println("test!!!");
//    Scanner sc = new Scanner(System.in);
//    int a = sc.nextInt();
//    int b = sc.nextInt();
//
//    System.out.println(a + b);

    int n=5;
    int m=3;
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        sb.append('*');
      }
      sb.append(System.lineSeparator());
    }
    System.out.println(sb.toString());
  }


  public int solution(int[][] office, int r, int c, String[] move) {

    int x = c;
    int y = r;

    int yTemp = y;
    int xTemp = x;

    boolean north = true;
    boolean south = false;
    boolean west = false;
    boolean east = false;

    int sum = 0;
    if (office[y][x] > 0) {
      sum = office[y][x];
      office[y][x] = 0;
    }


    //고, 북 : x-1, 남 : x+ 1
    for (int i = 0; i < move.length; i++) {

      final String cmd = move[i];

      switch (cmd) {
        case "go":
          if (north) {
            yTemp -= 1;
          } else if (south){
            yTemp += 1;
          } else if (west) {
            xTemp -= 1;
          } else if (east){
            xTemp += 1;
          }
          break;
        case "left":
          if (north) {
            north = false;
            west = true;
          } else if (south){
            east = true;
            south = false;
          } else if (west) {
            west = false;
            south = true;
          } else if (east) {
            north = true;
            east = false;
          }
          continue;
        case "right":
          if (north) {
            north = false;
            east = true;
          } else if (south){
            west = true;
            south = false;
          } else if (west) {
            west = false;
            north = true;
          } else if (east) {
            south = true;
            east = false;
          }
          continue;
        default:
          break;
      }

      if (yTemp < 0 || xTemp < 0 || yTemp > office.length || xTemp > office[0].length
              || office[yTemp][xTemp] < 0) {
        yTemp = y;
        xTemp = x;
        continue;
      }

      sum += office[yTemp][xTemp];
      x = xTemp;
      y = yTemp;
      office[y][x] = 0;

    }

    return sum;
  }



}
