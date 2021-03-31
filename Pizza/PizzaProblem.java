package Pizza;

public class PizzaProblem {
    /**
     * returns the optimal division for the faster man
     * Complexity: O(1)
     */
    public static int MyPizza(double x, int n) {
        int k = (int) x + 1;
        int p = n / (k + 1);
        int r = n % (k + 1);
        int ans = -1;
        if (r != 1) {
            double t = (x * p + r - 1) / ((x + 1) * p + r);
            if (t < x / (x + 1)) {
                ans = 1;
            } else {
                ans = 0;
            }
        }
        return ans;
    }

    public static int getNumberOfPieces(double k) {
        if(k == (int)k) return (int)k+1;
        return (int)k+2;
    }

    public static void main(String[] args) {
        int number=2;
        System.out.println("The pizza should be devised to "+getNumberOfPieces(number)+" pieces");
    }
}
