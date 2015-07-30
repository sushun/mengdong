package cn.md.trainclient.activity;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;
import cn.md.trainclient.R;
import cn.md.trainclient.utils.ColoredSnackbar;
import cn.md.trainclient.utils.StringUtils;
import com.readystatesoftware.systembartint.SystemBarTintManager;

/**
 * User: su
 * Date: 2015-07-10.
 */
public abstract class BaseActivity extends AppCompatActivity {
    protected ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        actionBar = getSupportActionBar();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintResource(R.color.primary);
        onInit(savedInstanceState);
    }


    protected abstract void onInit(Bundle savedInstanceState);

    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (android.R.id.home == item.getItemId()) {
            finish();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    public void toast(String message) {
        if (!StringUtils.isEmpty(message)) {
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
        }
    }
    protected  void snackShow(View view, @NonNull CharSequence msg) {
        Snackbar.make(view, msg, Snackbar.LENGTH_SHORT).setAction("取消", new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        }).setActionTextColor(getResources().getColor(R.color.primary)).show();
    }

    protected  void snackErr(View view, @NonNull CharSequence msg) {
        ColoredSnackbar.alert(Snackbar.make(view, msg, Snackbar.LENGTH_SHORT).setAction("取消", new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        })).setActionTextColor(getResources().getColor(R.color.primary)).show();
    }
}
