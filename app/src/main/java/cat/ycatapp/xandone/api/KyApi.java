package cat.ycatapp.xandone.api;

import java.util.Map;

import cat.ycatapp.xandone.model.video.VideoInfo;
import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * author: xandone
 * created on: 2018/7/19 22:08
 */
public interface KyApi {

    String HOST = "http://baobab.wandoujia.com/";

    @GET("api/v3/ranklist")
    Flowable<VideoInfo> getVideoList(@QueryMap Map<String, String> map);

}
