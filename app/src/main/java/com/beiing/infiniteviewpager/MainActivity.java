package com.beiing.infiniteviewpager;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import jackwharton_salvage.RecyclingPagerAdapter;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
//    private List<View> imageViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
//        initImages();

        RecyclePagerAdapter recyclePagerAdapter = new RecyclePagerAdapter();
        viewPager.setAdapter(recyclePagerAdapter);
        viewPager.setCurrentItem(recyclePagerAdapter.getRealCount() * 50 -
                (recyclePagerAdapter.getRealCount() * 50 % recyclePagerAdapter.getRealCount()));
    }

    private class RecyclePagerAdapter extends RecyclingPagerAdapter{
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
            return Data.imageUrls.size();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup container) {
            String url = Data.imageUrls.get(getPosition(position));
            if(convertView == null){
                convertView = new ImageView(MainActivity.this);
                ((ImageView)convertView).setScaleType(ImageView.ScaleType.CENTER_CROP);
                Glide.with(MainActivity.this).load(url).into((ImageView) convertView);
            } else {
                Glide.with(MainActivity.this).load(url).into((ImageView) convertView);
            }
            return convertView;
        }
    }

}
