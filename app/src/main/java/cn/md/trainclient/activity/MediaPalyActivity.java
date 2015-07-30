package cn.md.trainclient.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.PixelFormat;
import android.graphics.drawable.AnimationDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.*;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.*;
import android.widget.SeekBar.OnSeekBarChangeListener;
import cn.md.trainclient.R;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MediaPalyActivity extends Activity implements SurfaceHolder.Callback {
    private MediaPlayer mMediaPlayer01;
    private SurfaceView mSurfaceView01;
    private SurfaceHolder mSurfaceHolder01;
    private Button myButton, fullButton;
    private LinearLayout linerLayout;
    private ImageView imageView;
    private AnimationDrawable frameAnimation;
    private SeekBar seekBar;
    //是否暂停
    private boolean bIsPaused = false;
    //是否是第一次播放
    private boolean bIsNotStart = true;
    private boolean isDissMissView = false;
    private boolean dissMissing = false;
    private LayoutParams lp;
    private int screenWidthPixels, screenHightPixels, videoWidth, videoHeight;
    private TextView allTime, currentTime;
    private TextView playTitle, playContent;
    private Runnable disMissRun;
    //视屏地址 url 可以是本地的 也可以是网络的
    private static final String url = getDir() + "/a.mp4";
    //弹出dialog的时间集合
    private List<String> pauseTimes = new ArrayList<String>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inWindow();
        pauseTimes.add("3000");
        pauseTimes.add("5000");
        pauseTimes.add("9000");
        setContentView(R.layout.activity_palymedia);
        intiview();
        getWindow().setFormat(PixelFormat.UNKNOWN);

    }

    private void intiview() {
        linerLayout = (LinearLayout) findViewById(R.id.liner_layout);
        imageView = (ImageView) findViewById(R.id.imgeView);
        imageView.setBackgroundResource(R.anim.loading);
        frameAnimation = (AnimationDrawable) imageView.getBackground();
        playTitle = (TextView) findViewById(R.id.play_title);
        playContent = (TextView) findViewById(R.id.play_content);
        //设置下面文字
        setContent();
        setRelativeLayout();
        allTime = (TextView) findViewById(R.id.alltime);
        currentTime = (TextView) findViewById(R.id.currenttime);
        setSurfaceView();
        setSeekBar();
        setPalyButton();
        fullButton = (Button) findViewById(R.id.full_screen);
        fullButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (getApplicationContext().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT
                        && !bIsNotStart) {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                } else if (!bIsNotStart) {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                }
            }
        });
    }

    private void setRelativeLayout() {
        RelativeLayout re = (RelativeLayout) findViewById(R.id.relaout01);
        re.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                clickRelativeLayout();

            }
        });
    }

    private void setSurfaceView() {
        mSurfaceView01 = (SurfaceView) findViewById(R.id.mSurfaceView1);
        mSurfaceView01.setBackgroundResource(R.color.black);
        lp = mSurfaceView01.getLayoutParams();
        lp.width = LayoutParams.MATCH_PARENT;
        DisplayMetrics dms = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dms);
        screenHightPixels = dms.heightPixels;
        screenWidthPixels = dms.widthPixels;
        lp.height = screenWidthPixels * 309 / 500; //高度为宽度的黄金分割比
        mSurfaceView01.setLayoutParams(lp);
        mSurfaceHolder01 = mSurfaceView01.getHolder();
        mSurfaceHolder01.addCallback(this);
        // mSurfaceHolder01.setFixedSize(176, 144);
        mSurfaceHolder01.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }

    private void setPalyButton() {
        myButton = (Button) findViewById(R.id.mybutton);
        myButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //首次点击播放
                if (checkSDCard() && mMediaPlayer01 == null) {
                    firstPaly(url);
                }
                //非首次点击播放
                else if (checkSDCard() && mMediaPlayer01 != null) {
                    clickPalyButton();
                }
            }
        });
    }

    private void firstPaly(final String url) {
        if (bIsNotStart) {
            dissMIssView();
            isDissMissView = true;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    playVideo(url);
                }
            }).start();
            bIsNotStart = false;
            myButton.setBackgroundResource(R.drawable.pause);
        }
    }

    /**
     * 非首次点击播放 点击播放按钮事件
     */
    private void clickPalyButton() {
        if (!bIsNotStart) {
            if (!bIsPaused) {
                mMediaPlayer01.pause();
                bIsPaused = true;
                myButton.setBackgroundResource(R.drawable.play);
                handler.removeCallbacks(disMissRun);
            } else {
                mMediaPlayer01.start();
                bIsPaused = false;
                myButton.setBackgroundResource(R.drawable.pause);
                dissMIssView();
            }
        }
    }

    /**
     * 点击surface界面事件
     */
    private void clickRelativeLayout() {
        if (!bIsPaused && !bIsNotStart) {
            if (!isDissMissView) {
                dissMIssView();
            } else {
                seekBar.setVisibility(View.VISIBLE);
                myButton.setVisibility(View.VISIBLE);
                fullButton.setVisibility(View.VISIBLE);
                allTime.setVisibility(View.VISIBLE);
                currentTime.setVisibility(View.VISIBLE);
                isDissMissView = false;
                dissMIssView();
            }
        }
    }

    private void setSeekBar() {
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar arg0) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar arg0) {
                if (mMediaPlayer01 != null) {
                    int total = mMediaPlayer01.getDuration();
                    int max = seekBar.getMax();
                    mMediaPlayer01.seekTo(arg0.getProgress() * total / max);
                }
            }
        });
    }

    private void setContent() {
        playTitle.setText("主题");
        playContent.setText("内容");
    }

    private boolean isDestory = false;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mMediaPlayer01 != null) {
            mMediaPlayer01.release();
            mMediaPlayer01 = null;
        }
        isDestory = true;
    }

    int total;
    int currentPositon;
    //每100ms回调本方法,更新一次seekBar,并检查是否要展厅显示东西
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (!isDestory) {
                super.handleMessage(msg);
                currentPositon = mMediaPlayer01.getCurrentPosition();
                total = mMediaPlayer01.getDuration();
                int max = seekBar.getMax();
                if (currentPositon >= 0 && total != 0) {
                    seekBar.setProgress(currentPositon * max / total);
                }
                allTime.setText(formatLongToTimeStr(total));
                currentTime.setText(formatLongToTimeStr(currentPositon));
                checkIsPuaseAndShowDialog(currentPositon + "");
            }
        }
    };
    private String lastCurrentPositon = "0";

    private void checkIsPuaseAndShowDialog(String currentPositon) {

        for (String time : pauseTimes) {
            if (Integer.parseInt(lastCurrentPositon) <= Integer.parseInt(time) && Integer.parseInt(time) <= Integer.parseInt(currentPositon)) {
                clickRelativeLayout();
                clickPalyButton();
                showMyDialog();
                lastCurrentPositon = currentPositon;
            }
        }
    }

    private void showMyDialog() {
        new AlertDialog.Builder(MediaPalyActivity.this).setTitle("对话框")//设置对话框标题
                .setMessage("message！")//设置显示的内容
                .setPositiveButton("继续播放", new DialogInterface.OnClickListener() {//添加确定按钮
                    @Override
                    public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件
                        // TODO Auto-generated method stub
                        clickPalyButton();
                    }

                }).setNegativeButton("返回", new DialogInterface.OnClickListener() {//添加返回按钮

            @Override
            public void onClick(DialogInterface dialog, int which) {//响应事件
                // TODO Auto-generated method stub
            }

        }).show();//在按键响应事件中显示此对话框
    }

    private String formatLongToTimeStr(int l) {
        int hour = 0;
        int minute = 0;
        int second;

        second = l / 1000;

        if (second > 60) {
            minute = second / 60;
            second = second % 60;
        }
        if (minute > 60) {
            hour = minute / 60;
            minute = minute % 60;
        }
        return (getTwoLength(hour) + ":" + getTwoLength(minute) + ":" + getTwoLength(second));
    }

    private String getTwoLength(final int data) {
        if (data < 10) {
            return "0" + data;
        } else {
            return "" + data;
        }
    }

    private void dissMIssView() {
        if (!dissMissing) {
            dissMissing = true;
            disMissRun = new Runnable() {

                @Override
                public void run() {
                    seekBar.setVisibility(View.GONE);
                    myButton.setVisibility(View.GONE);
                    fullButton.setVisibility(View.GONE);
                    allTime.setVisibility(View.GONE);
                    currentTime.setVisibility(View.GONE);
                    isDissMissView = true;
                }
            };
            handler.postDelayed(disMissRun, 3000);
            new onceDelayThread(3000).start();
        }
    }

    private int saveTime;

    /**
     * @param strPath 播放地址可以是本地或者网络
     */
    private void playVideo(String strPath) {
        initMediaPlayer();
        try {
            mMediaPlayer01.setDataSource(strPath);
            mMediaPlayer01.prepare();
        } catch (IllegalArgumentException e1) {
            e1.printStackTrace();
        } catch (SecurityException e1) {
            e1.printStackTrace();
        } catch (IllegalStateException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 初始化MediaPlayer
     */
    private void initMediaPlayer() {
        mMediaPlayer01 = new MediaPlayer();
        mMediaPlayer01.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mMediaPlayer01.setOnBufferingUpdateListener(new OnBufferingUpdateListener() {
            @Override
            public void onBufferingUpdate(MediaPlayer mp, int percent) {
                seekBar.setSecondaryProgress(percent);
                saveTime = percent % 100 * total / 100 - currentPositon;
                //刚开是加载网络资源，saveTime(缓存时间)为0
                if (saveTime == 0) {
                    startLodingAnimation();
                } else {
                    stopLodingAnimation();
                }
            }
        });

        mMediaPlayer01.setOnPreparedListener(new OnPreparedListener() {

            @Override
            public void onPrepared(MediaPlayer mp) {
                videoWidth = mMediaPlayer01.getVideoWidth();
                videoHeight = mMediaPlayer01.getVideoHeight();
                if (videoHeight != 0 && videoWidth != 0) {
                    mp.start();
                    DelayThread delaythread = new DelayThread(100);
                    delaythread.start();
                    mSurfaceView01.setBackgroundDrawable(null);
                }
            }
        });
        mMediaPlayer01.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer arg0) {

            }
        });

        mMediaPlayer01.setDisplay(mSurfaceHolder01);
    }

    /**
     * 停止加载动画
     */
    private void stopLodingAnimation() {
        frameAnimation.setOneShot(false);
        frameAnimation.stop();
        linerLayout.setVisibility(View.GONE);
    }

    /**
     * 开始加载动画
     */
    private void startLodingAnimation() {
        linerLayout.setVisibility(View.VISIBLE);
        frameAnimation.setOneShot(false);
        frameAnimation.start();
    }

    private class DelayThread extends Thread {
        private int delayTime;

        private DelayThread(int delayTime) {
            this.delayTime = delayTime;
        }

        @Override
        public void run() {
            super.run();
            while (true) {
                try {
                    sleep(delayTime);
                    handler.sendEmptyMessage(0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private class onceDelayThread extends Thread {
        private int delayTime;

        private onceDelayThread(int delayTime) {
            this.delayTime = delayTime;
        }

        @Override
        public void run() {
            super.run();
            try {
                sleep(delayTime);
                dissMissing = false;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if (getApplicationContext().getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            lp = mSurfaceView01.getLayoutParams();
            lp.width = screenHightPixels;
            lp.height = screenWidthPixels;
            mSurfaceView01.setLayoutParams(lp);
            fullButton.setBackgroundResource(R.drawable.small_screen);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        } else {
            //横屏切换成竖屏重新计算 lp
            lp = mSurfaceView01.getLayoutParams();
            lp.width = LayoutParams.MATCH_PARENT;
            lp.height = screenWidthPixels * 309 / 500;  //高度为宽度的黄金分割比
            mSurfaceView01.setLayoutParams(lp);
            fullButton.setBackgroundResource(R.drawable.full_screen);
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceholder, int format, int w,
                               int h) {
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceholder) {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceholder) {
    }

    /**
     * @return 储存卡更目录
     */
    private static File getDir() {
        File temp = Environment.getExternalStorageDirectory();
        String dirPaht = temp.getPath();
        File dir = new File(dirPaht);
        if (dir.exists()) {
            return dir;
        } else {
            dir.mkdirs();
            return dir;
        }
    }

    private boolean checkSDCard() {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }

    public void inWindow() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

}