import utils.IntPair;
import utils.MiscUtils;
import utils.Pair;

import java.util.*;


public class CalendarSlices {
    HashMap<Integer, List<Character>> organizedTimes; //may or may not have taken inspiration from GFG
    IntPair[] timesRaw;
    int[] overlappingGraph; //slice amt over time
    int[] positions;

    public CalendarSlices(IntPair[] times) {
        //convert to (start|end, 'x'|'y') pairs
        this.organizedTimes = new HashMap<>();
        this.timesRaw = times;
        for (IntPair time : times) {
            List<Character> listX = organizedTimes.getOrDefault(time.key - 1, new ArrayList<>()); //converts to 0 based
            listX.add('x');
            organizedTimes.putIfAbsent(time.key, listX);
            List<Character> listY = organizedTimes.getOrDefault(time.val, new ArrayList<>());
            listY.add('y');
            organizedTimes.putIfAbsent(time.val, listY);
        }
        //sort
        Arrays.sort(times, Comparator.comparingInt((obj) -> obj.key));
        overlappingGraph = new int[times[times.length - 1].val]; //screw it
        int count = 0;
        for (int i = 0; i < overlappingGraph.length; i++) {
            System.out.println("\nindex: " + i);
            //is there a value here?
            if(organizedTimes.containsKey(i)){
                for(char c : organizedTimes.get(i)) {
                    if (c == 'x') {
                        System.out.println("found x");
                        count++;
                    } else {
                        System.out.println("found y");
                        count--;
                    }
                    System.out.println(organizedTimes.get(i));
                    System.out.println("count is now " + count);
                }
            }
            overlappingGraph[i] = count;
            //MiscUtils.safeSleep(500);
        }
        System.out.println(Arrays.toString(overlappingGraph));
        positions = new int[times.length];
    }

    public static void main(String[] args) {
        CalendarSlices slices = new CalendarSlices(new IntPair[]{new IntPair(1, 5), new IntPair(4,9), new IntPair(8,10),new IntPair(10,12), new IntPair(2,7), new IntPair(5,6), new IntPair(6,7)});
    }

    boolean overlaps(IntPair e1, IntPair e2){
        return (e1.key >= e2.key && e1.key < e2.val) || (e1.val <= e2.val && e1.val > e2.key);
    }

    /**
     *
     * @return Maximum overlap with this event
     */
    int getOverlapping(int index){
        IntPair pair = timesRaw[index];
        int max = 0;
        for(int i = pair.key; i<pair.val; i++){
            int here = overlappingGraph[i];
            if(here > max) max = here;
        }
        return max;
    }

//    HashSet<Integer> overlappingByProxy(int index){
//        HashSet<Integer> overlappingIndices = new HashSet<>();
//        Stack<Integer> toCheck = new Stack<>();
//        toCheck.push(index);
//        while (!toCheck.empty()) {
//            int proxy = toCheck.pop();
//            HashSet<Integer> overlappingWithCurrent = getOverlapping(proxy);
//            for(int eventIndex : overlappingWithCurrent){
//                if(eventIndex != index && overlappingIndices.add(eventIndex)) toCheck.push(eventIndex);
//            }
//        }
//        return overlappingIndices;
//    }

//    public int minSlices(int index){
//        HashSet<Integer> oL = overlappingByProxy(index);
//        int max = getOverlapping(index).size();
//        for(int i : oL){
//            if(getOverlapping(i).size() > max) max = oL.size();
//        }
//        return max;
//    }

    /**
     * Calculates the minimum amount of slices needed for each event, and assigns a position to each.
     */
//    public void solve(){
//        int[] positions = new int[organizedTimes.length];
//        Arrays.fill(positions, -1);
//        for (int i = 0; i < organizedTimes.length; i++) {
//            int min = minSlices(i); // the second part of the output
//            HashSet<Integer> overlappingWithThis = getOverlapping(i);
//            //so many for loops because i don't want to organize them into "cohorts" of references to overlapping cells
//            int position = min == 0 ? 0 : -1;
//            pos: for (int j = 0; j < min + 1; j++) { //this loop goes through each possible position
//                System.out.println("checking " + j);
//                //check if that position is taken
//                for(int index : overlappingWithThis){
//                    if(positions[index] == j){
//                        System.out.println(j + " is already used by index " + index);
//                        continue pos;
//                    }
//                }
//                position = j;
//                break;
//            }
//            positions[i] = position;
//            assert(position != -1);
//            System.out.println(position + "//" + (min + 1));
//        }
//    }
}
