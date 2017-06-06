package com.beiing.infiniteviewpager;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import jackwharton_salvage.RecyclingPagerAdapter;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private List<View> imageViews;
    private int maxCount = 1000;
    private int curPosition = maxCount / 2;
    private int start = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager) findViewById(R.id.view_pager);

        RecyclePagerAdapter recyclePagerAdapter = new RecyclePagerAdapter();
        viewPager.setAdapter(recyclePagerAdapter);
        viewPager.setCurrentItem(curPosition);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //当前滚动的位置
            }

            @Override
            public void onPageSelected(int position) {
                //滚动结束时的位置

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
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
            return maxCount;
        }

        public int getRealCount() {
            return Data.imageUrls.size();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup container) {
            Log.e("====", "getView:" + position + "convertView == null:" + (convertView == null));
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
