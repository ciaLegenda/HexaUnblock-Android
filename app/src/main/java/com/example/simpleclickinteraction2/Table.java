package com.example.simpleclickinteraction2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Region;
import android.util.Pair;
import android.view.View;

public class Table{
    private int [][]contents;
    enum cellStatus {WALL,EMPTY}//values grater than 1 are block ids
    private int table_width_hex;
    private int table_height_hex;

    private HexDrawer hexDrawer;

    Table(Pair<Integer,Integer> dimensions, int[][] contents){
        this.contents = contents;
        this.table_height_hex = dimensions.first;
        this.table_width_hex = dimensions.second;
    }

    public void draw(Canvas canvas,HexDrawer drawer){
        for(int i=0;i<table_height_hex;i++){
            for(int j=0;j<table_width_hex;j++){
                if(contents[i][j] > cellStatus.WALL.ordinal()){
                    drawer.draw(canvas,i,j);
                }
            }
        }
    }
}
