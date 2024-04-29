package com.example.simpleclickinteraction2;

public class CanvasClickChecker {
    private int tableWidthHex;
    private Vect2D [][]centers;
    private int xOrigin;
    private int yOrigin;
    private int hexagonSize;

    public Vect2D clickedCell(float x,float y){
        double minDist = 100000000;
        Vect2D res = new Vect2D(0,0);
        for(int i=0;i<tableWidthHex;i++) {
            for (int j = 0; j < tableWidthHex; j++) {
                double dist = Math.pow(centers[i][j].i - x, 2) + Math.pow(centers[i][j].j - y, 2);
                if (dist < minDist) {
                    minDist = dist;
                    res = new Vect2D(i, j);
                }
            }
        }
        return res;
    }
    CanvasClickChecker(int tableWidthHex){
        centers = new Vect2D[tableWidthHex][tableWidthHex];
        this.tableWidthHex = tableWidthHex;
    }

    public void setViewPx(int view_height_px,int view_width_px){
        hexagonSize = (int) ((view_width_px - 50) / ((tableWidthHex + 1) * 2 * Math.sin(Math.PI / 3)));
        this.xOrigin = (int) (hexagonSize*Math.sin(Math.PI/3))+20;
        this.yOrigin = view_height_px/2;
        initialiseCenters();
    }

    private void initialiseCenters(){
        for(int i=0;i<tableWidthHex;i++)
            for(int j=0;j<tableWidthHex;j++) {
                int x = (int)(xOrigin + 2*hexagonSize*(float)Math.cos(Math.PI / 3)*(j+i));
                int y = (int)(yOrigin + 2*hexagonSize*(float)Math.sin(Math.PI/3)*(i-j));
                centers[i][j] = new Vect2D(x,y);
            }
    }

}
