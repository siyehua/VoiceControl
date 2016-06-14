package com.siyehua.viewpagerandtoolsbar;/**
 * Created by huangxk on 2016/6/13.
 */

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.support.v7.app.NotificationCompat;

/**
 * @method
 * @pram
 * @return
 */
public class SendNotification {
    public static void setMsgNotification(Context context) {
        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService
                (Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context);
        mBuilder.setContentTitle("一键静音")//设置通知栏标题
                .setContentText(VoiceIntentService.flag ? "点击通知一键静音" : "点击通知恢复声音") //设置通知栏显示内容

                .setContentIntent(getDefaultIntent(context, Notification.FLAG_NO_CLEAR |
                        Notification.FLAG_ONGOING_EVENT))//设置通知栏点击意图
                        //提醒标志符成员：
                        //Notification.FLAG_SHOW_LIGHTS         //三色灯提醒，在使用三色灯提醒时候必须加该标志符
                        //Notification.FLAG_ONGOING_EVENT       //发起正在运行事件（活动中）
                        //Notification.FLAG_INSISTENT           //让声音、振动无限循环，直到用户响应 （取消或者打开）
                        //Notification.FLAG_ONLY_ALERT_ONCE     //发起Notification后，铃声和震动均只执行一次
                        //Notification.FLAG_AUTO_CANCEL         //用户单击通知后自动消失
                        //Notification.FLAG_NO_CLEAR            //只有全部清除时，Notification才会清除 ，不清楚该通知
                        // (QQ的通知无法清除，就是用的这个)
                        // Notification.FLAG_FOREGROUND_SERVICE //表示正在运行的服务
                        //
                        //contentIntent：在通知窗口区域，Notification被单击时的响应事件由该intent触发；
                        //deleteIntent：当用户点击全部清除按钮时，响应该清除事件的Intent；
                        //fullScreenIntent：响应紧急状态的全屏事件（例如来电事件），也就是说通知来的时候，跳过在通知区域点击通知这一步，直接执行fullScreenIntent代表的事件。

                .setTicker(VoiceIntentService.flag ? "恢复声音" : "已静音") //通知首次出现在通知栏，带上升动画效果的
                .setWhen(System.currentTimeMillis())//通知产生的时间，会在通知信息里显示，一般是系统获取到的时间

                        //.setPriority(Notification.PRIORITY_DEFAULT) //设置该通知优先级
                        //注意只有API16及以上版本才能设置该方法
                        //Notification.PRIORITY_MAX	重要而紧急的通知，通知用户这个事件是时间上紧迫的或者需要立即处理的。
                        //Notification.PRIORITY_HIGH	高优先级用于重要的通信内容，例如短消息或者聊天，这些都是对用户来说比较有兴趣的。
                        //Notification.PRIORITY_DEFAULT	默认优先级用于没有特殊优先级分类的通知。
                        //Notification.PRIORITY_LOW	低优先级可以通知用户但又不是很紧急的事件。
                        //Notification.PRIORITY_MIN	用于后台消息 (例如天气或者位置信息)
                        // 。最低优先级通知将只在状态栏显示图标，只有用户下拉通知抽屉才能看到内容。


                        //.setAutoCancel(true)//设置这个标志当用户单击面板就可以让通知将自动取消

                .setOngoing(true)//ture，设置他为一个正在进行的通知。他们通常是用来表示一个后台任务,用户积极参与(如播放音乐)或以某种方式正在等待,

                .setDefaults(Notification.DEFAULT_SOUND)
                        //功能：向通知添加声音、闪灯和振动效果的最简单、使用默认（defaults）属性，可以组合多个属性（和方法1中提示效果一样的）对应属性
                        //Notification.DEFAULT_VIBRATE    //添加默认震动提醒  需要 VIBRATE permission
                        //Notification.DEFAULT_SOUND    // 添加默认声音提醒
                        //Notification.DEFAULT_LIGHTS// 添加默认三色灯提醒
                        // Notification.DEFAULT_ALL// 添加默认以上3种全部提醒

                .setSmallIcon(R.drawable.ic_launcher)//设置通知小ICON

        //.setVibrate(new long[]{0, 300, 500, 700})
        //注意只有前面setDefaults设置了震动,并添加了权限,该方法才有效
        //实现效果：延迟0ms，然后振动300ms，在延迟500ms，接着在振动700ms。

        //.setLights(0xff0000ff, 300, 0)
        // 功能：android支持三色灯提醒，这个方法就是设置不同场景下的不同颜色的灯。
        //描述：其中ledARGB 表示灯光颜色、 ledOnMS 亮持续时间、ledOffMS 暗的时间。
        // 注意：1）只有只有前面setDefaults(),Flags为Notification.FLAG_SHOW_LIGHTS的时候，才支持三色灯提醒。
        //2）这边的颜色跟设备有关，不是所有的颜色都可以，要看具体设备。

        //功能：设置默认或则自定义的铃声，来提醒。
        //.setDefaults(Notification.DEFAULT_SOUND)//获取默认铃声
        //.setSound(Uri.parse("file:///sdcard/xx/xx.mp3"))//获取自定义铃声
        //.setSound(Uri.withAppendedPath(Audio.Media.INTERNAL_CONTENT_URI, "5"))
        // 获取Android多媒体库内的铃声

        //.setProgress(100, 50, true)
        //属性：max:进度条最大数值  、progress:当前进度、indeterminate:表示进度是否不确定，true为不确定
        //功能：设置带进度条的通知，可以在下载中使用
        //注意：此方法在4.0及以后版本才有用，如果为早期版本：需要自定义通知布局，其中包含ProgressBar视图
        //使用：如果为确定的进度条：调用setProgress(max, progress, false)
        // 来设置通知，在更新进度的时候在此发起通知更新progress，并且在下载完成后要移除进度条，通过调用setProgress(0, 0, false)既可。

        ;

        mNotificationManager.notify(0, mBuilder.build());
    }

    public static void cancelNotification(Context context) {
        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService
                (Context.NOTIFICATION_SERVICE);
        mNotificationManager.cancelAll();
    }

    private static PendingIntent getDefaultIntent(Context context, int flags) {

        //根据需求创建需要跳转到Activity,Service,Broadcast
        return PendingIntent.getService(context, 0, VoiceIntentService.getStartIntent(context),
                flags);
    }
}
