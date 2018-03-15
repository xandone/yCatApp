package cat.ycatapp.xandone.ui.joke;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import butterknife.BindView;
import cat.ycatapp.xandone.R;
import cat.ycatapp.xandone.base.RxBaseActivity;
import cat.ycatapp.xandone.widget.LoadingLayout;

/**
 * author: xandone
 * created on: 2018/3/15 22:04
 */

public class JokeCommentActivity extends RxBaseActivity {
    @BindView(R.id.act_joke_comment_list)
    RecyclerView act_joke_comment_list;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.loadingLayout)
    LoadingLayout loadingLayout;
    @BindView(R.id.toolBar)
    Toolbar toolBar;

    @Override
    public void initInject() {

    }

    @Override
    public int setLayout() {
        return R.layout.act_joke_comment_layout;
    }

    @Override
    public void initData() {
        super.initData();
        setToolBar(toolBar, getString(R.string.x_joke_comment_title));
    }
}
