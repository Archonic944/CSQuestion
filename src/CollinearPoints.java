import utils.Coordinates;
import utils.ParseUtility;

public class CollinearPoints {
    public static void main(String[] args) {
        //parse input of the format 1,1 2,2 3,3
        ParseUtility parse = new ParseUtility();
        parse.readTable(1);
        String[] strs = parse.strArrayAt(0);
        Coordinates[] coords = new Coordinates[strs.length];
        for (int i = 0; i < strs.length; i++) {
            String[] split = strs[i].split(",");
            coords[i] = new Coordinates(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
        }
        System.out.println(pointsAreCollinear(coords));
    }

    static boolean pointsAreCollinear(Coordinates[] coords){
        if(coords.length < 3) return true;

        double slope = 0;
        if(coords[0].x != coords[1].x){
            slope = (double) (coords[0].y - coords[1].y) /(coords[0].x - coords[1].x);
        }
        for(int j = 2; j<coords.length; j++){
            Coordinates c = coords[j];
            Coordinates c0 = coords[j - 1];
            if(c.x == c0.x) continue;
            double thisSlope = (double) (c.y - c0.y) / (c.x - c0.x);
            if(Math.abs(thisSlope - slope) > 0.0000001){ //float equals check with tolerance
                return false;
            }
        }
        return true;
    }
}
