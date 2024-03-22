package com.example.simpleclickinteraction2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Region;
import android.util.Log;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;

public class CanvasController extends View {
    private Table table;

    private HexDrawer hexDrawer;
    private boolean isDragging = false;

    public CanvasController(Context context) {
        super(context);
        ConfigFileReader reader = new ConfigFileReader(context);
        Pair<Integer,Integer> dim = reader.readDimensions();
        int[][] contents = reader.readTaleContents();
        table = new Table(dim,contents);
        hexDrawer = new HexDrawer(dim);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        hexDrawer.setViewPx(getHeight(),getWidth());
        super.onDraw(canvas);
        table.draw(canvas,hexDrawer);
    }

/*
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float touchX = event.getX();
        float touchY = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // Check if touch is inside the hexagon
                if (hexagonRegion.contains((int) touchX, (int) touchY)) {
                    isDragging = true;
                    lastTouchX = touchX;
                    lastTouchY = touchY;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (isDragging) {
                    float dx = touchX - lastTouchX;
                    float dy = touchY - lastTouchY;
                    hexagonPath.offset(dx, dy);
                    centerX += dx;
                    centerY += dy;
                    hexagonRegion = new Region();
                    hexagonRegion.setPath(hexagonPath, new Region((int) (centerX - hexagonSize), (int) (centerY - hexagonSize), (int) (centerX + hexagonSize), (int) (centerY + hexagonSize)));
                    lastTouchX = touchX;
                    lastTouchY = touchY;
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_UP:
                isDragging = false;
                break;
        }
        return true;
    }
*/
}
