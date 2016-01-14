/**
 * Copyright  陈延军 All rights reserved.
 */
package org.fairyks.im.myclient.fragment;


import org.fairyks.im.myclient.util.Constant;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>公司名称 :陈延军 </p>
 * <p>项目名称 : ImClient</p>
 * <p>创建时间 : 2016年1月14日 下午3:43:03</p>
 * <p>类描述 :         </p>
 *
 *
 * @version 1.0.0
 * @author <a href=" ">陈延军</a>
 */

public class BaseFragment extends Fragment {

	private static final String TAG = "BaseFragment";
	protected FragmentManager fragmentManager = null;
	protected FragmentTransaction fragmentTransaction = null;
	
	
	public static BaseFragment newInstance(Context context,String tag){
		BaseFragment baseFragment =  null;
		if(TextUtils.equals(tag, Constant.FRAGMENT_FLAG_CONTACTS)){
			baseFragment = new ContactsFragment();
		}else if(TextUtils.equals(tag, Constant.FRAGMENT_FLAG_MESSAGE)){
			baseFragment = new MessageFragment();
		}else if(TextUtils.equals(tag, Constant.FRAGMENT_FLAG_FIND)){
			baseFragment = new FindFragment();
		}else if(TextUtils.equals(tag, Constant.FRAGMENT_FLAG_ME)){
			baseFragment = new MeFragment();
		}
		return baseFragment;
	}
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		Log.i(TAG, "onAttach...");
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i(TAG, "onCreate...");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.i(TAG, "onCreateView...");
		return 	super.onCreateView(inflater, container, savedInstanceState);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		Log.i(TAG, "onActivityCreated...");
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void onStart() {
		Log.i(TAG, "onStart...");
		super.onStart();
	}

	@Override
	public void onResume() {
		Log.i(TAG, "onResume...");
		super.onResume();
	}

	@Override
	public void onPause() {
		Log.i(TAG, "onPause...");
		super.onPause();
	}

	@Override
	public void onStop() {
		Log.i(TAG, "onStop...");
		super.onStop();
	}

	@Override
	public void onDestroyView() {
		Log.i(TAG, "onDestroyView...");
		super.onDestroyView();
	}

	@Override
	public void onDestroy() {
		Log.i(TAG, "onDestroy...");
		super.onDestroy();
	}
	
}
