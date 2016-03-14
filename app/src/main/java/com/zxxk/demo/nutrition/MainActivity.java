package com.zxxk.demo.nutrition;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;

import com.zxxk.demo.nutrition.interfaces.NavigationDrawerCallbacks;
import com.zxxk.demo.nutrition.ui.BaseAppCompatActivity;
import com.zxxk.demo.nutrition.ui.fragment.BaseFragment;
import com.zxxk.demo.nutrition.ui.fragment.NavigationFragment;
import com.zxxk.demo.nutrition.ui.fragment.NewsFragment;

/** 
 * Tag:
 * Author: Michael(michael.hms@outlook.com) 
 * Date: 2016/3/7
 * Time: 15:53
 * Description: 
 */
public class MainActivity extends BaseAppCompatActivity implements NavigationDrawerCallbacks{
    public static final String TAG = MainActivity.class.getSimpleName();
    private NavigationFragment navigationFragment;
    private CharSequence mTitle = "";

    @Override
    protected int getContentViewLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NutritionApp.initImageLoader(MainActivity.this.getApplicationContext());
        setupDrawer();
        if (savedInstanceState ==null){
            navigationFragment.selectItem(NavigationFragment.getDefaultNavDrawerItem());
        }
    }
    public void setupDrawer(){
        navigationFragment = (NavigationFragment)getSupportFragmentManager().findFragmentById(R.id
                .navigation_drawer);
        mTitle = getTitle();
        navigationFragment.setup(R.id.navigation_drawer,(DrawerLayout)findViewById(R.id
                .nav_drawer_layout),mActionBarToolbar);
    }

    private int lastPosition = 0;

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        Fragment lastFragment = fm.findFragmentByTag(getTag(lastPosition));
        if (lastFragment != null) {
            ft.detach(lastFragment);
        }

        Fragment fragment = fm.findFragmentByTag(getTag(position));
        if (fragment == null) {
            fragment = getFragmentItem(position);
            ft.add(R.id.main_fragment_container, fragment, getTag(position));
        } else {
            ft.attach(fragment);
        }

        ft.commit();
        lastPosition = position;

        mTitle = navigationFragment.getTitle(position);
        restoreActionBar();
    }


    private String getTag(int position) {
       return NewsFragment.TAG + position;
    }

    private Fragment getFragmentItem(int position) {
        return BaseFragment.newInstance(position, navigationFragment.getSectionId(position));
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }



    @Override
    public void onBackPressed() {
        if (navigationFragment.isDrawerOpened()) {
            navigationFragment.closeDrawer();
        } else {
            if (navigationFragment.getCurrentSelectedPosition() != NavigationFragment.getDefaultNavDrawerItem()) {
                navigationFragment.selectItem(NavigationFragment.getDefaultNavDrawerItem());
            } else {
                super.onBackPressed();
            }
        }
    }
}
