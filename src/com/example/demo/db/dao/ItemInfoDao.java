package com.example.demo.db.dao;

import android.content.Context;

import com.example.demo.bean.ItemInfo;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.exception.DbException;

public class ItemInfoDao
{
	public void save(Context context)
	{
		DbUtils db = DbUtils.create(context);
		ItemInfo bean = new ItemInfo();
		try
        {
	        db.save(bean);
        } catch (DbException e)
        {
	        e.printStackTrace();
        }
	}
}