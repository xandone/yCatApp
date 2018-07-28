package cat.ycatapp.xandone.ui.collect;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cat.ycatapp.xandone.App;
import cat.ycatapp.xandone.R;
import cat.ycatapp.xandone.greendao.DaoManager;
import cat.ycatapp.xandone.greendao.gen.JokeBeanDao;
import cat.ycatapp.xandone.model.bean.JokeBean;
import cat.ycatapp.xandone.ui.joke.JokeDetailsActivity;
import cat.ycatapp.xandone.ui.joke.JokeFragment;
import cat.ycatapp.xandone.ui.joke.JokeListAdapter;
import cat.ycatapp.xandone.uitils.TimeUtil;
import cat.ycatapp.xandone.uitils.imgload.XGlide;

/**
 * author: xandone
 * created on: 2018/7/27 10:29
 */
public class CollectSimpleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<JokeBean> datas;
    private Activity context;

    public CollectSimpleAdapter(Activity context, List<JokeBean> list) {
        this.context = context;
        this.datas = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_collect_list, parent, false);
        return new CollectHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((CollectHolder) holder).bindView(position);
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    class CollectHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_joke_user_name)
        TextView item_joke_user_name;
        @BindView(R.id.item_joke_list_title)
        TextView item_joke_list_title;
        @BindView(R.id.item_joke_list_content)
        TextView item_joke_list_content;
        @BindView(R.id.item_joke_list_collect_date)
        TextView item_joke_list_collect_date;
        @BindView(R.id.item_joke_user_icon)
        ImageView item_joke_user_icon;

        CollectHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindView(int position) {
            JokeBean jokeBean = datas.get(position);

            item_joke_user_name.setText(jokeBean.getJoke_user_nick());
            item_joke_list_title.setText(jokeBean.getTitle());
            item_joke_list_content.setText(jokeBean.getContent());
            item_joke_list_collect_date.setText(TimeUtil.getStringByFormat(jokeBean.getPost_time(),
                    TimeUtil.dateFormat));

            XGlide.loadImage(Glide.with(context), item_joke_user_icon, jokeBean.getJoke_user_icon(),
                    R.drawable.df_icon);
        }

        @OnClick({R.id.collect_root, R.id.v_delete_tv})
        public void click(View view) {
            switch (view.getId()) {
                case R.id.collect_root:
                    Intent intent = new Intent(context, JokeDetailsActivity.class);
                    intent.putExtra(JokeListAdapter.KEY_JOKEBEAN, datas.get(getLayoutPosition()));
                    intent.putExtra(JokeListAdapter.KEY_JOKEBEAN_POSITION, getLayoutPosition());
                    context.startActivityForResult(intent, JokeFragment.RQS_CODE_JOKEBEAN);
                    break;
                case R.id.v_delete_tv:
                    try {
                        JokeBeanDao jokeBeanDao = App.getDaoSession().getJokeBeanDao();
                        jokeBeanDao.delete(datas.get(getLayoutPosition()));
                        datas.remove(datas.get(getLayoutPosition()));
                        notifyItemRemoved(getLayoutPosition());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    }
}
