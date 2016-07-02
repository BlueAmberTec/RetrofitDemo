package com.hengda.smart.retrofitdemo;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hengda.smart.retrofit.VersionApi;
import com.hengda.smart.retrofit.VersionUtil;
import com.hengda.smart.versioncheck.DownStyle;
import com.hengda.smart.versioncheck.DownloadApkServiceDialog;
import com.hengda.smart.versioncheck.DownloadApkServiceNotification;
import com.hengda.smart.versioncheck.HDVersionConfig;
import com.hengda.smart.versioncheck.VersionInfo;
import com.hengda.smart.widget.animnew.BounceTopEnter;
import com.hengda.smart.widget.animnew.ZoomOutExit;
import com.hengda.smart.widget.simple.NormalDialog;
import com.hengda.smart.widget.simple.OnBtnClickL;

import org.kymjs.kjframe.utils.KJLoger;
import org.kymjs.kjframe.utils.SystemTool;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;

/**
 * 今天学习一下最近火的不要不要的retorfit + okhttp+ RxJava(Android)
 * 之前一直在kjframe，感觉不错，但是retrofit +okhttp 到哪儿，移动开发人员都是饭后谈资
 * 所以尝试一下转型，学习了解一下
 * 1 Retrofit 2.0介绍
 * Retrofit是SQUARE美国一家移动支付公司最近新发布的在Android平台上http访问的开源项目
 * 为Android平台的应用提供一个类型安全的REST客户端
 */
public class MainActivity extends AppCompatActivity {


    @BindView(R.id.btn_versionCheck_dialog)
    Button btnVersionCheckDialog;
    @BindView(R.id.btn_versionCheck_noti)
    Button btnVersionCheckNoti;
    @BindView(R.id.btn_retrofit)
    Button btnRetrofit;
    @BindView(R.id.tv_show)
    TextView tvShow;
    @BindView(R.id.tv_content)
    TextView tvContent;

    //弹框
    private NormalDialog dialog ;
    private VersionApi versionApi = new VersionApi();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
       dialog = new NormalDialog(this);
    }

    @OnClick({R.id.btn_versionCheck_dialog, R.id.btn_versionCheck_noti, R.id.btn_retrofit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_versionCheck_dialog:
                checkVersion(DownStyle.DIALOG_SHOW);
                break;
            case R.id.btn_versionCheck_noti:
                checkVersion(DownStyle.NOTIFICATION_SHOW);
                break;
            case R.id.btn_retrofit:

                break;
        }
    }


    //===========================================================================
    //===========================版本升级====================================
    //===========================================================================

    /**
     * 显示下载弹框
     */
    private void downloadAPKDialog(final VersionInfo.VersionInfoBean appVersion, final DownStyle style) {
        // TODO Auto-generated method stub
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.title("有新的版本")
                .titleTextColor(Color.parseColor("#9f1f1d"))
                .bgColor(Color.parseColor("#ffffff"))//
                .cornerRadius(5)//
                .content(appVersion.getVersionLog())//
                .contentGravity(Gravity.CENTER)//
                .contentTextColor(Color.parseColor("#383838"))//
                .dividerColor(Color.parseColor("#222222"))//
                .btnTextSize(15.5f, 15.5f)//
                .btnText("更新", "取消")
                .btnTextColor(Color.parseColor("#383838"), Color.parseColor("#383838"))//
                .widthScale(0.85f)//
                .showAnim(new BounceTopEnter())//
                .dismissAnim(new ZoomOutExit())//
                .show();
        dialog.setOnBtnClickL(
                new OnBtnClickL() {
                    @Override
                    public void onBtnClick() {
                        downloadApk(appVersion.getVersionUrl(), style);
                        dialog.dismiss();

                    }
                },
                new OnBtnClickL() {
                    @Override
                    public void onBtnClick() {
                        dialog.dismiss();
                    }
                });
    }


    /**
     * 下载apk文件
     */
    private void downloadApk(String url, DownStyle style) {

        switch (style) {
            case DIALOG_SHOW:
                if (!isServiceWorked()) {
                    Intent serviceIntent = new Intent(this, DownloadApkServiceDialog.class);
                    serviceIntent.putExtra("downloadURL", url);
                    serviceIntent.putExtra("savePath", HDVersionConfig.DOWNLOADPATH);
                    serviceIntent.putExtra("mApkName", "HD_HELPER.apk");
                    this.startService(serviceIntent);
                }
                break;
            case NOTIFICATION_SHOW:
                if(!isServiceWorked()){
                    Intent serviceIntent = new Intent(this, DownloadApkServiceNotification.class);
                    serviceIntent.putExtra("downloadURL", url);
                    serviceIntent.putExtra("savePath", HDVersionConfig.DOWNLOADPATH);
                    serviceIntent.putExtra("mApkName", "HD_HELPER.apk");
                    this.startService(serviceIntent);
                }
                break;
        }


    }


    // 判断服务是否运行
    private boolean isServiceWorked() {
        ActivityManager myManager = (ActivityManager) this.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : myManager
                .getRunningServices(Integer.MAX_VALUE)) {

            if (HDVersionConfig.SERIVCE_DIALOG
                    .equals(service.service.getClassName())) {
                return true;
            }
            if(HDVersionConfig.SERIVCE_NOTIFICATION
                    .equals(service.service.getClassName())){
                return true;
            }

        }
        return false;
    }


    /**
     * 检测新版本
     */

    public void checkVersion(final DownStyle style){

        versionApi.getVersionInfo(HDVersionConfig.APP_KEY,
                HDVersionConfig.APP_SECRET,
                VersionUtil.getDeviceId(this),
                SystemTool.getAppVersionCode(this),
                HDVersionConfig.AppKind.MOBLILE_APP).subscribe(new Observer<VersionInfo>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                KJLoger.debug(e.getStackTrace().toString());
            }

            @Override
            public void onNext(VersionInfo versionInfo) {

                KJLoger.debug(versionInfo.toString());
                if(versionInfo.getStatus().equals(HDVersionConfig.STATUS.CODE_ERROR)){

                }else if(versionInfo.getStatus().equals(HDVersionConfig.STATUS.CODE_NEW_VERSION)){
                    downloadAPKDialog(versionInfo.getVersionInfo(),style);
                }else if(versionInfo.getStatus().equals(HDVersionConfig.STATUS.CODE_NO_UP_)){

                }

            }
        });
    }


    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onStop() {
        super.onStop();

    }
}
