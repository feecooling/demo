package com.example.demo.application;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import android.app.Application;
import android.content.Context;
import android.os.Environment;
import cn.trinea.android.common.util.PreferencesUtils;

import com.example.demo.GetMobileInfo;
import com.example.demo.bean.ItemInfo;
import com.example.demo.bean.ListviewItem;
import com.example.demo.bean.MobileInfo;
import com.example.demo.common.Common;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.exception.DbException;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.utils.StorageUtils;

public class MyApplication extends Application
{
	public static MyApplication mInstance;
	DbUtils db;
    public MyApplication() {
    }

    public static MyApplication getInstance() {
    	return mInstance;
    }
    
    @Override
    public void onCreate() {
    	super.onCreate();
    	mInstance = this;
    	init(getApplicationContext());
	}
    
    private void init(Context context) {
		initImageLoader(context);
		initDB(context);
		//initData(context);
		boolean initMobileInfo = PreferencesUtils.getBoolean(context, Common.initMobileInfo, false);
		if (!initMobileInfo)
        {
			initMobileInfo(context);
        }
	} 

	private void initMobileInfo(final Context context)
	{
		new Thread()
		{
			public void run()
			{
				MobileInfo info = GetMobileInfo.getMobileInfo(context);
				try
                {
	                PreferencesUtils.putBoolean(context, Common.initMobileInfo, true);
	                db.save(info);
                } catch (DbException e1) 
                {
	                e1.printStackTrace();
                }
				HttpClient httpClient = null;
				HttpPost post = null;
				String url = Common.IPAddress + "SetMobileInfo";
				List<NameValuePair> pairs = new ArrayList<NameValuePair>();
				NameValuePair pair1 = new BasicNameValuePair("id", info.getId() + "");
				NameValuePair pair2 = new BasicNameValuePair("screen_width", info.getScreen_width() + "");
				NameValuePair pair3 = new BasicNameValuePair("screen_height", info.getScreen_height() + "");
				NameValuePair pair4 = new BasicNameValuePair("imei", info.getImei());
				NameValuePair pair5 = new BasicNameValuePair("software_version", info.getSoftware_version());
				NameValuePair pair6 = new BasicNameValuePair("channel_id", info.getChannel_id() + "");
				NameValuePair pair7 = new BasicNameValuePair("ip", info.getIp());
				NameValuePair pair8 = new BasicNameValuePair("imsi", info.getImsi());
				NameValuePair pair9 = new BasicNameValuePair("android_id", info.getAndroid_id());
				NameValuePair pair10 = new BasicNameValuePair("mac_address", info.getMac_address());
				NameValuePair pair11 = new BasicNameValuePair("mobile_manufacturer", info.getMobile_manufacturer());
				NameValuePair pair12 = new BasicNameValuePair("mobile_model", info.getMobile_model());
				NameValuePair pair13 = new BasicNameValuePair("operating_system_version", info.getOperating_system_version());
				NameValuePair pair14 = new BasicNameValuePair("location_latitude", info.getLocation_latitude());
				NameValuePair pair15 = new BasicNameValuePair("location_longitude", info.getLocation_longitude());
				NameValuePair pair16 = new BasicNameValuePair("iswifi", info.getIswifi() + "");
				pairs.add(pair1);
				pairs.add(pair2);
				pairs.add(pair3);
				pairs.add(pair4);
				pairs.add(pair5);
				pairs.add(pair6);
				pairs.add(pair7);
				pairs.add(pair8);
				pairs.add(pair9);
				pairs.add(pair10);
				pairs.add(pair11);
				pairs.add(pair12);
				pairs.add(pair13);
				pairs.add(pair14);
				pairs.add(pair15);
				pairs.add(pair16);

				try
				{
					HttpEntity entity = new UrlEncodedFormEntity(pairs, HTTP.UTF_8);
					post = new HttpPost(url);
					post.setEntity(entity);
					httpClient = new DefaultHttpClient();
					httpClient.execute(post);
				} catch (UnsupportedEncodingException e)
				{
					e.printStackTrace();
				} catch (ClientProtocolException e)
				{
					e.printStackTrace();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			};
		}.start();
	}

	private void initDB(Context context)
    {
		boolean isCreateDB = PreferencesUtils.getBoolean(context, Common.isCreateDB, false);
		if (!isCreateDB)
        {
			 db = DbUtils.create(context, Environment.getExternalStorageDirectory() + "", "test.db");
			try
	        {
		        db.createTableIfNotExist(ItemInfo.class);
		        db.createTableIfNotExist(ListviewItem.class);
		        db.createTableIfNotExist(MobileInfo.class);
		        PreferencesUtils.putBoolean(context, Common.isCreateDB, true);
	        } catch (DbException e)
	        {
		        e.printStackTrace();
		        PreferencesUtils.putBoolean(context, Common.isCreateDB, false);
	        }
        }
		else 
		{
			  PreferencesUtils.putBoolean(context, Common.isCreateDB, true);
		}
    }

	/** 初始化ImageLoader */
	@SuppressWarnings("deprecation")
    public static void initImageLoader(Context context) {
		File cacheDir = StorageUtils.getOwnCacheDirectory(MyApplication.getInstance(), "imageloader/Cache");  
		ImageLoaderConfiguration config = new ImageLoaderConfiguration  
					    .Builder(context)  
					    .memoryCacheExtraOptions(480, 800) // max width, max height，即保存的每个缓存文件的最大长宽  
					    .discCacheExtraOptions(480, 800, null) // Can slow ImageLoader, use it carefully (Better don't use it)/设置缓存的详细信息，最好不要设置这个  
					    .threadPoolSize(5)//线程池内加载的数量  
					    .threadPriority(Thread.MAX_PRIORITY )  
					    .denyCacheImageMultipleSizesInMemory()  
					    .memoryCache(new UsingFreqLimitedMemoryCache(16 * 1024 * 1024)) // You can pass your own memory cache implementation/你可以通过自己的内存缓存实现  
					    .memoryCacheSize(20 * 1024 * 1024)    
					    .discCacheSize(50 * 1024 * 1024)
					    .discCacheFileNameGenerator(new Md5FileNameGenerator())//将保存的时候的URI名称用MD5 加密  
					    .tasksProcessingOrder(QueueProcessingType.LIFO)  
					    .discCacheFileCount(10000) //缓存的文件数量  
					    .discCache(new UnlimitedDiscCache(cacheDir))//自定义缓存路径  
					    .defaultDisplayImageOptions(DisplayImageOptions.createSimple())  
					    .imageDownloader(new BaseImageDownloader(context,10 * 1000, 60 * 1000)) // connectTimeout (5 s), readTimeout (30 s)超时时间  
					    .writeDebugLogs() // Remove for release app  
					    .build();//开始构建  
					    // Initialize ImageLoader with configuration.  
						ImageLoader.getInstance().init(config);// 全局初始化此配置
	}
}