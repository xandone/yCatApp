package cat.ycatapp.xandone.ui.regist;


import java.util.List;

import javax.inject.Inject;

import cat.ycatapp.xandone.api.CommonSubscriber;
import cat.ycatapp.xandone.base.RxPresenter;
import cat.ycatapp.xandone.model.DataManager;
import cat.ycatapp.xandone.model.base.BaseResponse;
import cat.ycatapp.xandone.model.bean.RegistBean;
import cat.ycatapp.xandone.uitils.LogUtils;
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
    public void regist(String name, String psw, String nick) {
        Flowable<BaseResponse<List<RegistBean>>> result = mDataManager.regist(name, psw, nick);

        addSubscrible(result.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new CommonSubscriber<BaseResponse<List<RegistBean>>>(view) {
                    @Override
                    public void onNext(BaseResponse<List<RegistBean>> registBeen) {
                        view.showContent(registBeen);
                    }
                })
        );
    }
}
