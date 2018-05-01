package cn.edu.gdmec.android.boxuegu.Fragment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import cn.edu.gdmec.android.boxuegu.R;
import cn.edu.gdmec.android.boxuegu.activity.ActivityPlayHistoryActivity;
import cn.edu.gdmec.android.boxuegu.activity.LoginActivity;
import cn.edu.gdmec.android.boxuegu.activity.SettingActivity;
import cn.edu.gdmec.android.boxuegu.activity.UserInfoActivity;
import cn.edu.gdmec.android.boxuegu.utils.AnalysisUtils;

public class MainViewMyinfoFragment extends Fragment  implements View.OnClickListener{

    private LinearLayout llHead;
    private ImageView ivHeadIcon;
    private TextView tvUserName;
    private RelativeLayout rlCourseHistory;
    private ImageView ivCourseHistoryicon;
    private RelativeLayout rlSetting;
    private ImageView ivUserinfoIcon;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_view_myinfo, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        llHead = (LinearLayout) view.findViewById(R.id.ll_head);
        ivHeadIcon = (ImageView) view.findViewById(R.id.iv_head_icon);
        tvUserName = (TextView) view.findViewById(R.id.tv_user_name);
        rlCourseHistory = (RelativeLayout) view.findViewById(R.id.rl_course_history);
        ivCourseHistoryicon = (ImageView) view.findViewById(R.id.iv_course_historyicon);
        rlSetting = (RelativeLayout) view.findViewById(R.id.rl_setting);
        ivUserinfoIcon = (ImageView) view.findViewById(R.id.iv_userinfo_icon);
        llHead.setOnClickListener(this);
        rlCourseHistory.setOnClickListener(this);
        rlSetting.setOnClickListener(this);
        if (AnalysisUtils.readLoginStats(getActivity())){
            tvUserName.setText(AnalysisUtils.readLoginUserName(getActivity()));
        }else {
            tvUserName.setText("点击登录");
        }
    }

    @Override
    public void onClick(View view) {
    switch (view.getId()){
        case R.id.ll_head:
            if(AnalysisUtils.readLoginStats(getActivity())){
                Intent intent=new Intent(getActivity(), UserInfoActivity.class);
                startActivityForResult(intent,1);
            }else {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivityForResult(intent,1);
            }
            break;
        case R.id.rl_course_history:
            if (AnalysisUtils.readLoginStats(getActivity())){
                Intent intent = new Intent(getActivity(), ActivityPlayHistoryActivity.class);
               getActivity().startActivity(intent);
            }else {
                Toast.makeText(getActivity(),"您还未登录，请先登录",Toast.LENGTH_SHORT).show();
            }
            break;
        case R.id.rl_setting:
            if (AnalysisUtils.readLoginStats(getActivity())){
                Intent intent=new Intent(getActivity(), SettingActivity.class);
                startActivityForResult(intent,1);
            }else {
                Toast.makeText(getActivity(),"您还未登录，请先登录",Toast.LENGTH_SHORT).show();
            }
            break;
    }
    }
}
