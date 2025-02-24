import utils.Coordinates;
import utils.ParseUtility;

/**
 * Solution to my_problems/supernova.md
 */
public class Supernova {
    public static void main(String[] args) {
        ParseUtility parse = new ParseUtility();
        parse.readTable(2);
        int n = parse.strArrayAt(0).length;
        Coordinates[] coordinates1 = new Coordinates[n];
        Coordinates[] coordinates2 = new Coordinates[n];
        for (int i = 0; i < n; i++) {
            String[] raw = parse.strAt(i, 0).split(",");
            coordinates1[i] = new Coordinates(Integer.parseInt(raw[0]), Integer.parseInt(raw[1]));
        }

        for (int i = 0; i < coordinates2.length; i++) {
            String[] raw = parse.strAt(i, 1).split(",");
            coordinates2[i] = new Coordinates(Integer.parseInt(raw[0]), Integer.parseInt(raw[1]));
        }

        System.out.println(solve(coordinates1, coordinates2));
    }

    static final int GRID_SIZE = 256;

    /**
     * since p2 is shuffled, and p2[i] isn't the same particle as p1[i], some level of brute force is required
     * <br>
     * your first thought might be to sort both arrays, but around what origin? by what? the only thing we could sort them by is their trajectory to an unknown origin.
     * <br>
     * so, that leaves us with a couple approaches
     * <br>
     * the first is to bruteforce two pairs of particles (where the first element of each pair is from p1, second from p2)
     * <br>
     * i won't get into it here, but if we have two pairs of particles to that specification, we can immediately tell if we've matched up the correct ones by seeing if they have a valid intersection (thanks zach)
     * <br>
     * from there, finding the origin is as simple as calculating their intersection
     * <br>
     * but the complexity of that algorithm is O((n choose 2) choose 2). Yikes...
     * <br>
     * <br>
     * one key realization can help us solve this problem faster
     * that realization (thanks again zach) is that there is a "void" around the origin that grows from p1 to p2. we can take advantage of that void despite the fact that p2 is not sorted according to p1.
     * <br>
     * so, let's:
     * <br>
     * <br>
     *   1. iterate through every cell in the grid
     *   <br>
     *   2. for each cell, calculate the distance from that cell to every point in p1. sum the distances. let's call that sum d1.
     *   <br>
     *   3. calculate the same sum from that cell to every point in p2. that's d2.
     *   <br>
     *   4. calculate d2 - d1
     *   <br>
     *   5. whichever cell with the highest d2 - d1 is our origin.<br>
     * @param p1 An unordered set of every point in the first photo
     * @param p2 An unordered set of every point in the second photo
     * @return The origin of the supernova
     */
    public static Coordinates solve(Coordinates[] p1, Coordinates[] p2){
        int n = p1.length;
        double max = 0.0;
        Coordinates maxC = new Coordinates(-1, -1);
        for(int r = 0; r<GRID_SIZE; r++){
            for(int c = 0; c<GRID_SIZE; c++){
                double s1 = 0.0;
                double s2 = 0.0;
                Coordinates o = new Coordinates(c, r);
                for(int i = 0; i<n; i++){
                    Coordinates c1 = p1[i];
                    Coordinates c2 = p2[i]; //we're not associating those two, don't worry; we're calculating distance independently
                    double d1 = o.distance(c1);
                    s1 += d1;
                    double d2 = o.distance(c2);
                    s2 += d2;
                }
                double diff = s2 - s1;
                if(diff > max){
                    max = diff;
                    maxC = o;
                }
            }
        }
        return maxC;
    }
}
