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
public class Pager0 extends FrameLayout {
    @Bind(R.id.img_tablet)
    ImageView mImgTablet;
    @Bind(R.id.img_desktop)
    ImageView mImgDesktop;
    @Bind(R.id.txt_title)
    TextView mTxtTitle;
    @Bind(R.id.txt_introduction)
    TextView mTxtIntroduction;

    public Pager0(Context context) {
        super(context);
        init();
    }

    public Pager0(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Pager0(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.content_pager_0, this, true);
        ButterKnife.bind(this);
        mImgDesktop.setVisibility(View.INVISIBLE);
        mImgTablet.setVisibility(View.INVISIBLE);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public Pager0(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        if (mImgDesktop.getVisibility() == View.INVISIBLE){
            postDelayed(new Runnable() {
                @Override
                public void run() {
                    mImgDesktop.setVisibility(View.VISIBLE);
                    YoYo.with(Techniques.SlideInRight).duration(650).playOn(mImgDesktop);
                }
            },50);
            postDelayed(new Runnable() {
                @Override
                public void run() {
                    mImgTablet.setVisibility(View.VISIBLE);
                    YoYo.with(Techniques.SlideInRight).duration(650).playOn(mImgTablet);
                    YoYo.with(Techniques.SlideInLeft).duration(650).playOn(mImgTablet);
                }
            },50);
        }
    }
}
