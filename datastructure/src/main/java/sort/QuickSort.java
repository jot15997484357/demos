package sort;

import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
        QuickSort sorter = new QuickSort();
        sorter.sort0(new int[]{1, 5, 2, 6, 3, 10, 7});
    }

    public void sort0(int[] arr) {
        int a = 0;
        int b = arr.length - 1;
        func(arr, a, b);
        System.out.println(Arrays.toString(arr));
    }

    private void func(int[] arr, int low, int high) {
        int i, j, tmp;
        if (high < low) return;
        i = low;
        j = high;
        tmp = arr[low];//就是分隔值
        while (i < j) {
            while (arr[j] >= tmp && i < j) {
                j--;
            }
            while (arr[i] <= tmp && i < j) {
                i++;
            }
            if (i < j) {
                int tmp1 = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp1;
            }
        }
        //最后i==j一定成立
        arr[low] = arr[i];
        arr[i] = tmp;

        func(arr, low, j - 1);
        func(arr, j + 1, high);
    }


}
