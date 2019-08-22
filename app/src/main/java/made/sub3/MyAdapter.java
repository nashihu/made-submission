package made.sub3;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import made.sub3.favactivity.favmoviefragment.FavMovieFragment;
import made.sub3.favactivity.favtelefragment.FavTeleFragment;

public class MyAdapter extends FragmentPagerAdapter {

    private Context myContext;
    int totalTabs;

    public MyAdapter(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        myContext = context;
        this.totalTabs = totalTabs;
    }

    // this is for fragment tabs
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                FavMovieFragment movieFragment = new FavMovieFragment();
                return movieFragment;
            case 1:
                FavTeleFragment favTeleFragment = new FavTeleFragment();
                return favTeleFragment;
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