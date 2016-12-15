package com.zac4j.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.zac4j.library.Banner;
import com.zac4j.library.BannerView;
import com.zac4j.library.ImageLoader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

  int[] bannerRes = { R.drawable.a, R.drawable.b, R.drawable.c };
  List<String> imageUrlList = new ArrayList<>();

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    BannerView bannerView = (BannerView) findViewById(R.id.banner_view);
    //Banner banner = new Banner.Builder().bind(bannerView).offline(true).offlineRes(bannerRes).build();

    imageUrlList.add("http://7xom3t.com1.z0.glb.clouddn.com/a.png");
    imageUrlList.add("http://7xom3t.com1.z0.glb.clouddn.com/b.jpg");
    imageUrlList.add("http://7xom3t.com1.z0.glb.clouddn.com/c.jpg");

    Banner banner = new Banner.Builder().bind(bannerView)
        .onlineRes(imageUrlList)
        .engine(ImageLoader.GLIDE)
        .build();
    banner.show();
  }
}
