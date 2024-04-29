package com.example.simpleclickinteraction2;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.TreeSet;

public class Level {
    private int []colors;
    private final int maxIterations=5;
    private Block[] blocks;
    private Table table;

    Level(Context context){
        TypedArray ta = context.getResources().obtainTypedArray(R.array.block_colors);
        this.colors = new int[ta.length()];
        for (int i = 0; i < ta.length(); i++) {
            colors[i] = ta.getColor(i, 0);
        }
        ta.recycle();
    }
    public void generate(int tableWidth,int noOfBlocks){
        int iter = 0;
        ArrayList<Pair<Integer, Integer>> solution;
        do {
            generateSolved(tableWidth,noOfBlocks);
            shuffle();
            Solver solver = new Solver(table,blocks);
            solution = solver.solve(Solver.MAX_ITERATIONS_GENERATE);
            iter++;
        }while(solution != null && solution.size() < 15 && iter < maxIterations);
    }
    private void generateSolved(int tableWidth,int noOfBlocks) {
        int iter = 0;
        boolean complete = false;
        do {
            this.table = new Table(tableWidth);
            blocks = new Block[noOfBlocks];
            //the red block
            blocks[1] = new Block(1, new Vect2D(0, 0), 0xffff0000, 3, 1, table, true);
            //the other ones
            Random rand = new Random();
            for (int i = 2; i < blocks.length; i++) {
                do {
                    Vect2D coord = table.getEmptyCell();
                    int orientation = rand.nextBoolean() ? 0 : 2;
                    int len = 2 + rand.nextInt(table.width / 3);
                    blocks[i] = new Block(i, coord, colors[rand.nextInt(colors.length)], len, orientation, table, false);
                    iter++;
                } while (!blocks[i].legalPlacement);
            }
        }while(iter < noOfBlocks*3 && !complete);
    }

    private void shuffle(){
        Random rand = new Random();
        for(int i=0;i<1000;i++){
            int blockId = rand.nextInt(blocks.length-2) + 1;
            int dir = rand.nextBoolean() ? 1 : -1;
            if(blocks[blockId].isLegalMove(dir))
                blocks[blockId].move(dir);
        }
    }
    public Block[] getBlocks() {
        return blocks;
    }

    public Table getTable() {
        return table;
    }
}
