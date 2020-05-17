package com.example.ordering;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class About extends Activity{
	RelativeLayout rl;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about);
		Bundle extras=getIntent().getExtras();
		String pass_cata=extras.getString("val1");
	rl=(RelativeLayout)findViewById(R.id.contents);
	if(pass_cata.equals("about")){
		rl.setBackgroundResource(R.drawable.aboutus);
		}
	else
	rl.setBackgroundResource(R.drawable.howto);
	
	
	}
}
