package com.example.util;   

import android.content.Context;  
import android.content.SharedPreferences;  
import android.content.SharedPreferences.Editor;  

public class SPUtil {  
	// 构造器私有  
	private SPUtil() {  
	}  

	// 私有、静态实例  
	private static SPUtil instance = new SPUtil();  

	// 公共、静态方法获取实例  
	public static SPUtil getInstance(Context context) {  
    	if (sp == null) {  
    	    sp = context.getSharedPreferences("atguigu", Context.MODE_PRIVATE);  
    	}  
    	return instance;  
	}  

	private static SharedPreferences sp;  

	// 保存数据的方法  
	public void put(String key, Object defValue) {  
      
    	Editor edit = sp.edit();  
    	if (defValue instanceof Boolean) {  
    	    edit.putBoolean(key, (Boolean) defValue);  
    	}  
    	if (defValue instanceof String) {  
    	    edit.putString(key, (String) defValue);  
    	}  
    	if (defValue instanceof Integer) {  
    	    edit.putInt(key, (Integer) defValue);  
    	}  
    	edit.commit();  
      
	}  

	// 获取数据  
	public String getString(String key, String defValue) {  
    	return sp.getString(key, defValue);  
	}  

	public int getInt(String key, int defValue) {  
    	return sp.getInt(key, defValue);  
	}  

	public boolean getboolean(String key, boolean defValue) {  
    	return sp.getBoolean(key, defValue);  
	}  
}  