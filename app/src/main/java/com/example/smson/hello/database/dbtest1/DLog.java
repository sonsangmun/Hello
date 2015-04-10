package com.example.smson.hello.database.dbtest1;

import android.util.Log;

/**
 * Created by sangmun on 2015-04-08.
 */
public class DLog {
    private static boolean DEBUG = true;

    public static int v(String tag, String msg) {
        if (DEBUG == false) {
            return 0;
        }
        return Log.v(tag, msg);
    }

    public static int v(String tag, String msg, Throwable tr) {
        if (DEBUG == false) {
            return 0;
        }
        return Log.v(tag, msg, tr);
    }

    public static int e(String tag, String msg) {
        if (DEBUG == false) {
            return 0;
        }
        return Log.e(tag, msg);
    }

    public static int e(String tag, String msg, Throwable tr) {
        if (DEBUG == false) {
            return 0;
        }
        return Log.e(tag, msg, tr);
    }

    public static int w(String tag, String msg) {
        if (DEBUG == false) {
            return 0;
        }
        return Log.w(tag, msg);
    }

    public static int w(String tag, String msg, Throwable tr) {
        if (DEBUG == false) {
            return 0;
        }
        return Log.w(tag, msg, tr);
    }

    public static int i(String tag, String msg) {
        if (DEBUG == false) {
            return 0;
        }
        return Log.i(tag, msg);
    }

    public static int i(String tag, String msg, Throwable tr) {
        if (DEBUG == false) {
            return 0;
        }
        return Log.i(tag, msg, tr);
    }

    public static int d(String tag, String msg) {
        if (DEBUG == false) {
            return 0;
        }
        return Log.d(tag, msg);
    }

    public static int d(String tag, String msg, Throwable tr) {
        if (DEBUG == false) {
            return 0;
        }
        return Log.d(tag, msg, tr);
    }
}
