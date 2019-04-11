package com.thalhah.instagramclone;


import android.os.Bundle;
import com.google.android.material.button.MaterialButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {


    @BindView(R.id.pp_profile)
    CircleImageView ppProfile;
    @BindView(R.id.txtpost)
    TextView txtpost;
    @BindView(R.id.txtfollower)
    TextView txtfollower;
    @BindView(R.id.txtfollowing)
    TextView txtfollowing;
    @BindView(R.id.btneditprofile)
    MaterialButton btneditprofile;
    @BindView(R.id.p_username)
    TextView pUsername;
    @BindView(R.id.p_bio)
    TextView pBio;
    private Unbinder unbinder;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.btneditprofile)
    void onViewClicked() {
    }
}
