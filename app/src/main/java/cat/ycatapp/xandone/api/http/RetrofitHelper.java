package cat.ycatapp.xandone.api.http;


import java.util.List;

import javax.inject.Inject;

import cat.ycatapp.xandone.api.Api;
import cat.ycatapp.xandone.model.base.BaseResponse;
import cat.ycatapp.xandone.model.bean.JokeBean;
import cat.ycatapp.xandone.model.bean.LoginBean;
import cat.ycatapp.xandone.model.bean.RegistBean;
import cat.ycatapp.xandone.model.bean.UserBean;
import io.reactivex.Flowable;

/**
 * author: xandone
 * created on: 2018/3/6 8:53
 */

public class RetrofitHelper implements HttpHelper {
    private Api mApi;

    @Inject
    public RetrofitHelper(Api api) {
        this.mApi = api;
    }

    @Override
    public Flowable<BaseResponse<List<RegistBean>>> regist(String name, String psw, String nick) {
        return mApi.regist(name, psw, nick);
    }

    @Override
    public Flowable<BaseResponse<List<UserBean>>> login(String name, String psw) {
        return mApi.login(name, psw);
    }

    @Override
    public Flowable<JokeBean> getJokeList(int page, int count) {
        return mApi.getJokeList(page, count);
    }

}
