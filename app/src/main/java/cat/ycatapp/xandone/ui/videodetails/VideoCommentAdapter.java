package cat.ycatapp.xandone.ui.videodetails;

import android.content.Context;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cat.ycatapp.xandone.R;
import cat.ycatapp.xandone.model.bean.CommentBean;


/**
 * author: xandone
 * created on: 2018/7/19 15:55
 */
public class VideoCommentAdapter extends BaseQuickAdapter<CommentBean.RowsBean, BaseViewHolder> {
    private Context context;

    public VideoCommentAdapter(Context context, int layoutResId, @Nullable List<CommentBean.RowsBean> data) {
        super(layoutResId, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, CommentBean.RowsBean item) {
        helper.setText(R.id.item_joke_comment_name, item.getComment_nick());
        helper.setText(R.id.item_joke_comment_details, item.getComment_details());
        helper.setText(R.id.item_joke_comment_date, "2018-7-19 16:42");

    }
}
