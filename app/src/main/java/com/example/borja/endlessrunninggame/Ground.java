package com.example.borja.endlessrunninggame;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by borja on 17/01/2016.
 */
public class Ground {
    private GameView gameView;
    private Bitmap bmp;
    private int x;
    private int y;

    public Ground(GameView gameView, Bitmap bmp, int x, int y){
        this.gameView=gameView;
        this.bmp=bmp;
        this.x=x;
        this.y=y;
    }
    public void update(){

    }
    public void onDraw(Canvas canvas){
        update();
        canvas.drawBitmap(bmp,x, y+gameView.getHeight() - 64,null);
    }
}
