package com.benny.app.sample.utils

import android.os.Build
import android.view.View
import com.benny.app.sample.Constants
import com.google.gson.GsonBuilder
import java.util.concurrent.atomic.AtomicInteger

/**
 * Created by benny on 12/17/15.
 */
fun toJson(`object`: Any): String {
    return GsonBuilder().setDateFormat(Constants.DATE_FORMAT).create().toJson(`object`)
}

fun <T> fromJson(`object`: String, clazz: Class<T>): T? {
    return if(`object`.isNullOrEmpty()) null else GsonBuilder().setDateFormat(Constants.DATE_FORMAT).create().fromJson(`object`, clazz)
}

val sNextGeneratedId: AtomicInteger = AtomicInteger(1);
fun generateViewId(): Int {
    if (Build.VERSION.SDK_INT < 17) {
        while (true) {
            val result: Int = sNextGeneratedId.get();
            // aapt-generated IDs have the high byte nonzero; clamp to the range under that.
            var newValue: Int = result + 1;
            if (newValue > 0x00FFFFFF)
                newValue = 1; // Roll over to 1, not 0.
            if (sNextGeneratedId.compareAndSet(result, newValue)) {
                return result;
            }
        }
    } else {
        return View.generateViewId();
    }
}

