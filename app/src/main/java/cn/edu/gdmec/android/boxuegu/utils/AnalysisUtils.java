package cn.edu.gdmec.android.boxuegu.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2018/3/26.
 */

public class AnalysisUtils {
    public static String readLoginUserName(Context context){
        SharedPreferences sp=context.getSharedPreferences("loginInfo",Context.MODE_PRIVATE);
        String userName=sp.getString("loginUserName","");
        return userName;
    }
    public static boolean readLoginStats(Context context){
        SharedPreferences sp=context.getSharedPreferences("loginInfo",Context.MODE_PRIVATE);
        boolean isLogin=sp.getBoolean("isLogin",false);
        return isLogin;
    }
    public static void clearLoginStats(Context context){
        SharedPreferences sp=context.getSharedPreferences("loginInfo",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        editor.putString("loginUserName","");
        editor.putBoolean("isLogin",false);
        editor.commit();
    }
}
