package com.iwhys.androidwheel;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;

import com.iwhys.library.widget.DatePickerDialog;
import com.iwhys.library.widget.TimePickerDialog;

import java.util.Date;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    private Button date, time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        date = (Button) findViewById(R.id.date);
        time = (Button) findViewById(R.id.time);
        date.setOnClickListener(this);
        time.setOnClickListener(this);
    }

    @Override
    public void onClick(final View v) {
        switch (v.getId()){
            case R.id.date:
                int[] dateValue = new int[]{1990, 1, 1};
                if (date.getTag() != null) {
                    dateValue = (int[]) date.getTag();
                }
                new DatePickerDialog(this, dateValue[0], dateValue[1], dateValue[2], new DatePickerDialog.OnSelectListener() {
                    @Override
                    public void onSelect(int[] values, String displayName) {
                        date.setText(displayName);
                        date.setTag(values);
                    }
                }).show();
                break;
            case R.id.time:
                Date timeValue = (Date) time.getTag();
                final Date current = new Date();
                if (timeValue == null || timeValue.before(current)) {//没有设置提醒时间，则默认当前时间往后推一天
                    timeValue = current;
                }
                new TimePickerDialog(this, timeValue, new TimePickerDialog.OnSelectListener() {
                    @Override
                    public void onSelect(Date value, String displayName) {
                        time.setText(displayName);
                        time.setTag(value);
                    }
                }).show();
                break;
        }
    }

}
