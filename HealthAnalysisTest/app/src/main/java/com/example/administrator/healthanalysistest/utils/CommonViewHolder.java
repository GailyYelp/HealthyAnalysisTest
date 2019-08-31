package com.example.administrator.healthanalysistest.utils;

import android.util.SparseArray;
import android.view.View;

/**
 * Created by Administrator on 2019/4/17.
 */

public class CommonViewHolder {
    /**
     * @param view 所有缓存View的根View
     * @param id   缓存View的唯一标识
     * @return
     */
    public static <T extends View> T get(View view, int id) {

        SparseArray<View> viewHolder = (SparseArray<View>) view.getTag();
        //如果根view没有用来缓存View的集合
        if (viewHolder == null) {
            viewHolder = new SparseArray<View>();
            view.setTag(viewHolder);//创建集合和根View关联
        }
        View chidlView = viewHolder.get(id);//获取根View储存在集合中的孩纸
        if (chidlView == null) {//如果没有改孩纸
            //找到该孩纸
            chidlView = view.findViewById(id);
            viewHolder.put(id, chidlView);//保存到集合
        }
        return (T) chidlView;
    }
}
