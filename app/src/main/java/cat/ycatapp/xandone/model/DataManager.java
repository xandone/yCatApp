package cat.ycatapp.xandone.model;

import java.util.List;
import java.util.Map;

import cat.ycatapp.xandone.api.http.HttpHelper;
import cat.ycatapp.xandone.model.base.BaseResponse;
import cat.ycatapp.xandone.model.bean.CommentBean;
import cat.ycatapp.xandone.model.bean.ImageBean;
import cat.ycatapp.xandone.model.bean.JokeBean;
import cat.ycatapp.xandone.model.bean.LoginBean;
import cat.ycatapp.xandone.model.bean.RegistBean;
import cat.ycatapp.xandone.model.bean.UserBean;
import io.reactivex.Flowable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

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
    public Flowable<BaseResponse<List<UserBean>>> regist(String name, String psw, String nick) {
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

    @Override
    public Flowable<BaseResponse<List<CommentBean.RowsBean>>> addComment(String jokeId, String userId, String details) {
        return mHttpHelper.addComment(jokeId, userId, details);
    }

    @Override
    public Flowable<BaseResponse<List<Object>>> addJoke(String title, String userId, String content) {
        return mHttpHelper.addJoke(title, userId, content);
    }

    @Override
    public Flowable<ImageBean> getImageList(int page, int count) {
        return mHttpHelper.getImageList(page, count);
    }

    @Override
    public Flowable<BaseResponse<List<UserBean>>> changeUserIcon(MultipartBody.Part part, Map<String, String> maps) {
        return mHttpHelper.changeUserIcon(part, maps);
    }

}
