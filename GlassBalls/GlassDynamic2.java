package GlassBalls;

import java.util.Arrays;


public class GlassDynamic2 {
    public static void main(String[] args) {
        glassBall2(100);
        System.out.println(numberOfchecking(6,2));
        System.out.println(NumberOfTries2(10)); // two balls
    }

    public static void glassBall2(int n) {
        int step = 1;
        while (step*(step + 1)/2 < n) {
            step++;
        }
        System.out.println(step);
    }

    public static int numberOfchecking(int n, int k) {
        int[][] numchecks = new int[k + 1][n + 1];
        for (int i = 0; i < n + 1; i++) { // מאתחל את 2 השורות הראשונות
            numchecks[0][i] = 0;
            numchecks[1][i] = i;
        }
        for (int i = 2; i < k + 1; i++) {
            numchecks[i][0] = 0;
            numchecks[i][1] = 1;
            if (n >= 2) {
                numchecks[i][2] = 2;
            }
            // כאשר יש יותר מ2 קומות
            for (int j = 2; j < n + 1; j++) {
                int in = n + 1;
                for (int p = 1; p < j; p++) {
                    in = Math.min(Math.max(numchecks[i-1][p - 1], numchecks[i][j - p]) + 1, in);
                }
                numchecks[i][j] = in;
            }
        }
        for (int j = 0; j < numchecks.length; j++) {
            for (int i = 0; i < numchecks[0].length; i++) {
                System.out.print(numchecks[j][i] + ",\t");
            }
            System.out.println();
        }
        return numchecks[k][n];
    }

    public static int NumberOfTries2(int n) {
        int[] floor = new int[n+1];
        floor[0] = 0;
        floor[1] = 1;
        floor[2] = 2;
        for (int i = 3; i < n + 1; i++) {
            int in = n;
            for (int j = 1; j < i; j++) {
                int x = Math.max(j, floor[i-j] + 1) ;
                if (x < in ) {
                    in = x;
                }
            }
            floor[i] = in;
        }
        System.out.println(Arrays.toString(floor));
        return floor[n];
    }
}