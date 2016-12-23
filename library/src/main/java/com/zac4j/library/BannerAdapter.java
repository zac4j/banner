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
  private boolean mIsOffline;
  private int[] mOfflineImageRes;
  private List<String> mOnlineImageRes;
  private ImageLoader mImageLoader;

  public BannerAdapter(Context context) {
    mContext = context;
  }

  public void setOffline(boolean isOffline) {
    mIsOffline = isOffline;
  }

  public void setOfflineImageRes(int[] offlineImageRes) {
    mOfflineImageRes = offlineImageRes;
  }

  public void setOnlineImageRes(List<String> onlineImageRes) {
    mOnlineImageRes = onlineImageRes;
  }

  public void setImageLoader(ImageLoader imageLoader) {
    mImageLoader = imageLoader;
  }

  @Override public Object instantiateItem(ViewGroup container, int position) {
    ImageView imageView = new ImageView(mContext);
    if (mIsOffline) {
      imageView.setImageResource(mOfflineImageRes[position]);
      imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
      container.addView(imageView);
    } else {
      String imageUrl = mOnlineImageRes.get(position);
      if (mImageLoader == ImageLoader.GLIDE) {
        Utils.loadImageWithGlide(mContext, imageUrl, imageView);
      } else {
        Utils.loadImageWithPicasso(mContext, imageUrl, imageView);
      }
      container.addView(imageView);
    }
    return imageView;
  }

  @Override public void destroyItem(ViewGroup container, int position, Object object) {
    container.removeView((View) object);
  }

  @Override public int getCount() {
    return mIsOffline ? mOfflineImageRes.length : mOnlineImageRes.size();
  }

  @Override public boolean isViewFromObject(View view, Object object) {
    return object == view;
  }
}
