package org.stormroboticsnj.mainactivity_fragments;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import org.stormroboticsnj.stormuserradar2020.R;


/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[]TAB_TITLES = new int[]{R.string.auto, R.string.teleop, R.string.map, R.string.endgame};
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
//        // getItem is called to instantiate the fragment for the given page.
//        if (position == 0) { // Fragment # 0 - This will show Scoring
//            return Scoring.newInstance("", "Page # 1");
//        }
//        return Endgame.newInstance("", "");
        switch (position) {
            case 0:
                return Auto.newInstance("","");

            case 1:
                return Teleop.newInstance("","" );

            case 2:
                return Map.newInstance("", "");

            default:
                return Endgame.newInstance("","");
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 4 total pages.
        return TAB_TITLES.length;
    }
}