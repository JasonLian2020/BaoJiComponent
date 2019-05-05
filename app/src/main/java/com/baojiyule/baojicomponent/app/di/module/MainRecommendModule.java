package com.baojiyule.baojicomponent.app.di.module;

import com.jess.arms.di.scope.FragmentScope;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

import com.baojiyule.baojicomponent.app.mvp.contract.MainRecommendContract;
import com.baojiyule.baojicomponent.app.mvp.model.MainRecommendModel;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 05/05/2019 12:05
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@Module
public abstract class MainRecommendModule {

    @Binds
    abstract MainRecommendContract.Model bindMainRecommendModel(MainRecommendModel model);
}