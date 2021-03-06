package cat.ycatapp.xandone.ui.joke;

import java.util.List;

import cat.ycatapp.xandone.base.BasePresenter;
import cat.ycatapp.xandone.base.BaseView;
import cat.ycatapp.xandone.model.base.BaseResponse;
import cat.ycatapp.xandone.model.bean.JokeBean;
import cat.ycatapp.xandone.model.bean.JokeListBean;

/**
 * author: xandone
 * created on: 2018/3/15 16:35
 */

public interface JokeDetailsContact {
    interface View extends BaseView {
        void showContent(BaseResponse<List<JokeBean>> baseResponse);

        void thumbsJokeResult(BaseResponse baseResponse);

        void showCollectionResult(boolean success, String msg);
    }

    interface Presenter extends BasePresenter<View> {
        void getThumbsJoke(String jokeId, String jokeUserId);

        void thumbsJoke(String jokeId, String jokeUserId);

        void addToCollection(JokeBean jokeBean);
    }
}
