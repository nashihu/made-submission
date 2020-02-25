package made.sub3.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import made.sub3.modules.favactivity.favmoviefragment.FavMovieFragment;
import made.sub3.modules.favactivity.favtelefragment.FavTeleFragment;

public class MyAdapter extends FragmentPagerAdapter {

    private int totalTabs;

    public MyAdapter(FragmentManager fm, int totalTabs) {
        super(fm);
        this.totalTabs = totalTabs;
    }

    // this is for fragment tabs
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new FavMovieFragment();
            case 1:
                return new FavTeleFragment();
            default:
                return null;
        }
    }

    // this counts total number of tabs
    @Override
    public int getCount() {
        return totalTabs;
    }
}