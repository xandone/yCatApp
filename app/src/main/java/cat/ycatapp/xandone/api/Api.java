package cat.ycatapp.xandone.api;

import java.util.List;
import java.util.Map;

import cat.ycatapp.xandone.model.base.BaseResponse;
import cat.ycatapp.xandone.model.bean.CommentBean;
import cat.ycatapp.xandone.model.bean.ImageBean;
import cat.ycatapp.xandone.model.bean.JokeBean;
import cat.ycatapp.xandone.model.bean.UserBean;
import cat.ycatapp.xandone.model.video.VideoInfo;
import io.reactivex.Flowable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * author: xandone
 * created on: 2018/3/6 9:23
 */

public interface Api {
        String HOST = "http://192.168.191.1:8080/";
//    String HOST = "http://192.168.137.1:8080/";

    @GET("ycat/regist")
    Flowable<BaseResponse<List<UserBean>>> regist(
            @Query("name") String name,
            @Query("password") String psw,
            @Query("nickname") String nick);

    //    @Headers("Cache-Control:public,max-age=0")
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

    @GET("ycat/joke/add")
    Flowable<BaseResponse<List<Object>>> addJoke(
            @Query("title") String title,
            @Query("joke_user_id") String userId,
            @Query("content") String content
    );

    @GET("ycat/imagelist")
    Flowable<ImageBean> getImageList(
            @Query("page") int page,
            @Query("rows") int count);

    @Multipart
    @POST("ycat/upload.do")
    Flowable<ImageBean> changeUserIcon(@Part("userId") String username,
                                       @PartMap() Map<String, RequestBody> files);

    @Multipart
    @POST("ycat/upload.do")
    Flowable<ImageBean> upImage(@QueryMap() Map<String, String> params,
                                @PartMap() Map<String, RequestBody> files);

    @Multipart
    @POST("ycat/user/upIcon")
    Flowable<BaseResponse<List<UserBean>>> changeUserIcon(@Part MultipartBody.Part part, @QueryMap Map<String, String> maps);

}
