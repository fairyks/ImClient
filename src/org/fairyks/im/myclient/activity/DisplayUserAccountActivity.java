/**
 * Copyright  陈延军 All rights reserved.
 */
package org.fairyks.im.myclient.activity;

import org.fairyks.im.myclient.util.ActivityUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

/**
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>公司名称 :陈延军 </p>
 * <p>项目名称 : ImClient</p>
 * <p>创建时间 : 2016年1月11日 下午3:46:23</p>
 * <p>类描述 :         </p>
 *
 *
 * @version 1.0.0
 * @author <a href=" ">陈延军</a>
 */

public class DisplayUserAccountActivity extends Activity implements OnClickListener {

	private TextView accountText;
	private Button goToLogin;
	/**
	 * <h4>  </h4>
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 * @param savedInstanceState
	 * @throws 
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.display_user_account);
		ActivityUtil.addToRegisterList(DisplayUserAccountActivity.this);
		accountText = (TextView) findViewById(R.id.display_account);
		StringBuilder builder = new StringBuilder("您的账号为：");
		builder.append(getIntent().getStringExtra("account"));
		builder.append("马上去登录吧！！！");
		accountText.setText(builder.toString());
		goToLogin = (Button) findViewById(R.id.go_to_login);
		goToLogin.setOnClickListener(this);
	}
	
	/**
	 * <h4>  </h4>
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 * @param v
	 * @throws 
	 */
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.go_to_login:
			Intent intent = new Intent(DisplayUserAccountActivity.this,LoginActivity.class);
			intent.putExtra("isAfterRegister", true);
			intent.putExtra("account", getIntent().getStringExtra("account"));
			startActivity(intent);
//			finish();
			break;

		default:
			break;
		}
	}

}
