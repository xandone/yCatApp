package cat.ycatapp.xandone.ui.jokeadd;

import cat.ycatapp.xandone.base.BasePresenter;
import cat.ycatapp.xandone.base.BaseView;

/**
 * author: xandone
 * Created by xandone on 2018/4/3.
 */

public interface JokeAddContact{
    interface MyView extends BaseView{

    }

    interface Presenter extends BasePresenter<MyView> {

        void addJoke(String title, String userId, String content);
    }
}
