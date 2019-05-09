package com.baojiyule.baojicomponent.app.mvp.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import com.baojiyule.baojicomponent.app.R;
import com.baojiyule.baojicomponent.app.di.component.DaggerMainHomeComponent;
import com.baojiyule.baojicomponent.app.mvp.contract.MainHomeContract;
import com.baojiyule.baojicomponent.app.mvp.presenter.MainHomePresenter;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.abs.IPagerNavigator;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import me.jason.autopagelayout.AutoPageLayout;
import me.jessyan.armscomponent.commonres.magicindicator.titles.ColorFlipPagerTitleView;
import me.jessyan.armscomponent.commonres.utils.FragmentUtil;

import static com.jess.arms.utils.Preconditions.checkNotNull;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 05/05/2019 12:02
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
public class MainHomeFragment extends BaseFragment<MainHomePresenter> implements MainHomeContract.View {

    @BindView(R.id.contentLayout)
    View contentLayout;
    @BindView(R.id.tabLayout)
    MagicIndicator tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    public static MainHomeFragment newInstance() {
        MainHomeFragment fragment = new MainHomeFragment();
        return fragment;
    }

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerMainHomeComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main_home, container, false);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        FragmentUtil.setTitle(this, R.string.main_home_title);
        initFragmentList();
        initViewPager();
        initPageLayout();
        //test
        new Handler().postDelayed(() -> {
            if (pageLayout != null) pageLayout.showContent();
        }, 2000);
    }

    @Override
    public void setData(@Nullable Object data) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        ArmsUtils.snackbarText(message);
    }

    @Override
    public void launchActivity(@NonNull Intent intent) {
        checkNotNull(intent);
        ArmsUtils.startActivity(intent);
    }

    @Override
    public void killMyself() {

    }

    private String[] mTitleList = {"关注", "专享", "视频", "纯文", "纯图", "精华"};
    private List<BaseFragment> mFragmentList;

    private List<BaseFragment> initFragmentList() {
        //
        if (mFragmentList == null) mFragmentList = new ArrayList<>();
        else mFragmentList.clear();
        //
        for (int i = 0; i < mTitleList.length; i++) {
            mFragmentList.add(HomeDetailFragment.newInstance(i));
        }
        return mFragmentList;
    }

    private void initViewPager() {
        viewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragmentList == null ? null : mFragmentList.get(position);
            }

            @Override
            public int getCount() {
                return mFragmentList == null ? 0 : mFragmentList.size();
            }
        });
        tabLayout.setNavigator(initNaviagtor());
        ViewPagerHelper.bind(tabLayout, viewPager);
    }


    private IPagerNavigator initNaviagtor() {
        CommonNavigator commonNavigator = new CommonNavigator(getActivity());
        commonNavigator.setScrollPivotX(0.65f);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {

            @Override
            public int getCount() {
                return mTitleList == null ? 0 : mTitleList.length;
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new ColorFlipPagerTitleView(context);
                simplePagerTitleView.setText(mTitleList[index]);
                simplePagerTitleView.setNormalColor(Color.parseColor("#999999"));
                simplePagerTitleView.setSelectedColor(Color.parseColor("#333333"));
                simplePagerTitleView.setOnClickListener(v -> viewPager.setCurrentItem(index));
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setMode(LinePagerIndicator.MODE_EXACTLY);
                indicator.setLineHeight(UIUtil.dip2px(context, 6));
                indicator.setLineWidth(UIUtil.dip2px(context, 10));
                indicator.setRoundRadius(UIUtil.dip2px(context, 3));
                indicator.setStartInterpolator(new AccelerateInterpolator());
                indicator.setEndInterpolator(new DecelerateInterpolator(2.0f));
                indicator.setColors(Color.parseColor("#00c853"));
                return indicator;
            }
        });
        return commonNavigator;
    }

    private AutoPageLayout pageLayout;

    private void initPageLayout() {
        pageLayout = new AutoPageLayout.Builder(this)
                .setTarget(contentLayout)
                .setLoadingLayout(R.layout.public_layout_loading, null)
                .setEmptyLayout(R.layout.public_layout_empty, null)
                .setErrorLayout(R.layout.public_layout_error, null)
                .showType(AutoPageLayout.SHOW_TYPE_LOADING)
                .build();
    }
}
