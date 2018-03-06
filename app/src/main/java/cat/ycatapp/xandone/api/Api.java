package cat.ycatapp.xandone.api;

import java.util.List;
import java.util.Map;

import cat.ycatapp.xandone.model.base.BaseResponse;
import cat.ycatapp.xandone.model.bean.RegistBean;
import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * author: xandone
 * created on: 2018/3/6 9:23
 */

public interface Api {
    String HOST = "http://192.168.3.102:8080/";

    @GET("ycat/regist")
    Flowable<BaseResponse<List<RegistBean>>> regist(
            @Query("name") String name,
            @Query("password") String psw,
            @Query("nickname") String nick);
}
