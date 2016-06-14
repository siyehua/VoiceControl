package com.siyehua.viewpagerandtoolsbar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SeekBar;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {


    @Bind(R.id.sb_media_voice)
    SeekBar sbMediaVoice;
    @Bind(R.id.sb_call_voice)
    SeekBar sbCallVoice;
    @Bind(R.id.sb_ring_voice)
    SeekBar sbRingVoice;
    @Bind(R.id.sb_notification_voice)
    SeekBar sbNotificationVoice;
    @Bind(R.id.sb_system_voice)
    SeekBar sbSystemVoice;
    @Bind(R.id.ch_no_voice)
    CheckBox chNoVoice;

    private AudioManager mAudioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        sbMediaVoice.setOnSeekBarChangeListener(onSeekBarChangeListener);
        sbSystemVoice.setOnSeekBarChangeListener(onSeekBarChangeListener);
        sbCallVoice.setOnSeekBarChangeListener(onSeekBarChangeListener);
        sbNotificationVoice.setOnSeekBarChangeListener(onSeekBarChangeListener);
        sbRingVoice.setOnSeekBarChangeListener(onSeekBarChangeListener);
        chNoVoice.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {//设置静音
                    mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 0, 0);
                    mAudioManager.setStreamVolume(AudioManager.STREAM_VOICE_CALL, 0, 0);
                    mAudioManager.setStreamVolume(AudioManager.STREAM_SYSTEM, 0, 0);
                    mAudioManager.setStreamVolume(AudioManager.STREAM_RING, 0, 0);
                    mAudioManager.setStreamVolume(AudioManager.STREAM_ALARM, 0, 0);
                    sbCallVoice.setProgress(0);
                    sbMediaVoice.setProgress(0);
                    sbRingVoice.setProgress(0);
                    sbNotificationVoice.setProgress(0);
                    sbSystemVoice.setProgress(0);
                } else {//设置最高音量
                    mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, mAudioManager
                            .getStreamMaxVolume(AudioManager.STREAM_MUSIC), 0);
                    mAudioManager.setStreamVolume(AudioManager.STREAM_VOICE_CALL, mAudioManager
                            .getStreamMaxVolume(AudioManager.STREAM_VOICE_CALL), 0);
                    mAudioManager.setStreamVolume(AudioManager.STREAM_SYSTEM, mAudioManager
                            .getStreamMaxVolume(AudioManager.STREAM_SYSTEM), 0);
                    mAudioManager.setStreamVolume(AudioManager.STREAM_RING, mAudioManager
                            .getStreamMaxVolume(AudioManager.STREAM_RING), 0);
                    mAudioManager.setStreamVolume(AudioManager.STREAM_ALARM, mAudioManager
                            .getStreamMaxVolume(AudioManager.STREAM_ALARM), 0);
                    sbCallVoice.setProgress(mAudioManager.getStreamMaxVolume(AudioManager
                            .STREAM_VOICE_CALL));
                    sbMediaVoice.setProgress(mAudioManager.getStreamMaxVolume(AudioManager
                            .STREAM_MUSIC));
                    sbRingVoice.setProgress(mAudioManager.getStreamMaxVolume(AudioManager
                            .STREAM_RING));
                    sbNotificationVoice.setProgress(mAudioManager.getStreamMaxVolume(AudioManager
                            .STREAM_ALARM));
                    sbSystemVoice.setProgress(mAudioManager.getStreamMaxVolume(AudioManager
                            .STREAM_SYSTEM));
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        initVoice();

    }

    @Override
    public void onBackPressed() {
        final SharedPreferences sharedPreferences = getSharedPreferences(SettingActivity
                .NOTIFICATION_FILENAME, Context.MODE_PRIVATE);
        boolean isChecked = sharedPreferences.getBoolean(SettingActivity.NOTIFICATION_KEY, false);
        if (isChecked) {
            VoiceIntentService.startActionFoo(this.getApplicationContext());
        }
        Intent i = new Intent(Intent.ACTION_MAIN);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.addCategory(Intent.CATEGORY_HOME);
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_option, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.setting://setting
                startActivity(new Intent(this, SettingActivity.class));
                break;
            case R.id.help://help
                startActivity(new Intent(this, HelpActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    SeekBar.OnSeekBarChangeListener onSeekBarChangeListener = new SeekBar.OnSeekBarChangeListener
            () {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            switch (seekBar.getId()) {
                case R.id.sb_media_voice:
                    mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
                    break;
                case R.id.sb_call_voice:
                    mAudioManager.setStreamVolume(AudioManager.STREAM_VOICE_CALL, progress, 0);
                    break;
                case R.id.sb_system_voice:
                    mAudioManager.setStreamVolume(AudioManager.STREAM_SYSTEM, progress, 0);
                    break;
                case R.id.sb_ring_voice:
                    mAudioManager.setStreamVolume(AudioManager.STREAM_RING, progress, 0);
                    break;
                case R.id.sb_notification_voice:
                    mAudioManager.setStreamVolume(AudioManager.STREAM_ALARM, progress, 0);
                    break;
            }
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    private void initVoice() {
        //////////////获得音量//////////////////////
        //通话音量
        int max = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_VOICE_CALL);
        int current = mAudioManager.getStreamVolume(AudioManager.STREAM_VOICE_CALL);
        Log.d("VIOCE_CALL", "max: " + max + " current : " + current);
        sbCallVoice.setMax(max);
        sbCallVoice.setProgress(current);

        //系统音量
        max = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_SYSTEM);
        current = mAudioManager.getStreamVolume(AudioManager.STREAM_SYSTEM);
        Log.d("SYSTEM", "max : " + max + " current : " + current);
        sbSystemVoice.setMax(max);
        sbSystemVoice.setProgress(current);
        //铃声音量
        max = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_RING);
        current = mAudioManager.getStreamVolume(AudioManager.STREAM_RING);
        Log.d("RING", "max : " + max + " current : " + current);
        sbRingVoice.setMax(max);
        sbRingVoice.setProgress(current);
        //音乐音量
        max = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        current = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        Log.d("MUSIC", "max : " + max + " current : " + current);
        sbMediaVoice.setMax(max);
        sbMediaVoice.setProgress(current);
        //提示声音音量
        max = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_ALARM);
        current = mAudioManager.getStreamVolume(AudioManager.STREAM_ALARM);
        Log.d("ALARM", "max : " + max + " current : " + current);
        sbNotificationVoice.setMax(max);
        sbNotificationVoice.setProgress(current);
        //////////////设置音量//////////////////////
        //        mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 音量大小, 0);
    }
}
