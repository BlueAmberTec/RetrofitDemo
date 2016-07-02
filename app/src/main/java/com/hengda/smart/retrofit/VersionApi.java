package com.hengda.smart.retrofit;

import com.hengda.smart.versioncheck.HDVersionConfig;
import com.hengda.smart.versioncheck.VersionInfo;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 *  blueAmer 20160701
 */
public final class VersionApi {

    public Observable<VersionInfo> getVersionInfo(String appKey, String appSecret, String deviceId, int versionCode, String kind) {
        return RetrofitUtil.getApi(HDVersionConfig.BASE_URL).checkVersion(appKey,appSecret,deviceId,versionCode+"",kind)
                .subscribeOn(Schedulers.newThread())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
