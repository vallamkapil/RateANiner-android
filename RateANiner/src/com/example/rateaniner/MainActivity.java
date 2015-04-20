package com.example.rateaniner;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.app.FragmentManager;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;

public class MainActivity extends Activity implements StartupFragment.OnFragmentInteractionListener,
													  LoginFragment.OnFragmentInteractionListener,
													  ForgotPasswordFragment.OnFragmentInteractionListener,
													  RegisterFragment.OnFragmentInteractionListener{

	ImageView splashLogo;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
 
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		
		getFragmentManager().beginTransaction()
				.add(R.id.Relative, new StartupFragment(), "splash").commit();
		
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				
				FragmentManager fragManager = getFragmentManager();
				
				fragManager.beginTransaction()
						.setCustomAnimations(R.anim.fadein,
				                R.anim.fadeout)
						.replace(R.id.Relative, new LoginFragment(), "login")
						.commit();
				
			}
		}, 4000);
	}

	@Override
	public void onFragmentInteraction(Uri uri) {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public void onBackPressed() {
	    if (getFragmentManager().getBackStackEntryCount() == 0) {
	        this.finish();
	    } else {
	        getFragmentManager().popBackStack();
	    }
	}
	
	@Override
	public void gotoForgotPassword() {
		// TODO Auto-generated method stub
		
		FragmentManager fragManager = getFragmentManager();
		android.app.FragmentTransaction ft = fragManager.beginTransaction();
		ft.addToBackStack(null);
		
		
		ft.setCustomAnimations(R.anim.fadein, R.anim.fadeout)
		  .replace(R.id.Relative, new ForgotPasswordFragment(), "forgotpassword")
		  .commit();

	}
	
	@Override
	public void gotoLogin() {
		// TODO Auto-generated method stub
		
		FragmentManager fragManager = getFragmentManager();
		
		fragManager.beginTransaction()
				.setCustomAnimations(R.anim.fadeinslower,
		                R.anim.fadeoutslower)
				.replace(R.id.Relative, new LoginFragment(), "login")
				.commit();

	}
	
	@Override
	public void gotoRegister() {
		// TODO Auto-generated method stub
		
		FragmentManager fragManager = getFragmentManager();
		android.app.FragmentTransaction ft = fragManager.beginTransaction();
		ft.addToBackStack(null);
		
		
		ft.setCustomAnimations(R.anim.fadein, R.anim.fadeout)
		  .replace(R.id.Relative, new RegisterFragment(), "register")
		  .commit();

	}
	
	
	
}
