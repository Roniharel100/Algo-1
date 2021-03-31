package BiggerThanMedian;

import java.util.Arrays;

public class bigger_than_median {
    public static void main(String[] args) {
        System.out.println(getMistakeProb(10000,2,10000));
    }

    // בעיה 1-
    //בהינתן מערך לא ממוין של מספרים אקראיים, יש למצוא את האיבר הגדול מחציון.
    public static int medianSort(int []arr){
        Arrays.sort(arr);
        int Emtza = arr.length/2;
        return arr[Emtza];
    }
    public static int median64(int arr[]) {
        int max = arr[0];
        for (int i=1; i<64; i++) { // ראשונים איברים 46 מתוך הגדול האיבר מציאת
            if (arr[i]>max) max = arr[i];
        }
        return max;
    }

    public static void twoArr(int n, int a[], int b[]){
        int i=n;
        int j=n;
        int k=n;
        int[] c = new int[n];
        while (k>=0) { //O(n)
            if (a[i] >= b[j])
                c[k--] = a[i--];
            else
                c[k--] = b[j--];
        }
    }

    /**
     * checks the probability to make a mistake if we checking check elements in the array with arr_size
     */
    public static double getMistakeProb(int arr_size,int check,int count) {
        int incorrect = 0;
        int[] arr = new int[arr_size];
        for (int i = 0; i < count; i++) {
            for (int j = 0; j < arr.length; j++) {
                arr[j] = (int)(Math.random()*count*10);
                System.out.println(arr[j]);
            }
            int x = getBiggerThanMedian(arr,check);
            Arrays.sort(arr);
            if(x < arr[arr.length/2]) incorrect++;
        }
        return (double)incorrect/count;
    }

    /**
     * return one element in the array that bigger than median
     * Complexity: O(check)
     */
    public static int getBiggerThanMedian(int[] arr,int check) {
        int max = arr[0];
        for (int i = 1; i < check; i++) {
            if(max < arr[i]) {
                max = arr[i];
            }
        }
        return max;
    }
}

