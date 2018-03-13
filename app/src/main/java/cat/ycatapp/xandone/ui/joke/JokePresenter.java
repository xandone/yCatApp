package cat.ycatapp.xandone.ui.joke;


import javax.inject.Inject;

import cat.ycatapp.xandone.api.CommonSubscriber;
import cat.ycatapp.xandone.base.RxPresenter;
import cat.ycatapp.xandone.model.DataManager;
import cat.ycatapp.xandone.model.bean.JokeBean;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * author: xandone
 * created on: 2018/3/13 9:26
 */

public class JokePresenter extends RxPresenter<JokeContact.View> implements JokeContact.Presenter {
    private DataManager dataManager;

    @Inject
    public JokePresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public void getJokeList(int page, int count, final int mode) {
        Flowable<JokeBean> result = dataManager.getJokeList(page, count);
        addSubscrible(result.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new CommonSubscriber<JokeBean>(view) {
                    @Override
                    public void onNext(JokeBean jokeBean) {
                        super.onNext(jokeBean);
                        if (mode == JokeContact.MODE_ONE) {
                            view.showContent(jokeBean);
                        } else if (mode == JokeContact.MODE_MORE) {
                            view.showContentMore(jokeBean);
                        }
                    }
                })
        );
    }

}
