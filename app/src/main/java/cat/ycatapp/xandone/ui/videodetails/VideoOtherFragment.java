package cat.ycatapp.xandone.ui.videodetails;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import cat.ycatapp.xandone.R;
import cat.ycatapp.xandone.base.RxBaseFragment;
import cat.ycatapp.xandone.model.video.VideoInfo;

/**
 * author: xandone
 * created on: 2018/7/19 13:56
 */
public class VideoOtherFragment extends RxBaseFragment<VideoOtherPresenter> implements VideoOtherContact.MyView {
    @BindView(R.id.video_list_rv)
    RecyclerView video_list_rv;

    private VideoRecyclerAdapter mVideoRecyclerAdapter;
    private List<VideoInfo.ItemListBean> datas;

    @Override
    public int setLayout() {
        return R.layout.frag_video_other_layout;
    }

    @Override
    public void initData() {
        super.initData();

        datas = new ArrayList<>();
        mVideoRecyclerAdapter = new VideoRecyclerAdapter(mActivity, datas);
        video_list_rv.setLayoutManager(new LinearLayoutManager(mActivity));
        video_list_rv.setAdapter(mVideoRecyclerAdapter);

        loadVideoList();
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected void lazyLoadData() {

    }

    public void loadVideoList() {
        Map<String, String> map = new HashMap<>();
        map.put("num", "10");
        map.put("udid", "26868b32e808498db32fd51fb422d00175e179df");
        map.put("vc", "83");

        mPresenter.getVideoOtherList(map);
    }

    @Override
    public void showContent(VideoInfo videoInfo) {
        if (videoInfo == null
                || videoInfo.getItemList() == null
                || videoInfo.getItemList().isEmpty()) {
            return;
        }
        datas.clear();
        datas.addAll(videoInfo.getItemList());
        datas.get(0).setItemType(1);
        mVideoRecyclerAdapter.notifyDataSetChanged();
    }
}
