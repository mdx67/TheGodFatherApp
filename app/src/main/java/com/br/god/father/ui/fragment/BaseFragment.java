package com.br.god.father.ui.fragment;

import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.br.god.father.R;

public class BaseFragment extends Fragment {

    protected void showMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }

    protected void showAlertDialogWithOKButton(final String tittle, final String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle(tittle);
        builder.setMessage(message);

        builder.setNegativeButton(android.R.string.ok, (dialog, id) -> dialog.dismiss());

        AlertDialog dialog = builder.create();

        dialog.show();
    }

}
