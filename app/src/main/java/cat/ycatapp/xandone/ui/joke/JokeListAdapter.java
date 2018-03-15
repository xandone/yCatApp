package cat.ycatapp.xandone.ui.joke;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import java.util.List;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cat.ycatapp.xandone.App;
import cat.ycatapp.xandone.R;
import cat.ycatapp.xandone.model.bean.JokeBean;
import cat.ycatapp.xandone.uitils.TimeUtil;
import cat.ycatapp.xandone.uitils.imgload.GlideLoader;
import cat.ycatapp.xandone.uitils.imgload.ImageLoadInterface;
import cat.ycatapp.xandone.uitils.imgload.XGlide;
import cat.ycatapp.xandone.widget.UserCircleIcon;

/**
 * author: xandone
 * created on: 2018/3/12 23:03
 */

public class JokeListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<JokeBean.RowsBean> list;
    private Context mContext;
    private RequestManager requestManager;

    public static final String JOKEBEAN_TAG = "JOKEBEAN_TAG";

    public JokeListAdapter(Context context, List list) {
        this.list = list;
        this.mContext = context;
        requestManager = Glide.with(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(App.sContext).inflate(R.layout.item_joke_list, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyHolder) {
            MyHolder myHolder = (MyHolder) holder;
            myHolder.bindView(list.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_joke_user_ll)
        LinearLayout item_joke_user_ll;
        @BindView(R.id.item_joke_list_title)
        TextView item_joke_list_title;
        @BindView(R.id.item_joke_list_content)
        TextView item_joke_list_content;
        @BindView(R.id.item_joke_list_like)
        TextView item_joke_list_like;
        @BindView(R.id.item_joke_list_comment_count)
        TextView item_joke_list_comment_count;
        @BindView(R.id.item_joke_list_date)
        TextView item_joke_list_date;
        @BindView(R.id.item_joke_user_name)
        TextView item_joke_user_name;
        @BindView(R.id.item_joke_user_icon)
        UserCircleIcon item_joke_user_icon;

        public MyHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.item_joke_list_root)
        public void click(View view) {
            switch (view.getId()) {
                case R.id.item_joke_list_root:
                    Intent intent = new Intent(mContext, JokeDetailsActivity.class);
                    intent.putExtra(JOKEBEAN_TAG, list.get(getLayoutPosition()));
                    mContext.startActivity(intent);
                    break;
            }
        }

        public void bindView(JokeBean.RowsBean jokeBean) {
            if (jokeBean == null) {
                return;
            }
            item_joke_user_name.setText(jokeBean.getJoke_user_nick());
            item_joke_list_title.setText(jokeBean.getTitle());
            item_joke_list_content.setText(jokeBean.getContent());
            item_joke_list_like.setText(String.valueOf(jokeBean.getArticle_like_count()));
            item_joke_list_comment_count.setText(String.valueOf(jokeBean.getArticle_comment_count()));
            item_joke_list_date.setText(TimeUtil.getStringByFormat(jokeBean.getPost_time(), TimeUtil.dateFormat));

            XGlide.loadImage(requestManager, item_joke_user_icon, jokeBean.getJoke_user_icon(), R.drawable.df_icon);
        }
    }

}
