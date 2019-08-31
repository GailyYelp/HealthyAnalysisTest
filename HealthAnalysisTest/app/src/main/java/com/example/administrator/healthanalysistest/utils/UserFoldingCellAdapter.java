package com.example.administrator.healthanalysistest.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.healthanalysistest.R;
import com.example.administrator.healthanalysistest.bean.Info;
import com.ramotion.foldingcell.FoldingCell;

import java.util.List;

/**
 * Created by Administrator on 2019/4/21.
 */

public class UserFoldingCellAdapter extends BaseAdapter {
    private Context mContext;
    private List<Info> data;

    public UserFoldingCellAdapter(Context mContext, List<Info> data) {
        this.mContext = mContext;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        FoldingCell view=(FoldingCell)convertView;
        ViewHolder holder=null;
        if(view==null){
            view= (FoldingCell) LayoutInflater.from(mContext).inflate(R.layout.cell,null);
            holder=new ViewHolder();
            holder.former=view.findViewById(R.id.former_title);
            holder.reason=view.findViewById(R.id.reason);
            holder.title=view.findViewById(R.id.the_title);
            holder.picture=view.findViewById(R.id.picture);
            holder.feature=view.findViewById(R.id.feature);
            holder.description=view.findViewById(R.id.description);
            view.setTag(holder);
        }else{
            holder=(ViewHolder) view.getTag();
        }
        holder.former.setText(data.get(position).getTheFormTitle());
        holder.reason.setText(data.get(position).getReason());
        holder.title.setText(data.get(position).getTitle());
        holder.picture.setImageResource(data.get(position).getImage());
        holder.feature.setText(data.get(position).getFeature());
        holder.description.setText(data.get(position).getContent());

        return view;
    }

    static class ViewHolder{
        ImageView picture;
        TextView feature;
        TextView description;
        TextView title;
        TextView former;
        TextView reason;
    }
}
