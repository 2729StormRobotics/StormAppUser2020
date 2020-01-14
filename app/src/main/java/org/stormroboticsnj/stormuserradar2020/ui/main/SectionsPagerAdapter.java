package org.stormroboticsnj.stormuserradar2020.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import org.stormroboticsnj.stormuserradar2020.Auto;
import org.stormroboticsnj.stormuserradar2020.Endgame;
import org.stormroboticsnj.stormuserradar2020.PathAuto;
import org.stormroboticsnj.stormuserradar2020.R;
import org.stormroboticsnj.stormuserradar2020.Scoring;
import org.stormroboticsnj.stormuserradar2020.StartActivity;
import org.stormroboticsnj.stormuserradar2020.Teleop;


/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[]TAB_TITLES = new int[]{R.string.auto, R.string.teleop, R.string.endgame, R.string.map};
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
                return Endgame.newInstance("","");

            default:
                return PathAuto.newInstance("","");
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 4;
    }
}