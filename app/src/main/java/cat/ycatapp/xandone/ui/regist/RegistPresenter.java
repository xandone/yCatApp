package cat.ycatapp.xandone.ui.regist;


import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import cat.ycatapp.xandone.api.CommonSubscriber;
import cat.ycatapp.xandone.base.RxPresenter;
import cat.ycatapp.xandone.cache.UserInfoCache;
import cat.ycatapp.xandone.config.Constants;
import cat.ycatapp.xandone.model.DataManager;
import cat.ycatapp.xandone.model.base.BaseResponse;
import cat.ycatapp.xandone.model.bean.RegistBean;
import cat.ycatapp.xandone.model.bean.UserBean;
import cat.ycatapp.xandone.uitils.GsonUtil;
import cat.ycatapp.xandone.uitils.LogUtils;
import cat.ycatapp.xandone.uitils.SPUtils;
import cat.ycatapp.xandone.uitils.ToastUtils;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * author: xandone
 * created on: 2018/3/6 14:43
 */

public class RegistPresenter extends RxPresenter<RegistContact.View> implements RegistContact.Presenter {
    private DataManager mDataManager;

    @Inject
    RegistPresenter(DataManager dataManager) {
        this.mDataManager = dataManager;
    }

    @Override
    public void regist(final String name, final String psw, String nick) {
        Flowable<BaseResponse<List<UserBean>>> result = mDataManager.regist(name, psw, nick);

        addSubscrible(result.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new CommonSubscriber<BaseResponse<List<UserBean>>>(view) {
                    @Override
                    public void onNext(BaseResponse<List<UserBean>> baseResponse) {
                        if (baseResponse != null) {
                            if ("1".equals(baseResponse.getCode()) && baseResponse.getDataList() != null
                                    && !baseResponse.getDataList().isEmpty()) {
                                UserBean loginBean = baseResponse.getDataList().get(0);
                                loginBean.setUserName(name);
                                loginBean.setUserPsw(psw);
                                UserInfoCache.setLogin(true);
                                UserInfoCache.setUserBean(loginBean);

                                SPUtils spUtils = SPUtils.getInstance(Constants.USER_INFO_NAME);
                                String infoJson = GsonUtil.objToJson(loginBean);
                                spUtils.put(Constants.USER_INFO_KEY, infoJson);

                                Log.d("yandone", infoJson);
                            }
                            view.showContent(baseResponse);
                        } else {
                            ToastUtils.showShort("服务器异常,请稍后再试");
                        }
                    }
                })
        );
    }
}
