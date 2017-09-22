package com.jiv.target_mvd.ui.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Region;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import com.jiv.target_mvd.R;


public class BubblesView extends View {
    private Path mIntersectionPath;
    private Paint mLeftBubblePaint;
    private Paint mIntersectionPaint;
    private Paint mRightBubblePaint;
    private float mLeftBubbleCenterX;
    private float mLeftBubbleCenterY;
    private float mLeftBubbleRadius;
    private int mLeftBubbleColor;
    private float mRightBubbleCenterX;
    private float mRightBubbleCenterY;
    private float mRightBubbleRadius;
    private int mRightBubbleColor;
    private int mIntersectionColor;

    public BubblesView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float leftBubbleCenterX = canvas.getWidth() * mLeftBubbleCenterX;
        float leftBubbleCenterY = canvas.getHeight() * mLeftBubbleCenterY;
        float leftBubbleRadius = canvas.getWidth() * mLeftBubbleRadius;

        float rightBubbleCenterX = canvas.getWidth() * mRightBubbleCenterX;
        float rightBubbleCenterY = canvas.getHeight() * mRightBubbleCenterY;
        float rightBubbleRadius = canvas.getWidth() * mRightBubbleRadius;

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

    private void init(Context context, AttributeSet attrs) {
        TypedArray attributes = context.getTheme().obtainStyledAttributes(attrs, R.styleable.BubblesViewAttr,0,0);

        mLeftBubbleCenterX = attributes.getFloat(R.styleable.BubblesViewAttr_leftBubbleCenterX, 0);
        mLeftBubbleCenterY = attributes.getFloat(R.styleable.BubblesViewAttr_leftBubbleCenterY, 0);
        mLeftBubbleRadius = attributes.getFloat(R.styleable.BubblesViewAttr_leftBubbleRadius, 0);
        mLeftBubbleColor = attributes.getColor(R.styleable.BubblesViewAttr_leftBubbleColor,
                ContextCompat.getColor(context, R.color.bubblesViewLeftColor));

        mRightBubbleCenterX = attributes.getFloat(R.styleable.BubblesViewAttr_rightBubbleCenterX, 0);
        mRightBubbleCenterY = attributes.getFloat(R.styleable.BubblesViewAttr_rightBubbleCenterY, 0);
        mRightBubbleRadius = attributes.getFloat(R.styleable.BubblesViewAttr_rightBubbleRadius, 0);
        mRightBubbleColor = attributes.getColor(R.styleable.BubblesViewAttr_rightBubbleColor,
                ContextCompat.getColor(context, R.color.bubblesViewRightColor));

        mIntersectionColor = attributes.getColor(R.styleable.BubblesViewAttr_intersectionColor,
                ContextCompat.getColor(context, R.color.bubblesViewIntersectionColor));

        mIntersectionPath = new Path();

        mLeftBubblePaint = new Paint();
        mLeftBubblePaint.setColor(mLeftBubbleColor);
        mLeftBubblePaint.setStyle(Paint.Style.FILL_AND_STROKE);

        mRightBubblePaint = new Paint();
        mRightBubblePaint.setColor(mRightBubbleColor);
        mRightBubblePaint.setStyle(Paint.Style.FILL_AND_STROKE);

        mIntersectionPaint = new Paint();
        mIntersectionPaint.setColor(mIntersectionColor);
        mIntersectionPaint.setStyle(Paint.Style.FILL_AND_STROKE);
    }
}
