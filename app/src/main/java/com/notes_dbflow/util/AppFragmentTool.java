package com.notes_dbflow.util;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.notes_dbflow.R;
import com.notes_dbflow.ui.MainActivity;
import com.notes_dbflow.ui.fragment.BaseFragment;
import com.notes_dbflow.ui.fragment.AddDetailFragment;
import com.notes_dbflow.ui.fragment.MainFragment;
import com.notes_dbflow.ui.fragment.WebViewFragment;

public class AppFragmentTool {
    private static final String TAG = "AppFragmentTool";

    private FragmentManager mFragmentManager;
    private FragmentTransaction mFragmentTransaction;
    private String currentFragmentTag = "";

    public AppFragmentTool(MainActivity mainActivity) {
        mFragmentManager = mainActivity.getSupportFragmentManager();
    }

    /**
     * 最先添加一个fragment
     */
    public void initMainFragment(String fragmentTag){
        switchFragment(fragmentTag,null);
    }

    public void switchFragment(String fragmentTag, Bundle bundle) {
        if (fragmentTag.equals(currentFragmentTag)) {
            return;
        }
        Fragment fragment = mFragmentManager.findFragmentByTag(fragmentTag);
        if (null == fragment) {
            if (fragmentTag.equals(MainFragment.class.getSimpleName())) {
                fragment = new MainFragment();
            }else if(fragmentTag.equals(AddDetailFragment.class.getSimpleName())){
                fragment = new AddDetailFragment();
            }else if(fragmentTag.equals(WebViewFragment.class.getSimpleName())){
                fragment = new WebViewFragment();
            }
            fragment.setArguments(bundle);
        }
        ensureTransaction().replace(R.id.main_container, fragment, fragmentTag);
        mFragmentTransaction.addToBackStack(null);

        commitTransactions();
    }

    /**
     * get FragmentTransaction
     * @return
     */
    protected FragmentTransaction ensureTransaction() {
        if (mFragmentTransaction == null) {
            mFragmentTransaction = mFragmentManager.beginTransaction();
        }
        return mFragmentTransaction;
    }

    /**
     * commit FragmentTransaction
     */
    protected void commitTransactions() {
        if (mFragmentTransaction != null && !mFragmentTransaction.isEmpty()) {
            mFragmentTransaction.commitAllowingStateLoss();
            mFragmentTransaction = null;
        }
    }

    public void setCurrentFragmentTag(String tag) {
        currentFragmentTag = tag;
    }

    public String getCurrentFragmentTag() {
        return currentFragmentTag;
    }

    public BaseFragment getCurrentFragment(){
        return (BaseFragment) mFragmentManager.findFragmentByTag(currentFragmentTag);
    }


    /**
     * 弹出堆栈中的一个并且显示，也就是代码模拟按下返回键的操作。
     */
    public void popBackStack() {
        mFragmentManager.popBackStack();
    }
}