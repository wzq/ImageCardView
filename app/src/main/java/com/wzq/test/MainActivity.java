package com.wzq.test;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.wzq.cardview.ImageCardView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageCardView img1 = findViewById(R.id.img1);
        ImageCardView img2 = findViewById(R.id.img2);
        img1.getMainImage().setImageResource(R.mipmap.p1);
        img2.getMainImage().setImageResource(R.mipmap.p2);
        img2.setTitle("test image card view, test image card view, test image card view");
    }
}
