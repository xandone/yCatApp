package cat.ycatapp.xandone.ui.videodetails;

import java.util.Map;

import cat.ycatapp.xandone.base.BasePresenter;
import cat.ycatapp.xandone.base.BaseView;
import cat.ycatapp.xandone.model.video.VideoInfo;

/**
 * author: xandone
 * created on: 2018/7/20 09:53
 */
public interface VideoOtherContact {
    interface MyView extends BaseView {
        void showContent(VideoInfo videoInfo);
    }

    interface Presenter extends BasePresenter<MyView> {
        void getVideoOtherList(Map<String, String> value);
    }
}
