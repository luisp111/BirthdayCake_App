package cs301.birthdaycake;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceView;

public class CakeView extends SurfaceView {

    /* These are the paints we'll use to draw the birthday cake below */
    Paint cakePaint = new Paint();
    Paint frostingPaint = new Paint();
    Paint candlePaint = new Paint();
    Paint outerFlamePaint = new Paint();
    Paint innerFlamePaint = new Paint();
    Paint wickPaint = new Paint();

    Paint checkersPaintr = new Paint();
    Paint checkersPaintg = new Paint();

    Paint drawBalloon = new Paint(); // Creating new object for drawBalloon

    Paint textPaint = new Paint();
    /* These constants define the dimensions of the cake.  While defining constants for things
        like this is good practice, we could be calculating these better by detecting
        and adapting to different tablets' screen sizes and resolutions.  I've deliberately
        stuck with hard-coded values here to ease the introduction for CS371 students.
     */
    public static final float cakeTop = 400.0f;
    public static final float cakeLeft = 100.0f;
    public static final float cakeWidth = 1200.0f;
    public static final float layerHeight = 200.0f;
    public static final float frostHeight = 50.0f;
    public static final float candleHeight = 300.0f;
    public static final float candleWidth = 40.0f;
    public static final float wickHeight = 30.0f;
    public static final float wickWidth = 6.0f;
    public static final float outerFlameRadius = 30.0f;
    public static final float innerFlameRadius = 15.0f;


    /**
     * ctor must be overridden here as per standard Java inheritance practice.  We need it
     * anyway to initialize the member variables
     */
    public CakeView(Context context, AttributeSet attrs) {
        super(context, attrs);

        //This is essential or your onDraw method won't get called
        setWillNotDraw(false);
        cake1 = new CakeModel();
        //Setup our palette
        cakePaint.setColor(0xAFC22011);  //violet-red
        cakePaint.setStyle(Paint.Style.FILL);
        frostingPaint.setColor(0xFFFFFACD);  //pale yellow
        frostingPaint.setStyle(Paint.Style.FILL);
        candlePaint.setColor(0xFF32CD32);  //lime green
        candlePaint.setStyle(Paint.Style.FILL);
        outerFlamePaint.setColor(0xFFFFD700);  //gold yellow
        outerFlamePaint.setStyle(Paint.Style.FILL);
        innerFlamePaint.setColor(0xFFFFA500);  //orange
        innerFlamePaint.setStyle(Paint.Style.FILL);
        wickPaint.setColor(Color.BLACK);
        wickPaint.setStyle(Paint.Style.FILL);
        checkersPaintr.setColor(Color.RED);
        checkersPaintr.setStyle(Paint.Style.FILL);
        checkersPaintg.setColor(Color.GREEN);
        checkersPaintg.setStyle(Paint.Style.FILL);
        setBackgroundColor(Color.WHITE);  //better than black default
        textPaint.setColor(Color.RED);
        textPaint.setTextSize(150);
        textPaint.setStyle(Paint.Style.FILL);
        drawBalloon.setColor(Color.BLUE);
    }

    public CakeModel getCakeView() {
        return cake1;
    }

    private CakeModel cake1;


    /**
     * draws a candle at a specified position.  Important:  the left, bottom coordinates specify
     * the position of the bottom left corner of the candle
     */
    public void drawCandle(Canvas canvas, float left, float bottom) {
        canvas.drawRect(left, bottom - candleHeight, left + candleWidth, bottom, candlePaint);

        //draw the outer flame

        if (cake1.Candlelit == true) {
            float flameCenterX = left + candleWidth / 2;

            float flameCenterY = bottom - wickHeight - candleHeight - outerFlameRadius / 3;
            canvas.drawCircle(flameCenterX, flameCenterY, outerFlameRadius, outerFlamePaint);

            //draw the inner flame
            flameCenterY += outerFlameRadius / 3;
            canvas.drawCircle(flameCenterX, flameCenterY, innerFlameRadius, innerFlamePaint);

            //draw the wick
            float wickLeft = left + candleWidth / 2 - wickWidth / 2;
            float wickTop = bottom - wickHeight - candleHeight;
            canvas.drawRect(wickLeft, wickTop, wickLeft + wickWidth, wickTop + wickHeight, wickPaint);
        } else {
            float wickLeft = left + candleWidth / 2 - wickWidth / 2;
            float wickTop = bottom - wickHeight - candleHeight;
            canvas.drawRect(wickLeft, wickTop, wickLeft + wickWidth, wickTop + wickHeight, wickPaint);
        }

    }

    public void drawBalloon(Canvas canvas)  {
        canvas.drawOval(cake1.balloonX - 25, cake1.balloonY + 50, cake1.balloonX + 25, cake1.balloonY - 50, drawBalloon);
        canvas.drawLine(cake1.balloonX + 0, cake1.balloonY + 0, cake1.balloonX + 0, cake1.balloonY + 100, drawBalloon);
    }


    public void drawCheckers(Canvas canvas) {
        canvas.drawRect(cake1.CheckersX-25,cake1.CheckersY-25, cake1.CheckersX, cake1.CheckersY, checkersPaintg);
        canvas.drawRect(cake1.CheckersX,cake1.CheckersY - 25, cake1.CheckersX+25, cake1.CheckersY, checkersPaintr);
        canvas.drawRect(cake1.CheckersX-25,cake1.CheckersY, cake1.CheckersX, cake1.CheckersY+25, checkersPaintr);
        canvas.drawRect(cake1.CheckersX,cake1.CheckersY, cake1.CheckersX+25, cake1.CheckersY+25, checkersPaintg);
    }



    /**
     * onDraw is like "paint" in a regular Java program.  While a Canvas is
     * conceptually similar to a Graphics in javax.swing, the implementation has
     * many subtle differences.  Show care and read the documentation.
     * <p>
     * This method will draw a birthday cake
     */
    @Override
    public void onDraw(Canvas canvas) {
        //top and bottom are used to keep a running tally as we progress down the cake layers
        float top = cakeTop;
        float bottom = cakeTop + frostHeight;

        //Frosting on top
        canvas.drawRect(cakeLeft, top, cakeLeft + cakeWidth, bottom, frostingPaint);
        top += frostHeight;
        bottom += layerHeight;

        //Then a cake layer
        canvas.drawRect(cakeLeft, top, cakeLeft + cakeWidth, bottom, cakePaint);
        top += layerHeight;
        bottom += frostHeight;

        //Then a second frosting layer
        canvas.drawRect(cakeLeft, top, cakeLeft + cakeWidth, bottom, frostingPaint);
        top += frostHeight;
        bottom += layerHeight;

        //Then a second cake layer
        canvas.drawRect(cakeLeft, top, cakeLeft + cakeWidth, bottom, cakePaint);

        //Now a candle in the center
        for(int i = 1; i <= cake1.candlesOnCake;i++){

                 drawCandle(canvas, cakeLeft + (cakeWidth / (cake1.candlesOnCake + 1))*i - (candleWidth/i), cakeTop);

        }//onDraw
        drawText(cake1.CheckersX, cake1.CheckersY, canvas);

    }

    public void drawText( float x, float y, Canvas canvas){
        String xString = Float.toString(x);
        String yString = Float.toString(y);
        String finalText = ("("+ xString + "," + yString+")");
        canvas.drawText(finalText, 1090, 1100, textPaint );
        drawBalloon(canvas);
        drawCheckers(canvas);
    }
}
