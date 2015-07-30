package cn.md.trainclient.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import cn.md.trainclient.R;
import cn.md.trainclient.common.IntentExtra;

import java.io.*;

/**
 * User: su
 * Date: 2015-07-12.
 */
public class ExaminationGuideActivity extends BaseActivity {
    @Override
    protected void onInit(Bundle savedInstanceState) {
        setContentView(R.layout.activity_exam_guide);

        actionBar.setTitle("考试");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayUseLogoEnabled(false);

        TextView content = (TextView) findViewById(R.id.content);
        content.setText(R.string.exam_guide);

        this.findViewById(R.id.start_exam).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ExaminationActivity.class);
                intent.putExtra(IntentExtra.INT_EXAMINATION_EXAM_TIME, 30);
                startActivity(intent);
                finish();
            }
        });
    }

    public String getString(InputStream inputStream) {
        InputStreamReader inputStreamReader;
        try {
            inputStreamReader = new InputStreamReader(inputStream, "utf-8");
        } catch (UnsupportedEncodingException e1) {
            return "";
        }
        BufferedReader reader = new BufferedReader(inputStreamReader);
        StringBuilder sb = new StringBuilder("");
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }
            inputStreamReader.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
