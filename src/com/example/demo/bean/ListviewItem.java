package com.example.demo.bean;

import com.lidroid.xutils.db.annotation.Table;

@Table(name = "listview_item")
public class ListviewItem
{
	private int id;
	private String icon_url;
	private String app_name;
	private String app_introduces;

	public ListviewItem()
	{
		super();
	}

	public ListviewItem(int id, String icon_url, String app_name, String app_introduces)
	{
		super();
		this.id = id;
		this.icon_url = icon_url;
		this.app_name = app_name;
		this.app_introduces = app_introduces;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getIcon_url()
	{
		return icon_url;
	}

	public void setIcon_url(String iconUrl)
	{
		icon_url = iconUrl;
	}

	public String getApp_name()
	{
		return app_name;
	}

	public void setApp_name(String appName)
	{
		app_name = appName;
	}

	public String getApp_introduces()
	{
		return app_introduces;
	}

	public void setApp_introduces(String appIntroduces)
	{
		app_introduces = appIntroduces;
	}

	@Override
	public String toString()
	{
		return "Listview_item_bean [app_introduces=" + app_introduces + ", app_name=" + app_name + ", icon_url=" + icon_url + ", id=" + id + "]";
	}
}