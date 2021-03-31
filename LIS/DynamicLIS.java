package LIS;

import java.util.Arrays;

public class DynamicLIS {
    public static void main(String[] args) {
      //  int[] x = {1, 7, 9, 50, 2, 3, 4, 5};
        int[] x = {8, 4, 12, 2, 3, 10,14};
        int lenPath = dynamicLen(x);
        int[] ans = LISDynamic(x);
        System.out.println(lenPath);
        System.out.println(Arrays.toString(ans));
    }

    /**
     * אפשרות ד'
     * נדרוס איברים תוך כדי שמירה על האורך הנכון,
     * נשתמש במערך עזר ונחזיר את האורך הגדול ביותר שהצלחנו להגיע אליו
     */
    private static int dynamicLen(int[] x) {
        int index = 0;
        int[] help = new int[x.length];
        for (int j : x) {
            if (j > help[index]) {
                help[++index] = j;
                System.out.println(Arrays.toString(help));
            } else {
                help[binarySearchLen(help, j, index)] = j;
            }
        }
        return index;
    }

    private static int binarySearchLen(int[] help, int key, int index) {
        int low = 0;
        int high = index;
        while (high >= low) {
            int middle = (low + high) / 2;
            if (help[middle] > key && middle > 0 && help[middle - 1] < key) {
                return middle;
            }
            if (help[middle] < key) {
                low = middle + 1;
            }
            else if (help[middle] > key) {
                high = middle - 1;
            }
        }
        return index;
    }

    /**
     * אפשרות ה'
     * לא נדרוס את כל האיברים אלא נשמור את מה שמחקנו כדי שנוכל להחזיר דוגמא לתת סדרה חוקית.
     * כל פעם שנקבל מספר נבדוק אם הוא גדול מהאיבר האחרון בשורה האחרונה במטריצה:
     * אם כן- נשכפל את השורה ונוסיף אותו לסוף.
     * אם לא- נבדוק באלכסון איפה להוסיף אותו במקום מספר שגדול ממנו, ונעתיק את כל השורה שמעליו לשורה שהשתנתה.
     * נחזיר את השורה האחרונה במערך.
     */
    public static int[] LISDynamic(int[] arr) {
        int n = arr.length;
        int[][] matriza = new int[n][n];
        matriza[0][0] = arr[0];
        int len = 1;
        for (int i = 1; i < n; i++) {
            System.out.println(" len "+ len+" arr[i] "+ arr[i]);
            int index = binarySearchBetween(matriza, len, arr[i]);
            System.out.println(" return "+ index);
            matriza[index][index] = arr[i];
            if (index == len) len++;
            copy(matriza, index);
        }
        int[] ans = new int[len];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = matriza[len - 1][i];
        }
        return ans;
    }

    private static int binarySearchBetween(int[][] mat, int end, int v) {
        if (v > mat[end - 1][end - 1]) return end;
        if (v < mat[0][0]) return 0;
        int high = end, low = 0;
        while (low <= high) {
            if (low == high) return low;
            int mid = (low + high) / 2;
            if (mat[mid][mid] == v) return mid;
            if (mat[mid][mid] > v) high = mid;
            else low = mid + 1;
        }
        return -1;
    }

    private static void copy(int[][] mat, int index) {
        for (int i = 0; i < index; i++) {
            mat[index][i] = mat[index - 1][i];
        }
    }
}