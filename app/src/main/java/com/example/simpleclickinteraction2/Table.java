package com.example.simpleclickinteraction2;

import android.app.ActionBar;
import android.graphics.Canvas;

import java.util.Random;

public class Table implements Comparable<Table>{
    private int [][]contents;
    private final int WALL= -1,EMPTY=0;
    public int width;

    Table(int width){
        this.width = width;
        this.contents = new int[width][width];
        for(int i=0;i<width;i++)
            for(int j=0;j<width;j++) {
                if(i-j > width/2 || j-i > width/2)
                    contents[i][j] = WALL;
                else
                    contents[i][j]=EMPTY;
            }
    }
    Table(Table oldTable){
        this.width = oldTable.width;
        this.contents = new int[width][width];
        for(int i=0;i<width;i++)
            for(int j=0;j<width;j++)
                this.contents[i][j] = oldTable.contents[i][j];
    }
    public void draw(Canvas canvas,HexDrawer drawer){
        for(int i=0;i<width;i++){
            for(int j=0;j<width;j++){
                if(contents[i][j] > WALL){
                    drawer.draw(canvas,i,j);
                }
            }
        }
    }

    public int getCellContent(Vect2D coord){
        if(coord.i>=width || coord.i<0)
            return WALL;
        if(coord.j>=width || coord.j<0)
            return WALL;
        return contents[coord.i][coord.j];
    }

    public Boolean isEmptyCell(Vect2D coord){
        return getCellContent(coord) == EMPTY;
    }
    public void setCellContent(Vect2D coord,int value){
        contents[coord.i][coord.j] = value;
    }

    public Boolean isComplete(){
        for(int i=width-1;i>=0;i--) {
            if (contents[i][i] > 1)
                return false;
            if (contents[i][i] == 1)
                return true;
        }
        return false;
    }

    @Override
    public int compareTo(Table o) {
        for(int i=0;i<width;i++)
            for(int j=0;j<width;j++)
                if(this.contents[i][j] != o.contents[i][j])
                    return this.contents[i][j] - o.contents[i][j];
        return 0;
    }

    public Vect2D getEmptyCell() {
        Random rand = new Random();
        Vect2D firstCoord = new Vect2D(rand.nextInt(width), rand.nextInt(width));
        for (int i = 0; i < width; i++)
            for (int j = 0; j < width; j++) {
                Vect2D coord = firstCoord.add(new Vect2D(i,j));
                if (getCellContent(coord) == EMPTY)
                    return coord;
            }
        return firstCoord;
    }
}
