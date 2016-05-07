package com.notes_dbflow.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;

import com.notes_dbflow.R;
import com.notes_dbflow.ui.fragment.BaseFragment;
import com.notes_dbflow.ui.fragment.MainFragment;
import com.notes_dbflow.util.AppFragmentTool;

public class MainActivity extends AppCompatActivity{
    private static final String TAG = "MainActivity";
    private AppFragmentTool mAppFragmentTool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAppFragmentTool = new AppFragmentTool(this);

        mAppFragmentTool.initMainFragment(MainFragment.class.getSimpleName());
    }

    public AppFragmentTool getAppFragmentTool() {
        return mAppFragmentTool;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Log.i(TAG, "mAppFragmentTool.getCurrentFragmentTag()=" + mAppFragmentTool.getCurrentFragmentTag());
            BaseFragment currentFragment = mAppFragmentTool.getCurrentFragment();
            Log.i(TAG, "currentFragment=" + currentFragment);
            if (null != currentFragment) {
                mAppFragmentTool.getCurrentFragment().onBackPressed();
            }
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }
}
