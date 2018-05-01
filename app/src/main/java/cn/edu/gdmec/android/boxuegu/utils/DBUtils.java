package cn.edu.gdmec.android.boxuegu.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import cn.edu.gdmec.android.boxuegu.bean.UserBean;
import cn.edu.gdmec.android.boxuegu.bean.VideoBean;
import cn.edu.gdmec.android.boxuegu.sqlite.SQLiteHelper;

/**
 * Created by Administrator on 2018/4/9.
 */

public class DBUtils {
    private static DBUtils instance=null;
    private static SQLiteHelper helper;
    private static SQLiteDatabase db;
    public DBUtils(Context context){
        helper=new SQLiteHelper(context);
        db=helper.getWritableDatabase();
    }
    public static DBUtils getInstance(Context context){
        if (instance==null){
            instance=new DBUtils(context);
        }
        return instance;
    }
    public void saveUserInfo(UserBean bean){
        ContentValues cv=new ContentValues();
        cv.put("userName",bean.userName);
        cv.put("nickName",bean.nickName);
        cv.put("sex",bean.sex);
        cv.put("signature",bean.signature);
        cv.put("qq",bean.qq);
        db.insert(SQLiteHelper.U_USERINFO,null,cv);
    }
    public UserBean getUserInfo(String userName){
        String sql="SELECT * FROM "+SQLiteHelper.U_USERINFO+" WHERE userName=?";
        Cursor cursor=db.rawQuery(sql,new String[]{userName});
        UserBean bean=null;
        while (cursor.moveToNext()){
            bean=new UserBean();
            bean.userName=cursor.getString(cursor.getColumnIndex("userName"));
            bean.nickName=cursor.getString(cursor.getColumnIndex("nickName"));
            bean.sex=cursor.getString(cursor.getColumnIndex("sex"));
            bean.signature=cursor.getString(cursor.getColumnIndex("signature"));
            bean.qq=cursor.getString(cursor.getColumnIndex("qq"));
        }
        cursor.close();
        return bean;
    }
    public void updateUserInfo(String key,String value,String userName){
        ContentValues cv=new ContentValues();
        cv.put(key,value);
        db.update(SQLiteHelper.U_USERINFO,cv,"userName=?",new String[]{userName});
    }
    public void saveVideoPlayList(VideoBean videoBean,String userName){
        if (hasVideoPlay(videoBean.chaterId,videoBean.videoId,userName)){
            boolean isDelete = delVideoPlay(videoBean.chaterId,videoBean.videoId,userName);
            if (isDelete){
                return;
            }
        }
        ContentValues cv=new ContentValues();
        cv.put("userName",userName);
        cv.put("chapterId",videoBean.chaterId);
        cv.put("videoId",videoBean.videoId);
        cv.put("vedioPath",videoBean.videoPath);
        cv.put("title",videoBean.title);
        cv.put("secondTitle",videoBean.secondTitle);
        db.insert(SQLiteHelper.U_VIDEO_PLAY_LIST,null,cv);
    }
    public boolean delVideoPlay(int chapterId,int videoId,String userName){
        boolean delSuccess=false;
        int row=db.delete(SQLiteHelper.U_VIDEO_PLAY_LIST," chapterId=? AND videoId=? AND userName=?",
                new String[]{chapterId+"",videoId+"",userName});
        if (row>0){
            delSuccess=true;
        }
        return delSuccess;
    }
    private boolean hasVideoPlay(int chapterId,int videoId,String userName){
       boolean hasVideo=false;
       String sql="SELECT * FROM " +SQLiteHelper.U_VIDEO_PLAY_LIST+
               " WHERE chapterId=? AND videoId=? AND userName=?";
       Cursor cursor =db.rawQuery(sql,new String[]{chapterId+"",videoId+"",userName});
       if (cursor.moveToNext()){
           hasVideo=true;
       }
       cursor.close();
       return hasVideo;
    }
    public List<VideoBean> getVideoHistory(String s){
        String sql="SELECT * FROM "+SQLiteHelper.U_VIDEO_PLAY_LIST+" WHERE userName=?";
        Cursor cursor =db.rawQuery(sql,new String[]{s});
        List<VideoBean> vbl=new ArrayList<>();
        VideoBean bean=null;
        while (cursor.moveToNext()){
            bean=new VideoBean();
            bean.chaterId=cursor.getInt(cursor.getColumnIndex("chapterId"));
            bean.videoId=cursor.getInt(cursor.getColumnIndex("videoId"));
            bean.videoPath=cursor.getString(cursor.getColumnIndex("videoPath"));
            bean.title=cursor.getString(cursor.getColumnIndex("title"));
            bean.secondTitle=cursor.getString(cursor.getColumnIndex("secondTitle"));
            vbl.add(bean);
            bean=null;
        }
        cursor.close();
        return vbl;
    }
}
