package com.baojiyule.baojicomponent.gank.mvp.presenter;

import android.app.Application;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.OnLifecycleEvent;
import android.support.v4.app.Fragment;
import android.support.v4.app.SupportActivity;
import android.support.v7.widget.RecyclerView;

import com.baojiyule.baojicomponent.gank.mvp.contract.GankHomeContract;
import com.baojiyule.baojicomponent.gank.mvp.model.entity.GankBaseResponse;
import com.baojiyule.baojicomponent.gank.mvp.model.entity.GankItemBean;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.utils.RxLifecycleUtils;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import me.jessyan.rxerrorhandler.handler.RetryWithDelay;

import static com.baojiyule.baojicomponent.gank.app.GankConstants.NUMBER_OF_PAGE;


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
public class GankHomePresenter extends BasePresenter<GankHomeContract.Model, GankHomeContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    AppManager mAppManager;
    @Inject
    Application mApplication;
    @Inject
    List<GankItemBean> mDatas;
    @Inject
    RecyclerView.Adapter mAdapter;

    private int lastPage = 1;

    @Inject
    public GankHomePresenter(GankHomeContract.Model model, GankHomeContract.View rootView) {
        super(model, rootView);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mApplication = null;
        this.mDatas = null;
        this.mAdapter = null;
    }

    /**
     * 使用 2017 Google IO 发布的 Architecture Components 中的 Lifecycles 的新特性 (此特性已被加入 Support library)
     * 使 {@code Presenter} 可以与 {@link SupportActivity} 和 {@link Fragment} 的部分生命周期绑定
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    void onCreate() {
        requestGirls(true);//打开 App 时自动加载列表
    }

    public void requestGirls(final boolean pullToRefresh) {
        if (pullToRefresh) lastPage = 1;//下拉刷新默认只请求第一页

        mModel.getGirlList(NUMBER_OF_PAGE, lastPage)
                .subscribeOn(Schedulers.io())
                .retryWhen(new RetryWithDelay(3, 2))//遇到错误时重试,第一个参数为重试几次,第二个参数为重试的间隔
                .doOnSubscribe(disposable -> {
                    if (pullToRefresh)
                        mRootView.showLoading();//显示下拉刷新的进度条
                    else
                        mRootView.startLoadMore();//显示上拉加载更多的进度条
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(() -> {
                    if (pullToRefresh)
                        mRootView.hideLoading();//隐藏下拉刷新的进度条
                    else
                        mRootView.endLoadMore();//隐藏上拉加载更多的进度条
                })
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))//使用 Rxlifecycle,使 Disposable 和 Activity 一起销毁
                .subscribe(new ErrorHandleSubscriber<GankBaseResponse<List<GankItemBean>>>(mErrorHandler) {
                    @Override
                    public void onNext(GankBaseResponse<List<GankItemBean>> datas) {
                        lastPage = lastPage + 1;
                        if (pullToRefresh) {
                            mDatas.clear();//如果是下拉刷新则清空列表
                            mDatas.addAll(datas.getResults());
                            mAdapter.notifyDataSetChanged();
                        } else {
                            int preEndIndex = mDatas.size();//更新之前列表总长度,用于确定加载更多的起始位置
                            mDatas.addAll(datas.getResults());
                            mAdapter.notifyItemRangeInserted(preEndIndex, datas.getResults().size());
                        }
                    }
                });
    }
}
