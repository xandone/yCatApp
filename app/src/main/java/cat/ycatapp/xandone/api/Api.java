package cat.ycatapp.xandone.api;

import java.util.List;

import cat.ycatapp.xandone.model.base.BaseResponse;
import cat.ycatapp.xandone.model.bean.CommentBean;
import cat.ycatapp.xandone.model.bean.JokeBean;
import cat.ycatapp.xandone.model.bean.RegistBean;
import cat.ycatapp.xandone.model.bean.UserBean;
import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * author: xandone
 * created on: 2018/3/6 9:23
 */

public interface Api {
    //        String HOST = "http://192.168.3.102:8080/";
    String HOST = "http://192.168.137.1:8080/";

    @GET("ycat/regist")
    Flowable<BaseResponse<List<RegistBean>>> regist(
            @Query("name") String name,
            @Query("password") String psw,
            @Query("nickname") String nick);

    @GET("ycat/login")
    Flowable<BaseResponse<List<UserBean>>> login(
            @Query("name") String name,
            @Query("password") String psw);

    @GET("ycat/joke/list")
    Flowable<JokeBean> getJokeList(
            @Query("page") int page,
            @Query("rows") int count);

    @GET("ycat/joke/thumbs/self")
    Flowable<BaseResponse> getThumbsJoke(
            @Query("jokeId") String jokeId,
            @Query("jokeUserId") String jokeUserId);

    @GET("ycat/joke/thumbs")
    Flowable<BaseResponse> thumbsJoke(
            @Query("jokeId") String jokeId,
            @Query("jokeUserId") String jokeUserId);

    @GET("ycat/joke/comment/list")
    Flowable<CommentBean> getJokeCommentList(
            @Query("page") int page,
            @Query("rows") int rows,
            @Query("jokeId") String jokeId
    );

    @GET("ycat/joke/comment/add")
    Flowable<BaseResponse<List<CommentBean.RowsBean>>> addComment(
            @Query("jokeId") String jokeId,
            @Query("userId") String userId,
            @Query("details") String details
    );

}
