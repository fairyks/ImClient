/**
 * Copyright  陈延军 All rights reserved.
 */
package org.fairyks.im.myclient.fragment;

import java.util.ArrayList;
import java.util.List;

import org.fairyks.im.myclient.activity.SearchFriendActivity;
import org.fairyks.im.myclient.activity.HomeActivity;
import org.fairyks.im.myclient.activity.R;
import org.fairyks.im.myclient.adapter.PinyinAdapter;
import org.fairyks.im.myclient.util.Constant;
import org.fairyks.im.myclient.view.AlphabetView;
import org.fairyks.im.myclient.view.AlphabetView.OnTouchalphabetListener;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ExpandableListView.OnChildClickListener;

/**
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>公司名称 :陈延军 </p>
 * <p>项目名称 : ImClient</p>
 * <p>创建时间 : 2016年1月14日 下午3:46:39</p>
 * <p>类描述 :         </p>
 *
 *
 * @version 1.0.0
 * @author <a href=" ">陈延军</a>
 */

public class ContactsFragment extends BaseFragment {

	private PinyinAdapter adapter;
	private ExpandableListView expandableListView;
	private AlphabetView alphabetView;
	private Button addFriendButton;
	private View contactsLayout;
	private List<String> names;

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
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		contactsLayout = inflater.inflate(R.layout.contacts_layout, container, false);
		expandableListView = (ExpandableListView) contactsLayout.findViewById(R.id.contact_list);
		alphabetView = (AlphabetView) contactsLayout.findViewById(R.id.alphabet_list);
		addFriendButton = (Button) contactsLayout.findViewById(R.id.addFriend);

		names=new ArrayList<String>();
		names.add("lxz_123");
		names.add("A酱_6789");
		names.add("芙兰_12309");
		names.add("鱼鱼_98765");
		names.add("妹妹_7683");
		names.add("你好_4897");
		names.add("林小姐_98765");
		names.add("联盟_7653");
		names.add("L_2187");
		names.add("xdsfsdggsdsf_98776");
		names.add("星星_1876");
		names.add("靴刀誓死_87766");
		names.add("Java_8765");
		names.add("倒塌_35421");
		names.add("黑人_292872");
		names.add("a妹_38752");
		names.add("aYa_2282772");

		adapter = new PinyinAdapter(getActivity(), names);
		expandableListView.setAdapter(adapter);

		//展开所有
		for (int i = 0, length = adapter.getGroupCount(); i < length; i++) {
			expandableListView.expandGroup(i);
		}

		
		
			expandableListView.setOnChildClickListener(new OnChildClickListener() {
			
			public boolean onChildClick(ExpandableListView parent, View view, int groupPosition, int childPosition, long id) {
//				Intent intent = new Intent(MainActivity.this, PlayActivity.class);
//				intent.setData(Uri.parse(adapter.getList().get(groupPosition).videos.get(childPosition).url));
				
//				Toast.makeText(MainActivity.this, adapter.getChild(groupPosition, childPosition).toString(),Toast.LENGTH_SHORT).show();
				String userId = adapter.getChild(groupPosition, childPosition).toString();
				Toast.makeText(getActivity(), userId.substring(userId.indexOf("_")+1),Toast.LENGTH_SHORT).show();
//				startActivity(intent);
				return true;
			}
		});

		//字母按键回调
		alphabetView.setOnTouchalphabetListener(new OnTouchalphabetListener() {

			View layoutView = LayoutInflater.from(getActivity()).inflate(R.layout.alert_dialog_menu_layout, null);
			TextView text = (TextView) layoutView.findViewById(R.id.content);

			PopupWindow popupWindow;
			
			@Override
			public void onTouchalphabetListener(String str) {
				int index = adapter.getalphabetPinyinList().getHashList().indexOfKey(str);
				if (index != -1) {
					expandableListView.setSelectedGroup(index);
				}
				if (popupWindow != null) {
					text.setText(str);
				} else {
					popupWindow = new PopupWindow(layoutView, 500, 500, false);
					//显示在Activity的根视图中心
					popupWindow.showAtLocation(getActivity().getWindow().getDecorView(), Gravity.CENTER, 0, 0);
				}
				text.setText(str);
			}

			@Override
			public void onTouchalphabetUP() {
				if (popupWindow != null){
					popupWindow.dismiss();
					popupWindow = null;
				}
			}
		});

		
		addFriendButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
//				Toast.makeText(getActivity(), "add friend", Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(getActivity(), SearchFriendActivity.class);
				startActivity(intent);
			}
		});
		return contactsLayout;
	}
	
	/**
	 * <h4>  </h4>
	 * @see org.fairyks.im.myclient.fragment.BaseFragment#onResume()
	 * @throws 
	 */
	@Override
	public void onResume() {
		super.onResume();
		HomeActivity.currFragTag = Constant.FRAGMENT_FLAG_CONTACTS;
	}
}
