package cat.ycatapp.xandone.ui.videodetails;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import cat.ycatapp.xandone.R;
import cat.ycatapp.xandone.base.RxBaseFragment;

/**
 * author: xandone
 * created on: 2018/7/19 11:46
 */
public class ContentFragment extends RxBaseFragment {
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;

    private PagerAdapter mPagerAdapter;
    private List<String> titleList = new ArrayList<>(Arrays.asList("影视信息", "影视评论"));
    private List<Fragment> fragList = new ArrayList<>();

    @Override
    public int setLayout() {
        return R.layout.frag_content_layout;
    }

    @Override
    public void initData() {
        fragList.add(new VideoOtherFragment());
        fragList.add(new VideoCommentFragment());

        mPagerAdapter = new PagerAdapter(getActivity().getSupportFragmentManager(), fragList, titleList);
        viewPager.setAdapter(mPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void initInject() {

    }

    @Override
    protected void lazyLoadData() {

    }
}
