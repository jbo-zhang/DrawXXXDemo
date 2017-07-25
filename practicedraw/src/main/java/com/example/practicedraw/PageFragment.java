package com.example.practicedraw;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.HashMap;

/**
 * Created by zhangjinbo on 17-7-24.
 */

public class PageFragment extends Fragment {
    private View view;
    public static PageFragment newInstance(View view) {
        PageFragment fragment = new PageFragment(view);
        return fragment;
    }

    public PageFragment(View view) {
        this.view = view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return view;
    }
}
