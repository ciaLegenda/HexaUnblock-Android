package com.example.simpleclickinteraction2;

import android.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;

public class GameState implements Comparable<GameState>{
    private boolean tested=false;
    public Table table;
    public Block[] blocks;
    public ArrayList<Pair<Integer,Integer>> moves;
    void addMove(int blockId,int dir){
        this.moves.add(new Pair<Integer,Integer>(blockId,dir));
        this.blocks[blockId].move(dir);
    }
    public GameState(Table table, Block[] blocks, ArrayList<Pair<Integer, Integer>> moves) {
        this.table = new Table(table);
        this.blocks = new Block[blocks.length];
        for(int i=1;i< blocks.length;i++) {
            this.blocks[i] = new Block(blocks[i]);
            this.blocks[i].table = this.table;
        }
        this.moves = new ArrayList<>(moves.size());
        for(int i=0;i<moves.size();i++)
            this.moves.add(new Pair<Integer, Integer>(moves.get(i).first,moves.get(i).second));
    }
    public GameState(Table table, Block[] blocks) {
        this(table,blocks,new ArrayList<Pair<Integer,Integer>>());
    }
    public GameState(GameState gameState) {
        this(gameState.table, gameState.blocks,gameState.moves);
    }

    @Override
    public int compareTo(GameState o) {
        //placing non tested games first
        if(!this.tested && o.tested)
            return -1;
        if(this.tested && !o.tested)
            return 1;
        //placing the games with the fewest moves first
        if(this.moves.size() != o.moves.size())
            return this.moves.size() - o.moves.size();
        //placing the games with the red block to the left first
        if(this.blocks[1].getCoord().i != o.blocks[1].getCoord().i)
            return this.blocks[1].getCoord().i - o.blocks[1].getCoord().i;
        return this.table.compareTo(o.table);
    }

    public void setTested(boolean tested) {
        this.tested = tested;
    }

    public boolean isTested(){
        return tested;
    }
}