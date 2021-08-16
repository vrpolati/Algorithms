package recursion_dp_memorization_dp_tabulation;


import java.util.HashMap;
import java.util.Map;

/*   https://leetcode.com/problems/longest-palindromic-subsequence/ */
public class LongestPalindromicSubstring {

    public static void main(String[] args) {
        LongestPalindromicSubstring lps = new LongestPalindromicSubstring();
        String[] inputs = new String[]{
                "bbbab", "cbbd","sdkhjfgwandsnvls","a"
        }; /*  4, 2 */
        for(String input: inputs){
            System.out.print("Input: "+input);
            System.out.println("\nRecursion: "+lps.longestPalindromeSubseqRecursion(input));
            Map<String,Integer> cache = new HashMap<>();
            System.out.println("DpMemorization: "+lps.longestPalindromeSubseqDpMemorization(input, cache));
            System.out.println("DpTabulation: "+lps.longestPalindromeSubseqDpTabulation(input));
            System.out.println("DpTabulationV2: "+lps.longestPalindromeSubseqDpTabulation2(input));
            String reverse = new StringBuilder(input).reverse().toString();
            Map<String,Integer> lcsCache = new HashMap<>();
            System.out.println("lcsDpMemorization: "+lps.longestCommonSubsequenceDpMemorization(input, reverse, input.length()-1, reverse.length()-1,lcsCache));
            System.out.println("lcsDpTabulation: "+lps.longestCommonSubsequenceDpTabulation(input, reverse));
            System.out.println("--------------------");
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

    public int longestPalindromeSubseqDpTabulation2(String input) {
        int[][] dp = new int[input.length()][input.length()];
        for(int l=0;l<input.length();l++){
            for(int r1=0, c1=r1+l;r1<input.length() && c1 < input.length();r1++,c1++){
                if(r1 == c1) dp[r1][c1] = 1;
                else if(input.charAt(r1) == input.charAt(c1)){
                    dp[r1][c1] = 2+dp[r1+1][c1-1];
                }else{
                    dp[r1][c1] = Math.max(dp[r1][c1-1], dp[r1+1][c1]);
                }
            }
        }
        return dp[0][input.length()-1];
    }

    public int longestPalindromeSubseqDpTabulation(String input) {
        Map<String,Integer> cache = new HashMap<>();
        for(int l=0; l<input.length();l++){
            for(int r1=0;r1<input.length() ;r1++){
                int col = r1+l;
                if(r1 == col) cache.put(getkey(r1,col,input), 1);
                else if(col < input.length()){
                    int result;
                    if(input.charAt(r1) == input.charAt(col)){
                        result = 2+cache.getOrDefault(getkey(r1+1,col-1,input),0);
                    }else{
                        String p1Key = getkey(r1,col-1,input); //r1+","+(col-1);
                        String p2Key = getkey(r1+1,col,input);//(r1+1)+","+col;
                        result = Math.max(cache.get(p1Key), cache.get(p2Key));
                    }
                    cache.put(getkey(r1,col,input), result);
                }
            }
        }
        return cache.get(input);
    }

    private String getkey(int i1, int i2, String input){
        return input.substring(i1,i2+1);
    }

    public int longestPalindromeSubseqDpMemorization(String s, Map<String,Integer> cache) {
        if(s.length() < 2) return s.length();
        if(cache.containsKey(s)) return cache.get(s);
        int p1 = longestPalindromeSubseqDpMemorization(s.substring(0,s.length()-1), cache);
        int p2 = longestPalindromeSubseqDpMemorization(s.substring(1), cache);
        int result = Math.max(p1,p2);
        if(s.charAt(0) == s.charAt(s.length()-1)){
            result = Math.max(2+longestPalindromeSubseqDpMemorization(s.substring(1, s.length()-1), cache), result);
        }
        cache.put(s, result);
        return result;
    }

    public int longestPalindromeSubseqRecursion(String s) {
        if(s.length() < 2) return s.length();
        int p1 = longestPalindromeSubseqRecursion(s.substring(0,s.length()-1));
        int p2 = longestPalindromeSubseqRecursion(s.substring(1));
        int result = Math.max(p1,p2);
        if(s.charAt(0) == s.charAt(s.length()-1)){
            result = Math.max(2+longestPalindromeSubseqRecursion(s.substring(1, s.length()-1)), result);
        }
        return result;
    }

}
