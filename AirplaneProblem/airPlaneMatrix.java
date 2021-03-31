package AirplaneProblem;
import java.util.Arrays;
import java.util.LinkedList;

public class airPlaneMatrix {

    static int cheapRouteDis(Node[][] mat) {

//        fill first row
        for (int i = 1; i < mat.length; i++) {
            mat[i][0].setValue(mat[i - 1][0].getValue() + mat[i - 1][0].getRight());
        }
//        fill first column
        for (int i = 1; i < mat[0].length; i++) {
            mat[0][i].setValue(mat[0][i - 1].getValue() + mat[0][i - 1].getDown());
        }

        for (int i = 1; i < mat.length; i++) {
            for (int j = 1; j < mat[0].length; j++) {
                int min = Math.min(mat[i - 1][j].getValue() + mat[i - 1][j].getRight(), mat[i][j - 1].getValue() + mat[i][j - 1].getDown());
                mat[i][j].setValue(min);
            }
        }


        for(int i=0; i<mat[0].length; i++){
            for(int j=0; j<mat.length; j++){
                if(j==0) System.out.println();
                System.out.print("  "+mat[i][j].getValue());
            }
        }
        System.out.println();
        return mat[mat[0].length - 1][mat.length - 1].getValue();
    }

    static String exampleOfPath(Node[][] mat) {
        int i = mat[0].length - 1, j = mat.length - 1;
        String path = "";
        while (i > 0 && j > 0) {
            if (mat[i][j].getValue() == mat[i - 1][j].getValue() + mat[i - 1][j].getRight()) {
                path = 'r' + path;
                i--;
            } else {
                path = 'd' + path;
                j--;
            }

        }
        while (i > 0) {
            path = 'r' + path;
            i--;
        }
        while (j > 0) {
            path = 'd' + path;
            j--;
        }
        return path;
    }

    static int cheapRouteCount(Node[][] mat) {

//        fill first row and first column
        for (int i = 1, j = 1; i < mat[0].length || j < mat.length; i++, j++) {
            if (i < mat[0].length)
                mat[i][0].setCountOfPath(1);
            if (j < mat.length)
                mat[0][j].setCountOfPath(1);

        }

        for (int i = 1; i < mat[0].length; i++) {
            for (int j = 1; j < mat.length; j++) {
                int right = mat[i - 1][j].getValue() + mat[i - 1][j].getRight();
                int down = mat[i][j - 1].getValue() + mat[i][j - 1].getDown();
                mat[i][j].setValue(Math.min(down, right));
                if (down == right)
                    mat[i][j].setCountOfPath(mat[i - 1][j].getCountOfPath() + mat[i][j - 1].getCountOfPath());
                else if (down < right) mat[i][j].setCountOfPath(mat[i][j - 1].getCountOfPath());
                else mat[i][j].setCountOfPath(mat[i - 1][j].getCountOfPath());
            }
        }

        return mat[mat[0].length - 1][mat.length - 1].getCountOfPath();
    }

    static LinkedList<String> allPath(Node[][] mat) {
        for (int i = 0; i < mat[0].length; i++) {
            for (int j = 0; j < mat.length; j++) {
                mat[i][j].getPaths().clear();
            }}
        //        fill first row
        for (int i = 1; i < mat[0].length; i++) {
            int k = i;
            String path = "";
            while (k > 0) {
                path += "r";
                k--;
            }
            mat[i][0].getPaths().add(path);
        }
//        fill first column
        for (int i = 1; i < mat.length; i++) {
            int k = i;
            String path = "";
            while (k > 0) {
                path += "d";
                k--;
            }
            mat[0][i].getPaths().add(0, path);
        }

        for (int i = 1; i < mat[0].length; i++) {
            for (int j = 1; j < mat.length; j++) {
                if (mat[i][j].getValue() == mat[i - 1][j].getValue() + mat[i - 1][j].getRight()) {
                    for (String list : mat[i - 1][j].getPaths()) {
                        mat[i][j].getPaths().addLast(list + "r");
                    }
                }
                if (mat[i][j].getValue() == mat[i][j - 1].getValue() + mat[i][j - 1].getDown()) {
                    for (String list : mat[i][j - 1].getPaths()) {
                        mat[i][j].getPaths().addLast(list + "d");
                    }
                }
            }
        }
        return mat[mat[0].length - 1][mat.length - 1].getPaths();
    }

    static String minimumInquiries(Node[][] mat) {
        LinkedList<String> all = allPath(mat);
        int min=all.get(0).length();
        String minPath=all.get(0);

        for (String path : all) {
            int count=0;
            for (int j = 1; j < path.length(); j++) {
                if (path.charAt(j) == 'r' && path.charAt(j - 1) == 'd')
                   count++;
                if (path.charAt(j) == 'd' && path.charAt(j - 1) == 'r')
                    count++;
            }
            if(count<min) {
                min=count;
                minPath=path;
            }
        }
        return minPath;
    }

//    static int cheapRouteDisFromPoint(Node[][] mat,int n, int m){
//        mat[n][m].setValue(0);
////        fill first row
//        for (int i = n+1; i <mat[0].length; i++) {
//            mat[i][m].setValue(mat[i - 1][m].getValue() + mat[i - 1][m].getRight());
//        }
////        fill first column
//        for (int i = m+1; i < mat.length; i++) {
//            mat[n][i].setValue(mat[n][i - 1].getValue() + mat[n][i - 1].getDown());
//        }
//
//        for (int i = n+1; i <mat[0].length; i++) {
//            for (int j = m+1; j <mat.length; j++) {
//                int min = Math.min(mat[i - 1][j].getValue() + mat[i - 1][j].getRight(), mat[i][j - 1].getValue() + mat[i][j - 1].getDown());
//                mat[i][j].setValue(min);
//            }
//        }
//
//        return mat[mat[0].length-1][mat.length-1].getValue();
//        }

    static int cheapRouteDisFromA(Node[][] mat,int n, int m){
//        fill first row
        for (int i = n+1; i <mat[0].length; i++) {
            mat[i][m].setValueA(mat[i - 1][m].getValueA() + mat[i - 1][m].getRight());
        }
//        fill first column
        for (int i = m+1; i < mat.length; i++) {
            mat[n][i].setValueA(mat[n][i - 1].getValueA() + mat[n][i - 1].getDown());
        }

        for (int i = n+1; i <mat[0].length; i++) {
            for (int j = m+1; j <mat.length; j++) {
                int min = Math.min(mat[i - 1][j].getValueA() + mat[i - 1][j].getRight(), mat[i][j - 1].getValueA() + mat[i][j - 1].getDown());
                mat[i][j].setValueA(min);
            }
        }

        return mat[mat[0].length-1][mat.length-1].getValueA();

        //        fill first row
//
    }

    static int cheapRouteDisFromB(Node[][] mat){
        for(int i=0; i<mat[0].length; i++){
            for(int j=0; j<mat.length; j++){
                if(j==0) System.out.println();
                System.out.print("  "+mat[i][j].getValue());
            }
        }
        System.out.println();
//        fill first row
        for (int i = mat[0].length-2; i>=0; i--) {
            mat[i][mat.length-1].setValueB(mat[i + 1][mat.length-1].getValueB() + mat[i][mat.length-1].getRight());
        }

//        fill first column
        for (int i = mat[0].length-2; i>=0; i--) {
            mat[mat[0].length-1][i].setValueB(mat[mat[0].length-1][i + 1].getValueB() + mat[mat[0].length-1][i].getDown());
        }

        for(int i=0; i<mat[0].length; i++){
            for(int j=0; j<mat.length; j++){
                if(j==0) System.out.println();
                System.out.print("  "+mat[i][j].getValueB());
            }
        }
        System.out.println();

//        for (int i = mat[0].length-2; i >= 0; i--) {
//            for (int j = mat.length-2; j >=0; j--) {
//                int min = Math.min(mat[i-1][j].getValueB() + mat[i-1][j].getRight(), mat[i][j - 1].getValueB() + mat[i][j - 1].getDown());
//                mat[i][j].setValueB(min);
//            }
//        }
        return mat[mat[0].length-1][mat.length-1].getValueB();

    }

//    static boolean midpoint(Node[][] mat, int x, int y){
//        int dis=cheapRouteDis(mat);
//        int temp=mat[x][y].getValue();
//        int dis_x_y=cheapRouteDisFromPoint(mat,x,y);
//        if(dis==temp+dis_x_y)
//            return true;
//        return false;
//    }

    static LinkedList<String> allMidPoint(Node[][] mat){
        LinkedList<String> all= new LinkedList<>();
        //        fill first row
        for (int i = mat[0].length-2; i>0; i--) {
            mat[i][mat[0].length-1].setValue(mat[i - 1][0].getValue() + mat[i - 1][mat[0].length-1].getRight());
        }
//        fill first column
        for (int i = mat[0].length-2; i>0; i--) {
            mat[mat[0].length-1][i].setValue(mat[0][i - 1].getValue() + mat[mat[0].length-1][i - 1].getDown());
        }

        for (int i = mat[0].length-2; i >= 0; i--) {
            for (int j = mat.length-2; j >=0; j--) {
                int min = Math.min(mat[i+1][j].getValue() + mat[i+1][j].getRight(), mat[i][j + 1].getValue() + mat[i][j + 1].getDown());
                mat[i][j].setValueA(min);
            }
        }

        for(int i=0; i<mat[0].length; i++){
            for(int j=0; j<mat.length; j++){
                if(mat[i][j].getValueB()+mat[i][j].getValueA()==mat[i][j].getValue())
                    all.add(i+","+j);
            }
        }
        return all;
    }





    private static Node[][] InitMat1() {
        Node[][] mat = new Node[4][4];

        mat[0][0] = new Node(1, 2);
        mat[0][1] = new Node(5, 4);
        mat[0][2] = new Node(3, 9);
        mat[0][3] = new Node(9, 0);

        mat[1][0] = new Node(5, 4);
        mat[1][1] = new Node(4, 4);
        mat[1][2] = new Node(7, 4);
        mat[1][3] = new Node(2, 0);

        mat[2][0] = new Node(8, 3);
        mat[2][1] = new Node(5, 6);
        mat[2][2] = new Node(8, 0);
        mat[2][3] = new Node(15, 0);

        mat[3][0] = new Node(0, 0);
        mat[3][1] = new Node(0, 7);
        mat[3][2] = new Node(0, 9);
        mat[3][3] = new Node(0, 0);
        return mat;
    }

    private static Node[][] InitMat2() {
        Node[][] mat = new Node[4][4];

//        example2

//                --4----6----5--
//
//        3    7    1    6
//
//                --2----3----4--
//
//        1    3    3    1
//
//                --2----1----2--
//
//        5    1    2    3
//
//                --4----2----4--

        mat[0][0] = new Node(5, 4);
        mat[0][1] = new Node(1, 2);
        mat[0][2] = new Node(2, 4);
        mat[0][3] = new Node(3, 0);

        mat[1][0] = new Node(1, 2);
        mat[1][1] = new Node(3, 1);
        mat[1][2] = new Node(3, 2);
        mat[1][3] = new Node(1, 0);

        mat[2][0] = new Node(3, 2);
        mat[2][1] = new Node(7, 3);
        mat[2][2] = new Node(1, 4);
        mat[2][3] = new Node(6, 0);

        mat[3][0] = new Node(0, 4);
        mat[3][1] = new Node(0, 6);
        mat[3][2] = new Node(0, 5);
        mat[3][3] = new Node(0, 0);
        return mat;
    }

    private static Node[][] InitMat3() {
        Node[][] mat = new Node[4][4];

//        example3
//                --1----2----3--
//
//        5    4    3    4
//
//                --5----1----2--
//
//        4    3    2    1
//
//                --3----5----5--
//
//        2    4    1    4
//
//                --1----2----3--

        mat[0][0] = new Node(2, 1);
        mat[0][1] = new Node(4, 2);
        mat[0][2] = new Node(1, 3);
        mat[0][3] = new Node(4, 0);

        mat[1][0] = new Node(4, 3);
        mat[1][1] = new Node(3, 5);
        mat[1][2] = new Node(2, 5);
        mat[1][3] = new Node(1, 0);

        mat[2][0] = new Node(5, 5);
        mat[2][1] = new Node(4, 1);
        mat[2][2] = new Node(3, 2);
        mat[2][3] = new Node(4, 0);

        mat[3][0] = new Node(0, 1);
        mat[3][1] = new Node(0, 2);
        mat[3][2] = new Node(0, 3);
        mat[3][3] = new Node(0, 0);

        return mat;
    }

    public static void main(String[] args) {

        Node[][] Mat = InitMat1();
        System.out.println("~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("mat:\n" + Arrays.deepToString(Mat));
        System.out.println("cheapRouteDis:\t" + cheapRouteDis(Mat));
//        do after running the algorithm cheapRouteDis
        System.out.println("ExampleOfPath\t" + exampleOfPath(Mat));
        System.out.println("cheapRouteCount\t" + cheapRouteCount(Mat));
//        do after running the algorithm cheapRouteDis
        System.out.println("allPath\t" + allPath(Mat));
        System.out.println("minimumInquiries\t"+ minimumInquiries(Mat));
//        System.out.println("cheapRouteDisFromPoint\t"+cheapRouteDisFromPoint(Mat,0,2));
//        System.out.println("cheapRouteDisFromPoint\t"+cheapRouteDisFromPoint(Mat,0,3));
//        System.out.println(midpoint(Mat, 0,2));
//        System.out.println(midpoint(Mat, 0,3));
        System.out.println("allMidPoint\t"+allMidPoint(Mat));
        System.out.println("cheapRouteDisFromB\t"+cheapRouteDisFromB(Mat));
        System.out.println("~~~~~~~~~~~~~~~~~~~~~");


        Mat = InitMat2();
        System.out.println("~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("mat:\n" + Arrays.deepToString(Mat));
        System.out.println("cheapRouteDis:\t" + cheapRouteDis(Mat));
        System.out.println("ExampleOfPath\t" + exampleOfPath(Mat));
        System.out.println("cheapRouteCount\t" + cheapRouteCount(Mat));
        System.out.println("allPath\t" + allPath(Mat));
        System.out.println("minimumInquiries\t"+ minimumInquiries(Mat));
        System.out.println("~~~~~~~~~~~~~~~~~~~~~");
        System.out.println();

        Mat = InitMat3();
        System.out.println("~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("mat:\n" + Arrays.deepToString(Mat));
        System.out.println("cheapRouteDis:\t" + cheapRouteDis(Mat));
        System.out.println("ExampleOfPath\t" + exampleOfPath(Mat));
        System.out.println("cheapRouteCount\t" + cheapRouteCount(Mat));
        System.out.println("allPath\t" + allPath(Mat));
        System.out.println("minimumInquiries\t"+ minimumInquiries(Mat));
        System.out.println("~~~~~~~~~~~~~~~~~~~~~");

    }
}
