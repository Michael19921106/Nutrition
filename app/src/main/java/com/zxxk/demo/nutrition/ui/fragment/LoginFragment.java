package com.zxxk.demo.nutrition.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hkm.ui.processbutton.iml.ActionProcessButton;
import com.zxxk.demo.nutrition.MainActivity;
import com.zxxk.demo.nutrition.R;

/**
 * User: Michael(michael.hms@outlook.com)
 * Date: 2016-03-18
 * Time: 16:32
 * Description:
 */
public class LoginFragment extends Fragment {

    private ActionProcessButton mLoginBtn;
    private Context mContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mLoginBtn = (ActionProcessButton) view.findViewById(R.id.login_btn);
        mLoginBtn.setMode(ActionProcessButton.Mode.ENDLESS);
        mLoginBtn.setOnProcessState(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, MainActivity.class);
                mContext.startActivity(intent);
            }
        });
    }
}