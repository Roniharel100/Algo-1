package LCS;

public class DynamicLCS {

    public static void main(String[] args) {
        String x = "abcbdab";
        String y = "bdcaba";
        int wordLen = LCS(x, y);
        String myWord = StringLCS(x, y);
        System.out.println(wordLen + " " + myWord);
    }
    // מציאת אורך תת המחרוזת הארוכה ביותר
    public static int LCS(String x, String y) {
        int[][] helpTable = new int[y.length() + 1][x.length() + 1];
        for (int i = 0; i < helpTable.length; i++) {
            for (int j = 0; j < helpTable[0].length; j++) {
                if (i == 0 || j == 0)
                    helpTable[i][j] = 0;
                else if (y.charAt(i - 1) == x.charAt(j - 1))
                    helpTable[i][j] = helpTable[i - 1][j - 1] + 1;
                else
                    helpTable[i][j] = Math.max(helpTable[i - 1][j], helpTable[i][j - 1]);
            }
        }
        return helpTable[y.length()][x.length()];
    }
    // מציאת המחרוזת הארוכה ביותר
    public static String StringLCS(String x, String y) {
        int[][] helpTable = new int[y.length() + 1][x.length() + 1];
        for (int i = 0; i < helpTable.length; i++) {
            for (int j = 0; j < helpTable[0].length; j++) {
                if (i == 0 || j == 0)
                    helpTable[i][j] = 0;
                else if (y.charAt(i - 1) == x.charAt(j - 1))
                    helpTable[i][j] = helpTable[i - 1][j - 1] + 1;
                else
                    helpTable[i][j] = Math.max(helpTable[i - 1][j], helpTable[i][j - 1]);
            }
        }
        String myWord = "";
        int i = helpTable.length - 1;
        int j = helpTable[0].length - 1;
        while (helpTable[i][j] > 0) {
            if (y.charAt(i - 1) == x.charAt(j - 1)) {
                myWord = y.charAt(i - 1) + myWord;
                i--;
                j--;
            } else {
                if (helpTable[i - 1][j] >= helpTable[i][j-1])
                    i--;
                else j--;
            }
        }
        return myWord;
    }
}
