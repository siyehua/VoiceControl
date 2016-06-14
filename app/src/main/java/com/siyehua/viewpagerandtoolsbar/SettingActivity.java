package com.siyehua.viewpagerandtoolsbar;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SettingActivity extends AppCompatActivity {
    public static final String NOTIFICATION_FILENAME = SettingActivity.class.getSimpleName() +
            "_notification_file";
    public static final String NOTIFICATION_KEY = SettingActivity.class.getSimpleName() +
            "_notification_key";

    @Bind(R.id.cb_quickly_control)
    CheckBox cbQuicklyControl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        ActionBar toolbar = getDelegate().getSupportActionBar();
        assert toolbar != null;
        toolbar.setDisplayHomeAsUpEnabled(true);
        final SharedPreferences sharedPreferences = getSharedPreferences(NOTIFICATION_FILENAME,
                Context.MODE_PRIVATE);
        cbQuicklyControl.setChecked(sharedPreferences.getBoolean(NOTIFICATION_KEY, false));
        cbQuicklyControl.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                sharedPreferences.edit().putBoolean(NOTIFICATION_KEY, isChecked).apply();
            }
        });
    }
}
