package com.baojiyule.baojicomponent.zhihu.di.module;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.baojiyule.baojicomponent.zhihu.app.ZhihuConstants;
import com.baojiyule.baojicomponent.zhihu.mvp.contract.ZhihuHomeContract;
import com.baojiyule.baojicomponent.zhihu.mvp.model.ZhihuModel;
import com.baojiyule.baojicomponent.zhihu.mvp.model.entity.DailyListBean;
import com.baojiyule.baojicomponent.zhihu.mvp.ui.adapter.ZhihuHomeAdapter;
import com.jess.arms.base.DefaultAdapter;
import com.jess.arms.di.scope.ActivityScope;

import java.util.ArrayList;
import java.util.List;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import me.jessyan.armscomponent.commonservice.RouterHub;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 04/29/2019 14:58
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@Module
public abstract class ZhihuHomeModule {

    @Binds
    abstract ZhihuHomeContract.Model bindZhihuModel(ZhihuModel model);

    @ActivityScope
    @Provides
    static RecyclerView.LayoutManager provideLayoutManager(ZhihuHomeContract.View view) {
        return new LinearLayoutManager(view.getActivity());
    }

    @ActivityScope
    @Provides
    static List<DailyListBean.StoriesBean> provideList() {
        return new ArrayList<>();
    }

    @ActivityScope
    @Provides
    static RecyclerView.Adapter provideZhihuHomeAdapter(ZhihuHomeContract.View zhihuHomeView, List<DailyListBean.StoriesBean> list) {
        ZhihuHomeAdapter adapter = new ZhihuHomeAdapter(list);
        adapter.setOnItemClickListener(new DefaultAdapter.OnRecyclerViewItemClickListener<DailyListBean.StoriesBean>() {
            @Override
            public void onItemClick(View view, int viewType, DailyListBean.StoriesBean data, int position) {
                ARouter.getInstance()
                        .build(RouterHub.ZHIHU_DETAILACTIVITY)
                        .withInt(ZhihuConstants.DETAIL_ID, data.getId())
                        .withString(ZhihuConstants.DETAIL_TITLE, data.getTitle())
                        .navigation(zhihuHomeView.getActivity());
            }
        });
        return adapter;
    }
}