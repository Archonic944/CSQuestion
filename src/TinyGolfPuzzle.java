import utils.*;

import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

import static utils.MiscUtils.vectorFromName;

public class TinyGolfPuzzle {
    public static void main(String[] args) {
        //parse board
        Scanner scan = new Scanner(System.in);
        int reps = Integer.parseInt(scan.nextLine());
        ParseUtility parse = new ParseUtility(scan);
        parse.readTable(reps, "");
        //we can actually just straight up use this ParseUtility as a grid
        System.out.println("Minimum moves: " + solve(parse.table));
    }

    public static String solve(String[][] board){ //every string is 1 char
        int height = board.length;
        int width = board[0].length;
        //least number of moves, associated with a path through the board
        HashMap<Coordinates, Integer> traversals = new HashMap<>(); //x,y where x is coordinates and y is the least amt of moves it takes to get to that coord
        HashMap<Coordinates, String> directions = new HashMap<>(); //x,y where x is coordinates and y is the direction we took to get there
        Stack<Coordinates> toCheck = new Stack<>();
        int ops = 0;
        //we must manually search for the starting point
        search: for (int i = 0; i < board.length; i++) {
            String[] s = board[i];
            for (int j = 0; j < s.length; j++) {
                String ss = s[j];
                if(ss.equals("O")){
                    toCheck.push(new Coordinates(j, i));
                    traversals.put(new Coordinates(j, i), 0);
                    break search;
                }
            }
        }
        //go over every element of toCheck, adding more to toCheck as we go
        Coordinates flagLocation = null;
        while (!toCheck.isEmpty()) {
            Coordinates coo = toCheck.pop();
            IntPair[] vectors = MiscUtils.vectors;
            vecLoop:
            for (int i = 0; i < vectors.length; i++) {
                IntPair vector = vectors[i];
                ops++;
                int current = traversals.get(coo) + 1;
                Coordinates coos = new Coordinates(coo.x, coo.y);
                coos.x += vector.key;
                coos.y += vector.val;
                //search out in all directions
                while (coos.x >= 0 && coos.x < width && coos.y >= 0 && coos.y < height) {
                    String here = board[coos.y][coos.x];
                    if (here.equals("X") || here.equals("4")) {
                        //WAIT! we are currently *inside* of a wall. go back.
                        if (here.equals("X")) {
                            coos.x -= vector.key;
                            coos.y -= vector.val;
                        }
                        if (!traversals.containsKey(coos) || traversals.get(coos) > current) {
                            traversals.put(coos, current);
                            directions.put(coos, MiscUtils.vectorNames[i]);
                            toCheck.push(coos);
                            //begin searching in another direction, and make sure to be a good samaritan and report the flag
                            if (flagLocation == null && board[coos.y][coos.x].equals("4")) {
                                flagLocation = coos;
                            }
                        }
                        //begin searching in another direction
                        continue vecLoop;
                    }
                    coos.x += vector.key;
                    coos.y += vector.val;
                }
            }
        }
        //recall our path
        if(flagLocation == null){
            return "Impossible";
        }
        Coordinates coords = flagLocation;
        int i = traversals.get(flagLocation);
        Stack<String> path = new Stack<>();
        while(i > 0){
            path.push(directions.get(coords));
            //move coords in the opposite direction of the vector
            IntPair vec = vectorFromName(directions.get(coords));
            assert vec != null;
            while(!board[coords.y][coords.x].equals("X")){
                coords.x -= vec.key;
                coords.y -= vec.val;
            }
            //hit a wall, go back
            coords.x += vec.key;
            coords.y += vec.val;
            i--;
        }
        StringBuilder pathString = new StringBuilder();
        while(!path.isEmpty()){
            String s = path.pop();
            pathString.append(s).append(" ");
        }
        System.out.println("Made " + ops + " moves");
        return pathString.toString();
    }
}
