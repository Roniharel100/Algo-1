package LCS;

import java.util.Arrays;
import java.util.Stack;

public class FullSearchLCS {

    public static void main(String[] args) {
        String x = "abcbdab";
        String y = "bdcaba";
        String ans = fullSearch(x, y);
        int lenWord = ans.length();
        System.out.println(ans + " " + lenWord);
    }

    private static String fullSearch(String x, String y) {
        int[][] binaryX = stringToBin(x);
        int[][] binaryY = stringToBin(y);
        String[] subX = sub(x, binaryX);
        String[] subY = sub(y, binaryY);
        if (y.length() < x.length())
            return findTheWord(subY, subX);
        else
            return findTheWord(subX, subY);
    }

    private static int[][] stringToBin(String a) {
        int[][] binaryA = new int[(int) Math.pow(2, a.length())][a.length()];
        for (int i = 0; i < binaryA.length; i++)
            binPlusOne(i, binaryA);
        return binaryA;
    }

    private static void binPlusOne(int i, int[][] binaryA) {
        int x = castToBin(i);
        for (int j = binaryA[0].length - 1; j >= 0; j--) {
            binaryA[i][j] = x % 10;
            x = x / 10;
        }
    }

    public static int castToBin(int n) {
        Stack<Integer> s = new Stack<>();
        int num = n;
        while (num > 0) {
            s.add(n % 2);
            n = n / 2;
            num = n;
        }
        int x = 0;
        while (!s.isEmpty()) {
            x = x * 10;
            x = x + s.pop();
        }
        return x;
    }

    private static String[] sub(String a, int[][] binaryA) {
        String[] subA = new String[(int) Math.pow(2, a.length())];
        for (int i = 1; i < binaryA.length; i++) {
            String word = "";
            for (int j = 0; j < binaryA[0].length; j++) {
                if (binaryA[i][j] == 1)
                    word += a.charAt(j);
            }
            subA[i] = word;
        }
        System.out.println(Arrays.toString(subA));
        return subA;
    }

    private static String findTheWord(String[] min, String[] max) {
        int maxLen = 0;
        String maxWord = "";
        for (int i = 1; i < min.length; i++) {
            for (int j = 1; j < max.length; j++) {
                if (min[i].equals(max[j]))
                    if (min[i].length() > maxLen) {
                        maxLen = min[i].length();
                        maxWord = min[i];
                    }
            }
        }
        return maxWord;
    }
}
