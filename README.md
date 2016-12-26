# Banner
A simple, responsive, touch-enabled,[RxJava2][r2] based image slider.
## Getting Started
#### 1.Include Banner framework and its dependencies in your project dependency.
```groovy
compile 'com.github.bumptech.glide:glide:3.7.0' // or
// compile 'com.squareup.picasso:picasso:2.5.2' // if you like
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
    showOnlineBanner(bannerView);
  }

  private void showOfflineBanner(BannerView bannerView) {
    int[] bannerRes = { R.drawable.a, R.drawable.b, R.drawable.c };
    Banner banner =
        new Banner.Builder().bind(bannerView).offline(true).offlineRes(bannerRes).build();
    banner.show();
  }

  private void showOnlineBanner(BannerView bannerView) {
    List<String> imageUrlList = new ArrayList<>();
    imageUrlList.add("http://7xom3t.com1.z0.glb.clouddn.com/a.png");
    imageUrlList.add("http://7xom3t.com1.z0.glb.clouddn.com/b.jpg");
    imageUrlList.add("http://7xom3t.com1.z0.glb.clouddn.com/c.jpg");

    Banner banner = new Banner.Builder().bind(bannerView)
        .onlineRes(imageUrlList)
        .engine(ImageLoader.GLIDE) // Or Picasso if you like
        .autoSlide(true)
        .build();
    banner.show();
  }
}
```
#### 4.Options and Settings
Write your own logic using these APIs:
- `bind(BannerView bannerView)` Bind BannerView in your layout with Banner Builder.
- `engine(ImageLoader engine)` Image loader engine, currently only support [Glide][glide] or [Picasso][picasso].
- `onlineRes(List<String> imgUrlList)` Download online image resources and display it.
- `offline(boolean isOffline)` If is load local image.
- `offlineRes(int[] imgUris)` Load local image resources and display it.
- `autoSlide(boolean isAutoSlide)` If image slider is auto slide.

## License
The code is available under the [Apache License][license]

[r2]:https://github.com/ReactiveX/RxJava
[glide]:https://github.com/bumptech/glide
[picasso]:https://github.com/square/picasso
[license]:https://github.com/zac4j/Banner/blob/master/LICENSE