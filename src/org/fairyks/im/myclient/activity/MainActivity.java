package org.fairyks.im.myclient.activity;

import org.fairyks.im.myclient.R;
import org.fairyks.im.myclient.service.CommunicationService;
import org.fairyks.im.myclient.service.ConnectionManager;
import org.fairyks.im.myclient.service.SendMessage;
import org.w3c.dom.Text;

import android.R.color;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private  TextView displayText = null;
	private EditText inputMessage = null;
	private Button sendMessageButton = null;
	private LocalReceiver localReceiver;
	private LocalBroadcastManager localBroadcastManager;
	private IntentFilter intentFilter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
		localBroadcastManager = LocalBroadcastManager.getInstance(this);
//		ConnectionManager.getConnectionManager().setLocalBroadcastManager(localBroadcastManager);
		ConnectionManager.setLocalBroadcastManager(localBroadcastManager);
		
		displayText = (TextView) findViewById(R.id.chat_content_display);
		inputMessage = (EditText) findViewById(R.id.chat_content);
		sendMessageButton = (Button) findViewById(R.id.chat_sendbtn);
		sendMessageButton.setOnClickListener(new SendMesageListener());
		
		intentFilter = new IntentFilter();
		intentFilter.addAction("org.fairyks.im.myclient.broadcast");
		localReceiver = new LocalReceiver();
		localBroadcastManager.registerReceiver(localReceiver, intentFilter);
	}

	
	
	class SendMesageListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			String message = inputMessage.getText().toString();
			if ("".equals(message)||message==null) {
				Toast.makeText(MainActivity.this, "不能为空", Toast.LENGTH_SHORT).show();
			}else{
				displayText.setText(message);
				try {
					new SendMessage().execute(message);
					inputMessage.setText("");
				} catch (Exception e) {
					System.out.println("信息发送失败");
				}
				
			}
		}
		
	}

	
	class LocalReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {
			displayText.setTextColor(Color.RED);
			displayText.setText(intent.getStringExtra("msg").toString());
		}
	}
	
	/**
	 * 方法描述 : 初始化环境
	 */
	private void init() {
//		new Thread(new CommunicationService()).start();
		CommunicationService.getCommunicationService().connect();
	}
}