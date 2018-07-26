package cat.ycatapp.xandone.ui.joke;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cat.ycatapp.xandone.App;
import cat.ycatapp.xandone.R;
import cat.ycatapp.xandone.base.RxBaseActivity;
import cat.ycatapp.xandone.cache.UserInfoCache;
import cat.ycatapp.xandone.model.base.BaseResponse;
import cat.ycatapp.xandone.model.bean.CommentBean;
import cat.ycatapp.xandone.model.bean.JokeBean;
import cat.ycatapp.xandone.model.bean.JokeListBean;
import cat.ycatapp.xandone.uitils.SimpleUtils;
import cat.ycatapp.xandone.uitils.ToastUtils;
import cat.ycatapp.xandone.uitils.XString;
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
    @BindView(R.id.act_joke_comment_et)
    EditText act_joke_comment_et;

    private JokeCommentAdapter jokeCommentAdapter;
    private List<CommentBean.RowsBean> comments;
    private int mPage = 1;
    private int mCount = 10;

    private LoadingLayout.OnReloadListener onReloadListener;
    private JokeBean jokeBean;

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

        jokeBean = (JokeBean) getIntent().getSerializableExtra(JokeListAdapter.KEY_JOKEBEAN);
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

        act_joke_comment_et.post(new Runnable() {
            @Override
            public void run() {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(act_joke_comment_et, 0);
            }
        });

    }

    @Override
    public void showContent(CommentBean commentBean) {
        mRefreshLayout.finishRefresh();

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
        mRefreshLayout.finishLoadmore();
        loadingLayout.setLoadingTips(loadStatus);
    }

    @Override
    public void showCommentResult(BaseResponse<List<CommentBean.RowsBean>> response) {
        dismissLoadingDialog();
        if (response == null || response.getDataList() == null || response.getDataList().isEmpty()) {
            return;
        }
        if ("1".equals(response.getCode())) {
            if (loadingLayout.getVisibility() == View.VISIBLE) {
                loadingLayout.setVisibility(View.GONE);
            }
            ToastUtils.showShort("评论成功");
            act_joke_comment_et.setText("");

            CommentBean.RowsBean commentBean = response.getDataList().get(0);
            comments.add(0, commentBean);
            jokeCommentAdapter.notifyItemChanged(0);
        } else {
            ToastUtils.showShort("评论失败");
        }
    }

    @Override
    public void showCommentError() {
        dismissLoadingDialog();
    }

    @OnClick({R.id.act_joke_comment_commit})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.act_joke_comment_commit:
                if (!UserInfoCache.isLogin()) {
                    ToastUtils.showShort("你还未登录");
                    break;
                }
                String jokeId = jokeBean.getJoke_id();
                String userId = UserInfoCache.getUserBean().getUserId();
                String details = act_joke_comment_et.getText().toString();
                if (XString.isEmpty(details)) {
                    ToastUtils.showShort("请输入内容");
                    break;
                }
                SimpleUtils.hideSoftInput(this);
                showLoadingDialog(false);
                mPresenter.addComment(jokeId, userId, details);
                break;
        }
    }
}
