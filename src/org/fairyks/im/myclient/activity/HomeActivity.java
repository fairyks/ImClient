/**
 * Copyright  陈延军 All rights reserved.
 */
package org.fairyks.im.myclient.activity;

import org.fairyks.im.myclient.fragment.BaseFragment;
import org.fairyks.im.myclient.service.CommunicationService;
import org.fairyks.im.myclient.service.ConnectionManager;
import org.fairyks.im.myclient.ui.BottomControlPanel;
import org.fairyks.im.myclient.ui.BottomControlPanel.BottomPanelCallback;
import org.fairyks.im.myclient.ui.HeadControlPanel;
import org.fairyks.im.myclient.util.ActivityUtil;
import org.fairyks.im.myclient.util.Constant;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.view.Window;

/**
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>公司名称 :陈延军 </p>
 * <p>项目名称 : ImClient</p>
 * <p>创建时间 : 2016年1月11日 下午2:21:41</p>
 * <p>类描述 :     登录进来的主页面   </p>
 *
 *
 * @version 1.0.0
 * @author <a href=" ">陈延军</a>
 */

public class HomeActivity extends Activity implements BottomPanelCallback {

	
	BottomControlPanel bottomPanel = null;
	HeadControlPanel headPanel = null;

	private FragmentManager fragmentManager = null;
	private FragmentTransaction fragmentTransaction = null;

	private LocalBroadcastManager localBroadcastManager;
	
	public static String currFragTag = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.home);
		initUi();
		ActivityUtil.removeFromRegisterList();
		fragmentManager = getFragmentManager();
		setDefaultFirstFragment(Constant.FRAGMENT_FLAG_MESSAGE);
		
//		initConnection();
		
		localBroadcastManager = LocalBroadcastManager.getInstance(this);
		ConnectionManager.setLocalBroadcastManager(localBroadcastManager);
		
		
		
		//		nickNameView = (TextView) findViewById(R.id.nick_name);
		//		Intent intent = getIntent();
		//		nickNameView.setText(intent.getStringExtra("nickName"));
	}

	/**
	 * 方法描述 : 初始化连接
	 */
	private void initConnection() {
	 new Thread(new CommunicationService()).start();
//		CommunicationService.getCommunicationService().connect();
	}

	private void initUi() {
		bottomPanel = (BottomControlPanel) findViewById(R.id.bottom_layout);
		if (bottomPanel != null) {
			bottomPanel.initBottomPanel();
			bottomPanel.setBottomCallback(this);
		}

		headPanel = (HeadControlPanel) findViewById(R.id.head_layout);
		if (headPanel != null) {
			headPanel.initHeadPanel();
		}
	}

	/**
	 * 
	 * <h4>  处理BottomControlPanel的回调 </h4>
	 * @see org.fairyks.im.myclient.ui.BottomControlPanel.BottomPanelCallback#onBottomPanelClick(int)
	 * @param itemId
	 * @throws
	 */
	@Override
	public void onBottomPanelClick(int itemId) {
		String tag = "";
		if ((itemId & Constant.BTN_FLAG_MESSAGE) != 0) {
			tag = Constant.FRAGMENT_FLAG_MESSAGE;
		} else if ((itemId & Constant.BTN_FLAG_CONTACTS) != 0) {
			tag = Constant.FRAGMENT_FLAG_CONTACTS;
		} else if ((itemId & Constant.BTN_FLAG_FIND) != 0) {
			tag = Constant.FRAGMENT_FLAG_FIND;
		} else if ((itemId & Constant.BTN_FLAG_ME) != 0) {
			tag = Constant.FRAGMENT_FLAG_ME;
		}
		setTabSelection(tag); //切换Fragment
		headPanel.setMiddleTitle(tag);//切换标题 
	}

	/**
	 * 方法描述 : 设置选中的Tag
	 * @param tag
	 */
	private void setTabSelection(String tag) {
		// 开启一个Fragment事务
		fragmentTransaction = fragmentManager.beginTransaction();
		switchFragment(tag);
	}

	/**
	 * 方法描述 : 切换fragment 
	 * @param tag
	 */
	private void switchFragment(String tag) {
		if (TextUtils.equals(tag, currFragTag)) {
			return;
		}

		//把上一个fragment detach掉 
		if (currFragTag != null && !currFragTag.equals("")) {
			detachFragment(getFragment(currFragTag));
		}
		attachFragment(R.id.fragment_content, getFragment(tag), tag);
		commitTransactions(tag);
	}

	/**
	 * 方法描述 : 该方法实现的功能描述
	 * @param tag
	 */
	private void commitTransactions(String tag) {
		if (fragmentTransaction != null && !fragmentTransaction.isEmpty()) {
			fragmentTransaction.commit();
			currFragTag = tag;
			fragmentTransaction = null;
		}
	}

	/**
	 * 方法描述 : 该方法实现的功能描述
	 * @param fragmentContent
	 * @param fragment
	 * @param tag
	 */
	private void attachFragment(int layout, Fragment fragment, String tag) {
		if (fragment != null) {
			if (fragment.isDetached()) {
				ensureTransaction();
				fragmentTransaction.attach(fragment);
			} else if (!fragment.isAdded()) {
				ensureTransaction();
				fragmentTransaction.add(layout, fragment, tag);
			}
		}
	}

	/**
	 * 方法描述 : 该方法实现的功能描述
	 * @param fragment
	 */
	private void detachFragment(Fragment fragment) {
		if (fragment != null && !fragment.isDetached()) {
			ensureTransaction();
			fragmentTransaction.detach(fragment);
		}
	}

	/**
	 * 方法描述 : 该方法实现的功能描述
	 */
	private void ensureTransaction() {
		if (fragmentTransaction == null) {
			fragmentTransaction = fragmentManager.beginTransaction();
			fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
		}
	}

	/**
	 * 方法描述 : 该方法实现的功能描述
	 * @param currFragTag2
	 * @return
	 */
	private Fragment getFragment(String tag) {
		Fragment fragment = fragmentManager.findFragmentByTag(tag);
		if (fragment == null) {
			fragment = BaseFragment.newInstance(getApplicationContext(), tag);
		}
		return fragment;
	}

	/**
	 * 方法描述 : 设置默认选中的按钮
	 * @param fragmentFlagMessage
	 */
	private void setDefaultFirstFragment(String tag) {
		setTabSelection(tag);
		bottomPanel.setDefaultButtonCheced();
	}

	@Override
	protected void onStop() {
		super.onStop();
		currFragTag = "";
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
	}
}