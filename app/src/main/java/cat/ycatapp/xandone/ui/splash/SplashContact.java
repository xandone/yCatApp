package cat.ycatapp.xandone.ui.splash;

import java.util.List;

import cat.ycatapp.xandone.base.BasePresenter;
import cat.ycatapp.xandone.base.BaseView;
import cat.ycatapp.xandone.model.base.BaseResponse;
import cat.ycatapp.xandone.model.bean.UserBean;

/**
 * author: xandone
 * created on: 2018/3/12 9:16
 */

public interface SplashContact {

    interface  View extends BaseView{
        void showContent(BaseResponse<List<UserBean>> baseResponse);
        void jumpAct();
    }

    interface Presenter extends BasePresenter<View> {
        void getContent( String email,  String psw);
    }


}
