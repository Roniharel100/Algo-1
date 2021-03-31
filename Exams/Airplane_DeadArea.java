package Exams;
import java.awt.Point;

// ממשו את בעיית המטוס כאשר נתון "שטח מת" = שטח שלא ניתן לעבור בו המוגדר ע"י 2 נקודות:
// p1= (x1 ,y1), p2=(x2 ,y2)
// (2 קצוות נגדיות של מלבן כאשר p1 היא הנק השמאלית התחתונה (בבעיית המטוס) וp2 היא הנק הימנית העליונה.)
// פתרון:
//עבור כל מעבר היוצא מנקודה מ"השטח המת" להפוך אותו למחיר אינסוף. ואז להריץ את האלגוריתם הרגיל
// סיבוכיות: (nm(O עבור שינוי הקלט + (nm(O עבור האלגוריתם הרגיל.

class DoubleNode {
    double price, x, y;

    public DoubleNode(double x, double y) {
        this.x = x;
        this.y = y;
    }
}

public class Airplane_DeadArea {

    public static void main(String[] args) {
        Node[][] mat = {
                {new Node(1, 5), new Node(4, 1), new Node(0, 6)},
                {new Node(4, 7), new Node(2, 5), new Node(0, 3)},
                {new Node(1, 0), new Node(2, 0), new Node(0, 0)}};
        System.out.println(minPriceWithDeadArea(mat, new Point(0, 0), new Point(2, 1)));
    }

    public static int minPriceWithDeadArea(Node[][] mat, Point p1, Point p2) {
        DoubleNode[][] mat2 = createNewMatrix(mat, p1, p2);
        return minPrice(mat2);
    }

    private static DoubleNode[][] createNewMatrix(Node[][] mat, Point p1, Point p2) {
        int n = mat.length;
        int m = mat[0].length;
        DoubleNode[][] newMat = new DoubleNode[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i >= p1.y && i <= p2.y && j >= p1.x && j <= p2.x) {
                    newMat[i][j] = new DoubleNode(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
                } else {
                    newMat[i][j] = new DoubleNode(mat[i][j].x, mat[i][j].y);
                }
            }
        }
        return newMat;
    }

    public static int minPrice(DoubleNode[][] mat) {
        int n = mat.length, m = mat[0].length;
        mat[0][0].price = 0;
        for (int i = 1; i < n; i++) {
            mat[i][0].price = mat[i - 1][0].price + mat[i - 1][0].y;
        }
        for (int i = 1; i < m; i++) {
            mat[0][i].price = mat[0][i - 1].price + mat[0][i - 1].x;
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                mat[i][j].price = Math.min(mat[i - 1][j].price + mat[i - 1][j].y, mat[i][j - 1].price + mat[i][j - 1].x);
            }
        }
        if (mat[n - 1][m - 1].price == Double.POSITIVE_INFINITY) return -1;
        return (int) mat[n - 1][m - 1].price;
    }
}
