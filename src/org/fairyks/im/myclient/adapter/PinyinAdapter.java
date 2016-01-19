/**
 * Copyright  陈延军 All rights reserved.
 */
package org.fairyks.im.myclient.adapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.fairyks.im.myclient.activity.R;
import org.fairyks.im.myclient.util.pinyin.AlphabetPinyinList;
import org.fairyks.im.myclient.util.pinyin.LanguageComparator_CN;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

/**
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>公司名称 :陈延军 </p>
 * <p>项目名称 : ImClient</p>
 * <p>创建时间 : 2016年1月15日 下午3:27:03</p>
 * <p>类描述 :         </p>
 *
 *
 * @version 1.0.0
 * @author <a href=" ">陈延军</a>
 */

public class PinyinAdapter extends BaseExpandableListAdapter {

	// 字符串
	private List<String> stringList;

	private AlphabetPinyinList alphabetPinyinList = new AlphabetPinyinList();

	private Context context;

	private LayoutInflater inflater;
	// 中文排序
	private LanguageComparator_CN cnSort = new LanguageComparator_CN();

	public PinyinAdapter(Context context, List<String> stringList) {
		super();
		this.context = context;
		this.inflater = LayoutInflater.from(context);
		this.stringList = stringList;
		if (stringList == null) {
			stringList = new ArrayList<String>();
		}
		// 排序
		sort();
	}

	private void sort() {
		// 分类
		for (String string : stringList) {
			alphabetPinyinList.getHashList().add(string);
		}
		alphabetPinyinList.getHashList().sortKeyComparator(cnSort);

		for (int i = 0; i < alphabetPinyinList.getHashList().size(); i++) {
			Collections.sort((alphabetPinyinList.getHashList().getValueListIndex(i)), cnSort);
		}

	}

	/**
	 * <h4>  </h4>
	 * @see android.widget.ExpandableListAdapter#getGroupCount()
	 * @return
	 * @throws 
	 */
	@Override
	public int getGroupCount() {
		return alphabetPinyinList.getHashList().size();
	}

	/**
	 * <h4>  </h4>
	 * @see android.widget.ExpandableListAdapter#getChildrenCount(int)
	 * @param groupPosition
	 * @return
	 * @throws 
	 */
	@Override
	public int getChildrenCount(int groupPosition) {
		return alphabetPinyinList.getHashList().getValueListIndex(groupPosition).size();
	}

	/**
	 * <h4>  </h4>
	 * @see android.widget.ExpandableListAdapter#getGroup(int)
	 * @param groupPosition
	 * @return
	 * @throws 
	 */
	@Override
	public Object getGroup(int groupPosition) {
		return alphabetPinyinList.getHashList().getValueListIndex(groupPosition);
	}

	/**
	 * <h4>  </h4>
	 * @see android.widget.ExpandableListAdapter#getChild(int, int)
	 * @param groupPosition
	 * @param childPosition
	 * @return
	 * @throws 
	 */
	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return alphabetPinyinList.getHashList().getValueIndex(groupPosition, childPosition);
	}

	/**
	 * <h4>  </h4>
	 * @see android.widget.ExpandableListAdapter#getGroupId(int)
	 * @param groupPosition
	 * @return
	 * @throws 
	 */
	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	/**
	 * <h4>  </h4>
	 * @see android.widget.ExpandableListAdapter#getChildId(int, int)
	 * @param groupPosition
	 * @param childPosition
	 * @return
	 * @throws 
	 */
	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	/**
	 * <h4>  </h4>
	 * @see android.widget.ExpandableListAdapter#hasStableIds()
	 * @return
	 * @throws 
	 */
	@Override
	public boolean hasStableIds() {
		return true;
	}

	/**
	 * <h4>  </h4>
	 * @see android.widget.ExpandableListAdapter#getGroupView(int, boolean, android.view.View, android.view.ViewGroup)
	 * @param groupPosition
	 * @param isExpanded
	 * @param convertView
	 * @param parent
	 * @return
	 * @throws 
	 */
	@Override
	public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.list_group_item, null);
			convertView.setClickable(true);
		}
		TextView textView = (TextView) convertView.findViewById(R.id.name);
		textView.setText(
				alphabetPinyinList.getFirstChar(alphabetPinyinList.getHashList().getValueIndex(groupPosition, 0)));
		// 禁止伸展
		return convertView;
	}

	/**
	 * <h4>  </h4>
	 * @see android.widget.ExpandableListAdapter#getChildView(int, int, boolean, android.view.View, android.view.ViewGroup)
	 * @param groupPosition
	 * @param childPosition
	 * @param isLastChild
	 * @param convertView
	 * @param parent
	 * @return
	 * @throws 
	 */
	@Override
	public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView,
			ViewGroup parent) {
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.adapter_chat, null);
		}
		TextView textView = (TextView) convertView.findViewById(R.id.name);
		String name = alphabetPinyinList.getHashList().getValueIndex(groupPosition, childPosition);
		textView.setText(name.substring(0, name.indexOf("_")));
		textView.setText(alphabetPinyinList.getHashList().getValueIndex(groupPosition, childPosition));
		return convertView;
	}

	/**
	 * <h4>  </h4>
	 * @see android.widget.ExpandableListAdapter#isChildSelectable(int, int)
	 * @param groupPosition
	 * @param childPosition
	 * @return
	 * @throws 
	 */
	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}

	public AlphabetPinyinList getalphabetPinyinList() {
		return alphabetPinyinList;
	}
}
