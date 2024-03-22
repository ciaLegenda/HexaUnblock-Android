package com.example.simpleclickinteraction2;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.Pair;

public class HexDrawer {
    private int xOrigin;
    private int yOrigin;
    private int hexagonSize;
    private Paint fill;
    private Paint stroke;
    private int table_height_hex;
    private int table_width_hex;

    HexDrawer(Pair<Integer,Integer> dim) {
        this.table_height_hex = dim.first;
        this.table_width_hex = dim.second;
        initPaint();
    }

    public void setViewPx(int view_height_px,int view_width_px){
        hexagonSize = view_width_px/((table_width_hex-1)*3+2);
        this.xOrigin = hexagonSize;
        this.yOrigin = (int)((view_height_px - table_height_hex/2*Math.sqrt(3.0)*hexagonSize) / 2);
        stroke.setStrokeWidth((float)hexagonSize/10f);
    }

    public void draw(Canvas canvas,int i,int j) {
        Path hexagonPath = calculateHexagonPath(i, j);
        canvas.drawPath(hexagonPath, fill);
        canvas.drawPath(hexagonPath, stroke);
    }
    private Path calculateHexagonPath(int i, int j) {
        Path hexagonPath = new Path();
        hexagonPath.reset();
        float angle,centerX,centerY;
        centerX = xOrigin+hexagonSize*3*(j+(float)(i%2)/2);
        centerY = yOrigin + hexagonSize * (float)Math.sqrt(3) * i / 2;
        for (int k = 0;  k< 6; k++) {
            angle = (float) (Math.PI / 3.0 * k);
            float x,y;
            x = (float) (centerX + hexagonSize * Math.cos(angle) * 0.9);
            y = (float) (centerY + hexagonSize * Math.sin(angle) * 0.9);
            if (k == 0) {
                hexagonPath.moveTo(x,y);
            } else {
                hexagonPath.lineTo(x,y);
            }
        }
        hexagonPath.close();
        return hexagonPath;
    }

    private void initPaint(){
        fill = new Paint();
        fill.setColor(Color.argb(255,250,100,100));
        stroke = new Paint();
        stroke.setStyle(Paint.Style.STROKE);
        stroke.setColor(Color.argb(255,100,20,20));
    }
}
