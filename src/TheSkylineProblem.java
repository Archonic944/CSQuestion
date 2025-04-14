import java.util.*;

public class TheSkylineProblem {
    public List<List<Integer>> getSkyline(int[][] buildings) {
        //translate to sorted array of BuildingPoints
        BuildingPoint[] points = toBuildingPoints(buildings);
        PriorityQueue<Integer> heights = new PriorityQueue<>(10, Collections.reverseOrder());
        ArrayList<List<Integer>> criticalPoints = new ArrayList<>();
        heights.add(0);
        int lastX = -1;
        int lastHeight = -1; //the last height added to our critical points
        for(BuildingPoint point : points){
            if(lastX != point.x && lastX != -1){ //we are moving forward, and need to consider critical points from the last x
                int currentHeight = heights.peek();
                if(currentHeight != lastHeight) criticalPoints.add(new PairList(lastX, currentHeight));
                lastHeight = currentHeight;
            }
            if(point.isStart) heights.add(point.height);
            else heights.remove(point.height);
            lastX = point.x;
        }
        criticalPoints.add(new PairList(lastX, 0));
        return criticalPoints;
    }

    static BuildingPoint[] toBuildingPoints(int[][] buildings){
        BuildingPoint[] points = new BuildingPoint[buildings.length * 2];
        for (int i = 0, buildingsLength = buildings.length; i < buildingsLength; i++) {
            int[] building = buildings[i];
            points[i * 2] = new BuildingPoint(building[0], building[2], true);
            points[i * 2 + 1] = new BuildingPoint(building[1], building[2], false);
        }
        Arrays.sort(points, Comparator.comparingInt((o) -> ((BuildingPoint) o).x).thenComparing((o) -> ((BuildingPoint) o).isStart ? -1 : 0));
        return points;
    }

    static class BuildingPoint {
        int x;
        int height;
        boolean isStart; //true for start, false for end

        public BuildingPoint(int x, int height, boolean isStart){
            this.x = x;
            this.height = height;
            this.isStart = isStart;
        }

        @Override
        public String toString() {
            return "BuildingPoint{" +
                    "x=" + x +
                    ", height=" + height +
                    ", isStart=" + isStart +
                    '}';
        }
    }

    /**
     * Integer list with 2 elements that only supports get() and size() (for efficient leetcode output)
     */
    static class PairList implements List<Integer> {
        int val1;
        int val2;

        public PairList(int val1, int val2) {
            this.val1 = val1;
            this.val2 = val2;
        }

        @Override
        public int size() {
            return 2;
        }

        @Override
        public boolean isEmpty() {
            return false;  // Always has two elements
        }

        @Override
        public boolean contains(Object o) {
            if (o instanceof Integer) {
                return val1 == (Integer) o || val2 == (Integer) o;
            }
            return false;
        }

        @Override
        public Iterator<Integer> iterator() {
            return Arrays.asList(val1, val2).iterator();
        }

        @Override
        public Object[] toArray() {
            return new Integer[]{val1, val2};
        }

        @Override
        public <T> T[] toArray(T[] a) {
            if (a.length < 2) {
                return (T[]) Arrays.copyOf(new Integer[]{val1, val2}, 2, a.getClass());
            } else {
                a[0] = (T) Integer.valueOf(val1);
                a[1] = (T) Integer.valueOf(val2);
                if (a.length > 2) {
                    a[2] = null;  // As per the List interface contract
                }
                return a;
            }
        }

        @Override
        public boolean add(Integer integer) {
            throw new UnsupportedOperationException("PairList is immutable");
        }

        @Override
        public boolean remove(Object o) {
            throw new UnsupportedOperationException("PairList is immutable");
        }

        @Override
        public boolean containsAll(Collection<?> c) {
            for (Object o : c) {
                if (!contains(o)) {
                    return false;
                }
            }
            return true;
        }

        @Override
        public boolean addAll(Collection<? extends Integer> c) {
            throw new UnsupportedOperationException("PairList is immutable");
        }

        @Override
        public boolean addAll(int index, Collection<? extends Integer> c) {
            throw new UnsupportedOperationException("PairList is immutable");
        }

        @Override
        public boolean removeAll(Collection<?> c) {
            throw new UnsupportedOperationException("PairList is immutable");
        }

        @Override
        public boolean retainAll(Collection<?> c) {
            throw new UnsupportedOperationException("PairList is immutable");
        }

        @Override
        public void clear() {
            throw new UnsupportedOperationException("PairList is immutable");
        }

        @Override
        public Integer get(int index) {
            if (index == 0) return val1;
            else if (index == 1) return val2;
            else throw new IndexOutOfBoundsException("Index must be 0 or 1");
        }

        @Override
        public Integer set(int index, Integer element) {
            throw new UnsupportedOperationException("PairList is immutable");
        }

        @Override
        public void add(int index, Integer element) {
            throw new UnsupportedOperationException("PairList is immutable");
        }

        @Override
        public Integer remove(int index) {
            throw new UnsupportedOperationException("PairList is immutable");
        }

        @Override
        public int indexOf(Object o) {
            if (o instanceof Integer) {
                if (val1 == (Integer) o) return 0;
                if (val2 == (Integer) o) return 1;
            }
            return -1;
        }

        @Override
        public int lastIndexOf(Object o) {
            return indexOf(o);  // Since there are only two elements, this is the same as indexOf
        }

        @Override
        public ListIterator<Integer> listIterator() {
            return Arrays.asList(val1, val2).listIterator();
        }

        @Override
        public ListIterator<Integer> listIterator(int index) {
            return Arrays.asList(val1, val2).listIterator(index);
        }

        @Override
        public List<Integer> subList(int fromIndex, int toIndex) {
            if (fromIndex < 0 || toIndex > 2 || fromIndex > toIndex) {
                throw new IndexOutOfBoundsException("Invalid subList range");
            }
            if (fromIndex == 0 && toIndex == 1) {
                return Collections.singletonList(val1);
            } else if (fromIndex == 1 && toIndex == 2) {
                return Collections.singletonList(val2);
            } else if (fromIndex == 0 && toIndex == 2) {
                return Arrays.asList(val1, val2);
            } else {
                return Collections.emptyList();
            }
        }

        //toString
        @Override
        public String toString() {
            return "[" + val1 + ", " + val2 + "]";
        }
    }

    public static void main(String[] args) {
        TheSkylineProblem skylineProblem = new TheSkylineProblem();
        int[][] buildings = {
                {2, 9, 5},
                {2, 7, 10},
                {7, 8, 8},
                {7, 8, 9},
                {3, 11, 4},
                {9, 10, 6}
        };
        List<List<Integer>> result = skylineProblem.getSkyline(buildings);
        //space + line separated result
        for (List<Integer> point : result) {
            System.out.println(point.get(0) + " " + point.get(1));
        }
    }
}
