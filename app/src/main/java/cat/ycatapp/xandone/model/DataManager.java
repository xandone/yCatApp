package cat.ycatapp.xandone.model;

import java.util.List;
import java.util.Map;

import cat.ycatapp.xandone.api.http.HttpHelper;
import cat.ycatapp.xandone.model.base.BaseResponse;
import cat.ycatapp.xandone.model.bean.LoginBean;
import cat.ycatapp.xandone.model.bean.RegistBean;
import cat.ycatapp.xandone.model.bean.SplashBean;
import io.reactivex.Flowable;

/**
 * author: xandone
 * created on: 2018/3/6 8:53
 */

public class DataManager implements HttpHelper {
    HttpHelper mHttpHelper;

    public DataManager(HttpHelper httpHelper) {
        this.mHttpHelper = httpHelper;
    }

    @Override
    public Flowable<BaseResponse<List<RegistBean>>> regist(String name, String psw, String nick) {
        return mHttpHelper.regist(name, psw, nick);
    }

    @Override
    public Flowable<BaseResponse<List<LoginBean>>> login(String name, String psw) {
        return mHttpHelper.login(name,psw);
    }

    @Override
    public Flowable<BaseResponse<List<SplashBean>>> splash() {
        return mHttpHelper.splash();
    }


}
