package cat.ycatapp.xandone.api;

import java.util.List;

import cat.ycatapp.xandone.model.base.BaseResponse;
import cat.ycatapp.xandone.model.bean.LoginBean;
import cat.ycatapp.xandone.model.bean.RegistBean;
import cat.ycatapp.xandone.model.bean.SplashBean;
import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * author: xandone
 * created on: 2018/3/6 9:23
 */

public interface Api {
    String HOST = "http://192.168.137.1:8080/";

    @GET("ycat/regist")
    Flowable<BaseResponse<List<RegistBean>>> regist(
            @Query("name") String name,
            @Query("password") String psw,
            @Query("nickname") String nick);

    @GET("ycat/login")
    Flowable<BaseResponse<List<LoginBean>>> login(
            @Query("name") String name,
            @Query("password") String psw);

    @GET("ycat/joke/test")
    Flowable<BaseResponse<List<SplashBean>>> splash();

}