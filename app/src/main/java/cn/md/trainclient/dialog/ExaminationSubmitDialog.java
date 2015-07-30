package cn.md.trainclient.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import cn.md.trainclient.activity.ExaminationActivity;

/**
 * User: su
 * Date: 2015-07-12.
 */
public class ExaminationSubmitDialog extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("确认提交")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (getTargetFragment() != null) {
                            getTargetFragment().onActivityResult(ExaminationActivity.REQUEST_CODE_SUBMIT,
                                    Activity.RESULT_OK,
                                    new Intent());
                        }
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (getTargetFragment() != null) {
                            getTargetFragment().onActivityResult(ExaminationActivity.REQUEST_CODE_SUBMIT,
                                    Activity.RESULT_CANCELED,
                                    new Intent());
                        }
                    }
                });

        return builder.create();
    }

}
