package Exams;

/**
 * כתבו אלגוריתם המקבל מטריצה של אפסים ואחדות ומוצא את הסימן "+" של אחדות הגדול ביותר.
 * כאשר מספר האחדות בתוך ה "+" זהה לכל הכיוונים (סימטרי).
 */


public class OnesMatrix_Plus {
	public static int largestPlus(int[][] mat) {
		int n = mat.length;
		int m = mat[0].length;
		int[][] u = new int[n][m];
		int[][] d = new int[n][m];
		int[][] l = new int[n][m];
		int[][] r = new int[n][m];
		for (int i = 0; i < m; i++) {u[0][i] = mat[0][i];}
		for (int i = 0; i < m; i++) {d[n-1][i] = mat[n-1][i];}
		for (int i = 0; i < n; i++) {l[i][0] = mat[i][0];}
		for (int i = 0; i < n; i++) {r[i][m-1] = mat[i][m-1];}
		int max = 0;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if(mat[i][j] != 0) {
					if(i != 0) {u[i][j] = u[i-1][j] + 1;}
					if(j != 0) {l[i][j] = l[i][j-1] + 1;}
				}
			}
		}
		for (int i = n-1; i >= 0; i--) {
			for (int j = m-1; j >= 0; j--) {
				if(mat[i][j] != 0) {
					if(i != n-1) {d[i][j] = d[i+1][j] + 1;}
					if(j != m-1) {r[i][j] = r[i][j+1] + 1;}
				}
			}
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if(Math.min(Math.min(u[i][j], d[i][j]),Math.min(l[i][j], r[i][j])) > max) {
					max = Math.min(Math.min(u[i][j], d[i][j]),Math.min(l[i][j], r[i][j]));
				}
			}
		}
		return (max-1)*4 + 1;
	}

	public static void main(String[] args) {
		int[][] mat = {
				{1,0,1,1,1,1,0,1,1,1},
				{1,0,1,0,1,1,1,0,1,1},
				{1,1,1,0,1,1,0,1,0,1},
				{0,0,0,0,1,0,0,1,0,0},
				{1,0,0,0,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,0},
				{1,0,0,0,1,0,0,1,0,1},
				{1,0,1,1,1,1,0,0,1,1},
				{1,1,0,0,1,0,0,0,0,1},
				{1,0,1,1,1,1,0,1,0,0}
		};
		System.out.println(largestPlus(mat));
	}
}

	public static int imaginaryPlus(int[][] arr) {
		int n = arr.length;
		int m = arr[0].length;
		int[][] mat1 = new int[n][m];
		int[][] mat2 = new int[n][m];
		int[][] mat3 = new int[n][m];
		int[][] mat4 = new int[n][m];
		int sum = 0;

		for (int i = 0; i < m; i++) mat1[0][i] = arr[0][i];

		for (int i = 0; i < m; i++) mat2[n - 1][i] = arr[n - 1][i];

		for (int i = 0; i < n; i++) mat3[i][0] = arr[i][0];

		for (int i = 0; i < n; i++) mat4[i][m - 1] = arr[i][m - 1];

		int max = 0;

		for (int i = n - 1; i >= 0; i--) {
			for (int j = m - 1; j >= 0; j--) {
				if (arr[i][j] != 0) {
					if (i != n - 1) {
						mat2[i][j] = mat2[i + 1][j] + 1;
					}
					if (j != m - 1) {
						mat4[i][j] = mat4[i][j + 1] + 1;
					}
				}
			}
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (arr[i][j] != 0) {
					if (i != 0) {
						mat1[i][j] = mat1[i - 1][j] + 1;
					}
					if (j != 0) {
						mat3[i][j] = mat3[i][j - 1] + 1;
					}
				}
			}
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (arr[i][j] == 1)
					sum = mat1[i][j] + mat2[i][j] + mat3[i][j] + mat4[i][j] - 3;
				else sum=0;
				if (sum > max) {
					max = sum;
				}
			}
		}
		return max;
	}
}
