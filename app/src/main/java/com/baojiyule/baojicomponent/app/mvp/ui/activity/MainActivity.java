package com.baojiyule.baojicomponent.app.mvp.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.baojiyule.baojicomponent.app.R;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;

import me.jessyan.armscomponent.commonservice.RouterHub;

@Route(path = RouterHub.APP_MAINACTIVITY)
public class MainActivity extends BaseActivity {

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {

    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_main;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }
}
