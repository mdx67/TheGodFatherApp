package com.br.god.father.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import god.zup.com.br.godfatherapp.R;

/**
 * Created by zup-matheus on 04/08/2017.
 */

public class RegisterCredCardFragment extends Fragment {

    public static RegisterCredCardFragment newInstance() {
        return new RegisterCredCardFragment();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register_credit_card, container, false);

//        Button buttonNextStep = view.findViewById(R.id.btNext);
//        buttonNextStep.setOnClickListener(this);

        return view;
    }
}
