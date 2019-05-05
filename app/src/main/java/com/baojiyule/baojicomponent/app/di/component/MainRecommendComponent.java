package com.baojiyule.baojicomponent.app.di.component;

import dagger.BindsInstance;
import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.baojiyule.baojicomponent.app.di.module.MainRecommendModule;
import com.baojiyule.baojicomponent.app.mvp.contract.MainRecommendContract;

import com.jess.arms.di.scope.FragmentScope;
import com.baojiyule.baojicomponent.app.mvp.ui.fragment.MainRecommendFragment;


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
@FragmentScope
@Component(modules = MainRecommendModule.class, dependencies = AppComponent.class)
public interface MainRecommendComponent {
    void inject(MainRecommendFragment fragment);

    @Component.Builder
    interface Builder {
        @BindsInstance
        MainRecommendComponent.Builder view(MainRecommendContract.View view);

        MainRecommendComponent.Builder appComponent(AppComponent appComponent);

        MainRecommendComponent build();
    }
}