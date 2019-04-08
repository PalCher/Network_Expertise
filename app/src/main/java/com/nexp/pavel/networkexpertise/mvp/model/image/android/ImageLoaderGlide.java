package com.nexp.pavel.networkexpertise.mvp.model.image.android;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.nexp.pavel.networkexpertise.mvp.model.image.ImageLoader;

public class ImageLoaderGlide implements ImageLoader<ImageView> {


    @Override
    public void loadInto(@Nullable String url, ImageView container) {
        Glide.with(container.getContext())
                .load(url)
                .override(600,600)
                .into(container);
    }
}
