package recursion_dp_memorization_dp_tabulation;

import java.util.Arrays;
import java.util.Map;

/*  https://practice.geeksforgeeks.org/problems/trapping-rain-water-1587115621/1/?category[]=Dynamic%20Programming&category[]=Dynamic%20Programming&problemType=functional&page=1&sortBy=submissions&query=category[]Dynamic%20ProgrammingproblemTypefunctionalpage1sortBysubmissionscategory[]Dynamic%20Programming*/
public class TrappingRainWater {

    public static void main(String[] args) {
        int[][] inputs = {
                {3,0,0,2,0,4}, // #10
                {7,4,0,9}, // #10
                {6,9,9}, // #0
                {1,1,5,2,7,6,1,4,2,3} // #7

        };
        for(int[] blocks: inputs){
            System.out.println("Version1, O(n) space: "+trappingWater(blocks, blocks.length));
            System.out.println("Version2, O(1) space: "+trappingWaterV2(blocks, blocks.length));
        }
    }

    private static int trappingWater(int arr[], int n) {
        int[] rightMax = new int[n];
        for(int c1=n-2; c1 >= 0; c1--){
            rightMax[c1] = Math.max(rightMax[c1+1], arr[c1+1]);
        }
        int leftMaxSoFar = arr[0];
        int trapped = 0;
        for(int c1=1; c1<n; c1++){
            leftMaxSoFar = Math.max(leftMaxSoFar, arr[c1-1]);
            int trappedHere = Math.min(leftMaxSoFar, rightMax[c1]);
            trappedHere = trappedHere>arr[c1] ? trappedHere-arr[c1]:0;
            trapped = trapped + trappedHere;
        }
        return trapped;
    }

    private static int trappingWaterV2(int arr[], int n) {
        if(arr.length < 3) return 0;
        int waterTrapped = 0;
        int leftP = 1;
        int rightP = n-2;
        int leftMaxSoFar = arr[0];
        int rightMaxSoFar = arr[n-1];
        while(leftP <= rightP){
            if(leftMaxSoFar < rightMaxSoFar){
                waterTrapped += leftMaxSoFar > arr[leftP] ? (leftMaxSoFar-arr[leftP]) : 0;
                leftMaxSoFar = Math.max(leftMaxSoFar, arr[leftP]);
                leftP = leftP+1;
            }else{
                waterTrapped += rightMaxSoFar > arr[rightP] ? (rightMaxSoFar-arr[rightP]) : 0;
                rightMaxSoFar = Math.max(rightMaxSoFar, arr[rightP]);
                rightP = rightP-1;
            }
        }
        return waterTrapped;
    }

}
