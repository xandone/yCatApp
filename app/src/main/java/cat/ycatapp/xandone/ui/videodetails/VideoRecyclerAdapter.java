package cat.ycatapp.xandone.ui.videodetails;

import android.content.Context;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cat.ycatapp.xandone.R;
import cat.ycatapp.xandone.model.video.VideoInfo;


/**
 * author: xandone
 * created on: 2018/7/19 14:49
 */
public class VideoRecyclerAdapter extends BaseMultiItemQuickAdapter<VideoInfo.ItemListBean, BaseViewHolder> {
    private Context context;

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public VideoRecyclerAdapter(Context context, List<VideoInfo.ItemListBean> data) {
        super(data);
        this.context = context;

        addItemType(VideoInfo.ItemListBean.HEADER, R.layout.item_video_header_layout);
        addItemType(VideoInfo.ItemListBean.NORMAL, R.layout.item_video_normal_layout);

    }

    @Override
    protected void convert(BaseViewHolder helper, VideoInfo.ItemListBean item) {
        switch (helper.getItemViewType()) {
            case VideoInfo.ItemListBean.HEADER:
                helper.setText(R.id.video_title, "google机器人");
                helper.setText(R.id.video_content, "atlas是谷歌旗下的波士顿动力公司研发的人形机器人。Atlas的的产品迭代大概经历了三个大的版本更新。2016年2月24日，波士顿动力公司展示了最新升级版的Atlas人形机器人");
                break;
            case VideoInfo.ItemListBean.NORMAL:
                helper.setText(R.id.item_name_tv, item.getData().getTitle());
                break;
        }

    }
}
