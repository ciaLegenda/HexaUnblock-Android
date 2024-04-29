package com.example.simpleclickinteraction2;

import java.util.Objects;

public class Vect2D {
    public int i,j;
    public Vect2D() {
        this.i = 0;
        this.j = 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vect2D vect2D = (Vect2D) o;
        return i == vect2D.i && j == vect2D.j;
    }

    public static Vect2D directionVect(int dir){
        switch (dir){
            case 0:
                return new Vect2D(1,0);
            case 1:
                return new Vect2D(1,1);
            case 2:
                return new Vect2D(0,1);
            default:
                return new Vect2D(0,0);
        }
    }

    public Vect2D(int i, int j) {
        this.i = i;
        this.j = j;
    }
    public Vect2D add(Vect2D v){
        Vect2D res = new Vect2D();
        res.i = this.i + v.i;
        res.j = this.j + v.j;
        return res;
    }
    public Vect2D subtract(Vect2D v){
        Vect2D res = new Vect2D();
        res.i = this.i - v.i;
        res.j = this.j - v.j;
        return res;
    }

    public Vect2D multiply(int a){
        Vect2D res = new Vect2D();
        res.i = this.i * a;
        res.j = this.j * a;
        return res;
    }
}
