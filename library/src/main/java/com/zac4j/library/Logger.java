package com.zac4j.library;

import android.util.Log;

/**
 * Logger
 * Created by zac on 12/16/2016.
 */

public class Logger {

  static boolean DEBUG = BuildConfig.DEBUG;

  static void e(String tag, String errMsg) {
    if (DEBUG) {
      Log.e(tag, errMsg);
    }
  }

  static void i(String tag, String errMsg) {
    if (DEBUG) {
      Log.i(tag, errMsg);
    }
  }
}
