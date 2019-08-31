package com.example.administrator.healthanalysistest.chat;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.healthanalysistest.R;
import com.example.administrator.healthanalysistest.bean.ChatBean;
import com.example.administrator.healthanalysistest.bean.ItemBean;

import java.util.List;

/**
 * Created by Administrator on 2019/4/21.
 */

public class MyAdapter1 extends RecyclerView.Adapter<MyAdapter1.ViewHolder> {
    private List<ChatBean> mList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        View myView;
        TextView chatName;
        TextView chatContent;

        public ViewHolder(View itemView) {
            super(itemView);
            myView = itemView;
            chatName = (TextView) itemView.findViewById(R.id.tv_chatname);
            chatContent = (TextView) itemView.findViewById(R.id.tv_chatcontent);
        }

    }

    public MyAdapter1(List<ChatBean> list) {
        this.mList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat, null);
        final ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ChatBean bean = mList.get(position);
        holder.chatName.setText(bean.getChaterName());
        holder.chatContent.setText(bean.getChaterMessage());

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    //页面加载
    public void add(List<ChatBean> addMessageList){
        int position=mList.size();
        mList.addAll(position,mList);
        notifyItemInserted(position);
    }

    //页面刷新
    public void refresh(List<ChatBean> newList){
        mList.removeAll(mList);
        mList.addAll(newList);
        notifyDataSetChanged();
    }


}
