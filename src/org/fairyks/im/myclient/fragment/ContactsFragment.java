/**
 * Copyright  陈延军 All rights reserved.
 */
package org.fairyks.im.myclient.fragment;

import java.util.ArrayList;
import java.util.List;

import org.fairyks.im.myclient.activity.HomeActivity;
import org.fairyks.im.myclient.activity.MainActivity;
import org.fairyks.im.myclient.activity.R;
import org.fairyks.im.myclient.adapter.PinyinAdapter;
import org.fairyks.im.myclient.util.Constant;
import org.fairyks.im.myclient.view.AlphabetView;
import org.fairyks.im.myclient.view.AlphabetView.OnTouchalphabetListener;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.PopupWindow;
import android.widget.TextView;

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
		View contactsLayout = inflater.inflate(R.layout.contacts_layout, container, false);
		expandableListView = (ExpandableListView) contactsLayout.findViewById(R.id.contact_list);
		alphabetView = (AlphabetView) contactsLayout.findViewById(R.id.alphabet_list);
		;
		names = new ArrayList<String>();
		names.add("lxz");
		names.add("A酱");
		names.add("芙兰");
		names.add("鱼鱼");
		names.add("妹妹");
		names.add("你好");
		names.add("林小姐");
		names.add("联盟");
		names.add("L");
		names.add("xdsfsdggsdsf");
		names.add("星星");
		names.add("靴刀誓死");
		names.add("Java");
		names.add("倒塌");
		names.add("黑人");
		names.add("a妹");
		names.add("aYa");

		adapter = new PinyinAdapter(getActivity(), names);
		expandableListView.setAdapter(adapter);

		//展开所有
		for (int i = 0, length = adapter.getGroupCount(); i < length; i++) {
			expandableListView.expandGroup(i);
		}

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
