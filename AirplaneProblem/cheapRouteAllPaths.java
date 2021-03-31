package AirplaneProblem;

import java.util.ArrayList;
import java.util.LinkedList;

//בעיה 4- להחזיר את כל המסלולים הזולים:
public class cheapRouteAllPaths {

    public static void main(String[] args) {
        Node[][] mat = InitMat1();
        LinkedList<String> all = AllPaths(mat);
        System.out.println(all);
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

    // O((N+M)*CountOfPath)
    private static LinkedList<String> AllPaths(Node[][] mat) {
        int n = mat.length;
        int m = mat[0].length;

        mat[0][0].setCountOfPath(1);
        for (int i = 1; i < n; i++) {
            mat[i][0].setValue(mat[i - 1][0].getValue() + mat[i - 1][0].getDown());
            mat[i][0].setCountOfPath(1);
        }
        for (int j = 1; j < m; j++) {
            mat[0][j].setValue(mat[0][j - 1].getValue() + mat[0][j - 1].getRight());
            mat[0][j].setCountOfPath(1);
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                int min = Math.min((mat[i][j - 1].getValue() + mat[i][j - 1].getRight()), (mat[i - 1][j].getValue() + mat[i - 1][j].getDown()));
                mat[i][j].setValue(min);
            }
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {

                if (mat[i][j - 1].getValue() + mat[i][j - 1].getRight() == mat[i - 1][j].getValue() + mat[i - 1][j].getDown()) {
                    mat[i][j].setCountOfPath(mat[i][j - 1].getCountOfPath() + mat[i - 1][j].getCountOfPath());
                } else if (mat[i][j - 1].getValue() + mat[i][j - 1].getRight() < mat[i - 1][j].getValue() + mat[i - 1][j].getDown()) {
                    mat[i][j].setCountOfPath(mat[i][j - 1].getCountOfPath());
                } else mat[i][j].setCountOfPath(mat[i - 1][j].getCountOfPath());
            }
        }

        LinkedList<String> all = getAllPaths(mat, n - 1, m - 1, "", new LinkedList<>());
        return all;
    }

    private static LinkedList<String> getAllPaths(Node[][] mat, int i, int j, String from, LinkedList<String> lists) {
        if (i > 0 && j > 0) {
            int a = mat[i - 1][j].getValue() + mat[i - 1][j].getDown(); // from up
            int b = mat[i][j - 1].getValue() + mat[i][j - 1].getRight(); // from left

            if (a < b) {
                getAllPaths(mat, i - 1, j, "d" + from, lists);
            } else if (b < a) {
                getAllPaths(mat, i, j - 1, "r" + from, lists);
            } else {
                getAllPaths(mat, i - 1, j, "d" + from, lists);
                getAllPaths(mat, i, j - 1, "r" + from, lists);
            }
        } else if (i == 0 && j == 0) {
            lists.add(from);
        } else if (i == 0) {
            getAllPaths(mat, i, j - 1, "r" + from, lists);
        } else if (j == 0) {
            getAllPaths(mat, i - 1, j, "d" + from, lists);
        }
        return lists;
    }
}
