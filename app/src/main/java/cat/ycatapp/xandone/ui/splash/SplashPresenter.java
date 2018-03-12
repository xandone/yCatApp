package cat.ycatapp.xandone.ui.splash;

import android.util.Log;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import cat.ycatapp.xandone.base.RxPresenter;
import cat.ycatapp.xandone.model.DataManager;
import cat.ycatapp.xandone.model.base.BaseResponse;
import cat.ycatapp.xandone.model.bean.SplashBean;
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
    public SplashPresenter(DataManager dataManager) {
        this.mDataManager = dataManager;
    }

    @Override
    public void getContent() {
        Flowable<BaseResponse<List<SplashBean>>> result = mDataManager.splash();

        addSubscrible(result.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseResponse<List<SplashBean>>>() {
                    @Override
                    public void accept(@NonNull BaseResponse<List<SplashBean>> listBaseResponse) throws Exception {
                        view.showContent(listBaseResponse);
                        Log.d("yandone","1");
                        startAct();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        Log.d("yandone","2");
                        startAct();
                    }
                })
        );
    }

    private void startAct(){
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
