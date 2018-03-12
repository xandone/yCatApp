package cat.ycatapp.xandone.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * author: xandone
 * created on: 2018/3/5 15:30
 */

public abstract class BaseFragment extends Fragment implements BaseView{

    protected Activity mActivity;

    //Fragment的View加载完毕的标记
    protected boolean isViewCreated;

    //Fragment对用户可见的标记
    protected boolean isUIVisible;

    @Override
    public void onAttach(Context context) {
        this.mActivity = (Activity) context;
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
    public void showMsg(String msg) {

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
}