import utils.IntPair;

import java.util.ArrayList;
import java.util.Arrays;

public class CookieFilling {
    //TJIOI 2024 - Problem F
    static final boolean[][] BOARD = {
            {true, false, true, false},
            {false, true, false, true},
            {true, false, false, false},
            {false, false, false, true}
    };
    static final IntPair[] QUERIES = {
            new IntPair(0, 0),
            new IntPair(1, 1),
            new IntPair(2, 1),
            new IntPair(3, 3)
    };
    public static void main(String[] args) {
        solve(BOARD, QUERIES);
    }

    public static void solve(boolean[][] board, IntPair[] queries){
        int height = board.length;
        int width = board[0].length;
        for(IntPair ip : queries){
            int ct = 0;
            for(int r = 0; r<height; r++){
                for(int c = 0; c<width; c++){
                    if(canFit(board, c, r, ip.key, ip.val)){
                        ct++;
                    }
                }
            }
            System.out.println(ct);
        }
    }
    public static boolean canFit(boolean[][] board, int x, int y, int w, int h){
        for(int r = y; r<y + h; r++){
            if(r >= board.length) return false;
            for(int c = x; c<x + w; c++){
                if(c >= board[0].length) return false;
                if(board[r][c]){
                    System.out.println("couldn't fit");
                    return false;
                }
            }
        }
        return true;
    }

    private static class EmptyBlock {
        int width;
        int height;
        ArrayList<EmptyBlock> adjacentTo = new ArrayList<>();
        EmptyBlock(int width, int height){
            this.width = width;
            this.height = height;
        }

        public int getArea(){
            return width * height;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;

            EmptyBlock that = (EmptyBlock) o;
            return width == that.width && height == that.height;
        }

        @Override
        public int hashCode() {
            int result = width;
            result = 31 * result + height;
            return result;
        }
    }
}
