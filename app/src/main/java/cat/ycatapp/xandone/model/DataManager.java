package cat.ycatapp.xandone.model;

import java.util.List;

import cat.ycatapp.xandone.api.http.HttpHelper;
import cat.ycatapp.xandone.model.base.BaseResponse;
import cat.ycatapp.xandone.model.bean.CommentBean;
import cat.ycatapp.xandone.model.bean.JokeBean;
import cat.ycatapp.xandone.model.bean.LoginBean;
import cat.ycatapp.xandone.model.bean.RegistBean;
import cat.ycatapp.xandone.model.bean.UserBean;
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
    public Flowable<BaseResponse<List<UserBean>>> login(String name, String psw) {
        return mHttpHelper.login(name, psw);
    }

    @Override
    public Flowable<JokeBean> getJokeList(int page, int count) {
        return mHttpHelper.getJokeList(page, count);
    }

    @Override
    public Flowable<BaseResponse> getThumbsJoke(String jokeId, String jokeUserId) {
        return mHttpHelper.getThumbsJoke(jokeId, jokeUserId);
    }

    @Override
    public Flowable<BaseResponse> thumbsJoke(String jokeId, String jokeUserId) {
        return mHttpHelper.thumbsJoke(jokeId, jokeUserId);
    }

    @Override
    public Flowable<CommentBean> getJokeCommentList(int page, int rows, String jokeId) {
        return mHttpHelper.getJokeCommentList(page, rows, jokeId);
    }

}
