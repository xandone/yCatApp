package cat.ycatapp.xandone.ui.video;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cat.ycatapp.xandone.R;
import cat.ycatapp.xandone.model.video.VideoInfo;
import cat.ycatapp.xandone.uitils.SimpleUtils;

/**
 * author: xandone
 * created on: 2018/7/19 16:59
 */
public class VideoListAdapter extends BaseQuickAdapter<VideoInfo.ItemListBean, BaseViewHolder> {
    private Context context;

    public VideoListAdapter(Context context, int layoutResId, @Nullable List<VideoInfo.ItemListBean> data) {
        super(layoutResId, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, VideoInfo.ItemListBean item) {
        helper.setText(R.id.item_video_title, item.getData().getTitle());
        helper.setText(R.id.item_video_time, SimpleUtils.int2STime(item.getData().getDuration()));

        ImageView imageView = helper.getView(R.id.item_video_img);
        Glide.with(context)
                .load(item.getData().getCover().getDetail())
                .into(imageView);
    }
}
