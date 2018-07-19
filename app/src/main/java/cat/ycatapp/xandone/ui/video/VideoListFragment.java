package cat.ycatapp.xandone.ui.video;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;

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
 * created on: 2018/7/19 16:55
 */
public class VideoListFragment extends RxBaseFragment<VideoListPresenter> implements VideoContact.MyView {
    @BindView(R.id.video_list_recycler)
    RecyclerView video_list_recycler;

    private VideoListAdapter mVideoListAdapter;
    private List<VideoInfo.ItemListBean> datas;

    public static final String KEY_VIDEOINFO = "key_videoinfo";

    @Override
    public int setLayout() {
        return R.layout.frag_video_layout;
    }

    @Override
    public void initData() {
        super.initData();
        init();
        loadVideoList();
    }

    private void init() {
        datas = new ArrayList<>();

        mVideoListAdapter = new VideoListAdapter(mActivity, R.layout.item_video_list_layout, datas);
        video_list_recycler.setLayoutManager(new LinearLayoutManager(mActivity));
        video_list_recycler.setAdapter(mVideoListAdapter);

        mVideoListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                Intent intent = new Intent(mActivity, VideoDetailsActivity.class);
//                intent.putExtra(KEY_VIDEOINFO, datas.get(position));
//                startActivity(intent);
            }
        });
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

        mPresenter.getVideoList(map);

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
        mVideoListAdapter.notifyDataSetChanged();
    }
}
