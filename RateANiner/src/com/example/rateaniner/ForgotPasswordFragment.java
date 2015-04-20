package com.example.rateaniner;

import java.util.regex.Pattern;

import com.parse.ParseUser;
import com.parse.RequestPasswordResetCallback;

import android.app.Activity;
import android.app.AlertDialog;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass. Activities that contain this fragment
 * must implement the
 * {@link ForgotPasswordFragment.OnFragmentInteractionListener} interface to
 * handle interaction events. Use the {@link ForgotPasswordFragment#newInstance}
 * factory method to create an instance of this fragment.
 * 
 */
public class ForgotPasswordFragment extends Fragment {
	
	EditText emailEditText;
	ImageView envelope, resetPwButton;
	
	// TODO: Rename parameter arguments, choose names that match
	// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
	private static final String ARG_PARAM1 = "param1";
	private static final String ARG_PARAM2 = "param2";

	// TODO: Rename and change types of parameters
	private String mParam1;
	private String mParam2;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		
		emailEditText = (EditText) getActivity().findViewById(R.id.emailEditText);
		
		envelope = (ImageView) getActivity().findViewById(R.id.envelopeImageView);
		resetPwButton = (ImageView) getActivity().findViewById(R.id.resetPwImageView);
		
		emailEditText.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
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
					
					Pattern ptr = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
					
					if(!(ptr.matcher(value).matches()) && value.length() > 0){
						envelope.setImageResource(R.drawable.red_envelope);
						emailEditText.setTextColor(Color.parseColor("#ff0000"));
					}
					
	            }else if(hasFocus && emailEditText.length() > 0){
	            
	            	envelope.setImageResource(R.drawable.envelope);
	            	emailEditText.setTextColor(Color.parseColor("#ffffff"));
	            	
	            }

	        }
	    });
		
		resetPwButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				final String userEmail = emailEditText.getText().toString();
				
				final ProgressDialog dialog = new ProgressDialog(getActivity());
				
				Pattern ptr = Pattern.compile("^[A-Z0-9._%+-]+@uncc+\\.edu$", Pattern.CASE_INSENSITIVE);
				
				if(!(ptr.matcher(userEmail).matches()) || userEmail.length() == 0){
					AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
					alertDialog.setMessage("Please enter a valid e-mail address")
			    	.setCancelable(false)
			    	.setPositiveButton("OK", new DialogInterface.OnClickListener() {
	    	           public void onClick(DialogInterface dialog, int id) {
	    	              
	    	           }
	    	        });
			        AlertDialog alert = alertDialog.create();
			        alert.show();
									
				}else{

					dialog.setMessage("Sending Password..     ");
			        dialog.show();
					
					// TODO Auto-generated method stub
					ParseUser.requestPasswordResetInBackground(userEmail,
	                        new RequestPasswordResetCallback() {
						
						@Override
						public void done(com.parse.ParseException arg0) {
							// TODO Auto-generated method stub
							
							if (dialog.isShowing()) {
					            dialog.dismiss();
					        }
							
							if (arg0 == null) {
						    	AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
								alertDialog.setMessage("We've sent an e-mail with password reset instructions to: " + userEmail)
						    	       .setCancelable(false)
						    	       .setPositiveButton("OK", new DialogInterface.OnClickListener() {
						    	           public void onClick(DialogInterface dialog, int id) {
						    	              
						    	        	   mListener.gotoLogin();
						    	           }
						    	       });
						    	AlertDialog alert = alertDialog.create();
						    	alert.show();
		
							} else {
								
								AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
								alertDialog.setMessage("We couldn't find an account associated with the e-mail address: " + userEmail)
						    	       .setCancelable(false)
						    	       .setPositiveButton("OK", new DialogInterface.OnClickListener() {
						    	           public void onClick(DialogInterface dialog, int id) {
						    	               //do things
						    	           }
						    	       });
						    	AlertDialog alert = alertDialog.create();
						    	alert.show();
								Log.d("exception", arg0.toString());
							}
					}
					});
				}
			}
		});
		
	}

	private OnFragmentInteractionListener mListener;

	/**
	 * Use this factory method to create a new instance of this fragment using
	 * the provided parameters.
	 * 
	 * @param param1
	 *            Parameter 1.
	 * @param param2
	 *            Parameter 2.
	 * @return A new instance of fragment ForgotPasswordFragment.
	 */
	// TODO: Rename and change types and number of parameters
	public static ForgotPasswordFragment newInstance(String param1,
			String param2) {
		ForgotPasswordFragment fragment = new ForgotPasswordFragment();
		Bundle args = new Bundle();
		args.putString(ARG_PARAM1, param1);
		args.putString(ARG_PARAM2, param2);
		fragment.setArguments(args);
		return fragment;
	}

	public ForgotPasswordFragment() {
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
		return inflater.inflate(R.layout.fragment_forgot_password, container,
				false);
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
		public void gotoLogin();
	}
	
	public void onBackPressed()
    {
        mListener.gotoLogin();
    }

}


