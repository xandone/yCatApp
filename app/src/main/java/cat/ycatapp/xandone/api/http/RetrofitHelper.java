package cat.ycatapp.xandone.api.http;


import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import cat.ycatapp.xandone.api.Api;
import cat.ycatapp.xandone.api.KyApi;
import cat.ycatapp.xandone.model.base.BaseResponse;
import cat.ycatapp.xandone.model.bean.CommentBean;
import cat.ycatapp.xandone.model.bean.HeadArticleBean;
import cat.ycatapp.xandone.model.bean.ImageBean;
import cat.ycatapp.xandone.model.bean.JokeBean;
import cat.ycatapp.xandone.model.bean.JokeListBean;
import cat.ycatapp.xandone.model.bean.UserBean;
import cat.ycatapp.xandone.model.video.VideoInfo;
import io.reactivex.Flowable;
import okhttp3.MultipartBody;

/**
 * author: xandone
 * created on: 2018/3/6 8:53
 */

public class RetrofitHelper implements HttpHelper {
    private Api mApi;
    private KyApi mKyApi;

    @Inject
    public RetrofitHelper(Api api, KyApi kyApi) {
        this.mApi = api;
        this.mKyApi = kyApi;
    }

    @Override
    public Flowable<BaseResponse<List<UserBean>>> regist(String name, String psw, String nick) {
        return mApi.regist(name, psw, nick);
    }

    @Override
    public Flowable<BaseResponse<List<UserBean>>> login(String name, String psw) {
        return mApi.login(name, psw);
    }

    @Override
    public Flowable<JokeListBean> getJokeList(int page, int count) {
        return mApi.getJokeList(page, count);
    }

    @Override
    public Flowable<BaseResponse<List<JokeBean>>> getThumbsJoke(String jokeId, String jokeUserId) {
        return mApi.getThumbsJoke(jokeId, jokeUserId);
    }

    @Override
    public Flowable<BaseResponse> thumbsJoke(String jokeId, String jokeUserId) {
        return mApi.thumbsJoke(jokeId, jokeUserId);
    }

    @Override
    public Flowable<CommentBean> getJokeCommentList(int page, int rows, String jokeId) {
        return mApi.getJokeCommentList(page, rows, jokeId);
    }

    @Override
    public Flowable<BaseResponse<List<CommentBean.RowsBean>>> addComment(String jokeId, String userId, String details) {
        return mApi.addComment(jokeId, userId, details);
    }

    @Override
    public Flowable<BaseResponse<List<Object>>> addJoke(String title, String userId, String content) {
        return mApi.addJoke(title, userId, content);
    }

    @Override
    public Flowable<ImageBean> getImageList(int page, int count) {
        return mApi.getImageList(page, count);
    }

    @Override
    public Flowable<BaseResponse<List<UserBean>>> changeUserIcon(MultipartBody.Part part, Map<String, String> maps) {
        return mApi.changeUserIcon(part, maps);
    }

    @Override
    public Flowable<BaseResponse<List<ImageBean.RowsBean>>> upImage(MultipartBody.Part part, Map<String, String> maps) {
        return mApi.upImage(part, maps);
    }

    @Override
    public Flowable<VideoInfo> getVideoList(Map<String, String> map) {
        return mKyApi.getVideoList(map);
    }


    @Override
    public Flowable<HeadArticleBean> getHeadAticleList(int page, int count) {
        return mApi.getHeadAticleList(page, count);
    }

}
