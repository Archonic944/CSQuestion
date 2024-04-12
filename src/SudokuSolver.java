import utils.Pair;
import utils.ParseUtility;

import java.util.Arrays;
import java.util.Scanner;

public class SudokuSolver {
    public static void main(String[] args) {
        System.out.println("""
        Please input a board with no line breaks.
        Boards can be found at https://github.com/t-dillon/tdoku/blob/master/data.zip
        """); //java 15 string
        while(true) {
            Character[][] board = parseBoard();
            long millis = System.currentTimeMillis();
            if(solveSudoku(board, firstEmptyCell(board))){
                System.out.println("\n==========[DONE in " + (System.currentTimeMillis() - millis) + "ms]==========");
                printBoard(board);
            }else System.out.println("Invalid board, no solution.");
        }
    }

    /**
     * @return a (y, x) pair with the first empty cell. null if there are no empty cells left.
     */
    static Pair<Integer, Integer> firstEmptyCell(Character[][] board){
        for(int r = 0; r<9; r++){
            for(int c = 0; c<9; c++){
                if(board[r][c] == '.') return new Pair<>(r, c);
            }
        }
        return null;
    }

    /**
     * Parses a board with 2D array notation, no line breaks.
     * <blockquote><pre>
     *     [["5","3",".",".","7",".",".",".","."],["6",".",".","1","9","5",".",".","."],[".","9","8",".",".",".",".","6","."],["8",".",".",".","6",".",".",".","3"],["4",".",".","8",".","3",".",".","1"],["7",".",".",".","2",".",".",".","6"],[".","6",".",".",".",".","2","8","."],[".",".",".","4","1","9",".",".","5"],[".",".",".",".","8",".",".","7","9"]]
     * </pre></blockquote>
     */
    static Character[][] arrayNotationBoard(){
        ParseUtility parser = new ParseUtility(new Scanner(System.in));
        parser.readArrayNotation();
        Object[][] board =  parser.convert2D((s) -> s.charAt(0));
        //i hate java
        Character[][] board2 = new Character[board.length][board[0].length];
        for(int i = 0; i<board.length; i++){
            for(int j = 0; j<board[0].length; j++){
                board2[i][j] = (Character) board[i][j];
            }
        }
        return board2;
    }

    /**
     * Parses a board with no line breaks:
     * <blockquote>
     *     <pre>
     *         98.7.....6.....87...7.....5.4..3.5....65...9......2..1..86...5.....1.3.......4..2
     *     </pre>
     * </blockquote>
     */
    static Character[][] parseBoard(){
        Scanner scan = new Scanner(System.in);
        Character[][] board = new Character[9][9];
        String line = scan.nextLine();
        for(int i = 0; i<9; i++){
            for(int j = 0; j<9; j++){
                board[i][j] = line.charAt(i * 9 + j);
            }
        }
        return board;
    }

    /**
     * Solves sudoku with recursive backtracking. Takes 40-90ms.
     */
    public static boolean solveSudoku(Character[][] board, Pair<Integer, Integer> next) {
        if(next == null) return true;
        for (int i = 1; i < 10; i++) {
            board[next.key][next.value] = Character.forDigit(i, 10);
            if (boardIsValid(board) && solveSudoku(board, firstEmptyCell(board))) {
                return true;
            }
        }
        board[next.key][next.value] = '.';
        return false;
    }

    static void printBoard(Character[][] board){
        for(int i = 0; i<9; i++){
            System.out.println(Arrays.toString(board[i]));
        }
    }

    /**
     * Validates a board pretty quickly, by simultaneously checking rows, columns, and cohorts, and by using bitmasking.
     */
    static boolean boardIsValid(Character[][] board){
        for(int r = 0; r<9; r++) {
            short[] bitmasks = new short[3]; //digit bitfields, only 1-9 are used :)
            for (int c = 0; c < 9; c++) {
                char[] chars = {board[r][c], board[c][r], board[(c / 3) + (r / 3) * 3][c % 3 + (r % 3) * 3]}; //last one is getting the digits in each 3x3 square
                for (int i = 0; i < 3; i++) {
                    if (chars[i] == '.') continue;
                    int digit = chars[i] - '0';
                    short digitMask = (short) (1 << (digit - 1));
                    if ((bitmasks[i] & digitMask) != 0) {
                        //System.out.println("There are two " + digit + "s in the same row/column/square. ");
                        //System.out.println("Row: " + r + ", Col: " + c);
                        return false;
                    } else {
                        bitmasks[i] |= digitMask;
                    }
                }
            }
        }
        return true;
    }
}
