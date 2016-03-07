package com.zxxk.demo.nutrition.ui.fragment;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.zxxk.demo.nutrition.NutritionApp;
import com.zxxk.demo.nutrition.R;
import com.zxxk.demo.nutrition.entity.Tab;
import com.zxxk.demo.nutrition.entity.Tabs;
import com.zxxk.demo.nutrition.interfaces.NavigationDrawerCallbacks;
import com.zxxk.demo.nutrition.respository.NetRespository;
import com.zxxk.demo.nutrition.ui.adapter.NavigationFragmentAdapter;
import com.zxxk.demo.nutrition.utils.SharedPreferenceUtils;
import com.zxxk.demo.nutrition.utils.UIUtils;

import java.util.List;

/**
 * User: Michael(michael.hms@outlook.com)
 * Date: 2016-03-03
 * Time: 14:29
 * Description:
 */
public class NavigationFragment extends Fragment implements NavigationDrawerCallbacks {
    /**
     * 用来标记选中的位置
     **/
    private static final String STATE_SELECTED_POSITION = "selected_navigation_drawer_position";
    private NavigationDrawerCallbacks mCallbacks;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private View mFragmentContainerView;
    private int currentSelectedPosition = -1;
    private boolean mFromSaveInstanceState;
    private boolean mUserLearnedDrawer;
    private List<Tab> mTabs;
    private RecyclerView recyclerView;
    private NavigationFragmentAdapter mAdapter;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mCallbacks = (NavigationDrawerCallbacks) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException("activity must implements NavigationDrawerCallbacks");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUserLearnedDrawer = SharedPreferenceUtils.isUserLearnedDrawer(getActivity());
        if (savedInstanceState != null) {
            currentSelectedPosition = savedInstanceState.getInt(STATE_SELECTED_POSITION);
            mFromSaveInstanceState = true;
        }
        mAdapter = new NavigationFragmentAdapter(Build.VERSION.SDK_INT == Build.VERSION_CODES
                .KITKAT && UIUtils.hasNavigationBar(getActivity()));
        mAdapter.setNavigationDrawerCallBacks(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_navigation, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = (RecyclerView) view.findViewById(R.id.navigation_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        /**
         * Report that this fragment would like to participate in populating
         * the options menu by receiving a call to {@link #onCreateOptionsMenu}
         * and related methods.
         */
        setHasOptionsMenu(true);
        refresh();
    }

    /**
     * 获取网络数据
     */
    public void refresh() {
        NutritionApp.getNetRespository().getTabs(new NetRespository.CallBack<Tabs>() {
            @Override
            public void success(Tabs tabs, String url) {
                mTabs = tabs.getTabs();
                mAdapter.setThemes(mTabs);
                recyclerView.setAdapter(mAdapter);
            }

            @Override
            public void failure(Exception e, String url) {
                e.printStackTrace();
                Toast.makeText(getActivity(),"加载失败",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        if (position == currentSelectedPosition) {
            closeDrawer();
            return;
        }
        closeDrawer();
        currentSelectedPosition = position;
        if (mCallbacks != null) {
            mCallbacks.onNavigationDrawerItemSelected(position);
        }

    }

    public void selectItem(int position) {
        if (position == currentSelectedPosition) {
            closeDrawer();
            return;
        }
        //adapter设置选中的条目
        mAdapter.selectPosition(position);
    }

    public void selectTab(Tab tab) {
        int size = 0;
        /**Return true if the fragment is currently added to its activity.:idAdded**/
        if (mTabs != null && (size = mTabs.size()) > 0 && isAdded()) {
            for (int i = 0; i < size; i++) {
                if (mTabs.get(i).getId().equals(tab.getId())) {
                    selectItem(i + 1);
                    break;
                }
            }
        }
    }

    /**
     * 调用此方法完成和NavigationDrawer之间的交互
     */
    public void setup(int fragmentId, DrawerLayout drawerLayout, Toolbar toolbar) {
        mFragmentContainerView = getActivity().findViewById(fragmentId);
        mDrawerLayout = drawerLayout;
        mDrawerLayout.setStatusBarBackground(R.color.style_color_primary);
        /**添加阴影**/
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), mDrawerLayout, toolbar, R.string
                .navigation_drawer_open, R.string.navigation_drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if (!isAdded()){
                    return;
                }
                if (!mUserLearnedDrawer){
                    /**此处用来标记：当用户主动打开NavigationDrawer的时候，阻止自动打开**/
                    mUserLearnedDrawer = false;
                    SharedPreferenceUtils.markUserLearnedDrawer(getActivity());
                }
                getActivity().supportInvalidateOptionsMenu();

            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                if (!isAdded()){
                    return;
                }
                /**
                 * Invalidate the activity's options menu. This will cause relevant presentations
                 * of the menu to fully update via calls to onCreateOptionsMenu and
                 * onPrepareOptionsMenu the next time the menu is requested.
                 */
                getActivity().supportInvalidateOptionsMenu();
            }
        };
        if (!mUserLearnedDrawer && !mFromSaveInstanceState){
            mDrawerLayout.openDrawer(mFragmentContainerView);
        }
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(STATE_SELECTED_POSITION, currentSelectedPosition);
    }

    public int getCurrentSelectedPosition() {
        return currentSelectedPosition;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    public String getSectionId(int sectionNum) {
        return sectionNum == 0 ? null : mTabs.get(sectionNum - 1).getId();
    }

    public String getTitle(int sectionNum) {
        return sectionNum == 0 ? getString(R.string.app_name) : mTabs.get(sectionNum - 1).getName();
    }

    /**
     * 抽屉是否打开
     *
     * @return
     */
    public boolean isDrawerOpened() {
        return mDrawerLayout != null && mDrawerLayout.isDrawerOpen(mFragmentContainerView);
    }

    /**
     * 打开抽屉
     */
    public void openDrawer() {
        if (mDrawerLayout != null) {
            mDrawerLayout.openDrawer(mFragmentContainerView);
        }
    }

    public void closeDrawer() {
        if (mDrawerLayout != null) {
            mDrawerLayout.closeDrawer(mFragmentContainerView);
        }
    }

    public static int getDefaultNavDrawerItem() {
        return 0;
    }
}