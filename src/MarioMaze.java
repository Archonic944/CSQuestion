import utils.Pair;
import utils.ParseUtility;

import java.util.*;

public class MarioMaze {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int amt = scan.nextInt();
        scan.nextLine();
        ParseUtility parser = new ParseUtility(scan);
        for(int i = 0; i<amt; i++){
            int[] wh = Arrays.stream(scan.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int width = wh[0];
            int height = wh[1];
            parser.readTable(height);
            System.out.println(findPath(parser, width, height));
        }
    }

    /**
     * @return the minimum steps to end of path. -1 if impossible
     * @param maze parseutility with the following format: <br><blockquote>The labyrinth is provided in the form of a 2-dimensional character array representing its layout. Each cell in the array has a specific meaning: <br>
     * ● 'M': Mario's starting position<br>
     * ● 'P': Princess Peach's location<br>
     * ● 'O': Unobstructed path<br>
     * ● 'S': Spikes (obstacles Mario cannot pass through)<br></blockquote>
     */
    static int findPath(ParseUtility maze, int width, int height){
        int[][] floodFill = new int[height][width];
        fillGrid(floodFill);
        LinkedList<Pair<Pair<Integer, Integer>, Integer>> tilesToIterate = new LinkedList<>();
        Pair<Pair<Integer, Integer>, Integer> startPos = new Pair<>(new Pair<>(0, 0), 0);
        tilesToIterate.add(startPos);
        while(!tilesToIterate.isEmpty()){
            Pair<Pair<Integer, Integer>, Integer> currentPos = tilesToIterate.poll();
            floodFill[currentPos.key.value][currentPos.key.key] = currentPos.value;
            Pair<Integer, Integer>[] adjacent = tilesAround(currentPos.key);
            for(Pair<Integer, Integer> pair : adjacent){
                if((pair.key >= 0 && pair.key < width) &&
                        (pair.value >= 0 && pair.value < height) &&
                        maze.charAt(pair.key, pair.value) == 'O' &&
                        floodFill[pair.value][pair.key] < 0 &&
                        !tilesToIterate.contains(pair)
                ){
                    tilesToIterate.add(new Pair<>(pair, currentPos.value + 1));
                }
            }
        }
        return getMinSteps(width, height, floodFill);
    }

    /**
     * Gets the minimum amount of steps from the flood fill array. -1 for impossible.
     */
    private static int getMinSteps(int width, int height, int[][] floodFill) {
        //iterate through tiles adjacent to target
        Pair<Integer, Integer> peachPos = new Pair<>(width - 1, height - 1);
        Pair<Integer, Integer>[] endAdjacent = (Pair<Integer, Integer>[]) new Pair[]{new Pair<>(peachPos.key, peachPos.value - 1), new Pair<>(peachPos.key - 1, peachPos.value)};
        int min = Integer.MAX_VALUE;
        for(Pair<Integer, Integer> coords : endAdjacent){
            int ff = floodFill[coords.value][coords.key];
            if(ff == -1) continue;
            if(ff < min) min = ff;
        }
        return min == Integer.MAX_VALUE ? -1 : min + 1;
    }

    /**
     * Fills 2d array with -1
     */
    static void fillGrid(int[][] fill){
        for(int[] arr : fill){
            for(int i = 0; i<fill.length; i++){
                arr[i] = -1;
            }
        }
    }

    static Pair<Integer, Integer>[] tilesAround(Pair<Integer, Integer> tile){
        return new Pair[]{
                new Pair<>(tile.key + 1, tile.value),
                new Pair<>(tile.key - 1, tile.value),
                new Pair<>(tile.key, tile.value - 1),
                new Pair<>(tile.key, tile.value + 1)
        };
    }

    static Pair<Integer, Integer> findLetter(String letter, ParseUtility maze){
        for(int h = 0; h<maze.table.length; h++){
            String[] row = maze.strArrayAt(h);
            for(int w = 0; w<row.length; w++){
                if(Objects.equals(row[w], letter)){
                    return new Pair<>(w, h);
                }
            }
        }
        return new Pair<>(-1, -1);
    }
}
