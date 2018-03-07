package cat.ycatapp.xandone.ui.login;

import java.util.List;

import javax.inject.Inject;

import cat.ycatapp.xandone.api.CommonSubscriber;
import cat.ycatapp.xandone.base.RxPresenter;
import cat.ycatapp.xandone.model.DataManager;
import cat.ycatapp.xandone.model.base.BaseResponse;
import cat.ycatapp.xandone.model.bean.LoginBean;
import cat.ycatapp.xandone.uitils.LogUtils;
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
    public void login(String email, String psw) {
        Flowable<BaseResponse<List<LoginBean>>> result = mDataManager.login(email, psw);
        addSubscrible(result.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new CommonSubscriber<BaseResponse<List<LoginBean>>>(view) {
                    @Override
                    public void onNext(BaseResponse<List<LoginBean>> bean) {

                        if (bean != null) {
                            LogUtils.d("借口：" + bean.getMsg());
                            view.showContent(bean);
                        }
                    }
                })
        );
    }
}
