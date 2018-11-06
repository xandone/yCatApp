package cat.ycatapp.xandone.ui.videodetails;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.text.TextUtils;

import com.bumptech.glide.Glide;


import butterknife.BindView;
import cat.ycatapp.xandone.R;
import cat.ycatapp.xandone.base.RxBaseActivity;
import cat.ycatapp.xandone.model.video.VideoInfo;
import cat.ycatapp.xandone.ui.video.VideoListFragment;
import cat.ycatapp.xandone.widget.AJzVideoView;
import cat.ycatapp.xandone.widget.JZMediaIjkplayer;
import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;

/**
 * author: xandone
 * created on: 2018/7/18 09:25
 */
public class VideoDetailsActivity extends RxBaseActivity {
    @BindView(R.id.videoView)
    AJzVideoView mVideoView;

    private VideoInfo.ItemListBean mVideoInfo;
    private VideoBroadCast mVideoBroadCast;

    public static final String VIDEO_BEAN = "video_bean";
    public static final String VIDEO_CHANGE_ACTION = "video_change_action";

    @Override
    public int setLayout() {
        return R.layout.act_video_details_layout;
    }


    @Override
    public void initInject() {

    }

    @Override
    public void initData() {
        JZVideoPlayer.setMediaInterface(new JZMediaIjkplayer());
        JZVideoPlayer.FULLSCREEN_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
        JZVideoPlayer.NORMAL_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;

        registerVideoBroadCast();

        Intent intent = getIntent();
        mVideoInfo = (VideoInfo.ItemListBean) intent.getSerializableExtra(VideoListFragment.KEY_VIDEOINFO);
        if (mVideoInfo == null) {
            return;
        }
        playVideo();

    }

    private void playVideo() {
        try {
            playVideo(mVideoInfo.getData().getTitle(), mVideoInfo.getData().getCover().getDetail(), mVideoInfo.getData().getPlayUrl());
        } catch (Exception e) {

        }
    }

    private void playVideo(String title, String thumImgUrl, String videoUrl) {
        mVideoView.setUp(videoUrl, JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL, title);
        //自动播放
        mVideoView.startButton.performClick();
        if (!TextUtils.isEmpty(thumImgUrl)) {
            Glide.with(this)
                    .load(thumImgUrl)
                    .into(mVideoView.thumbImageView);
        }
    }

    private void registerVideoBroadCast() {
        mVideoBroadCast = new VideoBroadCast();
        IntentFilter filter = new IntentFilter();
        filter.addAction(VIDEO_CHANGE_ACTION);
        registerReceiver(mVideoBroadCast, filter);
    }

    private void unRegisterVideoBroadCast() {
        if (mVideoBroadCast != null) {
            unregisterReceiver(mVideoBroadCast);
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        JZVideoPlayer.goOnPlayOnPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        JZVideoPlayer.goOnPlayOnResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        JZVideoPlayer.releaseAllVideos();
        unRegisterVideoBroadCast();
    }

    @Override
    public void onBackPressed() {
        if (JZVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }


    class VideoBroadCast extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                return;
            }
            if (VIDEO_CHANGE_ACTION.equals(intent.getAction())) {
                mVideoInfo = (VideoInfo.ItemListBean) intent.getSerializableExtra(VIDEO_BEAN);
            }
            if (mVideoInfo == null) {
                return;
            }
            playVideo();
        }
    }

}
