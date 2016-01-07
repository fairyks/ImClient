/**
 * Copyright  陈延军 All rights reserved.
 */
package org.fairyks.im.myclient.activity;

import org.fairyks.im.myclient.R;
import org.fairyks.im.myclient.bean.Packet;
import org.fairyks.im.myclient.util.HttpUtil;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

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
	public static final int SHOW_RESPONSE = 0;
	private TextView textView;
	private Button loginButton;
	private Handler handler = new Handler(){
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case SHOW_RESPONSE:
				String response = (String) msg.obj;
				textView.setText(response);
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
		setContentView(R.layout.login);
		textView = (TextView) findViewById(R.id.showResponse);
		loginButton = (Button) findViewById(R.id.login);
		loginButton.setOnClickListener(this);
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
		case R.id.login:
			sendHttpRequest();
			break;
		default:
			break;
		}
	}
	
	private void sendHttpRequest() {
		// 开启线程来发起网络请求
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Packet packet = new Packet();
					packet.setType("chat");
					packet.setFrom("123@123");
					packet.setTo("456@456");
					String params = HttpUtil.toJson(packet);
					String response = HttpUtil.post("http://192.168.0.122:9999/imServer/syncAction", params);
					Message message = new Message();
					message.what = SHOW_RESPONSE;
					// 将服务器返回的结果存放到Message中
					message.obj = response.toString();
					handler.sendMessage(message);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
}
