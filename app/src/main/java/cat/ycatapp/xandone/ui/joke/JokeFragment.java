package cat.ycatapp.xandone.ui.joke;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cat.ycatapp.xandone.App;
import cat.ycatapp.xandone.MainPresenter;
import cat.ycatapp.xandone.R;
import cat.ycatapp.xandone.base.RxBaseFragment;
import cat.ycatapp.xandone.model.bean.JokeBean;

/**
 * author: xandone
 * created on: 2018/3/6 13:34
 */

public class JokeFragment extends RxBaseFragment<MainPresenter> {
    @BindView(R.id.frag_joke_list)
    RecyclerView frag_joke_list;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;

    private JokeListAdapter jokeListAdapter;
    private List<JokeBean> jokes;
    private int mPage = 1;

    @Override
    public int setLayout() {
        return R.layout.frag_joke_layout;
    }

    @Override
    protected void initInject() {
//        getFragmentComponent().inject(this);
    }

    @Override
    public void initData() {
        super.initData();

        jokes = new ArrayList<>();
        jokes.add(new JokeBean());
        jokes.add(new JokeBean());
        jokes.add(new JokeBean());
        jokes.add(new JokeBean());
        jokes.add(new JokeBean());
        jokes.add(new JokeBean());
        jokeListAdapter = new JokeListAdapter(jokes);
        frag_joke_list.setAdapter(jokeListAdapter);
        frag_joke_list.setLayoutManager(new LinearLayoutManager(App.sContext));

        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {

            }
        });

        mRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {

            }
        });
    }
}
