package cat.ycatapp.xandone.ui.video;


import java.util.Map;

import cat.ycatapp.xandone.base.BasePresenter;
import cat.ycatapp.xandone.base.BaseView;
import cat.ycatapp.xandone.model.video.VideoInfo;

/**
 * author: xandone
 * created on: 2018/7/19 19:10
 */
public interface VideoContact {
    interface MyView extends BaseView {
        void showContent(VideoInfo videoInfo);
    }

    interface Presenter extends BasePresenter<MyView> {

        void getVideoList(Map<String, String> value);
    }
}
