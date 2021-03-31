package DoughnutsProblem;

public class doughnuts {
    private static final int time = 2;

    /**
     * returns the total time for the daunts
     * Complexity: O(1)
     */

    public static void main(String[] args) {
        int number_of_donuts = 59;
        int capacity = 5;
        System.out.println("the time that will take to fry the donuts is " + getTime(number_of_donuts, capacity) + " minutes");
    }

    public static int getTime(int numOfDonuts, int capacity) {
        if (capacity >= numOfDonuts)
            return time;
        if ((time * numOfDonuts) % capacity == 0)
            return (time * numOfDonuts) / capacity;
        return (time * numOfDonuts) / capacity + 1;
    }
}
