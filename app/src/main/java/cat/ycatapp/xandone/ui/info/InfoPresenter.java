package cat.ycatapp.xandone.ui.info;


import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import cat.ycatapp.xandone.api.DialogSubscriber;
import cat.ycatapp.xandone.base.RxPresenter;
import cat.ycatapp.xandone.cache.UserInfoCache;
import cat.ycatapp.xandone.config.Constants;
import cat.ycatapp.xandone.model.DataManager;
import cat.ycatapp.xandone.model.base.BaseResponse;
import cat.ycatapp.xandone.model.bean.UserBean;
import cat.ycatapp.xandone.uitils.GsonUtil;
import cat.ycatapp.xandone.uitils.SPUtils;
import cat.ycatapp.xandone.uitils.ToastUtils;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;

/**
 * author: xandone
 * Created on: 2018/5/9 17:12
 */

public class InfoPresenter extends RxPresenter<InfoContact.MyView> implements InfoContact.Presenter {
    private DataManager dataManager;

    @Inject
    public InfoPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void changeUserIcon(Map<String, String> maps, MultipartBody.Part part) {

        Flowable<BaseResponse<List<UserBean>>> result = dataManager.changeUserIcon(part, maps);
        addSubscrible(result.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DialogSubscriber<BaseResponse<List<UserBean>>>(view) {
                    @Override
                    public void onNext(BaseResponse<List<UserBean>> baseResponse) {
                        super.onNext(baseResponse);
                        if (baseResponse != null) {
                            if ("1".equals(baseResponse.getCode()) && baseResponse.getDataList() != null
                                    && !baseResponse.getDataList().isEmpty()) {
                                UserBean userResponse = baseResponse.getDataList().get(0);
                                UserBean userCache = UserInfoCache.getUserBean();
                                userCache.setIconUrl(userResponse.getIconUrl());

                                UserInfoCache.setLogin(true);
                                UserInfoCache.setUserBean(userCache);

                                SPUtils spUtils = SPUtils.getInstance(Constants.USER_INFO_NAME);
                                String infoJson = GsonUtil.objToJson(userCache);
                                spUtils.put(Constants.USER_INFO_KEY, infoJson);

                            }
                            view.showContent(baseResponse);
                        } else {
                            ToastUtils.showShort("服务器异常,请稍后再试");
                        }

                    }
                })
        );
    }
}
