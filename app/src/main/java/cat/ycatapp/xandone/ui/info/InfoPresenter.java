package cat.ycatapp.xandone.ui.info;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import cat.ycatapp.xandone.api.CommonSubscriber;
import cat.ycatapp.xandone.base.RxPresenter;
import cat.ycatapp.xandone.model.DataManager;
import cat.ycatapp.xandone.model.bean.ImageBean;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * author: xandone
 * Created on: 2018/5/9 17:12
 */

public class InfoPresenter extends RxPresenter<InfoContact.MyView> implements InfoContact.Presenter {
    private DataManager dataManager;

    @Inject
    public InfoPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void changeUserIcon(Map<String, String> maps, MultipartBody.Part part) {

        Flowable<ImageBean> result = dataManager.changeUserIcon(part, maps);
        addSubscrible(result.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new CommonSubscriber<ImageBean>(view) {
                    @Override
                    public void onNext(ImageBean jokeBean) {
                        super.onNext(jokeBean);

                    }
                })
        );
    }
}
