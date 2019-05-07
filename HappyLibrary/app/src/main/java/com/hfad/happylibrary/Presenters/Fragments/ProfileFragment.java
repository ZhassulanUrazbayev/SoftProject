package com.hfad.happylibrary.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.hfad.happylibrary.Models.Login;
import com.hfad.happylibrary.R;


public class ProfileFragment extends Fragment {
    Button sign_out;
    TextView email_value, number_value, name_value;
    public ProfileFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        sign_out = view.findViewById(R.id.sign_out);
        sign_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();

                Intent intent = new Intent(getActivity(), Login.class);
                startActivity(intent);

                getActivity().finish();
            }
        });

        email_value = view.findViewById(R.id.email_profile_value);
        email_value.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail());

        return view;
    }
}
