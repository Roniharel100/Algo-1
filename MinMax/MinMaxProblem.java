package MinMax;

import java.util.Arrays;

public class MinMaxProblem {
    static int[] minMax(int[] arr) {
        int min, max;
        if (arr[0] < arr[1]) {
            min = arr[0];
            max = arr[1];
        } else {
            min = arr[1];
            max = arr[0];
        }
        for (int i = 2; i < arr.length - 1; i = i + 2) {
            if (arr[i] < arr[i + 1]) {
                if (arr[i] < min) min = arr[i];
                if (arr[i + 1] > max) max = arr[i + 1];
            } else {
                if (arr[i + 1] < min) min = arr[i + 1];
                if (arr[i] > max) max = arr[i];
            }
        }
        if (arr.length % 2 != 0) {
            if (arr[arr.length - 1] > max) max = arr[arr.length - 1];
            else if (arr[arr.length - 1] < min) min = arr[arr.length - 1];
        }
        int[] ans = {min, max};
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {2, 3, 1, 2, 32, 3, 45, 34, 78, 98, 7};
        System.out.println(Arrays.toString(minMax(arr)));
    }
}