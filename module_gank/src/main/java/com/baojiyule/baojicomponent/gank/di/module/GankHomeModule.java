package com.baojiyule.baojicomponent.gank.di.module;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.baojiyule.baojicomponent.gank.mvp.contract.GankHomeContract;
import com.baojiyule.baojicomponent.gank.mvp.model.GankHomeModel;
import com.baojiyule.baojicomponent.gank.mvp.model.entity.GankItemBean;
import com.baojiyule.baojicomponent.gank.mvp.ui.adapter.GankHomeAdapter;
import com.jess.arms.di.scope.ActivityScope;

import java.util.ArrayList;
import java.util.List;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 04/28/2019 18:08
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@Module
public abstract class GankHomeModule {

    @Binds
    abstract GankHomeContract.Model bindGankHomeModel(GankHomeModel model);

    @ActivityScope
    @Provides
    static RecyclerView.LayoutManager provideLayoutManager(GankHomeContract.View view) {
        return new GridLayoutManager(view.getActivity(), 2);
    }

    @ActivityScope
    @Provides
    static List<GankItemBean> provideGankList() {
        return new ArrayList<>();
    }

    @ActivityScope
    @Provides
    static RecyclerView.Adapter provideGankHomeAdapter() {
        return new GankHomeAdapter(new ArrayList<>());
    }
}