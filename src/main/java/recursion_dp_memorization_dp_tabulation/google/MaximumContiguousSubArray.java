package recursion_dp_memorization_dp_tabulation.google;

import java.util.Objects;

public class MaximumContiguousSubArray {

    public static void main(String[] args) {
        MaximumContiguousSubArray mcs = new MaximumContiguousSubArray();
        int[][] inputs = {
                {1,2,3,-2,5} // #5
        };
        for(int[] input: inputs){
            System.out.println(mcs.maxSubarraySum(input, input.length));
        }
    }

    private int maxSubarraySum(int arr[], int n){
        Integer maxSoFar = null;
        int sumSoFar = 0;
        for(int c1=0; c1<arr.length; c1++){
            if(Objects.isNull(maxSoFar)){
                maxSoFar = arr[c1];
                sumSoFar = maxSoFar;
            }else if(sumSoFar < 0){
                sumSoFar = arr[c1];
            }else sumSoFar += arr[c1];
            maxSoFar = Math.max(maxSoFar, sumSoFar);
        }
        return maxSoFar;
    }
}
