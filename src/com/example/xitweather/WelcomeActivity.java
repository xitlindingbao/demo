package com.example.xitweather;

import com.example.util.SPUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;

public class WelcomeActivity extends Activity {

	private RelativeLayout rl_root;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		init();
	}

	private void init() {
		rl_root = (RelativeLayout) findViewById(R.id.rl_root);
		// ��ת
		RotateAnimation rotateAnimation = new RotateAnimation(0, 360,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		rotateAnimation.setDuration(2000);
		rotateAnimation.setFillAfter(true);
		// ����
		ScaleAnimation scaleAnimation = new ScaleAnimation(0, 1, 0, 1,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		scaleAnimation.setDuration(2000);
		scaleAnimation.setFillAfter(true);
		// ͸����
		AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
		alphaAnimation.setDuration(2000);
		alphaAnimation.setFillAfter(true);
		// ��Ӷ���
		AnimationSet animationSet = new AnimationSet(false);
		animationSet.addAnimation(rotateAnimation);
		animationSet.addAnimation(scaleAnimation);
		animationSet.addAnimation(alphaAnimation);
		rl_root.startAnimation(animationSet);

		// ���������¼�
		animationSet.setAnimationListener(new MyAnimation());
	}

	class MyAnimation implements AnimationListener {

		@Override
		public void onAnimationEnd(Animation animation) {
			// �Ƿ��Ѿ�����������棨Ĭ��û�н������
			boolean isEnterMain = SPUtil.getInstance(WelcomeActivity.this)
					.getboolean("isEnterMain", false);

			Log.e("TAG", "isEnterMain" + isEnterMain);
			if (isEnterMain) {
				// ����������뵽������
				startActivity(new Intent(WelcomeActivity.this,
						MainActivity.class));
				Log.e("TAG", "Main");
			} else {
				// û������������򵼽���
				Log.e("TAG", "guide");
				startActivity(new Intent(WelcomeActivity.this,
						GuideActivity.class));
			}
			finish();
		}

		@Override
		public void onAnimationRepeat(Animation arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onAnimationStart(Animation arg0) {
			// TODO Auto-generated method stub

		}
	}
}
