package recursion_dp_memorization_dp_tabulation;


import java.util.*;

/* https://leetcode.com/discuss/study-guide/1152328/01-Knapsack-Problem-and-Dynamic-Programming  */
public class Knapsack0_1 {

    public static void main(String[] args) {
        int[] weights = {83,84, 85, 76, 13, 87, 2, 23, 33, 82, 79, 100, 88, 85, 91, 78, 83, 44, 4 ,50, 11, 68, 90, 88, 73, 83, 46, 16, 7, 35, 76, 31, 40, 49, 65,
                                                                        2, 18, 47, 55, 38, 75, 58, 86, 77, 96, 94, 82, 92, 10, 86, 54, 49, 65, 44, 77, 22, 81, 52};
        int[] values = {57,95, 13, 29, 1, 99, 34, 77, 61, 23, 24, 70, 73, 88, 33, 61, 43, 5, 41, 63, 8, 67, 20, 72, 98, 59, 46, 58, 64, 94, 97, 70,
                                                                46, 81, 42, 7, 1, 52, 20, 54, 81, 3 ,73, 78, 81, 11, 41, 45, 18, 94, 24, 82, 9, 19, 59, 48, 2, 72};
        int maxCapacity = 41;
        System.out.println(kanpSackRecursion(weights, values, maxCapacity, weights.length));
        System.out.println(knapSackDPMemorization(weights, values, maxCapacity, weights.length));
        System.out.println(knapSackDPTabulation(weights, values, maxCapacity, weights.length));
    }

    private static int kanpSackRecursion(int[] weights, int[] values, int maxCapacity, int idx){
        List<Integer> weightLit = new ArrayList<>(weights.length);
        List<Integer> valueList = new ArrayList<>(values.length);
        for(int c1=0; c1< weights.length; c1++){
            weightLit.add(weights[c1]);
            valueList.add(values[c1]);
        }
        return kanpSackRecursion(weightLit, valueList, maxCapacity, weights.length);
    }

    private static int knapSackDPMemorization(int[] weights, int[] values, int maxCapacity, int idx){
        Map<String,Integer> cache = new HashMap<>();
        return knapSackHelper(maxCapacity, weights, values, idx, cache);
    }

    private static int knapSackDPTabulation(int[] weights, int[] values, int maxCapacity, int idx){
        int[][] dp = new int[2][maxCapacity+1];
        int prev = 1;
        for(int m1 = 0; m1 <weights.length; m1++){
            int weight = weights[m1];
            for(int c1=0; c1<dp[0].length; c1++){
                if(c1<weight){
                    dp[prev^1][c1] = dp[prev][c1];
                }else{
                    int left = values[m1]+(dp[prev][c1-weight]);
                    dp[prev ^ 1][c1] = Math.max(left, dp[prev][c1]);
                }
            }
/*            System.out.println(Arrays.toString(dp[prev^1]));
            System.out.println(m1+":("+prev+"_"+(prev^1)+")");*/
            prev = prev ^ 1;
/*            System.out.println(m1+"_next:("+prev+"_"+(prev^1)+")");*/
        }
        return dp[prev][maxCapacity];
    }



    private static int knapSackHelper(int maxCapacity,int[] weights, int[] values, int idx, Map<String, Integer> cache){
        if(maxCapacity < 0) return 0;
        else if(idx < 1) return 0;
        else{
            String key = maxCapacity+","+idx;
            if(cache.containsKey(key)) return cache.get(key);
            int lastWeight = weights[idx-1];
            int lastValue = values[idx-1];
            int left = knapSackHelper(maxCapacity-lastWeight,weights, values, idx-1, cache)
                    + (lastWeight <= maxCapacity ? lastValue : 0);
            int right = knapSackHelper(maxCapacity,weights, values, idx-1, cache);
            int result = Math.max(left,right);
            cache.put(key,result);
            return cache.get(key);
        }
    }

    private static int kanpSackRecursion(List<Integer> weights, List<Integer> values, int maxCapacity, int idx){
        if(maxCapacity < 0) return 0;
        else if(idx < 1) return 0;
        else{
            int lastWeight = weights.get(idx-1);
            int lastValue = values.get(idx-1);
            int left = kanpSackRecursion(weights, values,maxCapacity-lastWeight, idx-1)
                        + (lastWeight <= maxCapacity ? lastValue : 0);
            int right = kanpSackRecursion(weights, values,maxCapacity, idx-1);
            return Math.max(left,right);
        }
    }

}
