package AirplaneProblem;

import java.util.LinkedList;
import java.util.Vector;

public class AirPlaneProblems {
    public static void main(String[] args) {

        AirPlaneNode[][] Mat = new AirPlaneNode[4][4];
        InitMat(Mat);//בונה מטריצה
        ShortestPath(Mat);//עלות מסלול קצת ביותר
        PrintPath(Mat);//דוגמה למסלול קצר
        PrintNumberOfPath(Mat);//כמות מסלולים קצרים
        PrintAllPathsRec(Mat);//מדפיס את כל המסלולים הכי קצרים בריקורסיה
        PrintAllPathsInd(Mat);//מדפיס את כל המסלולים הכי קצרים באינדוקציה
        MinimumTurns(Mat);//מסלול הכי קצר מינימום פניות
        IsOnPath(2, 0, Mat);//האם נקודה נמצאת על מסלול הכי קצר
        allNodesOnPaths(Mat);//מחזירה את כל הקודקודים שעל הדרכים הקצרות
    }

    private static void Print(AirPlaneNode[][] mat) {
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                System.out.print(mat[i][j].toString() + "\t");
            }
            System.out.println();
        }
    }

    private static void InitMat(AirPlaneNode[][] mat) {

        mat[0][0] = new AirPlaneNode(5, 4);
        mat[0][1] = new AirPlaneNode(1, 2);
        mat[0][2] = new AirPlaneNode(2, 4);
        mat[0][3] = new AirPlaneNode(3, 0);

        mat[1][0] = new AirPlaneNode(1, 2);
        mat[1][1] = new AirPlaneNode(3, 1);
        mat[1][2] = new AirPlaneNode(3, 2);
        mat[1][3] = new AirPlaneNode(1, 0);

        mat[2][0] = new AirPlaneNode(3, 2);
        mat[2][1] = new AirPlaneNode(7, 3);
        mat[2][2] = new AirPlaneNode(1, 4);
        mat[2][3] = new AirPlaneNode(6, 0);

        mat[3][0] = new AirPlaneNode(0, 4);
        mat[3][1] = new AirPlaneNode(0, 6);
        mat[3][2] = new AirPlaneNode(0, 5);
        mat[3][3] = new AirPlaneNode(0, 0);


    }

    private static void ShortestPath(AirPlaneNode[][] mat) {
        mat[0][0].value = 0;

        for (int j = 1; j < mat[0].length; j++) {
            mat[0][j].value = mat[0][j - 1].value + mat[0][j - 1].right;
            mat[0][j].CountPaths = 1;
        }

        for (int i = 1; i < mat.length; i++) {
            mat[i][0].value = mat[i - 1][0].value + mat[i - 1][0].down;
            mat[i][0].CountPaths = 1;
        }

        for (int i = 1; i < mat.length; i++) {
            for (int j = 1; j < mat[0].length; j++) {
                mat[i][j].value = Math.min((mat[i - 1][j].value + (mat[i - 1][j]).down), (mat[i][j - 1].value + (mat[i][j - 1]).right));
                int right = mat[i][j - 1].value + (mat[i][j - 1]).right;
                int down = mat[i - 1][j].value + mat[i - 1][j].down;
                if (right < down)
                    mat[i][j].CountPaths = mat[i][j - 1].CountPaths;
                else if (right > down)
                    mat[i][j].CountPaths = mat[i - 1][j].CountPaths;
                else
                    mat[i][j].CountPaths = mat[i][j - 1].CountPaths + mat[i - 1][j].CountPaths;

            }

        }
        System.out.println("Min value from (0,0) to (3,3) is " + mat[mat.length - 1][mat[0].length - 1].value);
    }

    private static void PrintPath(AirPlaneNode[][] mat) {
        int i = mat.length - 1;
        int j = mat[0].length - 1;
        String path = " ";
        while (i > 0 && j > 0) {
            if (mat[i][j].value - mat[i - 1][j].down == mat[i - 1][j].value) {
                path = "down " + path;
                i--;
            } else {
                path = "right " + path;
                j--;
            }
        }

        while (i > 0) {
            path = "down " + path;
            i--;

        }

        while (j > 0) {
            path = "right " + path;
            j--;
        }

        System.out.println("Example of path =  " + path);

    }

    private static void PrintNumberOfPath(AirPlaneNode[][] mat) {
        System.out.println("Numbers of paths " + mat[mat.length - 1][mat[0].length - 1].CountPaths);
    }

    private static void PrintAllPathsRec(AirPlaneNode[][] mat) {
        Vector<String> ans = new Vector<String>();
        getAllPathsRec(mat, "", mat.length - 1, mat[0].length - 1, ans);
        System.out.println("All the paths in rec are:" + ans.toString());
    }

    private static void getAllPathsRec(AirPlaneNode[][] mat, String temp, int i, int j, Vector<String> ans) {
        if (i == 0 && j == 0) {
            ans.add(temp);
            return;
        } else if (i > 0 && j == 0) {
            getAllPathsRec(mat, "down " + temp, i - 1, 0, ans);
        } else if (i == 0 && j > 0) {
            getAllPathsRec(mat, "right " + temp, 0, j - 1, ans);
        } else {
            int a = mat[i - 1][j].value + mat[i - 1][j].down;
            int b = mat[i][j - 1].value + mat[i][j - 1].right;
            if (a < b) {
                getAllPathsRec(mat, "down " + temp, i - 1, j, ans);
            } else if (b < a) {
                getAllPathsRec(mat, "right " + temp, i, j - 1, ans);
            } else {
                getAllPathsRec(mat, "down " + temp, i - 1, j, ans);
                getAllPathsRec(mat, "right " + temp, i, j - 1, ans);
            }
        }
    }

    private static void PrintAllPathsInd(AirPlaneNode[][] mat) {

        mat[0][0].paths.add("");

        for (int j = 1; j < mat[0].length; j++) {
            mat[0][j].paths.add(mat[0][j - 1].paths.getFirst() + "r");
        }

        for (int i = 1; i < mat.length; i++) {
            mat[i][0].paths.add(mat[i - 1][0].paths.getFirst() + "d");
        }
        for (int i = 1; i < mat.length; i++) {
            for (int j = 1; j < mat[0].length; j++) {
                int right = mat[i][j - 1].value + (mat[i][j - 1]).right;
                int down = mat[i - 1][j].value + mat[i - 1][j].down;
                if (right < down) {
                    mat[i][j].paths.addAll(mat[i][j - 1].paths);
                    for (int t = 0; t < mat[i][j].paths.size(); t++) {
                        mat[i][j].paths.set(t, mat[i][j].paths.get(t) + "r");
                    }
                } else if (right > down) {
                    mat[i][j].paths.addAll(mat[i - 1][j].paths);
                    for (int t = 0; t < mat[i][j].paths.size(); t++) {
                        mat[i][j].paths.set(t, mat[i][j].paths.get(t) + "d");
                    }

                } else {
                    mat[i][j].paths.addAll(mat[i][j - 1].paths);
                    for (int t = 0; t < mat[i][j].paths.size(); t++) {
                        mat[i][j].paths.set(t, mat[i][j].paths.get(t) + "r");
                    }
                    int n = mat[i][j].paths.size();
                    mat[i][j].paths.addAll(mat[i - 1][j].paths);
                    for (int t = n; t < mat[i][j].paths.size(); t++) {
                        mat[i][j].paths.set(t, mat[i][j].paths.get(t) + "d");
                    }
                }
            }
        }
        System.out.println("All the paths in ind are: " + mat[mat.length - 1][mat[0].length - 1].paths.toString());
    }

    private static void MinimumTurns(AirPlaneNode[][] mat) {
        int MinTurns = Integer.MAX_VALUE;
        String MinTurnsPath = " ";
        int tempCount = 0;
        LinkedList<String> paths = mat[mat.length - 1][mat[0].length - 1].paths;
        for (int t = 0; t < paths.size(); t++) {
            tempCount = 0;
            for (int i = 0; i < paths.get(t).length() - 1; i++) {
                if (paths.get(t).charAt(i) != paths.get(t).charAt(i + 1))
                    tempCount++;
            }
            if (MinTurns > tempCount) {
                MinTurns = tempCount;
                MinTurnsPath = paths.get(t);
            }
        }
        System.out.println("The minimum turns path is " + MinTurnsPath + " with " + MinTurns + " terns");
    }

    private static void IsOnPath(int i, int j, AirPlaneNode[][] mat) {

        int fullPath = ShortestPathBetween(0, 0, mat.length - 1, mat[0].length - 1, mat);
        int fromStartToPoint = ShortestPathBetween(0, 0, i, j, mat);
        int fromPointToEnd = ShortestPathBetween(i, j, mat.length - 1, mat[0].length - 1, mat);
        if (fullPath == fromStartToPoint + fromPointToEnd)
            System.out.println("The point (" + i + "," + j + "), is on path");
        else
            System.out.println("The point (" + i + "," + j + "), is not on path");

    }

    private static int ShortestPathBetween(int i1, int j1, int i2, int j2, AirPlaneNode[][] mat) {
        AirPlaneNode[][] temp = new AirPlaneNode[j2 - j1 + 1][i2 - i1 + 1];
        for (int i = 0; i < temp.length; i++) {
            for (int j = 0; j < temp[0].length; j++) {
                temp[i][j] = new AirPlaneNode(0, 0);
            }
        }
        for (int i = 1; i < j2 - j1 + 1; i++) {
            temp[i][0].value = temp[i - 1][0].value + mat[i - 1 + j1][i1].down;
        }
        for (int j = 1; j < i2 - i1 + 1; j++) {
            temp[0][j].value = temp[0][j - 1].value + mat[j1][j - 1 + i1].right;
        }
        for (int i = 1; i < j2 - j1 + 1; i++) {
            for (int j = 1; j < i2 - i1 + 1; j++) {
                int down = temp[i - 1][j].value + mat[i - 1 + j1][j + i1].down;
                int right = temp[i][j - 1].value + mat[i + j1][j - 1 + i1].right;
                temp[i][j].value = Math.min(down, right);
            }
        }
        return temp[j2 - j1][i2 - i1].value;
    }

    private static void buildMatrixFromTheEnd(AirPlaneNode[][] mat) {
        int n = mat.length - 1, m = mat[0].length - 1;
        for (int i = n - 1; i >= 0; i--)
            mat[i][m].value_end = mat[i + 1][m].value_end + mat[i][m].down;
        for (int j = m - 1; j >= 0; j--)
            mat[n][j].value_end = mat[n][j + 1].value_end + mat[n][j].right;

        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                mat[i][j].value_end = Math.min(mat[i + 1][j].value_end + mat[i][j].down, mat[i][j + 1].value_end + mat[i][j].right);
            }
        }
   }

    private static void allNodesOnPaths(AirPlaneNode[][] mat) {
        buildMatrixFromTheEnd(mat);
        Print(mat);
        int sumA = mat[mat.length - 1][mat[0].length - 1].value;
        String points = "";
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                if (mat[i][j].value + mat[i][j].value_end == sumA)
                    points = points + " (" + i + "," + j + ")";
            }
        }
        System.out.println("The nodes that are on the paths" + points);
    }


}



