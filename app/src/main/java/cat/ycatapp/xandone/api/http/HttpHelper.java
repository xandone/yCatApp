package cat.ycatapp.xandone.api.http;


import java.util.List;
import java.util.Map;

import cat.ycatapp.xandone.model.base.BaseResponse;
import cat.ycatapp.xandone.model.bean.CommentBean;
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

public interface HttpHelper {
    Flowable<BaseResponse<List<UserBean>>> regist(String type, String id, String page);

    Flowable<BaseResponse<List<UserBean>>> login(String name, String psw);

    Flowable<JokeListBean> getJokeList(int page, int count);

    Flowable<BaseResponse<List<JokeBean>>> getThumbsJoke(String jokeId, String jokeUserId);

    Flowable<BaseResponse> thumbsJoke(String jokeId, String jokeUserId);

    Flowable<CommentBean> getJokeCommentList(int page, int rows, String jokeId);

    Flowable<BaseResponse<List<CommentBean.RowsBean>>> addComment(String jokeId, String userId, String details);

    Flowable<BaseResponse<List<Object>>> addJoke(String title, String userId, String content);

    Flowable<ImageBean> getImageList(int page, int count);

    Flowable<BaseResponse<List<UserBean>>> changeUserIcon(MultipartBody.Part part, Map<String, String> maps);

    Flowable<BaseResponse<List<ImageBean.RowsBean>>> upImage(MultipartBody.Part part, Map<String, String> maps);

    Flowable<VideoInfo> getVideoList(Map<String, String> map);
}
