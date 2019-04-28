package com.baojiyule.baojicomponent.gank.component.service;

import android.content.Context;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.baojiyule.baojicomponent.gank.R;
import com.jess.arms.utils.ArmsUtils;

import me.jessyan.armscomponent.commonservice.RouterHub;
import me.jessyan.armscomponent.commonservice.gank.bean.GankInfo;
import me.jessyan.armscomponent.commonservice.gank.service.GankInfoService;

/**
 * ================================================
 * 向外提供服务的接口实现类, 在此类中实现一些具有特定功能的方法提供给外部, 即可让一个组件与其他组件或宿主进行交互
 *
 * @see <a href="https://github.com/JessYanCoding/ArmsComponent/wiki#2.2.3.2">CommonService wiki 官方文档</a>
 * Created by JessYan on 2018/4/27 14:27
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * ================================================
 */
@Route(path = RouterHub.GANK_SERVICE_GANKINFOSERVICE, name = "干货集中营信息服务")
public class GankInfoServiceImpl implements GankInfoService {
    private Context mContext;

    @Override
    public GankInfo getInfo() {
        return new GankInfo(ArmsUtils.getString(mContext, R.string.public_name_gank));
    }

    @Override
    public void init(Context context) {
        mContext = context;
    }
}
