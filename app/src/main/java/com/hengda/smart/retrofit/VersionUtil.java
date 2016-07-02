package com.hengda.smart.retrofit;

import android.content.Context;
import android.provider.Settings;

/**
 * @author WZQ
 * @version V1.0
 * @Description ${日常工具}
 * @Email :wzq_steady@126.com
 * @date 2016/7/2 09:09
 * @update (date)
 */
public class VersionUtil {

    public static String getDeviceId(Context context){
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

}
