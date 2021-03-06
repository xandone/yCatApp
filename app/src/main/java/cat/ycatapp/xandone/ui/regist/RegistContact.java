package cat.ycatapp.xandone.ui.regist;

import java.util.List;

import cat.ycatapp.xandone.base.BasePresenter;
import cat.ycatapp.xandone.base.BaseView;
import cat.ycatapp.xandone.model.base.BaseResponse;
import cat.ycatapp.xandone.model.bean.RegistBean;
import cat.ycatapp.xandone.model.bean.UserBean;

/**
 * author: xandone
 * created on: 2018/3/6 14:47
 */

public interface RegistContact {

    interface View extends BaseView {
        void showContent(BaseResponse<List<UserBean>> baseResponse);
    }

    interface Presenter extends BasePresenter<View> {

        void regist(String name, String psw, String nick);
    }
}
