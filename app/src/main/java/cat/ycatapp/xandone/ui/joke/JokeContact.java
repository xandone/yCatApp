package cat.ycatapp.xandone.ui.joke;

import java.util.List;

import cat.ycatapp.xandone.base.BasePresenter;
import cat.ycatapp.xandone.base.BaseView;
import cat.ycatapp.xandone.model.bean.HeadArticleBean;
import cat.ycatapp.xandone.model.bean.JokeListBean;

/**
 * author: xandone
 * created on: 2018/3/13 9:27
 */

public interface JokeContact {
    int MODE_ONE = 0;
    int MODE_MORE = 1;

    interface View extends BaseView {
        void showHeadAticle(List<HeadArticleBean.RowsBean> articleList);

        void showContent(JokeListBean jokeBean);

        void showContentMore(JokeListBean jokeBean);
    }

    interface Presenter extends BasePresenter<View> {
        void getJokeList(int page, int count, int mode);
    }

}
