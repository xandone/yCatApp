package cat.ycatapp.xandone.ui.collect;

import java.util.List;

import cat.ycatapp.xandone.base.BasePresenter;
import cat.ycatapp.xandone.base.BaseView;
import cat.ycatapp.xandone.model.bean.JokeBean;

/**
 * author: xandone
 * created on: 2018/7/26 17:52
 */
public interface CollectContact {
    interface MyView extends BaseView {

        void showJokeCollect(List<JokeBean> list);

    }

    interface Presenter extends BasePresenter<MyView> {

        void getJokeCollect();
    }
}
