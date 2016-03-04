package com.zxxk.demo.nutrition.ui;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.zxxk.demo.nutrition.MainActivity;
import com.zxxk.demo.nutrition.R;
import com.zxxk.demo.nutrition.utils.UIUtils;



/**
 * Tag:
 * Author: Michael(michael.hms@outlook.com)
 * Date: 2016/3/3
 * Time: 14:29
 * Description:
 */
public abstract class BaseAppCompatActivity extends AppCompatActivity {
    public Toolbar mActionBarToolbar;

    protected abstract int getContentViewLayoutId();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewLayoutId());
        mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar_main_tb);
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) {
            int statusBarHeight = UIUtils.getStatusBarHeight(this);
            mActionBarToolbar.setPadding(0, statusBarHeight, 0, 0);
        }
        setupActionBar();
    }

    private void setupActionBar() {
        setSupportActionBar(mActionBarToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null && !(this instanceof MainActivity)) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
//        else if (id == R.id.action_settings) {
//            startActivity(new Intent(this, SettingsActivity.class));
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

}
