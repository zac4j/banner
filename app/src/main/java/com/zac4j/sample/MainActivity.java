package com.zac4j.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import com.zac4j.library.Banner;
import com.zac4j.library.ImageLoader;
import com.zac4j.widget.BannerView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

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
