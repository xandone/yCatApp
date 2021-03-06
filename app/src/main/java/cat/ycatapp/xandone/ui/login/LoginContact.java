package cat.ycatapp.xandone.ui.login;

import java.util.List;

import cat.ycatapp.xandone.base.BasePresenter;
import cat.ycatapp.xandone.base.BaseView;
import cat.ycatapp.xandone.model.base.BaseResponse;
import cat.ycatapp.xandone.model.bean.UserBean;

/**
 * author: xandone
 * created on: 2018/3/7 14:08
 */

public interface LoginContact {

    interface View extends BaseView {
        void showContent(BaseResponse<List<UserBean>> baseResponse);
    }

    interface Presenter extends BasePresenter<View> {

        void login(String email, String psw);
    }
}
