package com.zac4j.library;

import android.util.Log;

/**
 * Logger
 * Created by zac on 12/16/2016.
 */

public class Logger {

  private static boolean DEBUG = true;

  public static void e(String tag, String errMsg) {
    if (DEBUG) {
      Log.e(tag, errMsg);
    }
  }

  public static void i(String tag, String errMsg) {
    if (DEBUG) {
      Log.i(tag, errMsg);
    }
  }
}
