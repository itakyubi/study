package com.wa.leetcode;

public class SearchRotateArray {

    public static void main(String[] args) {
        System.out.println(search(new int[]{15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14}, 5));
    }

    public static int search(int[] arr, int target) {
        if (arr == null || arr.length == 0)
            return -1;

        int left = 0, right = arr.length - 1, mid;
        while (left < right) {
            mid = (left + right) / 2;
            if (arr[mid] > arr[left]) {
                if (target <= arr[mid] && target >= arr[left]) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            } else if (arr[mid] < arr[left]) {
                if (target <= arr[mid] || target >= arr[left]) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            } else {
                if (target == arr[left]){
                    right = left;
                }else {
                    left++;
                }
            }
        }
        return arr[left] == target ? left : -1;
    }
}
