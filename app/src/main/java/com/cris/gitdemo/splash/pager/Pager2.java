package com.cris.gitdemo.splash.pager;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.cris.gitdemo.R;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by cris on 16/7/24.
 */
public class Pager2 extends FrameLayout {
    @Bind(R.id.img_bubble1)
    ImageView mImgBubble1;
    @Bind(R.id.img_bubble2)
    ImageView mImgBubble2;
    @Bind(R.id.img_bubble3)
    ImageView mImgBubble3;
    @Bind(R.id.tvTitle)
    TextView mTvTitle;
    @Bind(R.id.tvIntroduction)
    TextView mTvIntroduction;

    public Pager2(Context context) {
        super(context);
        init();
    }

    public Pager2(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Pager2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.content_pager_2, this, true);
        ButterKnife.bind(this);

        mImgBubble1.setVisibility(View.INVISIBLE);
        mImgBubble2.setVisibility(View.INVISIBLE);
        mImgBubble3.setVisibility(View.INVISIBLE);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public Pager2(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    public void showAnimationIfNotInit(){
        if (mImgBubble1.getVisibility() == View.INVISIBLE){
            postDelayed(new Runnable() {
                @Override
                public void run() {
                    mImgBubble1.setVisibility(View.VISIBLE);
                    YoYo.with(Techniques.FadeInLeft).duration(300).playOn(mImgBubble1);
                }
            },50);
            postDelayed(new Runnable() {
                @Override
                public void run() {
                    mImgBubble2.setVisibility(View.VISIBLE);
                    YoYo.with(Techniques.FadeInLeft).duration(300).playOn(mImgBubble2);
                }
            },450);
            postDelayed(new Runnable() {
                @Override
                public void run() {
                    mImgBubble1.setVisibility(View.VISIBLE);
                    YoYo.with(Techniques.FadeInLeft).duration(300).playOn(mImgBubble3);
                }
            },850);
        }
    }

}
