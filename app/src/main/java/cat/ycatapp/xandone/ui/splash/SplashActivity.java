package cat.ycatapp.xandone.ui.splash;


import android.content.Intent;
import android.util.Log;
import android.widget.ImageView;

import java.util.List;

import butterknife.BindView;
import cat.ycatapp.xandone.MainActivity;
import cat.ycatapp.xandone.R;
import cat.ycatapp.xandone.base.RxBaseActivity;
import cat.ycatapp.xandone.cache.UserInfoCache;
import cat.ycatapp.xandone.config.Constants;
import cat.ycatapp.xandone.model.base.BaseResponse;
import cat.ycatapp.xandone.model.bean.UserBean;
import cat.ycatapp.xandone.uitils.GsonUtil;
import cat.ycatapp.xandone.uitils.SPUtils;
import cat.ycatapp.xandone.uitils.imgload.GlideLoader;
import cat.ycatapp.xandone.uitils.imgload.ImageLoadInterface;

/**
 * author: xandone
 * created on: 2018/3/9 16:43
 */

public class SplashActivity extends RxBaseActivity<SplashPresenter> implements SplashContact.View {
    @BindView(R.id.act_splash_bg)
    ImageView act_splash_bg;

    public static final String img_Url = "https://upload-images.jianshu.io/upload_images/2518499-5922e67cdc70350f.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240";

    private ImageLoadInterface<ImageView> mLoader;

    @Override
    public int setLayout() {
        return R.layout.act_splash_layout;
    }

    @Override
    public void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public void initData() {
        super.initData();
        SPUtils spUtils = SPUtils.getInstance(Constants.USER_INFO_NAME);
        String sp_user = spUtils.getString(Constants.USER_INFO_KEY);
        UserBean userBean = GsonUtil.deserialize(sp_user, UserBean.class);
        if (userBean != null) {
            String userName = userBean.getUserName();
            String userPsw = userBean.getUserPsw();
            mPresenter.getContent(userName, userPsw);
        } else {
            mPresenter.startAct();
        }

        if (mLoader == null) {
            mLoader = new GlideLoader();
        }
    }

    @Override
    public void showContent(BaseResponse<List<UserBean>> baseResponse) {


    }

    @Override
    public void jumpAct() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
