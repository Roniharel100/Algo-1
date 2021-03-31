package LIS;
import java.util.Arrays;
import static java.util.Arrays.sort;

/**
 * אפשרות ג'
 * נשתמש באלגוריתם - LCS באופן הבא:
 * נמיין את מחרוזת X
 * ונבצע LCS(X,sort(X)) בעזרת תכנות דינאמי
 * כדי שנוכל להשתמש בפונקציה של LCS נצטרך לשנות אותה שתפעל על מערך ולא על מחרוזות בלבד
 */
public class LISUsedLCS {

    public static void main(String[] args) {
        int[] x = {1, 7, 9, 50, 2, 3, 4, 5};
        int[] lenPath = usedLCS(x);
        System.out.println("\n"+Arrays.toString(lenPath) + " " + lenPath.length);
    }

    private static int[] usedLCS(int[] x) {
        int[] sorted = new int[x.length];
        for (int i = 0; i < x.length; i++) {
            sorted[i] = x[i];
        }
        sort(sorted);
        return LCS(x,sorted);
    }

    public static int [] LCS(int[] x, int[] y) {
        System.out.println(Arrays.toString(x)); // המערך הרגיל
        System.out.println(Arrays.toString(y)+"\n"); // המערך הממויין
        int[][] helpTable = new int[y.length + 1][x.length + 1];
        for (int i = 0; i < helpTable.length; i++) {
            for (int j = 0; j < helpTable[0].length; j++) {
                if (i == 0 || j == 0)
                    helpTable[i][j] = 0;
                else if (y[i - 1] == x[j - 1])
                    helpTable[i][j] = helpTable[i - 1][j - 1] + 1;
                else
                    helpTable[i][j] = Math.max(helpTable[i - 1][j], helpTable[i][j - 1]);
            }
            System.out.println(Arrays.toString(helpTable[i]));
        }
        int[] ans = new int[helpTable[y.length][x.length]];
        int i = helpTable.length - 1;
        int j = helpTable[0].length - 1;
        int index = ans.length-1;
        while (helpTable[i][j] > 0) {
            if (y[i - 1] == x[j - 1]) {
                ans[index--] = y[i - 1];
                i--;
                j--;
            } else {
                if (helpTable[i-1][j] >= helpTable[i][j-1])
                    i--;
                else j--;
            }
        }
        return ans;
    }
}