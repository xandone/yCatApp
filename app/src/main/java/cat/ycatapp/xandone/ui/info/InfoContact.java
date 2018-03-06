package cat.ycatapp.xandone.ui.info;

import cat.ycatapp.xandone.base.BasePresenter;
import cat.ycatapp.xandone.base.BaseView;

/**
 * author: xandone
 * created on: 2018/3/6 14:47
 */

public class InfoContact {

    interface View extends BaseView {

    }

    interface Presenter extends BasePresenter<View> {

        void regist(String name, String psw, String nick);
    }
}
