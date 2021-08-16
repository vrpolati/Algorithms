package recursion_dp_memorization_dp_tabulation.epi;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TowersOfHanoi {

    public static void main(String[] args) {
        TowersOfHanoi toh = new TowersOfHanoi();
        toh.computeTowerOfHanoi(3);
    }

    private void computeTowerOfHanoi(int ringCount){
        List<Queue<Integer>> pegs = new ArrayList<>();
        for(int peg=0; peg <3; peg++){
            pegs.add(new LinkedList<Integer>());
        }

        for(int c1=ringCount; c1 >= 1;c1--){
            pegs.get(0).add(c1);
        }
        towerOfHanoiSteps(ringCount, pegs, 0,1,2);
    }

    private void towerOfHanoiSteps(int ringsToMove, List<Queue<Integer>> pegs, int fromPeg, int toPeg, int usePeg){
        if(ringsToMove > 0){
            towerOfHanoiSteps(ringsToMove-1, pegs, fromPeg, usePeg, toPeg);
            System.out.println("R"+ringsToMove+"-> P"+toPeg);
            towerOfHanoiSteps(ringsToMove-1, pegs, usePeg, toPeg, fromPeg);
        }
    }


}
