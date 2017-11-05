package com.example.xitweather;

import java.util.ArrayList;

import com.example.util.SPUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;

public class GuideActivity extends Activity {
	private ViewPager viewpager;// 三个滑动的页面
	private ArrayList<ImageView> imageViews;// 三个滑动页面对应的图片集合
	private Button btn_enterMain;// 开始体验按钮

	private LinearLayout ll_point_group;// 下方的三个显示圆点为
	private View red_point;// 当前页面的高亮显示圆点

	private int leftMax;// 2个原点之前的距离

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_guide);
		// 获取点的集合
		ll_point_group = (LinearLayout) findViewById(R.id.ll_point_group);
		// 获取高亮的红点
		red_point = findViewById(R.id.red_point);
		// 获取viewpager
		viewpager = (ViewPager) findViewById(R.id.viewpager);
		// 获取button(开始体验)
		btn_enterMain = (Button) findViewById(R.id.btn_enterMain);
		// 创建集合
		imageViews = new ArrayList<ImageView>();

		// 准备数据
		int[] ids = { R.drawable.guide_1, R.drawable.guide_2,
				R.drawable.guide_3, R.drawable.guide_4, R.drawable.guide_5 };
		for (int i = 0; i < ids.length; i++) {
			ImageView imageView = new ImageView(this);// 创建图片对象
			imageView.setBackgroundResource(ids[i]);// 为图片设置背景
			imageViews.add(imageView);// 添加图片
			View point = new View(this);// 添加下标点-创建点击
			LayoutParams params = new LayoutParams(10, 10);// 创建布局参数
			// 除开第0个点，其他的都要距离左边有10个像素
			if (i != 0) {
				params.leftMargin = 10;
			}

			point.setBackgroundResource(R.drawable.point_normal);// 添加背景图片

			point.setLayoutParams(params);// 设置布局参数

			ll_point_group.addView(point);// 添加指示点击

		}

		// 设置适配器
		viewpager.setAdapter(new viewpagerAdapter());

		// 监听当onLayout方法执行的时候再去
		red_point.getViewTreeObserver().addOnGlobalLayoutListener(
				new OnGlobalLayoutListener() {
					@Override
					public void onGlobalLayout() {

						// 取消注册监听--因为孩子也会调用
						red_point.getViewTreeObserver()
								.removeGlobalOnLayoutListener(this);
						// 两点间的间距
						leftMax = ll_point_group.getChildAt(1).getLeft()
								- ll_point_group.getChildAt(0).getLeft();
					}
				});

		/**
		 * 设置页面改变监听
		 */
		viewpager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				// 只有第三种页面显示进入按钮
				if (position == 4) {
					btn_enterMain.setVisibility(View.VISIBLE);
				} else {
					btn_enterMain.setVisibility(View.GONE);
				}
			}

			/*
			 * 位置 屏幕上滑动的百分比 滑动的显示
			 */
			@Override
			public void onPageScrolled(int position, float positionOffset,
					int positionOffsetPixels) {
				// 计算要滑动的距离=间距*在屏幕上滑动的百分比
				int distance = (int) (leftMax * (positionOffset + position));
				RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
						10, 10);
				params.leftMargin = distance;
				red_point.setLayoutParams(params);
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {

			}
		});
		/**
		 * 为开始体验button添加点击事件
		 */
		btn_enterMain.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// 标记为true
				SPUtil.getInstance(GuideActivity.this).put("isEnterMain", true);
				// 进入主界面
				startActivity(new Intent(GuideActivity.this, MainActivity.class));
				// 干掉自己
				finish();
			}
		});
	}

	class viewpagerAdapter extends PagerAdapter {
		@Override
		public int getCount() {
			return imageViews.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return (arg0 == arg1);
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			ImageView imageView = imageViews.get(position);
			container.addView(imageView);

			return imageView;
		}
	}
}
