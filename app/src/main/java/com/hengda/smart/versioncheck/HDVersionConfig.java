package com.hengda.smart.versioncheck;

/**
 * @author WZQ
 * @version V1.0
 * @Description ${版本更新配置信息}
 * @Email :wzq_steady@126.com
 * @date 2016/7/2 08:54
 * @update (date)
 */
public class HDVersionConfig {


    public final static String BASEPATH = android.os.Environment.getExternalStorageDirectory().getAbsolutePath();
    // BASEURL
    public static final String BASE_URL  = "http://101.200.234.14/APPCloud/";
    // APPKEY
    public static final String APP_KEY   = "205bef8446f0d56390e7cfe25a9f80c4";
    // ScretKey
    public static final String APP_SECRET= "89d37355267762c785019d65598ed91c";
    //PATH
    public static final String  DOWNLOADPATH =BASEPATH+"/HD_Demo/" ;


    public class AppKind {
        public static final String MOBLILE_APP     =   "1" ;
        public static final String IOS_APP         =   "2" ;
        public static final String HDDEVICE_APP    =   "3" ;
    }

    public class STATUS{
        public static final String CODE_ERROR           =   "4041";
        public static final String CODE_NO_UP_          =   "2001";
        public static final String CODE_NEW_VERSION     =   "2002";
    }

    //下载服务
    public static final  String  SERIVCE_DIALOG       = "com.hengda.smart.versioncheck.DownloadApkServiceDialog";
    public static final  String  SERIVCE_NOTIFICATION = "com.hengda.smart.versioncheck.DownloadApkServiceNotification";


}



