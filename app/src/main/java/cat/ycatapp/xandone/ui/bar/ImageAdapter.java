package cat.ycatapp.xandone.ui.bar;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cat.ycatapp.xandone.App;
import cat.ycatapp.xandone.R;
import cat.ycatapp.xandone.model.bean.ImageBean;
import cat.ycatapp.xandone.ui.img.ImageDetailsActivity;
import cat.ycatapp.xandone.uitils.imgload.XGlide;

/**
 * author: xandone
 * Created on: 2018/5/3 22:17
 */

public class ImageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ImageBean.RowsBean> datas;
    private Activity mContext;
    private RequestManager requestManager;

    public static final String IMAGEADAPTER_PIC = "IMAGEADAPTER_PIC";

    public ImageAdapter(Context context, List datas) {
        this.mContext = (Activity) context;
        this.datas = datas;
        requestManager = Glide.with(App.sContext);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_image_layout, parent, false);
        return new ImageHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ImageHolder) {
            ImageHolder imageHolder = (ImageHolder) holder;
            imageHolder.bindView(datas.get(position));
        }

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }


    class ImageHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_image_iv)
        ImageView item_image_iv;


        public ImageHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindView(ImageBean.RowsBean rowsBean) {
            if (rowsBean == null) {
                return;
            }
            XGlide.loadImage(requestManager, item_image_iv, rowsBean.getImgUrl(), R.drawable.img_place);

        }

        @OnClick({R.id.item_image_iv})
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.item_image_iv:
                    Intent intent = new Intent(mContext, ImageDetailsActivity.class);
                    intent.putExtra(IMAGEADAPTER_PIC, datas.get(getLayoutPosition()));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        mContext.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(mContext,
                                Pair.create((View) item_image_iv, "item_image_iv_trans")).toBundle());
                    } else {
                        mContext.startActivity(intent);
                    }
                    break;
            }
        }
    }

}
