package com.example.util;   

import android.content.Context;  
import android.content.SharedPreferences;  
import android.content.SharedPreferences.Editor;  

public class SPUtil {  
	// ������˽��  
	private SPUtil() {  
	}  

	// ˽�С���̬ʵ��  
	private static SPUtil instance = new SPUtil();  

	// ��������̬������ȡʵ��  
	public static SPUtil getInstance(Context context) {  
    	if (sp == null) {  
    	    sp = context.getSharedPreferences("atguigu", Context.MODE_PRIVATE);  
    	}  
    	return instance;  
	}  

	private static SharedPreferences sp;  

	// �������ݵķ���  
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

	// ��ȡ����  
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