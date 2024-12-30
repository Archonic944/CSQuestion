import utils.ParseUtility;

import java.util.Arrays;
import java.util.Scanner;

/**
 * WARNING: CURRENTLY BROKEN. I AM NOT FIXING THIS SHIT.
 */
public class KnightTour {
    //https://www.geeksforgeeks.org/the-knights-tour-problem/ because i suck at explaining
    //find the series of moves where the knight visits each square once
    //do not revisit squares >:(
    //possible vectors: {1, 2}, {-1, -2}, {-1, 2}, {1, -2}, {2, 1}, {-2, 1}, {-2, -1}, {2, -1}
    // ^^ literally just every possible knight move expressed relatively
    //output: a chess board, with the turn number that the cell was visited corresponding to each cell
    static int N = 8; //N * N board.
    int xMove[] = { 2, 1, -1, -2, -2, -1, 1, 2 };
    int yMove[] = { 1, 2, 2, 1, -1, -2, -2, -1 };

    public int[][] solution = new int[N][N];

    public static void main(String[] args) {
        KnightTour kt = new KnightTour();
        kt.solve((int) 0, (int) 0, (int) 1);
        for(int[] row : kt.solution){
            System.out.println(Arrays.toString(row));
        }
    }

    {
        for(int[] r : solution) Arrays.fill(r, (int) -1);
        solution[0][0] = 0;
    }

    public boolean solve(int currentX, int currentY, int moveNum){ //start it at 0
        if(moveNum == N * N) return true;
        for (int i = 0; i < 8; i++) {
            System.out.println("moveNum = " + moveNum + ", currentX = " + currentX + ", currentY = " + currentY + ", i = " + i);
            int next_x = currentX + xMove[i];
            int next_y = currentY + yMove[i];
            if (isSafe(next_x, next_y)) {
                System.out.println(next_x + ", " + next_y + " is safe.");
                solution[next_x][next_y] = moveNum;
                if (solve(next_x, next_y, moveNum + 1)){
                    return true; //recurse
                }
                System.out.println("Backtracked " + next_x + ", " + next_y + ".");
                solution[next_x][next_y] = -1; //backtrack if not possible
            }
        }
        return false;
    }

    boolean isSafe(int x, int y){
        return x < N && y < N && x >= 0 && y >= 0 && solution[x][y] == -1;
    }
}
