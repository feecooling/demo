package com.example.demo.bean;

import com.lidroid.xutils.db.annotation.Table;

@Table(name = "mobile_info")
public class MobileInfo
{
	private int id;
	private int screen_width;
	private int screen_height;
	private String imei;
	private String software_version;
	private String channel_id;
	private String ip;
	private String imsi;
	private String android_id;
	private String mac_address;
	private String mobile_manufacturer;
	private String mobile_model;
	private String operating_system_version;
	private String location_latitude;
	private String location_longitude;
	private String iswifi;

	public MobileInfo()
	{
		super();
	}

	public MobileInfo(int id, int screen_width, int screen_height, String imei, String software_version, String channel_id, String ip, String imsi, String android_id, String mac_address, String mobile_manufacturer, String mobile_model, String operating_system_version, String location_latitude,
	                String location_longitude, String iswifi)
	{
		super();
		this.id = id;
		this.screen_width = screen_width;
		this.screen_height = screen_height;
		this.imei = imei;
		this.software_version = software_version;
		this.channel_id = channel_id;
		this.ip = ip;
		this.imsi = imsi;
		this.android_id = android_id;
		this.mac_address = mac_address;
		this.mobile_manufacturer = mobile_manufacturer;
		this.mobile_model = mobile_model;
		this.operating_system_version = operating_system_version;
		this.location_latitude = location_latitude;
		this.location_longitude = location_longitude;
		this.iswifi = iswifi;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public int getScreen_width()
	{
		return screen_width;
	}

	public void setScreen_width(int screenWidth)
	{
		screen_width = screenWidth;
	}

	public int getScreen_height()
	{
		return screen_height;
	}

	public void setScreen_height(int screenHeight)
	{
		screen_height = screenHeight;
	}

	public String getImei()
	{
		return imei;
	}

	public void setImei(String imei)
	{
		this.imei = imei;
	}

	public String getSoftware_version()
	{
		return software_version;
	}

	public void setSoftware_version(String softwareVersion)
	{
		software_version = softwareVersion;
	}

	public String getChannel_id()
	{
		return channel_id;
	}

	public void setChannel_id(String channelId)
	{
		channel_id = channelId;
	}

	public String getIp()
	{
		return ip;
	}

	public void setIp(String ip)
	{
		this.ip = ip;
	}

	public String getImsi()
	{
		return imsi;
	}

	public void setImsi(String imsi)
	{
		this.imsi = imsi;
	}

	public String getAndroid_id()
	{
		return android_id;
	}

	public void setAndroid_id(String androidId)
	{
		android_id = androidId;
	}

	public String getMac_address()
	{
		return mac_address;
	}

	public void setMac_address(String macAddress)
	{
		mac_address = macAddress;
	}

	public String getMobile_manufacturer()
	{
		return mobile_manufacturer;
	}

	public void setMobile_manufacturer(String mobileManufacturer)
	{
		mobile_manufacturer = mobileManufacturer;
	}

	public String getMobile_model()
	{
		return mobile_model;
	}

	public void setMobile_model(String mobileModel)
	{
		mobile_model = mobileModel;
	}

	public String getOperating_system_version()
	{
		return operating_system_version;
	}

	public void setOperating_system_version(String operatingSystemVersion)
	{
		operating_system_version = operatingSystemVersion;
	}

	public String getLocation_latitude()
	{
		return location_latitude;
	}

	public void setLocation_latitude(String locationLatitude)
	{
		location_latitude = locationLatitude;
	}

	public String getLocation_longitude()
	{
		return location_longitude;
	}

	public void setLocation_longitude(String locationLongitude)
	{
		location_longitude = locationLongitude;
	}

	public String getIswifi()
	{
		return iswifi;
	}

	public void setIswifi(String iswifi)
	{
		this.iswifi = iswifi;
	}

	@Override
	public String toString()
	{
		return "MobileInfoBean [android_id=" + android_id + ", channel_id=" + channel_id + ", id=" + id + ", imei=" + imei + ", imsi=" + imsi + ", ip=" + ip + ", iswifi=" + iswifi + ", location_latitude=" + location_latitude + ", location_longitude=" + location_longitude + ", mac_address="
		                + mac_address + ", mobile_manufacturer=" + mobile_manufacturer + ", mobile_model=" + mobile_model + ", operating_system_version=" + operating_system_version + ", screen_height=" + screen_height + ", screen_width=" + screen_width + ", software_version=" + software_version
		                + "]";
	}
}