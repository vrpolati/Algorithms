package recursion_dp_memorization_dp_tabulation;

import java.util.HashMap;
import java.util.Map;

public class LongestCommonSubsequence {

    public static void main(String[] args) {
        LongestCommonSubsequence lcs = new LongestCommonSubsequence();
        String[][] inputs = {
                {"abcde","ace"},
                {"abc","def"},
                {"sdkhjfgwandsnvls", new StringBuilder("sdkhjfgwandsnvls").reverse().toString()}
        };
        for(String[] input: inputs){
            String text1 = input[0];
            String text2 = input[1];
            System.out.println(lcs.longestCommonSubsequenceRecursion(text1, text2, text1.length()-1, text2.length()-1));
            Map<String,Integer> cache = new HashMap<>();
            System.out.println(lcs.longestCommonSubsequenceDpMemorization(text1, text2, text1.length()-1, text2.length()-1, cache));
            System.out.println(lcs.longestCommonSubsequenceDpTabulation(text1, text2));
        }
    }

    public int longestCommonSubsequenceRecursion(String text1, String text2, int t1Idx, int t2Idx) {
        if(t1Idx < 0 || t2Idx < 0) return 0;
        else{
            int p1 = longestCommonSubsequenceRecursion(text1, text2, t1Idx, t2Idx-1);
            int p2 = longestCommonSubsequenceRecursion(text1, text2, t1Idx-1, t2Idx);
            int result = Math.max(p1,p2);
            if(text1.charAt(t1Idx) == text2.charAt(t2Idx)){
                result = Math.max(result, 1+longestCommonSubsequenceRecursion(text1, text2,t1Idx-1,t2Idx-1));
            }
            return result;
        }
    }

    public int longestCommonSubsequenceDpMemorization(String text1, String text2, int t1Idx, int t2Idx, Map<String,Integer> cache) {
        String key = t1Idx+","+t2Idx;
        if(cache.containsKey(key)) return cache.get(key);
        else if(t1Idx < 0 || t2Idx < 0) return 0;
        else{
            int p1 = longestCommonSubsequenceDpMemorization(text1, text2, t1Idx, t2Idx-1, cache);
            int p2 = longestCommonSubsequenceDpMemorization(text1, text2, t1Idx-1, t2Idx, cache);
            int result = Math.max(p1,p2);
            if(text1.charAt(t1Idx) == text2.charAt(t2Idx)){
                result = Math.max(result, 1+longestCommonSubsequenceDpMemorization(text1, text2,t1Idx-1,t2Idx-1, cache));
            }
            cache.put(key, result);
            return cache.get(key);
        }
    }

    public int longestCommonSubsequenceDpTabulation(String text1, String text2) {
        int[][] dp = new int[text1.length()+1][text2.length()+1];
        for(int c1=1; c1<dp.length;c1++){
            for(int c2=1;c2<dp[c1].length;c2++){
                int result = Math.max(dp[c1][c2-1], dp[c1-1][c2]);
                if(text1.charAt(c1-1) == text2.charAt(c2-1)){
                    result = Math.max(result,1+dp[c1-1][c2-1]);
                }
                dp[c1][c2] = result;
            }
        }
        return dp[text1.length()][text2.length()];
    }

}
