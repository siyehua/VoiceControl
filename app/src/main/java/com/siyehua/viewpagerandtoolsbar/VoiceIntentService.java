package com.siyehua.viewpagerandtoolsbar;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class VoiceIntentService extends IntentService {
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private static final String ACTION_FOO = "com.siyehua.viewpagerandtoolsbar.action.FOO";
    private static final String ACTION_BAZ = "com.siyehua.viewpagerandtoolsbar.action.BAZ";

    // TODO: Rename parameters
    private static final String EXTRA_PARAM1 = "com.siyehua.viewpagerandtoolsbar.extra.PARAM1";
    private static final String EXTRA_PARAM2 = "com.siyehua.viewpagerandtoolsbar.extra.PARAM2";


    public static boolean flag = true;

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionFoo(Context context) {
        Intent intent = new Intent(context, VoiceIntentService.class);
        intent.setAction(ACTION_FOO);
        intent.putExtra(EXTRA_PARAM1, "10086");
        context.startService(intent);
    }

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, VoiceIntentService.class);
        intent.setAction(ACTION_FOO);
        return intent;
    }


    public VoiceIntentService() {
        super(VoiceIntentService.class.getSimpleName());
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_FOO.equals(action)) {
                String a = intent.getStringExtra(EXTRA_PARAM1);
                if (a != null && !a.equals("")) {
                    SendNotification.setMsgNotification(this, "快捷开关已开启");
                } else handleActionFoo();
            }
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionFoo() {
        AudioManager mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        if (flag) {//设置静音
            mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 0, 0);
            mAudioManager.setStreamVolume(AudioManager.STREAM_VOICE_CALL, 0, 0);
            mAudioManager.setStreamVolume(AudioManager.STREAM_SYSTEM, 0, 0);
            mAudioManager.setStreamVolume(AudioManager.STREAM_RING, 0, 0);
            mAudioManager.setStreamVolume(AudioManager.STREAM_ALARM, 0, 0);
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
        }
        flag = !flag;
        SendNotification.setMsgNotification(this);
    }

}
