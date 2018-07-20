package cat.ycatapp.xandone.ui.videodetails;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * author: xandone
 * created on: 2018/7/19 13:53
 */
public class PagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragList;
    private List<String> titleList;

    public PagerAdapter(FragmentManager fm, List<Fragment> fragList, List<String> titleList) {
        super(fm);
        this.fragList = fragList;
        this.titleList = titleList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragList.get(position);
    }

    @Override
    public int getCount() {
        return fragList.size();
    }

    public void addToTab(Fragment fragment, String title) {
        if (fragList == null || titleList == null) {
            return;
        }
        fragList.add(fragment);
        titleList.add(title);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titleList.get(position);
    }
}
