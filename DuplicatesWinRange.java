import java.util.HashMap;
import java.util.Map;
class DuplicatesWinRange{
    public static boolean duplicates(int[] data, int k){
        Map<Integer,Integer> fdup = new HashMap<Integer,Integer>();
        for(int i =0; i < data.length;i++){
            if(fdup.containsKey(data[i])){
                if(i-fdup.get(data[i])<=k){
                    return true;
                }
            }
            fdup.put(data[i], i);
        }
        return false;
    }
    public static void main(String[] ars){
        int[] nums = { 5, 6, 8, 2, 4, 6, 9 };
        int k = 4;
 
        if (duplicates(nums, k)) {
            System.out.println("Duplicates found");
        }
        else {
            System.out.println("No duplicates were found");
        }
    }
}