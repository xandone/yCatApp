package cat.ycatapp.xandone.ui.joke;

import cat.ycatapp.xandone.base.BasePresenter;
import cat.ycatapp.xandone.base.BaseView;
import cat.ycatapp.xandone.model.base.BaseResponse;

/**
 * author: xandone
 * created on: 2018/3/15 16:35
 */

public interface JokeDetailsContact {
    interface View extends BaseView{
         void showContent(BaseResponse baseResponse);
         void thumbsJokeResult(BaseResponse baseResponse);
    }
    interface Presenter extends BasePresenter<View>{
        void getThumbsJoke(String jokeId, String jokeUserId);
        void thumbsJoke(String jokeId, String jokeUserId);
    }
}
