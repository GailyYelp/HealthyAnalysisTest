package com.example.administrator.healthanalysistest.fragment;


import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.administrator.healthanalysistest.R;


public class OneFragment extends Fragment {

    private ImageView iv1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_one,null);
        //iv1=(ImageView) view.findViewById(R.id.iv_1);
        //RoundedCorners roundedCorners= new RoundedCorners(20);
        //通过RequestOptions扩展功能,override:采样率,因为ImageView就这么大,可以压缩图片,降低内存消耗
        //RequestOptions options=RequestOptions.bitmapTransform(roundedCorners).override(290, 150);

        //Glide.with(this).load(R.mipmap.info1).apply(options).into(iv1);
        return view;
    }




}
