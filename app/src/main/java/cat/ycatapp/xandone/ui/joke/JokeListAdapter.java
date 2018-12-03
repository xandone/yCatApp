package cat.ycatapp.xandone.ui.joke;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.google.gson.Gson;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cat.ycatapp.xandone.App;
import cat.ycatapp.xandone.R;
import cat.ycatapp.xandone.config.Constants;
import cat.ycatapp.xandone.model.bean.HeadArticleBean;
import cat.ycatapp.xandone.model.bean.JokeBean;
import cat.ycatapp.xandone.model.bean.JokeListBean;
import cat.ycatapp.xandone.model.bean.MainJokeBean;
import cat.ycatapp.xandone.uitils.TimeUtil;
import cat.ycatapp.xandone.uitils.imgload.XGlide;
import cat.ycatapp.xandone.widget.UserCircleIcon;

/**
 * author: xandone
 * created on: 2018/3/12 23:03
 */

public class JokeListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<JokeBean> list;
    private List<HeadArticleBean.RowsBean> articleList;
    private Activity mActivity;
    private Fragment mFragment;
    private RequestManager requestManager;

    private static final int TYPE_HEAD_ARTICLE = 1;
    private static final int TYPE_NORMAL = 2;


    public JokeListAdapter(Activity activity, Fragment fragment, List list, List articleList) {
        this.list = list;
        this.articleList = articleList;
        this.mActivity = activity;
        this.mFragment = fragment;
        requestManager = Glide.with(mActivity);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case TYPE_HEAD_ARTICLE:
                view = LayoutInflater.from(App.sContext).inflate(R.layout.item_head_article_list, parent, false);
                return new MyArticleHolder(view);
            default:
                view = LayoutInflater.from(App.sContext).inflate(R.layout.item_joke_list, parent, false);
                return new MyHolder(view);
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyHolder) {
            MyHolder myHolder = (MyHolder) holder;
            myHolder.bindView(list.get(position - 1));
        } else if (holder instanceof MyArticleHolder) {
            MyArticleHolder myHolder = (MyArticleHolder) holder;
            myHolder.bindView(articleList);
        }
    }

    @Override
    public int getItemCount() {
        return list.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? TYPE_HEAD_ARTICLE : TYPE_NORMAL;
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

        MyHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.item_joke_list_root)
        public void click(View view) {
            switch (view.getId()) {
                case R.id.item_joke_list_root:
                    Intent intent = new Intent(mActivity, JokeDetailsActivity.class);
                    intent.putExtra(Constants.KEY_JOKEBEAN, list.get(getLayoutPosition() - 1));
                    intent.putExtra(Constants.KEY_JOKEBEAN_POSITION, getLayoutPosition() - 1);
                    mFragment.startActivityForResult(intent, JokeFragment.RQS_CODE_JOKEBEAN);
                    break;
            }
        }

        void bindView(JokeBean jokeBean) {
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


    class MyArticleHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.banner)
        Banner banner;

        MyArticleHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindView(List<HeadArticleBean.RowsBean> articleList) {
            if (articleList == null) {
                return;
            }
            List<String> imgs = new ArrayList<>();
            for (int i = 0; i < articleList.size(); i++) {
                imgs.add(articleList.get(i).getImgUrl());
            }
            banner.setImages(imgs).setImageLoader(new GlideImageLoader()).start();
        }
    }


    class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            //Glide 加载图片简单用法
            Glide.with(context).load(path).into(imageView);
        }
    }

}
