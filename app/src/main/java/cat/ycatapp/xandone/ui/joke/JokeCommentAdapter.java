package cat.ycatapp.xandone.ui.joke;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cat.ycatapp.xandone.R;
import cat.ycatapp.xandone.model.bean.CommentBean;
import cat.ycatapp.xandone.uitils.TimeUtil;
import cat.ycatapp.xandone.uitils.imgload.XGlide;
import cat.ycatapp.xandone.widget.UserCircleIcon;

/**
 * author: xandone
 * created on: 2018/3/15 22:30
 */

public class JokeCommentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<CommentBean.RowsBean> commentBeanList;
    private RequestManager requestManager;

    public JokeCommentAdapter(Context context, List list) {
        this.mContext = context;
        this.commentBeanList = list;
        requestManager = Glide.with(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_comment_list, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyHolder) {
            MyHolder myHolder = (MyHolder) holder;
            myHolder.bindView(commentBeanList.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return commentBeanList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_joke_comment_icon)
        UserCircleIcon item_joke_comment_icon;
        @BindView(R.id.item_joke_comment_name)
        TextView item_joke_comment_name;
        @BindView(R.id.item_joke_comment_details)
        TextView item_joke_comment_details;
        @BindView(R.id.item_joke_comment_date)
        TextView item_joke_comment_date;

        public MyHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindView(CommentBean.RowsBean commentBean) {
            if (commentBean == null) {
                return;
            }
            item_joke_comment_name.setText(commentBean.getComment_nick());
            item_joke_comment_details.setText(commentBean.getComment_details());
            item_joke_comment_date.setText(TimeUtil.getStringByFormat(commentBean.getComment_date(), TimeUtil.dateFormat));

            XGlide.loadImage(requestManager, item_joke_comment_icon, commentBean.getComment_icon(), R.drawable.df_icon);
        }
    }
}
