package com.example.martinruiz.snapnotes.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by MartinRuiz on 10/17/2017.
 */

public abstract class BaseFragment extends Fragment {
    private View root;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(getLayoutResId(), container, false);
        atOnCreateView(root,container,savedInstanceState);
        return root;
    }

    public abstract void atOnCreateView(View root, @Nullable ViewGroup container, Bundle savedInstanceState);

    @LayoutRes
    public abstract int getLayoutResId();

}
