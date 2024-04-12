import java.util.*;
import java.util.stream.Collectors;

public class TaxLedger {
    public int[] taxAmounts;
    public TaxLedger(int[] taxAmounts){
        this.taxAmounts = taxAmounts;
    }

    public static int calcTotal(Set<Integer> indices) {
        int total = 0;
        for (int i : indices) {
            total += i;
        }
        return total;
    }

    public int size(){
        return taxAmounts.length;
    }

    /**
     *
     * @return A set of all sets of indices of the specified length. (e.g. [[0, 1]] when peoplePaid is 2 and there are two people in the ledger). Each index represents a taxpayer that paid.
     * @param peoplePaid Represents
     */
    public Set<Set<Integer>> allIndices(int peoplePaid) throws Exception {
        if(peoplePaid > taxAmounts.length) throw new Exception("People paid cannot be greater than the amount of people in the ledger!");
        Set<Set<Integer>> sets = new HashSet<>();
        int[] source = new int[peoplePaid];
        do{
            Set<Integer> set = Arrays.stream(source).boxed().collect(Collectors.toSet());
            if(set.size() == peoplePaid){
                boolean added = sets.add(set);
                //System.out.println((added ? "added" : "did not add") + " set with source " + Arrays.toString(source));
            }
            if(!isFull(source)){
                increment(source);
            } else {
                break;
            }
        }while(true);
        //System.out.println(sets);
        return sets;
    }

    /**
     * Full check with size() as the base
     *
     */
    private boolean isFull(int[] currentNum){
        int base = size();
        boolean full = true;
        for (int j : currentNum) {
            //full check
            if (!(j == base - 1)) {
                full = false;
                break;
            }
        }
        return full;
    }

    /**
     *
     * Basic addition with a certain base (e.g. base 4 addition)
     */
    private void increment(int[] currentNum){
        int base = taxAmounts.length;
        int carry = 1;
        for(int i = 0; i< currentNum.length; i++){
            if((currentNum[i] + carry) >= base){
                currentNum[i] = 0;
            }else if(carry >= 1){
                currentNum[i] += 1;
                carry = 0;
            }
        }
    }
}
