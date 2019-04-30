package com.baojiyule.baojicomponent.app.mvp.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.baojiyule.baojicomponent.app.R;
import com.baojiyule.baojicomponent.app.mvp.ui.fragment.MainHomeFragment;
import com.baojiyule.baojicomponent.app.mvp.ui.fragment.MainRecommendFragment;
import com.baojiyule.baojicomponent.app.mvp.ui.fragment.MainUsencenterFragment;
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

    private void showFragment(BaseFragment baseFragment, Class<BaseFragment> _class) {
        if (currentFragment == baseFragment && currentFragment != null) return;
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (currentFragment != null) ft.hide(currentFragment);
        if (baseFragment == null) {
            baseFragment = (BaseFragment) getSupportFragmentManager().findFragmentByTag(getTagByClass(_class));
        }
        if (baseFragment == null) {
            baseFragment = newInstanceByClass(_class);
            ft.add(R.id.contentLayout, baseFragment, getTagByClass(_class));
        } else {
            ft.show(baseFragment);
        }
        ft.commitAllowingStateLoss();
        currentFragment = baseFragment;
        // Update UI
        refreshBottomUI(_class);
    }

    private BaseFragment getBaseFragmentByClass(Class<BaseFragment> _class) {
        BaseFragment baseFragment = null;
        try {
            baseFragment = _class.newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return baseFragment;
    }

    private BaseFragment newInstanceByClass(Class<BaseFragment> _class) {
        BaseFragment baseFragment = getBaseFragmentByClass(_class);
        if (baseFragment instanceof MainHomeFragment)
            return new MainHomeFragment();
        else if (baseFragment instanceof MainRecommendFragment)
            return new MainRecommendFragment();
        else if (baseFragment instanceof MainUsencenterFragment)
            return new MainUsencenterFragment();
        return null;
    }

    private String getTagByClass(Class<BaseFragment> _class) {
        BaseFragment baseFragment = getBaseFragmentByClass(_class);
        return null;
    }

    private void refreshBottomUI(Class<BaseFragment> _class) {
        BaseFragment baseFragment = getBaseFragmentByClass(_class);
    }
}
