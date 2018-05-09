package cat.ycatapp.xandone.ui.info;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cat.ycatapp.xandone.R;
import cat.ycatapp.xandone.base.RxBaseFragment;
import cat.ycatapp.xandone.cache.UserInfoCache;
import cat.ycatapp.xandone.config.Constants;
import cat.ycatapp.xandone.model.bean.UserBean;
import cat.ycatapp.xandone.ui.login.LoginActivity;
import cat.ycatapp.xandone.ui.personal.PersonalActivity;
import cat.ycatapp.xandone.uitils.GsonUtil;
import cat.ycatapp.xandone.uitils.SPUtils;
import cat.ycatapp.xandone.widget.BottomDialog;
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

public class InfoFragment extends RxBaseFragment<InfoPresenter> implements View.OnClickListener {
    @BindView(R.id.frag_info_login_ll)
    LinearLayout frag_info_login_ll;
    @BindView(R.id.frag_info_icon_ll)
    LinearLayout frag_info_icon_ll;
    @BindView(R.id.frag_info_nick)
    TextView frag_info_nick;
    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.frag_info_icon_iv)
    ImageView frag_info_icon_iv;

    @Override
    public int setLayout() {
        return R.layout.frag_info_layout;
    }

    @Override
    public void initData() {
        super.initData();
        setToolBar(toolBar, getString(R.string.x_personal_title));
        if (UserInfoCache.isLogin()) {
            showUserInfo();
        }
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @OnClick({R.id.frag_info_login, R.id.frag_info_out, R.id.frag_info_icon_iv})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.frag_info_login:
                startActivity(new Intent(mActivity, LoginActivity.class));
                break;
            case R.id.frag_info_out:

                showDialog("是否退出登录", "确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SPUtils.getInstance(Constants.USER_INFO_NAME).remove(Constants.USER_INFO_KEY);
                        UserInfoCache.setLogin(false);
                        UserInfoCache.setUserBean(null);
                        logout();

                    }
                }, "取消", null);
                break;
            case R.id.frag_info_icon_iv:
                changeIconDialog();
                break;
        }
    }

    /**
     * 显示个人信息
     */
    public void showUserInfo() {
        frag_info_login_ll.setVisibility(View.GONE);
        frag_info_icon_ll.setVisibility(View.VISIBLE);
        frag_info_nick.setText(UserInfoCache.getUserBean().getNickName());
    }

    /**
     * 退出登录清除用户信息
     */
    public void logout() {
        frag_info_login_ll.setVisibility(View.VISIBLE);
        frag_info_icon_ll.setVisibility(View.GONE);
        frag_info_nick.setText("");
    }

    public void changeIconDialog() {
        final BottomDialog bottomDialog = BottomDialog.create(getChildFragmentManager());
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

                        bottom_dialog_photo.setOnClickListener(InfoFragment.this);
                    }
                }).show();
    }


    public void compressImg(String photos) {
        Luban.with(mActivity)
                .load(photos)                                   // 传人要压缩的图片列表
                .ignoreBy(100)                                  // 忽略不压缩图片的大小
                .setCompressListener(new OnCompressListener() { //设置回调
                    @Override
                    public void onStart() {
                    }

                    @Override
                    public void onSuccess(File file) {
                        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                        MultipartBody.Part body =
                                MultipartBody.Part.createFormData("file", file.getName(), requestFile);
                        Map<String, String> map = new HashMap();
                        map.put("userId", UserInfoCache.getUserBean().getUserId());
                        Log.d("yandone", UserInfoCache.getUserBean().getUserId());

                        mPresenter.changeUserIcon(map, body);
                    }

                    @Override
                    public void onError(Throwable e) {
                    }
                }).launch();    //启动压缩
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bottom_dialog_photo:
                PhotoPicker.builder()
                        .setPhotoCount(1)
                        .setShowCamera(true)
                        .setShowGif(true)
                        .setPreviewEnabled(false)
                        .start(mActivity, this);
                break;
        }
    }
}
