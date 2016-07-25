package com.cris.gitdemo.commons;

import android.util.Log;

import java.util.Objects;
import java.util.concurrent.ThreadFactory;

/**
 * Created by cris on 16/7/24.
 */
public class LogUtils {

    private static final String TAG = "GitDroid";

    private static final String TAG_TRACE = "GitDroid";

    private static final class StackTraceDebug extends RuntimeException{
        final static private long serialVersionUID = 27058374L;
    }

    private static boolean isDebug = true;

    private static final boolean logTrace = true;

    private LogUtils(){

    }

    public static void trace(Object object){
        if (logTrace){
            StackTraceElement[] traces = Thread.currentThread().getStackTrace();
            StackTraceElement trace = traces[3];
            Log.d(TAG_TRACE,addThreadInfo(object.getClass().getSimpleName()+":"+trace.getMethodName()));
        }
    }

    private static String addThreadInfo(final String msg){
        final String threadName = Thread.currentThread().getName();
        final String shortName = threadName.startsWith("OkHttp")?"OkHttp":threadName;
        return "["+shortName+"]"+msg;
    }

    public static void v(final String msg){
        if (isDebug){
            Log.v(TAG,addThreadInfo(msg));
        }
    }

    public static void v(final String msg,final Throwable t){
        if (isDebug){
            Log.v(TAG,addThreadInfo(msg),t);
        }
    }

    public static void d(final String msg){
        if (isDebug){
            Log.d(TAG,addThreadInfo(msg));
        }
    }

    public static void d(final String msg,final Throwable t){
        if (isDebug){
            Log.d(TAG,addThreadInfo(msg),t);
        }
    }

    public static void w(final String msg){
        if (isDebug){
            Log.w(TAG,addThreadInfo(msg));
        }
    }

    public static void w(final String msg,final Throwable t){
        if (isDebug){
            Log.w(TAG,addThreadInfo(msg),t);
        }
    }

    public static void i(final String msg){
        if (isDebug){
            Log.i(TAG,addThreadInfo(msg));
        }
    }

    public static void i(final String msg,final Throwable t){
        if (isDebug){
            Log.i(TAG,addThreadInfo(msg),t);
        }
    }

    public static void e(final String msg){
        if (isDebug){
            Log.e(TAG,addThreadInfo(msg));
        }
    }

    public static void e(final String msg,final Throwable t){
        if (isDebug){
            Log.e(TAG,addThreadInfo(msg),t);
        }
    }

    public static void logStackTrace(final String msg){
        try {
            throw new StackTraceDebug();
        } catch (StackTraceDebug stackTraceDebug) {
            d(msg,stackTraceDebug);
        }
    }



}
