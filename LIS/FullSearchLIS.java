package LIS;

import java.util.Arrays;
import java.util.Stack;

/**
 * אפשרות ב'*
 * נעבור על כל תתי הסדרות האפשריות-
 * נבנה טבלה שתעזור לנו בעזרת ייצוג בינארי, ונחפש תת הסדרה העולה הארוכה ביותר
 * על כל תת סדרה נבדוק:
 * האם הסדרה עולה?
 * מה הגודל שלה?
 */
public class FullSearchLIS {

    public static void main(String[] args) {
        int[] x = {1, 7, 9, 50, 2, 3, 4, 5};
        int[] lenPath = fullSearch(x);
        System.out.println(Arrays.toString(lenPath) + " \n" + lenPath.length);
    }

    private static int[] fullSearch(int[] x) {
        int[][] binaryX = ArrayToBin(x);
        int[][] subX = sub(x, binaryX);
        return findTheWord(subX);
    }

    private static int[][] ArrayToBin(int[] a) {
        int[][] binaryA = new int[(int) Math.pow(2, a.length)][a.length];
        for (int i = 0; i < binaryA.length; i++)
            binPlusOne(i, binaryA);
        return binaryA;
    }

    private static void binPlusOne(int i, int[][] binaryA) {
        int x = castToBin(i);
        for (int j = binaryA[0].length - 1; j >= 0; j--) {
            binaryA[i][j] = x % 10;
            x /= 10;
        }
    }

    public static int castToBin(int n) {
        Stack<Integer> s = new Stack<>();
        int num = n;
        while (num > 0) {
            s.add(n % 2);
            n /= 2;
            num = n;
        }
        int x = 0;
        while (!s.isEmpty()) {
            x *= 10;
            x += s.pop();
        }
        return x;
    }

    private static int[][] sub(int[] a, int[][] binaryA) {
        int[][] subA = new int[(int) Math.pow(2, a.length)][];
        for (int i = 1; i < binaryA.length; i++) {
            int sumOfOnes = 0;
            for (int t = 0; t < binaryA[0].length; t++) {
                if (binaryA[i][t] == 1)
                    sumOfOnes++;
            }
            int[] word = new int[sumOfOnes];
            int index = 0;
            for (int j = 0; j < binaryA[0].length; j++) {
                if (binaryA[i][j] == 1) {
                    word[index++] = a[j];
                }
            }
            subA[i] = word;
        }
        return subA;
    }
    // מה הגודל של הסדרה
    private static int[] findTheWord(int[][] min) {
        int maxLen = 0;
        int[] ans = null;
        for (int i = min.length - 1; 0 < i; i--) {
            int[] temp = min[i];
            if (temp.length > maxLen) {
                if (isUp(temp)) {
                    ans = temp;
                    maxLen = temp.length;
                }
            }
        }
        return ans;
    }

    // האם הסדרה עולה
    private static boolean isUp(int[] temp) {
        for (int i = 0; i < temp.length - 1; i++)
            if (temp[i] > temp[i + 1])
                return false;
        return true;
    }
}