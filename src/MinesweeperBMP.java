import utils.Coordinates;
import utils.FileUtils;
import utils.MiscUtils;

import java.io.Console;
import java.util.Scanner;
import java.util.logging.ConsoleHandler;

/**
 * Given a bitmap, return a VALID minesweeper board where every pixel indicated by the bitmap is a revealed (clicked) tile, and the value is at least 1 (and not a bomb). The value of each tile on the minesweeper board should be the LEAST VALUE POSSIBLE; if there exists another board where the sum of all the tiles is less than the sum of all your tiles, you failed the challenge. If impossible, print "impossible".
 * <br><br> Board is returned as a 2D int array, where an integer at (x,y) represents how many bombs are adjacent to x,y. The int array doesn't represent the bombs, but the revealed spaces.
 */
public class MinesweeperBMP {
    public static void main(String[] args){
        String path = new Scanner(System.in).nextLine();
        boolean[][] bmp = FileUtils.loadBMP(path);
        for(boolean[] ba : bmp){
            for(boolean b : ba){
                System.out.print((b ? "▮" : "▯") + " ");
            }
            System.out.println();
            //bump down priority based on overlap, maintain separate 2d array of adjacent counts accompanied by a map
        }
        byte[][] solution = board(bmp);
        for(byte b[] : solution){
            for(byte b1 : b){
                System.out.print(b1 == 127 ? "X" : b1 == -1 ? " " : b1);
            }
            System.out.println();
        }

    }

    /**
     *
     * @return A board corresponding to with revealed squares 0+. Unrevealed squares -1. Bombs 127.
     */
    public static byte[][] board(boolean[][] bmp){
        int width = bmp[0].length;
        int height = bmp.length;
        //we'll solve this problem by first figuring out where the bombs should go
        byte[][] revealedBoard = new byte[height][width];
        byte[][] priorities = new byte[height][width]; //at any given point how many unrevealed target tiles are adjacent to this tile
        //greedy algorithm; find spaces with the most tiles surrounding them, place bombs there
        for(int r = 0; r<height; r++){
            for (int c = 0; c < width; c++) {
                boolean revealed = bmp[r][c];
                if(revealed) continue; //we are not interested in revealed tiles, because those cannot be bombs. we are looking for bomb potential here
                int adjacent = countAdjacent(bmp, revealedBoard, c, r, width, height);
                priorities[r][c] = (byte) adjacent;
            }
        }
        //very bad no good bruteforce. will optimize later.
        int max;
        Coordinates maxCoords;
        while(true){
            max = 0;
            maxCoords = new Coordinates(-1, -1);
            for (int r = 0; r < height; r++) {
                for (int c = 0; c < width; c++) {
                    if(priorities[r][c] > max){
                        maxCoords.x = c;
                        maxCoords.y = r;
                        max = priorities[r][c];
                    }
                }
            }
            if(max <= 0) break;
            //"place a bomb" at that location
            revealedBoard[maxCoords.y][maxCoords.x] = 127;
            Coordinates[] adjTiles = adjTiles(maxCoords.x, maxCoords.y, 1);
            for(Coordinates c : adjTiles){
                if(inBounds(width, height, c) && bmp[c.y][c.x]){
                    revealedBoard[c.y][c.x]++;
                }
            }
            priorities[maxCoords.y][maxCoords.x] = -1;
            //update priorities (efficient, inconsistent)
            /*Coordinates[] toUpdate = adjTiles(maxCoords.x, maxCoords.y, 2);
            for(Coordinates c : toUpdate){
                priorities[c.y][c.x] = (byte) countAdjacent(bmp, revealedBoard, c.x, c.y, width, height);
            }*/
            //highly inefficient, but consistent. optimize
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    priorities[i][j] = (byte) countAdjacent(bmp, revealedBoard, j, i, width, height);
                }
            }
            //single pass to trim any value that isn't in the bmp
            for (int i = 0; i < height; i++) {
                for(int j = 0; j<width; j++){
                    if(!bmp[i][j] && revealedBoard[i][j] != 127) revealedBoard[i][j] = -1;
                }
            }
        }
        return revealedBoard;
    }

    static boolean inBounds(int width, int height, Coordinates c){
        return c.x >= 0 && c.x < width && c.y >= 0 && c.y < height;
    }

    static Coordinates[] adjTiles(int c, int r, int radius){
        return new Coordinates[]{
                new Coordinates(c, r + radius),
                new Coordinates(c, r - radius),
                new Coordinates(c - radius, r),
                new Coordinates(c + radius, r),
                new Coordinates(c + radius, r + radius),
                new Coordinates(c - radius, r - radius),
                new Coordinates(c + radius, r - radius),
                new Coordinates(c - radius, r + radius)
        };
    }

    private static int countAdjacent(boolean[][] bmp, byte[][] revealedBoard, int c, int r, int width, int height) {
        if(bmp[r][c]) return -1;
        int adjacent = 0;
        Coordinates[] adjTiles = adjTiles(c, r, 1);
        for(Coordinates adj : adjTiles){
            if(inBounds(width, height, adj) && bmp[adj.y][adj.x]){
                if(revealedBoard[adj.y][adj.x] == 0){
                    adjacent++;
                }
            }
        }
        return adjacent;
    }
}
