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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        IntPair intPair = (IntPair) o;
        return key == intPair.key && val == intPair.val;
    }

    @Override
    public int hashCode() {
        int result = key;
        result = 31 * result + val;
        return result;
    }
}
