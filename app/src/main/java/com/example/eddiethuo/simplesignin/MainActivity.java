package com.example.eddiethuo.simplesignin;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;

import Dialogs.SignOutDialog;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    private NavigationView mNavigationView;
    public static DrawerLayout mDrawerLayout;
    public static Toolbar mToolbar;
    public static ActionBarDrawerToggle actionBarDrawerToggle;
    public static String SIGN_OUT_DIALOG = "SignOutDialog";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_view);
        mToolbar = (Toolbar) findViewById(R.id.toool_bar);
        setSupportActionBar(mToolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mNavigationView = (NavigationView) findViewById(R.id.main_navigation_view);
        mNavigationView.setNavigationItemSelectedListener(this);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.openDrawer, R.string.closeDrawer);
        mDrawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        setUpWindowAnimation();
        FragmentManager fm = getSupportFragmentManager();
        Fragment initialFragment = fm.findFragmentById(R.id.fragment_container);
        if (initialFragment == null) {
            initialFragment = new ProfileSummaryFragment2();
            fm.beginTransaction().add(R.id.fragment_container, initialFragment).commit();

        }

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item){
        displayMenuFragment(item);
        return true;
    }

    private void displayMenuFragment(MenuItem itemId) {
        Fragment fragment = null;
        switch (itemId.getItemId()) {
            case R.id.person_details_option:
                setToolbarTitle(itemId);
                fragment = new ProfileSummaryFragment2();
                mDrawerLayout.closeDrawers();
                break;
            case R.id.course_details_option:
                fragment = new CourseDetailsPagerFragment();
                setToolbarTitle(itemId);
                mDrawerLayout.closeDrawers();
                break;
            case R.id.course_contacts_option:
                fragment = new CourseContactsFragment();
                setToolbarTitle(itemId);
                mDrawerLayout.closeDrawers();
                break;
            case R.id.exam_option:
                fragment = new TimeTableFragment();
                setToolbarTitle(itemId);
                mDrawerLayout.closeDrawers();
                break;
            case R.id.su_union_option:
                fragment = new StudentsUnionFragment();
                setToolbarTitle(itemId);
                mDrawerLayout.closeDrawers();
                break;
            case R.id.twitter_option:
                fragment = new TwitterFragment();
                setToolbarTitle(itemId);
                mDrawerLayout.closeDrawers();
                break;
            case R.id.instagram_option:
                fragment = new InstagramFragment();
                setToolbarTitle(itemId);
                mDrawerLayout.closeDrawers();
                break;
            case R.id.faq_option:
                fragment = new FAQListFragment();
                setToolbarTitle(itemId);
                mDrawerLayout.closeDrawers();
                break;
            case R.id.contact_option:
                fragment = new ContactInfoFragment();
                setToolbarTitle(itemId);
                mDrawerLayout.closeDrawers();
                break;
            case R.id.log_out_option:
                setToolbarTitle(itemId);
                mDrawerLayout.closeDrawers();
                break;
        }

        if(fragment != null){
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            ft.replace(R.id.fragment_container, fragment).commit();
        }

    }

    private void setToolbarTitle(MenuItem item){
        mToolbar.setTitle(item.getTitle());
    }

    private void setUpWindowAnimation(){
        Slide slide = new Slide(Gravity.TOP);
        slide.setDuration(500);
        getWindow().setExitTransition(slide);
        getWindow().setReenterTransition(slide);
        getWindow().setAllowReturnTransitionOverlap(false);
    }


    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


}
