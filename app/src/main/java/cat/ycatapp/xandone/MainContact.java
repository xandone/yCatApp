package cat.ycatapp.xandone;

import cat.ycatapp.xandone.base.BasePresenter;
import cat.ycatapp.xandone.base.BaseView;

/**
 * author: xandone
 * created on: 2018/3/6 11:21
 */

public interface MainContact {
    interface View extends BaseView {

    }

    interface Presenter extends BasePresenter<View> {

        void regist(String name, String psw, String nick);
    }
}
