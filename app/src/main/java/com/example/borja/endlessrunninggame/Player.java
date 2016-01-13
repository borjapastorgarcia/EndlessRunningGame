package com.example.borja.endlessrunninggame;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by borja on 13/01/2016.
 */
public class Player {
    static int x;
    static int y;
    static float gravity = (float) 1.2;
    static float vspeed = 1;
    static int playerheight;
    static int playerwidth;
    static int jumppower=-10;
    Bitmap bmp;
    GameView gameView;
    public Player(GameView gameView, Bitmap bmp, int x, int y){
        this.x=x;
        this.y=y;
        this.gameView=gameView;
        this.bmp=bmp;
        playerheight=bmp.getHeight();
        playerwidth=bmp.getWidth();
    }
    public void update(){
        checkground();
    }
    public void checkground(){
        if(y<gameView.getHeight()-64-playerheight) {
            vspeed += gravity;
            if (y > gameView.getHeight() - 64 - playerheight - vspeed) {
                vspeed = gameView.getHeight() - 64 - y - playerheight;
            }
        }
        else
        if(vspeed>0){
            y=gameView.getHeight()-64-playerheight;
            vspeed=0;
        }
            y+=vspeed;
    }
    public void onTouch(){
        if(y>=gameView.getHeight()-64-playerheight)
            vspeed=jumppower;
    }
    public void onDraw(Canvas c){
        update();
        c.drawBitmap(bmp,x,y,null);
    }
}
