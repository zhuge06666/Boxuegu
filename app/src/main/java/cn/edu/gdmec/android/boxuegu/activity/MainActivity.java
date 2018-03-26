package cn.edu.gdmec.android.boxuegu.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import cn.edu.gdmec.android.boxuegu.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private FrameLayout mBodyLayout;
    public LinearLayout mBottomLayout;
private View mCourseBtn;
private View mExerciseBtn;
private View mMyInfoBtn;
private TextView tv_course;
private TextView tv_exercises;
private TextView tv_myInfo;
private ImageView iv_course;
    private ImageView iv_exercises;
    private ImageView iv_myInfo;
    private TextView tv_back;
    private TextView tv_main_title;
    private RelativeLayout rl_title_bar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        init();
        initBottomBar();
        setListener();
        setInitStatus();
    }
    private void init() {
        tv_back=findViewById(R.id.tv_back);
        tv_main_title=findViewById(R.id.tv_main_title);
        tv_main_title.setText("博学谷课程");
        rl_title_bar=findViewById(R.id.title_bar);
        rl_title_bar.setBackgroundColor(Color.parseColor("#30B4FE"));
        tv_back.setVisibility(View.GONE);
        initBodyLayout();
    }
    private void initBottomBar() {
        mBottomLayout=findViewById(R.id.main_bottom_bar);
        mCourseBtn=findViewById(R.id.bottom_bar_course_btn);
        mExerciseBtn=findViewById(R.id.bottom_bar_exercises_btn);
        mMyInfoBtn=findViewById(R.id.bottom_bar_myinfo_btn);
        tv_course=findViewById(R.id.bottom_bar_text_course);
        tv_exercises=findViewById(R.id.bottom_bar_text_exercises);
        tv_myInfo=findViewById(R.id.bottom_bar_text_myinfo);
        iv_course=findViewById(R.id.bottom_bar_image_course);
        iv_exercises=findViewById(R.id.bottom_bar_image_exercises);
        iv_myInfo=findViewById(R.id.bottom_bar_image_myinfo);
    }

    private void setListener() {
        for (int i=0;i<mBottomLayout.getChildCount();i++){
            mBottomLayout.getChildAt(i).setOnClickListener(this);
        }
    }
    private void setInitStatus() {
        clearBottomImageState();
        setSelectedStatus(0);
        createView(0);
    }
   private void selectDisplayView(int index){
        removeAllView();
        createView(index);
        setSelectedStatus(index);
   }
   private void createView(int viewIndex){
       switch (viewIndex){
           case 0:
               //课程界面
               break;
           case 1:
               //习题界面
               break;
           case 2:
               //我的界面
               break;
       }
   }
   protected long exitTime;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
       if (keyCode == KeyEvent.KEYCODE_BACK&&event.getAction()==KeyEvent.ACTION_DOWN){
           if ((System.currentTimeMillis()-exitTime)>2000){
               Toast.makeText(MainActivity.this,"再按一次退出博学谷",Toast.LENGTH_LONG).show();
               exitTime=System.currentTimeMillis();
           }else {
               MainActivity.this.finish();
               if (readLoginStatus()){
                   clearLoginStatus();
               }
               System.exit(0);
           }
           return true;
       }
       return super.onKeyDown(keyCode,event);
    }
      private boolean readLoginStatus(){
          SharedPreferences sp=getSharedPreferences("loginInfo", Context.MODE_PRIVATE);
          boolean isLogin=sp.getBoolean("isLogin",false);
          return isLogin;
      }
      private void clearLoginStatus(){
          SharedPreferences sp=getSharedPreferences("loginInfo",Context.MODE_PRIVATE);
          SharedPreferences.Editor editor=sp.edit();
          editor.putBoolean("isLogin",false);
          editor.putString("loginUserName","");
          editor.commit();
      }
    private void initBodyLayout() {
        mBodyLayout=findViewById(R.id.main_body);
    }
    private void clearBottomImageState(){
       tv_course.setTextColor(Color.parseColor("#666666"));
       tv_exercises.setTextColor(Color.parseColor("#666666"));
        tv_myInfo.setTextColor(Color.parseColor("#666666"));
        iv_course.setImageResource(R.drawable.main_course_icon);
        iv_exercises.setImageResource(R.drawable.main_exercises_icon);
        iv_myInfo.setImageResource(R.drawable.main_my_icon);
        for (int i=0;i<mBottomLayout.getChildCount();i++){
            mBottomLayout.getChildAt(i).setSelected(false);
        }
    }
    private void setSelectedStatus(int index){
        switch (index){
            case 0:
                mCourseBtn.setSelected(true);
                iv_course.setImageResource(R.drawable.main_course_icon_selected);
                tv_course.setTextColor(Color.parseColor("#0097F7"));
                rl_title_bar.setVisibility(View.VISIBLE);
                tv_main_title.setText("博学谷课程");
                break;
            case 1:
                mExerciseBtn.setSelected(true);
                iv_exercises.setImageResource(R.drawable.main_course_icon_selected);
                tv_exercises.setTextColor(Color.parseColor("#0097F7"));
                rl_title_bar.setVisibility(View.VISIBLE);
                tv_main_title.setText("博学谷习题");
                break;
            case 2:
                mMyInfoBtn.setSelected(true);
                iv_myInfo.setImageResource(R.drawable.main_course_icon_selected);
                tv_myInfo.setTextColor(Color.parseColor("#0097F7"));
                rl_title_bar.setVisibility(View.GONE);
                break;
        }
    }
    private void removeAllView(){
        for (int i=0;i<mBodyLayout.getChildCount();i++){
            mBodyLayout.getChildAt(i).setVisibility(View.GONE);
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bottom_bar_course_btn:
                clearBottomImageState();
                selectDisplayView(0);
                break;
            case R.id.bottom_bar_exercises_btn:
                clearBottomImageState();
                selectDisplayView(1);
                break;
            case R.id.bottom_bar_myinfo_btn:
                clearBottomImageState();
                selectDisplayView(2);
                break;
                default:break;
        }
    }
}
