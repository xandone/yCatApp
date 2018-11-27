package cat.ycatapp.xandone.ui.collect;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cat.ycatapp.xandone.R;
import cat.ycatapp.xandone.base.BaseActivity;
import cat.ycatapp.xandone.base.RxBaseActivity;
import cat.ycatapp.xandone.config.Constants;
import cat.ycatapp.xandone.model.bean.JokeBean;
import cat.ycatapp.xandone.ui.joke.JokeFragment;

/**
 * author: xandone
 * created on: 2018/7/26 17:50
 */
public class CollectionActivity extends RxBaseActivity<CollectPresenter> implements CollectContact.MyView {
    @BindView(R.id.collect_recycler)
    RecyclerView collect_recycler;
    @BindView(R.id.toolBar)
    Toolbar toolBar;

    private List<JokeBean> datas;
    private CollectSimpleAdapter mCollectAdapter;

    @Override
    public void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public int setLayout() {
        return R.layout.act_collect_layout;
    }

    @Override
    public void initData() {
        super.initData();

        setToolBar(toolBar, getTitle().toString(), R.drawable.back_icon);

        datas = new ArrayList<>();
        mCollectAdapter = new CollectSimpleAdapter(this, datas);
        collect_recycler.setAdapter(mCollectAdapter);
        collect_recycler.setLayoutManager(new LinearLayoutManager(this));

        //和SwipeMenuView冲突
//        mCollectAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                Intent intent = new Intent(CollectionActivity.this, JokeDetailsActivity.class);
//                intent.putExtra(JokeListAdapter.KEY_JOKEBEAN, datas.get(position));
//                intent.putExtra(JokeListAdapter.KEY_JOKEBEAN_POSITION, position);
//                startActivityForResult(intent, JokeFragment.RQS_CODE_JOKEBEAN);
//            }
//        });

        mPresenter.getJokeCollect();
    }

    @Override
    public void showJokeCollect(List<JokeBean> list) {
        if (list == null) {
            return;
        }
        datas.addAll(list);
        mCollectAdapter.notifyDataSetChanged();
    }

    private void updataDatas(boolean isThumb, int position) {
        if (isThumb) {
            datas.get(position).setArticle_like_count(datas.get(position).getArticle_like_count() + 1);
            mCollectAdapter.notifyItemChanged(position);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
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
            boolean isThumb = data.getBooleanExtra(JokeFragment.KEY_RQS_IS_THUMB, false);
            int position = data.getIntExtra(Constants.KEY_JOKEBEAN_POSITION, 0);
            updataDatas(isThumb, position);
        }
    }
}
