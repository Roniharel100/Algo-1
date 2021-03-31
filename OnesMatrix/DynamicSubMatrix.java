package OnesMatrix;

public class DynamicSubMatrix {
    public static void main(String[] args) {
        int[][] matrix = createMatrix(7);
        int ans = dynamicSub(matrix);
        System.out.println(ans);
    }

    private static int[][] createMatrix(int n) {
        int[][] m = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                m[i][j] = (int) (Math.random() * 2);
                System.out.print(m[i][j] + ",");
            }
            System.out.println();
        }
        return m;
    }

    private static int dynamicSub(int[][] matrix) {
        int ans = 0;
        int[][] help = new int[matrix.length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (i == 0 || j == 0)
                    help[i][j] = matrix[i][j];
                else if (matrix[i][j] == 0)
                    help[i][j] = 0;
                else {
                    int temp = Math.min(help[i - 1][j], help[i][j - 1]);
                    help[i][j] = Math.min(temp, help[i - 1][j - 1]) + 1;
                    if (help[i][j] > ans)
                        ans = help[i][j];
                }
            }
        }
        return ans;
    }
}
