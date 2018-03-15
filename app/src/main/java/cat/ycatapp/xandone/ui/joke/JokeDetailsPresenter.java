package cat.ycatapp.xandone.ui.joke;

import javax.inject.Inject;

import cat.ycatapp.xandone.api.CommonSubscriber;
import cat.ycatapp.xandone.base.RxPresenter;
import cat.ycatapp.xandone.model.DataManager;
import cat.ycatapp.xandone.model.base.BaseResponse;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * author: xandone
 * created on: 2018/3/15 16:34
 */

public class JokeDetailsPresenter extends RxPresenter<JokeDetailsContact.View>implements JokeDetailsContact.Presenter{
    DataManager dataManager;

    @Inject
    public JokeDetailsPresenter(DataManager dataManager){
        this.dataManager=dataManager;
    }

    @Override
    public void getThumbsJoke(String jokeId, String jokeUserId) {
        Flowable<BaseResponse> result = dataManager.getThumbsJoke(jokeId, jokeUserId);
        addSubscrible(result.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new CommonSubscriber<BaseResponse>(view) {
                    @Override
                    public void onNext(BaseResponse baseResponse) {
                        super.onNext(baseResponse);
                        view.showContent(baseResponse);
                    }
                })
        );
    }

    @Override
    public void thumbsJoke(String jokeId, String jokeUserId) {
        Flowable<BaseResponse> result = dataManager.thumbsJoke(jokeId, jokeUserId);
        addSubscrible(result.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new CommonSubscriber<BaseResponse>(view) {
                    @Override
                    public void onNext(BaseResponse baseResponse) {
                        super.onNext(baseResponse);
                        view.thumbsJokeResult(baseResponse);
                    }
                })
        );
    }

}
