package cat.ycatapp.xandone.api.http;


import java.util.List;

import cat.ycatapp.xandone.model.base.BaseResponse;
import cat.ycatapp.xandone.model.bean.LoginBean;
import cat.ycatapp.xandone.model.bean.RegistBean;
import cat.ycatapp.xandone.model.bean.SplashBean;
import io.reactivex.Flowable;

/**
 * author: xandone
 * created on: 2018/3/6 8:53
 */

public interface HttpHelper {
    Flowable<BaseResponse<List<RegistBean>>> regist(String type, String id, String page);
    Flowable<BaseResponse<List<LoginBean>>> login(String name, String psw);
    Flowable<BaseResponse<List<SplashBean>>> splash();
}
