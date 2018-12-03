package cat.ycatapp.xandone.ui.img;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import butterknife.BindView;
import cat.ycatapp.xandone.App;
import cat.ycatapp.xandone.R;
import cat.ycatapp.xandone.base.RxBaseActivity;
import cat.ycatapp.xandone.model.bean.ImageBean;
import cat.ycatapp.xandone.ui.bar.ImageAdapter;
import cat.ycatapp.xandone.uitils.imgload.XGlide;

/**
 * author: xandone
 * Created on: 2018/5/7 22:48
 */

public class ImageDetailsActivity extends RxBaseActivity {
    @BindView(R.id.atc_img_iv)
    ImageView atc_img_iv;

    private ImageBean.RowsBean rowsBean;
    private RequestManager mRequestManager;

    @Override
    public void initInject() {


    }

    @Override
    public int setLayout() {
        return R.layout.act_img_details;
    }

    @Override
    public void initData() {
        super.initData();
        mRequestManager = Glide.with(App.sContext);

        rowsBean = (ImageBean.RowsBean) getIntent().getSerializableExtra(ImageAdapter.IMAGEADAPTER_PIC);
        if (rowsBean == null) {
            return;
        }

        XGlide.loadImage(mRequestManager, atc_img_iv, rowsBean.getImgUrl());

    }
}
