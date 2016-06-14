package com.siyehua.viewpagerandtoolsbar;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

public class HelpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        ActionBar toolbar = getDelegate().getSupportActionBar();
        assert toolbar != null;
        toolbar.setDisplayHomeAsUpEnabled(true);
    }
}
