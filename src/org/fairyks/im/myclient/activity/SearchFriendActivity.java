/**
 * Copyright  陈延军 All rights reserved.
 */
package org.fairyks.im.myclient.activity;

import org.fairyks.im.myclient.bean.Msg;
import org.fairyks.im.myclient.bean.Packet;
import org.fairyks.im.myclient.service.ConnectionManager;
import org.fairyks.im.myclient.service.SendMessage;
import org.fairyks.im.myclient.util.Constant;

import com.google.gson.Gson;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>公司名称 :陈延军 </p>
 * <p>项目名称 : ImClient</p>
 * <p>创建时间 : 2016年1月20日 上午11:53:19</p>
 * <p>类描述 :     搜索好友界面    </p>
 *
 *
 * @version 1.0.0
 * @author <a href=" ">陈延军</a>
 */

public class SearchFriendActivity extends Activity implements OnClickListener {

	private SharedPreferences preferences;
	private EditText searchFriendEditText;
	private Button searchFriendButton;
	private TextView textView;

	private IntentFilter intentFilter;
	private SearchFriendInfoReceiver receiver;

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
		setContentView(R.layout.search_new_friend);
		searchFriendEditText = (EditText) findViewById(R.id.search_friend_input);
		searchFriendButton = (Button) findViewById(R.id.search_button);
		searchFriendButton.setOnClickListener(this);
		textView = (TextView) findViewById(R.id.show_search_result);
		
		intentFilter = new IntentFilter();
		intentFilter.addAction("org.fairyks.searchFriendInfoBroadcast");
		receiver = new SearchFriendInfoReceiver();
		ConnectionManager.getLocalBroadcastManager().registerReceiver(receiver, intentFilter);
	}

	/**
	 * <h4>  </h4>
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 * @param v
	 * @throws 
	 */
	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.search_button:
			String friendAccount = searchFriendEditText.getText().toString();
			if (TextUtils.isEmpty(friendAccount)) {
				Toast.makeText(getApplicationContext(), "不能为空", Toast.LENGTH_SHORT).show();
			} else {
				try {
					Gson gson = new Gson();
					Packet packet = new Packet();
					preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
					String localAcount = preferences.getString("localAccount", "");
					packet.setType(Constant.IQ_SEARCH_FRIEND);
					packet.setMessage(Constant.IQ_SEARCH_FRIEND);
					packet.setFrom(localAcount);
					packet.setTo(friendAccount);
					new SendMessage().execute(gson.toJson(packet));
				} catch (Exception e) {
					System.out.println("信息发送失败");
				}
			}
			break;

		default:
			break;
		}
	}

	class SearchFriendInfoReceiver extends BroadcastReceiver {

		/**
		 * <h4> 查询好友结果接收器 </h4>
		 * @param context
		 * @param intent
		 * @throws 
		 */
		@Override
		public void onReceive(Context context, Intent intent) {
			String result = intent.getStringExtra("searchResult");
			textView.setText(result);
		}

	}

}
