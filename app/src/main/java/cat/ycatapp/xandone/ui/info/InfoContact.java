package cat.ycatapp.xandone.ui.info;


import java.util.Map;

import cat.ycatapp.xandone.base.BasePresenter;
import cat.ycatapp.xandone.base.BaseView;
import okhttp3.MultipartBody;

/**
 * author: xandone
 * Created on: 2018/5/9 17:12
 */

public interface InfoContact {
    interface MyView extends BaseView {

    }

    interface Presenter extends BasePresenter<MyView> {

        void changeUserIcon(Map<String, String> maps, MultipartBody.Part part);

    }
}
