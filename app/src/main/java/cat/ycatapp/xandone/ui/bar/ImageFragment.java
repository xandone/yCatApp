package cat.ycatapp.xandone.ui.bar;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.Toolbar;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cat.ycatapp.xandone.R;
import cat.ycatapp.xandone.base.RxBaseFragment;
import cat.ycatapp.xandone.model.bean.ImageBean;
import cat.ycatapp.xandone.ui.joke.JokeContact;
import cat.ycatapp.xandone.widget.LoadingLayout;

/**
 * author: xandone
 * created on: 2018/3/6 13:35
 */

public class ImageFragment extends RxBaseFragment<ImagePresenter> implements ImageContact.MyView {
    @BindView(R.id.toolBar)
    Toolbar toolbar;
    @BindView(R.id.frag_img_refresh)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.frag_img_recycler)
    RecyclerView frag_img_recycler;
    @BindView(R.id.loadingLayout)
    LoadingLayout loadingLayout;


    private ImageAdapter mImageAdapter;
    private List<ImageBean.RowsBean> datas = new ArrayList<>();
    private int mPage = 1;
    private int mCount = 10;
    private LoadingLayout.OnReloadListener onReloadListener;


    @Override
    public int setLayout() {
        return R.layout.frag_bar_layout;
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }


    @Override
    public void initData() {
        super.initData();
        setToolBar(toolbar, getString(R.string.x_img_title));

        mImageAdapter = new ImageAdapter(mActivity, datas);
        LayoutManager layoutManager = new GridLayoutManager(mActivity, 2);
        frag_img_recycler.setAdapter(mImageAdapter);
        frag_img_recycler.setLayoutManager(layoutManager);


        mPresenter.getImageList(mPage, mCount, ImageContact.MODE_ONE);

        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mPage = 1;
                mPresenter.getImageList(mPage, mCount, ImageContact.MODE_ONE);
            }
        });

        mRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                mPage++;
                mPresenter.getImageList(mPage, mCount, ImageContact.MODE_MORE);
            }
        });

        loadingLayout.setLoadingTips(LoadingLayout.loading);

        onReloadListener = new LoadingLayout.OnReloadListener() {
            @Override
            public void reLoad() {
                mPage = 1;
                mPresenter.getImageList(mPage, mCount, JokeContact.MODE_ONE);
                loadingLayout.setLoadingTips(LoadingLayout.loading);
            }
        };
        loadingLayout.setOnReloadListener(onReloadListener);

    }

    @Override
    public void showContent(ImageBean jokeBean) {
        mRefreshLayout.finishRefresh();
        if (jokeBean == null || jokeBean.getRows() == null || jokeBean.getRows().isEmpty()) {
            showMsg("无数据", LoadingLayout.empty);
            return;
        }
        showMsg("加载完毕", LoadingLayout.finish);
        datas.clear();
        datas.addAll(jokeBean.getRows());
        mImageAdapter.notifyDataSetChanged();
    }

    @Override
    public void showContentMore(ImageBean imageBean) {
        mRefreshLayout.finishLoadmore();
        if (imageBean == null || imageBean.getRows() == null || imageBean.getRows().isEmpty()) {
            return;
        }
        datas.addAll(imageBean.getRows());
        mImageAdapter.notifyDataSetChanged();
    }

    @Override
    public void showMsg(String msg, int loadStatus) {
        mRefreshLayout.finishRefresh();
        mRefreshLayout.finishLoadmore();
        loadingLayout.setLoadingTips(loadStatus);
    }

}
