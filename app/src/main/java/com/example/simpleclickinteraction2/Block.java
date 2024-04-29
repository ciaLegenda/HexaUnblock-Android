package com.example.simpleclickinteraction2;

import static androidx.core.app.NotificationCompat.getColor;

import android.app.ActionBar;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Region;
import android.util.Pair;

import androidx.core.content.ContextCompat;


import java.util.ArrayList;


public class Block {
    private final int orientation,id,length,color;
    public int highlithedDirection=0;
    private final boolean canIntersectMiddleRow;
    public boolean legalPlacement,isSelected,canMoveForwand,canMoveBack;
    private Vect2D coord,moveVector,backMoveCell,forwardMoveCell;
    private Context context;
    Table table;

    public void setCoord(Vect2D coord) {
        this.coord = coord;
    }

    public Block(int id, Vect2D coord, int color, int length, int orientation, Table table,boolean canIntersectMiddleRow){
        this.id = id;
        this.color = color;
        this.coord = coord;
        this.length = length;
        isSelected = false;
        this.orientation = orientation;
        this.canIntersectMiddleRow = canIntersectMiddleRow;
        moveVector = Vect2D.directionVect(orientation);
        this.table = table;

        legalPlacement = true;
        Vect2D cell = coord;
        for(int k=0;legalPlacement && k<length;k++){
            legalPlacement &= table.isEmptyCell(cell);
            if(!canIntersectMiddleRow && (cell.i==cell.j || orientation==1 && Math.abs(cell.i-cell.j)<2))
                legalPlacement=false;
            cell = cell.add(moveVector);
        }

        if(legalPlacement) {
            cell = coord;
            for (int k = 0; k < length; k++) {
                table.setCellContent(cell, id);
                cell = cell.add(moveVector);
            }
        }
    }

    Block(Block block){
        this(block.id,block.coord, block.color, block.length, block.orientation,block.table,block.canIntersectMiddleRow);
    }

    public void update(Vect2D clickedCell, ArrayList<Pair<Integer,Integer>> moves){
        if(canMoveForwand && clickedCell.equals(forwardMoveCell))
            move(1,moves);
        if(canMoveBack && clickedCell.equals(backMoveCell))
            move(-1,moves);
        highlithedDirection=0;
    }

    public void draw(Canvas canvas,HexDrawer hexDrawer){
        //the block itself
        for(int k=0;k<length;k++){
            Vect2D cell = moveVector;
            cell = cell.multiply(k);
            cell = cell.add(coord);
            hexDrawer.draw(canvas,cell,color,isSelected);
        }

        forwardMoveCell = coord.add(moveVector.multiply(length));
        canMoveForwand = table.isEmptyCell(forwardMoveCell);
        backMoveCell = coord.subtract(moveVector);
        canMoveBack = table.isEmptyCell(backMoveCell);
        //draw the highlighted direction
        if(highlithedDirection>0)
            hexDrawer.draw(canvas,forwardMoveCell,ContextCompat.getColor(hexDrawer.context,R.color.yellow),false);
        if(highlithedDirection<0)
            hexDrawer.draw(canvas,backMoveCell, ContextCompat.getColor(hexDrawer.context,R.color.yellow),false);
        //draw the possible movement direction
        if(isSelected){
            if(canMoveForwand)
                hexDrawer.drawSelector(canvas,forwardMoveCell);
            if(canMoveBack)
                hexDrawer.drawSelector(canvas,backMoveCell);
        }
    }

    public void setHighlithedDirection(int highlithedDirection) {
        this.highlithedDirection = highlithedDirection;
        this.isSelected = true;
    }

    public Boolean isLegalMove(int direction){
        Vect2D cellToCheck = coord;
        if(direction>0)
            cellToCheck = cellToCheck.add(moveVector.multiply(length));
        else
            cellToCheck = cellToCheck.subtract(moveVector);
        return table.isEmptyCell(cellToCheck);
    }

    public void move(int direction){
        Vect2D auxCell;
        if(direction>0){
            table.setCellContent(coord,0);
            auxCell = coord.add(moveVector.multiply(length));
            table.setCellContent(auxCell,id);
            coord = coord.add(moveVector);
        }else{
            auxCell = coord.add(moveVector.multiply(length-1));
            table.setCellContent(auxCell,0);
            auxCell = coord.subtract(moveVector);
            table.setCellContent(auxCell,id);
            coord = coord.subtract(moveVector);
        }

    }
    public void move(int direction, ArrayList<Pair<Integer,Integer>> moves) {
        moves.add(new Pair<>(this.id, direction));
        move(direction);
    }
    public Vect2D getCoord() {
        return coord;
    }
}
