package com.example.simpleclickinteraction2;

import android.content.Context;
import android.util.Log;
import android.util.Pair;

import androidx.annotation.RawRes;

import java.io.BufferedReader;
        import java.io.FileReader;
        import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Objects;
import java.util.Scanner;

public class ConfigFileReader {
    private InputStream raw;
    private Scanner scanner;
    private int width=0;
    private int height=0;
    ConfigFileReader(Context context){
        try {
            raw = context.getResources().openRawResource(R.raw.width5);
            scanner = new Scanner(raw);
        }catch (Exception e) {
            Log.e("INPUT ERR", Objects.requireNonNull(e.getMessage()));
        }
    }
    public Pair<Integer,Integer> readDimensions() {
        try {
           width = scanner.nextInt();
           height = scanner.nextInt();
        }catch (Exception e) {
            Log.e("INPUT ERR", Objects.requireNonNull(e.getMessage()));
        }
        return new Pair<Integer,Integer>(height,width);
    }

    public int[][] readTaleContents() {
        int[][] contents = new int[height][width];
        try {
           for(int i=0;i<height;i++)
               for(int j=0;j<width;j++)
                   contents[i][j] = scanner.nextInt();
        }catch (Exception e) {
            Log.e("INPUT ERR", Objects.requireNonNull(e.getMessage()));
        }
        return contents;
    }
}
