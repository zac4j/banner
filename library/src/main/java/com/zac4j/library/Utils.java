package com.zac4j.library;

import android.content.Context;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

/**
 * Utilities
 * Created by zac on 12/15/2016.
 */

class Utils {

  static void loadImageWithPicasso(Context context, String imageUrl, ImageView imageView) {
    try {
      Class.forName("com.squareup.picasso.Picasso");
      Picasso.with(context)
          .load(imageUrl)
          .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
          .centerCrop()
          .placeholder(R.color.white)
          .into(imageView);
    } catch (ClassNotFoundException e) {
      throw new IllegalArgumentException("Must set a Square Picasso image loader dependency!");
    }
  }

  static void loadImageWithGlide(Context context, String imageUrl, ImageView imageView) {
    try {
      Class.forName("com.bumptech.glide.Glide");
      Glide.with(context)
          .load(imageUrl)
          .skipMemoryCache(true)
          .diskCacheStrategy(DiskCacheStrategy.NONE)
          .centerCrop()
          .placeholder(R.color.white)
          .crossFade()
          .into(imageView);
    } catch (ClassNotFoundException e) {
      throw new IllegalArgumentException("Must set a Google Glide image loader dependency!");
    }
  }
}
