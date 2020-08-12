package algorithm.concept;

public class 정렬 {

  // 힙정렬
  public void maxHeapify_recur(int[] heapArr, int rootIdx) {

    // 1. 자식이 없다면 종료

    // 2. 자식중 큰 놈 index k 리턴

    // 3. 부모와 자식 비교 -> 부모 >= 자식 이면 종료

    // 4. 아니라면 교환 (exchange) heapArr[rootIdx] and heapArr[k]

    // 5. 다시 재귀 maxHeapify_recur(heapArr, k)

  }

  //
  public void maxHeapify_iter(int[] heapArr, int rootIdx) {

    // while heapArr[rootIdx] has a child do

    // k = index of the biggest child of

    // if heapArr[rootIdx] >= heapArr[k] return (종료)

    // else : exchange heapArr[rootIdx] and heapArr[k];
    // -> rootIdx = k (자식을 루트로 설정) end
  }

  public void heapSort(int[] arr) {
    // 1. 역순 탐색(오->왼) -> leaf node 아닌 경우 찾기 -> start 지점

    // 2. 자식노드 중 큰놈 인덱스랑 자신 비교 -> 스왑

  }
}
