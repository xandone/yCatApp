package cat.ycatapp.xandone.ui.joke;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cat.ycatapp.xandone.App;
import cat.ycatapp.xandone.R;
import cat.ycatapp.xandone.base.RxBaseFragment;
import cat.ycatapp.xandone.model.bean.JokeBean;
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
    private List<JokeBean.RowsBean> jokes;
    private int mPage = 1;
    private int mCount = 10;

    private LoadingLayout.OnReloadListener onReloadListener;

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

        setToolBar(toolBar, getString(R.string.x_joke_title));

        jokes = new ArrayList<>();
        jokeListAdapter = new JokeListAdapter(mActivity, jokes);
        frag_joke_list.setAdapter(jokeListAdapter);
        frag_joke_list.setLayoutManager(new LinearLayoutManager(App.sContext));

        mPresenter.getJokeList(mPage, mCount, JokeContact.MODE_ONE);
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

        mRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                mPage++;
                mPresenter.getJokeList(mPage, mCount, JokeContact.MODE_MORE);
            }
        });

    }

    @Override
    public void showContent(JokeBean jokeBean) {
        mRefreshLayout.finishRefresh();
        if (jokeBean == null || jokeBean.getRows() == null || jokeBean.getRows().isEmpty()) {
            showMsg("无数据", LoadingLayout.empty);
            return;
        }
        showMsg("加载完毕", LoadingLayout.finish);
        jokes.clear();
        jokes.addAll(jokeBean.getRows());
        jokeListAdapter.notifyDataSetChanged();
    }

    @Override
    public void showContentMore(JokeBean jokeBean) {
        mRefreshLayout.finishLoadmore();
        if (jokeBean == null || jokeBean.getRows() == null || jokeBean.getRows().isEmpty()) {
            return;
        }
        jokes.addAll(jokeBean.getRows());
        jokeListAdapter.notifyDataSetChanged();
    }

    @Override
    public void showMsg(String msg, int loadStatus) {
        mRefreshLayout.finishRefresh();
        loadingLayout.setLoadingTips(loadStatus);
    }
}
