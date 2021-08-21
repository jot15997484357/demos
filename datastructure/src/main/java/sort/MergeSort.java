package sort;

import java.lang.reflect.Array;
import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {
        MergeSort sorter = new MergeSort();
        int[] arr = new int[]{1, 5, 2, 6, 3, 10, 7};
        sorter.sort0(arr, 0, arr.length - 1, new int[arr.length]);
        System.out.println(Arrays.toString(arr));
    }
    //要点1：使用辅助数组，避免在递归的过程中反复创建
    public void sort0(int[] arr, int left, int right, int[] tmp) {
        if (left < right) {//要点2：这里不需要等于，否则，left部分会无限递归
            int mid = (right + left) / 2;
            sort0(arr, left, mid, tmp);
            sort0(arr, mid + 1, right, tmp);
            merge(arr, left, mid, right, tmp);
        }
    }

    private void merge(int[] arr, int left, int mid, int right, int[] tmp) {
        int i, j, t;
        i = left;
        j = mid + 1;
        t = 0;
        while (i <= mid && j <= right) {
            if (arr[i] < arr[j]) {
                tmp[t++] = arr[i++];
            } else if (arr[i] > arr[j]) {
                tmp[t++] = arr[j++];
            } else {
                tmp[t++] = arr[i++];
                tmp[t++] = arr[j++];
            }
        }
        while (i <= mid) {
            tmp[t++] = arr[i++];
        }
        while (j <= right) {
            tmp[t++] = arr[j++];
        }
        //要点3：注意到t是从0开始的，所以这里的长度就是t
        System.arraycopy(tmp, 0, arr, left, t);
    }
}
