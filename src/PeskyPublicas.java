import java.util.Arrays;
import java.util.Scanner;
import java.util.Set;

public class PeskyPublicas {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter amount of test cases:");
        int testCases = Integer.parseInt(scanner.nextLine());
        for(int j = 0; j<testCases; j++) {
            System.out.println("Enter amount of citizens:");
            int citizens = Integer.parseInt(scanner.nextLine()); //useless 🙄
            System.out.println("Enter tax amounts:");
            String taxesRaw = scanner.nextLine();
            int[] taxesArray = Arrays.stream(taxesRaw.split(" ")).mapToInt(Integer::parseInt).toArray();
            long startTime = System.nanoTime();
            TaxLedger ledger = new TaxLedger(taxesArray);
            double total = 0;
            int length = 0;
            //System.out.println("========[Final Sets]========");
            for (int peoplePaid = 1; peoplePaid <= ledger.size(); peoplePaid++) {
                Set<Set<Integer>> allPermutations = ledger.allIndices(peoplePaid);
                for (Set<Integer> set : allPermutations) {
                    length++;
                    for (int integer : set) {
                        total += ledger.taxAmounts[integer];
                        //System.out.print(integer + " ");
                    }
                    //System.out.println();
                }
            }
            long endTime = System.nanoTime();
            System.out.println("========[Time]========");
            System.out.println((endTime - startTime) / 1000000.0 + "ms");
            System.out.println("========[Average]========");
            System.out.println(total / length);
        }
    }
}
