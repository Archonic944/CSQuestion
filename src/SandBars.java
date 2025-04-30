import utils.Coordinates;
import utils.IntPair;
import utils.MiscUtils;
import utils.ParseUtility;

import java.util.LinkedList;
import java.util.Queue;

public class SandBars {
    public static void main(String[] args) {
        ParseUtility parser = new ParseUtility();
        int[] dimensions = parser.readDimensions();
        int rows = dimensions[0];
        int cols = dimensions[1];
        parser.readTable(rows, "");
        boolean[][] grid = parser.toBinaryGrid();
        int result = solve(grid);
        System.out.println(result);
    }

    /**
     * Count the amount of unique "islands" given a grid of tiles that either have sand or not
     * @param sandBars A boolean grid where true represents sand and false represents water
     * @return The number of islands
     */
    public static int solve(boolean[][] sandBars){
        //copy the grid first
        boolean[][] gridCopy = new boolean[sandBars.length][sandBars[0].length];
        for (int i = 0; i < sandBars.length; i++) {
            System.arraycopy(sandBars[i], 0, gridCopy[i], 0, sandBars[i].length);
        }
        //done copying, solve
        int width = gridCopy[0].length;
        int height = gridCopy.length;
        int ct = 0;
        for(int r = 0; r < height; r++){
            for(int c = 0; c < width; c++){
                if(gridCopy[r][c]) { //if there's sand, bfs and fill this island with water
                    ct++;
                    bfs(gridCopy, c, r);
                }
            }
        }
        return ct;
    }


    /**
     * Fills an island with water
     */
    public static void bfs(boolean[][] sandBars, int x, int y){
        if(!sandBars[y][x]) return; //none at initial, no need to make a queue
        Queue<Coordinates> q = new LinkedList<>();
        q.add(new Coordinates(x, y));
        while(!q.isEmpty()){
            Coordinates next = q.poll();
            if(sandBars[next.y][next.x]){
                sandBars[next.y][next.x] = false;
                for(IntPair ip : MiscUtils.vectors){
                    int newX = next.x + ip.key;
                    int newY = next.y + ip.val;
                    if(newX >= 0 && newX < sandBars[0].length && newY >= 0 && newY < sandBars.length){
                        q.offer(new Coordinates(newX, newY));
                    }
                }
            }
        }
    }
}
