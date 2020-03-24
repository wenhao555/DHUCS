package com.example.dhucs.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dhucs.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserFengFragment extends Fragment
{

    public UserFengFragment()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_feng, container, false);
        return view;
    }
}
