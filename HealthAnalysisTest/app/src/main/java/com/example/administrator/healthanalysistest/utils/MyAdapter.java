package com.example.administrator.healthanalysistest.utils;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.administrator.healthanalysistest.R;
import com.example.administrator.healthanalysistest.bean.ItemBean;

import java.util.List;

/**
 * Created by Administrator on 2019/4/21.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<ItemBean> mList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        View myView;
        ImageView imageView;
        TextView content;

        public ViewHolder(View itemView) {
            super(itemView);
            myView = itemView;
            imageView = (ImageView) itemView.findViewById(R.id.iv_image);
            content = (TextView) itemView.findViewById(R.id.tv_content);
        }

    }

    public MyAdapter(List<ItemBean> list) {
        this.mList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item1, null);
        final ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ItemBean bean = mList.get(position);
        holder.imageView.setBackgroundResource(bean.getItemImage());
        holder.content.setText(bean.getItemContent());

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    //页面加载
    public void add(List<ItemBean> addMessageList){
        int position=mList.size();
        mList.addAll(position,addMessageList);
        notifyItemInserted(position);
    }

    //页面刷新
    public void refresh(List<ItemBean> newList){
        mList.removeAll(mList);
        mList.addAll(newList);
        notifyDataSetChanged();
    }


}
