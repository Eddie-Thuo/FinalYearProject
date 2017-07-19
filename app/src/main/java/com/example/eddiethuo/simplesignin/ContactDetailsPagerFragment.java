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
 * Created by eddiethuo on 19/07/2017.
 */

public class ContactDetailsPagerFragment extends Fragment {

    private TabLayout mContactsTabLayout;
    private ViewPager mViewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.contacts_pager_fragment, container, false);
        mContactsTabLayout = (TabLayout) v.findViewById(R.id.contacts_tab_layout);
        mViewPager = (ViewPager) v.findViewById(R.id.contacts_view_pager);
        setUpViewPagerAdapter(mViewPager);
        mContactsTabLayout.setupWithViewPager(mViewPager);
        return v;
    }

    private void setUpViewPagerAdapter(ViewPager pager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());
        adapter.addFragment(new CourseContactsFragment(), "Staff");
        adapter.addFragment(new StudentContactsFragment(), "Students");
        pager.setAdapter(adapter);
    }


    public static class ViewPagerAdapter extends FragmentStatePagerAdapter {
        private List<Fragment> allFragments = new ArrayList<>();
        private List<String> fragmentTitles = new ArrayList<>();


        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            allFragments.add(fragment);
            fragmentTitles.add(title);
        }

        @Override
        public int getCount() {
            return allFragments.size();
        }

        @Override
        public Fragment getItem(int position) {
            return allFragments.get(position);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return fragmentTitles.get(position);
        }
    }

}
