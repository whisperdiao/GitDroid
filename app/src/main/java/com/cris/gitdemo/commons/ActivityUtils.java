package com.cris.gitdemo.commons;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import java.lang.ref.WeakReference;

/**
 * Created by cris on 16/7/24.
 */
public class ActivityUtils {
    private WeakReference<Activity> activityWeakReference;
    private WeakReference<Fragment> fragmentWeakReference;

    private Toast toast;

    public ActivityUtils(Activity activity) {
        activityWeakReference = new WeakReference<Activity>(activity);
    }

    public ActivityUtils(Fragment fragment) {
        fragmentWeakReference = new WeakReference<Fragment>(fragment);
    }

    private
    @Nullable
    Activity getActivity() {
        if (activityWeakReference != null) {
            return activityWeakReference.get();
        }
        if (fragmentWeakReference != null) {
            Fragment fragment = fragmentWeakReference.get();
            return fragment == null ? null : fragment.getActivity();
        }
        return null;
    }

    public void showToast(CharSequence msg) {
        Activity activity = getActivity();
        if (activity != null) {
            if (toast == null) {
                toast = Toast.makeText(activity, msg, Toast.LENGTH_SHORT);
            }
            toast.setText(msg);
            toast.show();
        }
    }

    @SuppressWarnings("SameParameterValue")
    public void showToast(@StringRes int resId) {
        Activity activity = getActivity();
        if (activity != null) {
            String msg = activity.getString(resId);
            showToast(msg);
        }
    }

    public void startActivity(Class<? extends Activity> cls) {
        Activity activity = getActivity();
        if (activity == null) {
            return;
        }
        Intent intent = new Intent(activity, cls);
        activity.startActivity(intent);
    }

    public int getStatusBarHeight() {
        Activity activity = getActivity();
        if (activity == null) {
            return 0;
        }
        Resources resources = activity.getResources();
        int result = 0;
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = resources.getDimensionPixelSize(resourceId);
        }
        LogUtils.v("getStatusBarHeight:" + result);
        return result;
    }

    public int getScreenWidth() {
        Activity activity = getActivity();
        if (activity == null) {
            return 0;
        }
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return metrics.widthPixels;
    }

    public int getScreenHeight() {
        Activity activity = getActivity();
        if (activity == null) {
            return 0;
        }
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return metrics.heightPixels;
    }

    public void hideSoftKeyboard() {
        Activity activity = getActivity();
        if (activity == null) {
            return;
        }
        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public void startBrowser(String url) {
        if (getActivity() == null) {
            return;
        }
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        Uri uri = Uri.parse(url);
        intent.setData(uri);
        getActivity().startActivity(intent);
    }

}
