import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Portals {
    public static void main(String[] args) {
        int testCase = 2; // Change this value to test different cases
        portals = new HashMap<>();
        portals.put('A', new char[]{'B', 'C', 'D'});
        portals.put('B', new char[]{'E', 'F'});
        portals.put('C', new char[]{'G', 'H'});
        portals.put('D', new char[]{'I', 'J'});
        portals.put('E', new char[]{'K', 'L'});
        portals.put('F', new char[]{'M'});
        portals.put('G', new char[]{'N'});
        portals.put('H', new char[]{'O'});
        portals.put('I', new char[]{'P'});
        portals.put('J', new char[]{'Q'});
        portals.put('K', new char[]{'R'});
        portals.put('L', new char[]{'S'});
        portals.put('M', new char[]{'T'});
        portals.put('N', new char[]{'U'});
        portals.put('O', new char[]{'V'});
        portals.put('P', new char[]{'W'});
        portals.put('Q', new char[]{'R'});
        portals.put('R', new char[]{'S'});
        portals.put('S', new char[]{'T'});
        portals.put('T', new char[]{'U'});
        portals.put('U', new char[]{'V'});
        portals.put('V', new char[]{'W'});
        portals.put('W', new char[]{'X'});
        portals.put('X', new char[]{'Y'});
        portals.put('Y', new char[]{'A'});
        portals.put('Z', new char[]{});
        System.out.println(pathExists("", 'A', 'Z')); // Expected output: false
    }

    // A basic cache. A portal is only invalidated once ALL of its possible paths are dead ends
    static Set<Character> traversed = new HashSet<>();
    static HashMap<Character, char[]> portals;

    public static boolean pathExists(String history, char current, char target) {
        char[] result = portals.getOrDefault(current, new char[0]); // If there is no entry for this portal, supply an empty array
        for (char c : result) { // Try each path
            if (c == target) return true; // We found it!
            if (history.indexOf(c) == -1 && !traversed.contains(c)) { // We need to keep looking
                if (pathExists(history + c, c, target)) return true;
            }
        }
        traversed.add(current);
        return false;
    }
}
