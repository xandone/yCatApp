package cat.ycatapp.xandone.api.http;


import java.util.List;

import cat.ycatapp.xandone.model.base.BaseResponse;
import cat.ycatapp.xandone.model.bean.CommentBean;
import cat.ycatapp.xandone.model.bean.ImageBean;
import cat.ycatapp.xandone.model.bean.JokeBean;
import cat.ycatapp.xandone.model.bean.RegistBean;
import cat.ycatapp.xandone.model.bean.UserBean;
import io.reactivex.Flowable;

/**
 * author: xandone
 * created on: 2018/3/6 8:53
 */

public interface HttpHelper {
    Flowable<BaseResponse<List<UserBean>>> regist(String type, String id, String page);

    Flowable<BaseResponse<List<UserBean>>> login(String name, String psw);

    Flowable<JokeBean> getJokeList(int page, int count);

    Flowable<BaseResponse> getThumbsJoke(String jokeId, String jokeUserId);

    Flowable<BaseResponse> thumbsJoke(String jokeId, String jokeUserId);

    Flowable<CommentBean> getJokeCommentList(int page, int rows, String jokeId);

    Flowable<BaseResponse<List<CommentBean.RowsBean>>> addComment(String jokeId, String userId, String details);

    Flowable<BaseResponse<List<Object>>> addJoke(String title, String userId, String content);

    Flowable<ImageBean> getImageList(int page, int count);
}
