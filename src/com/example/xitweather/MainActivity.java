package com.example.xitweather;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

public class MainActivity extends FragmentActivity {

	private Button homep_menu;
	private Button homep_refresh;
	private SlidingMenu sm;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.homepager_activity_main);
		initviews();
		initSlidingMenu();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private void initviews() {
		homep_menu = (Button) findViewById(R.id.homep_menu);
		homep_refresh = (Button) findViewById(R.id.homep_refresh);
	}

	private void initSlidingMenu() {
		sm = new SlidingMenu(this);// 创建侧边栏
		// 设置菜单模式，LEFT(仅左侧边栏) RIGHT(仅右侧边栏)
		// LEFT(左侧边栏)
		sm.setMode(SlidingMenu.LEFT);
		sm.setMenu(R.layout.slide_menu_left_frame);// 设置第一个（左）侧边栏

		// 设置第二个(右)侧边栏,若设置LEFT_RIGHT模式使用该方法设置右侧边栏
		// slidingMenu.setSecondaryMenu(R.layout.navigation_layout);
		// 将侧边栏粘连到Activity上
		sm.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
		// 设置侧边栏阴影大小
		sm.setShadowWidth(10);
		sm.setBehindOffsetRes(R.dimen.sliding_menu_offset);
		// 全屏模式，全屏滑动都可打开
		sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		// getSupportFragmentManager().beginTransaction()
		// .replace(R.id.slide_left, new SlidingLeftMenuFragment())
		// .commit();
	}

	private void createSlidingMenu() {
		// 1、初始化SlidingMenu
		SlidingMenu sm = new SlidingMenu(this);
		// 2、设置SlidingMenu阴影
		// sm.setShadowWidthRes(R.dimen.shadow_width);
		// // 阴影drawable
		// sm.setShadowDrawable(R.drawable.shadow);
		// 是否有阴影
		// sm.setShadowDrawable(true);

		// 3、设置SlidingMenu拉开后离边框距离
		sm.setBehindOffsetRes(R.dimen.sliding_menu_offset);

		// 4、设置渐变:是否有渐变 ,渐变比率
		sm.setFadeEnabled(true);
		sm.setFadeDegree(0.35f);

		// 5、设置SlidingMenu布局
		// this.setBehindContentView(R.layout.slide_menu_left_frame);
		this.getSupportFragmentManager().beginTransaction()
				.replace(R.id.slide_left, new SlidingLeftMenuFragment())
				.commit();
		// 6、设置模式
		sm.setMode(SlidingMenu.LEFT);

		// 7、设置划动模式：全屏
		/*
		 * SlidingMenu.setTouchModeAbove().其中一共包含三中手势模式：
		 * TOUCHMODE_FULLSCREEN：全屏模式，在正文布局中通过手势也可以打开SlidingMenu
		 * TOUCHMODE_MARGIN：边缘模式，在正文布局的边缘处通过手势可以找开SlidingMenu
		 * TOUCHMODE_NONE：自然是不能通过手势打开SlidingMenu了
		 */
		sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);

		// 8、设置SldingMenu自动判断当前是打开还是关闭：
		sm.toggle();

		// 9、设置缩放比例
		// getSlidingMenu().setBehindScrollScale((float) 0.5);
	}

	public void click(View v) {
		switch (v.getId()) {
		case R.id.homep_menu:
			sm.toggle();
			break;
		case R.id.homep_refresh:
			break;
		}
	}

}
