package com.merpyzf.reviewandroid.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.merpyzf.reviewandroid.R;

/**
 * 在Fragment1中点击按钮修改Fragment2中的TextView的内容
 */
public class TestFirstFragment extends Fragment {

    private Button btn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_test_first, container, false);






        return view;
    }

    /**
     *
     * getActivity方法可以让Fragment获取到关联的Fragment，然后再调用Activityde
     * findViewById方法，就可以获取到和这个Activity关联的其他Fragment的视图了。
     *
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        btn = (Button) getActivity().findViewById(R.id.btn_fg1);

        final TextView tv_fg2 = (TextView) getActivity().findViewById(R.id.tv_fg2);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                tv_fg2.setText("被修改了");



            }
        });


    }
}
