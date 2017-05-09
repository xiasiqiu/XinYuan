package com.xinyuan.xyshop.ui.welcome;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.github.paolorotolo.appintro.AppIntro;
import com.xinyuan.xyshop.MainActivity;
import com.xinyuan.xyshop.R;

public class GuideActivity extends AppIntro {


	@Override
	public void init(Bundle savedInstanceState) {
		addSlide(new FirstSlide(), getApplicationContext());
		addSlide(new SecondSlide(), getApplicationContext());
		addSlide(new ThirdSlide(), getApplicationContext());

		// You can override bar/separator color if you want.

		setBarColor(getResources().getColor(R.color.colorPrimary));
		setSeparatorColor(getResources().getColor(R.color.colorPrimaryDark));

		// You can also hide Skip button
		showSkipButton(true);


	}

	@Override
	public void onSkipPressed() {
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
		finish();
	}

	@Override
	public void onDonePressed() {
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
		finish();
	}

}
