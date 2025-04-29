import utils.Pair;
import utils.ParseUtility;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Math.abs;

public class Triangles {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        ParseUtility parser = new ParseUtility(scan);
        parser.readTable(1);
        List<Pair<Double, Double>> triangle = parser.readCoords(0);
        //print triangle for debug
        int amt = Integer.parseInt(scan.nextLine());
        parser.readTable(amt);
        for(int i = 0; i<amt; i++){
            Pair<Double, Double> point = parser.readCoords(i).getFirst();
            System.out.println(triangleCollides(triangle, point) ? "YES" : "NO");
        }
    }

    static List<Double> areas = new ArrayList<>();

    public static boolean triangleCollides(List<Pair<Double, Double>> triangle, Pair<Double, Double> point){
        //check if point is inside triangle
        Double x = point.key;
        Double y = point.value;
        Double x1 = triangle.getFirst().key;
        Double y1 = triangle.get(0).value;
        Double x2 = triangle.get(1).key;
        Double y2 = triangle.get(1).value;
        Double x3 = triangle.get(2).key;
        Double y3 = triangle.get(2).value;
        double a0 = area(x1, y1, x2, y2, x3, y3);
        double a1 = area(x1, y1, x2, y2, x, y);
        double a2 = area(x, y, x2, y2, x3, y3);
        double a3 = area(x1, y1, x, y, x3, y3);
        areas.add(a0);
        areas.add(a1);
        areas.add(a2);
        areas.add(a3);
        return Math.abs((a1 + a2 + a3) - a0) < 0.00001;
    }

    static double area(double x1, double y1, double x2, double y2, double x3, double y3) {
        double area = abs((x2-x1)*(y3-y1) - (x3-x1)*(y2-y1));
        System.out.println("The area of a triangle with points " + x1 + ", " + y1 + " " + x2 + ", " + y2 + " " + x3 + ", " + y3 + " is " + area);
        return area;
    }
}
