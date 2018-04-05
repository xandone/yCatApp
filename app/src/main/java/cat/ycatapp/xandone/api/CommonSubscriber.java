package cat.ycatapp.xandone.api;

import android.text.TextUtils;
import android.util.Log;


import cat.ycatapp.xandone.base.BaseView;
import cat.ycatapp.xandone.exception.ApiException;
import cat.ycatapp.xandone.uitils.LogUtils;
import cat.ycatapp.xandone.widget.LoadingLayout;
import io.reactivex.subscribers.ResourceSubscriber;
import retrofit2.HttpException;

/**
 * author: xandone
 * created on: 2017/11/29 14:01
 */

public class CommonSubscriber<T> extends ResourceSubscriber<T> {
    private BaseView mView;
    private String mErrorMsg;
    private boolean isShowErrorState;

    public CommonSubscriber(BaseView baseView) {
        this.mView = baseView;
    }

    protected CommonSubscriber(BaseView view, String errorMsg) {
        this.mView = view;
        this.mErrorMsg = errorMsg;
    }

    protected CommonSubscriber(BaseView view, boolean isShowErrorState) {
        this.mView = view;
        this.isShowErrorState = isShowErrorState;
    }

    protected CommonSubscriber(BaseView view, String errorMsg, boolean isShowErrorState) {
        this.mView = view;
        this.mErrorMsg = errorMsg;
        this.isShowErrorState = isShowErrorState;
    }

    @Override
    public void onNext(T t) {
        Log.d("yandone","onnext");
    }

    @Override
    public void onError(Throwable t) {
        Log.d("yandone","onError");
        if (mView == null) {
            return;
        }
        if (!TextUtils.isEmpty(mErrorMsg)) {
            mView.showMsg(mErrorMsg, LoadingLayout.serverError);
        } else if (t instanceof ApiException) {
            mView.showMsg(t.toString(), LoadingLayout.serverError);
        } else if (t instanceof HttpException) {
            mView.showMsg("数据加载失败", LoadingLayout.netError);
        } else {
            mView.showMsg("未知错误", LoadingLayout.serverError);
            LogUtils.d(t.toString());
        }
        if (isShowErrorState) {
            mView.stateError();
        }
    }

    @Override
    public void onComplete() {

    }
}
