package com.cris.gitdemo.splash.pager;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.cris.gitdemo.R;

/**
 * Created by cris on 16/7/24.
 */
public class Pager1 extends FrameLayout {
    public Pager1(Context context) {
        super(context);
        init();
    }

    public Pager1(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Pager1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.content_pager_1,this,true);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public Pager1(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }


}
