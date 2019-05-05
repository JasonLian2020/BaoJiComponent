package com.baojiyule.baojicomponent.app.di.component;

import com.baojiyule.baojicomponent.app.di.module.HomeDetailModule;
import com.baojiyule.baojicomponent.app.mvp.contract.HomeDetailContract;
import com.baojiyule.baojicomponent.app.mvp.ui.fragment.HomeDetailFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.FragmentScope;

import dagger.BindsInstance;
import dagger.Component;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 05/05/2019 15:06
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@FragmentScope
@Component(modules = HomeDetailModule.class, dependencies = AppComponent.class)
public interface HomeDetailComponent {
    void inject(HomeDetailFragment fragment);

    @Component.Builder
    interface Builder {
        @BindsInstance
        HomeDetailComponent.Builder view(HomeDetailContract.View view);

        HomeDetailComponent.Builder appComponent(AppComponent appComponent);

        HomeDetailComponent build();
    }
}