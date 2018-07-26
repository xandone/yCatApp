package cat.ycatapp.xandone.ui.jokeadd;

import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import cat.ycatapp.xandone.api.CommonSubscriber;
import cat.ycatapp.xandone.api.DialogSubscriber;
import cat.ycatapp.xandone.base.RxPresenter;
import cat.ycatapp.xandone.cache.UserInfoCache;
import cat.ycatapp.xandone.config.Constants;
import cat.ycatapp.xandone.model.DataManager;
import cat.ycatapp.xandone.model.base.BaseResponse;
import cat.ycatapp.xandone.model.bean.UserBean;
import cat.ycatapp.xandone.uitils.GsonUtil;
import cat.ycatapp.xandone.uitils.SPUtils;
import cat.ycatapp.xandone.uitils.ToastUtils;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * author: xandone
 * Created by xandone on 2018/4/3.
 */

public class JokeAddPresenter extends RxPresenter<JokeAddContact.MyView> implements JokeAddContact.Presenter {

    private DataManager mDataManager;

    @Inject
    JokeAddPresenter(DataManager dataManager) {
        this.mDataManager = dataManager;
    }


    @Override
    public void addJoke(String title, String userId, String content) {
        Flowable<BaseResponse<List<Object>>> result = mDataManager.addJoke(title, userId, content);
        addSubscrible(result.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DialogSubscriber<BaseResponse<List<Object>>>(view) {
                    @Override
                    public void onNext(BaseResponse<List<Object>> baseResponse) {
                        super.onNext(baseResponse);
                        if (baseResponse != null && "1".equals(baseResponse.getCode())) {
                            view.showSuccess();
                        } else {
                            view.showFail();
                        }

                    }
                })
        );

    }
}
