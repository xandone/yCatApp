package cat.ycatapp.xandone.ui.videodetails;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cat.ycatapp.xandone.R;
import cat.ycatapp.xandone.base.RxBaseFragment;
import cat.ycatapp.xandone.model.bean.CommentBean;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * author: xandone
 * created on: 2018/7/19 15:45
 */
public class VideoCommentFragment extends RxBaseFragment {
    @BindView(R.id.video_comment_recyler)
    RecyclerView video_comment_recyler;

    private VideoCommentAdapter mVideoCommentAdapter;
    private List<CommentBean.RowsBean> datas;

    private int mPage = 1;
    private int mCount = 10;

    @Override
    public int setLayout() {
        return R.layout.frag_video_comment_layout;
    }

    @Override
    public void initData() {
        datas = new ArrayList<>();
        mVideoCommentAdapter = new VideoCommentAdapter(mActivity, R.layout.item_comment_list, datas);
        video_comment_recyler.setAdapter(mVideoCommentAdapter);
        video_comment_recyler.setLayoutManager(new LinearLayoutManager(mActivity));

        getVideoCommentList(mPage, mCount, "152138655864341", 1);
    }

    @Override
    protected void initInject() {

    }

    @Override
    protected void lazyLoadData() {

    }


    public void getVideoCommentList(int page, int rows, String jokeId, int mode) {
    }
}
