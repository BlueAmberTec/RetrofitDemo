package com.hengda.smart.versioncheck;

import android.app.Dialog;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.hengda.smart.activity.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;


public class DownloadApkServiceDialog extends Service {

	private Timer timer;
	private String downloadURL;
	private String savePath;
	private String mApkName;
	private int progress=0;
	private final int DOWNLOAD = 1;
	private final int DOWNLOAD_FINISH = 2;
	private final int DOWNLOAD_ERROR = 3;
	private boolean cancelUpdate = false;
	//下载显示弹框
	Dialog dialog;
	//
	private TextView mSize;
	private TextView mSpeed;
	private DownLoadProgressbar mProgress;
	private String total;
	private int current;
	private String currentSize;
	
	private Handler mHandler = new Handler() {

		public void handleMessage(Message msg) {
			switch (msg.what) {
			// 正在下载
			case DOWNLOAD:
				// 设置进度条位置
				//弹框下载
				mProgress.setMaxValue(100);
				mProgress.setCurrentValue(progress);
				currentSize= String.valueOf(current / Math.pow(2, 20))
						.substring(0, 4);
				mSize.setText(currentSize + "MB/" + total + "MB");
				break;
			case DOWNLOAD_FINISH:
				// 停止计时
				if (timer != null) {
					timer.cancel();
					timer = null;
				}

				if(dialog.isShowing()){
					dialog.cancel();
				}

				// 安装文件
				installApk();
				stopSelf();
				break;
			case DOWNLOAD_ERROR:
				// 下载失败
				timer.cancel();
				if(dialog.isShowing()){
					dialog.cancel();
				}
				Toast.makeText(DownloadApkServiceDialog.this, "下载新版本失败，请检查网络状况", Toast.LENGTH_SHORT).show();
				stopSelf();
				break;
			default:
				break;
			}
		};
	};


	public void  initDownLoadDialog() {
		dialog = new Dialog(this, R.style.Dialog);
		LayoutInflater inflater = (LayoutInflater) this
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		// instantiate the dialog with the custom Theme
		View layout = inflater.inflate(R.layout.layout_dialog_download, null);
		mProgress = (DownLoadProgressbar) layout.findViewById(R.id.dp_game_progress);
		mSize = (TextView) layout.findViewById(R.id.tv_size);
		mSpeed = (TextView)layout.findViewById(R.id.tv_speed);
		mProgress.setMaxValue(100);
		mProgress.setCurrentValue(0);
		dialog.setContentView(layout);
		dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
		dialog.setCancelable(false);
		dialog.setCanceledOnTouchOutside(false);
	}

	@Override
	public void onCreate() {
		super.onCreate();
		initDownLoadDialog();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		if(intent!=null){
			downloadURL = intent.getStringExtra("downloadURL");
			savePath = intent.getStringExtra("savePath");
			mApkName = intent.getStringExtra("mApkName");
			// 启动新线程下载软件
			new downloadApkThread().start();
			dialog.show();
		}
		return super.onStartCommand(intent, flags, startId);
	}

	/**
	 * 下载文件线程
	 * 
	 */
	private class downloadApkThread extends Thread {
		@Override
		public void run() {
			try {
				URL url = new URL(downloadURL);
				// 创建连接
				HttpURLConnection conn = (HttpURLConnection) url
						.openConnection();
				conn.connect();
				// 获取文件大小
				int length = conn.getContentLength();
				total = String.valueOf(length / Math.pow(2, 20))
						.substring(0, 4);
				// 创建输入流
				InputStream is = conn.getInputStream();

				File file = new File(savePath);
				// 判断文件目录是否存在
				if (!file.exists()) {
					file.mkdir();
				}
				File apkFile = new File(savePath + mApkName);
				if (!apkFile.exists()) {
					apkFile.createNewFile();
				}
				FileOutputStream fos = new FileOutputStream(apkFile);
				int count = 0;
				// 启动计时器
				timer = new Timer();
				timer.schedule(new MyTimerTask(), 0, 500);
				// 缓存
				byte buf[] = new byte[1024];
				// 写入到文件中
				do {
					int numread = is.read(buf);
					count += numread;
					current=count;
					// 计算进度条位置
					progress = (int) (((float) count / length) * 100);
					// }
					if (numread <= 0) {
						// 下载完成
						mHandler.sendEmptyMessage(DOWNLOAD_FINISH);
						break;
					}
					// 写入文件
					fos.write(buf, 0, numread);
				} while (!cancelUpdate);// 点击取消就停止下载.
				fos.close();
				is.close();
				// }
			} catch (Exception e) {
				e.printStackTrace();
				mHandler.sendEmptyMessage(DOWNLOAD_ERROR);
			} 
		}
	};
	
	/**
	 * 安装APK文件
	 */
	private void installApk() {
		File apkFile = new File(savePath + mApkName);
		if (!apkFile.exists()) {
			return;
		}
		// 通过Intent安装APK文件
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setDataAndType(Uri.parse("file://" + apkFile.toString()),
				"application/vnd.android.package-archive");
		i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(i);
	}
	

	private class MyTimerTask extends TimerTask {

		@Override
		public void run() {
			// 更新进度
			mHandler.sendEmptyMessage(DOWNLOAD);
			Intent intent = new Intent("module.appupdate.progress");
			intent.putExtra("progress", progress);
			sendBroadcast(intent);
		}
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onDestroy() {
		if (timer != null) {
			timer.cancel();
			timer = null;
		}
		super.onDestroy();
	}
}
