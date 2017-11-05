package com.example.db;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.model.City;
import com.example.model.County;
import com.example.model.Province;

/**
 * 把一些常用的数据库操作封装起来，方便日后使用
 * 
 * @author lindb
 * @time 2017.11.4
 * 
 */
public class XitWeatherDB {
	String TAG = "XitWeatherDB";
	/**
	 * 数据库名
	 */

	public static final String DB_NAME = "xit_weather";
	/**
	 * 数据库版本
	 */

	public static final int VERSION = 1;
	private static XitWeatherDB xitWeatherDB;
	private SQLiteDatabase db;

	/**
	 * 将构造方法私有化
	 * 
	 * @param context
	 */
	private XitWeatherDB(Context context) {
		XitWeatherOpenHelper dbHelper = new XitWeatherOpenHelper(context,
				DB_NAME, null, VERSION);
		Log.i(TAG, "dbHelper create success");
		db = dbHelper.getWritableDatabase();
		Log.i(TAG, "db create success");
	}

	/**
	 * 获取CoolWeatherDB的实例
	 * 
	 * @param context
	 * @return coolWeatherDB
	 */
	public synchronized static XitWeatherDB getInstance(Context context) {
		if (xitWeatherDB == null)
			xitWeatherDB = new XitWeatherDB(context);
		return xitWeatherDB;
	}

	/**
	 * 将province实例存储到数据库
	 * 
	 * @param province
	 */
	public void saveProvince(Province province) {
		if (province != null) {
			ContentValues values = new ContentValues();
			values.put("province_name", province.getProvinceName());
			values.put("province_code", province.getProvinceCode());
			db.insert("Province", null, values);
		}
	}

	/**
	 * 从数据库读取全国的所有省份信息
	 * 
	 * @return List<Province>
	 */
	public List<Province> loadProvince() {
		List<Province> list = new ArrayList<Province>();
		Cursor cursor = db
				.query("Province", null, null, null, null, null, null);
		if (cursor.moveToFirst()) {
			do {
				Province province = new Province();
				province.setId(cursor.getInt(cursor.getColumnIndex("id")));
				province.setProvinceName(cursor.getString(cursor
						.getColumnIndex("province_name")));
				province.setProvinceCode(cursor.getString(cursor
						.getColumnIndex("province_code")));
				list.add(province);
			} while (cursor.moveToNext());
		}
		if (cursor != null)
			cursor.close();
		return list;
	}

	/**
	 * 将City实例存储到数据库
	 * 
	 * @param city
	 */
	public void saveCity(City city) {
		if (city != null) {
			ContentValues values = new ContentValues();
			values.put("city_name", city.getCityName());
			values.put("city_code", city.getCityCode());
			values.put("province_id", city.getProvinceId());
			db.insert("City", null, values);
		}
	}

	/**
	 * 从数据库读取某省下所有的城市信息
	 * 
	 * @param provinceId
	 * @return List<City>
	 */
	public List<City> loadCities(int provinceId) {
		List<City> list = new ArrayList<City>();
		Cursor cursor = db.query("City", null, "province_id = ?",
				new String[] { String.valueOf(provinceId) }, null, null, null);
		if (cursor.moveToFirst()) {
			do {
				City city = new City();
				city.setId(cursor.getInt(cursor.getColumnIndex("id")));
				city.setCityName(cursor.getString(cursor
						.getColumnIndex("city_name")));
				city.setCityCode(cursor.getString(cursor
						.getColumnIndex("city_code")));
				city.setProvinceId(provinceId);
				list.add(city);
			} while (cursor.moveToNext());
		}
		if (cursor != null)
			cursor.close();
		return list;
	}

	/**
	 * 将County实例 存入数据库
	 * 
	 * @param county
	 */
	public void saveCounty(County county) {
		if (county != null) {
			ContentValues values = new ContentValues();
			values.put("county_name", county.getCountyName());
			values.put("county_code", county.getCountyCode());
			values.put("city_id", county.getCityId());
			db.insert("County", null, values);
		}
	}

	/**
	 * 从数据库读取某城市下所有的县信息
	 * 
	 * @param cityId
	 * @return List<County>
	 */
	public List<County> loadCounties(int cityId) {
		List<County> list = new ArrayList<County>();
		Cursor cursor = db.query("County", null, "city_id = ?",
				new String[] { String.valueOf(cityId) }, null, null, null);
		if (cursor.moveToFirst()) {
			do {
				County county = new County();
				county.setId(cursor.getInt(cursor.getColumnIndex("id")));
				county.setCountyName(cursor.getString(cursor
						.getColumnIndex("county_name")));
				county.setCountyCode(cursor.getString(cursor
						.getColumnIndex("county_code")));
				Log.d(TAG,
						cursor.getString(cursor.getColumnIndex("county_name"))
								+ "hell");
				Log.d(TAG,
						cursor.getString(cursor.getColumnIndex("county_code"))
								+ "hell");
				county.setCityId(cityId);
				list.add(county);
			} while (cursor.moveToNext());

		}
		if (cursor != null)
			cursor.close();
		return list;
	}
}
