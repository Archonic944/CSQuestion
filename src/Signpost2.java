//UMD 2015
//Unfinished

import java.util.Scanner;

import utils.IntPair;
import utils.Pair;
import utils.ParseUtility;

public class Signpost2 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int dimensions = Integer.parseInt(scan.nextLine());
        ParseUtility parse = new ParseUtility(scan);
        parse.readTable(dimensions);

    }

    public static boolean solveSignPostII(Pair<Integer, Character>[][] puzzle, IntPair start, int step){
        if(step == puzzle.length * puzzle.length + 1) return true;
        Pair<Integer, Character> startTile = puzzle[start.key][start.val];
        char dir = startTile.value;
        switch(dir) {
            case 'N':
                if(start.key == 0) {
                    return false;
                }else {
                    for(int i = start.key - 1; i >= 0; i--) {
                        Pair<Integer, Character> nextTile = puzzle[i][start.val];
                        if(nextTile.key != 0) continue;
                        nextTile.key = step + 1;
                        if(solveSignPostII(puzzle, new IntPair(i, start.val), step + 1)) {
                            return true;
                        }else {
                            //undo and continue
                            nextTile.key = 0;
                        }
                    }
                    return false;
                }
            case 'S':
                if(start.key >= puzzle.length - 1) {
                    return false;
                }else {
                    for(int i = start.key + 1; i < puzzle.length; i++) {
                        Pair<Integer, Character> nextTile = puzzle[i][start.val];
                        if(nextTile.key != 0) continue;
                        nextTile.key = step + 1;
                        if(solveSignPostII(puzzle, new IntPair(i, start.val), step + 1)) {
                            return true;
                        }else {
                            //undo and continue
                            nextTile.key = 0;
                        }
                    }
                    return false;
                }
            case 'E':
                if(start.val == puzzle.length - 1) {
                    return false;
                }else {
                    for(int i = start.val + 1; i < puzzle.length; i++) {
                        Pair<Integer, Character> nextTile = puzzle[start.key][i];
                        if(nextTile.key != 0) continue;
                        nextTile.key = step + 1;
                        if(solveSignPostII(puzzle, new IntPair(start.key, i), step + 1)) {
                            return true;
                        }else {
                            //undo and continue
                            nextTile.key = 0;
                        }
                    }
                    return false;
                }
            case 'W':
                if(start.val == 0) {
                    return false;
                }else {
                    for(int i = start.val - 1; i >= 0; i++) {
                        Pair<Integer, Character> nextTile = puzzle[start.key][i];
                        if(nextTile.key != 0) continue;
                        nextTile.key = step + 1;
                        if(solveSignPostII(puzzle, new IntPair(start.key, i), step + 1)) {
                            return true;
                        }else {
                            //undo and continue
                            nextTile.key = 0;
                        }
                    }
                    return false;
                }
            default:
                return false;


        }
    }
}
