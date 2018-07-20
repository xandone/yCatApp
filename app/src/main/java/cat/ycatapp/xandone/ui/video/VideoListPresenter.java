package cat.ycatapp.xandone.ui.video;



import java.util.Map;

import javax.inject.Inject;

import cat.ycatapp.xandone.api.CommonSubscriber;
import cat.ycatapp.xandone.base.RxPresenter;
import cat.ycatapp.xandone.model.DataManager;
import cat.ycatapp.xandone.model.video.VideoInfo;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * author: xandone
 * created on: 2018/7/19 19:09
 */
public class VideoListPresenter extends RxPresenter<VideoContact.MyView> implements VideoContact.Presenter {

    private DataManager mDataManager;

    @Inject
    VideoListPresenter(DataManager dataManager) {
        this.mDataManager = dataManager;
    }


    @Override
    public void getVideoList(Map<String, String> map) {
        Flowable<VideoInfo> result = mDataManager.getVideoList(map);
        addSubscrible(result.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new CommonSubscriber<VideoInfo>(view) {
                    @Override
                    public void onNext(VideoInfo videoInfo) {
                        view.showContent(videoInfo);
                    }

                })
        );
    }
}
