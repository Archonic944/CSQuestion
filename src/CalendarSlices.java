import utils.IntPair;
import utils.MiscUtils;
import utils.Pair;

import java.util.*;

public class CalendarSlices {
    HashMap<Integer, List<Character>> organizedTimes;
    IntPair[] timesRaw;
    int[] overlappingGraph;
    int[] overlapping;
    int[] positions;

    public CalendarSlices(IntPair[] times) {
        this.organizedTimes = new HashMap<>();
        this.timesRaw = times;
        for (IntPair time : times) {
            List<Character> listX = organizedTimes.getOrDefault(time.key - 1, new ArrayList<>());
            listX.add('x');
            organizedTimes.putIfAbsent(time.key - 1, listX);
            List<Character> listY = organizedTimes.getOrDefault(time.val - 1, new ArrayList<>());
            listY.add('y');
            organizedTimes.putIfAbsent(time.val - 1, listY);
        }
        Arrays.sort(times, Comparator.comparingInt((obj) -> obj.key));
        overlappingGraph = new int[times[times.length - 1].val];
        int count = 0;
        for (int i = 0; i < overlappingGraph.length; i++) {
            if(organizedTimes.containsKey(i)){
                for(char c : organizedTimes.get(i)) {
                    if (c == 'x') {
                        count++;
                    } else {
                        count--;
                    }
                }
            }
            overlappingGraph[i] = count;
        }
        overlapping = new int[times.length];
        Arrays.fill(overlapping, -1);
    }

    public static void main(String[] args) {
        IntPair[][] inputs = new IntPair[][]{
                {new IntPair(1, 5), new IntPair(2,7), new IntPair(4,9), new IntPair(5,6), new IntPair(6,7), new IntPair(8,10),new IntPair(10,12)},
                {new IntPair(1, 3), new IntPair(3, 5), new IntPair(5, 7)},
                {new IntPair(1, 4), new IntPair(2, 5), new IntPair(3, 6)}
        };

        for (IntPair[] input : inputs) {
            System.out.println("Input: " + Arrays.toString(input));
            CalendarSlices slices = new CalendarSlices(input);
            slices.solve();
        }
    }

    boolean overlaps(IntPair e1, IntPair e2){
        return (e1.key >= e2.key && e1.key < e2.val) || (e1.val <= e2.val && e1.val > e2.key);
    }

    int getOverlapping(int index){
        int cached = overlapping[index];
        if(cached == -1) {
            IntPair pair = timesRaw[index];
            int max = 0;
            for (int i = pair.key; i < pair.val; i++) {
                int here = overlappingGraph[i];
                if (here > max) max = here;
            }
            cached = overlapping[index] = max;
        }
        return cached;
    }

    HashSet<Integer> getOverlappingIndices(int index){
        final HashSet<Integer> indices = new HashSet<>();
        for (int i = 0; i < timesRaw.length; i++) {
            if(i == index) continue;
            IntPair pair = timesRaw[i];
            if(overlaps(pair, timesRaw[index])){
                indices.add(i);
            }
        }
        return indices;
    }
    HashSet<Integer> overlappingByProxy(int index) {
        HashSet<Integer> overlappingIndices = new HashSet<>();
        Stack<Integer> toCheck = new Stack<>();
        toCheck.push(index);
        while (!toCheck.empty()) {
            int proxy = toCheck.pop();
            HashSet<Integer> overlappingWithCurrent = getOverlappingIndices(proxy);
            for(int eventIndex : overlappingWithCurrent){
                if(eventIndex != index && overlappingIndices.add(eventIndex)) toCheck.push(eventIndex);
            }
        }
        return overlappingIndices;
    }

    public int minSlices(int index){
        HashSet<Integer> oL = overlappingByProxy(index);
        int max = getOverlapping(index);
        for(int i : oL){
            int count = getOverlapping(i);
            max = Math.max(count, max);
        }
        return max;
    }

    public void solve(){
        int[] positions = new int[timesRaw.length];
        Arrays.fill(positions, -1);
        for (int i = 0; i < timesRaw.length; i++) {
            int min = minSlices(i);
            HashSet<Integer> overlappingWithThis = getOverlappingIndices(i);
            int position = -1;
            pos: for (int j = 0; j < min; j++) {
                for(int index : overlappingWithThis){
                    if(positions[index] == j){
                        continue pos;
                    }
                }
                position = j;
                break;
            }
            positions[i] = position;
            assert(position != -1);
            System.out.println(position + "//" + min);
        }
    }
}