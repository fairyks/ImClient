/**
 * Copyright  陈延军 All rights reserved.
 */
package org.fairyks.im.myclient.activity;

import java.util.Date;

import org.fairyks.im.myclient.bean.ResponseBean;
import org.fairyks.im.myclient.bean.User;
import org.fairyks.im.myclient.util.DateUtil;
import org.fairyks.im.myclient.util.HttpUtil;

import com.google.gson.Gson;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>公司名称 :陈延军 </p>
 * <p>项目名称 : ImClient</p>
 * <p>创建时间 : 2016年1月11日 下午2:57:21</p>
 * <p>类描述 :         </p>
 *
 *
 * @version 1.0.0
 * @author <a href=" ">陈延军</a>
 */

public class RegisterActivity extends Activity implements OnClickListener {
	
	public static final int REGISTER_RESULT = 0;
	private EditText nickNameText;
	private EditText registerPasswordText;
	private Button registerButton;
	private Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case REGISTER_RESULT:
				String response = (String) msg.obj;
				if(response!=null&&!"".equals(response)){
					Gson gson = new Gson();
					ResponseBean bean = gson.fromJson(response, ResponseBean.class);
					if (bean.isFlag()) {
						startDisplayUserAccountActivity(RegisterActivity.this, bean.getUserId());
					}
				}
				break;
			default:
				break;
			}
		};
	};
	
	/**
	 * <h4>  </h4>
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 * @param savedInstanceState
	 * @throws 
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		nickNameText = (EditText) findViewById(R.id.nickName);
		registerPasswordText = (EditText) findViewById(R.id.reg_password);
		registerButton = (Button) findViewById(R.id.register_user_btn);
		registerButton.setOnClickListener(this);
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
		case R.id.register_user_btn:
			if (TextUtils.isEmpty(nickNameText.getText().toString())||TextUtils.isEmpty(registerPasswordText.getText().toString())) {
				Toast.makeText(RegisterActivity.this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
			} else {
				registerUser(nickNameText.getText().toString(),registerPasswordText.getText().toString());
			}
			break;

		default:
			break;
		}
	}

	/**
	 * 方法描述 : 注册成功,启动显示账号信息的界面
	 * @param context
	 * @param account
	 */
	protected void startDisplayUserAccountActivity(Context context, String account) {
		Intent intent = new Intent(context,DisplayUserAccountActivity.class);
		intent.putExtra("account", account);
		context.startActivity(intent);
	}
	

	private void registerUser(final String nickName,final String password) {
		// 开启线程来发起网络请求
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					User user = new User(password, nickName, DateUtil.dateToString(new Date()), "addUser");
					String params = HttpUtil.toJson(user);
//					String response = HttpUtil.post("http://127.0.0.1:9999/imServer/userManageAction", params);
					String response = HttpUtil.post("http://192.168.0.122:9999/imServer/userManageAction", params);
					Message message = new Message();
					message.what = REGISTER_RESULT;
					message.obj = response.toString();
					handler.sendMessage(message);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
}
