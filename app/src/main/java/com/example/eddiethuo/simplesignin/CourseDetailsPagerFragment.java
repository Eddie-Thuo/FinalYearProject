package com.example.eddiethuo.simplesignin;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eddiethuo on 25/11/2016.
 */

public class CourseDetailsPagerFragment extends Fragment {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.course_details_pager_fragment, container, false);
        mViewPager = (ViewPager)v.findViewById(R.id.course_details_pager);
        setViewPager(mViewPager);
        mTabLayout = (TabLayout)v.findViewById(R.id.tab_layout);
        mTabLayout.setupWithViewPager(mViewPager);
        return v;
    }


    public void setViewPager(ViewPager pager){
        ViewPagerAdapter adapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());
        adapter.addFragment(new CourseDetailsTwoFragment(),"2015/16");
        adapter.addFragment(new CourseDetailsOneFragment(), "2014/2015");
        pager.setAdapter(adapter);

    }


    class ViewPagerAdapter extends FragmentStatePagerAdapter{
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager){
            super(manager);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public Fragment getItem(int position){
                return mFragmentList.get(position);
        }

        @Override
        public int getCount(){
            return mFragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }


    }






}
