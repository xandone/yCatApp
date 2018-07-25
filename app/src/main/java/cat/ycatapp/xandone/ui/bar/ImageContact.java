package cat.ycatapp.xandone.ui.bar;

import java.util.Map;

import cat.ycatapp.xandone.base.BasePresenter;
import cat.ycatapp.xandone.base.BaseView;
import cat.ycatapp.xandone.model.bean.ImageBean;
import okhttp3.MultipartBody;

/**
 * author: xandone
 * Created on: 2018/5/3 22:01
 */

public interface ImageContact {
    int MODE_ONE = 0;
    int MODE_MORE = 1;

    interface MyView extends BaseView {

        void showContent(ImageBean jokeBean);

        void showContentMore(ImageBean jokeBean);

        void showUpImageResult(ImageBean.RowsBean jokeBean);

    }

    interface MyPresenter extends BasePresenter<MyView> {

        void getImageList(int page, int count, int mode);

        void upImage(Map<String, String> maps, MultipartBody.Part part);

    }
}
