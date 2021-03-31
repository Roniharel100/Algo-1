package LCS;

public class GreedyLCS {

    public static void main(String[] args) {
        String X = "abcdbca";
        String Y = "ecabdc";
        GreedyLCS(X, Y);
        ImprovedGreedyLCS(X, Y);
    }

    private static void GreedyLCS(String x, String y) {
        int ans1 = 0;
        String subsequence1 = "";
        int start1 = 0;
        for (int i = 0; i < x.length(); i++) {
            for (int j = start1; j < y.length(); j++) {
                if (x.charAt(i) == y.charAt(j)) {
                    subsequence1 += x.charAt(i);
                    ans1++;
                    start1 = j + 1;
                    break;
                }
            }
        }
        int ans2 = 0;
        String subsequence2 = "";
        int start2 = 0;
        for (int i = 0; i < y.length(); i++) {
            for (int j = start2; j < x.length(); j++) {
                if (y.charAt(i) == x.charAt(j)) {
                    subsequence2 += y.charAt(i);
                    ans2++;
                    start2 = j + 1;
                    break;
                }
            }
        }
        if (ans1 > ans2) {
            System.out.println("Greedy: subsequence = " + subsequence1 + ", LCS = " + ans1);
        } else {
            System.out.println("Greedy: subsequence = " + subsequence2 + ", LCS = " + ans2);
        }
    }

    private static void ImprovedGreedyLCS(String x, String y) {
        // In this implementation, we choose to start from the smallest string,
        // because it is more efficient to insert to the help array the smallest string
        String temp;
        if (y.length() < x.length())  //make X to be the smallest string.
        {
            temp = y;
            y = x;
            x = temp;
        }
        int[] help = new int[26];
        for (int i = 0; i < x.length(); i++) {
            help[x.charAt(i) - 'a']++;
        }
        int j = 0;
        String subsequence = "";
        int lcs = 0;
        for (int i = 0; i < y.length() && j < x.length(); i++) {
            if (help[y.charAt(i) - 'a'] > 0) {
                while (x.charAt(j) != y.charAt(i)) {
                    help[x.charAt(j) - 'a']--;
                    j++;
                }
                lcs++;
                subsequence += y.charAt(i);
                j++;
            }
        }
        System.out.println("Improved Greedy: subsequence = " + subsequence + ", LCS = " + lcs);
    }
}