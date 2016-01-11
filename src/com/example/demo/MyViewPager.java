package com.example.demo;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import cn.trinea.android.common.util.PreferencesUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.example.demo.bean.ItemInfo;
import com.example.demo.common.Common;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.exception.DbException;
import com.lidroid.xutils.util.LogUtils;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;

@SuppressLint({ "HandlerLeak", "ClickableViewAccessibility" })
@SuppressWarnings("deprecation")
public class MyViewPager extends FragmentActivity
{
	private static int TOTAL_COUNT = 5;
	private ProgressBar progressBar;
	public static ImageLoader imageLoader;
	private RelativeLayout viewPagerContainer;
	private ViewPager viewPager;
	private static final String PATH = Common.IPAddress + "GetItemInfo";
	DbUtils db;
	public static final int OK = 0;
	public static final int UPDATA_UI = 1;
	public static final int SHOW_PROGRESS_BAR = 2;
	protected static final int UPDATA_UI_BY_DB = 3;
	private TextView tv_app_name2, tv_app_name, tv_developer_info, tv_app_description, tv_update_info1, tv_update_info2, tv_update_info3, tv_update_info4, tv_update_info5, tv_update_time, tv_app_version;
	private ImageView iv_icon_url;
	LinearLayout pager;
	int id;
	Handler handler = new Handler()
	{
		@SuppressWarnings({ "unchecked" })
		public void handleMessage(android.os.Message msg)
		{
			switch (msg.what)
			{
			case SHOW_PROGRESS_BAR:
				progressBar.setVisibility(View.VISIBLE);
				pager.setVisibility(View.INVISIBLE);
				break;
			case UPDATA_UI:
				progressBar.setVisibility(View.GONE);
				pager.setVisibility(View.VISIBLE);
				List<Map<String, Object>> list3 = new ArrayList<Map<String, Object>>();
				list3 = (List<Map<String, Object>>) msg.obj;

				for (int i = 0; i < list3.size(); i++)
				{
					Map<String, Object> map = list3.get(i);
					// iv_icon_url.setImageResource(R.drawable.ic_launcher);
					imageLoader.displayImage((String) map.get(ICON_URL), iv_icon_url, options);
					tv_app_name.setText((String) map.get(APP_NAME));
					tv_app_name2.setText((String) map.get(APP_NAME));
					tv_developer_info.setText((String) map.get(APP_SIZE) + "|" + (String) map.get(DEVELOPER_INFO));
					tv_app_description.setText((String) map.get(APP_DESCRIPTION));
					tv_update_info1.setText((String) map.get(UPDATE_INFO1));
					tv_update_info2.setText((String) map.get(UPDATE_INFO2));
					tv_update_info3.setText((String) map.get(UPDATE_INFO3));
					tv_update_info4.setText((String) map.get(UPDATE_INFO4));
					tv_update_info5.setText((String) map.get(UPDATE_INFO5));
					tv_app_version.setText((String) map.get(APP_VERSION));
					tv_update_time.setText((String) map.get(UPDATE_TIME));
					
					String[] urls22 = { (String) map.get(APP_IMAGE1), (String) map.get(APP_IMAGE2), (String) map.get(APP_IMAGE3), (String) map.get(APP_IMAGE4), (String) map.get(APP_IMAGE5) };
					viewPager.setAdapter(new MyPagerAdapter(MyViewPager.this, urls22));
				}
				break;
			case UPDATA_UI_BY_DB:
				List<ItemInfo> items1 =(List<ItemInfo>) msg.obj;
				for (ItemInfo itemInfo : items1) {
					imageLoader.displayImage(itemInfo.getIcon_url(), iv_icon_url, options);
					tv_app_name.setText(itemInfo.getApp_name());
					tv_app_name2.setText(itemInfo.getApp_name());
					tv_developer_info.setText(itemInfo.getApp_size() + "|" +itemInfo.getDeveloper_info());
					tv_app_description.setText(itemInfo.getApp_description());
					tv_update_info1.setText(itemInfo.getUpdate_info1());
					tv_update_info2.setText(itemInfo.getUpdate_info2());
					tv_update_info3.setText(itemInfo.getUpdate_info3());
					tv_update_info4.setText(itemInfo.getUpdate_info4());
					tv_update_info5.setText(itemInfo.getUpdate_info5());
					tv_app_version.setText(itemInfo.getApp_version());
					tv_update_time.setText(itemInfo.getUpdate_time());
					String[] urls22 = { itemInfo.getApp_image1(),itemInfo.getApp_image2(), itemInfo.getApp_image3(), itemInfo.getApp_image4(), itemInfo.getApp_image5()};
					viewPager.setAdapter(new MyPagerAdapter(MyViewPager.this, urls22));
				}
				break;
			}
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_viewpager);
		db = DbUtils.create(MyViewPager.this, Environment.getExternalStorageDirectory() + "", "test.db");
		imageLoader = ImageLoader.getInstance();
		initView();

		id = PreferencesUtils.getInt(getApplicationContext(), "position") + 1;

		boolean isGetInfo = PreferencesUtils.getBoolean(getApplicationContext(), "isGetInfo", false);
		//new MyTask().execute(PATH);
		
		List<ItemInfo> items;
		try {
			items = db.findAll(ItemInfo.class);
			for (ItemInfo itemInfo : items) {
				itemInfo.toString();
				LogUtils.e(itemInfo.toString());
			}
			Message msg = Message.obtain();
			msg.obj = items;
			msg.what = UPDATA_UI_BY_DB;
			handler.sendMessage(msg);
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		/*
		if (!isGetInfo)
		{
			try {
				List<ItemInfo> items = db.findAll(ItemInfo.class);
				Message msg = Message.obtain();
				msg.obj = items;
				msg.what = UPDATA_UI;
				handler.sendMessage(msg);
			} catch (DbException e) {
				e.printStackTrace();
			}
		} else
		{

		}
		*/

		//String[] urls2 = { "http://192.168.0.112/icon_url/5.png", "http://192.168.0.112/icon_url/5.png", "http://192.168.0.112/icon_url/5.png", "http://192.168.0.112/icon_url/5.png", "http://192.168.0.112/icon_url/5.png", };
		viewPager.setOffscreenPageLimit(TOTAL_COUNT);
		viewPager.setPageMargin(20);
		// viewPager.setAdapter(new MyPagerAdapter(this, urls2));
		MyOnPageChangeListener myOnPageChangeListener = new MyOnPageChangeListener();
		viewPager.setOnPageChangeListener(myOnPageChangeListener);
		viewPagerContainer.setOnTouchListener(new OnTouchListener()
		{
			@Override
			public boolean onTouch(View v, MotionEvent event)
			{
				return viewPager.dispatchTouchEvent(event);
			}
		});
	}

	private void initView()
	{
		pager = (LinearLayout) findViewById(R.id.pager);
		progressBar = (ProgressBar) findViewById(R.id.progressBar);
		viewPager = (ViewPager) findViewById(R.id.view_pager);
		viewPagerContainer = (RelativeLayout) findViewById(R.id.pager_layout);
		tv_app_name2 = (TextView) findViewById(R.id.tv_app_name2);
		tv_app_name = (TextView) findViewById(R.id.tv_app_name);
		tv_developer_info = (TextView) findViewById(R.id.tv_developer_info);
		tv_app_description = (TextView) findViewById(R.id.tv_app_description);
		tv_update_info1 = (TextView) findViewById(R.id.tv_update_info1);
		tv_update_info2 = (TextView) findViewById(R.id.tv_update_info2);
		tv_update_info3 = (TextView) findViewById(R.id.tv_update_info3);
		tv_update_info4 = (TextView) findViewById(R.id.tv_update_info4);
		tv_update_info5 = (TextView) findViewById(R.id.tv_update_info5);
		tv_update_time = (TextView) findViewById(R.id.tv_update_time);
		tv_app_version = (TextView) findViewById(R.id.tv_app_version);
		iv_icon_url = (ImageView) findViewById(R.id.iv_icon_url);
	}

	private List<ItemInfo> items;
	private List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

	class MyTask extends AsyncTask<String, Void, String>
	{
		@Override
		protected void onPreExecute()
		{
			super.onPreExecute();
			Message msg = Message.obtain();
			msg.what = SHOW_PROGRESS_BAR;
			handler.sendMessage(msg);
		}

		@Override
		protected String doInBackground(String... params)
		{
			return RequestData();
		}

		@Override
		protected void onPostExecute(String result)
		{
			super.onPostExecute(result);
			if (result != null)
			{
				// 如果获取的result数据不为空，那么对其进行JSON解析。并显示在手机屏幕上。
				list = JSONAnalysis(result);
			
				Message msg = Message.obtain();
				msg.what = UPDATA_UI;
				msg.obj = list;
				handler.sendMessage(msg);
				PreferencesUtils.putBoolean(getApplicationContext(), "isGetInfo", true);
			} else if (result == null)
			{
				Toast.makeText(getApplicationContext(), "请求数据失败...", Toast.LENGTH_LONG).show();
			}
		}
	}

	public String RequestData()
	{
		String data = "";
		HttpClient httpClient = null;
		HttpPost post = null;
		String url = PATH;
		List<NameValuePair> pairs = new ArrayList<NameValuePair>();
		NameValuePair pair = new BasicNameValuePair("id", id + "");
		pairs.add(pair);

		try
		{
			HttpEntity entity = new UrlEncodedFormEntity(pairs, HTTP.UTF_8);
			post = new HttpPost(url);
			post.setEntity(entity);
			httpClient = new DefaultHttpClient();
			org.apache.http.HttpResponse response = httpClient.execute(post);

			data = EntityUtils.toString((response).getEntity());

			Log.e("hdj", "data : " + data);
			
			List<ItemInfo> parseArray = JSON.parseArray(data, ItemInfo.class);
			for (ItemInfo itemInfo : parseArray)
            {
				Log.e("hdj", "...................."+itemInfo.toString());
            }
			try
            {
	            db.saveAll(parseArray);
            } catch (DbException e)
            {
	            e.printStackTrace();
            }
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
		return data;
	}

	public List<Map<String, Object>> JSONAnalysis(String result)
	{
		items = JSONArray.parseArray(result, ItemInfo.class);

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();

		for (ItemInfo item : items)
		{
			Log.e("hdj", "Id : " + item.getId());
			Log.e("hdj", "Icon_url : " + item.getIcon_url());
			Log.e("hdj", "App_name : " + item.getApp_name());
			Log.e("hdj", "getApp_description : " + item.getApp_description());
			map = new HashMap<String, Object>();
			map.put(APP_ID, item.getApp_id());
			map.put(ID, item.getId());
			map.put(ICON_URL, item.getIcon_url());
			map.put(APP_NAME, item.getApp_name());
			map.put(APP_SIZE, item.getApp_size());
			map.put(DEVELOPER_INFO, item.getDeveloper_info());
			map.put(APP_IMAGE1, item.getApp_image1());
			map.put(APP_IMAGE2, item.getApp_image2());
			map.put(APP_IMAGE3, item.getApp_image3());
			map.put(APP_IMAGE4, item.getApp_image4());
			map.put(APP_IMAGE5, item.getApp_image5());
			map.put(APP_DESCRIPTION, item.getApp_description());
			map.put(UPDATE_INFO1, item.getUpdate_info1());
			map.put(UPDATE_INFO2, item.getUpdate_info2());
			map.put(UPDATE_INFO3, item.getUpdate_info3());
			map.put(UPDATE_INFO4, item.getUpdate_info4());
			map.put(UPDATE_INFO5, item.getUpdate_info5());
			map.put(UPDATE_TIME, item.getUpdate_time());
			map.put(APP_VERSION, item.getApp_version());
			map.put(PACKAGE_NAME, item.getPackage_name());
			list.add(map);
			map = null;
		}
		return list;
	}

	DisplayImageOptions options;

	class MyPagerAdapter extends PagerAdapter
	{
		// DisplayImageOptions options;
		@SuppressWarnings("unused")
		private Context mContext;
		// private LayoutInflater mInflater;
		String[] urls = {};

		public MyPagerAdapter(Context context, String[] urls1)
		{
			options = new DisplayImageOptions.Builder().resetViewBeforeLoading(false) // default 设置图片在加载前是否重置、复位
			                // .delayBeforeLoading(1000) // 下载前的延迟时间
			                .cacheInMemory(false) // default 设置下载的图片是否缓存在内存中
			                .cacheOnDisk(true) // default 设置下载的图片是否缓存在SD卡中
			                .considerExifParams(false) // default
			                .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2) // default 设置图片以如何的编码方式显示
			                .bitmapConfig(Bitmap.Config.ARGB_8888) // default 设置图片的解码类型
			                .displayer(new SimpleBitmapDisplayer()) // default 还可以设置圆角图片new RoundedBitmapDisplayer(20)
			                .handler(new Handler()) // default
			                .build();

			this.mContext = context;
			this.urls = urls1;
			// this.mInflater = LayoutInflater.from(mContext);
		}

		@Override
		public int getCount()
		{
			return urls.length;
		}

		@Override
		public boolean isViewFromObject(View view, Object object)
		{
			return (view == object);
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position)
		{
			ImageView imageView = new ImageView(MyViewPager.this);
			// imageView.setImageResource(R.drawable.app_image);
			imageLoader.displayImage(urls[position], imageView, options);
			((ViewPager) container).addView(imageView, position);
			return imageView;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object)
		{
			((ViewPager) container).removeView((ImageView) object);
		}
	}

	public class MyOnPageChangeListener implements OnPageChangeListener
	{
		@Override
		public void onPageSelected(int position)
		{
			// ToastUtils.show(getApplicationContext(), "test"+position);
		}

		@Override
		public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
		{
			if (viewPagerContainer != null)
			{
				viewPagerContainer.invalidate();
			}
		}

		@Override
		public void onPageScrollStateChanged(int arg0)
		{
		}
	}

	private static final String APP_ID = "app_id";
	private static final String ID = "id";
	private static final String ICON_URL = "icon_url";
	private static final String APP_NAME = "app_name";
	private static final String APP_SIZE = "app_size";
	private static final String DEVELOPER_INFO = "developer_info";
	private static final String APP_IMAGE1 = "app_image1";
	private static final String APP_IMAGE2 = "app_image2'";
	private static final String APP_IMAGE3 = "app_image3";
	private static final String APP_IMAGE4 = "app_image4";
	private static final String APP_IMAGE5 = "app_image5";
	private static final String APP_DESCRIPTION = "app_description";
	private static final String UPDATE_INFO1 = "update_info1";
	private static final String UPDATE_INFO2 = "update_info2";
	private static final String UPDATE_INFO3 = "update_info3";
	private static final String UPDATE_INFO4 = "update_info4";
	private static final String UPDATE_INFO5 = "update_info5";
	private static final String UPDATE_TIME = "update_time";
	private static final String APP_VERSION = "app_version";
	private static final String PACKAGE_NAME = "package_name";
}