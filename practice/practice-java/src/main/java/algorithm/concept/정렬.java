package algorithm.concept;

import java.util.Arrays;

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

  public void quickSort(int[] arr, int p, int r) {
    // 분할정복법 : 분할 -> 정복 -> 합병 (머지소트) 활용. 합병은 할 필요없음. 피봇 기준으로 앞뒤 이미 정렬이 이미됨
    // 기준값 (피봇) 설정

    // 1. 정렬할 배열 주어짐. pivot 설정 (대게 맨끝으로 설정)
    // 2. 기준(피봇)보다 작은수는 왼쪽에, 나머지는 오른쪽에 오도록 배열을 재배치
    // 3. 기준의 왼쪽과 오른쪽을 각각 순환적으로 정렬. (재귀활용)

    if (p < r) {
      int q = partition(arr, p, r);       // 분할. q -> 피봇의 위치
      quickSort(arr, p, q - 1);   // 왼쪽 부분 배열 정렬
      quickSort(arr, q + 1, r);   // 오른쪽 부분 배열 정렬
    }

  }

  private int partition(int[] arr, int p, int r) {
    // 배열 arr[p...r]의 원소들을 arr[r]을 기준으로 양쪽으로 재배치하고
    // arr[r]이 자리한 위치를 return (index값)
    int i = p - 1; // i는 앞부터 시작하여 arr[r]의 값보다 커지는 지점을 기억하기 위한 index

    for (int j = p; j < r; j++) {
      if (arr[j] <= arr[r]) {
        i = i + 1;
        exchange(arr, i, j);
      }
    }
    exchange(arr, i + 1, r);
    return i + 1;
  }

  private void exchange(int[] arr, int i, int j) {
    System.out.println(Arrays.toString(arr));
    int tmp = arr[i];
    arr[i] = arr[j];
    arr[j] = tmp;
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
