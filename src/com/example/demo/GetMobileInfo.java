package com.example.demo;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.view.WindowManager;
import cn.trinea.android.common.util.RandomUtils;

import com.example.demo.bean.MobileInfo;

public class GetMobileInfo
{
	//static int id = 432;
	int screen_width = 480;
	int screen_height = 960;
	String imei = "54554632113212";
	String software_version = "3.0";
	String channel_id = "18";
	String ip = "192.168.0.1";
	String imsi = "4654616579965";
	String android_id = "sdfasdf554sgsad";
	String mac_address = "10:sd:pa:df:q9:q2";
	String mobile_manufacturer = "xiaomi";
	String mobile_model = "note";
	String operating_system_version = "4.4.2";
	String iswifi = "true";

	public static MobileInfo getMobileInfo(Context context)
	{
		MobileInfo info = new MobileInfo();
		info.setId(RandomUtils.getRandom(0, Integer.MAX_VALUE));
		info.setImei(getImei(context));
		info.setScreen_width(getScreen_width(context));
		info.setScreen_height(getScreen_height(context));
		info.setSoftware_version(getSoftware_version(context));
		info.setIp(getIP(context));
		info.setImsi(getImsi(context));
		info.setIswifi(isWifi(context) + "");
		info.setAndroid_id(getAndroid_id(context));
		info.setMac_address(getMac_address(context));
		info.setMobile_manufacturer(getMobile_manufacturer(context));
		info.setMobile_model(getMobile_model(context));
		info.setOperating_system_version(getOperating_system_version(context));

		info.setChannel_id("10");
		info.setLocation_latitude("112.03");
		info.setLocation_longitude("12.06");
		return info;
	}

	public static String getOperating_system_version(Context context)
	{
		return android.os.Build.VERSION.RELEASE;
	}

	public static String getMobile_model(Context context)
	{
		return android.os.Build.MODEL;
	}

	public static String getMobile_manufacturer(Context context)
	{
		return android.os.Build.MANUFACTURER;
	}

	public static String getMac_address(Context context)
	{
		String mac = "";
		WifiManager wifiMng = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
		WifiInfo wifiInfor = wifiMng.getConnectionInfo();
		mac = wifiInfor.getMacAddress();
		return mac;
	}

	public static String getAndroid_id(Context context)
	{
		return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
	}

	public static String getImei(Context context)
	{
		TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		return tm.getDeviceId();
	}

	@SuppressWarnings("deprecation")
	public static int getScreen_width(Context context)
	{
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		return wm.getDefaultDisplay().getWidth();
	}

	@SuppressWarnings("deprecation")
	public static int getScreen_height(Context context)
	{
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		return wm.getDefaultDisplay().getHeight();
	}

	public static String getSoftware_version(Context context)
	{
		String versionName = "";
		try
		{
			PackageManager manager = context.getPackageManager();
			PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
			versionName = info.versionName;
			return versionName;
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return versionName;
	}

	public static String getIP(Context context)
	{
		String ip = "";

		WifiManager wifi_service = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
		WifiInfo wifiInfo = wifi_service.getConnectionInfo();

		if (wifiInfo.getIpAddress() != 0)
		{
			ip = intToIp(wifiInfo.getIpAddress());
		}
		return ip;
	}

	public static String getImsi(Context context)
	{
		String imsi = "";
		TelephonyManager mTelephonyMgr = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		imsi = mTelephonyMgr.getSubscriberId();
		return imsi;
	}

	public static boolean isWifi(Context context)
	{
		ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo wifiNetworkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		if (wifiNetworkInfo.isConnected())
		{
			return true;
		}

		return false;
	}

	private static String intToIp(int i)
	{
		return (i & 0xFF) + "." +

		((i >> 8) & 0xFF) + "." +

		((i >> 16) & 0xFF) + "." +

		(i >> 24 & 0xFF);
	}
}