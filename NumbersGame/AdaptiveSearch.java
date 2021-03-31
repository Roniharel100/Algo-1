package NumbersGame;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/** השיטה האדפטיבית-
 * בכל שלב נחשב את הסכום של הזוגי/אי זוגי הקיימים ונבחר במספר המשתלם לנו
 */
public class AdaptiveSearch {
    public static void main(String[] args) {
        int [] arr = buildArr();
        runGame(arr);
    }

    private static int[] buildArr() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a even number"); // הכנס את גודל המערך
        int n = sc.nextInt();
        while(n%2==1){
            n = sc.nextInt();
        }
        int[] arr = new int[n];
        Random rd = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = rd.nextInt(20);
        }
        return arr;
    }

    // חישוב סכום האיברים במקומות הזוגיים והאי זוגיים
    public static boolean MaxSumEven(int[] arr) {
        int sum_even = 0, sum_odd = 0;
        for (int i = 0; i < arr.length; i++) {
            if (i % 2 == 0) sum_even += arr[i];
            if (i % 2 == 1) sum_odd += arr[i];
        }
        return (sum_even >= sum_odd);
    }

    private static int[] resizeArr(int[] arr, int index) {
        int[] new_arr = new int[arr.length - 1];
        for (int i = 0; i < new_arr.length; i++) {
            if (index == 0) {
                new_arr[i] = arr[i + 1];
            } else new_arr[i] = arr[i];
        }
        return new_arr;
    }

    private static void runGame(int[] arr) {
        System.out.println("\nWe are going to play a game with this array: \n" + Arrays.toString(arr));
        System.out.println("\nYour goal is to accumulate the maximum amount");
        System.out.println("\nYour name:");
        Scanner sc = new Scanner(System.in);
        String name = sc.next();
        System.out.println("\n\tI'm playing first");

        int sum_comp = 0, sum_gamer = 0;
        while (arr.length > 1) {
            int index = 0;
            if (!(MaxSumEven(arr))) index = arr.length - 1;
            System.out.print("\n\tmy choice: " + arr[index]);
            sum_comp += arr[index];
            System.out.println(" my sum: " + sum_comp);
            int[] new_arr = resizeArr(arr, index);
            System.out.println("\nThe current array is :" + Arrays.toString(new_arr));
            System.out.println("\nSelect " + new_arr[0] + " or " + new_arr[new_arr.length - 1]);
            int at_arr = sc.nextInt();
            while((at_arr!=new_arr[0])&&(at_arr!=new_arr[new_arr.length-1])){
                at_arr = sc.nextInt();
            }
            if(new_arr[0]==at_arr) index=0;
            if(new_arr[new_arr.length-1]==at_arr) index=new_arr.length-1;
            System.out.print("\n\t" + name + " choice:" + new_arr[index]);
            sum_gamer += at_arr;
            System.out.println(" "+name+ " sum: " + sum_gamer);
            arr = resizeArr(new_arr, index);
            if(arr.length!=0)
                System.out.println("\nThe current array is :" + Arrays.toString(arr));
        }
        String win= "computer";
        if(sum_comp<sum_gamer) win=name;
        System.out.println("\n\tmy sum: " + sum_comp +",\t"+name+ " sum: " + sum_gamer +"\n\tThe win: "+win);
    }
}
