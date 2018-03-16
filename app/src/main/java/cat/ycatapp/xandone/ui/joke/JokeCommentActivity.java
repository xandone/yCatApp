package cat.ycatapp.xandone.ui.joke;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cat.ycatapp.xandone.App;
import cat.ycatapp.xandone.R;
import cat.ycatapp.xandone.base.RxBaseActivity;
import cat.ycatapp.xandone.model.bean.CommentBean;
import cat.ycatapp.xandone.model.bean.JokeBean;
import cat.ycatapp.xandone.uitils.GsonUtil;
import cat.ycatapp.xandone.widget.LoadingLayout;

/**
 * author: xandone
 * created on: 2018/3/15 22:04
 */

public class JokeCommentActivity extends RxBaseActivity<JokeCommentPresenter> implements JokeCommentContact.MyView {
    @BindView(R.id.act_joke_comment_list)
    RecyclerView act_joke_comment_list;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.loadingLayout)
    LoadingLayout loadingLayout;
    @BindView(R.id.toolBar)
    Toolbar toolBar;

    private JokeCommentAdapter jokeCommentAdapter;
    private List<CommentBean.RowsBean> comments;
    private int mPage = 1;
    private int mCount = 10;

    private LoadingLayout.OnReloadListener onReloadListener;
    private JokeBean.RowsBean jokeBean;

    @Override
    public void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public int setLayout() {
        return R.layout.act_joke_comment_layout;
    }

    @Override
    public void initData() {
        super.initData();
        setToolBar(toolBar, getString(R.string.x_joke_comment_title));

        jokeBean = (JokeBean.RowsBean) getIntent().getSerializableExtra(JokeListAdapter.JOKEBEAN_TAG);
        if (jokeBean == null) {
            return;
        }

        comments = new ArrayList<>();
        jokeCommentAdapter = new JokeCommentAdapter(this, comments);
        act_joke_comment_list.setAdapter(jokeCommentAdapter);
        act_joke_comment_list.setLayoutManager(new LinearLayoutManager(App.sContext));

        mPresenter.getContentList(mPage, mCount, jokeBean.getJoke_id(), JokeCommentContact.MODE_ONE);
        loadingLayout.setLoadingTips(LoadingLayout.loading);

        onReloadListener = new LoadingLayout.OnReloadListener() {
            @Override
            public void reLoad() {
                mPage = 1;
                mPresenter.getContentList(mPage, mCount, jokeBean.getJoke_id(), JokeContact.MODE_ONE);
                loadingLayout.setLoadingTips(LoadingLayout.loading);
            }
        };
        loadingLayout.setOnReloadListener(onReloadListener);

        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mPage = 1;
                mPresenter.getContentList(mPage, mCount, jokeBean.getJoke_id(), JokeContact.MODE_ONE);
            }
        });

        mRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                mPage++;
                mPresenter.getContentList(mPage, mCount, jokeBean.getJoke_id(), JokeContact.MODE_MORE);
            }
        });

    }

    @Override
    public void showContent(CommentBean commentBean) {
        mRefreshLayout.finishRefresh();

        Log.d("yandone", GsonUtil.objToJson(commentBean));
        if (commentBean == null || commentBean.getRows() == null || commentBean.getRows().isEmpty()) {
            showMsg("无数据", LoadingLayout.empty);
            return;
        }
        showMsg("加载完毕", LoadingLayout.finish);
        comments.clear();
        comments.addAll(commentBean.getRows());
        jokeCommentAdapter.notifyDataSetChanged();
    }

    @Override
    public void showContentMore(CommentBean commentBean) {
        mRefreshLayout.finishLoadmore();
        if (commentBean == null || commentBean.getRows() == null || commentBean.getRows().isEmpty()) {
            return;
        }
        comments.addAll(commentBean.getRows());
        jokeCommentAdapter.notifyDataSetChanged();
    }

    @Override
    public void showMsg(String msg, int loadStatus) {
        mRefreshLayout.finishRefresh();
        loadingLayout.setLoadingTips(loadStatus);
    }
}
