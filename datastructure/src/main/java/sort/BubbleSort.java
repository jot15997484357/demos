package sort;

import java.util.Arrays;

public class BubbleSort {
    public static void main(String[] args) {
        BubbleSort sorter = new BubbleSort();
        sorter.sort3(new int[]{1, 5, 2, 6, 3, 10, 7});
    }

    private void sort1(int[] arr) {
        int N = arr.length;
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < N - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
        }
        System.out.println(Arrays.toString(arr));
    }

    private void sort2(int[] arr) {
        int N = arr.length;
        boolean flag = true;
        for (int i = 1; i < N; i++) {
            if (!flag) break;
            flag = false;
            for (int j = 0; j < N - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                    flag = true;
                }
            }
        }
        System.out.println(Arrays.toString(arr));
    }

    private void sort3(int[] arr) {
        int border = arr.length - 1;
        while (border > 0) {
            int k = border;
            border = 0;
            for (int i = 0; i < k; i++) {
                if (arr[i] > arr[i + 1]) {
                    int tmp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = tmp;
                    border = i;
                }
            }
        }
        System.out.println(Arrays.toString(arr));
    }
}
