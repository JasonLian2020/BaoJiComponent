package com.baojiyule.baojicomponent.app.mvp.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.baojiyule.baojicomponent.app.R;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;

import butterknife.BindView;
import butterknife.OnClick;
import me.jessyan.armscomponent.commonservice.RouterHub;

@Route(path = RouterHub.APP_MAIN2ACTIVITY)
public class Main2Activity extends BaseActivity {
    @BindView(R.id.tvHome)
    ImageView tvHome;
    @BindView(R.id.tvRecommend)
    ImageView tvRecommend;
    @BindView(R.id.tvUserCenter)
    ImageView tvUserCenter;

    private BaseFragment currentFragment;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {

    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_main2;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }


    @OnClick({R.id.tvHome, R.id.tvRecommend, R.id.tvUserCenter})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvHome:
                break;
            case R.id.tvRecommend:
                break;
            case R.id.tvUserCenter:
                break;
        }
    }
}
