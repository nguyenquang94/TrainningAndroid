package com.kai.libre.apptrainning.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.kai.libre.apptrainning.R;
import com.kai.libre.apptrainning.common.AppConstants;

/**
 * Created by Kai on 1/17/2017.
 */

public class DialogFragmentConfirm extends DialogFragment {
    private AlertDialog alertDialog;

    private Context currentActivity;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        currentActivity = getActivity();
        Bundle bundle = getArguments();
        String nameEmployee = bundle.getString(AppConstants.NAME_EMPLOYEE);
        String token = bundle.getString(AppConstants.TOKEN);
        int userId = bundle.getInt(AppConstants.USER_ID);
        int badgeId = bundle.getInt(AppConstants.BADGE_ID);
        int creatorId = bundle.getInt(AppConstants.CREATOR_ID);
        String message = getString(R.string.tittle_dialog_confirm) + AppConstants.STRING_SPACE + nameEmployee;
        View view = LayoutInflater.from(currentActivity).inflate(R.layout.dialog_confirm, null);
        TextView tvMessage = (TextView) view.findViewById(R.id.message);
        tvMessage.setText(message);
        alertDialog = new AlertDialog.Builder(currentActivity).setView(view).setTitle(R.string.dialog_confirm)
                .setNegativeButton(R.string.btn_cancel, new android.content.DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).setPositiveButton(R.string.btn_ok, new android.content.DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).create();
        return alertDialog;
    }

}
