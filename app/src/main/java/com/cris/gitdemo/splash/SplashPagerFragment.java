package com.cris.gitdemo.splash;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.cris.gitdemo.R;
import com.cris.gitdemo.commons.LogUtils;
import com.cris.gitdemo.splash.pager.Pager2;

import butterknife.Bind;
import butterknife.BindColor;
import butterknife.ButterKnife;
import me.relex.circleindicator.CircleIndicator;

/**
 * Created by cris on 16/7/24.
 */
public class SplashPagerFragment extends Fragment {

    @Bind(R.id.viewpager)
    ViewPager mViewpager;
    @Bind(R.id.indicator)
    CircleIndicator mIndicator;
    @Bind(R.id.img_phone_blank)
    ImageView mImgPhoneBlank;
    @Bind(R.id.img_phone)
    ImageView mImgPhone;
    @Bind(R.id.layout_phone_inner)
    FrameLayout mLayoutPhoneInner;
    @Bind(R.id.layout_phone)
    FrameLayout mLayoutPhone;
    @Bind(R.id.content)
    FrameLayout mContent;
    @BindColor(R.color.colorGreen)
    int colorGreen;
    @BindColor(R.color.colorRed)
    int colorRed;
    @BindColor(R.color.colorYellow)
    int colorYellow;

    private SplashPagerAdapter mAdapter;
    private ValueAnimator mAnimator;

    private final ViewPager.OnPageChangeListener mPageListener = new ViewPager.OnPageChangeListener() {
        final ArgbEvaluator evaluator = new ArgbEvaluator();

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            if (position == 0){
                int color = (int) evaluator.evaluate(positionOffset,colorGreen,colorRed);
                mContent.setBackgroundColor(color);
            }else if (position == 1){
                int color = (int) evaluator.evaluate(positionOffset,colorRed,colorYellow);
                mContent.setBackgroundColor(color);
            }
        }

        @Override
        public void onPageSelected(int position) {
            if (position == 2){
                ((Pager2)mAdapter.getView(position)).showAnimationIfNotInit();
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private final ViewPager.OnPageChangeListener mPhoneViewHandler = new ViewPager.OnPageChangeListener() {
        int position;

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            this.position = position;
            if (position == 0){
                float scale = 0.3f+0.7f*positionOffset;
                mLayoutPhoneInner.setScaleX(scale);
                mLayoutPhoneInner.setScaleY(scale);

                if (positionOffsetPixels>0&&mAnimator.isRunning()){
                    mAnimator.cancel();
                    mLayoutPhone.setVisibility(View.VISIBLE);
                }

                mImgPhone.setAlpha(positionOffset);

                LogUtils.d("positionOffset:"+positionOffset);
                LogUtils.d("positionOffsetPixels:"+positionOffsetPixels);

                int scroll = -(int) ((positionOffset-1)*400);
                mLayoutPhone.scrollTo(scroll,scroll/2);
            }else if (position == 1){
                int scrollX = mLayoutPhone.getScrollX();
                LogUtils.d("scrollX:"+scrollX);
                mLayoutPhone.scrollBy(positionOffsetPixels-scrollX,0);
            }
        }

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {
            if (state == ViewPager.SCROLL_STATE_IDLE){
                if (position == 1){
                    mLayoutPhoneInner.setScaleX(1);
                    mLayoutPhoneInner.setScaleY(1);
                    mLayoutPhone.scrollTo(0,0);
                }
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_splash_pager, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAdapter = new SplashPagerAdapter(getContext());
        mViewpager.setAdapter(mAdapter);
        mViewpager.addOnPageChangeListener(mPageListener);
        mViewpager.addOnPageChangeListener(mPhoneViewHandler);
        mIndicator.setViewPager(mViewpager);

        mLayoutPhone.setVisibility(View.INVISIBLE);
        mViewpager.postDelayed(new Runnable() {
            @Override
            public void run() {
                mAnimator = ValueAnimator.ofInt(800,400).setDuration(800);
                mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        mLayoutPhone.setVisibility(View.VISIBLE);
                        mLayoutPhone.scrollTo((Integer) animation.getAnimatedValue(),200);
                    }
                });
                mAnimator.start();
            }
        },50);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
