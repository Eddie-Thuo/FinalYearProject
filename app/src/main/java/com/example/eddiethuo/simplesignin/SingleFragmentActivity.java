package com.example.eddiethuo.simplesignin;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import Dialogs.SignOutDialog;

public abstract class SingleFragmentActivity extends AppCompatActivity {

    protected abstract Fragment createFragment();


    private NavigationView mNavigationView;
    public static DrawerLayout mDrawerLayout;
    public static Toolbar mToolbar;
    public static ActionBarDrawerToggle actionBarDrawerToggle;
    public static String SIGN_OUT_DIALOG = "SignOutDialog";

    @LayoutRes
    protected int getLayoutResId() {
        return R.layout.navigation_view;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());


        mToolbar = (Toolbar) findViewById(R.id.toool_bar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
//        mToolbar.setBackgroundColor(getResources().getColor(R.color.main_color,null));
        setSupportActionBar(mToolbar);

        mNavigationView = (NavigationView) findViewById(R.id.main_navigation_view);
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.person_details_option:
                        item.setChecked(true);
                        ProfileSummaryFragment2 fragment1 = new ProfileSummaryFragment2();
                        FragmentManager fm1 = getSupportFragmentManager();
                        FragmentTransaction transaction1 = createfragmentAnims(fm1);
                        transaction1.replace(R.id.fragment_container, fragment1).commit();
                        mToolbar.setTitle("Personal Details");
                        mDrawerLayout.closeDrawer(GravityCompat.START);
                        return true;
                    case R.id.course_details_option:
                        item.setChecked(true);
                        CourseDetailsPagerFragment fragment2 = new CourseDetailsPagerFragment();
                        FragmentManager fm2 = getSupportFragmentManager();
                        FragmentTransaction transaction2 = createfragmentAnims(fm2);
                        transaction2.replace(R.id.fragment_container, fragment2).commit();
                        mToolbar.setTitle("Course Details");
                        mDrawerLayout.closeDrawer(GravityCompat.START);
                        return true;
                    case R.id.su_union_option:
                        item.setChecked(true);
                        StudentsUnionFragment fragment3 = new StudentsUnionFragment();
                        FragmentManager fm3 = getSupportFragmentManager();
                        FragmentTransaction transaction3 = createfragmentAnims(fm3);
                        transaction3.replace(R.id.fragment_container, fragment3).commit();
                        mToolbar.setTitle("Student Union Website");
                        mDrawerLayout.closeDrawer(GravityCompat.START);
                        return true;
                    case R.id.twitter_option:
                        item.setChecked(true);
                        TwitterFragment fragment4 = new TwitterFragment();
                        FragmentManager fm4 = getSupportFragmentManager();
                        FragmentTransaction transaction4 = createfragmentAnims(fm4);
                        transaction4.replace(R.id.fragment_container, fragment4).commit();
                        mToolbar.setTitle("University Page");
                        mDrawerLayout.closeDrawer(GravityCompat.START);
                        return true;
                    case R.id.course_contacts_option:
                        item.setChecked(true);
                        CourseContactsFragment fragment5 = new CourseContactsFragment();
                        FragmentManager fm5 = getSupportFragmentManager();
                        FragmentTransaction transaction6 = createfragmentAnims(fm5);
                        transaction6.replace(R.id.fragment_container, fragment5).commit();
                        mToolbar.setTitle("Course Contacts");
                        mDrawerLayout.closeDrawer(GravityCompat.START);
                        return true;
                    case R.id.faq_option:
                        item.setChecked(true);
                        FAQListFragment fragment6 = new FAQListFragment();
                        FragmentManager fm6 = getSupportFragmentManager();
                        FragmentTransaction transaction7 = createfragmentAnims(fm6);
                        transaction7.replace(R.id.fragment_container, fragment6).commit();
                        mToolbar.setTitle("FAQ");
                        mDrawerLayout.closeDrawer(GravityCompat.START);
                        return true;
                    case R.id.exam_option:
                        item.setChecked(true);
                        TimeTableFragment fragment7 = new TimeTableFragment();
                        FragmentManager fm7 = getSupportFragmentManager();
                        FragmentTransaction transaction8 = createfragmentAnims(fm7);
                        transaction8.replace(R.id.fragment_container, fragment7).commit();
                        mToolbar.setTitle("Exam Info");
                        mDrawerLayout.closeDrawer(GravityCompat.START);
                        return true;
                    case R.id.log_out_option:
                        item.setChecked(true);
                        FragmentManager fragment8 = getSupportFragmentManager();
                        SignOutDialog dialog = new SignOutDialog();
                        dialog.show(fragment8, SIGN_OUT_DIALOG);
                        mDrawerLayout.closeDrawer(GravityCompat.START);
                        return true;
                    case R.id.instagram_option:
                        item.setChecked(true);
                        InstagramFragment fragment9 = new InstagramFragment();
                        FragmentManager fm9 = getSupportFragmentManager();
                        FragmentTransaction transaction9 = createfragmentAnims(fm9);
                        transaction9.replace(R.id.fragment_container, fragment9).commit();
                        mToolbar.setTitle("Instagram Page");
                        mDrawerLayout.closeDrawer(GravityCompat.START);
                        return true;

                    case R.id.contact_option:
                        item.setChecked(true);
                        ContactInfoFragment fragment10 = new ContactInfoFragment();
                        FragmentManager fm10 = getSupportFragmentManager();
                        FragmentTransaction transaction10 = createfragmentAnims(fm10);
                        transaction10.replace(R.id.fragment_container, fragment10).commit();
                        mToolbar.setTitle("Contact Info");
                        mDrawerLayout.closeDrawer(GravityCompat.START);
                        return true;
                    default:
                        return true;
                }
            }
        });

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar,
                R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

        };
//        mDrawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);
        if (fragment == null) {
            fragment = createFragment(); // this will be from SignInActivity...
//            fm.beginTransaction().add(R.id.fragment_container, fragment).addToBackStack(null).commit();
            fm.beginTransaction().add(R.id.fragment_container, fragment).commit();
            actionBarDrawerToggle.setDrawerIndicatorEnabled(false);
        }

    }

   /* private void removeAuthenticationAfterSignIn(){
        SignInFragment2 fragment = new SignInFragment2();
        FragmentManager fm = getSupportFragmentManager();
        if(!fm.findFragmentById(R.id.fragment_container).equals(fragment)){
            fm.beginTransaction().remove(fragment);
        }

    }*/

    private FragmentTransaction createfragmentAnims(FragmentManager fm){
        FragmentTransaction ft = fm.beginTransaction();
        ft.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        return ft;
    }


}
