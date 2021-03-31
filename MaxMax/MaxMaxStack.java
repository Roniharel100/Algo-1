package MaxMax;
import java.util.Arrays;
import java.util.Stack;

public class MaxMaxStack {

    static class MaxNode {
        Stack<Integer> st;
        int data;

        MaxNode(int data) {
            this.st = new Stack<>();
            this.data = data;
        }
    }

    static MaxNode[] toArrayNode(int[] arr) {
        MaxNode[] newArr = new MaxNode[arr.length];
        for (int i = 0; i < arr.length; i++) {
            newArr[i] = new MaxNode(arr[i]);
        }
        return newArr;
    }

    /**
     * הפונק מקבלת מערך של node אינדקס התחלה ואינדקס סיום של הקטע
     */
    private static int max1(MaxNode[] arr, int start, int end) {
        if (start < end) {
            int index = 0;
            int middle = (start + end) / 2;
            // נבצע רקורסיה באופן הבא:
            int i = max1(arr, start, middle);
            int j = max1(arr, middle + 1, end);
            if (arr[i].data > arr[j].data) {
                arr[i].st.push(arr[j].data);
                index = i;
            } else {
                arr[j].st.push(arr[i].data);
                index = j;
            }
            return index;
        }
        else return start;
    }

    /**
     *מקבל מערך של node בגודל המערך המקורי
     * בכל node יהיה הערך במערך הקורי ומחסנית
     */
    static int[] maxMax(int[] arr) {
        MaxNode[] newArr = toArrayNode(arr);
      // נקרא לפונק max1 ונקבל ממנה את המיקום של האיבר המקסימלי במערך
        int index_max1 = max1(newArr, 0, newArr.length - 1);
        int max1 = arr[index_max1];
        int max2 = newArr[index_max1].st.pop();
        while (!newArr[index_max1].st.isEmpty()) {
            int temp = newArr[index_max1].st.pop();
            if (temp > max2) max2 = temp;
        }
        int[] ans = {max1, max2};
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {2, 3, 1, 2, 32, 3, 45, 34, 78, 98, 7};
        System.out.println(Arrays.toString(maxMax(arr)));
    }
}
