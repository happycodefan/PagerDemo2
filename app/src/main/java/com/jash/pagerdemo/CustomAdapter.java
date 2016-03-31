package com.jash.pagerdemo;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.util.Pools;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by jash on 16-3-15.
 */
public class CustomAdapter extends PagerAdapter {
    private static final String TAG = CustomAdapter.class.getCanonicalName();
    private Context context;
    private List<String> list;
    /**
     * 控件池,减少view的创建,前提是布局可以复用
     */
    private Pools.Pool<View> pool;

    public CustomAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
        pool = new Pools.SimplePool<>(3);
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    /**
     * view是否展示object中的数据
     * @param view
     * @param object
     * @return
     */
    @Override
    public boolean isViewFromObject(View view, Object object) {
        Object tag = view.getTag();
        return tag.equals(object);
    }

    /**
     * 加入一个item
     * @param container ViewPager
     * @param position 位置
     * @return 为isViewFromObject的第二个参数,也是destroyItem的第三个参数
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        TextView view = (TextView) pool.acquire();
        if (view == null) {
            view = new TextView(context);
            view.setBackgroundColor(Color.RED);
            Log.d(TAG, "instantiateItem: 创建TextView");
        }
        TextView text = view;
        String s = list.get(position % list.size());
        text.setText(s);
        text.setId(position);
        container.addView(text);
        text.setTag(s);
        return s;
    }

    /**
     * 销毁一个item
     * @param container
     * @param position
     * @param object
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = container.findViewWithTag(object);
        container.removeView(view);
        pool.release(view);
    }
}
