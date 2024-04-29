package com.example.simpleclickinteraction2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

import androidx.core.content.ContextCompat;


public class HexDrawer {
    private int xOrigin,yOrigin,hexagonSize,tableWidthHex;
    private Paint fill, stroke;
    Context context;
    HexDrawer(int tableWidthHex,Context context) {
        this.tableWidthHex = tableWidthHex;
        this.context = context;
        fill = new Paint();
        stroke = new Paint();
        stroke.setStyle(Paint.Style.STROKE);
    }

    public void setViewPx(int view_height_px,int view_width_px){
        hexagonSize = (int)((view_width_px-50)/((tableWidthHex+1)*2*Math.sin(Math.PI/3)));
        this.xOrigin = (int) (hexagonSize*Math.sin(Math.PI/3))+20;
        this.yOrigin = view_height_px/2;
        stroke.setStrokeWidth((float)hexagonSize/10f);
    }

    public void draw(Canvas canvas, Vect2D coord,int color,Boolean isSelected){
        Path hexagonPath = calculateHexagonPath(coord);
        fill.setColor(color);
        if(isSelected)
            stroke.setColor(ContextCompat.getColor(context,R.color.yellow));
        else
            stroke.setColor(ContextCompat.getColor(context,R.color.black));
        canvas.drawPath(hexagonPath, fill);
        canvas.drawPath(hexagonPath, stroke);
    }
    public void draw(Canvas canvas,int i,int j) {
        Vect2D coord = new Vect2D(i,j);
        draw(canvas,coord, ContextCompat.getColor(context,R.color.table_background),false);
    }

    public void drawSelector(Canvas canvas,Vect2D coord){
        float centerX = xOrigin + 2*hexagonSize*(float)Math.cos(Math.PI / 3)*(coord.i+coord.j);
        float centerY = yOrigin + 2*hexagonSize*(float)Math.sin(Math.PI/3)*(coord.i-coord.j);
        Paint selectorPaint = new Paint();
        selectorPaint.setColor(Color.BLACK);
        canvas.drawCircle(centerX,centerY,15,selectorPaint);
    }
    private Path calculateHexagonPath(Vect2D coord) {
        int i=coord.i,j=coord.j;
        Path hexagonPath = new Path();
        hexagonPath.reset();
        float angle,centerX,centerY;
        centerX = xOrigin + 2*hexagonSize*(float)Math.cos(Math.PI / 3)*(j+i);
        centerY = yOrigin + 2*hexagonSize*(float)Math.sin(Math.PI/3)*(i-j);
        for (int k = 0;  k< 6; k++) {
            angle = (float) (Math.PI / 3.0 * k+ Math.PI/6);
            float x,y;
            x = (float) (centerX + hexagonSize * Math.cos(angle));
            y = (float) (centerY + hexagonSize * Math.sin(angle));
            if (k == 0) {
                hexagonPath.moveTo(x,y);
            } else {
                hexagonPath.lineTo(x,y);
            }
        }
        hexagonPath.close();
        return hexagonPath;
    }
}
