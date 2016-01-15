/**
 * Copyright  陈延军 All rights reserved.
 */
package org.fairyks.im.myclient.fragment;

import org.fairyks.im.myclient.activity.HomeActivity;
import org.fairyks.im.myclient.activity.R;
import org.fairyks.im.myclient.util.Constant;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>公司名称 :陈延军 </p>
 * <p>项目名称 : ImClient</p>
 * <p>创建时间 : 2016年1月14日 下午3:55:49</p>
 * <p>类描述 :         </p>
 *
 *
 * @version 1.0.0
 * @author <a href=" ">陈延军</a>
 */

public class FindFragment extends BaseFragment {

	/**
	 * <h4>  </h4>
	 * @see org.fairyks.im.myclient.fragment.BaseFragment#onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)
	 * @param inflater
	 * @param container
	 * @param savedInstanceState
	 * @return
	 * @throws 
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View newsLayout = inflater.inflate(R.layout.find_layout, container,
				false);
		return newsLayout;
	}
	
	
	@Override
	public void onResume() {
		super.onResume();
		HomeActivity.currFragTag = Constant.FRAGMENT_FLAG_FIND;
	}
}
