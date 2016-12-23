package com.zac4j.library;

import android.support.annotation.NonNull;
import com.zac4j.widget.BannerView;
import java.util.List;

/**
 * Banner
 * Created by zac on 12/15/2016.
 */

public class Banner {

  private BannerView mBannerView;

  private Banner(BannerView bannerView, boolean isOffline, int[] offlineRes,
      ImageLoader imageLoader, List<String> networkImageRes, boolean autoSlide) {
    mBannerView = bannerView;
    if (isOffline) {
      bannerView.setOfflineData(offlineRes);
    } else {
      bannerView.setOnlineData(networkImageRes, imageLoader);
    }
    bannerView.setAutoSlide(autoSlide);
  }

  public void show() {
    mBannerView.show();
  }

  public void hide() {
    mBannerView.hide();
  }

  public static class Builder {
    private BannerView bannerView;
    private ImageLoader imageLoader;
    private List<String> imageUrlList;
    private int[] imageRes;
    private boolean isOffline;
    private boolean isAutoSlide;

    public Builder() {
    }

    public Builder bind(@NonNull BannerView bannerView) {
      if (bannerView == null) {
        throw new IllegalArgumentException("BannerView has not been bound!");
      }
      this.bannerView = bannerView;
      return this;
    }

    public Builder engine(ImageLoader imageLoader) {
      if (imageLoader == null) {
        throw new IllegalArgumentException("ImageLoader must not be null!");
      }
      this.imageLoader = imageLoader;
      return this;
    }

    public Builder onlineRes(@NonNull List<String> imageUrlList) {
      if (imageUrlList == null || imageUrlList.isEmpty()) {
        throw new IllegalStateException("network image resource is empty!");
      }
      this.imageUrlList = imageUrlList;
      return this;
    }

    public Builder offlineRes(@NonNull int[] imageRes) {
      if (imageRes == null || imageRes.length == 0) {
        throw new IllegalStateException("offline image resource is empty!");
      }
      this.imageRes = imageRes;
      return this;
    }

    public Builder offline(boolean isOffline) {
      this.isOffline = isOffline;
      return this;
    }

    public Builder autoSlide(boolean isAutoSlide) {
      this.isAutoSlide = isAutoSlide;
      return this;
    }

    public Banner build() {
      if (bannerView == null) {
        throw new IllegalArgumentException("BannerView has not been bound!");
      }

      if (isOffline && imageRes.length == 0) {
        throw new IllegalStateException("offline resource required!");
      }

      return new Banner(bannerView, isOffline, imageRes, imageLoader, imageUrlList, isAutoSlide);
    }
  }
}
