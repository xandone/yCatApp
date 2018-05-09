package cat.ycatapp.xandone.ui.bar;


import javax.inject.Inject;

import cat.ycatapp.xandone.api.CommonSubscriber;
import cat.ycatapp.xandone.base.RxPresenter;
import cat.ycatapp.xandone.model.DataManager;
import cat.ycatapp.xandone.model.bean.ImageBean;
import cat.ycatapp.xandone.model.bean.JokeBean;
import cat.ycatapp.xandone.ui.joke.JokeContact;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * author: xandone
 * Created on: 2018/5/3 22:01
 */

public class ImagePresenter extends RxPresenter<ImageContact.MyView> implements ImageContact.MyPresenter {
    private DataManager dataManager;

    @Inject
    public ImagePresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void getImageList(int page, int count, final int mode) {
        Flowable<ImageBean> result = dataManager.getImageList(page, count);
        addSubscrible(result.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new CommonSubscriber<ImageBean>(view) {
                    @Override
                    public void onNext(ImageBean imageBean) {
                        super.onNext(imageBean);
                        if (mode == JokeContact.MODE_ONE) {
                            view.showContent(imageBean);
                        } else if (mode == JokeContact.MODE_MORE) {
                            view.showContentMore(imageBean);
                        }
                    }
                })
        );

    }
}
