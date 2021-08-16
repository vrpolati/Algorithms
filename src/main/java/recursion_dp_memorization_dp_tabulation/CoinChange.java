package recursion_dp_memorization_dp_tabulation;


import java.util.*;

/*  https://leetcode.com/problems/coin-change/ */
public class CoinChange {

    public static void main(String[] args) {
        int[] coins = {1,2,5};
        int amount = 11;
        System.out.println(coinChange(coins, amount));

        List<Integer> coinList = new ArrayList<>();
        for(int coin: coins){
            coinList.add(coin);
        }
        Map<String, Integer> cache = new HashMap<>();
        Integer minWays = coinChangeDPMemorization(coinList, amount, cache);
        System.out.println(Objects.isNull(minWays) ? -1 : minWays);
        System.out.println(coinChangeDPTabluation(coins, amount));
        System.out.println(coinChangeDPTabluation2(coins, amount));
    }

    public static int coinChange(int[] coins, int amount) {
        List<Integer> coinList = new ArrayList<>();
        for(int coin: coins){
            coinList.add(coin);
        }
        Integer minWays = coinChangeRecursion(coinList, amount);
        return  Objects.isNull(minWays) ? -1 : minWays;
    }

    public static Integer coinChangeRecursion(List<Integer> coins, int amount) {
        if(amount < 0) return null;
        else if(coins.isEmpty() && amount > 0) return null;
        else if(amount == 0 && !coins.isEmpty()) return 0;
        else if(coins.size()==1 && amount == coins.get(0)) return 1;
        else{
            Integer left = coinChangeRecursion(coins, amount-coins.get(coins.size()-1));
            int lastElement = coins.remove(coins.size()-1);
            Integer right = coinChangeRecursion(coins, amount);
            coins.add(lastElement);
            if(Objects.isNull(left)) return right;
            else if(Objects.isNull(right)) return 1+left;
            else return Math.min(1+left, right);
        }
    }


    public static Integer coinChangeDPMemorization(List<Integer> coins, int amount, Map<String,Integer> cache) {
        if(amount < 0) return null;
        else if(coins.isEmpty() && amount > 0) return null;
        else if(amount == 0 && !coins.isEmpty()) return 0;
        else if(coins.size()==1 && amount == coins.get(0)) return 1;
        else{
            String key = amount+","+coins.size();
            if(cache.containsKey(key)) return cache.get(key);
            Integer left = coinChangeDPMemorization(coins, amount-coins.get(coins.size()-1), cache);
            int lastElement = coins.remove(coins.size()-1);
            Integer right = coinChangeDPMemorization(coins, amount, cache);
            coins.add(lastElement);
            Integer result;
            if(Objects.isNull(left)) result =  right;
            else if(Objects.isNull(right)) result = 1+left;
            else result =  Math.min(1+left, right);
            cache.put(key, result);
            return result;
        }
    }


    public static int coinChangeDPTabluation(int[] coins, int amount){
        Integer[] dp = new Integer[amount+1];
/*        Arrays.fill(dp, null);*/
        dp[0] = 0;
        for(int coin: coins){
            for(int c1=coin; c1<=amount; c1++){
                Integer left = dp[c1-coin];
                Integer right = dp[c1];
                Integer result;
                if(Objects.isNull(left)) result =  right;
                else if(Objects.isNull(right)) result = 1+left;
                else result =  Math.min(1+left, right);
                dp[c1] = result;
            }
        }
        return Objects.isNull(dp[amount]) ? -1 : dp[amount];
    }

    public static int coinChangeDPTabluation2(int[] coins, int amount){
        Integer[] dp = new Integer[amount+1];
        /*        Arrays.fill(dp, null);*/
        dp[0] = 0;
        for(int coin: coins){
            for(int c1=coin; c1<=amount; c1++){
                Integer left = dp[c1-coin];
                if(Objects.isNull(left)) continue;
                if(dp[c1] == null) dp[c1] = 1+left;
                else dp[c1] = Math.min(dp[c1], left+1);
            }
        }
        return Objects.isNull(dp[amount]) ? -1 : dp[amount];
    }

}
