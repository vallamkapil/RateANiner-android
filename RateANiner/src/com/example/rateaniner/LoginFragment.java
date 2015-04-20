package com.example.rateaniner;

import java.util.regex.Pattern;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseUser;

import android.animation.AnimatorSet.Builder;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass. Activities that contain this fragment
 * must implement the {@link LoginFragment.OnFragmentInteractionListener}
 * interface to handle interaction events. Use the
 * {@link LoginFragment#newInstance} factory method to create an instance of
 * this fragment.
 * 
 */
public class LoginFragment extends Fragment {
	
	EditText emailEditText, pwEditText;
	ImageView envelope, padlock, signInButton, forgotPw, registerButton;
	
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		Parse.initialize(getActivity(), "kaGobXqpVfCShEQBU7GX43Wn3bh7VwX9CqC8mXOB", 
													"Hak3kPtTIKLWjffOce7wLVYQJnlrN57AAfl9eRCz");
		
		emailEditText = (EditText) getActivity().findViewById(R.id.emailEditText);
		pwEditText = (EditText) getActivity().findViewById(R.id.pwEditText);
		
		envelope = (ImageView) getActivity().findViewById(R.id.envelopeImageView);
		padlock = (ImageView) getActivity().findViewById(R.id.padlockImageView);
		signInButton = (ImageView) getActivity().findViewById(R.id.signInIV);
		forgotPw = (ImageView) getActivity().findViewById(R.id.forgotPwIV);
		registerButton = (ImageView) getActivity().findViewById(R.id.registerIV);
		
		
		emailEditText.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				
				emailEditText.setTextColor(Color.parseColor("#ffffff"));
				
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
	            
	            	
	            	//envelope.setImageResource(R.drawable.envelope);
	            	//emailEditText.setTextColor(Color.parseColor("#ffffff"));
	            	
	            }

	        }
	    });
		
		pwEditText.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				
				pwEditText.setTextColor(Color.parseColor("#ffffff"));
				
				if(pwEditText.length() > 0)
					padlock.setImageResource(R.drawable.padlock);
				else
					padlock.setImageResource(R.drawable.faded_padlock);
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
	            	
	            	//padlock.setImageResource(R.drawable.padlock);
	            	//pwEditText.setTextColor(Color.parseColor("#ffffff"));
	            }

	        }
	    });
		
		signInButton.setOnClickListener(new View.OnClickListener() {
					
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
	
				String userName = emailEditText.getText().toString();
				String userPass = pwEditText.getText().toString();
				
				final ProgressDialog dialog = new ProgressDialog(getActivity());
				
				if(userName.length() == 0 || userPass.length() == 0){
					
					AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
					alertDialog.setMessage("E-mail/Password fields cannot be empty.")
			    	       .setCancelable(false)
			    	       .setPositiveButton("OK", new DialogInterface.OnClickListener() {
			    	           public void onClick(DialogInterface dialog, int id) {
			    	                //do things
			    	           }
			    	       });
			    	AlertDialog alert = alertDialog.create();
			    	alert.show();
					
				}else{
					
					dialog.setMessage("Logging In..     ");
			        dialog.show();
					
					ParseUser.logInInBackground(userName, userPass,
					
					new LogInCallback() {
	
						@Override
						public void done(ParseUser arg0, com.parse.ParseException arg1) {
							// TODO Auto-generated method stub
							if (arg1 == null) {
								Intent i = new Intent(getActivity(), HomeActivity.class);
								startActivity(i);
								
							} else {							
								
								AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
								alertDialog.setMessage("We were unable to log you in. Please check the "
										+ "e-mail and password entered and try again.")
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
	
		forgotPw.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mListener.gotoForgotPassword();
			}
		});
		
		registerButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mListener.gotoRegister();
			}
		});
		
	}

	// TODO: Rename parameter arguments, choose names that match
	// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
	private static final String ARG_PARAM1 = "param1";
	private static final String ARG_PARAM2 = "param2";

	// TODO: Rename and change types of parameters
	private String mParam1;
	private String mParam2;

	private OnFragmentInteractionListener mListener;

	/**
	 * Use this factory method to create a new instance of this fragment using
	 * the provided parameters.
	 * 
	 * @param param1
	 *            Parameter 1.
	 * @param param2
	 *            Parameter 2.
	 * @return A new instance of fragment LoginFragment.
	 */
	// TODO: Rename and change types and number of parameters
	public static LoginFragment newInstance(String param1, String param2) {
		LoginFragment fragment = new LoginFragment();
		Bundle args = new Bundle();
		args.putString(ARG_PARAM1, param1);
		args.putString(ARG_PARAM2, param2);
		fragment.setArguments(args);
		return fragment;
	}

	public LoginFragment() {
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
		return inflater.inflate(R.layout.fragment_login, container, false);
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
		public void gotoForgotPassword();
		public void gotoRegister();
	}
	
}
