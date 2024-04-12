import utils.Coordinates;
import utils.Pair;
import utils.ParseUtility;

import java.util.*;

public class MarchingLegion {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int times = Integer.parseInt(scan.nextLine());
        ParseUtility parser = new ParseUtility(scan);
        for(int i = 0; i<times; i++){
            parser.readTable(1);
            int width = parser.integerAt(0, 0);
            int height = parser.integerAt(1, 0);
            parser.readTable(height, "");
            if(canMarch(parser.table, width, height)) System.out.println("March onward!");
            else System.out.println("Stay home!");
        }
    }

    public static boolean canMarch(String[][] map, int width, int height){
        //we'll do a bfs starting at R
        Pair<Coordinates, Coordinates> startEnd = startEndCoords(map);
        Coordinates start = startEnd.key;
        Coordinates end = startEnd.value;
        boolean[][] markedMap = new boolean[height][width];
        LinkedList<Coordinates> bfs = new LinkedList<>(); //<location, number to set>. number is incremented before going in the pair
        bfs.offer(start);
        markedMap[start.y][start.x] = true;
        while(!bfs.isEmpty()){
            Coordinates current = bfs.poll();
            markedMap[current.y][current.x] = true;
            for(Coordinates c : adjacent(current)){
                if(c.x < width && c.x >= 0 && c.y < height && c.y >= 0 && !markedMap[c.y][c.x] && !map[c.y][c.x].equals("X")){
                    if(c.equals(end)) return true;
                    markedMap[c.y][c.x] = true;
                    bfs.offer(c);
                }
            }
        }
        return false;
    }

    public static List<Coordinates> adjacent(Coordinates c){
        return Arrays.asList(new Coordinates(c.x + 1, c.y), new Coordinates(c.x, c.y + 1), new Coordinates(c.x - 1, c.y), new Coordinates(c.x, c.y - 1));
    }

    public static Pair<Coordinates, Coordinates> startEndCoords(String[][] map){
        Coordinates start = null;
        Coordinates end = null;
        for(int y = 0; y<map.length; y++){
            for(int x = 0; x<map[y].length; x++){
                String val = map[y][x];
                switch(val) {
                    case "R":
                        start = new Coordinates(x, y);
                        break;
                    case "A":
                        end = new Coordinates(x, y);
                        break;
                }
            }
        }
        return new Pair<>(start, end);
    }
}
