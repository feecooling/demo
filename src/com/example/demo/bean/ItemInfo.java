package com.example.demo.bean;

import com.lidroid.xutils.db.annotation.Table;

@Table(name = "item_info")
public class ItemInfo
{
	private int app_id;
	private int id;
	private String icon_url;
	private String app_name;
	private String app_size;
	private String developer_info;
	private String app_image1;
	private String app_image2;
	private String app_image3;
	private String app_image4;
	private String app_image5;
	private String app_description;
	private String update_info1;
	private String update_info2;
	private String update_info3;
	private String update_info4;
	private String update_info5;
	private String update_time;
	private String app_version;
	private String package_name;

	public ItemInfo()
	{
	}

	public ItemInfo(int app_id, int id, String icon_url, String app_name, String app_size, String developer_info, String app_image1, String app_image2, String app_image3, String app_image4, String app_image5, String app_description, String update_info1, String update_info2, String update_info3,
	                String update_info4, String update_info5, String update_time, String app_version, String package_name)
	{
		super();
		this.app_id = app_id;
		this.id = id;
		this.icon_url = icon_url;
		this.app_name = app_name;
		this.app_size = app_size;
		this.developer_info = developer_info;
		this.app_image1 = app_image1;
		this.app_image2 = app_image2;
		this.app_image3 = app_image3;
		this.app_image4 = app_image4;
		this.app_image5 = app_image5;
		this.app_description = app_description;
		this.update_info1 = update_info1;
		this.update_info2 = update_info2;
		this.update_info3 = update_info3;
		this.update_info4 = update_info4;
		this.update_info5 = update_info5;
		this.update_time = update_time;
		this.app_version = app_version;
		this.package_name = package_name;
	}

	public int getApp_id()
	{
		return app_id;
	}

	public void setApp_id(int app_id)
	{
		this.app_id = app_id;
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

	public void setIcon_url(String icon_url)
	{
		this.icon_url = icon_url;
	}

	public String getApp_name()
	{
		return app_name;
	}

	public void setApp_name(String app_name)
	{
		this.app_name = app_name;
	}

	public String getApp_size()
	{
		return app_size;
	}

	public void setApp_size(String app_size)
	{
		this.app_size = app_size;
	}

	public String getDeveloper_info()
	{
		return developer_info;
	}

	public void setDeveloper_info(String developer_info)
	{
		this.developer_info = developer_info;
	}

	public String getApp_image1()
	{
		return app_image1;
	}

	public void setApp_image1(String app_image1)
	{
		this.app_image1 = app_image1;
	}

	public String getApp_image2()
	{
		return app_image2;
	}

	public void setApp_image2(String app_image2)
	{
		this.app_image2 = app_image2;
	}

	public String getApp_image3()
	{
		return app_image3;
	}

	public void setApp_image3(String app_image3)
	{
		this.app_image3 = app_image3;
	}

	public String getApp_image4()
	{
		return app_image4;
	}

	public void setApp_image4(String app_image4)
	{
		this.app_image4 = app_image4;
	}

	public String getApp_image5()
	{
		return app_image5;
	}

	public void setApp_image5(String app_image5)
	{
		this.app_image5 = app_image5;
	}

	public String getApp_description()
	{
		return app_description;
	}

	public void setApp_description(String app_description)
	{
		this.app_description = app_description;
	}

	public String getUpdate_info1()
	{
		return update_info1;
	}

	public void setUpdate_info1(String update_info1)
	{
		this.update_info1 = update_info1;
	}

	public String getUpdate_info2()
	{
		return update_info2;
	}

	public void setUpdate_info2(String update_info2)
	{
		this.update_info2 = update_info2;
	}

	public String getUpdate_info3()
	{
		return update_info3;
	}

	public void setUpdate_info3(String update_info3)
	{
		this.update_info3 = update_info3;
	}

	public String getUpdate_info4()
	{
		return update_info4;
	}

	public void setUpdate_info4(String update_info4)
	{
		this.update_info4 = update_info4;
	}

	public String getUpdate_info5()
	{
		return update_info5;
	}

	public void setUpdate_info5(String update_info5)
	{
		this.update_info5 = update_info5;
	}

	public String getUpdate_time()
	{
		return update_time;
	}

	public void setUpdate_time(String update_time)
	{
		this.update_time = update_time;
	}

	public String getApp_version()
	{
		return app_version;
	}

	public void setApp_version(String app_version)
	{
		this.app_version = app_version;
	}

	public String getPackage_name()
	{
		return package_name;
	}

	public void setPackage_name(String package_name)
	{
		this.package_name = package_name;
	}

	@Override
    public String toString()
    {
	    return "ItemInfo [app_id=" + app_id + ", id=" + id + ", icon_url=" + icon_url + ", app_name=" + app_name + ", app_size=" + app_size + ", developer_info=" + developer_info + ", app_image1=" + app_image1 + ", app_image2=" + app_image2 + ", app_image3=" + app_image3 + ", app_image4="
	                    + app_image4 + ", app_image5=" + app_image5 + ", app_description=" + app_description + ", update_info1=" + update_info1 + ", update_info2=" + update_info2 + ", update_info3=" + update_info3 + ", update_info4=" + update_info4 + ", update_info5=" + update_info5
	                    + ", update_time=" + update_time + ", app_version=" + app_version + ", package_name=" + package_name + ", getApp_id()=" + getApp_id() + ", getId()=" + getId() + ", getIcon_url()=" + getIcon_url() + ", getApp_name()=" + getApp_name() + ", getApp_size()=" + getApp_size()
	                    + ", getDeveloper_info()=" + getDeveloper_info() + ", getApp_image1()=" + getApp_image1() + ", getApp_image2()=" + getApp_image2() + ", getApp_image3()=" + getApp_image3() + ", getApp_image4()=" + getApp_image4() + ", getApp_image5()=" + getApp_image5()
	                    + ", getApp_description()=" + getApp_description() + ", getUpdate_info1()=" + getUpdate_info1() + ", getUpdate_info2()=" + getUpdate_info2() + ", getUpdate_info3()=" + getUpdate_info3() + ", getUpdate_info4()=" + getUpdate_info4() + ", getUpdate_info5()=" + getUpdate_info5()
	                    + ", getUpdate_time()=" + getUpdate_time() + ", getApp_version()=" + getApp_version() + ", getPackage_name()=" + getPackage_name() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
    }
}