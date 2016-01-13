package com.example.borja.endlessrunninggame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

/**
 * Created by borja on 13/01/2016.
 */
public class Coin {
    private double x, y;
    private Bitmap bmp;
    private  GameView gameView;
    private double mCurrentFrame=0;
    private int mColumnWidth=10;
    private double xSpeed=-GameView.globalXSpeed;
    private int width;
    public void update(){
        x+=xSpeed;
        if(mCurrentFrame>=mColumnWidth-1)
            mCurrentFrame=0;
        else
            mCurrentFrame+=1;
        if(x<0-width)
            x=gameView.getWidth()+width;
        mCurrentFrame+=1%(mColumnWidth-1);
    }
    public void onDraw(Canvas canvas){
        update();
        int srcX=(int)mCurrentFrame*width;
        Rect src=new Rect(srcX,0,srcX+width,80);
        Rect dst=new Rect((int)x,(int)y,(int)x+width,(int)y+80 );
        canvas.drawBitmap(bmp,src,dst,null);
    }
    public Coin(GameView gameView, Bitmap bmp, float x, float y){
        this.gameView=gameView;
        this.bmp=bmp;
        this.y=y;
        this.x=x;
        this.width=bmp.getWidth()/mColumnWidth;

    }
}
