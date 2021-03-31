package Exams;
import java.util.Arrays;

public class NumbersGame_Circle {

	public static void main(String[] args) {
		int[] arr = {1,3,6,1,3,6};
		System.out.println(numberGameCycle(arr));
	}

	// משחק המספרים של מעגל-
	// כאשר המתחיל יכול לבחור בפעם הראשונה איזה מספר שהוא רוצה
	public static int numberGameCycle(int[] arr) { // O(n^3)
		int number=-1;
		int n = arr.length;
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < n; i++) {
			int a = arr[i]; // הבחירה של השחקן הראשון
			int[] b = new int[n-1]; // הגדרת מערך חדש משאר האיברים
			int k = (i+1) % n;
			for (int j = 0; j < n-1; j++) {
				b[j] = arr[k];
				k = (k+1) % n;
			}
			System.out.println("my choice: "+a);
			int f = numberGame(b);
			if(a-f > max) {
				max = a-f; // האיבר שלקחתי פחות הפינה הימנית העליונה
				number=a;
			}
		}
		System.out.println("the first player should choose the number: "+number);
		return max;
	}

	// משחק המספרים הרגיל!
	public static int numberGame(int[] arr) {
		int n = arr.length;
		int[][] m = new int[n][n];
		for (int i = 0; i < m.length; i++) {
			m[i][i] = arr[i];
		}
		for (int i = n-1; i >= 0; i--) {
			for (int j = i+1; j < n; j++) {
				m[i][j] = Math.max(arr[i] - m[i+1][j], arr[j] - m[i][j-1]);
			}
		}
		for (int i = 0; i < m.length; i++) {
			System.out.println(Arrays.toString(m[i]));
		}
		System.out.println();
		return m[0][n-1];
	}
}
