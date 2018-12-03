package cat.ycatapp.xandone.ui.bar;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import cat.ycatapp.xandone.R;
import cat.ycatapp.xandone.base.RxBaseFragment;
import cat.ycatapp.xandone.cache.UserInfoCache;
import cat.ycatapp.xandone.model.bean.ImageBean;
import cat.ycatapp.xandone.ui.joke.JokeContact;
import cat.ycatapp.xandone.uitils.ToastUtils;
import cat.ycatapp.xandone.widget.BottomDialog;
import cat.ycatapp.xandone.widget.LoadingLayout;
import me.iwf.photopicker.PhotoPicker;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

import static android.app.Activity.RESULT_OK;

/**
 * author: xandone
 * created on: 2018/3/6 13:35
 */

public class ImageFragment extends RxBaseFragment<ImagePresenter> implements ImageContact.MyView, View.OnClickListener {
    @BindView(R.id.toolBar)
    Toolbar toolbar;
    @BindView(R.id.frag_img_refresh)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.frag_img_recycler)
    RecyclerView frag_img_recycler;
    @BindView(R.id.loadingLayout)
    LoadingLayout loadingLayout;

    private ImageAdapter mImageAdapter;
    private ArrayList<ImageBean.RowsBean> datas = new ArrayList<>();
    private int mPage = 1;
    private int mCount = 10;
    private LoadingLayout.OnReloadListener onReloadListener;
    private BottomDialog bottomDialog;


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
        LayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
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

        mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
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
    public void showContent(ImageBean imageBean) {
        mRefreshLayout.finishRefresh();
        if (imageBean == null || imageBean.getRows() == null || imageBean.getRows().isEmpty()) {
            showMsg("无数据", LoadingLayout.empty);
            return;
        }
        showMsg("加载完毕", LoadingLayout.finish);
        datas.clear();
        datas.addAll(imageBean.getRows());
        mImageAdapter.notifyDataSetChanged();

        if (imageBean.getTotal() <= mCount) {
            mRefreshLayout.setNoMoreData(true);
        }
    }

    @Override
    public void showContentMore(ImageBean imageBean) {
        mRefreshLayout.finishLoadMore();
        if (imageBean == null || imageBean.getRows() == null || imageBean.getRows().isEmpty()) {
            return;
        }
        datas.addAll(imageBean.getRows());
        mImageAdapter.notifyDataSetChanged();

        if (imageBean.getTotal() <= mCount * mPage) {
            mRefreshLayout.setNoMoreData(true);
        }
    }

    @Override
    public void showUpImageResult(ImageBean.RowsBean imageBean) {
        datas.add(0, imageBean);
        mImageAdapter.notifyDataSetChanged();
    }

    @Override
    public void showMsg(String msg, int loadStatus) {
        mRefreshLayout.finishRefresh();
        mRefreshLayout.finishLoadMore();
        loadingLayout.setLoadingTips(loadStatus);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == PhotoPicker.REQUEST_CODE) {
            if (data != null) {
                ArrayList<String> photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
                if (photos != null && !photos.isEmpty()) {
                    compressImg(photos.get(0));
                }
            }
        }
    }

    public void upImageDialog() {
        bottomDialog = BottomDialog.create(getChildFragmentManager());
        bottomDialog.setLayoutRes(R.layout.bottom_dialog_layout)
                .setCancelOutside(true)
                .setDimAmount(0.4f)
                .setViewListener(new BottomDialog.ViewListener() {
                    @Override
                    public void bindView(View v) {
                        TextView bottom_dialog_photo = (TextView) v.findViewById(R.id.bottom_dialog_photo);
                        TextView bottom_dialog_camera = (TextView) v.findViewById(R.id.bottom_dialog_camera);
                        TextView bottom_dialog_cancel = (TextView) v.findViewById(R.id.bottom_dialog_cancel);
                        bottom_dialog_photo.setText("相册");
                        bottom_dialog_camera.setText("拍照");
                        bottom_dialog_cancel.setText("取消");

                        bottom_dialog_photo.setOnClickListener(ImageFragment.this);
                        bottom_dialog_camera.setOnClickListener(ImageFragment.this);
                        bottom_dialog_cancel.setOnClickListener(ImageFragment.this);
                    }
                }).show();
    }

    public void compressImg(String photos) {
        Luban.with(mActivity)
                .load(photos)
                .ignoreBy(100)
                .setCompressListener(new OnCompressListener() {
                    @Override
                    public void onStart() {
                    }

                    @Override
                    public void onSuccess(File file) {
                        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                        MultipartBody.Part body =
                                MultipartBody.Part.createFormData("file", file.getName(), requestFile);
                        Map<String, String> map = new HashMap<>();
                        map.put("userId", UserInfoCache.getUserBean().getUserId());

                        mPresenter.upImage(map, body);
                        showLoadingDialog(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                    }
                }).launch();    //启动压缩
    }

    @OnClick({R.id.toolbar_add})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.toolbar_add:
                if (!UserInfoCache.isLogin()) {
                    ToastUtils.showShort("登录后才可以发照片");
                    break;
                }
                upImageDialog();
                break;
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bottom_dialog_photo:
                if (bottomDialog != null) {
                    bottomDialog.dismiss();
                }
                PhotoPicker.builder()
                        .setPhotoCount(1)
                        .setShowCamera(true)
                        .setShowGif(true)
                        .setPreviewEnabled(false)
                        .start(mActivity, this);
                break;
            case R.id.bottom_dialog_camera:
                if (bottomDialog != null) {
                    bottomDialog.dismiss();
                }
                break;
            case R.id.bottom_dialog_cancel:
                if (bottomDialog != null) {
                    bottomDialog.dismiss();
                }
                break;
        }
    }
}
