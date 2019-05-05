package com.baojiyule.baojicomponent.app.di.component;

import com.baojiyule.baojicomponent.app.di.module.MainUsercenterModule;
import com.baojiyule.baojicomponent.app.mvp.contract.MainUsercenterContract;
import com.baojiyule.baojicomponent.app.mvp.ui.fragment.MainUsercenterFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.FragmentScope;

import dagger.BindsInstance;
import dagger.Component;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 05/05/2019 12:06
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@FragmentScope
@Component(modules = MainUsercenterModule.class, dependencies = AppComponent.class)
public interface MainUsercenterComponent {
    void inject(MainUsercenterFragment fragment);

    @Component.Builder
    interface Builder {
        @BindsInstance
        MainUsercenterComponent.Builder view(MainUsercenterContract.View view);

        MainUsercenterComponent.Builder appComponent(AppComponent appComponent);

        MainUsercenterComponent build();
    }
}