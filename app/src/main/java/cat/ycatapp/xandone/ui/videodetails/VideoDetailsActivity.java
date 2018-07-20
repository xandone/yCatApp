package cat.ycatapp.xandone.ui.videodetails;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.text.TextUtils;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;


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

        Intent intent = getIntent();
        mVideoInfo = (VideoInfo.ItemListBean) intent.getSerializableExtra(VideoListFragment.KEY_VIDEOINFO);
        if (mVideoInfo == null) {
            return;
        }
        playVideo("google机器人", mVideoInfo.getData().getCover().getDetail(), mVideoInfo.getData().getPlayUrl());

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
    }

    @Override
    public void onBackPressed() {
        if (JZVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

}
