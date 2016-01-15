/**
 * Copyright  陈延军 All rights reserved.
 */
package org.fairyks.im.myclient.fragment;

import org.fairyks.im.myclient.activity.HomeActivity;
import org.fairyks.im.myclient.activity.R;
import org.fairyks.im.myclient.util.Constant;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>公司名称 :陈延军 </p>
 * <p>项目名称 : ImClient</p>
 * <p>创建时间 : 2016年1月14日 下午3:51:18</p>
 * <p>类描述 :         </p>
 *
 *
 * @version 1.0.0
 * @author <a href=" ">陈延军</a>
 */

public class MessageFragment extends BaseFragment {

	private static final String TAG = "MessageFragment";
	
	/**
	 * <h4>  </h4>
	 * @see android.app.Fragment#onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)
	 * @param inflater
	 * @param container
	 * @param savedInstanceState
	 * @return
	 * @throws 
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View messageLayout = inflater.inflate(R.layout.message_layout, container, false);
		return messageLayout;
	}
	
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		Log.e(TAG, "onActivityCreated-------");
	}

	@Override
	public void onStart() {
		super.onStart();
		Log.e(TAG, "onStart----->");
	}

	@Override
	public void onResume() {
		super.onResume();
		Log.e(TAG, "onresume---->");
		HomeActivity.currFragTag = Constant.FRAGMENT_FLAG_MESSAGE;
	}

	@Override
	public void onPause() {
		super.onPause();
		Log.e(TAG, "onpause");
	}

	@Override
	public void onStop() {
		super.onStop();
		Log.e(TAG, "onStop");
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		Log.e(TAG, "ondestoryView");
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.e(TAG, "ondestory");
	}

	@Override
	public void onDetach() {
		super.onDetach();
		Log.d(TAG, "onDetach------");
	}
}
