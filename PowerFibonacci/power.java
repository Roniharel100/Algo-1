package PowerFibonacci;

public class power {
    public static void main(String[] args) {
        System.out.println(powerLoop(3, 4));
        System.out.println(powerRec(3, 4));
    }
    // חישוב באמצעות רקורסיה
    public static int powerRec(int x, int n) {
        if (n == 0)
            return 1;
        if (n % 2 == 0)
            return powerRec(x * x, n / 2);
        return x * powerRec(x * x, (n - 1) / 2);
    }
    // חישוב באמצעות אינדוקציה
    public static int powerLoop(int x, int n) {
        int ans = 1;
        while (n != 0) {
            if (n % 2 != 0)
                ans = ans * x;
            x = x * x;
            n = n / 2;
        }
        return ans;
    }
}
