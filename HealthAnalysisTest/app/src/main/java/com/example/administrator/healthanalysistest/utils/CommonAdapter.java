package com.example.administrator.healthanalysistest.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by Administrator on 2019/4/17.
 */

public abstract class CommonAdapter<T> extends BaseAdapter {
    private Context context;
    private List<T> datas;
    private int layoutId;

    public CommonAdapter(Context context,List<T> datas,int layoutId){
        this.context=context;
        this.datas=datas;
        this.layoutId=layoutId;
    }

    @Override
    public int getCount() {
        return datas== null ? 0 : datas.size();
    }

    @Override
    public T getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(layoutId,null);
        }
        T t=getItem(position);
        convertView(convertView,t);
        return convertView;
    }

    protected  abstract  void convertView(View item,T t);
}
