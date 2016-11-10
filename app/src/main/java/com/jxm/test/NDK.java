package com.jxm.test;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/10/28.
 */

public class NDK {

    private Context mContext;

    public NDK(Context mContext) {
        this.mContext = mContext;
    }

    public native String getStringFromC() throws IllegalStateException;

    public native String getStringFromNative() throws IllegalStateException;
    
    public native String _sayHello() throws IllegalStateException;

    public void showToast(){
        Toast.makeText(mContext,"c++ call java", Toast.LENGTH_SHORT).show();
    }

    public void showToast(String str){
        Toast.makeText(mContext,str, Toast.LENGTH_SHORT).show();
    }
    public native void onClick();
}
