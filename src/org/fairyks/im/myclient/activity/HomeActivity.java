/**
 * Copyright  陈延军 All rights reserved.
 */
package org.fairyks.im.myclient.activity;

import org.fairyks.im.myclient.util.ActivityUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

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

public class HomeActivity extends Activity implements OnClickListener {
	
	private TextView nickNameView;
	
	/**
	 * <h4>  </h4>
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 * @param savedInstanceState
	 * @throws 
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);
		ActivityUtil.removeFromRegisterList();
		nickNameView = (TextView) findViewById(R.id.nick_name);
		Intent intent = getIntent();
		nickNameView.setText(intent.getStringExtra("nickName"));
		Log.d("HomeActivity", intent.getStringExtra("nickName"));
	}
	
	
	
	/**
	 * <h4>  </h4>
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 * @param v
	 * @throws 
	 */
	@Override
	public void onClick(View v) {
	}

}
