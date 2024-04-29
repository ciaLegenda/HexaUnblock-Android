package com.example.simpleclickinteraction2;

import android.util.Pair;
import java.util.ArrayList;
import java.util.TreeSet;

public class Solver {
    public static final int MAX_ITERATIONS_HINT = 500;
    public static final int MAX_ITERATIONS_GENERATE = 300;
    private TreeSet<GameState> gameStates;
    Solver(Table table,Block[] blocks){
        gameStates = new TreeSet<GameState>();
        gameStates.add(new GameState(table,blocks));
    }

    public ArrayList<Pair<Integer,Integer>> solve(int maxIterations){
        int iterations = 0;
        do{
            GameState current = gameStates.first();
            if(current.table.isComplete())
                return current.moves;
            for(int i=1;i<current.blocks.length;i++) {
                if (current.blocks[i].isLegalMove(1)) {
                    GameState next = new GameState(current);
                    next.addMove(i,1);
                    gameStates.add(next);
                }
                if (current.blocks[i].isLegalMove(-1)) {
                    GameState next = new GameState(current);
                    next.addMove(i,-1);
                    gameStates.add(next);
                }
            }


            GameState testedState = new GameState(gameStates.pollFirst());
            testedState.setTested(true);
            gameStates.add(testedState);
            iterations++;
        }while(! gameStates.first().isTested() && iterations<maxIterations);
        return null;
    }

}
