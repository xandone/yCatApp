package cat.ycatapp.xandone.base;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * author: xandone
 * created on: 2018/3/5 15:30
 */

public abstract class BaseFragment extends Fragment implements BaseView {

    protected BaseActivity mActivity;

    //Fragment的View加载完毕的标记
    protected boolean isViewCreated;

    //Fragment对用户可见的标记
    protected boolean isUIVisible;

    @Override
    public void onAttach(Context context) {
        this.mActivity = (BaseActivity) context;
        super.onAttach(context);
    }

    public abstract int setLayout();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(setLayout(), container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isViewCreated = true;
        ButterKnife.bind(this, view);
        initData();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            isUIVisible = true;
            lazyLoad();
        } else {
            isUIVisible = false;
        }
    }

    public abstract void initData();

    public void lazyLoad() {
        if (isViewCreated && isUIVisible) {
            lazyLoadData();
            //数据加载完毕,恢复标记,防止重复加载
            isViewCreated = false;
            isUIVisible = false;
        }
    }

    protected abstract void lazyLoadData();

    @Override
    public void showMsg(String msg, int loadStatus) {

    }

    @Override
    public void stateError() {

    }

    @Override
    public void stateEmpty() {

    }

    @Override
    public void stateLoading() {

    }

    @Override
    public void stateMain() {

    }

    public void setToolBar(Toolbar toolbar, String title) {
        toolbar.setTitle(title);
        mActivity.setSupportActionBar(toolbar);
        mActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mActivity.getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationIcon(null);
    }

    protected android.app.AlertDialog showDialog(String msg,
                                                 String positiveBtn, DialogInterface.OnClickListener positiveListener,
                                                 String negativeBtn, DialogInterface.OnClickListener negativeListener) {
        return showDialog(msg, positiveBtn, positiveListener, negativeBtn, negativeListener, false);
    }

    protected android.app.AlertDialog showDialog(String msg,
                                                 String positiveBtn, DialogInterface.OnClickListener positiveListener,
                                                 String negativeBtn, DialogInterface.OnClickListener negativeListener,
                                                 boolean cancelable) {
        return showDialog(msg, positiveBtn, positiveListener, negativeBtn, negativeListener, null, null, cancelable);
    }

    protected android.app.AlertDialog showDialog(String msg,
                                                 String positiveBtn, DialogInterface.OnClickListener positiveListener,
                                                 String negativeBtn, DialogInterface.OnClickListener negativeListener,
                                                 String neutralBtn, DialogInterface.OnClickListener neutralListener,
                                                 boolean cancelable) {
        return showDialog("提示", msg, positiveBtn, positiveListener, negativeBtn, negativeListener, neutralBtn, neutralListener, cancelable);
    }

    private android.app.AlertDialog showDialog(String title, String msg,
                                               String positiveBtn, DialogInterface.OnClickListener positiveListener,
                                               String negativeBtn, DialogInterface.OnClickListener negativeListener,
                                               String neutralBtn, DialogInterface.OnClickListener neutralListener,
                                               boolean cancelable) {
        return new android.app.AlertDialog.Builder(mActivity)
                .setTitle(title)
                .setMessage(msg)
                .setCancelable(cancelable)
                .setPositiveButton(positiveBtn, positiveListener)
                .setNegativeButton(negativeBtn, negativeListener)
                .setNeutralButton(neutralBtn, neutralListener)
                .show();
    }
}
