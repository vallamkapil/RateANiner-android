package com.example.rateaniner;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class WriteReviewActivity extends Activity {

	AlertDialog levelDialog = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.duplicate_fragment_startup);
		/*
		EditText classTaken = (EditText)findViewById(R.id.classEditText);
		
		classTaken.addTextChangedListener(new TextWatcher(){

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}
			
			
		});	
		*/
	}
	
	public void onClickSubject(View v){
		
	}
	
	public void onClickReading(View v){
		
		// Strings to Show In Dialog with Radio Buttons
		final CharSequence[] items = {" Little/None "," Average Amount "," Heavy Amount "};
		           
        // Creating and Building the Dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select The Difficulty Level");
        builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int item) {
          
           
            switch(item){
            
                case 0:
                         //first option
                         break;
                case 1:
                        //2nd  option
                        break;
                case 2:
                        //3rd option 
                        break;  
            }
            
            levelDialog.dismiss();   
            }
        });
        
        levelDialog = builder.create();
        levelDialog.show();
		
	}
	
	public void onClickWorkload(View v){
		
	}
	
	public void onClickDifficulty(View v){
		
	}
	
	public void onClickFriendliness(View v){
		
	}
	
	public void onClickCurves(View v){
		
	}
}
