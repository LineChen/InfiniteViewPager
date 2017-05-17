package com.beiing.infiniteviewpager;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import jackwharton_salvage.RecyclingPagerAdapter;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private List<View> imageViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        initImages();

        RecyclePagerAdapter recyclePagerAdapter = new RecyclePagerAdapter();
        viewPager.setAdapter(recyclePagerAdapter);
        viewPager.setCurrentItem(recyclePagerAdapter.getRealCount() * 50 -
                (recyclePagerAdapter.getRealCount() * 50 % recyclePagerAdapter.getRealCount()));
    }

    class RecyclePagerAdapter extends RecyclingPagerAdapter{
        /**
         * get really position
         *
         * @param position
         * @return
         */
        public int getPosition(int position) {
            return position % getRealCount();
        }

        @Override
        public int getCount() {
            return getRealCount() * 100;
        }

        public int getRealCount() {
            return imageViews.size();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup container) {
            return imageViews.get(getPosition(position));
        }
    }


    private void initImages() {
        imageViews = new ArrayList<>();
        for (String url : Data.imageUrls) {
            ImageView imageView = new ImageView(this);
            Glide.with(this).load(url).into(imageView);
            imageViews.add(imageView);
        }
    }

}
