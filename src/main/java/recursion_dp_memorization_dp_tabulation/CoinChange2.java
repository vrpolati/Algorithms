package recursion_dp_memorization_dp_tabulation;


import java.util.*;

/*  https://leetcode.com/problems/coin-change-2/ */
public class CoinChange2 {

    public static void main(String[] args) {
        int[] coins = {1,2,5};
        int amount = 11;
        List<Integer> coinsList = new ArrayList<>();
        for(int coin : coins) coinsList.add(coin);
        System.out.println(coinChangeRecursion(coinsList, amount));

        Map<String,Integer> cache = new HashMap<>();
        System.out.println(coinChangeDPMemorization(coinsList, amount, cache));
        System.out.println(coinChangeDPTabulation(coinsList, amount));
        System.out.println(coinChangeDPTabulationV2(coinsList, amount));
    }

    private static int coinChangeRecursion(List<Integer> coins, int amount){
/*        System.out.println(amount+","+coins);*/
        if(amount < 0) return 0;
        else if(amount > 0 && coins.isEmpty()) return 0;
        else if(amount == 0 && coins.isEmpty()) return 1;
        else{
            int left = coinChangeRecursion(coins, amount-coins.get(coins.size()-1));
            int lastElement = coins.remove(coins.size()-1);
            int right = coinChangeRecursion(coins, amount);
            coins.add(lastElement);
            return left + right;
        }
    }


    private static int coinChangeDPMemorization(List<Integer> coins, int amount, Map<String,Integer> cache){
        /*        System.out.println(amount+","+coins);*/
        if(amount < 0) return 0;
        else if(amount > 0 && coins.isEmpty()) return 0;
        else if(amount == 0 && coins.isEmpty()) return 1;
        else{
            String key = amount+","+coins.size();
            if(cache.containsKey(key)) return cache.get(key);
            int left = coinChangeDPMemorization(coins, amount-coins.get(coins.size()-1), cache);
            int lastElement = coins.remove(coins.size()-1);
            int right = coinChangeDPMemorization(coins, amount, cache);
            coins.add(lastElement);
            cache.put(key, (left+right));
            return cache.get(key);
        }
    }


    private static int coinChangeDPTabulation(List<Integer> coins, int amount){
        int[] dp = new int[amount+1];
        dp[0] = 1;
        for(int coin: coins){
            for(int c1=1; c1<dp.length;c1++){
                dp[c1] += (c1-coin) >=0 ? dp[c1-coin] : 0;
            }
        }
/*        System.out.println(Arrays.toString(table));*/
        return dp[amount];
    }


    private static int coinChangeDPTabulationV2(List<Integer> coins, int amount){
        int[] dp = new int[amount+1];
        dp[0] = 1;
        for(int coin: coins){
            for(int c1=coin; c1<dp.length;c1++){
                dp[c1] += dp[c1-coin];
            }
        }
        /*        System.out.println(Arrays.toString(table));*/
        return dp[amount];
    }

}
