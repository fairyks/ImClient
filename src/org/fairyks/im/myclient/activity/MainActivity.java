package org.fairyks.im.myclient.activity;

import java.util.ArrayList;
import java.util.List;

import org.fairyks.im.myclient.adapter.MsgAdapter;
import org.fairyks.im.myclient.bean.Msg;
import org.fairyks.im.myclient.service.CommunicationService;
import org.fairyks.im.myclient.service.ConnectionManager;
import org.fairyks.im.myclient.service.SendMessage;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private LocalReceiver localReceiver;
	private LocalBroadcastManager localBroadcastManager;
	private IntentFilter intentFilter;
	private ListView msgListView;
	private EditText inputText;
	private Button send;
	private MsgAdapter adapter;
	private List<Msg> msgList = new ArrayList<Msg>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		localBroadcastManager = LocalBroadcastManager.getInstance(this);
//		ConnectionManager.getConnectionManager().setLocalBroadcastManager(localBroadcastManager);
		ConnectionManager.setLocalBroadcastManager(localBroadcastManager);
		
		adapter = new MsgAdapter(MainActivity.this, R.layout.msg_item, msgList);
		
		
		inputText = (EditText) findViewById(R.id.input_text);
		send = (Button) findViewById(R.id.send);
		msgListView = (ListView) findViewById(R.id.msg_list_view);
		msgListView.setAdapter(adapter);
		
		send.setOnClickListener(new SendMesageListener());
		
		intentFilter = new IntentFilter();
		intentFilter.addAction("org.fairyks.im.myclient.broadcast");
		localReceiver = new LocalReceiver();
		localBroadcastManager.registerReceiver(localReceiver, intentFilter);
	}

	
	
	class SendMesageListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			String message = inputText.getText().toString();
			if ("".equals(message)||message==null) {
				Toast.makeText(MainActivity.this, "不能为空", Toast.LENGTH_SHORT).show();
			}else{
				Msg msg = new Msg(message, Msg.TYPE_SENT);
				msgList.add(msg);
				adapter.notifyDataSetChanged(); // 当有新消息时，刷新ListView中的显示
				msgListView.setSelection(msgList.size()); // 将ListView定位到最后一行
				inputText.setText(""); // 清空输入框中的内容
				try {
					new SendMessage().execute(message);
				} catch (Exception e) {
					System.out.println("信息发送失败");
				}
				
			}
		}
		
	}

	
	class LocalReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {
			Msg msg = new Msg(intent.getStringExtra("msg").toString(), Msg.TYPE_RECEIVED);
			msgList.add(msg);
			adapter.notifyDataSetChanged();
			msgListView.setSelection(msgList.size());
//			displayText.setTextColor(Color.RED);
//			displayText.setText(intent.getStringExtra("msg").toString());
		}
	}
	
}