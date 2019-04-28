package com.baojiyule.baojicomponent.app.mvp.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.airbnb.lottie.LottieAnimationView;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.baojiyule.baojicomponent.app.R;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;

import butterknife.BindView;
import butterknife.OnClick;
import me.jessyan.armscomponent.commonsdk.utils.Utils;
import me.jessyan.armscomponent.commonservice.RouterHub;
import me.jessyan.armscomponent.commonservice.gank.service.GankInfoService;
import me.jessyan.armscomponent.commonservice.gold.service.GoldInfoService;
import me.jessyan.armscomponent.commonservice.zhihu.service.ZhihuInfoService;

@Route(path = RouterHub.APP_MAINACTIVITY)
public class MainActivity extends BaseActivity {

    @BindView(R.id.animation_view)
    LottieAnimationView animationView;
    @BindView(R.id.bt_zhihu)
    Button btZhihu;
    @BindView(R.id.bt_gank)
    Button btGank;
    @BindView(R.id.bt_gold)
    Button btGold;

    @Autowired(name = RouterHub.ZHIHU_SERVICE_ZHIHUINFOSERVICE)
    ZhihuInfoService mZhihuInfoService;
    @Autowired(name = RouterHub.GANK_SERVICE_GANKINFOSERVICE)
    GankInfoService mGankInfoService;
    @Autowired(name = RouterHub.GOLD_SERVICE_GOLDINFOSERVICE)
    GoldInfoService mGoldInfoService;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {

    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_main;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        ARouter.getInstance().inject(this);
        //这里想展示组件向外提供服务的功能, 模拟下组件向宿主提供一些必要的信息, 这里为了简单就直接返回本地数据不请求网络了
        loadZhihuInfo();
        loadGankInfo();
        loadGoldInfo();
    }

    private void loadZhihuInfo() {
        //当非集成调试阶段, 宿主 App 由于没有依赖其他组件, 所以使用不了对应组件提供的服务
        if (mZhihuInfoService == null) {
            btZhihu.setEnabled(false);
            return;
        }
        btZhihu.setText(mZhihuInfoService.getInfo().getName());
    }

    private void loadGankInfo() {
        //当非集成调试阶段, 宿主 App 由于没有依赖其他组件, 所以使用不了对应组件提供的服务
        if (mGankInfoService == null) {
            btGank.setEnabled(false);
            return;
        }
        btGank.setText(mGankInfoService.getInfo().getName());
    }

    private void loadGoldInfo() {
        //当非集成调试阶段, 宿主 App 由于没有依赖其他组件, 所以使用不了对应组件提供的服务
        if (mGoldInfoService == null) {
            btGold.setEnabled(false);
            return;
        }
        btGold.setText(mGoldInfoService.getInfo().getName());
    }

    /**
     * 这里注意下在组件的页面中(使用了 R2 的页面)使用 {@link butterknife.OnClick} 会有概率出现 id 不正确的问题, 使用以下方式解决
     * <pre>
     * @OnClick({R2.id.button1, R2.id.button2})
     * public void Onclick(View view){
     *      if (view.getId() == R.id.button1){
     *          ...
     *      } else if(view.getId() == R.id.button2){
     *          ...
     *      }
     * }
     * </pre>
     * <p>
     * 在注解上使用 R2, 下面使用 R, 并且使用 {@code if else}, 替代 {@code switch}
     *
     * @param view
     */
    @OnClick({R.id.bt_zhihu, R.id.bt_gank, R.id.bt_gold})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_zhihu:
                Utils.navigation(MainActivity.this, RouterHub.ZHIHU_HOMEACTIVITY);
                break;
            case R.id.bt_gank:
                Utils.navigation(MainActivity.this, RouterHub.GANK_HOMEACTIVITY);
                break;
            case R.id.bt_gold:
                Utils.navigation(MainActivity.this, RouterHub.GOLD_HOMEACTIVITY);
                break;
        }
    }
}
