package com.example.administrator.news.Utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2017/6/26.
 */

public class sharePreUtil {
    private static final String FIRST_RUN="first_run";
    private static final String FIRST_SP="first_sp";

    public static boolean getFirstRun(Context context){
        SharedPreferences sp=context.getSharedPreferences(FIRST_SP,Context.MODE_PRIVATE);
        return sp.getBoolean(FIRST_RUN,true);
    }

    public static void setFirstRun(Context context ,boolean isFirst){
        SharedPreferences sharedPreferences=context.getSharedPreferences(FIRST_SP,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putBoolean(FIRST_RUN,isFirst);
        editor.commit();
    }


}
