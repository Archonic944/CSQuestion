package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

public class ParseUtility {
    public static Scanner scannerFromFile(String path) {
        try {
            return new Scanner(new File(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Scanner scannerFromFile(File file){
        try {
            return new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String fileToString(File f){
        try {
            Scanner scan = new Scanner(f);
            StringBuilder sb = new StringBuilder();
            while(scan.hasNextLine()){
                sb.append(scan.nextLine());
            }
            return sb.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    static String[] asArray(String line){
        return line.split(" ");
    }

    static int[] asIntArray(String line){
        return asIntArray(asArray(line));
    }

    static int[] asIntArray(String[] line){
        return Arrays.stream(line).mapToInt(Integer::parseInt).toArray();
    }

    Scanner scanner;
    String split = " ";
    public String[][] table;
    public ParseUtility(Scanner scanner){
        this.scanner = scanner;
    }

    public ParseUtility(){
        this(new Scanner(System.in));
    }

    /**
     * Takes in new <code>split</code>-delimited data of the specified length. Must be called before other operations; previous data will be replaced.
     * @param length amount of rows to take from the scanner
     * @return This for chaining
     */
    public ParseUtility readTable(int length) {
        return readTable(length, " ");
    }

    /**
     * Takes in new <code>split</code>-delimited data of the specified length. Must be called before other operations; previous data will be replaced.
     *
     * @param length    amount of rows to take from the scanner
     * @param delimiter what character to split each row on
     * @return This for chaining
     */
    public ParseUtility readTable(int length, String delimiter){
        this.split = delimiter;
        table = new String[length][];
        int maxRowLength = 0;
        for(int i = 0; i<length; i++){
            table[i] = scanner.nextLine().split(split);
            if(table[i].length > maxRowLength) maxRowLength = table[i].length;
        }
        return this;
    }

    /**
     * reads space separated float coordinates, in format: (x y) (x y) ...
     */
    public List<Pair<Double, Double>> readCoords(int row){
        String[] arr = String.join(split, strArrayAt(row)).split("\\) \\(");
        ArrayList<Pair<Double, Double>> list = new ArrayList<>();
        for(String coord : arr){
            coord = coord.replace("(", "");
            coord = coord.replace(")", "");
            String[] split = coord.split(" ");
            list.add(new Pair<>(Double.parseDouble(split[0]), Double.parseDouble(split[1])));
        }
        return list;
    }

    /**
     * Reads a table of the format [[1,2,3],[4,5,6],[7,8,9]] into a 2D array.
     */
    public void readArrayNotation() {
        readArrayNotation(1);
    }

    /**
     * Reads a table of the format
     * <br> [[1,2,3], <br>
     * [4,5,6], <br>
     * [7,8,9]] <br>
     * into a 2D array.
     * <BR><BR>NOTE: This method will delete all quotation marks.
     */
    public void readArrayNotation(int lines){
        StringBuilder line = new StringBuilder();
        for(int i = 0; i<lines; i++){
            line.append(scanner.nextLine().replaceAll(" ", "").replaceAll("\"", ""));
        }
        if(line.charAt(0) == '[' && line.charAt(line.length() - 1) == ']'){
            line.deleteCharAt(0);
            line.deleteCharAt(line.length() - 1);
        }else throw new RuntimeException("Invalid array notation: " + line);
        if(line.isEmpty()){
            table = new String[0][];
            return;
        }
        String[] raw = line.toString().split("],\\[");
        for(int i = 0; i<raw.length; i++){
            raw[i] = raw[i].replace("[", "").replace("]", "");
        }
        table = new String[raw.length][];
        for(int i = 0; i<raw.length; i++){
            table[i] = raw[i].split(",");
        }
    }

    public int integerAt(int x, int y){
        return Integer.parseInt(strAt(x, y));
    }

    public int[] intArrayAtRow(int row){
        return intArrayAtRow(row, 0);
    }

    public int[] intArrayAtRow(int row, int offset){
        return asIntArray(Arrays.copyOfRange(table[row], offset, table[row].length)); //this is pretty slow. 🐌
    }

    /**
     * @throws NumberFormatException if the column contains non-integer values OR if the array is jagged in a way that leaves the column with empty spaces
     */
    public int[] intArrayAtColumn(int column){
        int[] arr = new int[table.length];
        for(int i = 0; i<table.length; i++){
            arr[i] = Integer.parseInt(table[i][column]);
        }
        return arr;
    }

    public String[] stringArrayAtColumn(int column){
        String[] arr = new String[table.length];
        for(int i = 0; i<table.length; i++){
            arr[i] = table[i][column];
        }
        return arr;
    }

    public double[] doubleArrayAtColumn(int column){
        double[] arr = new double[table.length];
        for(int i = 0; i<table.length; i++){
            arr[i] = Double.parseDouble(table[i][column]);
        }
        return arr;
    }

    /**
     *
     * @param x Column location of string
     * @param y Row location of string
     * @return String at coordinates
     */
    public String strAt(int x, int y){
        return table[y][x];
    }

    /**
     * In case each string is meant to be accessed as an array of characters.
     * @return Character at y-value of string at x-value
     * WARNING: This method assumes that the characters are combined into a single string, not 1 character per string.
     */
    public char charAt(int x, int y){
        return strAt(0, y).charAt(x);
    }

    /**
     * Assumes each entry is split into a single char separated by the delimiter.
     * @see ParseUtility::charAt
     */
    public char singleCharAt(int x, int y){
        return strAt(x, y).charAt(0);
    }

    public String[] strArrayAt(int row){
        return table[row];
    }

    public char[] charArrayAt(int row){
        char[] chars = new char[table[row].length];
        for(int i = 0; i<chars.length; i++){
            chars[i] = table[row][i].charAt(0);
        }
        return chars;
    }

    /**
     * Converts the table to a 2D array of type T using the provided function. This function processes each string in the table individually.
     */
    public <T> T[][] convert2D(Function<String, T> function){
        T[][] arr = (T[][]) new Object[table.length][table[0].length];
        for(int i = 0; i<table.length; i++){
            for(int j = 0; j<table[i].length; j++){
                arr[i][j] = function.apply(table[i][j]);
            }
        }
        return arr;
    }
}