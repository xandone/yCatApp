package cat.ycatapp.xandone.ui.splash;

import java.util.List;

import cat.ycatapp.xandone.base.BasePresenter;
import cat.ycatapp.xandone.base.BaseView;
import cat.ycatapp.xandone.model.base.BaseResponse;
import cat.ycatapp.xandone.model.bean.SplashBean;

/**
 * author: xandone
 * created on: 2018/3/12 9:16
 */

public class SplashContact {

    interface  View extends BaseView{
        void showContent(BaseResponse<List<SplashBean>> baseResponse);
        void jumpAct();
    }

    interface Presenter extends BasePresenter<View> {
        void getContent();
    }


}
