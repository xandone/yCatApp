package cat.ycatapp.xandone.ui.collect;

import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import cat.ycatapp.xandone.App;
import cat.ycatapp.xandone.base.RxPresenter;
import cat.ycatapp.xandone.greendao.DaoManager;
import cat.ycatapp.xandone.model.bean.JokeBean;

/**
 * author: xandone
 * created on: 2018/7/26 17:51
 */
public class CollectPresenter extends RxPresenter<CollectContact.MyView> implements CollectContact.Presenter {

    @Inject
    CollectPresenter() {

    }


    @Override
    public void getJokeCollect() {
        try {
            List<JokeBean> list = DaoManager.getInstance(App.sContext).getSession().getJokeBeanDao().loadAll();
            view.showJokeCollect(list);
        } catch (Exception e) {
            e.printStackTrace();
            view.showJokeCollect(null);
        }
    }
}
