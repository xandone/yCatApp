package cat.ycatapp.xandone.ui.info;


import java.util.List;
import java.util.Map;

import cat.ycatapp.xandone.base.BasePresenter;
import cat.ycatapp.xandone.base.BaseView;
import cat.ycatapp.xandone.model.base.BaseResponse;
import cat.ycatapp.xandone.model.bean.UserBean;
import okhttp3.MultipartBody;

/**
 * author: xandone
 * Created on: 2018/5/9 17:12
 */

public interface InfoContact {
    interface MyView extends BaseView {
        void showContent(BaseResponse<List<UserBean>> baseResponse);

    }

    interface Presenter extends BasePresenter<MyView> {

        void changeUserIcon(Map<String, String> maps, MultipartBody.Part part);

    }
}
