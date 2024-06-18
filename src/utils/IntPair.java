package utils;

//ignore the fact that it's the same as Coordinates, please
public class IntPair {
    public int key;
    public int val;

    @Override
    public String toString() {
        return "(" + key + ", " + val + ")";
    }

    public IntPair(int key, int val) {
        this.key = key;
        this.val = val;
    }
}
