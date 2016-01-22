/**
 * Copyright  陈延军 All rights reserved.
 */
package org.fairyks.im.myclient.activity;

import org.fairyks.im.myclient.bean.Packet;
import org.fairyks.im.myclient.bean.ResponseBean;
import org.fairyks.im.myclient.bean.User;
import org.fairyks.im.myclient.service.CommunicationService;
import org.fairyks.im.myclient.service.ConnectionManager;
import org.fairyks.im.myclient.service.SendMessage;
import org.fairyks.im.myclient.util.ActivityUtil;
import org.fairyks.im.myclient.util.Constant;
import org.fairyks.im.myclient.util.HttpUtil;

import com.google.gson.Gson;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>公司名称 :陈延军 </p>
 * <p>项目名称 : ImClient</p>
 * <p>创建时间 : 2016年1月7日 下午2:07:39</p>
 * <p>类描述 :         </p>
 *
 *
 * @version 1.0.0
 * @author <a href=" ">陈延军</a>
 */

public class LoginActivity extends Activity implements OnClickListener {
	
	public static final int VERIFY_RESULT = 0;
	private SharedPreferences preferences;
	private SharedPreferences.Editor editor;
	
	private IntentFilter intentFilter;
	private LoginReceiver receiver;
	private LocalBroadcastManager localBroadcastManager;
	private EditText userNameEditText;
	private EditText password;
	private Button loginButton;
	private Button registerButton;
	
	
	private ResponseBean bean;
	private String userName;
	
	private Handler handler = new Handler(){
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case VERIFY_RESULT:
				startHomeActivity(LoginActivity.this, bean.getNickName());
			}
		}
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
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.login);
		preferences = PreferenceManager.getDefaultSharedPreferences(this);
		userNameEditText = (EditText) findViewById(R.id.username);
		password = (EditText) findViewById(R.id.password);
		loginButton = (Button) findViewById(R.id.login_btn);
		registerButton = (Button) findViewById(R.id.register_btn);
		loginButton.setOnClickListener(this);
		registerButton.setOnClickListener(this);
		initConnection(); //初始化连接和用户信息绑定的位置，再考虑加到别处
		intentFilter = new IntentFilter();
		intentFilter.addAction("org.fairyks.loginBroadcast");
		
		localBroadcastManager = LocalBroadcastManager.getInstance(this);
		ConnectionManager.setLocalBroadcastManager(localBroadcastManager);
		receiver = new LoginReceiver();
		ConnectionManager.getLocalBroadcastManager().registerReceiver(receiver, intentFilter);
		
		boolean flag = getIntent().getBooleanExtra("isAfterRegister", false);
		if (flag) {
			userNameEditText.setText(getIntent().getStringExtra("account"));
			ActivityUtil.addToRegisterList(LoginActivity.this);
		}
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
		case R.id.login_btn:
			if (TextUtils.isEmpty(userNameEditText.getText().toString())||TextUtils.isEmpty(password.getText().toString())) {
				Toast.makeText(LoginActivity.this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
			} else {
				userName = userNameEditText.getText().toString();
				verifyUser(userNameEditText.getText().toString(),password.getText().toString());
			}
			break;
		case R.id.register_btn:
			startRegisterActivity(LoginActivity.this);
		default:
			break;
		}
	}
	
	public static void startHomeActivity(Context context, String nickName) {
		Intent intent = new Intent(context, HomeActivity.class);
		intent.putExtra("nickName", nickName);
		context.startActivity(intent);
	}
	public static void startRegisterActivity(Context context) {
		Intent intent = new Intent(context, RegisterActivity.class);
		context.startActivity(intent);
	}

	
	private void verifyUser(final String userName,final String password) {
		// 开启线程来发起网络请求
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					User user = new User(userName, password,"login");
					String params = HttpUtil.toJson(user);
//					String response = HttpUtil.post("http://127.0.0.1:9999/imServer/userManageAction", params);
					String response = HttpUtil.post("http://192.168.0.122:9999/imServer/userManageAction", params);
//					Message message = new Message();
//					message.what = VERIFY_RESULT;
//					// 将服务器返回的结果存放到Message中
//					message.obj = response.toString();
					
					Gson gson = new Gson();
					bean = gson.fromJson(response, ResponseBean.class);
					if (bean.isFlag()) {
						//记住用户名
						editor = preferences.edit();
						editor.putString("localAccount", userName);
						editor.commit();
						
						Packet packet = new Packet();
						packet.setFrom(userName);
						packet.setType(Constant.PRESENCE_ONLINE);
						new SendMessage().execute(gson.toJson(packet));
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
	
	
	
	/**
	 * 方法描述 : 初始化连接
	 */
	private void initConnection() {
	 new Thread(new CommunicationService()).start();
//		CommunicationService.getCommunicationService().connect();
	}
	
	private class LoginReceiver extends BroadcastReceiver {
		/**
		 * <h4>  </h4>
		 * @param context
		 * @param intent
		 * @throws 
		 */
		@Override
		public void onReceive(Context context, Intent intent) {
			boolean flag = intent.getBooleanExtra("loginEcho", false);
			if(flag){
				Message message = new Message();
				message.what = VERIFY_RESULT;
				// 将服务器返回的结果存放到Message中
//				message.obj = response.toString();
				handler.sendMessage(message);
			}
		}
	}
	
}
