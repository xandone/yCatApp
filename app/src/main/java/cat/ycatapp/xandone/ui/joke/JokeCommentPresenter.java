package cat.ycatapp.xandone.ui.joke;


import javax.inject.Inject;

import cat.ycatapp.xandone.api.CommonSubscriber;
import cat.ycatapp.xandone.base.RxPresenter;
import cat.ycatapp.xandone.model.DataManager;
import cat.ycatapp.xandone.model.bean.CommentBean;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * author: xandone
 * created on: 2018/3/16 9:08
 */

public class JokeCommentPresenter extends RxPresenter<JokeCommentContact.MyView> implements JokeCommentContact.Presenter {
    private DataManager dataManager;

    @Inject
    public JokeCommentPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }


    @Override
    public void getContentList(int page, int rows, String jokeId, final int mode) {
        Flowable<CommentBean> result=dataManager.getJokeCommentList(page,rows,jokeId);
        addSubscrible(result.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new CommonSubscriber<CommentBean>(view) {
                    @Override
                    public void onNext(CommentBean commentBean) {
                        super.onNext(commentBean);
                        if (mode == JokeCommentContact.MODE_ONE) {
                            view.showContent(commentBean);
                        } else if (mode == JokeCommentContact.MODE_MORE) {
                            view.showContentMore(commentBean);
                        }
                    }
                })
        );
    }
}
