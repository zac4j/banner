package com.zac4j.library;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import java.util.List;

/**
 * PagerAdapter
 * Created by zac on 12/12/2016.
 */

public class BannerAdapter extends PagerAdapter {

  private Context mContext;
  private int[] mOfflineImageRes;
  private List<String> mOnlineImageRes;
  private ImageLoader mImageLoader;
  private OnBannerClickListener mOnBannerClickListener;

  public BannerAdapter(Context context, ImageLoader imageLoader, int[] offlineImageRes,
      List<String> onlineImageRes) {
    mContext = context;
    mImageLoader = imageLoader;
    mOfflineImageRes = offlineImageRes;
    mOnlineImageRes = onlineImageRes;
  }

  public void setOnBannerClickListener(OnBannerClickListener listener) {
    mOnBannerClickListener = listener;
  }

  @Override public Object instantiateItem(ViewGroup container, final int position) {
    ImageView imageView = new ImageView(mContext);
    imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
    String imageUrl = mOnlineImageRes.get(position);
    int placeholder = 0;
    if (mOfflineImageRes != null && mOfflineImageRes.length != 0) {
      placeholder = mOfflineImageRes[position];
    }
    if (mImageLoader == ImageLoader.GLIDE) {
      Utils.loadImageWithGlide(mContext, imageUrl, placeholder, imageView);
    } else {
      Utils.loadImageWithPicasso(mContext, imageUrl, placeholder, imageView);
    }
    container.addView(imageView);
    imageView.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        mOnBannerClickListener.onClick(position);
      }
    });
    return imageView;
  }

  @Override public void destroyItem(ViewGroup container, int position, Object object) {
    container.removeView((View) object);
  }

  @Override public int getCount() {
    return mOnlineImageRes.size();
  }

  @Override public boolean isViewFromObject(View view, Object object) {
    return object == view;
  }
}
