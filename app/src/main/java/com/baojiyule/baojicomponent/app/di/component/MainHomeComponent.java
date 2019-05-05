package com.baojiyule.baojicomponent.app.di.component;

import com.baojiyule.baojicomponent.app.di.module.MainHomeModule;
import com.baojiyule.baojicomponent.app.mvp.contract.MainHomeContract;
import com.baojiyule.baojicomponent.app.mvp.ui.fragment.MainHomeFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.FragmentScope;

import dagger.BindsInstance;
import dagger.Component;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 05/05/2019 12:02
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@FragmentScope
@Component(modules = MainHomeModule.class, dependencies = AppComponent.class)
public interface MainHomeComponent {
    void inject(MainHomeFragment fragment);

    @Component.Builder
    interface Builder {
        @BindsInstance
        MainHomeComponent.Builder view(MainHomeContract.View view);

        MainHomeComponent.Builder appComponent(AppComponent appComponent);

        MainHomeComponent build();
    }
}