package sort;

import java.util.Arrays;

public class InsertSort {
    public static void main(String[] args) {
        InsertSort sorter = new InsertSort();
        sorter.sort0(new int[]{1, 5, 2, 6, 3, 10, 7});
    }

    private void sort0(int[] arr) {
        //有两个变量表示下标a,b
        //[0,a)表示已经完成排序的部分，b表示arr[a]应该插入到有序部分的位置的前一个
        for (int i = 1; i < arr.length; i++) {
            int j = i - 1;
            for (; j >= 0; j--) {
                if (arr[i] >= arr[j]) {
                    //说明arr[i]应该插入到j的位置
                    if ((j + 1) != i) {
                        int tmp = arr[i];
                        System.arraycopy(arr, j + 1, arr, j + 2, i - j - 1);
                        arr[j + 1] = tmp;
                    }
                    break;
                }
            }
            if (j < 0) {
                int tmp = arr[i];
                System.arraycopy(arr, 0, arr, 1, i);
                arr[0] = tmp;
            }
        }
        System.out.println(Arrays.toString(arr));
    }
}
