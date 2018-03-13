package cat.ycatapp.xandone.ui.joke;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cat.ycatapp.xandone.App;
import cat.ycatapp.xandone.R;
import cat.ycatapp.xandone.model.bean.JokeBean;
import cat.ycatapp.xandone.uitils.TimeUtil;

/**
 * author: xandone
 * created on: 2018/3/12 23:03
 */

public class JokeListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<JokeBean.RowsBean> list;

    public JokeListAdapter(List list) {
        this.list = list;
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

    public static class MyHolder extends RecyclerView.ViewHolder {
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

        public MyHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindView(JokeBean.RowsBean jokeBean) {
            if (jokeBean == null) {
                return;
            }
            item_joke_list_title.setText(jokeBean.getTitle());
            item_joke_list_content.setText(jokeBean.getContent());
            item_joke_list_like.setText(String.valueOf(jokeBean.getArticle_like_count()));
            item_joke_list_comment_count.setText(String.valueOf(jokeBean.getArticle_comment_count()));
            item_joke_list_date.setText(TimeUtil.getStringByFormat(jokeBean.getPost_time(), TimeUtil.dateFormat));
        }
    }

}
