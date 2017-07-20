package com.example.emergency;

import android.support.v7.app.ActionBarActivity;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;


@SuppressLint("NewApi") public class MainActivity extends Activity {

    private ImageButton btnclick;

	@SuppressLint("NewApi") @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       
        ActionBar ab = getActionBar();
        ab.setTitle("E-Helper");
        ab.setBackgroundDrawable(new android.graphics.drawable.ColorDrawable(Color.parseColor("#D62741")));
        ab.show();
        
       /* ImageView spaceshipImage = (ImageView) findViewById(R.id.imageView1);*/
      /*  Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(this, R.anim.hyperspace_jump);
        spaceshipImage.startAnimation(hyperspaceJumpAnimation);*/
        
        
        final Animation animAlpha = AnimationUtils.loadAnimation(this,
				R.anim.anim_button);
		final MediaPlayer mp = MediaPlayer.create(this, R.raw.click);
		btnclick = (ImageButton)findViewById(R.id.imagebutton1);

		btnclick.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				
				arg0.startAnimation(animAlpha);
				
				mp.start();
				Intent i = new Intent(MainActivity.this, FirstScreen.class);
				startActivity(i);
			}
		});
   
    /* // METHOD 1     
        
       *//****** Create Thread that will sleep for 5 seconds *************//*        
       Thread background = new Thread() {
           public void run() {
                
               try {
                   // Thread will sleep for 5 seconds
                   sleep(3*1000);
                    
                   // After 5 seconds redirect to another intent
                   Intent i=new Intent(getBaseContext(),FirstScreen.class);
                   startActivity(i);
                    
                   //Remove activity
                   finish();
                    
               } catch (Exception e) {
                
               }
           }
       };
       
        
       // start thread
       background.start();
        
//METHOD 2  
        
       
       new Handler().postDelayed(new Runnable() {
             
           // Using handler with postDelayed called runnable run method
 
           @Override
           public void run() {
               Intent i = new Intent(MainSplashScreen.this, FirstScreen.class);
               startActivity(i);
 
               // close this activity
               finish();
           }
       }, 5*1000); // wait for 5 seconds
       
   
   }
    
    protected void onDestroy() {
        
        super.onDestroy();
    }*/
}
}
