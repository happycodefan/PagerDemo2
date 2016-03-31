package com.jash.pagerdemo;

import android.graphics.drawable.BitmapDrawable;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private CustomFragmentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ViewPager pager = (ViewPager) findViewById(R.id.main_pager);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(String.format("第%02d页", i));
        }
        adapter = new CustomFragmentAdapter(getSupportFragmentManager(), list);
        pager.setAdapter(new CustomAdapter(this, list));

        pager.setCurrentItem(Integer.MAX_VALUE / 2 / list.size() * list.size(), false);
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            /**
             * 滚动中调用的方法
             * @param position 同时显示的两个item中,position较小的那个.
             * @param positionOffset [0,1)较大position的item出现在屏幕中的百分比.
             * @param positionOffsetPixels 较大position的item出现在屏幕中的像素
             */
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                View lt = pager.findViewById(position);
                View rt = pager.findViewById(position + 1);
                ViewCompat.setRotationY(lt, 90 * (positionOffset));
                if (rt != null) {
                    ViewCompat.setRotationY(rt, 90 * (positionOffset - 1));
                }
            }

            /**
             * 选中某个item,某个item全部显示出来
             * @param position
             */
            @Override
            public void onPageSelected(int position) {
                Toast.makeText(MainActivity.this, "当前为" + (position % 20), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        Button button = (Button) findViewById(R.id.main_btn);
        button.setOnClickListener(this);
//        pager.setAdapter(new CustomAdapter(this, list));
    }

    @Override
    public void onClick(View v) {
        List<String> list = new ArrayList<>();
        for (int i = 20; i < 40; i++) {
            list.add(String.format("第%02d页", i));
        }
        adapter.addAll(list);
    }
}
