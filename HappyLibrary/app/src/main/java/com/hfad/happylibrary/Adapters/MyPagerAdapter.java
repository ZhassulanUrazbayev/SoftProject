package com.hfad.happylibrary;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.hfad.happylibrary.Fragments.BooksFragment;
import com.hfad.happylibrary.Fragments.MyBooksFragment;
import com.hfad.happylibrary.Fragments.ProfileFragment;

class MyPagerAdapter extends FragmentStatePagerAdapter {
    private int mNumOfTabs;
    private BooksFragment f1;
    private MyBooksFragment f2;
    private ProfileFragment f3;

    private String tabTitles[] = new String[]{"Books", "My Books", "Profile"};

    public MyPagerAdapter(FragmentManager supportFragmentManager, int tabCount) {
        super(supportFragmentManager);

        f1 = new BooksFragment();
        f2 = new MyBooksFragment();
        f3 = new ProfileFragment();

        mNumOfTabs = tabCount;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return f1;
            case 1:
                return f2;
            case 2:
                return f3;
            default:
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
