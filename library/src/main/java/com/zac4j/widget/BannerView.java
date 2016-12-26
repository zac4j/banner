package com.zac4j.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import com.zac4j.library.BannerAdapter;
import com.zac4j.library.ImageLoader;
import com.zac4j.library.Logger;
import com.zac4j.library.OnBannerClickListener;
import com.zac4j.library.R;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * BannerPager
 * Created by zac on 12/12/2016.
 */

public class BannerView extends FrameLayout {

  private static final String TAG = "BannerView";

  private ViewPager mViewPager;
  private CircleIndicator mIndicator;

  private Context mContext;
  private View mView;
  private int mSlideCount;
  private int mBeep;
  private boolean mNeedPause;
  private CompositeDisposable mDisposable;
  private BannerAdapter mAdapter;

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

    mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
      @Override
      public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
      }

      @Override public void onPageSelected(int position) {
        mBeep = position;
      }

      @Override public void onPageScrollStateChanged(int state) {
        mNeedPause = !(state == ViewPager.SCROLL_STATE_IDLE);
      }
    });

    mDisposable = new CompositeDisposable();
  }

  public void show() {
    this.addView(mView);
  }

  public void hide() {
    if (mView != null) {
      this.removeView(mView);
    }
  }

  /**
   * Set image resource
   *
   * @param imageLoader Image Loader
   * @param offlineData offline resources
   * @param onlineData online resources
   */
  public void setData(ImageLoader imageLoader, int[] offlineData, List<String> onlineData) {
    if (mAdapter == null) {
      mAdapter = new BannerAdapter(mContext, imageLoader, offlineData, onlineData);
    }
    mViewPager.setAdapter(mAdapter);
    mIndicator.setViewPager(mViewPager);
  }

  /**
   * Set banner click listener
   *
   * @param listener on banner click listener
   */
  public void setOnBannerClickListener(OnBannerClickListener listener) {
    if (listener == null || mAdapter == null) {
      return;
    }

    mAdapter.setOnBannerClickListener(listener);
  }

  /**
   * Set if auto slide page view.
   *
   * @param autoSlide auto slide page view.
   */
  public void setAutoSlide(boolean autoSlide) {
    if (autoSlide) {
      mSlideCount = mViewPager.getAdapter().getCount();
      applyAutoSlide();
    }
  }

  /**
   * On view destroyed need clear interval task.
   */
  @Override protected void onDetachedFromWindow() {
    super.onDetachedFromWindow();
    mDisposable.clear();
  }

  /**
   * Apply auto slide logic
   */
  private void applyAutoSlide() {
    mDisposable.add(getObservable().subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeWith(getObserver()));
  }

  /**
   * Get interval task observable object.
   */
  private Observable<? extends Long> getObservable() {
    return Observable.interval(0, 3, TimeUnit.SECONDS);
  }

  /**
   * Get observer to consume observable object.
   */
  private DisposableObserver<Long> getObserver() {
    return new DisposableObserver<Long>() {
      @Override public void onNext(Long value) {
        if (mNeedPause) {
          return;
        }
        if (mBeep == mSlideCount) {
          mBeep = 0;
        }
        mViewPager.setCurrentItem(mBeep);
        mBeep++;
        Logger.i(TAG, "Beep");
      }

      @Override public void onError(Throwable e) {
        Logger.e(TAG, e.getMessage());
      }

      @Override public void onComplete() {
        Logger.i(TAG, "Complete");
      }
    };
  }
}
