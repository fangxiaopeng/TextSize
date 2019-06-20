package com.fxp.textsize;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Map;

/**
 * Title:       PreferencesUtil
 * <p>
 * Package:     com.fxp.textsize
 * <p>
 * Author:      fxp
 * <p>
 * Create at:   2019-06-20 09:18
 * <p>
 * Description:
 * <p>
 * <p>
 * Modification History:
 * <p>
 * Date       Author       Version      Description
 * -----------------------------------------------------------------
 * 2019-06-20    fxp       1.0         First Created
 * <p>
 * Github:  https://github.com/fangxiaopeng
 */
public class PreferencesUtil {

    private static final String TAG = PreferencesUtil.class.getSimpleName();

    private Context mContext;

    /**
     * SharedPreferences 文件名称
     */
    private static String mName = "FXP";

    private static PreferencesUtil mInstance = null;

    private SharedPreferences mPreferences;

    public static PreferencesUtil getInstance(Context context) {
        if (null == mInstance) {
            synchronized (TAG) {
                if (null == mInstance) {
                    mInstance = new PreferencesUtil(context.getApplicationContext());
                }
            }
        }
        return mInstance;
    }

    private PreferencesUtil(Context context) {
        mContext = context;
        if (null == mContext) {
            throw new RuntimeException("Context is null! please wait context is init!");
        }
    }

    private SharedPreferences getSharedPreferences() {
        return mContext.getSharedPreferences(mName, Context.MODE_PRIVATE);
    }

    public void clear() {
        SharedPreferences preferences = getSharedPreferences();
        preferences.edit().clear().commit();
        Log.w(TAG, "clear!");
    }

    public void remove(String key) {
        if (key == null || key.equals("")) return;
        SharedPreferences preferences = getSharedPreferences();
        preferences.edit().remove(key).commit();
    }

    public void putInt(String key, int value) {
        if (key == null || key.equals("")) return;
        SharedPreferences preferences = getSharedPreferences();
        preferences.edit().putInt(key, value).commit();
    }

    public int getInt(String key) {
        if (key == null || key.equals("")) return 0;
        SharedPreferences preferences = getSharedPreferences();
        return preferences.getInt(key, 0);
    }

    public int getInt(String key, int value) {
        if (key == null || key.equals("")) return value;
        SharedPreferences preferences = getSharedPreferences();
        return preferences.getInt(key, value);
    }

    public void putString(String key, String value) {
        if (key == null || key.equals("")) return;
        SharedPreferences preferences = getSharedPreferences();
        preferences.edit().putString(key, value).commit();
    }

    public String getString(String key) {
        if (key == null || key.equals("")) return null;
        SharedPreferences preferences = getSharedPreferences();
        return preferences.getString(key, "");
    }

    public void putBoolean(String key, boolean value) {
        if (key == null || key.equals("")) return;
        SharedPreferences preferences = getSharedPreferences();
        preferences.edit().putBoolean(key, value).commit();
    }

    public boolean getBoolean(String key) {
        if (key == null || key.equals("")) return false;
        SharedPreferences preferences = getSharedPreferences();
        return preferences.getBoolean(key, false);
    }

    public boolean getBoolean(String key, boolean value) {
        if (key == null || key.equals("")) return value;
        SharedPreferences preferences = getSharedPreferences();
        return preferences.getBoolean(key, value);
    }

    public void putLong(String key, long value) {
        if (key == null || key.equals("")) return;
        SharedPreferences preferences = getSharedPreferences();
        preferences.edit().putLong(key, value).commit();
    }

    public long getLong(String key) {
        if (key == null || key.equals("")) return 0;
        SharedPreferences preferences = getSharedPreferences();
        return preferences.getLong(key, 0);
    }

    public long getLong(String key, long value) {
        if (key == null || key.equals("")) return 0;
        SharedPreferences preferences = getSharedPreferences();
        return preferences.getLong(key, value);
    }

    /**
     * @param key
     * @return boolean
     * @Description: 查询某个key是否已经存在
     * @Author: fxp
     * @Date: 2018/4/21   下午6:03
     * @exception/throws
     */
    public boolean contains(String key) {
        if (key == null || key.equals("")) return false;

        SharedPreferences preferences = getSharedPreferences();

        return preferences.contains(key);
    }

    /**
     * @param
     * @return java.util.Map<java.lang.String, ?>
     * @Description: 返回所有的键值对
     * @Author: fxp
     * @Date: 2018/4/21   下午6:05
     * @exception/throws
     */
    public Map<String, ?> getAll() {
        SharedPreferences preferences = getSharedPreferences();

        return preferences.getAll();
    }


    /**
     * @param key
     * @param bitmap 图片Bitmap
     * @return void
     * @Description: 保存图片到SharedPreferences
     * @Author: fxp
     * @Date: 2018/4/21   下午6:11
     * @exception/throws
     */
    public void putBitmap(String key, Bitmap bitmap) {
        if (key == null || key.equals("")) return;

        // 将Bitmap压缩成字节数组输出流
        ByteArrayOutputStream byStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 80, byStream);
        // 利用Base64将我们的字节数组输出流转换成String
        byte[] byteArray = byStream.toByteArray();
        String imgString = new String(Base64.encodeToString(byteArray, Base64.DEFAULT));

        SharedPreferences preferences = getSharedPreferences();
        preferences.edit().putString(key, imgString).commit();
    }

    /**
     * @param key
     * @return android.graphics.Bitmap    图片Bitmap
     * @Description: 从SharedPreferences读取图片
     * @Author: fxp
     * @Date: 2018/4/21   下午6:15
     * @exception/throws
     */
    public Bitmap getBitmap(String key) {

        if (key == null || key.equals("")) return null;

        SharedPreferences preferences = getSharedPreferences();

        String imgString = preferences.getString(key, "");

        if (!imgString.equals("")) {
            // 利用Base64将我们string转换
            byte[] byteArray = Base64.decode(imgString, Base64.DEFAULT);
            ByteArrayInputStream byStream = new ByteArrayInputStream(byteArray);
            // 生成bitmap
            return BitmapFactory.decodeStream(byStream);
        }

        return null;
    }

}
