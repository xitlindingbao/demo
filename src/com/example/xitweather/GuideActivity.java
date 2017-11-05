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
	private ViewPager viewpager;// ����������ҳ��
	private ArrayList<ImageView> imageViews;// ��������ҳ���Ӧ��ͼƬ����
	private Button btn_enterMain;// ��ʼ���鰴ť

	private LinearLayout ll_point_group;// �·���������ʾԲ��Ϊ
	private View red_point;// ��ǰҳ��ĸ�����ʾԲ��

	private int leftMax;// 2��ԭ��֮ǰ�ľ���

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_guide);
		// ��ȡ��ļ���
		ll_point_group = (LinearLayout) findViewById(R.id.ll_point_group);
		// ��ȡ�����ĺ��
		red_point = findViewById(R.id.red_point);
		// ��ȡviewpager
		viewpager = (ViewPager) findViewById(R.id.viewpager);
		// ��ȡbutton(��ʼ����)
		btn_enterMain = (Button) findViewById(R.id.btn_enterMain);
		// ��������
		imageViews = new ArrayList<ImageView>();

		// ׼������
		int[] ids = { R.drawable.guide_1, R.drawable.guide_2,
				R.drawable.guide_3, R.drawable.guide_4, R.drawable.guide_5 };
		for (int i = 0; i < ids.length; i++) {
			ImageView imageView = new ImageView(this);// ����ͼƬ����
			imageView.setBackgroundResource(ids[i]);// ΪͼƬ���ñ���
			imageViews.add(imageView);// ���ͼƬ
			View point = new View(this);// ����±��-�������
			LayoutParams params = new LayoutParams(10, 10);// �������ֲ���
			// ������0���㣬�����Ķ�Ҫ���������10������
			if (i != 0) {
				params.leftMargin = 10;
			}

			point.setBackgroundResource(R.drawable.point_normal);// ��ӱ���ͼƬ

			point.setLayoutParams(params);// ���ò��ֲ���

			ll_point_group.addView(point);// ���ָʾ���

		}

		// ����������
		viewpager.setAdapter(new viewpagerAdapter());

		// ������onLayout����ִ�е�ʱ����ȥ
		red_point.getViewTreeObserver().addOnGlobalLayoutListener(
				new OnGlobalLayoutListener() {
					@Override
					public void onGlobalLayout() {

						// ȡ��ע�����--��Ϊ����Ҳ�����
						red_point.getViewTreeObserver()
								.removeGlobalOnLayoutListener(this);
						// �����ļ��
						leftMax = ll_point_group.getChildAt(1).getLeft()
								- ll_point_group.getChildAt(0).getLeft();
					}
				});

		/**
		 * ����ҳ��ı����
		 */
		viewpager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				// ֻ�е�����ҳ����ʾ���밴ť
				if (position == 4) {
					btn_enterMain.setVisibility(View.VISIBLE);
				} else {
					btn_enterMain.setVisibility(View.GONE);
				}
			}

			/*
			 * λ�� ��Ļ�ϻ����İٷֱ� ��������ʾ
			 */
			@Override
			public void onPageScrolled(int position, float positionOffset,
					int positionOffsetPixels) {
				// ����Ҫ�����ľ���=���*����Ļ�ϻ����İٷֱ�
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
		 * Ϊ��ʼ����button��ӵ���¼�
		 */
		btn_enterMain.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// ���Ϊtrue
				SPUtil.getInstance(GuideActivity.this).put("isEnterMain", true);
				// ����������
				startActivity(new Intent(GuideActivity.this, MainActivity.class));
				// �ɵ��Լ�
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
