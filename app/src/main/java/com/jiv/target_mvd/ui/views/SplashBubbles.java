package com.jiv.target_mvd.ui.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Region;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import com.jiv.target_mvd.R;


public class SplashBubbles extends View {
    private Path mIntersectionPath;
    private Paint mLeftBubblePaint;
    private Paint mIntersectionPaint;
    private Paint mRightBubblePaint;

    public SplashBubbles(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float leftBubbleCenterX = canvas.getWidth() * 0.2f;
        float leftBubbleCenterY = canvas.getHeight() * 0.95f;
        float leftBubbleRadius = canvas.getWidth() * 0.7f;

        float rightBubbleCenterX = canvas.getWidth() * 1.1f;
        float rightBubbleCenterY = canvas.getHeight();
        float rightBubbleRadius = canvas.getWidth() * 0.65f;

        canvas.drawCircle(leftBubbleCenterX,
                          leftBubbleCenterY,
                          leftBubbleRadius,
                          mLeftBubblePaint);

        canvas.drawCircle(rightBubbleCenterX,
                          rightBubbleCenterY,
                          rightBubbleRadius,
                          mRightBubblePaint);

        mIntersectionPath.addCircle(rightBubbleCenterX,
                                    rightBubbleCenterY,
                                    rightBubbleRadius,
                                    Path.Direction.CW);

        canvas.clipPath(mIntersectionPath, Region.Op.INTERSECT);

        canvas.drawCircle(leftBubbleCenterX,
                          leftBubbleCenterY,
                          leftBubbleRadius,
                          mIntersectionPaint);
    }

    private void init(Context context) {
        mIntersectionPath = new Path();

        mLeftBubblePaint = new Paint();
        mLeftBubblePaint.setColor(ContextCompat.getColor(context, R.color.splashLeftBubble));
        mLeftBubblePaint.setStyle(Paint.Style.FILL_AND_STROKE);

        mRightBubblePaint = new Paint();
        mRightBubblePaint.setColor(ContextCompat.getColor(context, R.color.splashRightBubble));
        mRightBubblePaint.setStyle(Paint.Style.FILL_AND_STROKE);

        mIntersectionPaint = new Paint();
        mIntersectionPaint.setColor(ContextCompat.getColor(context, R.color.splashIntersection));
        mIntersectionPaint.setStyle(Paint.Style.FILL_AND_STROKE);
    }
}
