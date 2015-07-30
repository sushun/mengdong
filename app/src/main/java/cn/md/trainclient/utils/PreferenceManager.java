package cn.md.trainclient.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import cn.md.trainclient.common.ClientApplication;

public class PreferenceManager {
    private static SharedPreferences mSharedPreferences = null;
    private static Editor mEditor = null;
    
    public final static String PREFERENCES_FILE_NAME = "client_config.pref";
    public final static int PREFERENCES_MODE_PRIVATE = Context.MODE_PRIVATE;
    
    public static void init(){
    	if (null == mSharedPreferences) {
    		mSharedPreferences = android.preference.PreferenceManager.getDefaultSharedPreferences(ClientApplication.getInstance()) ;
    	}
    }
    
    public static void init(Context context){
    	if (null == mSharedPreferences) {
    		mSharedPreferences = android.preference.PreferenceManager.getDefaultSharedPreferences(context) ;
    	}
    }
    
    /**
     * Returns SharedPreferences by specified context.
     */
    private final static SharedPreferences getSharedPreferences(Context context) {
      if (context == null) {
        return null;
      }
      return context.getSharedPreferences(PREFERENCES_FILE_NAME, PREFERENCES_MODE_PRIVATE);
    }

    /**
     * Gets SharedPreferences by Application.
     */
    public final static SharedPreferences getSharedPreferences() {
      return getSharedPreferences(ClientApplication.getInstance());
    }
    
    protected static void removeKey(String key){
        mEditor = mSharedPreferences.edit();
        mEditor.remove(key);
        mEditor.commit();
    }
    
    protected static void removeAll(){
        mEditor = mSharedPreferences.edit();
        mEditor.clear();
        mEditor.commit();
    }

    public static void commitString(String key, String value){
        mEditor = mSharedPreferences.edit();
        mEditor.putString(key, value);
        mEditor.commit();
    }
    
    public static String getString(String key, String failValue){
        return mSharedPreferences.getString(key, failValue);
    }
    
    public static void commitInt(String key, int value){
        mEditor = mSharedPreferences.edit();
        mEditor.putInt(key, value);
        mEditor.commit();
    }
    
    public static int getInt(String key, int failValue){
        return mSharedPreferences.getInt(key, failValue);
    }
    
    public static void commitLong(String key, long value){
        mEditor = mSharedPreferences.edit();
        mEditor.putLong(key, value);
        mEditor.commit();
    }
    
    public static long getLong(String key, long failValue) {
        return mSharedPreferences.getLong(key, failValue);
    }
    
    public static void commitBoolean(String key, boolean value){
        mEditor = mSharedPreferences.edit();
        mEditor.putBoolean(key, value);
        mEditor.commit();
    }
    
    public static Boolean getBoolean(String key, boolean failValue){
        return mSharedPreferences.getBoolean(key, failValue);
    }
}
