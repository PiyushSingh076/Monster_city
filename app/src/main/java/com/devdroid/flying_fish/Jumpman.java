package com.devdroid.flying_fish;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

public class Jumpman extends View {
    AlertDialog.Builder builder2;
    private int manX=200;
    private int manY=550;
    private int count=3;
    private int manSpeed;
    private int canvasWidth;
    private int canvasHeight;
    private int score;
    private boolean touch=false;

    private int monster1X,monster1Y,monster1Speed=16;
    private Paint monster1Paint= new Paint();

    private int monster2X,monster2Y,monster2Speed=16;
    private Paint monster2Paint= new Paint();


    private int coinX,coinY,coinSpeed=16;
    private Paint coinPaint= new Paint();

    private int coin2X,coin2Y,coin2Speed=16;
    private Paint coin2Paint= new Paint();

    private int houseX,houseY,houseSpeed=16;
    private Paint housePaint= new Paint();

    private Bitmap man;
    private Bitmap monster1;
    private Bitmap monster2;

    private Bitmap coin;
    private Bitmap coin2;

    private Bitmap house;

    private Bitmap backgroundImage;

    private Paint scorePaint= new Paint();

    private Bitmap life[]= new Bitmap[2];

    private Bitmap life2[]= new Bitmap[2];
    public Jumpman(Context context) {
        super(context);
        man= BitmapFactory.decodeResource(getResources(),R.drawable.man2);
        monster1= BitmapFactory.decodeResource(getResources(),R.drawable.ogre);
        monster2= BitmapFactory.decodeResource(getResources(),R.drawable.cthullu);
        coin=BitmapFactory.decodeResource(getResources(),R.drawable.dollar);
        coin2=BitmapFactory.decodeResource(getResources(),R.drawable.dollar);
        house=BitmapFactory.decodeResource(getResources(),R.drawable.house);
        backgroundImage=BitmapFactory.decodeResource(getResources(),R.drawable.scary);

        scorePaint.setColor(Color.WHITE);
        scorePaint.setTextSize(80);
        scorePaint.setTypeface(Typeface.DEFAULT_BOLD);
        scorePaint.setAntiAlias(true);

        life[0]=BitmapFactory.decodeResource(getResources(), R.drawable.heart1);
        life2[0]=Bitmap.createScaledBitmap(life[0], 80, 80, false);
        life[1]=BitmapFactory.decodeResource(getResources(), R.drawable.brokenheart);
        life2[1]=Bitmap.createScaledBitmap(life[1], 80, 80, false);

        score=0;
        coinY=500;

    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvasWidth= canvas.getWidth();
        canvasHeight=canvas.getHeight();

        canvas.drawBitmap(backgroundImage,0,0,null);

        int minmanY=man.getHeight();
        int maxmanY=670;
        manY= manY+manSpeed;

        if(manY<minmanY){
            manY=minmanY;
        }

        if(manY>maxmanY){
            manY=maxmanY;
        }
        manSpeed+=2;

        if (touch){
            canvas.drawBitmap(man,manX,manY,null);
            touch=false;
        }
        else{
            canvas.drawBitmap(man,manX,manY,null);
        }

        manX=manX+12;
        if(manX>canvasWidth-400){
            manX=0;
            manY=670;
        }

        monster1X=monster1X-monster1Speed;

        if (hitcoin(monster1X,monster1Y+50)){
            manX=0;
            monster1X=-500;
            count-=1;
        }
        if(monster1X<manX-400){
            monster1X=canvasWidth+800;
            monster1Y=670;
        }
        canvas.drawBitmap(monster1,monster1X,monster1Y,null);

        if(monster2X<manX-400){
            monster2X=canvasWidth+3000;
            monster2Y=710;
        }
        canvas.drawBitmap(monster1,monster1X,monster1Y,null);

        houseX=houseX-houseSpeed;
        if(houseX<manX-400) {
            houseX = canvasWidth +18000;
            houseY = 670;
        }

        canvas.drawBitmap(house,houseX,houseY,null);

        if (hitcoin(houseX,houseY+50)){
            manX=0;
            houseX=-500;
            Intent intent=new Intent(getContext(), MainActivity4.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            // for intent to work inside the loop
            getContext().startActivity(intent);
        }

        monster2X=monster2X-monster2Speed;

        if (hitcoin(monster2X,monster2Y+90)){
            manX=0;
            monster2X=-500;
            count-=1;
        }


        if(count==0){
            manX=-200;
            monster1Speed=0;
            monster2Speed=0;
            coinX=-200;
            coin2X=-200;
            coinSpeed=0;
            coin2Speed=0;
            Intent intent=new Intent(getContext(), MainActivity3.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            getContext().startActivity(intent);
        }


        if(monster2X<manX-450){
            monster2X=canvasWidth+3000;
            monster2Y=710;
        }
        canvas.drawBitmap(monster2,monster2X,monster2Y,null);

        coinX=coinX-coinSpeed;

        if(hitcoin(coinX,coinY)){
            score+=10;
            coinX=-500;
        }

        if(coinX<manX-900){
            coinX=canvasWidth+1500;
            coinY=720;
        }
        canvas.drawBitmap(coin,coinX,coinY,null);

        coin2X=coin2X-coin2Speed;

        if(hitcoin(coin2X,coin2Y)){
            score+=10;
            coin2X=-500;
        }
        if(coin2X<manX-900){
            coin2X=canvasWidth+2200;
            coin2Y=420;
        }
        canvas.drawBitmap(coin2,coin2X,coin2Y,null);

        if(manX>canvasWidth-500){
            monster1X=canvasWidth+1000;
            monster1Y=670;
        }
        canvas.drawBitmap(monster1,monster1X,monster1Y,null);

        canvas.drawText("Score: "+score,40,80,scorePaint);

        for(int i=0;i<3;i++){
            int x=(int)(580+ life2[0].getWidth()*1.5*i);
            int y=10;

            if (i<count){
                canvas.drawBitmap(life2[0],x,y,null);
            }
            else{
                canvas.drawBitmap(life2[1],x,y,null);
            }
        }

    }
    public boolean hitcoin(int x,int y){
        if(manX<x && x<(manX + man.getWidth()) && manY<y && y<(manY + man.getHeight())){
            return true;
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction()== MotionEvent.ACTION_DOWN){

            touch=true;

            manSpeed=-27;
        }
        return true;
    }
}
