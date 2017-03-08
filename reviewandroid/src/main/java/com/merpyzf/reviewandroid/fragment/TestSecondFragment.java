package com.merpyzf.reviewandroid.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.merpyzf.reviewandroid.R;

/**
 * Fragment的生命周期学习
 */
public class TestSecondFragment extends Fragment {


    /**
     * 为Fragment加载布局时调用
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.i("wk","==>onCreateView()");

        return inflater.inflate(R.layout.fragment_test_second, container, false);
    }

    /**
     * Fragment 和 Activity建立关联的时候调用
     * @param context
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        Log.i("wk","==>onAttach()");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.i("wk","==>onCreate()");
    }


    /**
     * 当Activity中的onCreate方法执行完后调用
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i("wk","==>onActivityCreated()");


    }

    @Override
    public void onStart() {
        super.onStart();

        Log.i("wk","==>onStart() ");
    }


    @Override
    public void onResume() {
        super.onResume();

        Log.i("wk","==>onResume()");
    }

    @Override
    public void onPause() {
        super.onPause();

        Log.i("wk","==> onPause()");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i("wk","==>onStop()");


    }

    /**
     * 当Fragment中的布局移除时调用
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();

        Log.i("wk","==>onDestroyView()");
    }


    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.i("wk","==>onDestroy()");
    }

    /**
     * 当fragment和Activity分离时调用
     */
    @Override
    public void onDetach() {
        super.onDetach();

        Log.i("wk","==>onDetach()");
    }
}
