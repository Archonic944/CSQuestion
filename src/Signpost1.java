//UMD 2015 problem set

import utils.Pair;
import utils.IntPair;
import utils.ParseUtility;

import java.util.Arrays;
import java.util.Scanner;

public class Signpost1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numPuzzles = Integer.parseInt(scanner.nextLine());
        ParseUtility parse = new ParseUtility(scanner);
        for(int j = 0; j< numPuzzles; j++){
            int dimensions = Integer.parseInt(scanner.nextLine());
            parse.readTable(dimensions);
            @SuppressWarnings("unchecked") Pair<Integer,Character>[][] squares = new Pair[dimensions][dimensions];
            for(int i = 0; i<dimensions; i++){
                for(int k = 0; k<dimensions; k++){
                    squares[i][k] = new Pair<>(parse.integerAt(k * 2, i), parse.singleCharAt((k * 2) + 1, i));
                }
            }
            System.out.println("solveSignpost1(squares[" + j + ") = " + solveSignpost1(squares));
        }
    }

    public static int solveSignpost1(Pair<Integer, Character>[][] squares){
        //search for square 1
        IntPair c = new IntPair(-1, 0); //r, c
        for(int i = 0; i<squares.length; i++){
            for (int l = 0; l < squares.length; l++) {
                if(squares[l][i].key == 1){
                    c = new IntPair(i, l);
                }
            }
        }
        assert(c.key != 1);
        for (int i = 1; i < squares.length * squares.length; i++) {
            Pair<Integer, Character> current = squares[c.key][c.val];
            boolean valid = false;
            switch(current.value){
                case 'W':
                    //traverse west and check for the next coords
                    while(--c.val >= 0){
                        if(squares[c.key][c.val].key == (i + 1)) {
                            valid = true;
                            break;
                        }
                    }
                case 'S':
                    while(--c.key >= 0){
                        if(squares[c.key][c.val].key == (i + 1)) {
                            valid = true;
                            break;
                        }
                    }
                case  'N':
                    while(++c.key < squares.length){
                        if(squares[c.key][c.val].key == (i + 1)) {
                            valid = true;
                            break;
                        }
                    }
                case 'E':
                    while(++c.val < squares.length){
                        if(squares[c.key][c.val].key == (i + 1)) {
                            valid = true;
                            break;
                        }
                    }
            }
            if(!valid) return i;
        }
        return -1;
    }
}
