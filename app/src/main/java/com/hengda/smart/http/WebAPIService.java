package http;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * @author WZQ
 * @version V1.0
 * @Description ${todo}
 * @Email :wzq_steady@126.com
 * @date 2016/6/29 10:34
 * @update (date)
 */
public interface WebAPIService {
    @FormUrlEncoded
    @POST("WebService/")
    Call<Webdata> getData(@Field("service") String first);

    @FormUrlEncoded
    @POST("index.php?a=checkVersion")
    Call<VersionInfo> checkVersion(@Field("appkey") String appKey,
                              @Field("appsecret") String appsecret,
                              @Field("appkind") String appkind,
                              @Field("device_id") String device_id,
                              @Field("version_no") String version_no
    );;
}
