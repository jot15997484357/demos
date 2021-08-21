package binsearch;

public class BinSearch {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 2, 2, 3};
        BinSearch searcher = new BinSearch();
        System.out.println(searcher.search(arr, 2));
        System.out.println(searcher.searchLeft(arr,2));
        System.out.println(searcher.searchRight(arr,2));
    }

    //查找右边界
    public int searchRight(int[] arr, int tar) {
        int l = 0;
        int r = arr.length;//这就决定了左闭右开
        while (l < r) {//左闭右开，所以不可能相等
            int mid = (l + r) >> 1;
            if (arr[mid] == tar) {
                l = mid + 1;//左闭，所以mid需要被排除，否则重复searh mid
            } else if (arr[mid] < tar) {
                l = mid + 1;
            } else if (arr[mid] > tar) {
                r = mid;//又开，所以mid本身不会被search，不存在重复search mid
            }
        }
        //停止在右开的索引位置
        return r - 1;//左边不断右移，最终l==r结束在右边界，右开，所以右边界需要减一得到真实的右边界
    }

    //查找左边界
    public int searchLeft(int[] arr, int tar) {
        int l = 0;
        int r = arr.length;
        while (l < r) {
            int mid = (r + l) >> 1;
            if (arr[mid] == tar) {
                r = mid;
            } else if (arr[mid] > tar) {
                r = mid;//由于r是开区间，mid也正好搜索过了
            } else if (arr[mid] < tar) {
                l = mid + 1;//因为l是闭区间，因此是在搜索范围之内
            }
        }
        return l;//停止在左闭的索引位置
    }

    //basic search
    public int search(int[] arr, int tar) {
        int l = 0;
        int r = arr.length - 1;
        while (l <= r) {
            int mid = (r + l) >> 1;
            if (arr[mid] == tar) {
                return mid;
            } else if (arr[mid] < tar) {
                l = mid + 1;
            } else if (arr[mid] > tar) {
                r = mid - 1;
            }
        }
        return -1;
    }
}
