package com.example.demo.application;

import android.app.Activity;

@SuppressWarnings("deprecation")
public class ViewPager1 extends Activity
{
	/*
	public static ImageLoader imageLoader;
	Gallery gallery;
	ImageView imageView;

	public static final int OK = 0;
	public static final int NO1 = 1;
	protected static final int NO2 = 2;
	Handler handler = new Handler()
	{
		public void handleMessage(android.os.Message msg)
		{
			switch (msg.what)
			{
			case NO1:
				break;

			case NO2:
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
		imageView = (ImageView) this.findViewById(R.id.iv);

		imageLoader = ImageLoader.getInstance();
		gallery = (Gallery) this.findViewById(R.id.gallery);
		gallery.setAdapter(new ImageAdapter(this));
		gallery.setOnItemClickListener(new OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id)
			{
				Toast.makeText(getApplicationContext(), "position = " + position, Toast.LENGTH_LONG).show();
				// getInfo8(ViewPager.this);
			}
		});

		findViewById(R.id.btn_back).setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				// startActivity(new Intent(ViewPager.this, TabActivity.class));
			}
		});
	}

	protected void getInfo8(final Context context)
	{
		HttpUtils http = new HttpUtils();
		RequestParams params = new RequestParams();

		String url = "http://192.168.0.114/apk/test.apk";
		http.download(HttpMethod.GET, url, "/storage/emulated/0/Download/test.apk", params, new RequestCallBack<File>()
		{
			long t1 = 0;
			long t2 = 0;

			@Override
			public void onStart()
			{
				t1 = System.currentTimeMillis();
				Log.e("hdj", "onStart");
			}

			@Override
			public void onLoading(long total, long current, boolean isUploading)
			{
				if (isUploading)
				{
					Log.e("hdj", "upload: " + current + "/" + total);
				} else
				{
					Log.e("hdj", "reply: " + current + "/" + total);
				}
			}

			@Override
			public void onSuccess(ResponseInfo<File> responseInfo)
			{
				t2 = System.currentTimeMillis();

				long t = t2 - t1;
				Log.e("hdj", "onSuccess reply: " + responseInfo.result);
				Log.e("hdj", "time : " + t);

				// PackageUtils.install(context, responseInfo.result.toString());
			}

			@Override
			public void onFailure(HttpException error, String msg)
			{
				Log.e("hdj", "onFailure : " + msg);
			}
		});
	}

	@SuppressLint("InflateParams")
	public class ImageAdapter extends BaseAdapter
	{
		DisplayImageOptions options;

		public final Integer[] urls1 = { R.drawable.a1, R.drawable.a2, R.drawable.a3, R.drawable.a4, R.drawable.a5 };

		public final String[] urls = { "http://192.168.0.114/icon_url/1.png", "http://192.168.0.114/icon_url/2.png", "http://192.168.0.114/icon_url/3.png", "http://192.168.0.114/icon_url/4.png", "http://192.168.0.114/icon_url/5.png", };

		private Context mContext;
		private LayoutInflater mInflater;

		public ImageAdapter(Context context)
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
			this.mInflater = LayoutInflater.from(mContext);
		}

		@Override
		public int getCount()
		{
			return urls.length;
		}

		@Override
		public Object getItem(int position)
		{
			return urls[position];
		}

		@Override
		public long getItemId(int position)
		{
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent)
		{
			ViewHolder holder = null;
			if (convertView == null)
			{
				convertView = mInflater.inflate(R.layout.item_viewpager, null);

				holder = new ViewHolder();
				holder.iv = (ImageView) convertView.findViewById(R.id.iv);
				convertView.setTag(holder);
			} else
			{
				holder = (ViewHolder) convertView.getTag();
			}
			// holder.iv.setImageResource(urls[position]);

			// imageLoader.DisplayImage(urls[position].toString(), holder.iv);
			imageLoader.displayImage(urls[position].toString(), holder.iv, options);
			return convertView;
		}
	}

	public static class ViewHolder
	{
		public ImageView iv;
	}
	*/
}