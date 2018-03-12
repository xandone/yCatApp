package cat.ycatapp.xandone.ui.splash;


import android.content.Intent;
import android.widget.ImageView;

import java.util.List;

import butterknife.BindView;
import cat.ycatapp.xandone.MainActivity;
import cat.ycatapp.xandone.R;
import cat.ycatapp.xandone.base.RxBaseActivity;
import cat.ycatapp.xandone.model.base.BaseResponse;
import cat.ycatapp.xandone.model.bean.SplashBean;
import cat.ycatapp.xandone.uitils.imgload.GlideLoader;
import cat.ycatapp.xandone.uitils.imgload.ImageLoadInterface;

/**
 * author: xandone
 * created on: 2018/3/9 16:43
 */

public class SplashActivity extends RxBaseActivity<SplashPresenter> implements SplashContact.View {
    @BindView(R.id.act_splash_bg)
    ImageView act_splash_bg;

    public static final String img_Url="https://upload-images.jianshu.io/upload_images/2518499-5922e67cdc70350f.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240";

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
        mPresenter.getContent();
        if (mLoader == null) {
            mLoader = new GlideLoader();
        }
    }

    @Override
    public void showContent(BaseResponse<List<SplashBean>> baseResponse) {
        mLoader.displayImage(this, img_Url, act_splash_bg);
    }

    @Override
    public void jumpAct() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
