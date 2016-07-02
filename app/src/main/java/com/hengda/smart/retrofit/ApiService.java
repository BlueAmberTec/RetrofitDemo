package com.hengda.smart.retrofit;

import com.hengda.smart.versioncheck.VersionInfo;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by blueamer on 1/7/16.
 */
public interface ApiService {


//    http://101.200.234.14/APPCloud/
    @FormUrlEncoded
    @POST("index.php?a=checkVersion")
    Observable<VersionInfo> checkVersion(@Field("appKey") String appKey,
                                         @Field("appSecret") String appSecret,
                                         @Field("deviceId") String deviceId,
                                         @Field("versionCode") String versionCode,
                                         @Field("appKind") String appKind
    );

}
