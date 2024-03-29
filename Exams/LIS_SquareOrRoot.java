package Exams;

import java.util.HashMap;

public class LIS_SquareOrRoot {
    // כתבו אלגוריתם המקבל מערך ומחזיר את אורך תת הסדרה הארוכה ביותר כך שכל איבר הוא ריבוע של
    //האיבר שלפניו או שורש של האיבר שלפניו
    //2, 7, 1, 49, 3, 4, 1, 9, 2, 30, 81 :לדוגמא
    //. 3, 9, 81 :תהיה התשובה
    //סיבוכיות: ( n(O כי על כל איבר עוברים על כל אלה שלפניו ולוקחים את המקסימום.

    public static void main(String[] args) {
        System.out.println(longestSequenceWithConditionIntime
                (new int[]{2, 7, 1, 49, 3, 4, 1, 9, 2, 30, 81}));
    }

    public static int longestSequenceWithConditionIntime(int[] arr) {
        HashMap<Integer, Integer> lsc = new HashMap<Integer, Integer>();
        int n = arr.length;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            Integer x = lsc.get(arr[i] * arr[i]);
            Integer y = lsc.get((int) (Math.sqrt(arr[i])));
            int max = 1;
            if (x != null) max = Math.max(max, x + 1);
            if (y != null && Math.sqrt(arr[i]) == (int) (Math.sqrt(arr[i]))) max = Math.max(max, y + 1);
            lsc.put(arr[i], max);
            if (max > ans) ans = max;
        }
        return ans;
    }

    public static int longestSequenceWithConditionSize(int[] arr) {
        int n = arr.length;
        int[] lsc = new int[n];
        int ans = 1;
        for (int i = 0; i < n; i++) {
            int max = 1;
            for (int j = 0; j < i; j++) {
                if (condition(arr[i], arr[j])) {
                    if (lsc[j] + 1 > max) max = lsc[j] + 1;
                }
            }
            lsc[i] = max;
            if (max > ans) ans = max;
        }
        return ans;
    }

    public static int[] longestSequenceWithCondition(int[] arr) {
        int n = arr.length;
        int[] lsc = new int[n];
        int ans = 1;
        int index = 0;
        for (int i = 0; i < n; i++) {
            int max = 1;
            for (int j = 0; j < i; j++) {
                if (condition(arr[i], arr[j])) {
                    if (lsc[j] + 1 > max) max = lsc[j] + 1;
                }
            }
            lsc[i] = max;
            if (max > ans) {
                ans = max;
                index = i;
            }
        }
        int[] seq = new int[ans];
        int k = ans - 1;
        while (ans > 1) {
            for (int i = 0; i < index; i++) {
                if (condition(arr[index], arr[i]) && lsc[i] == lsc[index] - 1) {
                    seq[k--] = arr[index];
                    index = i;
                    ans--;
                    break;
                }
            }
        }
        seq[0] = arr[index];
        return seq;
    }

    private static boolean condition2(int x, int y) {
        return x * x == y || y * y == x;
    }

    private static boolean condition3(int x, int y) {
        return x >= y;
    }

    private static boolean condition(int x, int y) {
        return Math.abs(x - y) <= 1;
    }
}
