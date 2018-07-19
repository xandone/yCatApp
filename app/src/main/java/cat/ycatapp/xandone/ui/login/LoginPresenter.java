package cat.ycatapp.xandone.ui.login;

import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import cat.ycatapp.xandone.api.CommonSubscriber;
import cat.ycatapp.xandone.base.RxPresenter;
import cat.ycatapp.xandone.cache.UserInfoCache;
import cat.ycatapp.xandone.config.Constants;
import cat.ycatapp.xandone.model.DataManager;
import cat.ycatapp.xandone.model.base.BaseResponse;
import cat.ycatapp.xandone.model.bean.UserBean;
import cat.ycatapp.xandone.uitils.GsonUtil;
import cat.ycatapp.xandone.uitils.SPUtils;
import cat.ycatapp.xandone.uitils.SimpleUtils;
import cat.ycatapp.xandone.uitils.ToastUtils;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * author: xandone
 * created on: 2018/3/7 14:09
 */

public class LoginPresenter extends RxPresenter<LoginContact.View> implements LoginContact.Presenter {
    private DataManager mDataManager;

    @Inject
    LoginPresenter(DataManager dataManager) {
        this.mDataManager = dataManager;
    }

    @Override
    public void login(final String email, final String psw) {
        Flowable<BaseResponse<List<UserBean>>> result = mDataManager.login(email, psw);
        addSubscrible(result.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new CommonSubscriber<BaseResponse<List<UserBean>>>(view) {
                    @Override
                    public void onNext(BaseResponse<List<UserBean>> baseResponse) {

                        if (!SimpleUtils.isNetworkConnected()) {
                            ToastUtils.showShort("没有网络");
                            return;
                        }

                        if (baseResponse != null) {
                            if ("1".equals(baseResponse.getCode()) && baseResponse.getDataList() != null
                                    && !baseResponse.getDataList().isEmpty()) {
                                UserBean loginBean = baseResponse.getDataList().get(0);
                                loginBean.setUserName(email);
                                loginBean.setUserPsw(psw);
                                UserInfoCache.setLogin(true);
                                UserInfoCache.setUserBean(loginBean);

                                SPUtils spUtils = SPUtils.getInstance(Constants.USER_INFO_NAME);
                                String infoJson = GsonUtil.objToJson(loginBean);
                                spUtils.put(Constants.USER_INFO_KEY, infoJson);

                                Log.d("yandone", "login===" + infoJson);
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
