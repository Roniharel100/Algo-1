package AirplaneProblem;

// בעיה 6- האם קודקוד נמצא על מסלול זול:
public class isOnCheapRouteDis {

    public static void main(String[] args) {
        Node[][] mat = InitMat1();
        int i = 2;
        int j = 3;
        int AtoB = cheapRouteAll(mat);
        int AtoC = cheapRouteToC(mat, i, j);
        int CtoB = cheapRouteFromC(mat, i, j);
        if (AtoB == AtoC + CtoB)
            System.out.println("True");
        else System.out.println("False");
    }

    private static Node[][] InitMat1() {
        Node[][] mat = new Node[4][4];

        mat[0][0] = new Node(1, 2);
        mat[0][1] = new Node(5, 4);
        mat[0][2] = new Node(3, 9);
        mat[0][3] = new Node(0, 9);

        mat[1][0] = new Node(5, 4);
        mat[1][1] = new Node(4, 4);
        mat[1][2] = new Node(7, 4);
        mat[1][3] = new Node(0, 2);

        mat[2][0] = new Node(8, 3);
        mat[2][1] = new Node(5, 6);
        mat[2][2] = new Node(8, 0);
        mat[2][3] = new Node(0, 0);

        mat[3][0] = new Node(2, 0);
        mat[3][1] = new Node(4, 0);
        mat[3][2] = new Node(5, 0);
        mat[3][3] = new Node(0, 0);
        return mat;
    }

    private static int cheapRouteAll(Node[][] mat) {
        int n = mat.length;
        int m = mat[0].length;

        for (int i = 1; i < n; i++) {
            mat[i][0].setValue(mat[i - 1][0].getValue() + mat[i - 1][0].getDown());
        }
        for (int j = 1; j < m; j++) {
            mat[0][j].setValue(mat[0][j - 1].getValue() + mat[0][j - 1].getRight());
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                int min = Math.min((mat[i][j - 1].getValue() + mat[i][j - 1].getRight()), (mat[i - 1][j].getValue() + mat[i - 1][j].getDown()));
                mat[i][j].setValue(min);
            }
        }
        return mat[n - 1][m - 1].getValue();
    }

    //AtoC
    private static int cheapRouteToC(Node[][] mat, int r, int c) {
        {
            int n = mat.length;
            int m = mat[0].length;

            for (int i = 1; i < n; i++) {
                mat[i][0].setValue(mat[i - 1][0].getValue() + mat[i - 1][0].getDown());
            }
            for (int j = 1; j < m; j++) {
                mat[0][j].setValue(mat[0][j - 1].getValue() + mat[0][j - 1].getRight());
            }

            for (int i = 1; i < n; i++) {
                for (int j = 1; j < m; j++) {
                    int min = Math.min((mat[i][j - 1].getValue() + mat[i][j - 1].getRight()), (mat[i - 1][j].getValue() + mat[i - 1][j].getDown()));
                    mat[i][j].setValue(min);
                }
            }
            return mat[r][c].getValue();
        }
    }

    //CtoB
    private static int cheapRouteFromC(Node[][] mat, int r, int c) {
        int n = mat.length;
        int m = mat[0].length;

        mat[r][c].setValue(0);

        for (int i = r + 1; i < n; i++) {
            mat[i][c].setValue(mat[i - 1][c].getValue() + mat[i - 1][c].getDown());
        }
        for (int j = c + 1; j < m; j++) {
            mat[r][j].setValue(mat[r][j - 1].getValue() + mat[r][j - 1].getRight());
        }

        for (int i = r + 1; i < n; i++) {
            for (int j = c + 1; j < m; j++) {
                int min = Math.min((mat[i][j - 1].getValue() + mat[i][j - 1].getRight()), (mat[i - 1][j].getValue() + mat[i - 1][j].getDown()));
                mat[i][j].setValue(min);
            }
        }
        return mat[n - 1][m - 1].getValue();
    }
}
