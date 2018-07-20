package cat.ycatapp.xandone.ui.videodetails;

import android.content.Context;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cat.ycatapp.xandone.R;
import cat.ycatapp.xandone.model.video.VideoInfo;


/**
 * author: xandone
 * created on: 2018/7/19 14:49
 */
public class VideoRecyclerAdapter extends BaseQuickAdapter<VideoInfo.ItemListBean, BaseViewHolder> {
    private Context context;

    public VideoRecyclerAdapter(Context context, int layoutResId, @Nullable List<VideoInfo.ItemListBean> data) {
        super(layoutResId, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, VideoInfo.ItemListBean item) {
        helper.setText(R.id.item_name_tv,item.getData().getTitle());
    }
}
