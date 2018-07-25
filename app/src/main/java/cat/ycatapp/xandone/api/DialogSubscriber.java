package cat.ycatapp.xandone.api;

import cat.ycatapp.xandone.App;
import cat.ycatapp.xandone.base.BaseView;
import cat.ycatapp.xandone.uitils.ProgressDialogUtil;

/**
 * author: xandone
 * created on: 2018/7/25 14:39
 */
public class DialogSubscriber<T> extends CommonSubscriber<T> {
    public DialogSubscriber(BaseView baseView) {
        super(baseView);
    }

    protected DialogSubscriber(BaseView view, String errorMsg) {
        super(view, errorMsg);
    }

    protected DialogSubscriber(BaseView view, boolean isShowErrorState) {
        super(view, isShowErrorState);
    }

    protected DialogSubscriber(BaseView view, String errorMsg, boolean isShowErrorState) {
        super(view, errorMsg, isShowErrorState);
    }


    @Override
    public void onNext(T t) {
        dismissLoadingDialog();
        super.onNext(t);
    }

    @Override
    public void onError(Throwable t) {
        dismissLoadingDialog();
        super.onError(t);
    }


    protected void showLoadingDialog(boolean cancelable) {
        showLoadingDialog("正在请求...", cancelable);
    }

    protected void showLoadingDialog(String msg, boolean cancelable) {
        ProgressDialogUtil.showProgress(App.sContext, msg, cancelable);
    }

    protected void dismissLoadingDialog() {
        ProgressDialogUtil.dismiss();
    }
}
