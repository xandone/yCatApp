package cat.ycatapp.xandone.ui.joke;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cat.ycatapp.xandone.App;
import cat.ycatapp.xandone.R;
import cat.ycatapp.xandone.base.BaseActivity;
import cat.ycatapp.xandone.base.RxBaseFragment;
import cat.ycatapp.xandone.config.Constants;
import cat.ycatapp.xandone.model.bean.HeadArticleBean;
import cat.ycatapp.xandone.model.bean.JokeBean;
import cat.ycatapp.xandone.model.bean.JokeListBean;
import cat.ycatapp.xandone.model.bean.MainJokeBean;
import cat.ycatapp.xandone.ui.jokeadd.JokeAddActivity;
import cat.ycatapp.xandone.widget.LoadingLayout;

/**
 * author: xandone
 * created on: 2018/3/6 13:34
 */

public class JokeFragment extends RxBaseFragment<JokePresenter> implements JokeContact.View {
    @BindView(R.id.frag_joke_list)
    RecyclerView frag_joke_list;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.loadingLayout)
    LoadingLayout loadingLayout;
    @BindView(R.id.toolBar)
    Toolbar toolBar;

    private JokeListAdapter jokeListAdapter;
    private List<JokeBean> jokes;
    private List<HeadArticleBean.RowsBean> heads;
    private int mPage = 1;
    private int mCount = 10;

    private LoadingLayout.OnReloadListener onReloadListener;

    public static final int RQS_CODE_JOKEBEAN = 1;
    public static final String KEY_RQS_IS_THUMB = "key_rqs_is_thumb";

    @Override
    public int setLayout() {
        return R.layout.frag_joke_layout;
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    public void initData() {
        super.initData();

        setToolBar(toolBar, getString(R.string.x_joke_title), R.drawable.icon_show_left);

        jokes = new ArrayList<>();
        heads = new ArrayList<>();
        jokeListAdapter = new JokeListAdapter(mActivity, this, jokes, heads);
        frag_joke_list.setAdapter(jokeListAdapter);
        frag_joke_list.setLayoutManager(new LinearLayoutManager(App.sContext));

        mPresenter.getJokeList(mPage, mCount, JokeContact.MODE_ONE);
        mPresenter.getHeadAticleList(0, 10);
        loadingLayout.setLoadingTips(LoadingLayout.loading);

        onReloadListener = new LoadingLayout.OnReloadListener() {
            @Override
            public void reLoad() {
                mPage = 1;
                mPresenter.getJokeList(mPage, mCount, JokeContact.MODE_ONE);
                loadingLayout.setLoadingTips(LoadingLayout.loading);
            }
        };
        loadingLayout.setOnReloadListener(onReloadListener);

        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mPage = 1;
                mPresenter.getJokeList(mPage, mCount, JokeContact.MODE_ONE);
            }
        });

        mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                mPage++;
                mPresenter.getJokeList(mPage, mCount, JokeContact.MODE_MORE);
            }

        });

    }

    @Override
    public void showHeadAticle(List<HeadArticleBean.RowsBean> articleList) {
        heads.clear();
        heads.addAll(articleList);
        jokeListAdapter.notifyItemChanged(0);
    }

    @Override
    public void showContent(JokeListBean jokeBean) {
        mRefreshLayout.finishRefresh();
        if (jokeBean == null || jokeBean.getRows() == null || jokeBean.getRows().isEmpty()) {
            showMsg("无数据", LoadingLayout.empty);
            return;
        }
        showMsg("加载完毕", LoadingLayout.finish);
        jokes.clear();
        jokes.addAll(jokeBean.getRows());
        jokeListAdapter.notifyDataSetChanged();

        if (jokeBean.getTotal() <= mCount) {
            mRefreshLayout.setNoMoreData(true);
        }
    }

    @Override
    public void showContentMore(JokeListBean jokeBean) {
        mRefreshLayout.finishLoadMore();
        if (jokeBean == null || jokeBean.getRows() == null || jokeBean.getRows().isEmpty()) {
            return;
        }
        jokes.addAll(jokeBean.getRows());
        jokeListAdapter.notifyDataSetChanged();

        if (jokeBean.getTotal() <= mCount * mPage) {
            mRefreshLayout.setNoMoreData(true);
        }

    }

    @Override
    public void showMsg(String msg, int loadStatus) {
        mRefreshLayout.finishRefresh();
        mRefreshLayout.finishLoadMore();
        loadingLayout.setLoadingTips(loadStatus);
    }

    private void updataDatas(boolean isThumb, int position) {
        if (isThumb) {
            jokes.get(position).setArticle_like_count(jokes.get(position).getArticle_like_count() + 1);
            jokeListAdapter.notifyItemChanged(position);
        }
    }


    @OnClick({R.id.toolbar_add})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.toolbar_add:
                Intent intent = new Intent(mActivity, JokeAddActivity.class);
                startActivity(intent);
                break;
        }

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != BaseActivity.RESULT_OK) {
            return;
        }

        if (data == null) {
            return;
        }
        if (requestCode == 1) {
            boolean isThumb = data.getBooleanExtra(KEY_RQS_IS_THUMB, false);
            int position = data.getIntExtra(Constants.KEY_JOKEBEAN_POSITION, 0);
            updataDatas(isThumb, position);
        }
    }
}
