package MaxMax;
import java.util.Arrays;

public class MaxMaxList {
    static int[] maxMax(int[] arr) {
        int max1, max2;
        if (arr[0] < arr[1]) {
            max2 = arr[0];
            max1 = arr[1];
        } else {
            max1 = arr[1];
            max2 = arr[0];
        }
        for (int i = 2; i < arr.length - 1; i = i + 2) {
            if (arr[i] > arr[i + 1]) {
                if (arr[i] > max1) {
                    max2 = max1;
                    max1 = arr[i];
                }
                if (arr[i + 1] > max2) max2 = arr[i + 1];
            } else {
                if (arr[i + 1] > max1) {
                    max2 = max1;
                    max1 = arr[i + 1];
                }
                if (arr[i] > max2) max2 = arr[i];
            }
        }
        if (arr.length % 2 != 0) {
            if (arr[arr.length - 1] > max1) {
                max2 = max1;
                max1 = arr[arr.length - 1];
            } else if (arr[arr.length - 1] > max2) max2 = arr[arr.length - 1];
        }
        int[] ans = {max1, max2};
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {2, 3, 1, 2, 32, 3, 45, 34, 78, 98, 7};
        System.out.println(Arrays.toString(maxMax(arr)));
    }
}
