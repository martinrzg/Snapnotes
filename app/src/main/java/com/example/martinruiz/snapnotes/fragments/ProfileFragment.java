package com.example.martinruiz.snapnotes.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.martinruiz.snapnotes.R;
import com.example.martinruiz.snapnotes.activity.LogInActivity;
import com.example.martinruiz.snapnotes.activity.SettingsActivity;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    @BindView(R.id.textViewProfileName) TextView textViewProfileName;
    @BindView(R.id.imageViewProfile) ImageView imageViewProfile;
    @BindView(R.id.textViewEmail) TextView textViewEmail;

    public ProfileFragment() {
        // Required empty public constructor
    }
    public static ProfileFragment create (){
        return new ProfileFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this,view);
        view.setTag(20);

        textViewProfileName.setText(FirebaseAuth.getInstance().getCurrentUser().getDisplayName());
        textViewEmail.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail());

        String photoUrl = FirebaseAuth.getInstance().getCurrentUser().getPhotoUrl().toString();
        Glide.with(view).load(photoUrl).apply(RequestOptions.circleCropTransform()).into(imageViewProfile);

        return view;
    }

    @OnClick(R.id.imageViewSettings)
    public void settingsClick(){
        Intent settingsIntent = new Intent(getActivity(), SettingsActivity.class);
        startActivity(settingsIntent);
    }

}
