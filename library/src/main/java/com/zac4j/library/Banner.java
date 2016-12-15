package com.zac4j.library;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

/**
 * BannerPager
 * Created by zac on 12/12/2016.
 */

public class Banner extends FrameLayout {

  private ViewPager mViewPager;
  private CircleIndicator mIndicator;

  public Banner(Context context) {
    super(context);
    init(context);
  }

  public Banner(Context context, AttributeSet attrs) {
    super(context, attrs);
    init(context);
  }

  private void init(Context context) {
    View view = LayoutInflater.from(context).inflate(R.layout.banner, null);

    mViewPager = (ViewPager) view.findViewById(R.id.view_pager);

    mIndicator = (CircleIndicator) view.findViewById(R.id.view_indicator);
    this.addView(view);
  }

  public void setData(int[] bannerResources) {
    PagerAdapter adapter = new BannerAdapter(bannerResources);
    mViewPager.setAdapter(adapter);
    mIndicator.setViewPager(mViewPager);
  }
}
