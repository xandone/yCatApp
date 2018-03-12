package cat.ycatapp.xandone.ui.splash;



import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import cat.ycatapp.xandone.base.RxPresenter;
import cat.ycatapp.xandone.cache.UserInfoCache;
import cat.ycatapp.xandone.config.Constants;
import cat.ycatapp.xandone.model.DataManager;
import cat.ycatapp.xandone.model.base.BaseResponse;
import cat.ycatapp.xandone.model.bean.UserBean;
import cat.ycatapp.xandone.uitils.GsonUtil;
import cat.ycatapp.xandone.uitils.SPUtils;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * author: xandone
 * created on: 2018/3/12 9:16
 */

public class SplashPresenter extends RxPresenter<SplashContact.View> implements SplashContact.Presenter {
    private DataManager mDataManager;

    @Inject
    SplashPresenter(DataManager dataManager) {
        this.mDataManager = dataManager;
    }

    @Override
    public void getContent(final String email, final String psw) {
        Flowable<BaseResponse<List<UserBean>>> result = mDataManager.login(email, psw);

        addSubscrible(result.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<BaseResponse<List<UserBean>>>() {
                            @Override
                            public void accept(@NonNull BaseResponse<List<UserBean>> baseResponse) throws Exception {

                                if (baseResponse != null) {
//                                     密码正确
                                    if ("1".equals(baseResponse.getCode()) && baseResponse.getDataList() != null
                                            && !baseResponse.getDataList().isEmpty()) {
                                        UserBean result = baseResponse.getDataList().get(0);
                                        result.setUserName(email);
                                        result.setUserPsw(psw);
                                        UserInfoCache.setUserBean(result);
                                        UserInfoCache.setLogin(true);

                                        String userResult = GsonUtil.objToJson(result);
                                        SPUtils spUtils = SPUtils.getInstance(Constants.USER_INFO_NAME);
                                        spUtils.put(Constants.USER_INFO_KEY, userResult);
                                    }

                                }
                                view.showContent(baseResponse);
                                startAct();
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(@NonNull Throwable throwable) throws Exception {
                                startAct();
                            }
                        })
        );
    }

    void startAct() {
        addSubscrible(Flowable.timer(2000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(@NonNull Long aLong) throws Exception {
                        view.jumpAct();
                    }
                })
        );
    }
}
