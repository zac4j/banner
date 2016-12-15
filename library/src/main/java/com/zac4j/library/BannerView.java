package com.zac4j.library;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import java.util.List;

/**
 * BannerPager
 * Created by zac on 12/12/2016.
 */

public class BannerView extends FrameLayout {

  private Context mContext;
  private ViewPager mViewPager;
  private CircleIndicator mIndicator;

  private boolean mAutoSlide;
  private View mView;

  public BannerView(Context context) {
    super(context);
    init(context);
  }

  public BannerView(Context context, AttributeSet attrs) {
    super(context, attrs);
    init(context);
  }

  private void init(Context context) {
    mContext = context;
    mView = LayoutInflater.from(context).inflate(R.layout.banner, null);

    mViewPager = (ViewPager) mView.findViewById(R.id.view_pager);

    mIndicator = (CircleIndicator) mView.findViewById(R.id.view_indicator);
  }

  void show() {
    this.addView(mView);
  }

  void hide() {
    if (mView != null) {
      this.removeView(mView);
    }
  }

  void setOfflineData(int[] offlineData) {
    BannerAdapter adapter = new BannerAdapter(mContext);
    adapter.setOffline(true);
    adapter.setOfflineImageRes(offlineData);
    mViewPager.setAdapter(adapter);
    mIndicator.setViewPager(mViewPager);
  }

  void setNetworkData(List<String> networkData, ImageLoader imageLoader) {
    BannerAdapter adapter = new BannerAdapter(mContext);
    adapter.setOnlineImageRes(networkData);
    adapter.setImageLoader(imageLoader);
    mViewPager.setAdapter(adapter);
    mIndicator.setViewPager(mViewPager);
  }

  public void setAutoSlide(boolean autoSlide) {
    mAutoSlide = autoSlide;
  }
}
