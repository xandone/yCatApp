package cat.ycatapp.xandone.base;

import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import butterknife.ButterKnife;
import cat.ycatapp.xandone.AppManager;
import cat.ycatapp.xandone.uitils.ProgressDialogUtil;

/**
 * author: xandone
 * created on: 2018/3/5 15:30
 */

public abstract class BaseActivity extends AppCompatActivity implements BaseView {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        doBefore();
        setContentView(setLayout());
        ButterKnife.bind(this);
        AppManager.newInstance().addActivivty(this);
        initData();
    }

    public void doBefore() {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    public abstract int setLayout();

    public abstract void initData();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.newInstance().removectivity(this);
    }

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
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationIcon(null);
    }

    protected void showLoadingDialog(boolean cancelable) {
        showLoadingDialog("正在请求...", cancelable);
    }

    protected void showLoadingDialog(String msg, boolean cancelable) {
        ProgressDialogUtil.showProgress(this, msg, cancelable);
    }

    protected void setLoadingMessage(String str) {
        ProgressDialogUtil.setMessage(str);
    }

    protected void dismissLoadingDialog() {
        ProgressDialogUtil.dismiss();
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
        return new android.app.AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(msg)
                .setCancelable(cancelable)
                .setPositiveButton(positiveBtn, positiveListener)
                .setNegativeButton(negativeBtn, negativeListener)
                .setNeutralButton(neutralBtn, neutralListener)
                .show();
    }
}
