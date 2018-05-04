package cat.ycatapp.xandone.ui.bar;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cat.ycatapp.xandone.App;
import cat.ycatapp.xandone.R;
import cat.ycatapp.xandone.model.bean.ImageBean;
import cat.ycatapp.xandone.uitils.imgload.XGlide;

/**
 * author: xandone
 * Created on: 2018/5/3 22:17
 */

public class ImageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ImageBean.RowsBean> datas;
    private Context context;
    private RequestManager requestManager;

    public ImageAdapter(Context context, List datas) {
        this.context = context;
        this.datas = datas;
        requestManager = Glide.with(App.sContext);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_image_layout, parent, false);
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
            Log.d("yandone", "tup:-----" + rowsBean.getImgUrl());
            XGlide.loadImage(requestManager, item_image_iv, rowsBean.getImgUrl(), R.drawable.img_place);

        }
    }

}
