# Banner
A simple, responsive, touch-enabled, [RxJava2][r2] based image slider.

## Warning
If you hate [RxJava][r2] or [Picasso][picasso]/[Glide][glide] by now, i personally beg you don't select this library,it's waste your time.

## ScreenShot
![auto-slide][slide]

## Getting Started
#### 1.Include Banner framework and its dependencies in your project dependency.
```groovy
compile 'com.zac4j.library:Banner:0.0.1'
compile 'com.github.bumptech.glide:glide:3.7.0'
// compile 'com.squareup.picasso:picasso:2.5.2' // as your wish
compile 'io.reactivex.rxjava2:rxandroid:2.0.1'
compile 'io.reactivex.rxjava2:rxjava:2.0.1'
```
#### 2.Add the BannerView widget in your xml file.
```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout
  xmlns:android="http://schemas.android.com/apk/res/android"
  android:layout_width="match_parent"
  android:layout_height="match_parent"
  >

  <com.zac4j.widget.BannerView
    android:id="@id/banner_view"
    android:layout_width="match_parent"
    android:layout_height="@dimen/banner_height_small"
    />

</LinearLayout>
```
#### 3.Initialize Banner
```java
public class MainActivity extends Activity {
  @Override protected void onCreate(Bundle savedInstanceState) {
    ...
    BannerView bannerView = (BannerView) findViewById(R.id.banner_view);
    showBanner(bannerView);
  }

  private int[] getOfflineRes() {
      return new int[] { R.drawable.a, R.drawable.b, R.drawable.c };
    }

  private List<String> getOnlineRes() {
    List<String> imageUrlList = new ArrayList<>();
    imageUrlList.add("http://7xom3t.com1.z0.glb.clouddn.com/a.png");
    imageUrlList.add("http://7xom3t.com1.z0.glb.clouddn.com/b.jpg");
    imageUrlList.add("http://7xom3t.com1.z0.glb.clouddn.com/c.jpg");
    return imageUrlList;
  }

  private void showBanner(BannerView bannerView) {
    Banner banner = new Banner.Builder().bind(bannerView)
        .offlineRes(getOfflineRes())
        .onlineRes(getOnlineRes())
        .engine(ImageLoader.GLIDE)
        .onClick(mListener)
        .autoSlide(true)
        .build();
    banner.show();
  }

  private Banner.OnClickListener mListener = new Banner.OnClickListener() {
    @Override public void onClick(int position) {
      Toast.makeText(MainActivity.this, "Position >> " + position, Toast.LENGTH_SHORT).show();
    }
  };
}
```
#### 4.Options and Settings
Write your own logic using these APIs:
- `bind(BannerView bannerView)` Bind BannerView in your layout with Banner Builder.
- `engine(ImageLoader engine)` Image loader engine, currently only support [Glide][glide] or [Picasso][picasso].
- `offlineRes(int[] imgUris)` Load local image resources and display it.
- `onlineRes(List<String> imgUrlList)` Download online image resources and display it.
- `onClick(Banenr.OnClickListener listener)` On banner item click callback.
- `autoSlide(boolean isAutoSlide)` If image slider is auto slide.

## License
The code is available under the [Apache License][license]

[slide]:http://7xom3t.com1.z0.glb.clouddn.com/auto-slide.gif
[r2]:https://github.com/ReactiveX/RxJava
[glide]:https://github.com/bumptech/glide
[picasso]:https://github.com/square/picasso
[license]:https://github.com/zac4j/Banner/blob/master/LICENSE