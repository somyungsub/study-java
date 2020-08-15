package algorithm.concept;

public class 정렬 {

  public void selectionSort(int[] arr) {

  }

  public void bubbleSort(int[] arr) {

  }

  public void insertSort(int[] arr) {

  }

  public void mergeSort(int[] arr, int p, int r) {
    // 1. 나누어 정렬
    // 2. 재귀
    // 3. 추가배열 이용. 정렬된 2개의 배열을 합쳐서 전체 정렬

    if (p < r) {
      int q = (p + r) / 2;          // p,r ,의 중간 지점
      mergeSort(arr, p, q);         // 전반부 정렬
      mergeSort(arr, q + 1, r); // 후반부 정렬
      merge(arr, p, q, r);          // 합병
    }
  }

  private void merge(int[] arr, int p, int q, int r) {
    // 정렬되어 있는 두배열 A[p...q], A[q+1...r]을 합하여
    // 정렬된 하나의 배열 A[p...r]을 만든다.
    int i = p, j = q + 1, k = p;
    int[] tmp = new int[arr.length];

    // i부터 (왼쪽), j부터(오른쪽) 비교 -> tmp에 저장
    while (i <= q && j <= r) {
      if (arr[i] <= arr[j]) {
        tmp[k++] = arr[i++];
      } else {
        tmp[k++] = arr[j++];
      }
    }

    // 한쪽 끝나고 남은 부분 정렬
    // 앞부분이 남은 경우
    while (i <= q) {
      tmp[k++] = arr[i++];
    }
    // 뒷부분이 남은 경우
    while (j <= r) {
      tmp[k++] = arr[j++];
    }

    for (int l = p; l <= r; l++) {
      arr[l] = tmp[l];
    }

  }


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
