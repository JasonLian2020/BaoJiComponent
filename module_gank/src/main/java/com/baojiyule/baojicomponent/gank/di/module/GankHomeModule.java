package com.baojiyule.baojicomponent.gank.di.module;

import com.baojiyule.baojicomponent.gank.mvp.contract.GankHomeContract;
import com.baojiyule.baojicomponent.gank.mvp.model.GankHomeModel;

import dagger.Binds;
import dagger.Module;


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
}