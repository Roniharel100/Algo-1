package AirplaneProblem;

import java.util.LinkedList;

public class AirPlaneNode {
        int down;
        int right;
        int value;
        int value_end;
        int CountPaths;
        LinkedList<String> paths;


        AirPlaneNode (int d, int r)
        {
            down = d;
            right = r;
            paths= new LinkedList<>();
        }

    @Override
    public String toString() {
        return "{" +
                "down=" + down +
                ", right=" + right +
                ", value=" + value +
                ", value_end=" + value_end +
                ", CountPaths=" + CountPaths +
                '}';
    }
}
