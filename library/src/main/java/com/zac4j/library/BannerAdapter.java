package com.zac4j.library;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * PagerAdapter
 * Created by zac on 12/12/2016.
 */

class BannerAdapter extends PagerAdapter {

  private int[] mBannerResources;

  BannerAdapter(int[] bannerResources) {
    mBannerResources = bannerResources;
  }

  @Override public Object instantiateItem(ViewGroup container, int position) {
    int resource = mBannerResources[position];
    ImageView imageView = new ImageView(container.getContext());
    imageView.setImageResource(resource);
    imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
    container.addView(imageView);
    return imageView;
  }

  @Override public void destroyItem(ViewGroup container, int position, Object object) {
    container.removeView((View) object);
  }

  @Override public int getCount() {
    return mBannerResources.length;
  }

  @Override public boolean isViewFromObject(View view, Object object) {
    return object == view;
  }
}
