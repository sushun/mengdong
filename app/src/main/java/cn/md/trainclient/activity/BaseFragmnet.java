package cn.md.trainclient.activity;


import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.View;
import cn.md.trainclient.R;
import cn.md.trainclient.utils.ColoredSnackbar;

/**
 * Created by jindongping on 15/7/24.
 */
public class BaseFragmnet extends Fragment {


    protected  void snackShow(View view, @NonNull CharSequence msg) {
        Snackbar.make(view, msg, Snackbar.LENGTH_SHORT).setAction("取消", new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        }).setActionTextColor(BaseFragmnet.this.getResources().getColor(R.color.primary)).show();
    }

    protected  void snackErr(View view, @NonNull CharSequence msg) {
        ColoredSnackbar.alert(Snackbar.make(view, msg, Snackbar.LENGTH_SHORT).setAction("取消", new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        })).setActionTextColor(BaseFragmnet.this.getResources().getColor(R.color.primary)).show();
    }
}
