package com.example.rateaniner;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.regex.Pattern;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.net.ParseException;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnFocusChangeListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass. Activities that contain this fragment
 * must implement the {@link RegisterFragment.OnFragmentInteractionListener}
 * interface to handle interaction events. Use the
 * {@link RegisterFragment#newInstance} factory method to create an instance of
 * this fragment.
 * 
 */
public class RegisterFragment extends Fragment {
	
	EditText emailEditText, pwEditText, bdayEditText;
	ImageView envelope, padlock, cake, signUpButton;
	
	// TODO: Rename parameter arguments, choose names that match
	// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
	private static final String ARG_PARAM1 = "param1";
	private static final String ARG_PARAM2 = "param2";

	// TODO: Rename and change types of parameters
	private String mParam1;
	private String mParam2;
	
	private int year;
    private int month;
    private int day;

	private OnFragmentInteractionListener mListener;
	
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		
		final Calendar cal = Calendar.getInstance();
		
		//Current Date
		year  = cal.get(Calendar.YEAR);
        month = cal.get(Calendar.MONTH);
        day   = cal.get(Calendar.DAY_OF_MONTH);
		
		Parse.initialize(getActivity(), "kaGobXqpVfCShEQBU7GX43Wn3bh7VwX9CqC8mXOB", 
				"Hak3kPtTIKLWjffOce7wLVYQJnlrN57AAfl9eRCz");

		emailEditText = (EditText) getActivity().findViewById(R.id.emailEditText);
		pwEditText = (EditText) getActivity().findViewById(R.id.pwEditText);
		bdayEditText = (EditText) getActivity().findViewById(R.id.bdayEditText);
		
		bdayEditText.setKeyListener(null);
		
		envelope = (ImageView) getActivity().findViewById(R.id.envelopeImageView);
		padlock = (ImageView) getActivity().findViewById(R.id.padlockImageView);
		cake = (ImageView) getActivity().findViewById(R.id.cakeImageView);
		
		signUpButton = (ImageView) getActivity().findViewById(R.id.signUpIV);
		
		
		emailEditText.addTextChangedListener(new TextWatcher() {
		
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
			// TODO Auto-generated method stub
			if(emailEditText.length() > 0)
				envelope.setImageResource(R.drawable.envelope);
			else
				envelope.setImageResource(R.drawable.faded_envelope);
			
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}

		});
		
		//Monitors e-mail address to verify it is properly input
		
		emailEditText.setOnFocusChangeListener(new OnFocusChangeListener() {          
		
			public void onFocusChange(View v, boolean hasFocus) {
			
				if(!hasFocus) {
					
					String value = emailEditText.getText().toString();
					
					Pattern ptr = Pattern.compile("^[A-Z0-9._%+-]+@uncc+\\.edu$", Pattern.CASE_INSENSITIVE);
					
					if(!(ptr.matcher(value).matches()) && value.length() > 0){
						envelope.setImageResource(R.drawable.red_envelope);
						emailEditText.setTextColor(Color.parseColor("#ff0000"));
					}
				
				}else if(hasFocus){		
					
					if(emailEditText.getText().length() > 0){
						envelope.setImageResource(R.drawable.envelope);
						emailEditText.setTextColor(Color.parseColor("#ffffff"));	
					}
				}
				
				}
				});
		
		pwEditText.addTextChangedListener(new TextWatcher() {
		
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				pwEditText.setTextColor(Color.parseColor("#ffffff"));
				
				if(pwEditText.length() > 0)
					padlock.setImageResource(R.drawable.padlock);
				else
					padlock.setImageResource(R.drawable.faded_padlock);
		}
		
		@Override
		public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			// TODO Auto-generated method stub
		
		}
		
		@Override
		public void afterTextChanged(Editable s) {
			// TODO Auto-generated method stub
		
		}
		
		});
		
		//Monitors password to verify it is properly input
		
		pwEditText.setOnFocusChangeListener(new OnFocusChangeListener() {          
		
			public void onFocusChange(View v, boolean hasFocus) {
			
			if(!hasFocus) {
			//String value = pwEditText.getText().toString();
			
				if(pwEditText.length() < 8 && pwEditText.length()> 0){
					padlock.setImageResource(R.drawable.red_padlock);
					pwEditText.setTextColor(Color.parseColor("#ff0000"));
				}
			
			}else if(hasFocus){

			}
			
			}
		});
		
		final DatePickerDialog.OnDateSetListener datePick = new DatePickerDialog.OnDateSetListener() {

		    @Override
		    public void onDateSet(DatePicker view, int year, int monthOfYear,
		            int dayOfMonth) {
		        // TODO Auto-generated method stub
		        cal.set(Calendar.YEAR, year);
		        cal.set(Calendar.MONTH, monthOfYear);
		        cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
		        updateLabel();
		    }

			private void updateLabel() {
				// TODO Auto-generated method stub
				String myFormat = "MM/dd/yy"; //In which you need put here
			    SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
			    
			    cake.setImageResource(R.drawable.cake);
			    bdayEditText.setText(sdf.format(cal.getTime()));
			}

		};
		
		bdayEditText.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            	new DatePickerDialog(getActivity(), datePick, cal.get(Calendar.YEAR), 
	            		cal.get(Calendar.MONTH),
	                    cal.get(Calendar.DAY_OF_MONTH)).show();
            	
            	
            	
            } 
		});
		
		signUpButton.setOnClickListener(new View.OnClickListener() {
		
			@Override
			public void onClick(View v) {
			// TODO Auto-generated method stub
				
				String userName = emailEditText.getText().toString();
				String userPass = pwEditText.getText().toString();
				String userBday = bdayEditText.getText().toString();
				
				Pattern ptr = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
				
				final ProgressDialog dialog = new ProgressDialog(getActivity());
				
				//If e-mail address is invalid format
				if(!(ptr.matcher(userName).matches()) || userName.length() == 0){
					
					AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
					alertDialog.setMessage("Please enter a valid e-mail address.")
			    	       .setCancelable(false)
			    	       .setPositiveButton("OK", new DialogInterface.OnClickListener() {
			    	           public void onClick(DialogInterface dialog, int id) {
			    	                //do things
			    	           }
			    	       });
			    	
					AlertDialog alert = alertDialog.create();
			    	alert.show();
				
			    //If password is less than 8 chars
				}else if(userPass.length() < 8){
					
					AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
					alertDialog.setMessage("Password length must be a minimum of 8 characters.")
			    	       .setCancelable(false)
			    	       .setPositiveButton("OK", new DialogInterface.OnClickListener() {
			    	           public void onClick(DialogInterface dialog, int id) {
			    	                //do things
			    	           }
			    	       });
					
			    	AlertDialog alert = alertDialog.create();
			    	alert.show();
			    	
			    	
			    //If birthday is too early or too short a length
				}else if(userBday == null){
					
					AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
					alertDialog.setMessage("Please input your birthday.")
			    	       .setCancelable(false)
			    	       .setPositiveButton("OK", new DialogInterface.OnClickListener() {
			    	           public void onClick(DialogInterface dialog, int id) {
			    	                //do things
			    	           }
			    	       });
			    	AlertDialog alert = alertDialog.create();
			    	alert.show();
					
				}else{		
				
					ParseUser user = new ParseUser();
					user.setUsername(userName);
					user.setPassword(userPass);
					user.setEmail(userName);
					user.put("birthday", userBday);
					
					dialog.setMessage("Signing Up..     ");
			        dialog.show();
					 
					user.signUpInBackground(new SignUpCallback() {
						
						
						
						@Override
						public void done(com.parse.ParseException arg0) {
							// TODO Auto-generated method stub
							if (dialog.isShowing()) {
					            dialog.dismiss();
					        }
							
							
							if (arg0 == null) {
						    	AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
								alertDialog.setMessage("Registration successful. Welcome to Rate-a-Niner!")
						    	       .setCancelable(false)
						    	       .setPositiveButton("OK", new DialogInterface.OnClickListener() {
						    	           public void onClick(DialogInterface dialog, int id) {
						    	               //do things
						    	        	   Intent i = new Intent(getActivity(), HomeActivity.class);
						    	        	   startActivity(i);
						    	           }
						    	       });
						    	AlertDialog alert = alertDialog.create();
						    	alert.show();
	
							} else {
								
								AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
								alertDialog.setMessage("A user account for that e-mail address already exists.")
						    	       .setCancelable(false)
						    	       .setPositiveButton("OK", new DialogInterface.OnClickListener() {
						    	           public void onClick(DialogInterface dialog, int id) {
						    	               //do things
						    	        	
						    	           }
						    	       });
						    	AlertDialog alert = alertDialog.create();
						    	alert.show();
							}
	
						}
						
					});
				}
			}
		
		});
		
	}

	/**
	 * Use this factory method to create a new instance of this fragment using
	 * the provided parameters.
	 * 
	 * @param param1
	 *            Parameter 1.
	 * @param param2
	 *            Parameter 2.
	 * @return A new instance of fragment RegisterFragment.
	 */
	
	// TODO: Rename and change types and number of parameters
	
	public static RegisterFragment newInstance(String param1, String param2) {
		RegisterFragment fragment = new RegisterFragment();
		Bundle args = new Bundle();
		args.putString(ARG_PARAM1, param1);
		args.putString(ARG_PARAM2, param2);
		fragment.setArguments(args);
		return fragment;
	}

	public RegisterFragment() {
		// Required empty public constructor
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments() != null) {
			mParam1 = getArguments().getString(ARG_PARAM1);
			mParam2 = getArguments().getString(ARG_PARAM2);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_register, container, false);
	}

	// TODO: Rename method, update argument and hook method into UI event
	public void onButtonPressed(Uri uri) {
		if (mListener != null) {
			mListener.onFragmentInteraction(uri);
		}
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			mListener = (OnFragmentInteractionListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnFragmentInteractionListener");
		}
	}

	@Override
	public void onDetach() {
		super.onDetach();
		mListener = null;
	}

	/**
	 * This interface must be implemented by activities that contain this
	 * fragment to allow an interaction in this fragment to be communicated to
	 * the activity and potentially other fragments contained in that activity.
	 * <p>
	 * See the Android Training lesson <a href=
	 * "http://developer.android.com/training/basics/fragments/communicating.html"
	 * >Communicating with Other Fragments</a> for more information.
	 */
	public interface OnFragmentInteractionListener {
		// TODO: Update argument type and name
		public void onFragmentInteraction(Uri uri);
	}

}
