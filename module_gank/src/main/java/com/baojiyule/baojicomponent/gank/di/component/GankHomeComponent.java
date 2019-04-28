package com.baojiyule.baojicomponent.gank.di.component;

import com.baojiyule.baojicomponent.gank.di.module.GankHomeModule;
import com.baojiyule.baojicomponent.gank.mvp.contract.GankHomeContract;
import com.baojiyule.baojicomponent.gank.mvp.ui.activity.GankHomeActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;

import dagger.BindsInstance;
import dagger.Component;


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
@ActivityScope
@Component(modules = GankHomeModule.class, dependencies = AppComponent.class)
public interface GankHomeComponent {
    void inject(GankHomeActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        GankHomeComponent.Builder view(GankHomeContract.View view);

        GankHomeComponent.Builder appComponent(AppComponent appComponent);

        GankHomeComponent build();
    }
}