package com.example.borja.endlessrunninggame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by borja on 13/01/2016.
 */
public class GameView extends SurfaceView {
    private GameLoopThread gameLoopThread;
    private SurfaceHolder surfaceHolder;
    public static int globalXSpeed=15;
    Bitmap playerBmp,player2Bmp,coinBmp;
    private List<Coin>coin=new ArrayList<Coin>();
    private List<Player>player=new ArrayList<Player>();
    private List<Player2>player2=new ArrayList<Player2>();
    public GameView(Context context) {
        super(context);
        gameLoopThread=new GameLoopThread(this);
        surfaceHolder=getHolder();
        surfaceHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                gameLoopThread.setRunning(true);
                gameLoopThread.start();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
            }
        });

        playerBmp= BitmapFactory.decodeResource(getResources(),R.drawable.player);
        player2Bmp= BitmapFactory.decodeResource(getResources(),R.drawable.player2);
        coinBmp= BitmapFactory.decodeResource(getResources(),R.drawable.coin);

        player.add(new Player(this,playerBmp,50,50));
        player2.add(new Player2(this,player2Bmp,50,50));
        coin.add(new Coin(this,coinBmp,120,650));

    }
    public boolean onTouchEvent(MotionEvent e){
        for(Player player1:player)
            player1.onTouch();
        for(Player2 player1:player2)
            player1.onTouch();
        return false;
    }
    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.GRAY);
       /* for(Player player1:player)
            player1.onDraw(canvas);*/
        for(Player2 player1:player2)
            player1.onDraw(canvas);
        for(int i=0;i<coin.size();i++) {
            coin.get(i).onDraw(canvas);
            Rect playerR=player2.get(0).getBounds();
            Rect coinR=coin.get(i).getBounds();
            if(coin.get(i).checkCollission(playerR,coinR)){
                coin.remove(i);
            }
        }
    }


}
