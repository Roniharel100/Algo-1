package AirplaneProblem;

//בעיה 7 -החזרת כל הקודקודים שנמצאים על המסלולים הזולים:
public class AllIsOnCheapRoute {

    public static void main(String[] args) {
        Node[][] mat = InitMat1();
        allOncheapRoute(mat);
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

    // 	חישוב העלות הזולה מ a->b  ומילוי value_a
    private static void allOncheapRoute(Node[][] mat) {
        int n = mat.length;
        int m = mat[0].length;

        for (int i = 1; i < n; i++) {
            mat[i][0].setValueA(mat[i - 1][0].getValueA() + mat[i - 1][0].getDown());
        }
        for (int j = 1; j < m; j++) {
            mat[0][j].setValueA(mat[0][j - 1].getValueA() + mat[0][j - 1].getRight());
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                int min = Math.min((mat[i][j - 1].getValueA() + mat[i][j - 1].getRight()), (mat[i - 1][j].getValueA() + mat[i - 1][j].getDown()));
                mat[i][j].setValueA(min);
            }
        }

       //	חישוב העלות הזולה מ b->a  מילוי value_b
        int nb = mat.length - 1;
        int mb = mat[0].length - 1;
        for (int i = nb - 1; i >= 0; i--)
            mat[i][mb].setValueB(mat[i + 1][mb].getValueB() + mat[i][mb].getDown());
        for (int j = mb - 1; j >= 0; j--)
            mat[nb][j].setValueB(mat[nb][j + 1].getValueB() + mat[nb][j].getRight());
        for (int i = nb - 1; i >= 0; i--) {
            for (int j = mb - 1; j >= 0; j--) {
                mat[i][j].setValueB(Math.min(mat[i + 1][j].getValueB() + mat[i][j].getDown(), mat[i][j + 1].getValueB() + mat[i][j].getRight()));
            }
        }
        //מעבר על כל המטריצה וחישוב value_a + value_b= mat[m,n].value
        int fullPath = mat[n - 1][m - 1].getValueA();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (mat[i][j].getValueB() + mat[i][j].getValueA() == fullPath)
                    System.out.println("[" + i + "," + j + "]");
            }
        }
    }
}
