package com.baojiyule.baojicomponent.zhihu.di.module;

import android.app.Dialog;

import com.baojiyule.baojicomponent.zhihu.mvp.contract.ZhihuDetailContract;
import com.baojiyule.baojicomponent.zhihu.mvp.model.ZhihuModel;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import me.jessyan.armscomponent.commonres.dialog.ProgresDialog;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 04/30/2019 10:09
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@Module
public abstract class ZhihuDetailModule {

    @Binds
    abstract ZhihuDetailContract.Model bindZhihuModel(ZhihuModel model);

    @ActivityScope
    @Provides
    static Dialog provideDialog(ZhihuDetailContract.View view) {
        return new ProgresDialog(view.getActivity());
    }
}