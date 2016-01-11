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
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import cn.trinea.android.common.util.PreferencesUtils;

import com.alibaba.fastjson.JSONArray;
import com.example.demo.bean.ListviewItem;
import com.example.demo.common.Common;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;
import com.shizhefei.fragment.LazyFragment;

@SuppressLint("HandlerLeak")
public class MoreFragment extends LazyFragment
{
	private ProgressBar progressBar;
	@SuppressWarnings("unused")
	private TextView textView;
	@SuppressWarnings("unused")
	private int tabIndex;
	public static final String INTENT_INT_INDEX = "intent_int_index";
	private Context context;
	public static final String TITLE = "title";
	private static final String IV = "iv";
	public static final String CONTENT = "content";
	private ImageLoader imageLoader;
	public ListView listview;
	private static final String PATH = Common.IPAddress + "GetListviewItem";
	private List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	private MyAdapter adapter;
	private static final int A = 0;
	public static final int B = 2;

	private Handler handler = new Handler()
	{
		public void handleMessage(android.os.Message msg)
		{
			switch (msg.what)
			{
			case A:
				progressBar.setVisibility(View.VISIBLE);
				break;

			case B:
				progressBar.setVisibility(View.INVISIBLE);
				break;
			}
		}
	};

	@SuppressWarnings("deprecation")
	@Override
	public void onAttach(Activity activity)
	{
		this.context = activity;
		imageLoader = ImageLoader.getInstance();
		super.onAttach(activity);
	}

	private List<ListviewItem> items;

	class MyTask extends AsyncTask<String, Void, String>
	{
		@Override
		protected void onPreExecute()
		{
			super.onPreExecute();
			Message msg = Message.obtain();
			msg.what = A;
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
				msg.what = B;
				handler.sendMessage(msg);

				adapter = new MyAdapter(context, list);
				listview.setAdapter(adapter);
				adapter.notifyDataSetChanged();
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
		String url = Common.IPAddress + "GetListviewItem";
		List<NameValuePair> pairs = new ArrayList<NameValuePair>();

		try
		{
			HttpEntity entity = new UrlEncodedFormEntity(pairs, HTTP.UTF_8);
			post = new HttpPost(url);
			post.setEntity(entity);
			httpClient = new DefaultHttpClient();
			org.apache.http.HttpResponse response = httpClient.execute(post);

			data = EntityUtils.toString((response).getEntity());

			Log.e("hdj", "data : " + data);
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
		items = JSONArray.parseArray(result, ListviewItem.class);

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();

		for (ListviewItem item : items)
		{
			Log.e("hdj", "Id : " + item.getId());
			Log.e("hdj", "Icon_url : " + item.getIcon_url());
			Log.e("hdj", "App_name : " + item.getApp_name());
			Log.e("hdj", "App_introduces : " + item.getApp_introduces());
			map = new HashMap<String, Object>();
			map.put(TITLE, "标题" + item.getApp_name());
			map.put(CONTENT, "内容" + item.getApp_introduces());
			map.put(IV, item.getIcon_url());
			list.add(map);
			map = null;
		}
		return list;
	}

	@Override
	protected void onCreateViewLazy(Bundle savedInstanceState)
	{
		super.onCreateViewLazy(savedInstanceState);
		setContentView(R.layout.fragment_tabmain_item);
		new MyTask().execute(PATH);
		tabIndex = getArguments().getInt(INTENT_INT_INDEX);
		listview = (ListView) findViewById(R.id.listview);
		progressBar = (ProgressBar) findViewById(R.id.fragment_mainTab_item_progressBar);

		// tabName = getArguments().getString(INTENT_STRING_TABNAME);
		// position = getArguments().getInt(INTENT_INT_POSITION);
		// setContentView(R.layout.fragment_tabmain_item);
	}

	@SuppressLint("InflateParams")
	public class MyAdapter extends BaseAdapter
	{
		DisplayImageOptions options;
		private List<Map<String, Object>> list;
		private LayoutInflater mInflater;

		public MyAdapter(Context context, List<Map<String, Object>> list)
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
			this.mInflater = LayoutInflater.from(context);
			this.list = list;
		}

		@Override
		public int getCount()
		{
			// return mData.size();
			return list.size();
		}

		@Override
		public Object getItem(int position)
		{
			return list.get(position);
		}

		@Override
		public long getItemId(int position)
		{
			return 0;
		}

		@Override
		public View getView(final int position, View convertView, ViewGroup parent)
		{
			ViewHolder holder = null;
			if (convertView == null)
			{
				convertView = mInflater.inflate(R.layout.item, null);

				holder = new ViewHolder();
				holder.iv = (ImageView) convertView.findViewById(R.id.iv);
				holder.tv_title = (TextView) convertView.findViewById(R.id.tv_title);
				holder.tv_content = (TextView) convertView.findViewById(R.id.tv_content);
				holder.item = (LinearLayout) convertView.findViewById(R.id.item);

				convertView.setTag(holder);
			} else
			{
				holder = (ViewHolder) convertView.getTag();
			}

			//holder.iv.setImageResource((Integer) mData.get(position).get(IV));
			holder.iv.setImageResource(R.drawable.ic_launcher);
			imageLoader.displayImage((String) list.get(position).get(IV), holder.iv, options);
			// holder.iv.setImageBitmap((Bitmap) mData.get(position).get(IV));
			holder.tv_title.setText((CharSequence) list.get(position).get(TITLE));
			holder.tv_content.setText((CharSequence) list.get(position).get(CONTENT));

			final int p = position;
			holder.item.setOnClickListener(new OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					Toast.makeText(getApplicationContext(), "位置" + p, Toast.LENGTH_SHORT).show();
					Intent intent = new Intent();
					intent.setClass(context, MyViewPager.class);
					Bundle bundle = new Bundle();
					bundle.putInt("position", position);
					PreferencesUtils.putInt(getApplicationContext(), "position", position);
					MoreFragment.this.startActivity(intent);
				}
			});
			return convertView;
		}
	}

	public static class ViewHolder
	{
		public LinearLayout item;
		public ImageView iv;
		public TextView tv_title;
		public TextView tv_content;
	}

	@Override
	public void onDestroyViewLazy()
	{
		super.onDestroyViewLazy();
		handler.removeMessages(1);
	}
}