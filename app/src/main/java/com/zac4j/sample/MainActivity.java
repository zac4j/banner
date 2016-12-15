package com.zac4j.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.zac4j.library.Banner;

public class MainActivity extends AppCompatActivity {

  int[] bannerRes = { R.drawable.a, R.drawable.b, R.drawable.c };

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    Banner bannerPager = (Banner) findViewById(R.id.banner);
    bannerPager.setData(bannerRes);
  }
}
