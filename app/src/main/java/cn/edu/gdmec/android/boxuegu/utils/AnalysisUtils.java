package cn.edu.gdmec.android.boxuegu.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Xml;
import android.widget.ImageView;

import org.xmlpull.v1.XmlPullParser;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import cn.edu.gdmec.android.boxuegu.bean.ExercisesBean;

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
    public static List<ExercisesBean> getExercisesInfos(InputStream is) throws Exception{
        XmlPullParser parser= Xml.newPullParser();
        parser.setInput(is,"utf-8");
        List<ExercisesBean> exercisesInfos=null;
        ExercisesBean exercisesInfo=null;
        int type=parser.getEventType();
        while (type!=XmlPullParser.END_DOCUMENT){
            switch (type){
                case XmlPullParser.START_TAG:
                    if ("infos".equals(parser.getName())){
                        exercisesInfos=new ArrayList<ExercisesBean>();
                    }else if ("exercises".equals(parser.getName())){
                        exercisesInfo=new ExercisesBean();
                        String ids=parser.getAttributeValue(0);
                        exercisesInfo.subjectId=Integer.parseInt(ids);
                    }else if ("subject".equals(parser.getName())){
                        String subject=parser.nextText();
                        exercisesInfo.subject=subject;
                    } else if ("a".equals(parser.getName())){
                        String a=parser.nextText();
                        exercisesInfo.a=a;
                    }else if ("b".equals(parser.getName())){
                        String b=parser.nextText();
                        exercisesInfo.b=b;
                    }else if ("c".equals(parser.getName())){
                        String c=parser.nextText();
                        exercisesInfo.c=c;
                    }else if ("d".equals(parser.getName())){
                        String d=parser.nextText();
                        exercisesInfo.d=d;
                    }else if ("answer".equals(parser.getName())){
                        String answer=parser.nextText();
                        exercisesInfo.answer=Integer.parseInt(answer);
                    }
                    break;
                case XmlPullParser.END_TAG:
                    if ("exercises".equals(parser.getName())){
                        exercisesInfos.add(exercisesInfo);
                        exercisesInfo=null;
                    }
                    break;
            }
            type=parser.next();
        }
        return exercisesInfos;
    }
    public static void setABCDEnable(boolean value, ImageView iv_a,ImageView iv_b,ImageView iv_c,ImageView iv_d){
    iv_a.setEnabled(value);
    iv_b.setEnabled(value);
    iv_c.setEnabled(value);
    iv_d.setEnabled(value);
    }
}
