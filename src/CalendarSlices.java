import utils.IntPair;
import utils.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Stack;

public class CalendarSlices {
    IntPair[] times;
    HashSet<Integer>[] overlapping; //cache for sets of overlapping slices
    int[] positions;

    public CalendarSlices(IntPair[] times) {
        this.times = times;
        overlapping = new HashSet[times.length];
        positions = new int[times.length];
    }

    public static void main(String[] args) {
        CalendarSlices slices = new CalendarSlices(new IntPair[]{new IntPair(1, 5), new IntPair(4,9), new IntPair(8,10),new IntPair(10,12), new IntPair(2,7)});
        slices.solve();
    }

    boolean overlaps(IntPair e1, IntPair e2){
        return (e1.key >= e2.key && e1.key < e2.val) || (e1.val <= e2.val && e1.val > e2.key);
    }

    /**
     *
     * @return Indexes of the events that the event is adjacent to
     */
    HashSet<Integer> getOverlapping(int index){
        if(overlapping[index] == null) {
            HashSet<Integer> indices = new HashSet<>();
            IntPair ev = times[index];
            for (int i = 0; i < times.length; i++) {
                if (i != index && overlaps(ev, times[i])) {
                    indices.add(i);
                }
            }
            overlapping[index] = indices;
            return indices;
        }else{
            return overlapping[index];
        }
    }

    HashSet<Integer> overlappingByProxy(int index){
        HashSet<Integer> overlappingIndices = new HashSet<>();
        Stack<Integer> toCheck = new Stack<>();
        toCheck.push(index);
        while (!toCheck.empty()) {
            int proxy = toCheck.pop();
            HashSet<Integer> overlappingWithCurrent = getOverlapping(proxy);
            for(int eventIndex : overlappingWithCurrent){
                if(eventIndex != index && overlappingIndices.add(eventIndex)) toCheck.push(eventIndex);
            }
        }
        return overlappingIndices;
    }

    public int minSlices(int index){
        HashSet<Integer> oL = overlappingByProxy(index);
        int max = oL.size();
        for(int i : oL){
            if(overlappingByProxy(i).size() > max) max = oL.size();
        }
        return max;
    }

    /**
     * Calculates the minimum amount of slices needed for each event, and assigns a position to each.
     */
    public void solve(){
        int[] positions = new int[times.length];
        Arrays.fill(positions, -1);
        for (int i = 0; i < times.length; i++) {
            int min = minSlices(i); // the second part of the output
            HashSet<Integer> overlappingWithThis = getOverlapping(i);
            //so many for loops because i don't want to organize them into "cohorts" of references to overlapping cells
            int position = min == 0 ? 0 : -1;
            pos: for (int j = 0; j < min + 1; j++) { //this loop goes through each possible position
                System.out.println("checking " + j);
                //check if that position is taken
                for(int index : overlappingWithThis){
                    if(positions[index] == j){
                        System.out.println(j + " is already used by index " + index);
                        continue pos;
                    }
                }
                position = j;
                break;
            }
            positions[i] = position;
            assert(position != -1);
            System.out.println(position + "//" + (min + 1));
        }
    }
}
