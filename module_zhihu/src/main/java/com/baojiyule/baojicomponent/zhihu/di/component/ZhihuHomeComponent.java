package com.baojiyule.baojicomponent.zhihu.di.component;

import com.baojiyule.baojicomponent.zhihu.di.module.ZhihuHomeModule;
import com.baojiyule.baojicomponent.zhihu.mvp.contract.ZhihuHomeContract;
import com.baojiyule.baojicomponent.zhihu.mvp.ui.activity.ZhihuHomeActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;

import dagger.BindsInstance;
import dagger.Component;


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
@ActivityScope
@Component(modules = ZhihuHomeModule.class, dependencies = AppComponent.class)
public interface ZhihuHomeComponent {
    void inject(ZhihuHomeActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        ZhihuHomeComponent.Builder view(ZhihuHomeContract.View view);

        ZhihuHomeComponent.Builder appComponent(AppComponent appComponent);

        ZhihuHomeComponent build();
    }
}