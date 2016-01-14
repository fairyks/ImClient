/**
 * Copyright  陈延军 All rights reserved.
 */
package org.fairyks.im.myclient.ui;

import java.util.ArrayList;
import java.util.List;

import org.fairyks.im.myclient.activity.R;
import org.fairyks.im.myclient.util.Constant;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;

/**
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>公司名称 :陈延军 </p>
 * <p>项目名称 : ImClient</p>
 * <p>创建时间 : 2016年1月14日 下午2:57:43</p>
 * <p>类描述 :         </p>
 *
 *
 * @version 1.0.0
 * @author <a href=" ">陈延军</a>
 */

public class BottomControlPanel extends RelativeLayout implements OnClickListener {

	private Context context;
	private ImageText messageButton = null;
	private ImageText contactsButton = null;
	private ImageText findButton = null;
	private ImageText meButton = null;
	private int DEFALUT_BACKGROUND_COLOR = Color.rgb(243, 243, 243); //Color.rgb(192, 192, 192)
	private BottomPanelCallback bottomCallback = null;
	private List<ImageText> viewList = new ArrayList<ImageText>();
	
	public interface BottomPanelCallback{
		public void onBottomPanelClick(int itemId);
	}

	/**
	 * @param bottomCallback the bottomCallback to set
	 */
	public void setBottomCallback(BottomPanelCallback bottomCallback) {
		this.bottomCallback = bottomCallback;
	}

	/**
	 * @description
	 * <p>TODO</p>
	 * @param context
	 * @param attrs
	 */
	public BottomControlPanel(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	/**
	 * <h4>  </h4>
	 * @see android.view.View#onFinishInflate()
	 * @throws 
	 */
	@Override
	protected void onFinishInflate() {
		messageButton = (ImageText)findViewById(R.id.btn_message);
		contactsButton = (ImageText)findViewById(R.id.btn_contacts);
		findButton = (ImageText)findViewById(R.id.btn_find);
		meButton = (ImageText)findViewById(R.id.btn_me);
		setBackgroundColor(DEFALUT_BACKGROUND_COLOR);
		viewList.add(messageButton);
		viewList.add(contactsButton);
		viewList.add(findButton);
		viewList.add(meButton);
	}
	
	public void initBottomPanel(){
		if (messageButton!=null) {
			messageButton.setImage(R.drawable.message_unselected);
			messageButton.setText("消息");
		}
		if (contactsButton!=null) {
			contactsButton.setImage(R.drawable.contacts_unselected);
			contactsButton.setText("通讯录");
		}
		if (findButton!=null) {
			findButton.setImage(R.drawable.news_unselected);
			findButton.setText("发现");
		}
		if (meButton!=null) {
			meButton.setImage(R.drawable.setting_unselected);
			meButton.setText("我");
		}
		
		setButtonListener();
	}
	
	
	/**
	 * 方法描述 : 设置按钮监听器
	 */
	private void setButtonListener() {
		int num = this.getChildCount();
		for(int i = 0; i < num; i++){
			View view = getChildAt(i);
			if (view!=null) {
				view.setOnClickListener(this);
			}
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
		initBottomPanel();
		int index = -1;
		switch (v.getId()) {
		case R.id.btn_message:
			index = Constant.BTN_FLAG_MESSAGE;
			messageButton.setChecked(Constant.BTN_FLAG_MESSAGE);
			break;
		case R.id.btn_contacts:
			index = Constant.BTN_FLAG_CONTACTS;
			contactsButton.setChecked(Constant.BTN_FLAG_CONTACTS);
			break;
		case R.id.btn_find:
			index = Constant.BTN_FLAG_FIND;
			findButton.setChecked(Constant.BTN_FLAG_FIND);
			break;
		case R.id.btn_me:
			index = Constant.BTN_FLAG_ME;
			meButton.setChecked(Constant.BTN_FLAG_ME);
			break;
		default:break;
		}
		if(bottomCallback != null){
			bottomCallback.onBottomPanelClick(index);
		}
	}

	/**
	 * 
	 * 方法描述 : 设置默认的选中按钮
	 */
	public void setDefaultButtonCheced(){
		if (messageButton!=null) {
			messageButton.setChecked(Constant.BTN_FLAG_MESSAGE);
		}
	}
	
	@Override
	protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
		super.onLayout(changed, left, top, right, bottom);
		layoutItems(left, top, right, bottom);
	}
	/**最左边和最右边的view由母布局的padding进行控制位置。这里需对第2、3个view的位置重新设置
	 * @param left
	 * @param top
	 * @param right
	 * @param bottom
	 */
	private void layoutItems(int left, int top, int right, int bottom){
		int n = getChildCount();
		if(n == 0){
			return;
		}
		int paddingLeft = getPaddingLeft();
		int paddingRight = getPaddingRight();
		Log.i("yanguoqi", "paddingLeft = " + paddingLeft + " paddingRight = " + paddingRight);
		int width = right - left;
		int height = bottom - top;
		Log.i("yanguoqi", "width = " + width + " height = " + height);
		int allViewWidth = 0;
		for(int i = 0; i< n; i++){
			View v = getChildAt(i);
			Log.i("yanguoqi", "v.getWidth() = " + v.getWidth());
			allViewWidth += v.getWidth();
		}
		int blankWidth = (width - allViewWidth - paddingLeft - paddingRight) / (n - 1);
		Log.i("yanguoqi", "blankV = " + blankWidth );

		LayoutParams params1 = (LayoutParams) viewList.get(1).getLayoutParams();
		params1.leftMargin = blankWidth;
		viewList.get(1).setLayoutParams(params1);

		LayoutParams params2 = (LayoutParams) viewList.get(2).getLayoutParams();
		params2.leftMargin = blankWidth;
		viewList.get(2).setLayoutParams(params2);
	}
}
