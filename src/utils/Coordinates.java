package utils;

/**
 * Stores a coordinate pair.
 * Death to Pair{@code <Integer, Integer>}!
 */
public class Coordinates {
    public int x;
    public int y;

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coordinates that = (Coordinates) o;

        if (x != that.x) return false;
        return y == that.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    public double distance(Coordinates c) {
        return Math.sqrt(Math.pow(this.x - c.x, 2) + Math.pow(this.y - c.y, 2));
    }

    /**
     *
     * @return Distance in arbitrary units. A little faster
     */
    public int simpleDist(Coordinates c){
        return Math.abs(this.x - c.x) + Math.abs(this.y - c.y);
    }

    public void add(Coordinates c){
        this.x += c.x;
        this.y += c.y;
    }

    public void subtract(Coordinates c){
        this.x -= c.x;
        this.y -= c.y;
    }

    public void multiply(int i){
        this.x *= i;
        this.y *= i;
    }

    public void divide(int i){
        this.x /= i;
        this.y /= i;
    }

    //stupid stupid stupid stupid stupid stupid stupid stupid stupid stupid stupid stupid

    public void add(IntPair i){
        this.x += i.key;
        this.y += i.val;
    }

    public void subtract(IntPair i){
        this.x -= i.key;
        this.y -= i.val;
    }

    public void multiply(IntPair i){
        this.x *= i.key;
        this.y *= i.val;
    }

    public void divide(IntPair i){
        this.x /= i.key;
        this.y /= i.val;
    }
}
