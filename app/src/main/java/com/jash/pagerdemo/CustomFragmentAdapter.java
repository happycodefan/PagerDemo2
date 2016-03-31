package com.jash.pagerdemo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.Collection;
import java.util.List;

/**
 * Created by jash on 16-3-15.
 */

/**
 * FragmentPagerAdapter 用于固定的数据源
 * FragmentStatePagerAdapter 用于变化的数据源
 */
public class CustomFragmentAdapter extends FragmentStatePagerAdapter {
    private List<String> list;

    public CustomFragmentAdapter(FragmentManager fm, List<String> list) {
        super(fm);
        this.list = list;
    }

    @Override
    public Fragment getItem(int position) {
        return BlankFragment.newInstance(list.get(position % list.size()));
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    public void addAll(Collection<? extends String> collection){
        list.addAll(collection);
        notifyDataSetChanged();
    }
}
