package com.customeview.rxjava_mvp.utils;

import android.app.Activity;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

/**
 * Created by Administrator on 2016/12/25 0025.
 */

public class SnackBarUtils {
    public static void  showSnackBar(Activity content,String str){
        if (getView(content)!=null){
            Snackbar.make(getView(content),str,Snackbar.LENGTH_SHORT);
        }
    }
    public static View getView(Activity activity) {
        ViewGroup contentView = (ViewGroup) activity.findViewById(Window.ID_ANDROID_CONTENT);
        View view = contentView.getChildAt(1);
        return view;
    }
    public static void ActionSnackBarShow(Activity content, String meg, String action_str, final SnackBarCallBack callBack){
        if (getView(content)==null){
            return;
        }
        Snackbar.make(getView(content),meg,Snackbar.LENGTH_SHORT).setAction(action_str, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (callBack!=null){
                    callBack.callBack();
                }
            }
        }).show();

    }
}
