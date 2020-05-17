package com.example.ordering;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;




import android.annotation.SuppressLint;
import android.app.Activity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("UseValueOf")
public class MainActivity extends Activity {
	int forwhat=1;
	DatabaseHelper db;
	int rand;
	int soundonoff=0;
	int vibrationonoff=0;
	CountDownTimer ct;
	Random r=new Random();
	int fire;
	int level=1;
	int achivement_count=0;
	int level_loop=5;
	int life_counter=5;
	int autoanim=0;
	int timeduration=5000;
	int start_time=1000;
	int randomnumber=r.nextInt(4-1)+1;
	ImageView one, two, three, four;
	ImageView five, six, seven, eight;
	ImageView nine, ten, eleven, twelve;
	int correct=0;
	int gamescine2=r.nextInt(2-0)+1;
	ImageView thirtin, fourtie, fiftine, sixtine;
	ImageView seventine, eightine, nintine, twenty;
	ImageView vibration,sound;
	MediaPlayer actionsound;
	MediaPlayer acivementsound;
	TextView notification,collected,life,levels;
	int continue_time=0;
	int i;
	int i_am_at_row=0;
	int firstnumber=1;
	ArrayList<Integer> list;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		init();
	}

/*
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		//ct.start();
		timeduration=continue_time;

		level--;
		life_counter++;
		load_four_pause();


	}
*/
	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		ct.cancel();
		continue_time=timeduration;

		sound.setBackgroundResource(R.drawable.off);
		vibration.setBackgroundResource(R.drawable.voff);
		vibrationonoff=1;
		soundonoff=1;
		finish();
		//this.onBackPressed();
		//Toast.makeText(getApplicationContext(), "pausedddd", Toast.LENGTH_LONG).show();

	}

	public void init(){
		setContentView(R.layout.activity_main);
		Bundle extras=getIntent().getExtras();
		level=1;
		String pass_cata=extras.getString("sound");
		String pass_cata2=extras.getString("vibration");
		level_loop=level*4+1;
		one=(ImageView)findViewById(R.id.one);
		two=(ImageView)findViewById(R.id.two);
		three=(ImageView)findViewById(R.id.three);
		four=(ImageView)findViewById(R.id.four);
		five=(ImageView)findViewById(R.id.five);
		six=(ImageView)findViewById(R.id.six);
		seven=(ImageView)findViewById(R.id.seven);
		eight=(ImageView)findViewById(R.id.eight);
		nine=(ImageView)findViewById(R.id.nine);
		ten=(ImageView)findViewById(R.id.ten);
		eleven=(ImageView)findViewById(R.id.eleven);
		twelve=(ImageView)findViewById(R.id.twelve);
		thirtin=(ImageView)findViewById(R.id.thirtine);
		fourtie=(ImageView)findViewById(R.id.fourtine);
		fiftine=(ImageView)findViewById(R.id.fiftine);
		sixtine=(ImageView)findViewById(R.id.sixtine);
		seventine=(ImageView)findViewById(R.id.seventine);
		eightine=(ImageView)findViewById(R.id.eightine);
		nintine=(ImageView)findViewById(R.id.nintine);
		twenty=(ImageView)findViewById(R.id.twenty);
		notification=(TextView)findViewById(R.id.notify_player);
		collected=(TextView)findViewById(R.id.collected);
		life=(TextView)findViewById(R.id.life);
		levels=(TextView)findViewById(R.id.level);
		vibration=(ImageView)findViewById(R.id.vibration);
		sound=(ImageView)findViewById(R.id.sound);
		collected.setText("No Correct Targets");
		notification.setTypeface(null, Typeface.BOLD_ITALIC);
		notification.setTextColor(Color.parseColor("#a77d46"));
		collected.setTypeface(null, Typeface.BOLD_ITALIC);
		collected.setTextColor(Color.parseColor("#a77d46"));
		levels.setTypeface(null, Typeface.ITALIC);
		levels.setTextColor(Color.parseColor("#a77d46"));
		life.setTypeface(null, Typeface.ITALIC);
		life.setTextColor(Color.parseColor("#a77d46"));
		collected.setText(correct+" Correct Target");
		if (pass_cata.contains("off")){
			sound.setBackgroundResource(R.drawable.off);
			soundonoff=1;
		}
		if (pass_cata2.contains("off")){
			vibration.setBackgroundResource(R.drawable.voff);
			vibrationonoff=1;
		}
		gamescne3();
		gamescne2();
		gamescne();
		starttimer();

		life.setText("Life "+life_counter);

		scaleanim(one);
		scaleanim(two);
		scaleanim(three);
		scaleanim(four);

		vibration.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if(vibrationonoff==0){
					vibration.setBackgroundResource(R.drawable.voff);
					vibrationonoff=1;
				}
				else if(vibrationonoff==1){
					vibration.setBackgroundResource(R.drawable.von);
					vibrationonoff=0;
				}

			}
		});

		sound.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if(soundonoff==0){
					sound.setBackgroundResource(R.drawable.off);
					soundonoff=1;
				}
				else if(soundonoff==1){
					sound.setBackgroundResource(R.drawable.on);
					soundonoff=0;
				}
			}
		});

		one.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {


				i_am_at_row++;
				if(list.get(0)==firstnumber){
					scaldowneanim(one);
					gamesuccess();

					autoanim();

				}
				else
					gameerror();


			}
		});
		two.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				i_am_at_row++;
				if(list.get(1)==firstnumber){
					scaldowneanim(two);
					gamesuccess();
					autoanim();
				}
				else
					gameerror();


			}
		});
		three.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				i_am_at_row++;
				if(list.get(2)==firstnumber){
					scaldowneanim(three);
					gamesuccess();
					autoanim();
				}
				else
					gameerror();

			}
		});
		four.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				i_am_at_row++;
				if(list.get(3)==firstnumber){
					scaldowneanim(four);
					gamesuccess();
					autoanim();
				}
				else
					gameerror();

			}
		});

		five.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				i_am_at_row++;
				if(list.get(4)==firstnumber){
					scaldowneanim(five);
					gamesuccess();
					autoanim();
				}
				else
					gameerror();

			}
		});
		six.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				i_am_at_row++;
				if(list.get(5)==firstnumber){
					scaldowneanim(six);
					gamesuccess();
					autoanim();
				}
				else
					gameerror();

			}
		});
		seven.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				i_am_at_row++;
				if(list.get(6)==firstnumber){
					scaldowneanim(seven);
					gamesuccess();
					autoanim();
				}
				else
					gameerror();

			}
		});
		eight.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				i_am_at_row++;
				if(list.get(7)==firstnumber){
					scaldowneanim(eight);
					gamesuccess();
					autoanim();
				}
				else
					gameerror();

			}
		});
		nine.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				i_am_at_row++;
				if(list.get(8)==firstnumber){
					scaldowneanim(nine);
					gamesuccess();
					autoanim();
				}
				else
					gameerror();

			}
		});
		ten.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				i_am_at_row++;
				if(list.get(9)==firstnumber){
					scaldowneanim(ten);
					gamesuccess();
					autoanim();
				}
				else
					gameerror();

			}
		});
		eleven.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				i_am_at_row++;
				if(list.get(10)==firstnumber){
					scaldowneanim(eleven);
					gamesuccess();
					autoanim();
				}
				else
					gameerror();

			}
		});
		twelve.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				i_am_at_row++;
				if(list.get(11)==firstnumber){
					scaldowneanim(twelve);
					gamesuccess();
					autoanim();
				}
				else
					gameerror();

			}
		});
		thirtin.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				i_am_at_row++;
				if(list.get(12)==firstnumber){
					scaldowneanim(thirtin);
					gamesuccess();
					autoanim();
				}
				else
					gameerror();

			}
		});
		fourtie.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				i_am_at_row++;
				if(list.get(13)==firstnumber){
					scaldowneanim(fourtie);
					gamesuccess();
					autoanim();
				}
				else
					gameerror();

			}
		});

		fiftine.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				i_am_at_row++;
				if(list.get(14)==firstnumber){
					scaldowneanim(fiftine);
					gamesuccess();
					autoanim();
				}
				else
					gameerror();

			}
		});
		sixtine.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				i_am_at_row++;
				if(list.get(15)==firstnumber){
					scaldowneanim(sixtine);
					gamesuccess();
					autoanim();
				}
				else
					gameerror();

			}
		});

		seventine.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				i_am_at_row++;
				if(list.get(16)==firstnumber){
					scaldowneanim(seventine);
					gamesuccess();
					autoanim();
				}
				else
					gameerror();

			}
		});

		eightine.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				i_am_at_row++;
				if(list.get(17)==firstnumber){
					scaldowneanim(eightine);
					gamesuccess();
					autoanim();
				}
				else
					gameerror();

			}
		});

		nintine.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				i_am_at_row++;
				if(list.get(18)==firstnumber){
					scaldowneanim(nintine);
					gamesuccess();
					autoanim();



				}
				else
					gameerror();

			}
		});


		twenty.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				i_am_at_row++;
				if(list.get(19)==firstnumber){
					scaldowneanim(twenty);
					gamesuccess();
					autoanim();
				}
				else
					gameerror();

			}
		});


	}

	public void starttimer(){
		levels.setText("Level "+level);
		ct=	new CountDownTimer(timeduration,start_time) {

			@Override
			public void onTick(long millisUntilFinished) {
				// TODO Auto-generated method stub

				notification.setText("Seconds Remaining: "+ millisUntilFinished/ 1000);


			}

			@Override
			public void onFinish() {

				scaleanim(one);
				scaleanim(two);
				scaleanim(three);
				scaleanim(four);
				scaleanim(five);
				scaleanim(six);
				scaleanim(seven);
				scaleanim(eight);
				scaleanim(nine);
				scaleanim(ten);
				scaleanim(eleven);
				scaleanim(twelve);
				scaleanim(thirtin);
				scaleanim(fourtie);
				scaleanim(fiftine);
				scaleanim(sixtine);
				scaleanim(seventine);
				scaleanim(eightine);
				scaleanim(nintine);
				scaleanim(twenty);
				gamescine2=r.nextInt(2-0)+1;
				if(level==1 && correct!=4){
					gameerror();
				}

				if(level==2 && correct!=8){
					gameerror();
				}

				if(level==3 && correct!=12){
					gameerror();
				}

				if(level==4 && correct!=16){
					gameerror();
				}

				if(level==5 && correct!=20){
					gameerror();
				}


				firstnumber=1;


				gamescne3();
				gamescne2();
				gamescne();
				starttimer();

				// TODO Auto-generated method stub

			}
		}.start();


	}


	public void acivements(){
		achivement_count++;
		if(soundonoff==0){
			if(acivementsound!=null){

				acivementsound.stop();
				acivementsound.release();
				acivementsound=null;

			}
			acivementsound=MediaPlayer.create(this, R.raw.achivement);
			acivementsound.start();

		}}


	public void gameerror() {

		life_counter--;
		life.setText("Life " + life_counter);
		if (life_counter == 0) {
			db = new DatabaseHelper(this);
			Cursor cursor = db.alldata();
			if (cursor.getCount() != 0) {
				while (cursor.moveToNext()) {
					//Toast.makeText(getApplicationContext(), "First Name:"+cursor.getString(0), Toast.LENGTH_SHORT).show();
					//Toast.makeText(getApplicationContext(), "Last Name:"+cursor.getString(1), Toast.LENGTH_SHORT).show();
					//Toast.makeText(getApplicationContext(), "Department:"+cursor.getString(2), Toast.LENGTH_SHORT).show();

					String levels = cursor.getString(0);
					int levelcount = Integer.valueOf(levels);
					if (levelcount < level) {


						db.update(level, correct);
						Toast.makeText(getApplicationContext(), "Best Score  Updated", Toast.LENGTH_LONG).show();


					}

				}
			} else {

				Boolean insert = db.insert(level, correct);
				if (insert == true) {

					Toast.makeText(getApplicationContext(), "Best Score Added", Toast.LENGTH_SHORT).show();

				}

			}
			//

			sound.setBackgroundResource(R.drawable.off);
			vibration.setBackgroundResource(R.drawable.voff);
			vibrationonoff=1;
			soundonoff=1;
			finish();
			onBackPressed();
			Toast.makeText(getApplicationContext(), "Game Over", Toast.LENGTH_LONG).show();
		}

		if (vibrationonoff == 0) {

			Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

			v.vibrate(100);
		}

	}

	public void load(){
		gamescine2=r.nextInt(2-0)+1;
		ct.cancel();
		scaleanim(one);
		scaleanim(two);
		scaleanim(three);
		scaleanim(four);
		scaleanim(five);
		scaleanim(six);
		scaleanim(seven);
		scaleanim(eight);
		scaleanim(nine);
		scaleanim(ten);
		scaleanim(eleven);
		scaleanim(twelve);
		scaleanim(thirtin);
		scaleanim(fourtie);
		scaleanim(fiftine);
		scaleanim(sixtine);
		scaleanim(seventine);
		scaleanim(eightine);
		scaleanim(nintine);
		scaleanim(twenty);

			//gamescine2=2;

		if(level==1 && correct!=4){
			gameerror();

		}

		else  if(level==2 && correct!=8){
			gameerror();
			Toast.makeText(getApplicationContext(), level+" and "+ correct, Toast.LENGTH_SHORT).show();

			correct=0;
		}
		else if(level==3 && correct!=12){
			gameerror();

			Toast.makeText(getApplicationContext(), level+" and "+ correct, Toast.LENGTH_SHORT).show();

		}

		else if(level==4 && correct!=16){
			gameerror();

			Toast.makeText(getApplicationContext(), level+" and "+ correct, Toast.LENGTH_SHORT).show();

		}

		else if(level==5 && correct!=20){
			gameerror();

			Toast.makeText(getApplicationContext(), level+" and "+ correct, Toast.LENGTH_SHORT).show();

		}
		else {

			acivements();

		}
		correct=0;
		level+=1;
		if(level<=5){

			timeduration+=6000;

		}
		else{
			timeduration-=1000;
		}
		level_loop+=4;
		firstnumber=1;
		gamescne3();
		gamescne2();
		gamescne();

		ct.cancel();
		starttimer();

	}

	public void autoanim(){
		autoanim++;
		if(autoanim==4 && level==1){load();autoanim=0;}
		else if (autoanim==8 && level==2) {load();autoanim=0;}
		else if (autoanim==12 && level==3) {load();autoanim=0;}
		else if(autoanim==16 && level==4){load();autoanim=0;}
		else if (autoanim==20 && level==5){
			load();
			autoanim=0;
		}

	}
	public void gamesuccess(){
		if(soundonoff==0){
			if(actionsound!=null){

				actionsound.stop();
				actionsound.release();
				actionsound=null;

			}
			actionsound=MediaPlayer.create(this, R.raw.target);
			actionsound.start();
		}
		correct++;
		firstnumber++;
		collected.setText(correct+" Correct Target");

	}

	public void scaleanim(View v){

		ScaleAnimation fade_in=new ScaleAnimation(0f, 1f,0f,1f, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
		fade_in.setDuration(670);
		fade_in.setFillAfter(true);
		v.startAnimation(fade_in);
		v.setClickable(true);
	}
	public void scaldowneanim(View v){

		ScaleAnimation fade_out=new ScaleAnimation(1f, 0f,1f,0f, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
		fade_out.setDuration(300);
		fade_out.setFillAfter(true);
		v.startAnimation(fade_out);
		v.setClickable(false);
	}



	public void gamescne(){
		if(gamescine2==0){

			list = new ArrayList<Integer>();
			for (i=1; i<level_loop; i++) {
				list.add(new Integer(i));

			}
			Collections.shuffle(list);

			int findnumbers=level_loop-1;
			for (int i=0; i<findnumbers; i++) {


				if(list.get(i)==1){

					if(level==1 || level ==2 || level==3 || level==4 || level==5){
						if(forwhat==1){
							one.setBackgroundResource(R.drawable.one);
						}
						if(forwhat==2){
							two.setBackgroundResource(R.drawable.one);
						}
						if(forwhat==3){
							three.setBackgroundResource(R.drawable.one);
						}
						if(forwhat==4){
							four.setBackgroundResource(R.drawable.one);
						}
					}
					if( level ==2 || level==3 || level==4 || level==5){

						if(forwhat==5){
							five.setBackgroundResource(R.drawable.one);
						}
						if(forwhat==6){
							six.setBackgroundResource(R.drawable.one);
						}
						if(forwhat==7){
							seven.setBackgroundResource(R.drawable.one);
						}
						if(forwhat==8){
							eight.setBackgroundResource(R.drawable.one);
						}

					}if(level==3 || level==4 || level==5){

						if(forwhat==9){
							nine.setBackgroundResource(R.drawable.one);
						}
						if(forwhat==10){
							ten.setBackgroundResource(R.drawable.one);
						}
						if(forwhat==11){
							eleven.setBackgroundResource(R.drawable.one);
						}
						if(forwhat==12){
							twelve.setBackgroundResource(R.drawable.one);
						}

					}


					if( level==4 || level==5){

						if(forwhat==13){
							thirtin.setBackgroundResource(R.drawable.one);
						}
						if(forwhat==14){
							fourtie.setBackgroundResource(R.drawable.one);
						}
						if(forwhat==15){
							fiftine.setBackgroundResource(R.drawable.one);
						}
						if(forwhat==16){
							sixtine.setBackgroundResource(R.drawable.one);
						}

					}

					if(  level==5){

						if(forwhat==17){
							seventine.setBackgroundResource(R.drawable.one);
						}
						if(forwhat==18){
							eightine.setBackgroundResource(R.drawable.one);
						}
						if(forwhat==19){
							nintine.setBackgroundResource(R.drawable.one);
						}
						if(forwhat==20){
							twenty.setBackgroundResource(R.drawable.one);
						}

					}










				}
				if(list.get(i)==2){

					if(level==1 || level ==2 || level==3 || level==4 || level==5){
						if(forwhat==1){
							one.setBackgroundResource(R.drawable.two);
						}
						if(forwhat==2){
							two.setBackgroundResource(R.drawable.two);
						}
						if(forwhat==3){
							three.setBackgroundResource(R.drawable.two);
						}
						if(forwhat==4){
							four.setBackgroundResource(R.drawable.two);
						}}






					if(level ==2 || level==3 || level==4 || level==5){

						if(forwhat==5){
							five.setBackgroundResource(R.drawable.two);
						}
						if(forwhat==6){
							six.setBackgroundResource(R.drawable.two);
						}
						if(forwhat==7){
							seven.setBackgroundResource(R.drawable.two);
						}
						if(forwhat==8){
							eight.setBackgroundResource(R.drawable.two);
						}

					}if( level==3 || level==4 || level==5){

						if(forwhat==9){
							nine.setBackgroundResource(R.drawable.two);
						}
						if(forwhat==10){
							ten.setBackgroundResource(R.drawable.two);
						}
						if(forwhat==11){
							eleven.setBackgroundResource(R.drawable.two);
						}
						if(forwhat==12){
							twelve.setBackgroundResource(R.drawable.two);
						}

					}

					if( level==4 || level==5){

						if(forwhat==13){
							thirtin.setBackgroundResource(R.drawable.two);
						}
						if(forwhat==14){
							fourtie.setBackgroundResource(R.drawable.two);
						}
						if(forwhat==15){
							fiftine.setBackgroundResource(R.drawable.two);
						}
						if(forwhat==16){
							sixtine.setBackgroundResource(R.drawable.two);
						}

					}

					if(  level==5){

						if(forwhat==17){
							seventine.setBackgroundResource(R.drawable.two);
						}
						if(forwhat==18){
							eightine.setBackgroundResource(R.drawable.two);
						}
						if(forwhat==19){
							nintine.setBackgroundResource(R.drawable.two);
						}
						if(forwhat==20){
							twenty.setBackgroundResource(R.drawable.two);
						}

					}














				}
				if(list.get(i)==3){

					if(level==1 || level ==2 || level==3 || level==4 || level==5){
						if(forwhat==1){
							one.setBackgroundResource(R.drawable.three);
						}
						if(forwhat==2){
							two.setBackgroundResource(R.drawable.three);
						}
						if(forwhat==3){
							three.setBackgroundResource(R.drawable.three);
						}
						if(forwhat==4){
							four.setBackgroundResource(R.drawable.three);
						}}



					if( level ==2 || level==3 || level==4 || level==5){

						if(forwhat==5){
							five.setBackgroundResource(R.drawable.three);
						}
						if(forwhat==6){
							six.setBackgroundResource(R.drawable.three);
						}
						if(forwhat==7){
							seven.setBackgroundResource(R.drawable.three);
						}
						if(forwhat==8){
							eight.setBackgroundResource(R.drawable.three);
						}

					}if( level==3 || level==4 || level==5){

						if(forwhat==9){
							nine.setBackgroundResource(R.drawable.three);
						}
						if(forwhat==10){
							ten.setBackgroundResource(R.drawable.three);
						}
						if(forwhat==11){
							eleven.setBackgroundResource(R.drawable.three);
						}
						if(forwhat==12){
							twelve.setBackgroundResource(R.drawable.three);
						}

					}


					if( level==4 || level==5){

						if(forwhat==13){
							thirtin.setBackgroundResource(R.drawable.three);
						}
						if(forwhat==14){
							fourtie.setBackgroundResource(R.drawable.three);
						}
						if(forwhat==15){
							fiftine.setBackgroundResource(R.drawable.three);
						}
						if(forwhat==16){
							sixtine.setBackgroundResource(R.drawable.three);
						}

					}

					if(  level==5){

						if(forwhat==17){
							seventine.setBackgroundResource(R.drawable.three);
						}
						if(forwhat==18){
							eightine.setBackgroundResource(R.drawable.three);
						}
						if(forwhat==19){
							nintine.setBackgroundResource(R.drawable.three);
						}
						if(forwhat==20){
							twenty.setBackgroundResource(R.drawable.three);
						}

					}










				}








				if(list.get(i)==4){
					if(level==1 || level ==2 || level==3 || level==4 || level==5){
						if(forwhat==1){
							one.setBackgroundResource(R.drawable.four);
						}
						if(forwhat==2){
							two.setBackgroundResource(R.drawable.four);
						}
						if(forwhat==3){
							three.setBackgroundResource(R.drawable.four);
						}
						if(forwhat==4){
							four.setBackgroundResource(R.drawable.four);
						}
					}



					if( level ==2 || level==3 || level==4 || level==5){

						if(forwhat==5){
							five.setBackgroundResource(R.drawable.four);
						}
						if(forwhat==6){
							six.setBackgroundResource(R.drawable.four);
						}
						if(forwhat==7){
							seven.setBackgroundResource(R.drawable.four);
						}
						if(forwhat==8){
							eight.setBackgroundResource(R.drawable.four);
						}

					}if(level==3 || level==4 || level==5){

						if(forwhat==9){
							nine.setBackgroundResource(R.drawable.four);
						}
						if(forwhat==10){
							ten.setBackgroundResource(R.drawable.four);
						}
						if(forwhat==11){
							eleven.setBackgroundResource(R.drawable.four);
						}
						if(forwhat==12){
							twelve.setBackgroundResource(R.drawable.four);
						}

					}

					if( level==4 || level==5){

						if(forwhat==13){
							thirtin.setBackgroundResource(R.drawable.four);
						}
						if(forwhat==14){
							fourtie.setBackgroundResource(R.drawable.four);
						}
						if(forwhat==15){
							fiftine.setBackgroundResource(R.drawable.four);
						}
						if(forwhat==16){
							sixtine.setBackgroundResource(R.drawable.four);
						}

					}

					if(  level==5){

						if(forwhat==17){
							seventine.setBackgroundResource(R.drawable.four);
						}
						if(forwhat==18){
							eightine.setBackgroundResource(R.drawable.four);
						}
						if(forwhat==19){
							nintine.setBackgroundResource(R.drawable.four);
						}
						if(forwhat==20){
							twenty.setBackgroundResource(R.drawable.four);
						}

					}



				}





				if(list.get(i)==5){
					if(level==1 || level ==2 || level==3 || level==4 || level==5){
						if(forwhat==1){
							one.setBackgroundResource(R.drawable.five);
						}
						if(forwhat==2){
							two.setBackgroundResource(R.drawable.five);
						}
						if(forwhat==3){
							three.setBackgroundResource(R.drawable.five);
						}
						if(forwhat==4){
							four.setBackgroundResource(R.drawable.five);
						}
					}



					if(level ==2 || level==3 || level==4 || level==5){

						if(forwhat==5){
							five.setBackgroundResource(R.drawable.five);
						}
						if(forwhat==6){
							six.setBackgroundResource(R.drawable.five);
						}
						if(forwhat==7){
							seven.setBackgroundResource(R.drawable.five);
						}
						if(forwhat==8){
							eight.setBackgroundResource(R.drawable.five);
						}

					}if( level==3 || level==4 || level==5){

						if(forwhat==9){
							nine.setBackgroundResource(R.drawable.five);
						}
						if(forwhat==10){
							ten.setBackgroundResource(R.drawable.five);
						}
						if(forwhat==11){
							eleven.setBackgroundResource(R.drawable.five);
						}
						if(forwhat==12){
							twelve.setBackgroundResource(R.drawable.five);
						}

					}


					if( level==4 || level==5){

						if(forwhat==13){
							thirtin.setBackgroundResource(R.drawable.five);
						}
						if(forwhat==14){
							fourtie.setBackgroundResource(R.drawable.five);
						}
						if(forwhat==15){
							fiftine.setBackgroundResource(R.drawable.five);
						}
						if(forwhat==16){
							sixtine.setBackgroundResource(R.drawable.five);
						}

					}

					if(  level==5){

						if(forwhat==17){
							seventine.setBackgroundResource(R.drawable.five);
						}
						if(forwhat==18){
							eightine.setBackgroundResource(R.drawable.five);
						}
						if(forwhat==19){
							nintine.setBackgroundResource(R.drawable.five);
						}
						if(forwhat==20){
							twenty.setBackgroundResource(R.drawable.five);
						}

					}




				}






				if(list.get(i)==6){
					if(level==1 || level ==2 || level==3 || level==4 || level==5){
						if(forwhat==1){
							one.setBackgroundResource(R.drawable.six);
						}
						if(forwhat==2){
							two.setBackgroundResource(R.drawable.six);
						}
						if(forwhat==3){
							three.setBackgroundResource(R.drawable.six);
						}
						if(forwhat==4){
							four.setBackgroundResource(R.drawable.six);
						}
					}



					if(level ==2 || level==3 || level==4 || level==5){

						if(forwhat==5){
							five.setBackgroundResource(R.drawable.six);
						}
						if(forwhat==6){
							six.setBackgroundResource(R.drawable.six);
						}
						if(forwhat==7){
							seven.setBackgroundResource(R.drawable.six);
						}
						if(forwhat==8){
							eight.setBackgroundResource(R.drawable.six);
						}

					}if( level==3 || level==4 || level==5){

						if(forwhat==9){
							nine.setBackgroundResource(R.drawable.six);
						}
						if(forwhat==10){
							ten.setBackgroundResource(R.drawable.six);
						}
						if(forwhat==11){
							eleven.setBackgroundResource(R.drawable.six);
						}
						if(forwhat==12){
							twelve.setBackgroundResource(R.drawable.six);
						}

					}



					if( level==4 || level==5){

						if(forwhat==13){
							thirtin.setBackgroundResource(R.drawable.six);
						}
						if(forwhat==14){
							fourtie.setBackgroundResource(R.drawable.six);
						}
						if(forwhat==15){
							fiftine.setBackgroundResource(R.drawable.six);
						}
						if(forwhat==16){
							sixtine.setBackgroundResource(R.drawable.six);
						}

					}

					if(  level==5){

						if(forwhat==17){
							seventine.setBackgroundResource(R.drawable.six);
						}
						if(forwhat==18){
							eightine.setBackgroundResource(R.drawable.six);
						}
						if(forwhat==19){
							nintine.setBackgroundResource(R.drawable.six);
						}
						if(forwhat==20){
							twenty.setBackgroundResource(R.drawable.six);
						}

					}

				}








				if(list.get(i)==7){
					if(level==1 || level ==2 || level==3 || level==4 || level==5){
						if(forwhat==1){
							one.setBackgroundResource(R.drawable.seven);
						}
						if(forwhat==2){
							two.setBackgroundResource(R.drawable.seven);
						}
						if(forwhat==3){
							three.setBackgroundResource(R.drawable.seven);
						}
						if(forwhat==4){
							four.setBackgroundResource(R.drawable.seven);
						}
					}



					if(level ==2 || level==3 || level==4 || level==5){

						if(forwhat==5){
							five.setBackgroundResource(R.drawable.seven);
						}
						if(forwhat==6){
							six.setBackgroundResource(R.drawable.seven);
						}
						if(forwhat==7){
							seven.setBackgroundResource(R.drawable.seven);
						}
						if(forwhat==8){
							eight.setBackgroundResource(R.drawable.seven);
						}

					}if( level==3 || level==4 || level==5){

						if(forwhat==9){
							nine.setBackgroundResource(R.drawable.seven);
						}
						if(forwhat==10){
							ten.setBackgroundResource(R.drawable.seven);
						}
						if(forwhat==11){
							eleven.setBackgroundResource(R.drawable.seven);
						}
						if(forwhat==12){
							twelve.setBackgroundResource(R.drawable.seven);
						}

					}



					if( level==4 || level==5){

						if(forwhat==13){
							thirtin.setBackgroundResource(R.drawable.seven);
						}
						if(forwhat==14){
							fourtie.setBackgroundResource(R.drawable.seven);
						}
						if(forwhat==15){
							fiftine.setBackgroundResource(R.drawable.seven);
						}
						if(forwhat==16){
							sixtine.setBackgroundResource(R.drawable.seven);
						}

					}

					if(  level==5){

						if(forwhat==17){
							seventine.setBackgroundResource(R.drawable.seven);
						}
						if(forwhat==18){
							eightine.setBackgroundResource(R.drawable.seven);
						}
						if(forwhat==19){
							nintine.setBackgroundResource(R.drawable.seven);
						}
						if(forwhat==20){
							twenty.setBackgroundResource(R.drawable.seven);
						}

					}



				}




				if(list.get(i)==8){
					if(level==1 || level ==2 || level==3 || level==4 || level==5){
						if(forwhat==1){
							one.setBackgroundResource(R.drawable.eight);
						}
						if(forwhat==2){
							two.setBackgroundResource(R.drawable.eight);
						}
						if(forwhat==3){
							three.setBackgroundResource(R.drawable.eight);
						}
						if(forwhat==4){
							four.setBackgroundResource(R.drawable.eight);
						}
					}



					if( level ==2 || level==3 || level==4 || level==5){

						if(forwhat==5){
							five.setBackgroundResource(R.drawable.eight);
						}
						if(forwhat==6){
							six.setBackgroundResource(R.drawable.eight);
						}
						if(forwhat==7){
							seven.setBackgroundResource(R.drawable.eight);
						}
						if(forwhat==8){
							eight.setBackgroundResource(R.drawable.eight);
						}

					}if( level==3 || level==4 || level==5){

						if(forwhat==9){
							nine.setBackgroundResource(R.drawable.eight);
						}
						if(forwhat==10){
							ten.setBackgroundResource(R.drawable.eight);
						}
						if(forwhat==11){
							eleven.setBackgroundResource(R.drawable.eight);
						}
						if(forwhat==12){
							twelve.setBackgroundResource(R.drawable.eight);
						}

					}


					if( level==4 || level==5){

						if(forwhat==13){
							thirtin.setBackgroundResource(R.drawable.eight);
						}
						if(forwhat==14){
							fourtie.setBackgroundResource(R.drawable.eight);
						}
						if(forwhat==15){
							fiftine.setBackgroundResource(R.drawable.eight);
						}
						if(forwhat==16){
							sixtine.setBackgroundResource(R.drawable.eight);
						}

					}

					if(  level==5){

						if(forwhat==17){
							seventine.setBackgroundResource(R.drawable.eight);
						}
						if(forwhat==18){
							eightine.setBackgroundResource(R.drawable.eight);
						}
						if(forwhat==19){
							nintine.setBackgroundResource(R.drawable.eight);
						}
						if(forwhat==20){
							twenty.setBackgroundResource(R.drawable.eight);
						}

					}

				}




				if(list.get(i)==9){
					if(level==1 || level ==2 || level==3 || level==4 || level==5){
						if(forwhat==1){
							one.setBackgroundResource(R.drawable.nine);
						}
						if(forwhat==2){
							two.setBackgroundResource(R.drawable.nine);
						}
						if(forwhat==3){
							three.setBackgroundResource(R.drawable.nine);
						}
						if(forwhat==4){
							four.setBackgroundResource(R.drawable.nine);
						}
					}



					if( level ==2 || level==3 || level==4 || level==5){

						if(forwhat==5){
							five.setBackgroundResource(R.drawable.nine);
						}
						if(forwhat==6){
							six.setBackgroundResource(R.drawable.nine);
						}
						if(forwhat==7){
							seven.setBackgroundResource(R.drawable.nine);
						}
						if(forwhat==8){
							eight.setBackgroundResource(R.drawable.nine);
						}

					}if( level==3 || level==4 || level==5){

						if(forwhat==9){
							nine.setBackgroundResource(R.drawable.nine);
						}
						if(forwhat==10){
							ten.setBackgroundResource(R.drawable.nine);
						}
						if(forwhat==11){
							eleven.setBackgroundResource(R.drawable.nine);
						}
						if(forwhat==12){
							twelve.setBackgroundResource(R.drawable.nine);
						}

					}

					if( level==4 || level==5){

						if(forwhat==13){
							thirtin.setBackgroundResource(R.drawable.nine);
						}
						if(forwhat==14){
							fourtie.setBackgroundResource(R.drawable.nine);
						}
						if(forwhat==15){
							fiftine.setBackgroundResource(R.drawable.nine);
						}
						if(forwhat==16){
							sixtine.setBackgroundResource(R.drawable.nine);
						}

					}

					if(  level==5){

						if(forwhat==17){
							seventine.setBackgroundResource(R.drawable.nine);
						}
						if(forwhat==18){
							eightine.setBackgroundResource(R.drawable.nine);
						}
						if(forwhat==19){
							nintine.setBackgroundResource(R.drawable.nine);
						}
						if(forwhat==20){
							twenty.setBackgroundResource(R.drawable.nine);
						}

					}


				}





				if(list.get(i)==10){
					if(level==1 || level ==2 || level==3 || level==4 || level==5){
						if(forwhat==1){
							one.setBackgroundResource(R.drawable.ten);
						}
						if(forwhat==2){
							two.setBackgroundResource(R.drawable.ten);
						}
						if(forwhat==3){
							three.setBackgroundResource(R.drawable.ten);
						}
						if(forwhat==4){
							four.setBackgroundResource(R.drawable.ten);
						}
					}



					if( level ==2 || level==3 || level==4 || level==5){

						if(forwhat==5){
							five.setBackgroundResource(R.drawable.ten);
						}
						if(forwhat==6){
							six.setBackgroundResource(R.drawable.ten);
						}
						if(forwhat==7){
							seven.setBackgroundResource(R.drawable.ten);
						}
						if(forwhat==8){
							eight.setBackgroundResource(R.drawable.ten);
						}

					}if( level==3 || level==4 || level==5){

						if(forwhat==9){
							nine.setBackgroundResource(R.drawable.ten);
						}
						if(forwhat==10){
							ten.setBackgroundResource(R.drawable.ten);
						}
						if(forwhat==11){
							eleven.setBackgroundResource(R.drawable.ten);
						}
						if(forwhat==12){
							twelve.setBackgroundResource(R.drawable.ten);
						}

					}


					if( level==4 || level==5){

						if(forwhat==13){
							thirtin.setBackgroundResource(R.drawable.ten);
						}
						if(forwhat==14){
							fourtie.setBackgroundResource(R.drawable.ten);
						}
						if(forwhat==15){
							fiftine.setBackgroundResource(R.drawable.ten);
						}
						if(forwhat==16){
							sixtine.setBackgroundResource(R.drawable.ten);
						}

					}

					if(  level==5){

						if(forwhat==17){
							seventine.setBackgroundResource(R.drawable.ten);
						}
						if(forwhat==18){
							eightine.setBackgroundResource(R.drawable.ten);
						}
						if(forwhat==19){
							nintine.setBackgroundResource(R.drawable.ten);
						}
						if(forwhat==20){
							twenty.setBackgroundResource(R.drawable.ten);
						}

					}

				}




				if(list.get(i)==11){
					if(level==1 || level ==2 || level==3 || level==4 || level==5){
						if(forwhat==1){
							one.setBackgroundResource(R.drawable.eleven);
						}
						if(forwhat==2){
							two.setBackgroundResource(R.drawable.eleven);
						}
						if(forwhat==3){
							three.setBackgroundResource(R.drawable.eleven);
						}
						if(forwhat==4){
							four.setBackgroundResource(R.drawable.eleven);
						}
					}



					if( level ==2 || level==3 || level==4 || level==5){

						if(forwhat==5){
							five.setBackgroundResource(R.drawable.eleven);
						}
						if(forwhat==6){
							six.setBackgroundResource(R.drawable.eleven);
						}
						if(forwhat==7){
							seven.setBackgroundResource(R.drawable.eleven);
						}
						if(forwhat==8){
							eight.setBackgroundResource(R.drawable.eleven);
						}

					}if( level==3 || level==4 || level==5){

						if(forwhat==9){
							nine.setBackgroundResource(R.drawable.eleven);
						}
						if(forwhat==10){
							ten.setBackgroundResource(R.drawable.eleven);
						}
						if(forwhat==11){
							eleven.setBackgroundResource(R.drawable.eleven);
						}
						if(forwhat==12){
							twelve.setBackgroundResource(R.drawable.eleven);
						}

					}



					if( level==4 || level==5){

						if(forwhat==13){
							thirtin.setBackgroundResource(R.drawable.eleven);
						}
						if(forwhat==14){
							fourtie.setBackgroundResource(R.drawable.eleven);
						}
						if(forwhat==15){
							fiftine.setBackgroundResource(R.drawable.eleven);
						}
						if(forwhat==16){
							sixtine.setBackgroundResource(R.drawable.eleven);
						}

					}

					if(  level==5){

						if(forwhat==17){
							seventine.setBackgroundResource(R.drawable.eleven);
						}
						if(forwhat==18){
							eightine.setBackgroundResource(R.drawable.eleven);
						}
						if(forwhat==19){
							nintine.setBackgroundResource(R.drawable.eleven);
						}
						if(forwhat==20){
							twenty.setBackgroundResource(R.drawable.eleven);
						}

					}



				}



				if(list.get(i)==12){
					if(level==1 || level ==2 || level==3 || level==4 || level==5){
						if(forwhat==1){
							one.setBackgroundResource(R.drawable.twelve);
						}
						if(forwhat==2){
							two.setBackgroundResource(R.drawable.twelve);
						}
						if(forwhat==3){
							three.setBackgroundResource(R.drawable.twelve);
						}
						if(forwhat==4){
							four.setBackgroundResource(R.drawable.twelve);
						}
					}



					if( level ==2 || level==3 || level==4 || level==5){

						if(forwhat==5){
							five.setBackgroundResource(R.drawable.twelve);
						}
						if(forwhat==6){
							six.setBackgroundResource(R.drawable.twelve);
						}
						if(forwhat==7){
							seven.setBackgroundResource(R.drawable.twelve);
						}
						if(forwhat==8){
							eight.setBackgroundResource(R.drawable.twelve);
						}

					}if( level==3 || level==4 || level==5){

						if(forwhat==9){
							nine.setBackgroundResource(R.drawable.twelve);
						}
						if(forwhat==10){
							ten.setBackgroundResource(R.drawable.twelve);
						}
						if(forwhat==11){
							eleven.setBackgroundResource(R.drawable.twelve);
						}
						if(forwhat==12){
							twelve.setBackgroundResource(R.drawable.twelve);
						}

					}



					if( level==4 || level==5){

						if(forwhat==13){
							thirtin.setBackgroundResource(R.drawable.twelve);
						}
						if(forwhat==14){
							fourtie.setBackgroundResource(R.drawable.twelve);
						}
						if(forwhat==15){
							fiftine.setBackgroundResource(R.drawable.twelve);
						}
						if(forwhat==16){
							sixtine.setBackgroundResource(R.drawable.twelve);
						}

					}

					if(  level==5){

						if(forwhat==17){
							seventine.setBackgroundResource(R.drawable.twelve);
						}
						if(forwhat==18){
							eightine.setBackgroundResource(R.drawable.twelve);
						}
						if(forwhat==19){
							nintine.setBackgroundResource(R.drawable.twelve);
						}
						if(forwhat==20){
							twenty.setBackgroundResource(R.drawable.twelve);
						}

					}


				}




				if(list.get(i)==13){
					if(level==1 || level ==2 || level==3 || level==4 || level==5){
						if(forwhat==1){
							one.setBackgroundResource(R.drawable.thirtine);
						}
						if(forwhat==2){
							two.setBackgroundResource(R.drawable.thirtine);
						}
						if(forwhat==3){
							three.setBackgroundResource(R.drawable.thirtine);
						}
						if(forwhat==4){
							four.setBackgroundResource(R.drawable.thirtine);
						}
					}



					if( level ==2 || level==3 || level==4 || level==5){

						if(forwhat==5){
							five.setBackgroundResource(R.drawable.thirtine);
						}
						if(forwhat==6){
							six.setBackgroundResource(R.drawable.thirtine);
						}
						if(forwhat==7){
							seven.setBackgroundResource(R.drawable.thirtine);
						}
						if(forwhat==8){
							eight.setBackgroundResource(R.drawable.thirtine);
						}

					}if( level==3 || level==4 || level==5){

						if(forwhat==9){
							nine.setBackgroundResource(R.drawable.thirtine);
						}
						if(forwhat==10){
							ten.setBackgroundResource(R.drawable.thirtine);
						}
						if(forwhat==11){
							eleven.setBackgroundResource(R.drawable.thirtine);
						}
						if(forwhat==12){
							twelve.setBackgroundResource(R.drawable.thirtine);
						}

					}



					if( level==4 || level==5){

						if(forwhat==13){
							thirtin.setBackgroundResource(R.drawable.thirtine);
						}
						if(forwhat==14){
							fourtie.setBackgroundResource(R.drawable.thirtine);
						}
						if(forwhat==15){
							fiftine.setBackgroundResource(R.drawable.thirtine);
						}
						if(forwhat==16){
							sixtine.setBackgroundResource(R.drawable.thirtine);
						}

					}

					if(  level==5){

						if(forwhat==17){
							seventine.setBackgroundResource(R.drawable.thirtine);
						}
						if(forwhat==18){
							eightine.setBackgroundResource(R.drawable.thirtine);
						}
						if(forwhat==19){
							nintine.setBackgroundResource(R.drawable.thirtine);
						}
						if(forwhat==20){
							twenty.setBackgroundResource(R.drawable.thirtine);
						}

					}


				}







				if(list.get(i)==14){
					if(level==1 || level ==2 || level==3 || level==4 || level==5){
						if(forwhat==1){
							one.setBackgroundResource(R.drawable.fourtine);
						}
						if(forwhat==2){
							two.setBackgroundResource(R.drawable.fourtine);
						}
						if(forwhat==3){
							three.setBackgroundResource(R.drawable.fourtine);
						}
						if(forwhat==4){
							four.setBackgroundResource(R.drawable.fourtine);
						}
					}



					if( level ==2 || level==3 || level==4 || level==5){

						if(forwhat==5){
							five.setBackgroundResource(R.drawable.fourtine);
						}
						if(forwhat==6){
							six.setBackgroundResource(R.drawable.fourtine);
						}
						if(forwhat==7){
							seven.setBackgroundResource(R.drawable.fourtine);
						}
						if(forwhat==8){
							eight.setBackgroundResource(R.drawable.fourtine);
						}

					}if( level==3 || level==4 || level==5){

						if(forwhat==9){
							nine.setBackgroundResource(R.drawable.fourtine);
						}
						if(forwhat==10){
							ten.setBackgroundResource(R.drawable.fourtine);
						}
						if(forwhat==11){
							eleven.setBackgroundResource(R.drawable.fourtine);
						}
						if(forwhat==12){
							twelve.setBackgroundResource(R.drawable.fourtine);
						}

					}



					if( level==4 || level==5){

						if(forwhat==13){
							thirtin.setBackgroundResource(R.drawable.fourtine);
						}
						if(forwhat==14){
							fourtie.setBackgroundResource(R.drawable.fourtine);
						}
						if(forwhat==15){
							fiftine.setBackgroundResource(R.drawable.fourtine);
						}
						if(forwhat==16){
							sixtine.setBackgroundResource(R.drawable.fourtine);
						}

					}

					if(  level==5){

						if(forwhat==17){
							seventine.setBackgroundResource(R.drawable.fourtine);
						}
						if(forwhat==18){
							eightine.setBackgroundResource(R.drawable.fourtine);
						}
						if(forwhat==19){
							nintine.setBackgroundResource(R.drawable.fourtine);
						}
						if(forwhat==20){
							twenty.setBackgroundResource(R.drawable.fourtine);
						}

					}


				}




				if(list.get(i)==15){
					if(level==1 || level ==2 || level==3 || level==4 || level==5){
						if(forwhat==1){
							one.setBackgroundResource(R.drawable.fivtine);
						}
						if(forwhat==2){
							two.setBackgroundResource(R.drawable.fivtine);
						}
						if(forwhat==3){
							three.setBackgroundResource(R.drawable.fivtine);
						}
						if(forwhat==4){
							four.setBackgroundResource(R.drawable.fivtine);
						}
					}



					if( level ==2 || level==3 || level==4 || level==5){

						if(forwhat==5){
							five.setBackgroundResource(R.drawable.fivtine);
						}
						if(forwhat==6){
							six.setBackgroundResource(R.drawable.fivtine);
						}
						if(forwhat==7){
							seven.setBackgroundResource(R.drawable.fivtine);
						}
						if(forwhat==8){
							eight.setBackgroundResource(R.drawable.fivtine);
						}

					}if( level==3 || level==4 || level==5){

						if(forwhat==9){
							nine.setBackgroundResource(R.drawable.fivtine);
						}
						if(forwhat==10){
							ten.setBackgroundResource(R.drawable.fivtine);
						}
						if(forwhat==11){
							eleven.setBackgroundResource(R.drawable.fivtine);
						}
						if(forwhat==12){
							twelve.setBackgroundResource(R.drawable.fivtine);
						}

					}



					if( level==4 || level==5){

						if(forwhat==13){
							thirtin.setBackgroundResource(R.drawable.fivtine);
						}
						if(forwhat==14){
							fourtie.setBackgroundResource(R.drawable.fivtine);
						}
						if(forwhat==15){
							fiftine.setBackgroundResource(R.drawable.fivtine);
						}
						if(forwhat==16){
							sixtine.setBackgroundResource(R.drawable.fivtine);
						}

					}

					if(  level==5){

						if(forwhat==17){
							seventine.setBackgroundResource(R.drawable.fivtine);
						}
						if(forwhat==18){
							eightine.setBackgroundResource(R.drawable.fivtine);
						}
						if(forwhat==19){
							nintine.setBackgroundResource(R.drawable.fivtine);
						}
						if(forwhat==20){
							twenty.setBackgroundResource(R.drawable.fivtine);
						}

					}


				}


				if(list.get(i)==16){
					if(level==1 || level ==2 || level==3 || level==4 || level==5){
						if(forwhat==1){
							one.setBackgroundResource(R.drawable.sixtine);
						}
						if(forwhat==2){
							two.setBackgroundResource(R.drawable.sixtine);
						}
						if(forwhat==3){
							three.setBackgroundResource(R.drawable.sixtine);
						}
						if(forwhat==4){
							four.setBackgroundResource(R.drawable.sixtine);
						}
					}



					if( level ==2 || level==3 || level==4 || level==5){

						if(forwhat==5){
							five.setBackgroundResource(R.drawable.sixtine);
						}
						if(forwhat==6){
							six.setBackgroundResource(R.drawable.sixtine);
						}
						if(forwhat==7){
							seven.setBackgroundResource(R.drawable.sixtine);
						}
						if(forwhat==8){
							eight.setBackgroundResource(R.drawable.sixtine);
						}

					}if( level==3 || level==4 || level==5){

						if(forwhat==9){
							nine.setBackgroundResource(R.drawable.sixtine);
						}
						if(forwhat==10){
							ten.setBackgroundResource(R.drawable.sixtine);
						}
						if(forwhat==11){
							eleven.setBackgroundResource(R.drawable.sixtine);
						}
						if(forwhat==12){
							twelve.setBackgroundResource(R.drawable.sixtine);
						}

					}



					if( level==4 || level==5){

						if(forwhat==13){
							thirtin.setBackgroundResource(R.drawable.sixtine);
						}
						if(forwhat==14){
							fourtie.setBackgroundResource(R.drawable.sixtine);
						}
						if(forwhat==15){
							fiftine.setBackgroundResource(R.drawable.sixtine);
						}
						if(forwhat==16){
							sixtine.setBackgroundResource(R.drawable.sixtine);
						}

					}

					if(  level==5){

						if(forwhat==17){
							seventine.setBackgroundResource(R.drawable.sixtine);
						}
						if(forwhat==18){
							eightine.setBackgroundResource(R.drawable.sixtine);
						}
						if(forwhat==19){
							nintine.setBackgroundResource(R.drawable.sixtine);
						}
						if(forwhat==20){
							twenty.setBackgroundResource(R.drawable.sixtine);
						}

					}


				}






				if(list.get(i)==17){
					if(level==1 || level ==2 || level==3 || level==4 || level==5){
						if(forwhat==1){
							one.setBackgroundResource(R.drawable.seventine);
						}
						if(forwhat==2){
							two.setBackgroundResource(R.drawable.seventine);
						}
						if(forwhat==3){
							three.setBackgroundResource(R.drawable.seventine);
						}
						if(forwhat==4){
							four.setBackgroundResource(R.drawable.seventine);
						}
					}



					if( level ==2 || level==3 || level==4 || level==5){

						if(forwhat==5){
							five.setBackgroundResource(R.drawable.seventine);
						}
						if(forwhat==6){
							six.setBackgroundResource(R.drawable.seventine);
						}
						if(forwhat==7){
							seven.setBackgroundResource(R.drawable.seventine);
						}
						if(forwhat==8){
							eight.setBackgroundResource(R.drawable.seventine);
						}

					}if( level==3 || level==4 || level==5){

						if(forwhat==9){
							nine.setBackgroundResource(R.drawable.seventine);
						}
						if(forwhat==10){
							ten.setBackgroundResource(R.drawable.seventine);
						}
						if(forwhat==11){
							eleven.setBackgroundResource(R.drawable.seventine);
						}
						if(forwhat==12){
							twelve.setBackgroundResource(R.drawable.seventine);
						}

					}



					if( level==4 || level==5){

						if(forwhat==13){
							thirtin.setBackgroundResource(R.drawable.seventine);
						}
						if(forwhat==14){
							fourtie.setBackgroundResource(R.drawable.seventine);
						}
						if(forwhat==15){
							fiftine.setBackgroundResource(R.drawable.seventine);
						}
						if(forwhat==16){
							sixtine.setBackgroundResource(R.drawable.seventine);
						}

					}

					if(  level==5){

						if(forwhat==17){
							seventine.setBackgroundResource(R.drawable.seventine);
						}
						if(forwhat==18){
							eightine.setBackgroundResource(R.drawable.seventine);
						}
						if(forwhat==19){
							nintine.setBackgroundResource(R.drawable.seventine);
						}
						if(forwhat==20){
							twenty.setBackgroundResource(R.drawable.seventine);
						}

					}


				}

				if(list.get(i)==18){
					if(level==1 || level ==2 || level==3 || level==4 || level==5){
						if(forwhat==1){
							one.setBackgroundResource(R.drawable.eightine);
						}
						if(forwhat==2){
							two.setBackgroundResource(R.drawable.eightine);
						}
						if(forwhat==3){
							three.setBackgroundResource(R.drawable.eightine);
						}
						if(forwhat==4){
							four.setBackgroundResource(R.drawable.eightine);
						}
					}



					if( level ==2 || level==3 || level==4 || level==5){

						if(forwhat==5){
							five.setBackgroundResource(R.drawable.eightine);
						}
						if(forwhat==6){
							six.setBackgroundResource(R.drawable.eightine);
						}
						if(forwhat==7){
							seven.setBackgroundResource(R.drawable.eightine);
						}
						if(forwhat==8){
							eight.setBackgroundResource(R.drawable.eightine);
						}

					}if( level==3 || level==4 || level==5){

						if(forwhat==9){
							nine.setBackgroundResource(R.drawable.eightine);
						}
						if(forwhat==10){
							ten.setBackgroundResource(R.drawable.eightine);
						}
						if(forwhat==11){
							eleven.setBackgroundResource(R.drawable.eightine);
						}
						if(forwhat==12){
							twelve.setBackgroundResource(R.drawable.eightine);
						}

					}



					if( level==4 || level==5){

						if(forwhat==13){
							thirtin.setBackgroundResource(R.drawable.eightine);
						}
						if(forwhat==14){
							fourtie.setBackgroundResource(R.drawable.eightine);
						}
						if(forwhat==15){
							fiftine.setBackgroundResource(R.drawable.eightine);
						}
						if(forwhat==16){
							sixtine.setBackgroundResource(R.drawable.eightine);
						}

					}

					if(  level==5){

						if(forwhat==17){
							seventine.setBackgroundResource(R.drawable.eightine);
						}
						if(forwhat==18){
							eightine.setBackgroundResource(R.drawable.eightine);
						}
						if(forwhat==19){
							nintine.setBackgroundResource(R.drawable.eightine);
						}
						if(forwhat==20){
							twenty.setBackgroundResource(R.drawable.eightine);
						}

					}


				}



				if(list.get(i)==19){
					if(level==1 || level ==2 || level==3 || level==4 || level==5){
						if(forwhat==1){
							one.setBackgroundResource(R.drawable.nintin);
						}
						if(forwhat==2){
							two.setBackgroundResource(R.drawable.nintin);
						}
						if(forwhat==3){
							three.setBackgroundResource(R.drawable.nintin);
						}
						if(forwhat==4){
							four.setBackgroundResource(R.drawable.nintin);
						}
					}



					if( level ==2 || level==3 || level==4 || level==5){

						if(forwhat==5){
							five.setBackgroundResource(R.drawable.nintin);
						}
						if(forwhat==6){
							six.setBackgroundResource(R.drawable.nintin);
						}
						if(forwhat==7){
							seven.setBackgroundResource(R.drawable.nintin);
						}
						if(forwhat==8){
							eight.setBackgroundResource(R.drawable.nintin);
						}

					}if( level==3 || level==4 || level==5){

						if(forwhat==9){
							nine.setBackgroundResource(R.drawable.nintin);
						}
						if(forwhat==10){
							ten.setBackgroundResource(R.drawable.nintin);
						}
						if(forwhat==11){
							eleven.setBackgroundResource(R.drawable.nintin);
						}
						if(forwhat==12){
							twelve.setBackgroundResource(R.drawable.nintin);
						}

					}



					if( level==4 || level==5){

						if(forwhat==13){
							thirtin.setBackgroundResource(R.drawable.nintin);
						}
						if(forwhat==14){
							fourtie.setBackgroundResource(R.drawable.nintin);
						}
						if(forwhat==15){
							fiftine.setBackgroundResource(R.drawable.nintin);
						}
						if(forwhat==16){
							sixtine.setBackgroundResource(R.drawable.nintin);
						}

					}

					if(  level==5){

						if(forwhat==17){
							seventine.setBackgroundResource(R.drawable.nintin);
						}
						if(forwhat==18){
							eightine.setBackgroundResource(R.drawable.nintin);
						}
						if(forwhat==19){
							nintine.setBackgroundResource(R.drawable.nintin);
						}
						if(forwhat==20){
							twenty.setBackgroundResource(R.drawable.nintin);
						}

					}


				}



				if(list.get(i)==20){
					if(level==1 || level ==2 || level==3 || level==4 || level==5){
						if(forwhat==1){
							one.setBackgroundResource(R.drawable.twenty);
						}
						if(forwhat==2){
							two.setBackgroundResource(R.drawable.twenty);
						}
						if(forwhat==3){
							three.setBackgroundResource(R.drawable.twenty);
						}
						if(forwhat==4){
							four.setBackgroundResource(R.drawable.twenty);
						}
					}



					if( level ==2 || level==3 || level==4 || level==5){

						if(forwhat==5){
							five.setBackgroundResource(R.drawable.twenty);
						}
						if(forwhat==6){
							six.setBackgroundResource(R.drawable.twenty);
						}
						if(forwhat==7){
							seven.setBackgroundResource(R.drawable.twenty);
						}
						if(forwhat==8){
							eight.setBackgroundResource(R.drawable.twenty);
						}

					}if( level==3 || level==4 || level==5){

						if(forwhat==9){
							nine.setBackgroundResource(R.drawable.twenty);
						}
						if(forwhat==10){
							ten.setBackgroundResource(R.drawable.twenty);
						}
						if(forwhat==11){
							eleven.setBackgroundResource(R.drawable.twenty);
						}
						if(forwhat==12){
							twelve.setBackgroundResource(R.drawable.twenty);
						}

					}



					if( level==4 || level==5){

						if(forwhat==13){
							thirtin.setBackgroundResource(R.drawable.twenty);
						}
						if(forwhat==14){
							fourtie.setBackgroundResource(R.drawable.twenty);
						}
						if(forwhat==15){
							fiftine.setBackgroundResource(R.drawable.twenty);
						}
						if(forwhat==16){
							sixtine.setBackgroundResource(R.drawable.twenty);
						}

					}

					if(  level==5){

						if(forwhat==17){
							seventine.setBackgroundResource(R.drawable.twenty);
						}
						if(forwhat==18){
							eightine.setBackgroundResource(R.drawable.twenty);
						}
						if(forwhat==19){
							nintine.setBackgroundResource(R.drawable.twenty);
						}
						if(forwhat==20){
							twenty.setBackgroundResource(R.drawable.twenty);
						}

					}


				}




				forwhat++;  }

			forwhat=1;




		}

	}




	public void gamescne2(){

		if(gamescine2==1){



			list = new ArrayList<Integer>();
			for (i=1; i<level_loop; i++) {
				list.add(new Integer(i));

			}
			Collections.shuffle(list);

			int findnumbers=level_loop-1;
			for (int i=0; i<findnumbers; i++) {


				if(list.get(i)==1){

					if(level==1 || level ==2 || level==3 || level==4 || level==5){

						if(forwhat==1){
							one.setBackgroundResource(R.drawable.oneb);
						}
						if(forwhat==2){
							two.setBackgroundResource(R.drawable.oneb);
						}
						if(forwhat==3){
							three.setBackgroundResource(R.drawable.oneb);
						}
						if(forwhat==4){
							four.setBackgroundResource(R.drawable.oneb);
						}
					}
					if( level ==2 || level==3 || level==4 || level==5){

						if(forwhat==5){
							five.setBackgroundResource(R.drawable.oneb);
						}
						if(forwhat==6){
							six.setBackgroundResource(R.drawable.oneb);
						}
						if(forwhat==7){
							seven.setBackgroundResource(R.drawable.oneb);
						}
						if(forwhat==8){
							eight.setBackgroundResource(R.drawable.oneb);
						}

					}if(level==3 || level==4 || level==5){

						if(forwhat==9){
							nine.setBackgroundResource(R.drawable.oneb);
						}
						if(forwhat==10){
							ten.setBackgroundResource(R.drawable.oneb);
						}
						if(forwhat==11){
							eleven.setBackgroundResource(R.drawable.oneb);
						}
						if(forwhat==12){
							twelve.setBackgroundResource(R.drawable.oneb);
						}

					}


					if( level==4 || level==5){

						if(forwhat==13){
							thirtin.setBackgroundResource(R.drawable.oneb);
						}
						if(forwhat==14){
							fourtie.setBackgroundResource(R.drawable.oneb);
						}
						if(forwhat==15){
							fiftine.setBackgroundResource(R.drawable.oneb);
						}
						if(forwhat==16){
							sixtine.setBackgroundResource(R.drawable.oneb);
						}

					}

					if(  level==5){

						if(forwhat==17){
							seventine.setBackgroundResource(R.drawable.oneb);
						}
						if(forwhat==18){
							eightine.setBackgroundResource(R.drawable.oneb);
						}
						if(forwhat==19){
							nintine.setBackgroundResource(R.drawable.oneb);
						}
						if(forwhat==20){
							twenty.setBackgroundResource(R.drawable.oneb);
						}

					}










				}
				if(list.get(i)==2){

					if(level==1 || level ==2 || level==3 || level==4 || level==5){
						if(forwhat==1){
							one.setBackgroundResource(R.drawable.twob);
						}
						if(forwhat==2){
							two.setBackgroundResource(R.drawable.twob);
						}
						if(forwhat==3){
							three.setBackgroundResource(R.drawable.twob);
						}
						if(forwhat==4){
							four.setBackgroundResource(R.drawable.twob);
						}}






					if(level ==2 || level==3 || level==4 || level==5){

						if(forwhat==5){
							five.setBackgroundResource(R.drawable.twob);
						}
						if(forwhat==6){
							six.setBackgroundResource(R.drawable.twob);
						}
						if(forwhat==7){
							seven.setBackgroundResource(R.drawable.twob);
						}
						if(forwhat==8){
							eight.setBackgroundResource(R.drawable.twob);
						}

					}if( level==3 || level==4 || level==5){

						if(forwhat==9){
							nine.setBackgroundResource(R.drawable.twob);
						}
						if(forwhat==10){
							ten.setBackgroundResource(R.drawable.twob);
						}
						if(forwhat==11){
							eleven.setBackgroundResource(R.drawable.twob);
						}
						if(forwhat==12){
							twelve.setBackgroundResource(R.drawable.twob);
						}

					}

					if( level==4 || level==5){

						if(forwhat==13){
							thirtin.setBackgroundResource(R.drawable.twob);
						}
						if(forwhat==14){
							fourtie.setBackgroundResource(R.drawable.twob);
						}
						if(forwhat==15){
							fiftine.setBackgroundResource(R.drawable.twob);
						}
						if(forwhat==16){
							sixtine.setBackgroundResource(R.drawable.twob);
						}

					}

					if(  level==5){

						if(forwhat==17){
							seventine.setBackgroundResource(R.drawable.twob);
						}
						if(forwhat==18){
							eightine.setBackgroundResource(R.drawable.twob);
						}
						if(forwhat==19){
							nintine.setBackgroundResource(R.drawable.twob);
						}
						if(forwhat==20){
							twenty.setBackgroundResource(R.drawable.twob);
						}

					}
















				}
				if(list.get(i)==3){

					if(level==1 || level ==2 || level==3 || level==4 || level==5){
						if(forwhat==1){
							one.setBackgroundResource(R.drawable.threeb);
						}
						if(forwhat==2){
							two.setBackgroundResource(R.drawable.threeb);
						}
						if(forwhat==3){
							three.setBackgroundResource(R.drawable.threeb);
						}
						if(forwhat==4){
							four.setBackgroundResource(R.drawable.threeb);
						}}



					if( level ==2 || level==3 || level==4 || level==5){

						if(forwhat==5){
							five.setBackgroundResource(R.drawable.threeb);
						}
						if(forwhat==6){
							six.setBackgroundResource(R.drawable.threeb);
						}
						if(forwhat==7){
							seven.setBackgroundResource(R.drawable.threeb);
						}
						if(forwhat==8){
							eight.setBackgroundResource(R.drawable.threeb);
						}

					}if( level==3 || level==4 || level==5){

						if(forwhat==9){
							nine.setBackgroundResource(R.drawable.threeb);
						}
						if(forwhat==10){
							ten.setBackgroundResource(R.drawable.threeb);
						}
						if(forwhat==11){
							eleven.setBackgroundResource(R.drawable.threeb);
						}
						if(forwhat==12){
							twelve.setBackgroundResource(R.drawable.threeb);
						}

					}


					if( level==4 || level==5){

						if(forwhat==13){
							thirtin.setBackgroundResource(R.drawable.threeb);
						}
						if(forwhat==14){
							fourtie.setBackgroundResource(R.drawable.threeb);
						}
						if(forwhat==15){
							fiftine.setBackgroundResource(R.drawable.threeb);
						}
						if(forwhat==16){
							sixtine.setBackgroundResource(R.drawable.threeb);
						}

					}

					if(  level==5){

						if(forwhat==17){
							seventine.setBackgroundResource(R.drawable.threeb);
						}
						if(forwhat==18){
							eightine.setBackgroundResource(R.drawable.threeb);
						}
						if(forwhat==19){
							nintine.setBackgroundResource(R.drawable.threeb);
						}
						if(forwhat==20){
							twenty.setBackgroundResource(R.drawable.threeb);
						}

					}










				}








				if(list.get(i)==4){
					if(level==1 || level ==2 || level==3 || level==4 || level==5){
						if(forwhat==1){
							one.setBackgroundResource(R.drawable.fourb);
						}
						if(forwhat==2){
							two.setBackgroundResource(R.drawable.fourb);
						}
						if(forwhat==3){
							three.setBackgroundResource(R.drawable.fourb);
						}
						if(forwhat==4){
							four.setBackgroundResource(R.drawable.fourb);
						}
					}



					if( level ==2 || level==3 || level==4 || level==5){

						if(forwhat==5){
							five.setBackgroundResource(R.drawable.fourb);
						}
						if(forwhat==6){
							six.setBackgroundResource(R.drawable.fourb);
						}
						if(forwhat==7){
							seven.setBackgroundResource(R.drawable.fourb);
						}
						if(forwhat==8){
							eight.setBackgroundResource(R.drawable.fourb);
						}

					}if(level==3 || level==4 || level==5){

						if(forwhat==9){
							nine.setBackgroundResource(R.drawable.fourb);
						}
						if(forwhat==10){
							ten.setBackgroundResource(R.drawable.fourb);
						}
						if(forwhat==11){
							eleven.setBackgroundResource(R.drawable.fourb);
						}
						if(forwhat==12){
							twelve.setBackgroundResource(R.drawable.fourb);
						}

					}

					if( level==4 || level==5){

						if(forwhat==13){
							thirtin.setBackgroundResource(R.drawable.fourb);
						}
						if(forwhat==14){
							fourtie.setBackgroundResource(R.drawable.fourb);
						}
						if(forwhat==15){
							fiftine.setBackgroundResource(R.drawable.fourb);
						}
						if(forwhat==16){
							sixtine.setBackgroundResource(R.drawable.fourb);
						}

					}

					if(  level==5){

						if(forwhat==17){
							seventine.setBackgroundResource(R.drawable.fourb);
						}
						if(forwhat==18){
							eightine.setBackgroundResource(R.drawable.fourb);
						}
						if(forwhat==19){
							nintine.setBackgroundResource(R.drawable.fourb);
						}
						if(forwhat==20){
							twenty.setBackgroundResource(R.drawable.fourb);
						}

					}



				}





				if(list.get(i)==5){
					if(level==1 || level ==2 || level==3 || level==4 || level==5){
						if(forwhat==1){
							one.setBackgroundResource(R.drawable.fiveb);
						}
						if(forwhat==2){
							two.setBackgroundResource(R.drawable.fiveb);
						}
						if(forwhat==3){
							three.setBackgroundResource(R.drawable.fiveb);
						}
						if(forwhat==4){
							four.setBackgroundResource(R.drawable.fiveb);
						}
					}



					if(level ==2 || level==3 || level==4 || level==5){

						if(forwhat==5){
							five.setBackgroundResource(R.drawable.fiveb);
						}
						if(forwhat==6){
							six.setBackgroundResource(R.drawable.fiveb);
						}
						if(forwhat==7){
							seven.setBackgroundResource(R.drawable.fiveb);
						}
						if(forwhat==8){
							eight.setBackgroundResource(R.drawable.fiveb);
						}

					}if( level==3 || level==4 || level==5){

						if(forwhat==9){
							nine.setBackgroundResource(R.drawable.fiveb);
						}
						if(forwhat==10){
							ten.setBackgroundResource(R.drawable.fiveb);
						}
						if(forwhat==11){
							eleven.setBackgroundResource(R.drawable.fiveb);
						}
						if(forwhat==12){
							twelve.setBackgroundResource(R.drawable.fiveb);
						}

					}


					if( level==4 || level==5){

						if(forwhat==13){
							thirtin.setBackgroundResource(R.drawable.fiveb);
						}
						if(forwhat==14){
							fourtie.setBackgroundResource(R.drawable.fiveb);
						}
						if(forwhat==15){
							fiftine.setBackgroundResource(R.drawable.fiveb);
						}
						if(forwhat==16){
							sixtine.setBackgroundResource(R.drawable.fiveb);
						}

					}

					if(  level==5){

						if(forwhat==17){
							seventine.setBackgroundResource(R.drawable.fiveb);
						}
						if(forwhat==18){
							eightine.setBackgroundResource(R.drawable.fiveb);
						}
						if(forwhat==19){
							nintine.setBackgroundResource(R.drawable.fiveb);
						}
						if(forwhat==20){
							twenty.setBackgroundResource(R.drawable.fiveb);
						}

					}




				}






				if(list.get(i)==6){
					if(level==1 || level ==2 || level==3 || level==4 || level==5){
						if(forwhat==1){
							one.setBackgroundResource(R.drawable.sixb);
						}
						if(forwhat==2){
							two.setBackgroundResource(R.drawable.sixb);
						}
						if(forwhat==3){
							three.setBackgroundResource(R.drawable.sixb);
						}
						if(forwhat==4){
							four.setBackgroundResource(R.drawable.sixb);
						}
					}



					if(level ==2 || level==3 || level==4 || level==5){

						if(forwhat==5){
							five.setBackgroundResource(R.drawable.sixb);
						}
						if(forwhat==6){
							six.setBackgroundResource(R.drawable.sixb);
						}
						if(forwhat==7){
							seven.setBackgroundResource(R.drawable.sixb);
						}
						if(forwhat==8){
							eight.setBackgroundResource(R.drawable.sixb);
						}

					}if( level==3 || level==4 || level==5){

						if(forwhat==9){
							nine.setBackgroundResource(R.drawable.sixb);
						}
						if(forwhat==10){
							ten.setBackgroundResource(R.drawable.sixb);
						}
						if(forwhat==11){
							eleven.setBackgroundResource(R.drawable.sixb);
						}
						if(forwhat==12){
							twelve.setBackgroundResource(R.drawable.sixb);
						}

					}



					if( level==4 || level==5){

						if(forwhat==13){
							thirtin.setBackgroundResource(R.drawable.sixb);
						}
						if(forwhat==14){
							fourtie.setBackgroundResource(R.drawable.sixb);
						}
						if(forwhat==15){
							fiftine.setBackgroundResource(R.drawable.sixb);
						}
						if(forwhat==16){
							sixtine.setBackgroundResource(R.drawable.sixb);
						}

					}

					if(  level==5){

						if(forwhat==17){
							seventine.setBackgroundResource(R.drawable.sixb);
						}
						if(forwhat==18){
							eightine.setBackgroundResource(R.drawable.sixb);
						}
						if(forwhat==19){
							nintine.setBackgroundResource(R.drawable.sixb);
						}
						if(forwhat==20){
							twenty.setBackgroundResource(R.drawable.sixb);
						}

					}

				}








				if(list.get(i)==7){
					if(level==1 || level ==2 || level==3 || level==4 || level==5){
						if(forwhat==1){
							one.setBackgroundResource(R.drawable.sevenb);
						}
						if(forwhat==2){
							two.setBackgroundResource(R.drawable.sevenb);
						}
						if(forwhat==3){
							three.setBackgroundResource(R.drawable.sevenb);
						}
						if(forwhat==4){
							four.setBackgroundResource(R.drawable.sevenb);
						}
					}



					if(level ==2 || level==3 || level==4 || level==5){

						if(forwhat==5){
							five.setBackgroundResource(R.drawable.sevenb);
						}
						if(forwhat==6){
							six.setBackgroundResource(R.drawable.sevenb);
						}
						if(forwhat==7){
							seven.setBackgroundResource(R.drawable.sevenb);
						}
						if(forwhat==8){
							eight.setBackgroundResource(R.drawable.sevenb);
						}

					}if( level==3 || level==4 || level==5){

						if(forwhat==9){
							nine.setBackgroundResource(R.drawable.sevenb);
						}
						if(forwhat==10){
							ten.setBackgroundResource(R.drawable.sevenb);
						}
						if(forwhat==11){
							eleven.setBackgroundResource(R.drawable.sevenb);
						}
						if(forwhat==12){
							twelve.setBackgroundResource(R.drawable.sevenb);
						}

					}



					if( level==4 || level==5){

						if(forwhat==13){
							thirtin.setBackgroundResource(R.drawable.sevenb);
						}
						if(forwhat==14){
							fourtie.setBackgroundResource(R.drawable.sevenb);
						}
						if(forwhat==15){
							fiftine.setBackgroundResource(R.drawable.sevenb);
						}
						if(forwhat==16){
							sixtine.setBackgroundResource(R.drawable.sevenb);
						}

					}

					if(  level==5){

						if(forwhat==17){
							seventine.setBackgroundResource(R.drawable.sevenb);
						}
						if(forwhat==18){
							eightine.setBackgroundResource(R.drawable.sevenb);
						}
						if(forwhat==19){
							nintine.setBackgroundResource(R.drawable.sevenb);
						}
						if(forwhat==20){
							twenty.setBackgroundResource(R.drawable.sevenb);
						}

					}



				}




				if(list.get(i)==8){
					if(level==1 || level ==2 || level==3 || level==4 || level==5){
						if(forwhat==1){
							one.setBackgroundResource(R.drawable.eightb);
						}
						if(forwhat==2){
							two.setBackgroundResource(R.drawable.eightb);
						}
						if(forwhat==3){
							three.setBackgroundResource(R.drawable.eightb);
						}
						if(forwhat==4){
							four.setBackgroundResource(R.drawable.eightb);
						}
					}



					if( level ==2 || level==3 || level==4 || level==5){

						if(forwhat==5){
							five.setBackgroundResource(R.drawable.eightb);
						}
						if(forwhat==6){
							six.setBackgroundResource(R.drawable.eightb);
						}
						if(forwhat==7){
							seven.setBackgroundResource(R.drawable.eightb);
						}
						if(forwhat==8){
							eight.setBackgroundResource(R.drawable.eightb);
						}

					}if( level==3 || level==4 || level==5){

						if(forwhat==9){
							nine.setBackgroundResource(R.drawable.eightb);
						}
						if(forwhat==10){
							ten.setBackgroundResource(R.drawable.eightb);
						}
						if(forwhat==11){
							eleven.setBackgroundResource(R.drawable.eightb);
						}
						if(forwhat==12){
							twelve.setBackgroundResource(R.drawable.eightb);
						}

					}


					if( level==4 || level==5){

						if(forwhat==13){
							thirtin.setBackgroundResource(R.drawable.eightb);
						}
						if(forwhat==14){
							fourtie.setBackgroundResource(R.drawable.eightb);
						}
						if(forwhat==15){
							fiftine.setBackgroundResource(R.drawable.eightb);
						}
						if(forwhat==16){
							sixtine.setBackgroundResource(R.drawable.eightb);
						}

					}

					if(  level==5){

						if(forwhat==17){
							seventine.setBackgroundResource(R.drawable.eightb);
						}
						if(forwhat==18){
							eightine.setBackgroundResource(R.drawable.eightb);
						}
						if(forwhat==19){
							nintine.setBackgroundResource(R.drawable.eightb);
						}
						if(forwhat==20){
							twenty.setBackgroundResource(R.drawable.eightb);
						}

					}

				}




				if(list.get(i)==9){
					if(level==1 || level ==2 || level==3 || level==4 || level==5){
						if(forwhat==1){
							one.setBackgroundResource(R.drawable.nineb);
						}
						if(forwhat==2){
							two.setBackgroundResource(R.drawable.nineb);
						}
						if(forwhat==3){
							three.setBackgroundResource(R.drawable.nineb);
						}
						if(forwhat==4){
							four.setBackgroundResource(R.drawable.nineb);
						}
					}



					if( level ==2 || level==3 || level==4 || level==5){

						if(forwhat==5){
							five.setBackgroundResource(R.drawable.nineb);
						}
						if(forwhat==6){
							six.setBackgroundResource(R.drawable.nineb);
						}
						if(forwhat==7){
							seven.setBackgroundResource(R.drawable.nineb);
						}
						if(forwhat==8){
							eight.setBackgroundResource(R.drawable.nineb);
						}

					}if( level==3 || level==4 || level==5){

						if(forwhat==9){
							nine.setBackgroundResource(R.drawable.nineb);
						}
						if(forwhat==10){
							ten.setBackgroundResource(R.drawable.nineb);
						}
						if(forwhat==11){
							eleven.setBackgroundResource(R.drawable.nineb);
						}
						if(forwhat==12){
							twelve.setBackgroundResource(R.drawable.nineb);
						}

					}

					if( level==4 || level==5){

						if(forwhat==13){
							thirtin.setBackgroundResource(R.drawable.nineb);
						}
						if(forwhat==14){
							fourtie.setBackgroundResource(R.drawable.nineb);
						}
						if(forwhat==15){
							fiftine.setBackgroundResource(R.drawable.nineb);
						}
						if(forwhat==16){
							sixtine.setBackgroundResource(R.drawable.nineb);
						}

					}

					if(  level==5){

						if(forwhat==17){
							seventine.setBackgroundResource(R.drawable.nineb);
						}
						if(forwhat==18){
							eightine.setBackgroundResource(R.drawable.nineb);
						}
						if(forwhat==19){
							nintine.setBackgroundResource(R.drawable.nineb);
						}
						if(forwhat==20){
							twenty.setBackgroundResource(R.drawable.nineb);
						}

					}


				}





				if(list.get(i)==10){
					if(level==1 || level ==2 || level==3 || level==4 || level==5){
						if(forwhat==1){
							one.setBackgroundResource(R.drawable.tenb);
						}
						if(forwhat==2){
							two.setBackgroundResource(R.drawable.tenb);
						}
						if(forwhat==3){
							three.setBackgroundResource(R.drawable.tenb);
						}
						if(forwhat==4){
							four.setBackgroundResource(R.drawable.tenb);
						}
					}



					if( level ==2 || level==3 || level==4 || level==5){

						if(forwhat==5){
							five.setBackgroundResource(R.drawable.tenb);
						}
						if(forwhat==6){
							six.setBackgroundResource(R.drawable.tenb);
						}
						if(forwhat==7){
							seven.setBackgroundResource(R.drawable.tenb);
						}
						if(forwhat==8){
							eight.setBackgroundResource(R.drawable.tenb);
						}

					}if( level==3 || level==4 || level==5){

						if(forwhat==9){
							nine.setBackgroundResource(R.drawable.tenb);
						}
						if(forwhat==10){
							ten.setBackgroundResource(R.drawable.tenb);
						}
						if(forwhat==11){
							eleven.setBackgroundResource(R.drawable.tenb);
						}
						if(forwhat==12){
							twelve.setBackgroundResource(R.drawable.tenb);
						}

					}


					if( level==4 || level==5){

						if(forwhat==13){
							thirtin.setBackgroundResource(R.drawable.tenb);
						}
						if(forwhat==14){
							fourtie.setBackgroundResource(R.drawable.tenb);
						}
						if(forwhat==15){
							fiftine.setBackgroundResource(R.drawable.tenb);
						}
						if(forwhat==16){
							sixtine.setBackgroundResource(R.drawable.tenb);
						}

					}

					if(  level==5){

						if(forwhat==17){
							seventine.setBackgroundResource(R.drawable.tenb);
						}
						if(forwhat==18){
							eightine.setBackgroundResource(R.drawable.tenb);
						}
						if(forwhat==19){
							nintine.setBackgroundResource(R.drawable.tenb);
						}
						if(forwhat==20){
							twenty.setBackgroundResource(R.drawable.tenb);
						}

					}

				}




				if(list.get(i)==11){
					if(level==1 || level ==2 || level==3 || level==4 || level==5){
						if(forwhat==1){
							one.setBackgroundResource(R.drawable.elevenb);
						}
						if(forwhat==2){
							two.setBackgroundResource(R.drawable.elevenb);
						}
						if(forwhat==3){
							three.setBackgroundResource(R.drawable.elevenb);
						}
						if(forwhat==4){
							four.setBackgroundResource(R.drawable.elevenb);
						}
					}



					if( level ==2 || level==3 || level==4 || level==5){

						if(forwhat==5){
							five.setBackgroundResource(R.drawable.elevenb);
						}
						if(forwhat==6){
							six.setBackgroundResource(R.drawable.elevenb);
						}
						if(forwhat==7){
							seven.setBackgroundResource(R.drawable.elevenb);
						}
						if(forwhat==8){
							eight.setBackgroundResource(R.drawable.elevenb);
						}

					}if( level==3 || level==4 || level==5){

						if(forwhat==9){
							nine.setBackgroundResource(R.drawable.elevenb);
						}
						if(forwhat==10){
							ten.setBackgroundResource(R.drawable.elevenb);
						}
						if(forwhat==11){
							eleven.setBackgroundResource(R.drawable.elevenb);
						}
						if(forwhat==12){
							twelve.setBackgroundResource(R.drawable.elevenb);
						}

					}



					if( level==4 || level==5){

						if(forwhat==13){
							thirtin.setBackgroundResource(R.drawable.elevenb);
						}
						if(forwhat==14){
							fourtie.setBackgroundResource(R.drawable.elevenb);
						}
						if(forwhat==15){
							fiftine.setBackgroundResource(R.drawable.elevenb);
						}
						if(forwhat==16){
							sixtine.setBackgroundResource(R.drawable.elevenb);
						}

					}

					if(  level==5){

						if(forwhat==17){
							seventine.setBackgroundResource(R.drawable.elevenb);
						}
						if(forwhat==18){
							eightine.setBackgroundResource(R.drawable.elevenb);
						}
						if(forwhat==19){
							nintine.setBackgroundResource(R.drawable.elevenb);
						}
						if(forwhat==20){
							twenty.setBackgroundResource(R.drawable.elevenb);
						}

					}



				}



				if(list.get(i)==12){
					if(level==1 || level ==2 || level==3 || level==4 || level==5){
						if(forwhat==1){
							one.setBackgroundResource(R.drawable.twelveb);
						}
						if(forwhat==2){
							two.setBackgroundResource(R.drawable.twelveb);
						}
						if(forwhat==3){
							three.setBackgroundResource(R.drawable.twelveb);
						}
						if(forwhat==4){
							four.setBackgroundResource(R.drawable.twelveb);
						}
					}



					if( level ==2 || level==3 || level==4 || level==5){

						if(forwhat==5){
							five.setBackgroundResource(R.drawable.twelveb);
						}
						if(forwhat==6){
							six.setBackgroundResource(R.drawable.twelveb);
						}
						if(forwhat==7){
							seven.setBackgroundResource(R.drawable.twelveb);
						}
						if(forwhat==8){
							eight.setBackgroundResource(R.drawable.twelveb);
						}

					}if( level==3 || level==4 || level==5){

						if(forwhat==9){
							nine.setBackgroundResource(R.drawable.twelveb);
						}
						if(forwhat==10){
							ten.setBackgroundResource(R.drawable.twelveb);
						}
						if(forwhat==11){
							eleven.setBackgroundResource(R.drawable.twelveb);
						}
						if(forwhat==12){
							twelve.setBackgroundResource(R.drawable.twelveb);
						}

					}



					if( level==4 || level==5){

						if(forwhat==13){
							thirtin.setBackgroundResource(R.drawable.twelveb);
						}
						if(forwhat==14){
							fourtie.setBackgroundResource(R.drawable.twelveb);
						}
						if(forwhat==15){
							fiftine.setBackgroundResource(R.drawable.twelveb);
						}
						if(forwhat==16){
							sixtine.setBackgroundResource(R.drawable.twelveb);
						}

					}

					if(  level==5){

						if(forwhat==17){
							seventine.setBackgroundResource(R.drawable.twelveb);
						}
						if(forwhat==18){
							eightine.setBackgroundResource(R.drawable.twelveb);
						}
						if(forwhat==19){
							nintine.setBackgroundResource(R.drawable.twelveb);
						}
						if(forwhat==20){
							twenty.setBackgroundResource(R.drawable.twelveb);
						}

					}


				}




				if(list.get(i)==13){
					if(level==1 || level ==2 || level==3 || level==4 || level==5){
						if(forwhat==1){
							one.setBackgroundResource(R.drawable.thirtineb);
						}
						if(forwhat==2){
							two.setBackgroundResource(R.drawable.thirtineb);
						}
						if(forwhat==3){
							three.setBackgroundResource(R.drawable.thirtineb);
						}
						if(forwhat==4){
							four.setBackgroundResource(R.drawable.thirtineb);
						}
					}



					if( level ==2 || level==3 || level==4 || level==5){

						if(forwhat==5){
							five.setBackgroundResource(R.drawable.thirtineb);
						}
						if(forwhat==6){
							six.setBackgroundResource(R.drawable.thirtineb);
						}
						if(forwhat==7){
							seven.setBackgroundResource(R.drawable.thirtineb);
						}
						if(forwhat==8){
							eight.setBackgroundResource(R.drawable.thirtineb);
						}

					}if( level==3 || level==4 || level==5){

						if(forwhat==9){
							nine.setBackgroundResource(R.drawable.thirtineb);
						}
						if(forwhat==10){
							ten.setBackgroundResource(R.drawable.thirtineb);
						}
						if(forwhat==11){
							eleven.setBackgroundResource(R.drawable.thirtineb);
						}
						if(forwhat==12){
							twelve.setBackgroundResource(R.drawable.thirtineb);
						}

					}



					if( level==4 || level==5){

						if(forwhat==13){
							thirtin.setBackgroundResource(R.drawable.thirtineb);
						}
						if(forwhat==14){
							fourtie.setBackgroundResource(R.drawable.thirtineb);
						}
						if(forwhat==15){
							fiftine.setBackgroundResource(R.drawable.thirtineb);
						}
						if(forwhat==16){
							sixtine.setBackgroundResource(R.drawable.thirtineb);
						}

					}

					if(  level==5){

						if(forwhat==17){
							seventine.setBackgroundResource(R.drawable.thirtineb);
						}
						if(forwhat==18){
							eightine.setBackgroundResource(R.drawable.thirtineb);
						}
						if(forwhat==19){
							nintine.setBackgroundResource(R.drawable.thirtineb);
						}
						if(forwhat==20){
							twenty.setBackgroundResource(R.drawable.thirtineb);
						}

					}


				}







				if(list.get(i)==14){
					if(level==1 || level ==2 || level==3 || level==4 || level==5){
						if(forwhat==1){
							one.setBackgroundResource(R.drawable.fourtineb);
						}
						if(forwhat==2){
							two.setBackgroundResource(R.drawable.fourtineb);
						}
						if(forwhat==3){
							three.setBackgroundResource(R.drawable.fourtineb);
						}
						if(forwhat==4){
							four.setBackgroundResource(R.drawable.fourtineb);
						}
					}



					if( level ==2 || level==3 || level==4 || level==5){

						if(forwhat==5){
							five.setBackgroundResource(R.drawable.fourtineb);
						}
						if(forwhat==6){
							six.setBackgroundResource(R.drawable.fourtineb);
						}
						if(forwhat==7){
							seven.setBackgroundResource(R.drawable.fourtineb);
						}
						if(forwhat==8){
							eight.setBackgroundResource(R.drawable.fourtineb);
						}

					}if( level==3 || level==4 || level==5){

						if(forwhat==9){
							nine.setBackgroundResource(R.drawable.fourtineb);
						}
						if(forwhat==10){
							ten.setBackgroundResource(R.drawable.fourtineb);
						}
						if(forwhat==11){
							eleven.setBackgroundResource(R.drawable.fourtineb);
						}
						if(forwhat==12){
							twelve.setBackgroundResource(R.drawable.fourtineb);
						}

					}



					if( level==4 || level==5){

						if(forwhat==13){
							thirtin.setBackgroundResource(R.drawable.fourtineb);
						}
						if(forwhat==14){
							fourtie.setBackgroundResource(R.drawable.fourtineb);
						}
						if(forwhat==15){
							fiftine.setBackgroundResource(R.drawable.fourtineb);
						}
						if(forwhat==16){
							sixtine.setBackgroundResource(R.drawable.fourtineb);
						}

					}

					if(  level==5){

						if(forwhat==17){
							seventine.setBackgroundResource(R.drawable.fourtineb);
						}
						if(forwhat==18){
							eightine.setBackgroundResource(R.drawable.fourtineb);
						}
						if(forwhat==19){
							nintine.setBackgroundResource(R.drawable.fourtineb);
						}
						if(forwhat==20){
							twenty.setBackgroundResource(R.drawable.fourtineb);
						}

					}


				}




				if(list.get(i)==15){
					if(level==1 || level ==2 || level==3 || level==4 || level==5){
						if(forwhat==1){
							one.setBackgroundResource(R.drawable.fivtineb);
						}
						if(forwhat==2){
							two.setBackgroundResource(R.drawable.fivtineb);
						}
						if(forwhat==3){
							three.setBackgroundResource(R.drawable.fivtineb);
						}
						if(forwhat==4){
							four.setBackgroundResource(R.drawable.fivtineb);
						}
					}



					if( level ==2 || level==3 || level==4 || level==5){

						if(forwhat==5){
							five.setBackgroundResource(R.drawable.fivtineb);
						}
						if(forwhat==6){
							six.setBackgroundResource(R.drawable.fivtineb);
						}
						if(forwhat==7){
							seven.setBackgroundResource(R.drawable.fivtineb);
						}
						if(forwhat==8){
							eight.setBackgroundResource(R.drawable.fivtineb);
						}

					}if( level==3 || level==4 || level==5){

						if(forwhat==9){
							nine.setBackgroundResource(R.drawable.fivtineb);
						}
						if(forwhat==10){
							ten.setBackgroundResource(R.drawable.fivtineb);
						}
						if(forwhat==11){
							eleven.setBackgroundResource(R.drawable.fivtineb);
						}
						if(forwhat==12){
							twelve.setBackgroundResource(R.drawable.fivtineb);
						}

					}



					if( level==4 || level==5){

						if(forwhat==13){
							thirtin.setBackgroundResource(R.drawable.fivtineb);
						}
						if(forwhat==14){
							fourtie.setBackgroundResource(R.drawable.fivtineb);
						}
						if(forwhat==15){
							fiftine.setBackgroundResource(R.drawable.fivtineb);
						}
						if(forwhat==16){
							sixtine.setBackgroundResource(R.drawable.fivtineb);
						}

					}

					if(  level==5){

						if(forwhat==17){
							seventine.setBackgroundResource(R.drawable.fivtineb);
						}
						if(forwhat==18){
							eightine.setBackgroundResource(R.drawable.fivtineb);
						}
						if(forwhat==19){
							nintine.setBackgroundResource(R.drawable.fivtineb);
						}
						if(forwhat==20){
							twenty.setBackgroundResource(R.drawable.fivtineb);
						}

					}


				}


				if(list.get(i)==16){
					if(level==1 || level ==2 || level==3 || level==4 || level==5){
						if(forwhat==1){
							one.setBackgroundResource(R.drawable.sixtineb);
						}
						if(forwhat==2){
							two.setBackgroundResource(R.drawable.sixtineb);
						}
						if(forwhat==3){
							three.setBackgroundResource(R.drawable.sixtineb);
						}
						if(forwhat==4){
							four.setBackgroundResource(R.drawable.sixtineb);
						}
					}



					if( level ==2 || level==3 || level==4 || level==5){

						if(forwhat==5){
							five.setBackgroundResource(R.drawable.sixtineb);
						}
						if(forwhat==6){
							six.setBackgroundResource(R.drawable.sixtineb);
						}
						if(forwhat==7){
							seven.setBackgroundResource(R.drawable.sixtineb);
						}
						if(forwhat==8){
							eight.setBackgroundResource(R.drawable.sixtineb);
						}

					}if( level==3 || level==4 || level==5){

						if(forwhat==9){
							nine.setBackgroundResource(R.drawable.sixtineb);
						}
						if(forwhat==10){
							ten.setBackgroundResource(R.drawable.sixtineb);
						}
						if(forwhat==11){
							eleven.setBackgroundResource(R.drawable.sixtineb);
						}
						if(forwhat==12){
							twelve.setBackgroundResource(R.drawable.sixtineb);
						}

					}



					if( level==4 || level==5){

						if(forwhat==13){
							thirtin.setBackgroundResource(R.drawable.sixtineb);
						}
						if(forwhat==14){
							fourtie.setBackgroundResource(R.drawable.sixtineb);
						}
						if(forwhat==15){
							fiftine.setBackgroundResource(R.drawable.sixtineb);
						}
						if(forwhat==16){
							sixtine.setBackgroundResource(R.drawable.sixtineb);
						}

					}

					if(  level==5){

						if(forwhat==17){
							seventine.setBackgroundResource(R.drawable.sixtineb);
						}
						if(forwhat==18){
							eightine.setBackgroundResource(R.drawable.sixtineb);
						}
						if(forwhat==19){
							nintine.setBackgroundResource(R.drawable.sixtineb);
						}
						if(forwhat==20){
							twenty.setBackgroundResource(R.drawable.sixtineb);
						}

					}


				}






				if(list.get(i)==17){
					if(level==1 || level ==2 || level==3 || level==4 || level==5){
						if(forwhat==1){
							one.setBackgroundResource(R.drawable.seventineb);
						}
						if(forwhat==2){
							two.setBackgroundResource(R.drawable.seventineb);
						}
						if(forwhat==3){
							three.setBackgroundResource(R.drawable.seventineb);
						}
						if(forwhat==4){
							four.setBackgroundResource(R.drawable.seventineb);
						}
					}



					if( level ==2 || level==3 || level==4 || level==5){

						if(forwhat==5){
							five.setBackgroundResource(R.drawable.seventineb);
						}
						if(forwhat==6){
							six.setBackgroundResource(R.drawable.seventineb);
						}
						if(forwhat==7){
							seven.setBackgroundResource(R.drawable.seventineb);
						}
						if(forwhat==8){
							eight.setBackgroundResource(R.drawable.seventineb);
						}

					}if( level==3 || level==4 || level==5){

						if(forwhat==9){
							nine.setBackgroundResource(R.drawable.seventineb);
						}
						if(forwhat==10){
							ten.setBackgroundResource(R.drawable.seventineb);
						}
						if(forwhat==11){
							eleven.setBackgroundResource(R.drawable.seventineb);
						}
						if(forwhat==12){
							twelve.setBackgroundResource(R.drawable.seventineb);
						}

					}



					if( level==4 || level==5){

						if(forwhat==13){
							thirtin.setBackgroundResource(R.drawable.seventineb);
						}
						if(forwhat==14){
							fourtie.setBackgroundResource(R.drawable.seventineb);
						}
						if(forwhat==15){
							fiftine.setBackgroundResource(R.drawable.seventineb);
						}
						if(forwhat==16){
							sixtine.setBackgroundResource(R.drawable.seventineb);
						}

					}

					if(  level==5){

						if(forwhat==17){
							seventine.setBackgroundResource(R.drawable.seventineb);
						}
						if(forwhat==18){
							eightine.setBackgroundResource(R.drawable.seventineb);
						}
						if(forwhat==19){
							nintine.setBackgroundResource(R.drawable.seventineb);
						}
						if(forwhat==20){
							twenty.setBackgroundResource(R.drawable.seventineb);
						}

					}


				}

				if(list.get(i)==18){
					if(level==1 || level ==2 || level==3 || level==4 || level==5){
						if(forwhat==1){
							one.setBackgroundResource(R.drawable.eightineb);
						}
						if(forwhat==2){
							two.setBackgroundResource(R.drawable.eightineb);
						}
						if(forwhat==3){
							three.setBackgroundResource(R.drawable.eightineb);
						}
						if(forwhat==4){
							four.setBackgroundResource(R.drawable.eightineb);
						}
					}



					if( level ==2 || level==3 || level==4 || level==5){

						if(forwhat==5){
							five.setBackgroundResource(R.drawable.eightineb);
						}
						if(forwhat==6){
							six.setBackgroundResource(R.drawable.eightineb);
						}
						if(forwhat==7){
							seven.setBackgroundResource(R.drawable.eightineb);
						}
						if(forwhat==8){
							eight.setBackgroundResource(R.drawable.eightineb);
						}

					}if( level==3 || level==4 || level==5){

						if(forwhat==9){
							nine.setBackgroundResource(R.drawable.eightineb);
						}
						if(forwhat==10){
							ten.setBackgroundResource(R.drawable.eightineb);
						}
						if(forwhat==11){
							eleven.setBackgroundResource(R.drawable.eightineb);
						}
						if(forwhat==12){
							twelve.setBackgroundResource(R.drawable.eightineb);
						}

					}



					if( level==4 || level==5){

						if(forwhat==13){
							thirtin.setBackgroundResource(R.drawable.eightineb);
						}
						if(forwhat==14){
							fourtie.setBackgroundResource(R.drawable.eightineb);
						}
						if(forwhat==15){
							fiftine.setBackgroundResource(R.drawable.eightineb);
						}
						if(forwhat==16){
							sixtine.setBackgroundResource(R.drawable.eightineb);
						}

					}

					if(  level==5){

						if(forwhat==17){
							seventine.setBackgroundResource(R.drawable.eightineb);
						}
						if(forwhat==18){
							eightine.setBackgroundResource(R.drawable.eightineb);
						}
						if(forwhat==19){
							nintine.setBackgroundResource(R.drawable.eightineb);
						}
						if(forwhat==20){
							twenty.setBackgroundResource(R.drawable.eightineb);
						}

					}


				}



				if(list.get(i)==19){
					if(level==1 || level ==2 || level==3 || level==4 || level==5){
						if(forwhat==1){
							one.setBackgroundResource(R.drawable.nintinb);
						}
						if(forwhat==2){
							two.setBackgroundResource(R.drawable.nintinb);
						}
						if(forwhat==3){
							three.setBackgroundResource(R.drawable.nintinb);
						}
						if(forwhat==4){
							four.setBackgroundResource(R.drawable.nintinb);
						}
					}



					if( level ==2 || level==3 || level==4 || level==5){

						if(forwhat==5){
							five.setBackgroundResource(R.drawable.nintinb);
						}
						if(forwhat==6){
							six.setBackgroundResource(R.drawable.nintinb);
						}
						if(forwhat==7){
							seven.setBackgroundResource(R.drawable.nintinb);
						}
						if(forwhat==8){
							eight.setBackgroundResource(R.drawable.nintinb);
						}

					}if( level==3 || level==4 || level==5){

						if(forwhat==9){
							nine.setBackgroundResource(R.drawable.nintinb);
						}
						if(forwhat==10){
							ten.setBackgroundResource(R.drawable.nintinb);
						}
						if(forwhat==11){
							eleven.setBackgroundResource(R.drawable.nintinb);
						}
						if(forwhat==12){
							twelve.setBackgroundResource(R.drawable.nintinb);
						}

					}



					if( level==4 || level==5){

						if(forwhat==13){
							thirtin.setBackgroundResource(R.drawable.nintinb);
						}
						if(forwhat==14){
							fourtie.setBackgroundResource(R.drawable.nintinb);
						}
						if(forwhat==15){
							fiftine.setBackgroundResource(R.drawable.nintinb);
						}
						if(forwhat==16){
							sixtine.setBackgroundResource(R.drawable.nintinb);
						}

					}

					if(  level==5){

						if(forwhat==17){
							seventine.setBackgroundResource(R.drawable.nintinb);
						}
						if(forwhat==18){
							eightine.setBackgroundResource(R.drawable.nintinb);
						}
						if(forwhat==19){
							nintine.setBackgroundResource(R.drawable.nintinb);
						}
						if(forwhat==20){
							twenty.setBackgroundResource(R.drawable.nintinb);
						}

					}


				}



				if(list.get(i)==20){
					if(level==1 || level ==2 || level==3 || level==4 || level==5){
						if(forwhat==1){
							one.setBackgroundResource(R.drawable.twentyb);
						}
						if(forwhat==2){
							two.setBackgroundResource(R.drawable.twentyb);
						}
						if(forwhat==3){
							three.setBackgroundResource(R.drawable.twentyb);
						}
						if(forwhat==4){
							four.setBackgroundResource(R.drawable.twentyb);
						}
					}



					if( level ==2 || level==3 || level==4 || level==5){

						if(forwhat==5){
							five.setBackgroundResource(R.drawable.twentyb);
						}
						if(forwhat==6){
							six.setBackgroundResource(R.drawable.twentyb);
						}
						if(forwhat==7){
							seven.setBackgroundResource(R.drawable.twentyb);
						}
						if(forwhat==8){
							eight.setBackgroundResource(R.drawable.twentyb);
						}

					}if( level==3 || level==4 || level==5){

						if(forwhat==9){
							nine.setBackgroundResource(R.drawable.twentyb);
						}
						if(forwhat==10){
							ten.setBackgroundResource(R.drawable.twentyb);
						}
						if(forwhat==11){
							eleven.setBackgroundResource(R.drawable.twentyb);
						}
						if(forwhat==12){
							twelve.setBackgroundResource(R.drawable.twentyb);
						}

					}



					if( level==4 || level==5){

						if(forwhat==13){
							thirtin.setBackgroundResource(R.drawable.twentyb);
						}
						if(forwhat==14){
							fourtie.setBackgroundResource(R.drawable.twentyb);
						}
						if(forwhat==15){
							fiftine.setBackgroundResource(R.drawable.twentyb);
						}
						if(forwhat==16){
							sixtine.setBackgroundResource(R.drawable.twentyb);
						}

					}

					if(  level==5){

						if(forwhat==17){
							seventine.setBackgroundResource(R.drawable.twentyb);
						}
						if(forwhat==18){
							eightine.setBackgroundResource(R.drawable.twentyb);
						}
						if(forwhat==19){
							nintine.setBackgroundResource(R.drawable.twentyb);
						}
						if(forwhat==20){
							twenty.setBackgroundResource(R.drawable.twentyb);
						}

					}


				}




				forwhat++;  }

			forwhat=1;







		}





	}



	public void gamescne3(){




		if(gamescine2==2){



			list = new ArrayList<Integer>();
			for (i=1; i<level_loop; i++) {
				list.add(new Integer(i));

			}
			Collections.shuffle(list);

			int findnumbers=level_loop-1;
			for (int i=0; i<findnumbers; i++) {


				if(list.get(i)==1){
					rand=r.nextInt(3-1)+1;
					if(rand==1){
						if(level==1 || level ==2 || level==3 || level==4 || level==5){

							if(forwhat==1){
								one.setBackgroundResource(R.drawable.oneb);
							}
							if(forwhat==2){
								two.setBackgroundResource(R.drawable.oneb);
							}
							if(forwhat==3){
								three.setBackgroundResource(R.drawable.oneb);
							}
							if(forwhat==4){
								four.setBackgroundResource(R.drawable.oneb);
							}
						}
						if( level ==2 || level==3 || level==4 || level==5){

							if(forwhat==5){
								five.setBackgroundResource(R.drawable.oneb);
							}
							if(forwhat==6){
								six.setBackgroundResource(R.drawable.oneb);
							}
							if(forwhat==7){
								seven.setBackgroundResource(R.drawable.oneb);
							}
							if(forwhat==8){
								eight.setBackgroundResource(R.drawable.oneb);
							}

						}if(level==3 || level==4 || level==5){

							if(forwhat==9){
								nine.setBackgroundResource(R.drawable.oneb);
							}
							if(forwhat==10){
								ten.setBackgroundResource(R.drawable.oneb);
							}
							if(forwhat==11){
								eleven.setBackgroundResource(R.drawable.oneb);
							}
							if(forwhat==12){
								twelve.setBackgroundResource(R.drawable.oneb);
							}

						}


						if( level==4 || level==5){

							if(forwhat==13){
								thirtin.setBackgroundResource(R.drawable.oneb);
							}
							if(forwhat==14){
								fourtie.setBackgroundResource(R.drawable.oneb);
							}
							if(forwhat==15){
								fiftine.setBackgroundResource(R.drawable.oneb);
							}
							if(forwhat==16){
								sixtine.setBackgroundResource(R.drawable.oneb);
							}

						}

						if(  level==5){

							if(forwhat==17){
								seventine.setBackgroundResource(R.drawable.oneb);
							}
							if(forwhat==18){
								eightine.setBackgroundResource(R.drawable.oneb);
							}
							if(forwhat==19){
								nintine.setBackgroundResource(R.drawable.oneb);
							}
							if(forwhat==20){
								twenty.setBackgroundResource(R.drawable.oneb);
							}

						}







					}

					if(rand==2){




						if(level==1 || level ==2 || level==3 || level==4 || level==5){

							if(forwhat==1){
								one.setBackgroundResource(R.drawable.one);
							}
							if(forwhat==2){
								two.setBackgroundResource(R.drawable.one);
							}
							if(forwhat==3){
								three.setBackgroundResource(R.drawable.one);
							}
							if(forwhat==4){
								four.setBackgroundResource(R.drawable.one);
							}
						}
						if( level ==2 || level==3 || level==4 || level==5){

							if(forwhat==5){
								five.setBackgroundResource(R.drawable.one);
							}
							if(forwhat==6){
								six.setBackgroundResource(R.drawable.one);
							}
							if(forwhat==7){
								seven.setBackgroundResource(R.drawable.one);
							}
							if(forwhat==8){
								eight.setBackgroundResource(R.drawable.one);
							}

						}if(level==3 || level==4 || level==5){

							if(forwhat==9){
								nine.setBackgroundResource(R.drawable.one);
							}
							if(forwhat==10){
								ten.setBackgroundResource(R.drawable.one);
							}
							if(forwhat==11){
								eleven.setBackgroundResource(R.drawable.one);
							}
							if(forwhat==12){
								twelve.setBackgroundResource(R.drawable.one);
							}

						}


						if( level==4 || level==5){

							if(forwhat==13){
								thirtin.setBackgroundResource(R.drawable.one);
							}
							if(forwhat==14){
								fourtie.setBackgroundResource(R.drawable.one);
							}
							if(forwhat==15){
								fiftine.setBackgroundResource(R.drawable.one);
							}
							if(forwhat==16){
								sixtine.setBackgroundResource(R.drawable.one);
							}

						}

						if(  level==5){

							if(forwhat==17){
								seventine.setBackgroundResource(R.drawable.one);
							}
							if(forwhat==18){
								eightine.setBackgroundResource(R.drawable.one);
							}
							if(forwhat==19){
								nintine.setBackgroundResource(R.drawable.one);
							}
							if(forwhat==20){
								twenty.setBackgroundResource(R.drawable.one);
							}

						}















					}






				}
				if(list.get(i)==2){
					rand=r.nextInt(3-1)+1;
					if(rand==1){
						if(level==1 || level ==2 || level==3 || level==4 || level==5){
							if(forwhat==1){
								one.setBackgroundResource(R.drawable.twob);
							}
							if(forwhat==2){
								two.setBackgroundResource(R.drawable.twob);
							}
							if(forwhat==3){
								three.setBackgroundResource(R.drawable.twob);
							}
							if(forwhat==4){
								four.setBackgroundResource(R.drawable.twob);
							}}






						if(level ==2 || level==3 || level==4 || level==5){

							if(forwhat==5){
								five.setBackgroundResource(R.drawable.twob);
							}
							if(forwhat==6){
								six.setBackgroundResource(R.drawable.twob);
							}
							if(forwhat==7){
								seven.setBackgroundResource(R.drawable.twob);
							}
							if(forwhat==8){
								eight.setBackgroundResource(R.drawable.twob);
							}

						}if( level==3 || level==4 || level==5){

							if(forwhat==9){
								nine.setBackgroundResource(R.drawable.twob);
							}
							if(forwhat==10){
								ten.setBackgroundResource(R.drawable.twob);
							}
							if(forwhat==11){
								eleven.setBackgroundResource(R.drawable.twob);
							}
							if(forwhat==12){
								twelve.setBackgroundResource(R.drawable.twob);
							}

						}

						if( level==4 || level==5){

							if(forwhat==13){
								thirtin.setBackgroundResource(R.drawable.twob);
							}
							if(forwhat==14){
								fourtie.setBackgroundResource(R.drawable.twob);
							}
							if(forwhat==15){
								fiftine.setBackgroundResource(R.drawable.twob);
							}
							if(forwhat==16){
								sixtine.setBackgroundResource(R.drawable.twob);
							}

						}

						if(  level==5){

							if(forwhat==17){
								seventine.setBackgroundResource(R.drawable.twob);
							}
							if(forwhat==18){
								eightine.setBackgroundResource(R.drawable.twob);
							}
							if(forwhat==19){
								nintine.setBackgroundResource(R.drawable.twob);
							}
							if(forwhat==20){
								twenty.setBackgroundResource(R.drawable.twob);
							}

						}
















					}



					if(rand==2){


						if(level==1 || level ==2 || level==3 || level==4 || level==5){
							if(forwhat==1){
								one.setBackgroundResource(R.drawable.two);
							}
							if(forwhat==2){
								two.setBackgroundResource(R.drawable.two);
							}
							if(forwhat==3){
								three.setBackgroundResource(R.drawable.two);
							}
							if(forwhat==4){
								four.setBackgroundResource(R.drawable.two);
							}}






						if(level ==2 || level==3 || level==4 || level==5){

							if(forwhat==5){
								five.setBackgroundResource(R.drawable.two);
							}
							if(forwhat==6){
								six.setBackgroundResource(R.drawable.two);
							}
							if(forwhat==7){
								seven.setBackgroundResource(R.drawable.two);
							}
							if(forwhat==8){
								eight.setBackgroundResource(R.drawable.two);
							}

						}if( level==3 || level==4 || level==5){

							if(forwhat==9){
								nine.setBackgroundResource(R.drawable.two);
							}
							if(forwhat==10){
								ten.setBackgroundResource(R.drawable.two);
							}
							if(forwhat==11){
								eleven.setBackgroundResource(R.drawable.two);
							}
							if(forwhat==12){
								twelve.setBackgroundResource(R.drawable.two);
							}

						}

						if( level==4 || level==5){

							if(forwhat==13){
								thirtin.setBackgroundResource(R.drawable.two);
							}
							if(forwhat==14){
								fourtie.setBackgroundResource(R.drawable.two);
							}
							if(forwhat==15){
								fiftine.setBackgroundResource(R.drawable.two);
							}
							if(forwhat==16){
								sixtine.setBackgroundResource(R.drawable.two);
							}

						}

						if(  level==5){

							if(forwhat==17){
								seventine.setBackgroundResource(R.drawable.two);
							}
							if(forwhat==18){
								eightine.setBackgroundResource(R.drawable.two);
							}
							if(forwhat==19){
								nintine.setBackgroundResource(R.drawable.two);
							}
							if(forwhat==20){
								twenty.setBackgroundResource(R.drawable.two);
							}

						}



















					}



				}
				if(list.get(i)==3){
					rand=r.nextInt(3-1)+1;
					if(rand==1){
						if(level==1 || level ==2 || level==3 || level==4 || level==5){
							if(forwhat==1){
								one.setBackgroundResource(R.drawable.threeb);
							}
							if(forwhat==2){
								two.setBackgroundResource(R.drawable.threeb);
							}
							if(forwhat==3){
								three.setBackgroundResource(R.drawable.threeb);
							}
							if(forwhat==4){
								four.setBackgroundResource(R.drawable.threeb);
							}}



						if( level ==2 || level==3 || level==4 || level==5){

							if(forwhat==5){
								five.setBackgroundResource(R.drawable.threeb);
							}
							if(forwhat==6){
								six.setBackgroundResource(R.drawable.threeb);
							}
							if(forwhat==7){
								seven.setBackgroundResource(R.drawable.threeb);
							}
							if(forwhat==8){
								eight.setBackgroundResource(R.drawable.threeb);
							}

						}if( level==3 || level==4 || level==5){

							if(forwhat==9){
								nine.setBackgroundResource(R.drawable.threeb);
							}
							if(forwhat==10){
								ten.setBackgroundResource(R.drawable.threeb);
							}
							if(forwhat==11){
								eleven.setBackgroundResource(R.drawable.threeb);
							}
							if(forwhat==12){
								twelve.setBackgroundResource(R.drawable.threeb);
							}

						}


						if( level==4 || level==5){

							if(forwhat==13){
								thirtin.setBackgroundResource(R.drawable.threeb);
							}
							if(forwhat==14){
								fourtie.setBackgroundResource(R.drawable.threeb);
							}
							if(forwhat==15){
								fiftine.setBackgroundResource(R.drawable.threeb);
							}
							if(forwhat==16){
								sixtine.setBackgroundResource(R.drawable.threeb);
							}

						}

						if(  level==5){

							if(forwhat==17){
								seventine.setBackgroundResource(R.drawable.threeb);
							}
							if(forwhat==18){
								eightine.setBackgroundResource(R.drawable.threeb);
							}
							if(forwhat==19){
								nintine.setBackgroundResource(R.drawable.threeb);
							}
							if(forwhat==20){
								twenty.setBackgroundResource(R.drawable.threeb);
							}

						}








					}

					if(rand==2){

						if(level==1 || level ==2 || level==3 || level==4 || level==5){
							if(forwhat==1){
								one.setBackgroundResource(R.drawable.three);
							}
							if(forwhat==2){
								two.setBackgroundResource(R.drawable.three);
							}
							if(forwhat==3){
								three.setBackgroundResource(R.drawable.three);
							}
							if(forwhat==4){
								four.setBackgroundResource(R.drawable.three);
							}}



						if( level ==2 || level==3 || level==4 || level==5){

							if(forwhat==5){
								five.setBackgroundResource(R.drawable.three);
							}
							if(forwhat==6){
								six.setBackgroundResource(R.drawable.three);
							}
							if(forwhat==7){
								seven.setBackgroundResource(R.drawable.three);
							}
							if(forwhat==8){
								eight.setBackgroundResource(R.drawable.three);
							}

						}if( level==3 || level==4 || level==5){

							if(forwhat==9){
								nine.setBackgroundResource(R.drawable.three);
							}
							if(forwhat==10){
								ten.setBackgroundResource(R.drawable.three);
							}
							if(forwhat==11){
								eleven.setBackgroundResource(R.drawable.three);
							}
							if(forwhat==12){
								twelve.setBackgroundResource(R.drawable.three);
							}

						}


						if( level==4 || level==5){

							if(forwhat==13){
								thirtin.setBackgroundResource(R.drawable.three);
							}
							if(forwhat==14){
								fourtie.setBackgroundResource(R.drawable.three);
							}
							if(forwhat==15){
								fiftine.setBackgroundResource(R.drawable.three);
							}
							if(forwhat==16){
								sixtine.setBackgroundResource(R.drawable.three);
							}

						}

						if(  level==5){

							if(forwhat==17){
								seventine.setBackgroundResource(R.drawable.three);
							}
							if(forwhat==18){
								eightine.setBackgroundResource(R.drawable.three);
							}
							if(forwhat==19){
								nintine.setBackgroundResource(R.drawable.three);
							}
							if(forwhat==20){
								twenty.setBackgroundResource(R.drawable.three);
							}

						}









					}

				}








				if(list.get(i)==4){
					rand=r.nextInt(3-1)+1;
					if(rand==1){
						if(level==1 || level ==2 || level==3 || level==4 || level==5){
							if(forwhat==1){
								one.setBackgroundResource(R.drawable.fourb);
							}
							if(forwhat==2){
								two.setBackgroundResource(R.drawable.fourb);
							}
							if(forwhat==3){
								three.setBackgroundResource(R.drawable.fourb);
							}
							if(forwhat==4){
								four.setBackgroundResource(R.drawable.fourb);
							}
						}



						if( level ==2 || level==3 || level==4 || level==5){

							if(forwhat==5){
								five.setBackgroundResource(R.drawable.fourb);
							}
							if(forwhat==6){
								six.setBackgroundResource(R.drawable.fourb);
							}
							if(forwhat==7){
								seven.setBackgroundResource(R.drawable.fourb);
							}
							if(forwhat==8){
								eight.setBackgroundResource(R.drawable.fourb);
							}

						}if(level==3 || level==4 || level==5){

							if(forwhat==9){
								nine.setBackgroundResource(R.drawable.fourb);
							}
							if(forwhat==10){
								ten.setBackgroundResource(R.drawable.fourb);
							}
							if(forwhat==11){
								eleven.setBackgroundResource(R.drawable.fourb);
							}
							if(forwhat==12){
								twelve.setBackgroundResource(R.drawable.fourb);
							}

						}

						if( level==4 || level==5){

							if(forwhat==13){
								thirtin.setBackgroundResource(R.drawable.fourb);
							}
							if(forwhat==14){
								fourtie.setBackgroundResource(R.drawable.fourb);
							}
							if(forwhat==15){
								fiftine.setBackgroundResource(R.drawable.fourb);
							}
							if(forwhat==16){
								sixtine.setBackgroundResource(R.drawable.fourb);
							}

						}

						if(  level==5){

							if(forwhat==17){
								seventine.setBackgroundResource(R.drawable.fourb);
							}
							if(forwhat==18){
								eightine.setBackgroundResource(R.drawable.fourb);
							}
							if(forwhat==19){
								nintine.setBackgroundResource(R.drawable.fourb);
							}
							if(forwhat==20){
								twenty.setBackgroundResource(R.drawable.fourb);
							}

						}


					}

					if(rand==2){


						if(level==1 || level ==2 || level==3 || level==4 || level==5){
							if(forwhat==1){
								one.setBackgroundResource(R.drawable.four);
							}
							if(forwhat==2){
								two.setBackgroundResource(R.drawable.four);
							}
							if(forwhat==3){
								three.setBackgroundResource(R.drawable.four);
							}
							if(forwhat==4){
								four.setBackgroundResource(R.drawable.four);
							}
						}



						if( level ==2 || level==3 || level==4 || level==5){

							if(forwhat==5){
								five.setBackgroundResource(R.drawable.four);
							}
							if(forwhat==6){
								six.setBackgroundResource(R.drawable.four);
							}
							if(forwhat==7){
								seven.setBackgroundResource(R.drawable.four);
							}
							if(forwhat==8){
								eight.setBackgroundResource(R.drawable.four);
							}

						}if(level==3 || level==4 || level==5){

							if(forwhat==9){
								nine.setBackgroundResource(R.drawable.four);
							}
							if(forwhat==10){
								ten.setBackgroundResource(R.drawable.four);
							}
							if(forwhat==11){
								eleven.setBackgroundResource(R.drawable.four);
							}
							if(forwhat==12){
								twelve.setBackgroundResource(R.drawable.four);
							}

						}

						if( level==4 || level==5){

							if(forwhat==13){
								thirtin.setBackgroundResource(R.drawable.four);
							}
							if(forwhat==14){
								fourtie.setBackgroundResource(R.drawable.four);
							}
							if(forwhat==15){
								fiftine.setBackgroundResource(R.drawable.four);
							}
							if(forwhat==16){
								sixtine.setBackgroundResource(R.drawable.four);
							}

						}

						if(  level==5){

							if(forwhat==17){
								seventine.setBackgroundResource(R.drawable.four);
							}
							if(forwhat==18){
								eightine.setBackgroundResource(R.drawable.four);
							}
							if(forwhat==19){
								nintine.setBackgroundResource(R.drawable.four);
							}
							if(forwhat==20){
								twenty.setBackgroundResource(R.drawable.four);
							}

						}





					}
				}





				if(list.get(i)==5){
					rand=r.nextInt(3-1)+1;
					if(rand==1){
						if(level==1 || level ==2 || level==3 || level==4 || level==5){
							if(forwhat==1){
								one.setBackgroundResource(R.drawable.fiveb);
							}
							if(forwhat==2){
								two.setBackgroundResource(R.drawable.fiveb);
							}
							if(forwhat==3){
								three.setBackgroundResource(R.drawable.fiveb);
							}
							if(forwhat==4){
								four.setBackgroundResource(R.drawable.fiveb);
							}
						}



						if(level ==2 || level==3 || level==4 || level==5){

							if(forwhat==5){
								five.setBackgroundResource(R.drawable.fiveb);
							}
							if(forwhat==6){
								six.setBackgroundResource(R.drawable.fiveb);
							}
							if(forwhat==7){
								seven.setBackgroundResource(R.drawable.fiveb);
							}
							if(forwhat==8){
								eight.setBackgroundResource(R.drawable.fiveb);
							}

						}if( level==3 || level==4 || level==5){

							if(forwhat==9){
								nine.setBackgroundResource(R.drawable.fiveb);
							}
							if(forwhat==10){
								ten.setBackgroundResource(R.drawable.fiveb);
							}
							if(forwhat==11){
								eleven.setBackgroundResource(R.drawable.fiveb);
							}
							if(forwhat==12){
								twelve.setBackgroundResource(R.drawable.fiveb);
							}

						}


						if( level==4 || level==5){

							if(forwhat==13){
								thirtin.setBackgroundResource(R.drawable.fiveb);
							}
							if(forwhat==14){
								fourtie.setBackgroundResource(R.drawable.fiveb);
							}
							if(forwhat==15){
								fiftine.setBackgroundResource(R.drawable.fiveb);
							}
							if(forwhat==16){
								sixtine.setBackgroundResource(R.drawable.fiveb);
							}

						}

						if(  level==5){

							if(forwhat==17){
								seventine.setBackgroundResource(R.drawable.fiveb);
							}
							if(forwhat==18){
								eightine.setBackgroundResource(R.drawable.fiveb);
							}
							if(forwhat==19){
								nintine.setBackgroundResource(R.drawable.fiveb);
							}
							if(forwhat==20){
								twenty.setBackgroundResource(R.drawable.fiveb);
							}

						}



					}

					if(rand==2){

						if(level==1 || level ==2 || level==3 || level==4 || level==5){
							if(forwhat==1){
								one.setBackgroundResource(R.drawable.five);
							}
							if(forwhat==2){
								two.setBackgroundResource(R.drawable.five);
							}
							if(forwhat==3){
								three.setBackgroundResource(R.drawable.five);
							}
							if(forwhat==4){
								four.setBackgroundResource(R.drawable.five);
							}
						}



						if(level ==2 || level==3 || level==4 || level==5){

							if(forwhat==5){
								five.setBackgroundResource(R.drawable.five);
							}
							if(forwhat==6){
								six.setBackgroundResource(R.drawable.five);
							}
							if(forwhat==7){
								seven.setBackgroundResource(R.drawable.five);
							}
							if(forwhat==8){
								eight.setBackgroundResource(R.drawable.five);
							}

						}if( level==3 || level==4 || level==5){

							if(forwhat==9){
								nine.setBackgroundResource(R.drawable.five);
							}
							if(forwhat==10){
								ten.setBackgroundResource(R.drawable.five);
							}
							if(forwhat==11){
								eleven.setBackgroundResource(R.drawable.five);
							}
							if(forwhat==12){
								twelve.setBackgroundResource(R.drawable.five);
							}

						}


						if( level==4 || level==5){

							if(forwhat==13){
								thirtin.setBackgroundResource(R.drawable.five);
							}
							if(forwhat==14){
								fourtie.setBackgroundResource(R.drawable.five);
							}
							if(forwhat==15){
								fiftine.setBackgroundResource(R.drawable.five);
							}
							if(forwhat==16){
								sixtine.setBackgroundResource(R.drawable.five);
							}

						}

						if(  level==5){

							if(forwhat==17){
								seventine.setBackgroundResource(R.drawable.five);
							}
							if(forwhat==18){
								eightine.setBackgroundResource(R.drawable.five);
							}
							if(forwhat==19){
								nintine.setBackgroundResource(R.drawable.five);
							}
							if(forwhat==20){
								twenty.setBackgroundResource(R.drawable.five);
							}

						}




					}

				}






				if(list.get(i)==6){
					rand=r.nextInt(3-1)+1;
					if(rand==1){
						if(level==1 || level ==2 || level==3 || level==4 || level==5){
							if(forwhat==1){
								one.setBackgroundResource(R.drawable.sixb);
							}
							if(forwhat==2){
								two.setBackgroundResource(R.drawable.sixb);
							}
							if(forwhat==3){
								three.setBackgroundResource(R.drawable.sixb);
							}
							if(forwhat==4){
								four.setBackgroundResource(R.drawable.sixb);
							}
						}



						if(level ==2 || level==3 || level==4 || level==5){

							if(forwhat==5){
								five.setBackgroundResource(R.drawable.sixb);
							}
							if(forwhat==6){
								six.setBackgroundResource(R.drawable.sixb);
							}
							if(forwhat==7){
								seven.setBackgroundResource(R.drawable.sixb);
							}
							if(forwhat==8){
								eight.setBackgroundResource(R.drawable.sixb);
							}

						}if( level==3 || level==4 || level==5){

							if(forwhat==9){
								nine.setBackgroundResource(R.drawable.sixb);
							}
							if(forwhat==10){
								ten.setBackgroundResource(R.drawable.sixb);
							}
							if(forwhat==11){
								eleven.setBackgroundResource(R.drawable.sixb);
							}
							if(forwhat==12){
								twelve.setBackgroundResource(R.drawable.sixb);
							}

						}



						if( level==4 || level==5){

							if(forwhat==13){
								thirtin.setBackgroundResource(R.drawable.sixb);
							}
							if(forwhat==14){
								fourtie.setBackgroundResource(R.drawable.sixb);
							}
							if(forwhat==15){
								fiftine.setBackgroundResource(R.drawable.sixb);
							}
							if(forwhat==16){
								sixtine.setBackgroundResource(R.drawable.sixb);
							}

						}

						if(  level==5){

							if(forwhat==17){
								seventine.setBackgroundResource(R.drawable.sixb);
							}
							if(forwhat==18){
								eightine.setBackgroundResource(R.drawable.sixb);
							}
							if(forwhat==19){
								nintine.setBackgroundResource(R.drawable.sixb);
							}
							if(forwhat==20){
								twenty.setBackgroundResource(R.drawable.sixb);
							}

						}
					}



					if(rand==2){


						if(level==1 || level ==2 || level==3 || level==4 || level==5){
							if(forwhat==1){
								one.setBackgroundResource(R.drawable.six);
							}
							if(forwhat==2){
								two.setBackgroundResource(R.drawable.six);
							}
							if(forwhat==3){
								three.setBackgroundResource(R.drawable.six);
							}
							if(forwhat==4){
								four.setBackgroundResource(R.drawable.six);
							}
						}



						if(level ==2 || level==3 || level==4 || level==5){

							if(forwhat==5){
								five.setBackgroundResource(R.drawable.six);
							}
							if(forwhat==6){
								six.setBackgroundResource(R.drawable.six);
							}
							if(forwhat==7){
								seven.setBackgroundResource(R.drawable.six);
							}
							if(forwhat==8){
								eight.setBackgroundResource(R.drawable.six);
							}

						}if( level==3 || level==4 || level==5){

							if(forwhat==9){
								nine.setBackgroundResource(R.drawable.six);
							}
							if(forwhat==10){
								ten.setBackgroundResource(R.drawable.six);
							}
							if(forwhat==11){
								eleven.setBackgroundResource(R.drawable.six);
							}
							if(forwhat==12){
								twelve.setBackgroundResource(R.drawable.six);
							}

						}



						if( level==4 || level==5){

							if(forwhat==13){
								thirtin.setBackgroundResource(R.drawable.six);
							}
							if(forwhat==14){
								fourtie.setBackgroundResource(R.drawable.six);
							}
							if(forwhat==15){
								fiftine.setBackgroundResource(R.drawable.six);
							}
							if(forwhat==16){
								sixtine.setBackgroundResource(R.drawable.six);
							}

						}

						if(  level==5){

							if(forwhat==17){
								seventine.setBackgroundResource(R.drawable.six);
							}
							if(forwhat==18){
								eightine.setBackgroundResource(R.drawable.six);
							}
							if(forwhat==19){
								nintine.setBackgroundResource(R.drawable.six);
							}
							if(forwhat==20){
								twenty.setBackgroundResource(R.drawable.six);
							}

						}





					}
				}








				if(list.get(i)==7){
					rand=r.nextInt(3-1)+1;

					if(rand==1){
						if(level==1 || level ==2 || level==3 || level==4 || level==5){
							if(forwhat==1){
								one.setBackgroundResource(R.drawable.sevenb);
							}
							if(forwhat==2){
								two.setBackgroundResource(R.drawable.sevenb);
							}
							if(forwhat==3){
								three.setBackgroundResource(R.drawable.sevenb);
							}
							if(forwhat==4){
								four.setBackgroundResource(R.drawable.sevenb);
							}
						}



						if(level ==2 || level==3 || level==4 || level==5){

							if(forwhat==5){
								five.setBackgroundResource(R.drawable.sevenb);
							}
							if(forwhat==6){
								six.setBackgroundResource(R.drawable.sevenb);
							}
							if(forwhat==7){
								seven.setBackgroundResource(R.drawable.sevenb);
							}
							if(forwhat==8){
								eight.setBackgroundResource(R.drawable.sevenb);
							}

						}if( level==3 || level==4 || level==5){

							if(forwhat==9){
								nine.setBackgroundResource(R.drawable.sevenb);
							}
							if(forwhat==10){
								ten.setBackgroundResource(R.drawable.sevenb);
							}
							if(forwhat==11){
								eleven.setBackgroundResource(R.drawable.sevenb);
							}
							if(forwhat==12){
								twelve.setBackgroundResource(R.drawable.sevenb);
							}

						}



						if( level==4 || level==5){

							if(forwhat==13){
								thirtin.setBackgroundResource(R.drawable.sevenb);
							}
							if(forwhat==14){
								fourtie.setBackgroundResource(R.drawable.sevenb);
							}
							if(forwhat==15){
								fiftine.setBackgroundResource(R.drawable.sevenb);
							}
							if(forwhat==16){
								sixtine.setBackgroundResource(R.drawable.sevenb);
							}

						}

						if(  level==5){

							if(forwhat==17){
								seventine.setBackgroundResource(R.drawable.sevenb);
							}
							if(forwhat==18){
								eightine.setBackgroundResource(R.drawable.sevenb);
							}
							if(forwhat==19){
								nintine.setBackgroundResource(R.drawable.sevenb);
							}
							if(forwhat==20){
								twenty.setBackgroundResource(R.drawable.sevenb);
							}

						}

					}
					if(rand==2){



						if(level==1 || level ==2 || level==3 || level==4 || level==5){
							if(forwhat==1){
								one.setBackgroundResource(R.drawable.seven);
							}
							if(forwhat==2){
								two.setBackgroundResource(R.drawable.seven);
							}
							if(forwhat==3){
								three.setBackgroundResource(R.drawable.seven);
							}
							if(forwhat==4){
								four.setBackgroundResource(R.drawable.seven);
							}
						}



						if(level ==2 || level==3 || level==4 || level==5){

							if(forwhat==5){
								five.setBackgroundResource(R.drawable.seven);
							}
							if(forwhat==6){
								six.setBackgroundResource(R.drawable.seven);
							}
							if(forwhat==7){
								seven.setBackgroundResource(R.drawable.seven);
							}
							if(forwhat==8){
								eight.setBackgroundResource(R.drawable.seven);
							}

						}if( level==3 || level==4 || level==5){

							if(forwhat==9){
								nine.setBackgroundResource(R.drawable.seven);
							}
							if(forwhat==10){
								ten.setBackgroundResource(R.drawable.seven);
							}
							if(forwhat==11){
								eleven.setBackgroundResource(R.drawable.seven);
							}
							if(forwhat==12){
								twelve.setBackgroundResource(R.drawable.seven);
							}

						}



						if( level==4 || level==5){

							if(forwhat==13){
								thirtin.setBackgroundResource(R.drawable.seven);
							}
							if(forwhat==14){
								fourtie.setBackgroundResource(R.drawable.seven);
							}
							if(forwhat==15){
								fiftine.setBackgroundResource(R.drawable.seven);
							}
							if(forwhat==16){
								sixtine.setBackgroundResource(R.drawable.seven);
							}

						}

						if(  level==5){

							if(forwhat==17){
								seventine.setBackgroundResource(R.drawable.seven);
							}
							if(forwhat==18){
								eightine.setBackgroundResource(R.drawable.seven);
							}
							if(forwhat==19){
								nintine.setBackgroundResource(R.drawable.seven);
							}
							if(forwhat==20){
								twenty.setBackgroundResource(R.drawable.seven);
							}

						}






					}

				}




				if(list.get(i)==8){
					rand=r.nextInt(3-1)+1;
					if(rand==1){
						if(level==1 || level ==2 || level==3 || level==4 || level==5){
							if(forwhat==1){
								one.setBackgroundResource(R.drawable.eightb);
							}
							if(forwhat==2){
								two.setBackgroundResource(R.drawable.eightb);
							}
							if(forwhat==3){
								three.setBackgroundResource(R.drawable.eightb);
							}
							if(forwhat==4){
								four.setBackgroundResource(R.drawable.eightb);
							}
						}



						if( level ==2 || level==3 || level==4 || level==5){

							if(forwhat==5){
								five.setBackgroundResource(R.drawable.eightb);
							}
							if(forwhat==6){
								six.setBackgroundResource(R.drawable.eightb);
							}
							if(forwhat==7){
								seven.setBackgroundResource(R.drawable.eightb);
							}
							if(forwhat==8){
								eight.setBackgroundResource(R.drawable.eightb);
							}

						}if( level==3 || level==4 || level==5){

							if(forwhat==9){
								nine.setBackgroundResource(R.drawable.eightb);
							}
							if(forwhat==10){
								ten.setBackgroundResource(R.drawable.eightb);
							}
							if(forwhat==11){
								eleven.setBackgroundResource(R.drawable.eightb);
							}
							if(forwhat==12){
								twelve.setBackgroundResource(R.drawable.eightb);
							}

						}


						if( level==4 || level==5){

							if(forwhat==13){
								thirtin.setBackgroundResource(R.drawable.eightb);
							}
							if(forwhat==14){
								fourtie.setBackgroundResource(R.drawable.eightb);
							}
							if(forwhat==15){
								fiftine.setBackgroundResource(R.drawable.eightb);
							}
							if(forwhat==16){
								sixtine.setBackgroundResource(R.drawable.eightb);
							}

						}

						if(  level==5){

							if(forwhat==17){
								seventine.setBackgroundResource(R.drawable.eightb);
							}
							if(forwhat==18){
								eightine.setBackgroundResource(R.drawable.eightb);
							}
							if(forwhat==19){
								nintine.setBackgroundResource(R.drawable.eightb);
							}
							if(forwhat==20){
								twenty.setBackgroundResource(R.drawable.eightb);
							}

						}
					}


					if(rand==2){


						if(level==1 || level ==2 || level==3 || level==4 || level==5){
							if(forwhat==1){
								one.setBackgroundResource(R.drawable.eight);
							}
							if(forwhat==2){
								two.setBackgroundResource(R.drawable.eight);
							}
							if(forwhat==3){
								three.setBackgroundResource(R.drawable.eight);
							}
							if(forwhat==4){
								four.setBackgroundResource(R.drawable.eight);
							}
						}



						if( level ==2 || level==3 || level==4 || level==5){

							if(forwhat==5){
								five.setBackgroundResource(R.drawable.eight);
							}
							if(forwhat==6){
								six.setBackgroundResource(R.drawable.eight);
							}
							if(forwhat==7){
								seven.setBackgroundResource(R.drawable.eight);
							}
							if(forwhat==8){
								eight.setBackgroundResource(R.drawable.eight);
							}

						}if( level==3 || level==4 || level==5){

							if(forwhat==9){
								nine.setBackgroundResource(R.drawable.eight);
							}
							if(forwhat==10){
								ten.setBackgroundResource(R.drawable.eight);
							}
							if(forwhat==11){
								eleven.setBackgroundResource(R.drawable.eight);
							}
							if(forwhat==12){
								twelve.setBackgroundResource(R.drawable.eight);
							}

						}


						if( level==4 || level==5){

							if(forwhat==13){
								thirtin.setBackgroundResource(R.drawable.eight);
							}
							if(forwhat==14){
								fourtie.setBackgroundResource(R.drawable.eight);
							}
							if(forwhat==15){
								fiftine.setBackgroundResource(R.drawable.eight);
							}
							if(forwhat==16){
								sixtine.setBackgroundResource(R.drawable.eight);
							}

						}

						if(  level==5){

							if(forwhat==17){
								seventine.setBackgroundResource(R.drawable.eight);
							}
							if(forwhat==18){
								eightine.setBackgroundResource(R.drawable.eight);
							}
							if(forwhat==19){
								nintine.setBackgroundResource(R.drawable.eight);
							}
							if(forwhat==20){
								twenty.setBackgroundResource(R.drawable.eight);
							}

						}




					}
				}




				if(list.get(i)==9){
					rand=r.nextInt(3-1)+1;
					if(rand==1){
						if(level==1 || level ==2 || level==3 || level==4 || level==5){
							if(forwhat==1){
								one.setBackgroundResource(R.drawable.nineb);
							}
							if(forwhat==2){
								two.setBackgroundResource(R.drawable.nineb);
							}
							if(forwhat==3){
								three.setBackgroundResource(R.drawable.nineb);
							}
							if(forwhat==4){
								four.setBackgroundResource(R.drawable.nineb);
							}
						}



						if( level ==2 || level==3 || level==4 || level==5){

							if(forwhat==5){
								five.setBackgroundResource(R.drawable.nineb);
							}
							if(forwhat==6){
								six.setBackgroundResource(R.drawable.nineb);
							}
							if(forwhat==7){
								seven.setBackgroundResource(R.drawable.nineb);
							}
							if(forwhat==8){
								eight.setBackgroundResource(R.drawable.nineb);
							}

						}if( level==3 || level==4 || level==5){

							if(forwhat==9){
								nine.setBackgroundResource(R.drawable.nineb);
							}
							if(forwhat==10){
								ten.setBackgroundResource(R.drawable.nineb);
							}
							if(forwhat==11){
								eleven.setBackgroundResource(R.drawable.nineb);
							}
							if(forwhat==12){
								twelve.setBackgroundResource(R.drawable.nineb);
							}

						}

						if( level==4 || level==5){

							if(forwhat==13){
								thirtin.setBackgroundResource(R.drawable.nineb);
							}
							if(forwhat==14){
								fourtie.setBackgroundResource(R.drawable.nineb);
							}
							if(forwhat==15){
								fiftine.setBackgroundResource(R.drawable.nineb);
							}
							if(forwhat==16){
								sixtine.setBackgroundResource(R.drawable.nineb);
							}

						}

						if(  level==5){

							if(forwhat==17){
								seventine.setBackgroundResource(R.drawable.nineb);
							}
							if(forwhat==18){
								eightine.setBackgroundResource(R.drawable.nineb);
							}
							if(forwhat==19){
								nintine.setBackgroundResource(R.drawable.nineb);
							}
							if(forwhat==20){
								twenty.setBackgroundResource(R.drawable.nineb);
							}

						}

					}
					if(rand==2){



						if(level==1 || level ==2 || level==3 || level==4 || level==5){
							if(forwhat==1){
								one.setBackgroundResource(R.drawable.nine);
							}
							if(forwhat==2){
								two.setBackgroundResource(R.drawable.nine);
							}
							if(forwhat==3){
								three.setBackgroundResource(R.drawable.nine);
							}
							if(forwhat==4){
								four.setBackgroundResource(R.drawable.nine);
							}
						}



						if( level ==2 || level==3 || level==4 || level==5){

							if(forwhat==5){
								five.setBackgroundResource(R.drawable.nine);
							}
							if(forwhat==6){
								six.setBackgroundResource(R.drawable.nine);
							}
							if(forwhat==7){
								seven.setBackgroundResource(R.drawable.nine);
							}
							if(forwhat==8){
								eight.setBackgroundResource(R.drawable.nine);
							}

						}if( level==3 || level==4 || level==5){

							if(forwhat==9){
								nine.setBackgroundResource(R.drawable.nine);
							}
							if(forwhat==10){
								ten.setBackgroundResource(R.drawable.nine);
							}
							if(forwhat==11){
								eleven.setBackgroundResource(R.drawable.nine);
							}
							if(forwhat==12){
								twelve.setBackgroundResource(R.drawable.nine);
							}

						}

						if( level==4 || level==5){

							if(forwhat==13){
								thirtin.setBackgroundResource(R.drawable.nine);
							}
							if(forwhat==14){
								fourtie.setBackgroundResource(R.drawable.nine);
							}
							if(forwhat==15){
								fiftine.setBackgroundResource(R.drawable.nine);
							}
							if(forwhat==16){
								sixtine.setBackgroundResource(R.drawable.nine);
							}

						}

						if(  level==5){

							if(forwhat==17){
								seventine.setBackgroundResource(R.drawable.nine);
							}
							if(forwhat==18){
								eightine.setBackgroundResource(R.drawable.nine);
							}
							if(forwhat==19){
								nintine.setBackgroundResource(R.drawable.nine);
							}
							if(forwhat==20){
								twenty.setBackgroundResource(R.drawable.nine);
							}

						}






					}
				}





				if(list.get(i)==10){
					rand=r.nextInt(3-1)+1;
					if(rand==1){
						if(level==1 || level ==2 || level==3 || level==4 || level==5){
							if(forwhat==1){
								one.setBackgroundResource(R.drawable.tenb);
							}
							if(forwhat==2){
								two.setBackgroundResource(R.drawable.tenb);
							}
							if(forwhat==3){
								three.setBackgroundResource(R.drawable.tenb);
							}
							if(forwhat==4){
								four.setBackgroundResource(R.drawable.tenb);
							}
						}



						if( level ==2 || level==3 || level==4 || level==5){

							if(forwhat==5){
								five.setBackgroundResource(R.drawable.tenb);
							}
							if(forwhat==6){
								six.setBackgroundResource(R.drawable.tenb);
							}
							if(forwhat==7){
								seven.setBackgroundResource(R.drawable.tenb);
							}
							if(forwhat==8){
								eight.setBackgroundResource(R.drawable.tenb);
							}

						}if( level==3 || level==4 || level==5){

							if(forwhat==9){
								nine.setBackgroundResource(R.drawable.tenb);
							}
							if(forwhat==10){
								ten.setBackgroundResource(R.drawable.tenb);
							}
							if(forwhat==11){
								eleven.setBackgroundResource(R.drawable.tenb);
							}
							if(forwhat==12){
								twelve.setBackgroundResource(R.drawable.tenb);
							}

						}


						if( level==4 || level==5){

							if(forwhat==13){
								thirtin.setBackgroundResource(R.drawable.tenb);
							}
							if(forwhat==14){
								fourtie.setBackgroundResource(R.drawable.tenb);
							}
							if(forwhat==15){
								fiftine.setBackgroundResource(R.drawable.tenb);
							}
							if(forwhat==16){
								sixtine.setBackgroundResource(R.drawable.tenb);
							}

						}

						if(  level==5){

							if(forwhat==17){
								seventine.setBackgroundResource(R.drawable.tenb);
							}
							if(forwhat==18){
								eightine.setBackgroundResource(R.drawable.tenb);
							}
							if(forwhat==19){
								nintine.setBackgroundResource(R.drawable.tenb);
							}
							if(forwhat==20){
								twenty.setBackgroundResource(R.drawable.tenb);
							}

						}

					}
					if(rand==2){



						if(level==1 || level ==2 || level==3 || level==4 || level==5){
							if(forwhat==1){
								one.setBackgroundResource(R.drawable.ten);
							}
							if(forwhat==2){
								two.setBackgroundResource(R.drawable.ten);
							}
							if(forwhat==3){
								three.setBackgroundResource(R.drawable.ten);
							}
							if(forwhat==4){
								four.setBackgroundResource(R.drawable.ten);
							}
						}



						if( level ==2 || level==3 || level==4 || level==5){

							if(forwhat==5){
								five.setBackgroundResource(R.drawable.ten);
							}
							if(forwhat==6){
								six.setBackgroundResource(R.drawable.ten);
							}
							if(forwhat==7){
								seven.setBackgroundResource(R.drawable.ten);
							}
							if(forwhat==8){
								eight.setBackgroundResource(R.drawable.ten);
							}

						}if( level==3 || level==4 || level==5){

							if(forwhat==9){
								nine.setBackgroundResource(R.drawable.ten);
							}
							if(forwhat==10){
								ten.setBackgroundResource(R.drawable.ten);
							}
							if(forwhat==11){
								eleven.setBackgroundResource(R.drawable.ten);
							}
							if(forwhat==12){
								twelve.setBackgroundResource(R.drawable.ten);
							}

						}


						if( level==4 || level==5){

							if(forwhat==13){
								thirtin.setBackgroundResource(R.drawable.ten);
							}
							if(forwhat==14){
								fourtie.setBackgroundResource(R.drawable.ten);
							}
							if(forwhat==15){
								fiftine.setBackgroundResource(R.drawable.ten);
							}
							if(forwhat==16){
								sixtine.setBackgroundResource(R.drawable.ten);
							}

						}

						if(  level==5){

							if(forwhat==17){
								seventine.setBackgroundResource(R.drawable.ten);
							}
							if(forwhat==18){
								eightine.setBackgroundResource(R.drawable.ten);
							}
							if(forwhat==19){
								nintine.setBackgroundResource(R.drawable.ten);
							}
							if(forwhat==20){
								twenty.setBackgroundResource(R.drawable.ten);
							}

						}




					}

				}




				if(list.get(i)==11){
					rand=r.nextInt(3-1)+1;
					if(rand==1){
						if(level==1 || level ==2 || level==3 || level==4 || level==5){
							if(forwhat==1){
								one.setBackgroundResource(R.drawable.elevenb);
							}
							if(forwhat==2){
								two.setBackgroundResource(R.drawable.elevenb);
							}
							if(forwhat==3){
								three.setBackgroundResource(R.drawable.elevenb);
							}
							if(forwhat==4){
								four.setBackgroundResource(R.drawable.elevenb);
							}
						}



						if( level ==2 || level==3 || level==4 || level==5){

							if(forwhat==5){
								five.setBackgroundResource(R.drawable.elevenb);
							}
							if(forwhat==6){
								six.setBackgroundResource(R.drawable.elevenb);
							}
							if(forwhat==7){
								seven.setBackgroundResource(R.drawable.elevenb);
							}
							if(forwhat==8){
								eight.setBackgroundResource(R.drawable.elevenb);
							}

						}if( level==3 || level==4 || level==5){

							if(forwhat==9){
								nine.setBackgroundResource(R.drawable.elevenb);
							}
							if(forwhat==10){
								ten.setBackgroundResource(R.drawable.elevenb);
							}
							if(forwhat==11){
								eleven.setBackgroundResource(R.drawable.elevenb);
							}
							if(forwhat==12){
								twelve.setBackgroundResource(R.drawable.elevenb);
							}

						}



						if( level==4 || level==5){

							if(forwhat==13){
								thirtin.setBackgroundResource(R.drawable.elevenb);
							}
							if(forwhat==14){
								fourtie.setBackgroundResource(R.drawable.elevenb);
							}
							if(forwhat==15){
								fiftine.setBackgroundResource(R.drawable.elevenb);
							}
							if(forwhat==16){
								sixtine.setBackgroundResource(R.drawable.elevenb);
							}

						}

						if(  level==5){

							if(forwhat==17){
								seventine.setBackgroundResource(R.drawable.elevenb);
							}
							if(forwhat==18){
								eightine.setBackgroundResource(R.drawable.elevenb);
							}
							if(forwhat==19){
								nintine.setBackgroundResource(R.drawable.elevenb);
							}
							if(forwhat==20){
								twenty.setBackgroundResource(R.drawable.elevenb);
							}

						}

					}


					if(rand==2){




						if(level==1 || level ==2 || level==3 || level==4 || level==5){
							if(forwhat==1){
								one.setBackgroundResource(R.drawable.eleven);
							}
							if(forwhat==2){
								two.setBackgroundResource(R.drawable.eleven);
							}
							if(forwhat==3){
								three.setBackgroundResource(R.drawable.eleven);
							}
							if(forwhat==4){
								four.setBackgroundResource(R.drawable.eleven);
							}
						}



						if( level ==2 || level==3 || level==4 || level==5){

							if(forwhat==5){
								five.setBackgroundResource(R.drawable.eleven);
							}
							if(forwhat==6){
								six.setBackgroundResource(R.drawable.eleven);
							}
							if(forwhat==7){
								seven.setBackgroundResource(R.drawable.eleven);
							}
							if(forwhat==8){
								eight.setBackgroundResource(R.drawable.eleven);
							}

						}if( level==3 || level==4 || level==5){

							if(forwhat==9){
								nine.setBackgroundResource(R.drawable.eleven);
							}
							if(forwhat==10){
								ten.setBackgroundResource(R.drawable.eleven);
							}
							if(forwhat==11){
								eleven.setBackgroundResource(R.drawable.eleven);
							}
							if(forwhat==12){
								twelve.setBackgroundResource(R.drawable.eleven);
							}

						}



						if( level==4 || level==5){

							if(forwhat==13){
								thirtin.setBackgroundResource(R.drawable.eleven);
							}
							if(forwhat==14){
								fourtie.setBackgroundResource(R.drawable.eleven);
							}
							if(forwhat==15){
								fiftine.setBackgroundResource(R.drawable.eleven);
							}
							if(forwhat==16){
								sixtine.setBackgroundResource(R.drawable.eleven);
							}

						}

						if(  level==5){

							if(forwhat==17){
								seventine.setBackgroundResource(R.drawable.eleven);
							}
							if(forwhat==18){
								eightine.setBackgroundResource(R.drawable.eleven);
							}
							if(forwhat==19){
								nintine.setBackgroundResource(R.drawable.eleven);
							}
							if(forwhat==20){
								twenty.setBackgroundResource(R.drawable.eleven);
							}

						}






					}

				}



				if(list.get(i)==12){
					rand=r.nextInt(3-1)+1;
					if(rand==1){
						if(level==1 || level ==2 || level==3 || level==4 || level==5){
							if(forwhat==1){
								one.setBackgroundResource(R.drawable.twelveb);
							}
							if(forwhat==2){
								two.setBackgroundResource(R.drawable.twelveb);
							}
							if(forwhat==3){
								three.setBackgroundResource(R.drawable.twelveb);
							}
							if(forwhat==4){
								four.setBackgroundResource(R.drawable.twelveb);
							}
						}



						if( level ==2 || level==3 || level==4 || level==5){

							if(forwhat==5){
								five.setBackgroundResource(R.drawable.twelveb);
							}
							if(forwhat==6){
								six.setBackgroundResource(R.drawable.twelveb);
							}
							if(forwhat==7){
								seven.setBackgroundResource(R.drawable.twelveb);
							}
							if(forwhat==8){
								eight.setBackgroundResource(R.drawable.twelveb);
							}

						}if( level==3 || level==4 || level==5){

							if(forwhat==9){
								nine.setBackgroundResource(R.drawable.twelveb);
							}
							if(forwhat==10){
								ten.setBackgroundResource(R.drawable.twelveb);
							}
							if(forwhat==11){
								eleven.setBackgroundResource(R.drawable.twelveb);
							}
							if(forwhat==12){
								twelve.setBackgroundResource(R.drawable.twelveb);
							}

						}



						if( level==4 || level==5){

							if(forwhat==13){
								thirtin.setBackgroundResource(R.drawable.twelveb);
							}
							if(forwhat==14){
								fourtie.setBackgroundResource(R.drawable.twelveb);
							}
							if(forwhat==15){
								fiftine.setBackgroundResource(R.drawable.twelveb);
							}
							if(forwhat==16){
								sixtine.setBackgroundResource(R.drawable.twelveb);
							}

						}

						if(  level==5){

							if(forwhat==17){
								seventine.setBackgroundResource(R.drawable.twelveb);
							}
							if(forwhat==18){
								eightine.setBackgroundResource(R.drawable.twelveb);
							}
							if(forwhat==19){
								nintine.setBackgroundResource(R.drawable.twelveb);
							}
							if(forwhat==20){
								twenty.setBackgroundResource(R.drawable.twelveb);
							}

						}

					}


					if(rand==2){


						if(level==1 || level ==2 || level==3 || level==4 || level==5){
							if(forwhat==1){
								one.setBackgroundResource(R.drawable.twelve);
							}
							if(forwhat==2){
								two.setBackgroundResource(R.drawable.twelve);
							}
							if(forwhat==3){
								three.setBackgroundResource(R.drawable.twelve);
							}
							if(forwhat==4){
								four.setBackgroundResource(R.drawable.twelve);
							}
						}



						if( level ==2 || level==3 || level==4 || level==5){

							if(forwhat==5){
								five.setBackgroundResource(R.drawable.twelve);
							}
							if(forwhat==6){
								six.setBackgroundResource(R.drawable.twelve);
							}
							if(forwhat==7){
								seven.setBackgroundResource(R.drawable.twelve);
							}
							if(forwhat==8){
								eight.setBackgroundResource(R.drawable.twelve);
							}

						}if( level==3 || level==4 || level==5){

							if(forwhat==9){
								nine.setBackgroundResource(R.drawable.twelve);
							}
							if(forwhat==10){
								ten.setBackgroundResource(R.drawable.twelve);
							}
							if(forwhat==11){
								eleven.setBackgroundResource(R.drawable.twelve);
							}
							if(forwhat==12){
								twelve.setBackgroundResource(R.drawable.twelve);
							}

						}



						if( level==4 || level==5){

							if(forwhat==13){
								thirtin.setBackgroundResource(R.drawable.twelve);
							}
							if(forwhat==14){
								fourtie.setBackgroundResource(R.drawable.twelve);
							}
							if(forwhat==15){
								fiftine.setBackgroundResource(R.drawable.twelve);
							}
							if(forwhat==16){
								sixtine.setBackgroundResource(R.drawable.twelve);
							}

						}

						if(  level==5){

							if(forwhat==17){
								seventine.setBackgroundResource(R.drawable.twelve);
							}
							if(forwhat==18){
								eightine.setBackgroundResource(R.drawable.twelve);
							}
							if(forwhat==19){
								nintine.setBackgroundResource(R.drawable.twelve);
							}
							if(forwhat==20){
								twenty.setBackgroundResource(R.drawable.twelve);
							}

						}



					}
				}




				if(list.get(i)==13){
					rand=r.nextInt(3-1)+1;
					if(rand==1){
						if(level==1 || level ==2 || level==3 || level==4 || level==5){
							if(forwhat==1){
								one.setBackgroundResource(R.drawable.thirtineb);
							}
							if(forwhat==2){
								two.setBackgroundResource(R.drawable.thirtineb);
							}
							if(forwhat==3){
								three.setBackgroundResource(R.drawable.thirtineb);
							}
							if(forwhat==4){
								four.setBackgroundResource(R.drawable.thirtineb);
							}
						}



						if( level ==2 || level==3 || level==4 || level==5){

							if(forwhat==5){
								five.setBackgroundResource(R.drawable.thirtineb);
							}
							if(forwhat==6){
								six.setBackgroundResource(R.drawable.thirtineb);
							}
							if(forwhat==7){
								seven.setBackgroundResource(R.drawable.thirtineb);
							}
							if(forwhat==8){
								eight.setBackgroundResource(R.drawable.thirtineb);
							}

						}if( level==3 || level==4 || level==5){

							if(forwhat==9){
								nine.setBackgroundResource(R.drawable.thirtineb);
							}
							if(forwhat==10){
								ten.setBackgroundResource(R.drawable.thirtineb);
							}
							if(forwhat==11){
								eleven.setBackgroundResource(R.drawable.thirtineb);
							}
							if(forwhat==12){
								twelve.setBackgroundResource(R.drawable.thirtineb);
							}

						}



						if( level==4 || level==5){

							if(forwhat==13){
								thirtin.setBackgroundResource(R.drawable.thirtineb);
							}
							if(forwhat==14){
								fourtie.setBackgroundResource(R.drawable.thirtineb);
							}
							if(forwhat==15){
								fiftine.setBackgroundResource(R.drawable.thirtineb);
							}
							if(forwhat==16){
								sixtine.setBackgroundResource(R.drawable.thirtineb);
							}

						}

						if(  level==5){

							if(forwhat==17){
								seventine.setBackgroundResource(R.drawable.thirtineb);
							}
							if(forwhat==18){
								eightine.setBackgroundResource(R.drawable.thirtineb);
							}
							if(forwhat==19){
								nintine.setBackgroundResource(R.drawable.thirtineb);
							}
							if(forwhat==20){
								twenty.setBackgroundResource(R.drawable.thirtineb);
							}

						}


					}

					if(rand==2){


						if(level==1 || level ==2 || level==3 || level==4 || level==5){
							if(forwhat==1){
								one.setBackgroundResource(R.drawable.thirtine);
							}
							if(forwhat==2){
								two.setBackgroundResource(R.drawable.thirtine);
							}
							if(forwhat==3){
								three.setBackgroundResource(R.drawable.thirtine);
							}
							if(forwhat==4){
								four.setBackgroundResource(R.drawable.thirtine);
							}
						}



						if( level ==2 || level==3 || level==4 || level==5){

							if(forwhat==5){
								five.setBackgroundResource(R.drawable.thirtine);
							}
							if(forwhat==6){
								six.setBackgroundResource(R.drawable.thirtine);
							}
							if(forwhat==7){
								seven.setBackgroundResource(R.drawable.thirtine);
							}
							if(forwhat==8){
								eight.setBackgroundResource(R.drawable.thirtine);
							}

						}if( level==3 || level==4 || level==5){

							if(forwhat==9){
								nine.setBackgroundResource(R.drawable.thirtine);
							}
							if(forwhat==10){
								ten.setBackgroundResource(R.drawable.thirtine);
							}
							if(forwhat==11){
								eleven.setBackgroundResource(R.drawable.thirtine);
							}
							if(forwhat==12){
								twelve.setBackgroundResource(R.drawable.thirtine);
							}

						}



						if( level==4 || level==5){

							if(forwhat==13){
								thirtin.setBackgroundResource(R.drawable.thirtine);
							}
							if(forwhat==14){
								fourtie.setBackgroundResource(R.drawable.thirtine);
							}
							if(forwhat==15){
								fiftine.setBackgroundResource(R.drawable.thirtine);
							}
							if(forwhat==16){
								sixtine.setBackgroundResource(R.drawable.thirtine);
							}

						}

						if(  level==5){

							if(forwhat==17){
								seventine.setBackgroundResource(R.drawable.thirtine);
							}
							if(forwhat==18){
								eightine.setBackgroundResource(R.drawable.thirtine);
							}
							if(forwhat==19){
								nintine.setBackgroundResource(R.drawable.thirtine);
							}
							if(forwhat==20){
								twenty.setBackgroundResource(R.drawable.thirtine);
							}

						}





					}
				}







				if(list.get(i)==14){
					rand=r.nextInt(3-1)+1;
					if(rand==1){
						if(level==1 || level ==2 || level==3 || level==4 || level==5){
							if(forwhat==1){
								one.setBackgroundResource(R.drawable.fourtineb);
							}
							if(forwhat==2){
								two.setBackgroundResource(R.drawable.fourtineb);
							}
							if(forwhat==3){
								three.setBackgroundResource(R.drawable.fourtineb);
							}
							if(forwhat==4){
								four.setBackgroundResource(R.drawable.fourtineb);
							}
						}



						if( level ==2 || level==3 || level==4 || level==5){

							if(forwhat==5){
								five.setBackgroundResource(R.drawable.fourtineb);
							}
							if(forwhat==6){
								six.setBackgroundResource(R.drawable.fourtineb);
							}
							if(forwhat==7){
								seven.setBackgroundResource(R.drawable.fourtineb);
							}
							if(forwhat==8){
								eight.setBackgroundResource(R.drawable.fourtineb);
							}

						}if( level==3 || level==4 || level==5){

							if(forwhat==9){
								nine.setBackgroundResource(R.drawable.fourtineb);
							}
							if(forwhat==10){
								ten.setBackgroundResource(R.drawable.fourtineb);
							}
							if(forwhat==11){
								eleven.setBackgroundResource(R.drawable.fourtineb);
							}
							if(forwhat==12){
								twelve.setBackgroundResource(R.drawable.fourtineb);
							}

						}



						if( level==4 || level==5){

							if(forwhat==13){
								thirtin.setBackgroundResource(R.drawable.fourtineb);
							}
							if(forwhat==14){
								fourtie.setBackgroundResource(R.drawable.fourtineb);
							}
							if(forwhat==15){
								fiftine.setBackgroundResource(R.drawable.fourtineb);
							}
							if(forwhat==16){
								sixtine.setBackgroundResource(R.drawable.fourtineb);
							}

						}

						if(  level==5){

							if(forwhat==17){
								seventine.setBackgroundResource(R.drawable.fourtineb);
							}
							if(forwhat==18){
								eightine.setBackgroundResource(R.drawable.fourtineb);
							}
							if(forwhat==19){
								nintine.setBackgroundResource(R.drawable.fourtineb);
							}
							if(forwhat==20){
								twenty.setBackgroundResource(R.drawable.fourtineb);
							}

						}

					}


					if(rand==2){


						if(level==1 || level ==2 || level==3 || level==4 || level==5){
							if(forwhat==1){
								one.setBackgroundResource(R.drawable.fourtine);
							}
							if(forwhat==2){
								two.setBackgroundResource(R.drawable.fourtine);
							}
							if(forwhat==3){
								three.setBackgroundResource(R.drawable.fourtine);
							}
							if(forwhat==4){
								four.setBackgroundResource(R.drawable.fourtine);
							}
						}



						if( level ==2 || level==3 || level==4 || level==5){

							if(forwhat==5){
								five.setBackgroundResource(R.drawable.fourtine);
							}
							if(forwhat==6){
								six.setBackgroundResource(R.drawable.fourtine);
							}
							if(forwhat==7){
								seven.setBackgroundResource(R.drawable.fourtine);
							}
							if(forwhat==8){
								eight.setBackgroundResource(R.drawable.fourtine);
							}

						}if( level==3 || level==4 || level==5){

							if(forwhat==9){
								nine.setBackgroundResource(R.drawable.fourtine);
							}
							if(forwhat==10){
								ten.setBackgroundResource(R.drawable.fourtine);
							}
							if(forwhat==11){
								eleven.setBackgroundResource(R.drawable.fourtine);
							}
							if(forwhat==12){
								twelve.setBackgroundResource(R.drawable.fourtine);
							}

						}



						if( level==4 || level==5){

							if(forwhat==13){
								thirtin.setBackgroundResource(R.drawable.fourtine);
							}
							if(forwhat==14){
								fourtie.setBackgroundResource(R.drawable.fourtine);
							}
							if(forwhat==15){
								fiftine.setBackgroundResource(R.drawable.fourtine);
							}
							if(forwhat==16){
								sixtine.setBackgroundResource(R.drawable.fourtine);
							}

						}

						if(  level==5){

							if(forwhat==17){
								seventine.setBackgroundResource(R.drawable.fourtine);
							}
							if(forwhat==18){
								eightine.setBackgroundResource(R.drawable.fourtine);
							}
							if(forwhat==19){
								nintine.setBackgroundResource(R.drawable.fourtine);
							}
							if(forwhat==20){
								twenty.setBackgroundResource(R.drawable.fourtine);
							}

						}





					}
				}




				if(list.get(i)==15){
					rand=r.nextInt(3-1)+1;
					if(rand==1){
						if(level==1 || level ==2 || level==3 || level==4 || level==5){
							if(forwhat==1){
								one.setBackgroundResource(R.drawable.fivtineb);
							}
							if(forwhat==2){
								two.setBackgroundResource(R.drawable.fivtineb);
							}
							if(forwhat==3){
								three.setBackgroundResource(R.drawable.fivtineb);
							}
							if(forwhat==4){
								four.setBackgroundResource(R.drawable.fivtineb);
							}
						}



						if( level ==2 || level==3 || level==4 || level==5){

							if(forwhat==5){
								five.setBackgroundResource(R.drawable.fivtineb);
							}
							if(forwhat==6){
								six.setBackgroundResource(R.drawable.fivtineb);
							}
							if(forwhat==7){
								seven.setBackgroundResource(R.drawable.fivtineb);
							}
							if(forwhat==8){
								eight.setBackgroundResource(R.drawable.fivtineb);
							}

						}if( level==3 || level==4 || level==5){

							if(forwhat==9){
								nine.setBackgroundResource(R.drawable.fivtineb);
							}
							if(forwhat==10){
								ten.setBackgroundResource(R.drawable.fivtineb);
							}
							if(forwhat==11){
								eleven.setBackgroundResource(R.drawable.fivtineb);
							}
							if(forwhat==12){
								twelve.setBackgroundResource(R.drawable.fivtineb);
							}

						}



						if( level==4 || level==5){

							if(forwhat==13){
								thirtin.setBackgroundResource(R.drawable.fivtineb);
							}
							if(forwhat==14){
								fourtie.setBackgroundResource(R.drawable.fivtineb);
							}
							if(forwhat==15){
								fiftine.setBackgroundResource(R.drawable.fivtineb);
							}
							if(forwhat==16){
								sixtine.setBackgroundResource(R.drawable.fivtineb);
							}

						}

						if(  level==5){

							if(forwhat==17){
								seventine.setBackgroundResource(R.drawable.fivtineb);
							}
							if(forwhat==18){
								eightine.setBackgroundResource(R.drawable.fivtineb);
							}
							if(forwhat==19){
								nintine.setBackgroundResource(R.drawable.fivtineb);
							}
							if(forwhat==20){
								twenty.setBackgroundResource(R.drawable.fivtineb);
							}

						}


					}


					if(rand==2){


						if(level==1 || level ==2 || level==3 || level==4 || level==5){
							if(forwhat==1){
								one.setBackgroundResource(R.drawable.fivtine);
							}
							if(forwhat==2){
								two.setBackgroundResource(R.drawable.fivtine);
							}
							if(forwhat==3){
								three.setBackgroundResource(R.drawable.fivtine);
							}
							if(forwhat==4){
								four.setBackgroundResource(R.drawable.fivtine);
							}
						}



						if( level ==2 || level==3 || level==4 || level==5){

							if(forwhat==5){
								five.setBackgroundResource(R.drawable.fivtine);
							}
							if(forwhat==6){
								six.setBackgroundResource(R.drawable.fivtine);
							}
							if(forwhat==7){
								seven.setBackgroundResource(R.drawable.fivtine);
							}
							if(forwhat==8){
								eight.setBackgroundResource(R.drawable.fivtine);
							}

						}if( level==3 || level==4 || level==5){

							if(forwhat==9){
								nine.setBackgroundResource(R.drawable.fivtine);
							}
							if(forwhat==10){
								ten.setBackgroundResource(R.drawable.fivtine);
							}
							if(forwhat==11){
								eleven.setBackgroundResource(R.drawable.fivtine);
							}
							if(forwhat==12){
								twelve.setBackgroundResource(R.drawable.fivtine);
							}

						}



						if( level==4 || level==5){

							if(forwhat==13){
								thirtin.setBackgroundResource(R.drawable.fivtine);
							}
							if(forwhat==14){
								fourtie.setBackgroundResource(R.drawable.fivtine);
							}
							if(forwhat==15){
								fiftine.setBackgroundResource(R.drawable.fivtine);
							}
							if(forwhat==16){
								sixtine.setBackgroundResource(R.drawable.fivtine);
							}

						}

						if(  level==5){

							if(forwhat==17){
								seventine.setBackgroundResource(R.drawable.fivtine);
							}
							if(forwhat==18){
								eightine.setBackgroundResource(R.drawable.fivtine);
							}
							if(forwhat==19){
								nintine.setBackgroundResource(R.drawable.fivtine);
							}
							if(forwhat==20){
								twenty.setBackgroundResource(R.drawable.fivtine);
							}

						}






					}
				}

				if(list.get(i)==16){
					rand=r.nextInt(3-1)+1;
					if(rand==1){
						if(level==1 || level ==2 || level==3 || level==4 || level==5){
							if(forwhat==1){
								one.setBackgroundResource(R.drawable.sixtineb);
							}
							if(forwhat==2){
								two.setBackgroundResource(R.drawable.sixtineb);
							}
							if(forwhat==3){
								three.setBackgroundResource(R.drawable.sixtineb);
							}
							if(forwhat==4){
								four.setBackgroundResource(R.drawable.sixtineb);
							}
						}



						if( level ==2 || level==3 || level==4 || level==5){

							if(forwhat==5){
								five.setBackgroundResource(R.drawable.sixtineb);
							}
							if(forwhat==6){
								six.setBackgroundResource(R.drawable.sixtineb);
							}
							if(forwhat==7){
								seven.setBackgroundResource(R.drawable.sixtineb);
							}
							if(forwhat==8){
								eight.setBackgroundResource(R.drawable.sixtineb);
							}

						}if( level==3 || level==4 || level==5){

							if(forwhat==9){
								nine.setBackgroundResource(R.drawable.sixtineb);
							}
							if(forwhat==10){
								ten.setBackgroundResource(R.drawable.sixtineb);
							}
							if(forwhat==11){
								eleven.setBackgroundResource(R.drawable.sixtineb);
							}
							if(forwhat==12){
								twelve.setBackgroundResource(R.drawable.sixtineb);
							}

						}



						if( level==4 || level==5){

							if(forwhat==13){
								thirtin.setBackgroundResource(R.drawable.sixtineb);
							}
							if(forwhat==14){
								fourtie.setBackgroundResource(R.drawable.sixtineb);
							}
							if(forwhat==15){
								fiftine.setBackgroundResource(R.drawable.sixtineb);
							}
							if(forwhat==16){
								sixtine.setBackgroundResource(R.drawable.sixtineb);
							}

						}

						if(  level==5){

							if(forwhat==17){
								seventine.setBackgroundResource(R.drawable.sixtineb);
							}
							if(forwhat==18){
								eightine.setBackgroundResource(R.drawable.sixtineb);
							}
							if(forwhat==19){
								nintine.setBackgroundResource(R.drawable.sixtineb);
							}
							if(forwhat==20){
								twenty.setBackgroundResource(R.drawable.sixtineb);
							}

						}

					}

					if(rand==2){

						if(level==1 || level ==2 || level==3 || level==4 || level==5){
							if(forwhat==1){
								one.setBackgroundResource(R.drawable.sixtine);
							}
							if(forwhat==2){
								two.setBackgroundResource(R.drawable.sixtine);
							}
							if(forwhat==3){
								three.setBackgroundResource(R.drawable.sixtine);
							}
							if(forwhat==4){
								four.setBackgroundResource(R.drawable.sixtine);
							}
						}



						if( level ==2 || level==3 || level==4 || level==5){

							if(forwhat==5){
								five.setBackgroundResource(R.drawable.sixtine);
							}
							if(forwhat==6){
								six.setBackgroundResource(R.drawable.sixtine);
							}
							if(forwhat==7){
								seven.setBackgroundResource(R.drawable.sixtine);
							}
							if(forwhat==8){
								eight.setBackgroundResource(R.drawable.sixtine);
							}

						}if( level==3 || level==4 || level==5){

							if(forwhat==9){
								nine.setBackgroundResource(R.drawable.sixtine);
							}
							if(forwhat==10){
								ten.setBackgroundResource(R.drawable.sixtine);
							}
							if(forwhat==11){
								eleven.setBackgroundResource(R.drawable.sixtine);
							}
							if(forwhat==12){
								twelve.setBackgroundResource(R.drawable.sixtine);
							}

						}



						if( level==4 || level==5){

							if(forwhat==13){
								thirtin.setBackgroundResource(R.drawable.sixtine);
							}
							if(forwhat==14){
								fourtie.setBackgroundResource(R.drawable.sixtine);
							}
							if(forwhat==15){
								fiftine.setBackgroundResource(R.drawable.sixtine);
							}
							if(forwhat==16){
								sixtine.setBackgroundResource(R.drawable.sixtine);
							}

						}

						if(  level==5){

							if(forwhat==17){
								seventine.setBackgroundResource(R.drawable.sixtine);
							}
							if(forwhat==18){
								eightine.setBackgroundResource(R.drawable.sixtine);
							}
							if(forwhat==19){
								nintine.setBackgroundResource(R.drawable.sixtine);
							}
							if(forwhat==20){
								twenty.setBackgroundResource(R.drawable.sixtine);
							}

						}


					}

				}






				if(list.get(i)==17){
					rand=r.nextInt(3-1)+1;
					if(rand==1){
						if(level==1 || level ==2 || level==3 || level==4 || level==5){
							if(forwhat==1){
								one.setBackgroundResource(R.drawable.seventineb);
							}
							if(forwhat==2){
								two.setBackgroundResource(R.drawable.seventineb);
							}
							if(forwhat==3){
								three.setBackgroundResource(R.drawable.seventineb);
							}
							if(forwhat==4){
								four.setBackgroundResource(R.drawable.seventineb);
							}
						}



						if( level ==2 || level==3 || level==4 || level==5){

							if(forwhat==5){
								five.setBackgroundResource(R.drawable.seventineb);
							}
							if(forwhat==6){
								six.setBackgroundResource(R.drawable.seventineb);
							}
							if(forwhat==7){
								seven.setBackgroundResource(R.drawable.seventineb);
							}
							if(forwhat==8){
								eight.setBackgroundResource(R.drawable.seventineb);
							}

						}if( level==3 || level==4 || level==5){

							if(forwhat==9){
								nine.setBackgroundResource(R.drawable.seventineb);
							}
							if(forwhat==10){
								ten.setBackgroundResource(R.drawable.seventineb);
							}
							if(forwhat==11){
								eleven.setBackgroundResource(R.drawable.seventineb);
							}
							if(forwhat==12){
								twelve.setBackgroundResource(R.drawable.seventineb);
							}

						}



						if( level==4 || level==5){

							if(forwhat==13){
								thirtin.setBackgroundResource(R.drawable.seventineb);
							}
							if(forwhat==14){
								fourtie.setBackgroundResource(R.drawable.seventineb);
							}
							if(forwhat==15){
								fiftine.setBackgroundResource(R.drawable.seventineb);
							}
							if(forwhat==16){
								sixtine.setBackgroundResource(R.drawable.seventineb);
							}

						}

						if(  level==5){

							if(forwhat==17){
								seventine.setBackgroundResource(R.drawable.seventineb);
							}
							if(forwhat==18){
								eightine.setBackgroundResource(R.drawable.seventineb);
							}
							if(forwhat==19){
								nintine.setBackgroundResource(R.drawable.seventineb);
							}
							if(forwhat==20){
								twenty.setBackgroundResource(R.drawable.seventineb);
							}

						}


					}


					if(rand==2){
						if(level==1 || level ==2 || level==3 || level==4 || level==5){
							if(forwhat==1){
								one.setBackgroundResource(R.drawable.seventine);
							}
							if(forwhat==2){
								two.setBackgroundResource(R.drawable.seventine);
							}
							if(forwhat==3){
								three.setBackgroundResource(R.drawable.seventine);
							}
							if(forwhat==4){
								four.setBackgroundResource(R.drawable.seventine);
							}
						}



						if( level ==2 || level==3 || level==4 || level==5){

							if(forwhat==5){
								five.setBackgroundResource(R.drawable.seventine);
							}
							if(forwhat==6){
								six.setBackgroundResource(R.drawable.seventine);
							}
							if(forwhat==7){
								seven.setBackgroundResource(R.drawable.seventine);
							}
							if(forwhat==8){
								eight.setBackgroundResource(R.drawable.seventine);
							}

						}if( level==3 || level==4 || level==5){

							if(forwhat==9){
								nine.setBackgroundResource(R.drawable.seventine);
							}
							if(forwhat==10){
								ten.setBackgroundResource(R.drawable.seventine);
							}
							if(forwhat==11){
								eleven.setBackgroundResource(R.drawable.seventine);
							}
							if(forwhat==12){
								twelve.setBackgroundResource(R.drawable.seventine);
							}

						}



						if( level==4 || level==5){

							if(forwhat==13){
								thirtin.setBackgroundResource(R.drawable.seventine);
							}
							if(forwhat==14){
								fourtie.setBackgroundResource(R.drawable.seventine);
							}
							if(forwhat==15){
								fiftine.setBackgroundResource(R.drawable.seventine);
							}
							if(forwhat==16){
								sixtine.setBackgroundResource(R.drawable.seventine);
							}

						}

						if(  level==5){

							if(forwhat==17){
								seventine.setBackgroundResource(R.drawable.seventine);
							}
							if(forwhat==18){
								eightine.setBackgroundResource(R.drawable.seventine);
							}
							if(forwhat==19){
								nintine.setBackgroundResource(R.drawable.seventine);
							}
							if(forwhat==20){
								twenty.setBackgroundResource(R.drawable.seventine);
							}

						}


					}


				}

				if(list.get(i)==18){
					rand=r.nextInt(3-1)+1;
					if(rand==1){
						if(level==1 || level ==2 || level==3 || level==4 || level==5){
							if(forwhat==1){
								one.setBackgroundResource(R.drawable.eightineb);
							}
							if(forwhat==2){
								two.setBackgroundResource(R.drawable.eightineb);
							}
							if(forwhat==3){
								three.setBackgroundResource(R.drawable.eightineb);
							}
							if(forwhat==4){
								four.setBackgroundResource(R.drawable.eightineb);
							}
						}



						if( level ==2 || level==3 || level==4 || level==5){

							if(forwhat==5){
								five.setBackgroundResource(R.drawable.eightineb);
							}
							if(forwhat==6){
								six.setBackgroundResource(R.drawable.eightineb);
							}
							if(forwhat==7){
								seven.setBackgroundResource(R.drawable.eightineb);
							}
							if(forwhat==8){
								eight.setBackgroundResource(R.drawable.eightineb);
							}

						}if( level==3 || level==4 || level==5){

							if(forwhat==9){
								nine.setBackgroundResource(R.drawable.eightineb);
							}
							if(forwhat==10){
								ten.setBackgroundResource(R.drawable.eightineb);
							}
							if(forwhat==11){
								eleven.setBackgroundResource(R.drawable.eightineb);
							}
							if(forwhat==12){
								twelve.setBackgroundResource(R.drawable.eightineb);
							}

						}



						if( level==4 || level==5){

							if(forwhat==13){
								thirtin.setBackgroundResource(R.drawable.eightineb);
							}
							if(forwhat==14){
								fourtie.setBackgroundResource(R.drawable.eightineb);
							}
							if(forwhat==15){
								fiftine.setBackgroundResource(R.drawable.eightineb);
							}
							if(forwhat==16){
								sixtine.setBackgroundResource(R.drawable.eightineb);
							}

						}

						if(  level==5){

							if(forwhat==17){
								seventine.setBackgroundResource(R.drawable.eightineb);
							}
							if(forwhat==18){
								eightine.setBackgroundResource(R.drawable.eightineb);
							}
							if(forwhat==19){
								nintine.setBackgroundResource(R.drawable.eightineb);
							}
							if(forwhat==20){
								twenty.setBackgroundResource(R.drawable.eightineb);
							}

						}


					}


					if(rand==2){


						if(level==1 || level ==2 || level==3 || level==4 || level==5){
							if(forwhat==1){
								one.setBackgroundResource(R.drawable.eightine);
							}
							if(forwhat==2){
								two.setBackgroundResource(R.drawable.eightine);
							}
							if(forwhat==3){
								three.setBackgroundResource(R.drawable.eightine);
							}
							if(forwhat==4){
								four.setBackgroundResource(R.drawable.eightine);
							}
						}



						if( level ==2 || level==3 || level==4 || level==5){

							if(forwhat==5){
								five.setBackgroundResource(R.drawable.eightine);
							}
							if(forwhat==6){
								six.setBackgroundResource(R.drawable.eightine);
							}
							if(forwhat==7){
								seven.setBackgroundResource(R.drawable.eightine);
							}
							if(forwhat==8){
								eight.setBackgroundResource(R.drawable.eightine);
							}

						}if( level==3 || level==4 || level==5){

							if(forwhat==9){
								nine.setBackgroundResource(R.drawable.eightine);
							}
							if(forwhat==10){
								ten.setBackgroundResource(R.drawable.eightine);
							}
							if(forwhat==11){
								eleven.setBackgroundResource(R.drawable.eightine);
							}
							if(forwhat==12){
								twelve.setBackgroundResource(R.drawable.eightine);
							}

						}



						if( level==4 || level==5){

							if(forwhat==13){
								thirtin.setBackgroundResource(R.drawable.eightine);
							}
							if(forwhat==14){
								fourtie.setBackgroundResource(R.drawable.eightine);
							}
							if(forwhat==15){
								fiftine.setBackgroundResource(R.drawable.eightine);
							}
							if(forwhat==16){
								sixtine.setBackgroundResource(R.drawable.eightine);
							}

						}

						if(  level==5){

							if(forwhat==17){
								seventine.setBackgroundResource(R.drawable.eightine);
							}
							if(forwhat==18){
								eightine.setBackgroundResource(R.drawable.eightine);
							}
							if(forwhat==19){
								nintine.setBackgroundResource(R.drawable.eightine);
							}
							if(forwhat==20){
								twenty.setBackgroundResource(R.drawable.eightine);
							}

						}





					}


				}



				if(list.get(i)==19){
					rand=r.nextInt(3-1)+1;
					if(rand==1){
						if(level==1 || level ==2 || level==3 || level==4 || level==5){
							if(forwhat==1){
								one.setBackgroundResource(R.drawable.nintinb);
							}
							if(forwhat==2){
								two.setBackgroundResource(R.drawable.nintinb);
							}
							if(forwhat==3){
								three.setBackgroundResource(R.drawable.nintinb);
							}
							if(forwhat==4){
								four.setBackgroundResource(R.drawable.nintinb);
							}
						}



						if( level ==2 || level==3 || level==4 || level==5){

							if(forwhat==5){
								five.setBackgroundResource(R.drawable.nintinb);
							}
							if(forwhat==6){
								six.setBackgroundResource(R.drawable.nintinb);
							}
							if(forwhat==7){
								seven.setBackgroundResource(R.drawable.nintinb);
							}
							if(forwhat==8){
								eight.setBackgroundResource(R.drawable.nintinb);
							}

						}if( level==3 || level==4 || level==5){

							if(forwhat==9){
								nine.setBackgroundResource(R.drawable.nintinb);
							}
							if(forwhat==10){
								ten.setBackgroundResource(R.drawable.nintinb);
							}
							if(forwhat==11){
								eleven.setBackgroundResource(R.drawable.nintinb);
							}
							if(forwhat==12){
								twelve.setBackgroundResource(R.drawable.nintinb);
							}

						}



						if( level==4 || level==5){

							if(forwhat==13){
								thirtin.setBackgroundResource(R.drawable.nintinb);
							}
							if(forwhat==14){
								fourtie.setBackgroundResource(R.drawable.nintinb);
							}
							if(forwhat==15){
								fiftine.setBackgroundResource(R.drawable.nintinb);
							}
							if(forwhat==16){
								sixtine.setBackgroundResource(R.drawable.nintinb);
							}

						}

						if(  level==5){

							if(forwhat==17){
								seventine.setBackgroundResource(R.drawable.nintinb);
							}
							if(forwhat==18){
								eightine.setBackgroundResource(R.drawable.nintinb);
							}
							if(forwhat==19){
								nintine.setBackgroundResource(R.drawable.nintinb);
							}
							if(forwhat==20){
								twenty.setBackgroundResource(R.drawable.nintinb);
							}

						}


					}


					if(rand==2){





						if(level==1 || level ==2 || level==3 || level==4 || level==5){
							if(forwhat==1){
								one.setBackgroundResource(R.drawable.nintin);
							}
							if(forwhat==2){
								two.setBackgroundResource(R.drawable.nintin);
							}
							if(forwhat==3){
								three.setBackgroundResource(R.drawable.nintin);
							}
							if(forwhat==4){
								four.setBackgroundResource(R.drawable.nintin);
							}
						}



						if( level ==2 || level==3 || level==4 || level==5){

							if(forwhat==5){
								five.setBackgroundResource(R.drawable.nintin);
							}
							if(forwhat==6){
								six.setBackgroundResource(R.drawable.nintin);
							}
							if(forwhat==7){
								seven.setBackgroundResource(R.drawable.nintin);
							}
							if(forwhat==8){
								eight.setBackgroundResource(R.drawable.nintin);
							}

						}if( level==3 || level==4 || level==5){

							if(forwhat==9){
								nine.setBackgroundResource(R.drawable.nintin);
							}
							if(forwhat==10){
								ten.setBackgroundResource(R.drawable.nintin);
							}
							if(forwhat==11){
								eleven.setBackgroundResource(R.drawable.nintin);
							}
							if(forwhat==12){
								twelve.setBackgroundResource(R.drawable.nintin);
							}

						}



						if( level==4 || level==5){

							if(forwhat==13){
								thirtin.setBackgroundResource(R.drawable.nintin);
							}
							if(forwhat==14){
								fourtie.setBackgroundResource(R.drawable.nintin);
							}
							if(forwhat==15){
								fiftine.setBackgroundResource(R.drawable.nintin);
							}
							if(forwhat==16){
								sixtine.setBackgroundResource(R.drawable.nintin);
							}

						}

						if(  level==5){

							if(forwhat==17){
								seventine.setBackgroundResource(R.drawable.nintin);
							}
							if(forwhat==18){
								eightine.setBackgroundResource(R.drawable.nintin);
							}
							if(forwhat==19){
								nintine.setBackgroundResource(R.drawable.nintin);
							}
							if(forwhat==20){
								twenty.setBackgroundResource(R.drawable.nintin);
							}

						}






					}

				}



				if(list.get(i)==20){
					rand=r.nextInt(3-1)+1;
					if(rand==1){
						if(level==1 || level ==2 || level==3 || level==4 || level==5){
							if(forwhat==1){
								one.setBackgroundResource(R.drawable.twentyb);
							}
							if(forwhat==2){
								two.setBackgroundResource(R.drawable.twentyb);
							}
							if(forwhat==3){
								three.setBackgroundResource(R.drawable.twentyb);
							}
							if(forwhat==4){
								four.setBackgroundResource(R.drawable.twentyb);
							}
						}



						if( level ==2 || level==3 || level==4 || level==5){

							if(forwhat==5){
								five.setBackgroundResource(R.drawable.twentyb);
							}
							if(forwhat==6){
								six.setBackgroundResource(R.drawable.twentyb);
							}
							if(forwhat==7){
								seven.setBackgroundResource(R.drawable.twentyb);
							}
							if(forwhat==8){
								eight.setBackgroundResource(R.drawable.twentyb);
							}

						}if( level==3 || level==4 || level==5){

							if(forwhat==9){
								nine.setBackgroundResource(R.drawable.twentyb);
							}
							if(forwhat==10){
								ten.setBackgroundResource(R.drawable.twentyb);
							}
							if(forwhat==11){
								eleven.setBackgroundResource(R.drawable.twentyb);
							}
							if(forwhat==12){
								twelve.setBackgroundResource(R.drawable.twentyb);
							}

						}



						if( level==4 || level==5){

							if(forwhat==13){
								thirtin.setBackgroundResource(R.drawable.twentyb);
							}
							if(forwhat==14){
								fourtie.setBackgroundResource(R.drawable.twentyb);
							}
							if(forwhat==15){
								fiftine.setBackgroundResource(R.drawable.twentyb);
							}
							if(forwhat==16){
								sixtine.setBackgroundResource(R.drawable.twentyb);
							}

						}

						if(  level==5){

							if(forwhat==17){
								seventine.setBackgroundResource(R.drawable.twentyb);
							}
							if(forwhat==18){
								eightine.setBackgroundResource(R.drawable.twentyb);
							}
							if(forwhat==19){
								nintine.setBackgroundResource(R.drawable.twentyb);
							}
							if(forwhat==20){
								twenty.setBackgroundResource(R.drawable.twentyb);
							}

						}


					}

					if(rand==2){


						if(level==1 || level ==2 || level==3 || level==4 || level==5){
							if(forwhat==1){
								one.setBackgroundResource(R.drawable.twenty);
							}
							if(forwhat==2){
								two.setBackgroundResource(R.drawable.twenty);
							}
							if(forwhat==3){
								three.setBackgroundResource(R.drawable.twenty);
							}
							if(forwhat==4){
								four.setBackgroundResource(R.drawable.twenty);
							}
						}



						if( level ==2 || level==3 || level==4 || level==5){

							if(forwhat==5){
								five.setBackgroundResource(R.drawable.twenty);
							}
							if(forwhat==6){
								six.setBackgroundResource(R.drawable.twenty);
							}
							if(forwhat==7){
								seven.setBackgroundResource(R.drawable.twenty);
							}
							if(forwhat==8){
								eight.setBackgroundResource(R.drawable.twenty);
							}

						}if( level==3 || level==4 || level==5){

							if(forwhat==9){
								nine.setBackgroundResource(R.drawable.twenty);
							}
							if(forwhat==10){
								ten.setBackgroundResource(R.drawable.twenty);
							}
							if(forwhat==11){
								eleven.setBackgroundResource(R.drawable.twenty);
							}
							if(forwhat==12){
								twelve.setBackgroundResource(R.drawable.twenty);
							}

						}



						if( level==4 || level==5){

							if(forwhat==13){
								thirtin.setBackgroundResource(R.drawable.twenty);
							}
							if(forwhat==14){
								fourtie.setBackgroundResource(R.drawable.twenty);
							}
							if(forwhat==15){
								fiftine.setBackgroundResource(R.drawable.twenty);
							}
							if(forwhat==16){
								sixtine.setBackgroundResource(R.drawable.twenty);
							}

						}

						if(  level==5){

							if(forwhat==17){
								seventine.setBackgroundResource(R.drawable.twenty);
							}
							if(forwhat==18){
								eightine.setBackgroundResource(R.drawable.twenty);
							}
							if(forwhat==19){
								nintine.setBackgroundResource(R.drawable.twenty);
							}
							if(forwhat==20){
								twenty.setBackgroundResource(R.drawable.twenty);
							}

						}





					}

				}




				forwhat++;  }

			forwhat=1;







		}








	}












}
