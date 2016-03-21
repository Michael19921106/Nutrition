package com.zxxk.demo.nutrition;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.zxxk.demo.nutrition.ui.fragment.LoginFragment;

/**
 * User: Michael(michael.hms@outlook.com)
 * Date: 2016-03-18
 * Time: 16:06
 * Description:
 */
public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.login_fragment_container, new LoginFragment())
                    .commit();
        }
    }
}