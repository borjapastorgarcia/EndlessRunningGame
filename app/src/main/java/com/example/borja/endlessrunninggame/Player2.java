package com.example.borja.endlessrunninggame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

/**
 * Created by borja on 14/01/2016.
 */
public class Player2{

        static int x;
        static int y;
        static float gravity = (float) 1.2;
        static float vspeed = 1;
        static int playerheight;
        static int playerwidth;
        static int jumppower=-25;
        private double mCurrentFrame=0;
        private int mColumnWidth=7;
        Bitmap bmp;
        GameView gameView;
        private int width, height;
        private int mColumnHeight=1;
        public Player2(GameView gameView, Bitmap bmp, int x, int y){
            this.x=x;
            this.y=y;
            this.gameView=gameView;
            this.bmp=bmp;
            this.width=bmp.getWidth()/mColumnWidth;
            this.height=bmp.getHeight()/mColumnHeight;

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
            int srcX=(int)mCurrentFrame*width;
            Rect src=new Rect(srcX,0,srcX+width,200);
            Rect dst=new Rect((int)x,(int)y,(int)x+width,(int)y+200);
            c.drawBitmap(bmp,src,dst,null);
        }

        public Rect getBounds(){
            return new Rect(this.x,this.y,this.x+width,this.y+height);
        }
}
