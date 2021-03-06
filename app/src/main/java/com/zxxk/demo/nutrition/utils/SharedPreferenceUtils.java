package com.zxxk.demo.nutrition.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;


/**
 * Tag:
 * Author: Michael(michael.hms@outlook.com)
 * Date: 2016/3/3
 * Time: 15:47
 * Description:
 */

public class SharedPreferenceUtils {
    public static final String TAG = SharedPreferenceUtils.class.getSimpleName();

    /**
     * Boolean indicating whether launch the app first time
     */
    public static final String SHARED_PREF_IS_FIRST_LAUNCH = "shared_pref_is_first_launch";

    /**
     * Boolean indicating whether user learned the drawer
     * <p/>
     * Per the design guidelines, you should show the drawer on launch until the user manually
     * expands it. This shared preference tracks this.
     */
    public static final String SHARED_PREF_IS_USER_LEARNED_DRAWER = "shared_pref_is_user_learned_drawer";


    public static final String SHARED_PREF_SPLASH_JSON = "shared_pref-splash_json";

    public static SharedPreferences getDefaultSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    /**
     * whether launch the app first time
     *
     * @param context
     * @return
     */
    public static boolean isFirstLaunch(final Context context) {
        SharedPreferences sp = getDefaultSharedPreferences(context);
        return sp.getBoolean(SHARED_PREF_IS_FIRST_LAUNCH, true);
    }

    /**
     * mark app launched
     *
     * @param context
     */
    public static void markFirstLaunch(final Context context) {
        SharedPreferences sp = getDefaultSharedPreferences(context);
        sp.edit().putBoolean(SHARED_PREF_IS_FIRST_LAUNCH, false).apply();
    }

    public static boolean isUserLearnedDrawer(final Context context) {
        SharedPreferences sp = getDefaultSharedPreferences(context);
        return sp.getBoolean(SHARED_PREF_IS_USER_LEARNED_DRAWER, false);
    }

    public static void markUserLearnedDrawer(Context context) {
        SharedPreferences sp = getDefaultSharedPreferences(context);
        sp.edit().putBoolean(SHARED_PREF_IS_USER_LEARNED_DRAWER, true).commit();
    }

    public static String getSplashJson(Context context){
        SharedPreferences sp = getDefaultSharedPreferences(context);
        return sp.getString(SHARED_PREF_SPLASH_JSON, null);
    }

    public static void setSplashJson(Context context, String jsonString){
        SharedPreferences sp = getDefaultSharedPreferences(context);
        sp.edit().putString(SHARED_PREF_SPLASH_JSON, jsonString).commit();
    }

}
