package com.baojiyule.baojicomponent.zhihu.di.component;

import com.baojiyule.baojicomponent.zhihu.di.module.ZhihuDetailModule;
import com.baojiyule.baojicomponent.zhihu.mvp.contract.ZhihuDetailContract;
import com.baojiyule.baojicomponent.zhihu.mvp.ui.activity.ZhihuDetailActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;

import dagger.BindsInstance;
import dagger.Component;


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
@ActivityScope
@Component(modules = ZhihuDetailModule.class, dependencies = AppComponent.class)
public interface ZhihuDetailComponent {
    void inject(ZhihuDetailActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        ZhihuDetailComponent.Builder view(ZhihuDetailContract.View view);

        ZhihuDetailComponent.Builder appComponent(AppComponent appComponent);

        ZhihuDetailComponent build();
    }
}