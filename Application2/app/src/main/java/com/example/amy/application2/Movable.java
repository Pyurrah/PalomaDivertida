package com.example.amy.application2;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

public class Movable extends ImageView {

    public static boolean destructible;

    public Movable(Context context) {
        this(context, null);
        init();
    }

    public Movable(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void init(){
        destructible = false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX() - 200; // - 70;
        float y = event.getY() - 300; // - 95;

        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN :
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE :

                ResultingActivity.r1.bottom = ResultingActivity.destruct.getBottom();
                ResultingActivity.r1.left = ResultingActivity.destruct.getLeft();
                ResultingActivity.r1.right = ResultingActivity.destruct.getRight();
                ResultingActivity.r1.top = ResultingActivity.destruct.getTop();

                ResultingActivity.r2.bottom = ResultingActivity.im.getBottom() + (int)getTranslationY();
                ResultingActivity.r2.left = ResultingActivity.im.getLeft() + (int)getTranslationX();
                ResultingActivity.r2.right = ResultingActivity.im.getRight() + (int)getTranslationX();
                ResultingActivity.r2.top = ResultingActivity.im.getTop() + (int)getTranslationY();

                ResultingActivity.r3.bottom = ResultingActivity.vanisher.getBottom();
                ResultingActivity.r3.left = ResultingActivity.vanisher.getLeft();
                ResultingActivity.r3.right = ResultingActivity.vanisher.getRight();
                ResultingActivity.r3.top = ResultingActivity.vanisher.getTop();

                ResultingActivity.r4.bottom = ResultingActivity.wall_1.getBottom();
                ResultingActivity.r4.left = ResultingActivity.wall_1.getLeft();
                ResultingActivity.r4.right = ResultingActivity.wall_1.getRight();
                ResultingActivity.r4.top = ResultingActivity.wall_1.getTop();

                ResultingActivity.r5.bottom = ResultingActivity.wall_2.getBottom();
                ResultingActivity.r5.left = ResultingActivity.wall_2.getLeft();
                ResultingActivity.r5.right = ResultingActivity.wall_2.getRight();
                ResultingActivity.r5.top = ResultingActivity.wall_2.getTop();

                if (!(ResultingActivity.r4.intersect(ResultingActivity.r2) || ResultingActivity.r5.intersect(ResultingActivity.r2))) {
                    setTranslationX(getTranslationX() + x);
                    setTranslationY(getTranslationY() + y);
                }
                else {
                    setTranslationY(getTranslationY() + 1);
                    if (ResultingActivity.r4.intersect(ResultingActivity.r2)){
                        setTranslationX(getTranslationX() + 5);
                    }
                    if (ResultingActivity.r5.intersect(ResultingActivity.r2)){
                        setTranslationX(getTranslationX() - 5);
                    }
                    break;
                }

                if (ResultingActivity.r1.intersect(ResultingActivity.r2)){
                    destructible = true;
                    System.out.println("a");
                }

                if (destructible){
                    if (ResultingActivity.r3.intersect(ResultingActivity.r2)){
                        ResultingActivity.im.setVisibility(GONE);
                        System.out.println("c");

                        final AnimationDrawable explosionAnimation = (AnimationDrawable) ResultingActivity.explosion.getBackground();
                        ResultingActivity.explosion.post(new Runnable() {
                            @Override
                            public void run() {
                                explosionAnimation.start();
                            }
                        });

                    }
                    setTranslationY(getTranslationY() - 5);
                    setTranslationX(getTranslationX() - x);
                    setTranslationY(getTranslationY() - y);
                }
                invalidate();
                break;
            case MotionEvent.ACTION_UP :
                invalidate();
                break;
        }
        return true;
    }
}