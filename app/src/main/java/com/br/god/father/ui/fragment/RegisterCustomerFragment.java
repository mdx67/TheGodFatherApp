package com.br.god.father.ui.fragment;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.br.god.father.ui.activity.MainActivity;

import butterknife.BindView;
import butterknife.OnClick;
import god.zup.com.br.godfatherapp.R;

public class RegisterCustomerFragment extends Fragment implements View.OnClickListener {


    @BindView(R.id.btNext)
    Button buttonNext;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register_customer, container, false);

        Button buttonNextStep = view.findViewById(R.id.btNext);
        buttonNextStep.setOnClickListener(this);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btNext:
                /** Do things you need to..
                 fragmentTwo = new FragmentTwo();

                 fragmentTransaction.replace(R.id.frameLayoutFragmentContainer, fragmentTwo);
                 fragmentTransaction.addToBackStack(null);

                 fragmentTransaction.commit();
                 */
                break;
        }
    }

    @OnClick(R.id.btNext)
    public void onClickBtNext() {
        ((MainActivity) getActivity()).replaceFragment(RegisterCredCardFragment.newInstance());

        ((MainActivity) getActivity()).getSupportActionBar().setIcon(new ColorDrawable(getResources().getColor(android.R.color.transparent)));
    }
}
