package cat.ycatapp.xandone.ui.login;

import java.util.List;

import cat.ycatapp.xandone.base.BasePresenter;
import cat.ycatapp.xandone.base.BaseView;
import cat.ycatapp.xandone.model.base.BaseResponse;
import cat.ycatapp.xandone.model.bean.LoginBean;
import cat.ycatapp.xandone.model.bean.RegistBean;
import cat.ycatapp.xandone.ui.regist.RegistContact;

/**
 * author: xandone
 * created on: 2018/3/7 14:08
 */

public class LoginContact {

    interface View extends BaseView {
        void showContent(BaseResponse<List<LoginBean>> baseResponse);
    }

    interface Presenter extends BasePresenter<LoginContact.View> {

        void login(String email, String psw);
    }
}
