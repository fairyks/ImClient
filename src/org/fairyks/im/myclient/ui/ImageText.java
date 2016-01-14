/**
 * Copyright  陈延军 All rights reserved.
 */
package org.fairyks.im.myclient.ui;

import org.fairyks.im.myclient.activity.R;
import org.fairyks.im.myclient.util.Constant;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>公司名称 :陈延军 </p>
 * <p>项目名称 : ImClient</p>
 * <p>创建时间 : 2016年1月14日 下午2:30:08</p>
 * <p>类描述 :         </p>
 *
 *
 * @version 1.0.0
 * @author <a href=" ">陈延军</a>
 */

public class ImageText extends LinearLayout {
	private Context context = null;
	private ImageView imageView = null;
	private TextView textView = null;
	private final static int DEFAULT_IMAGE_WIDTH = 64;
	private final static int DEFAULT_IMAGE_HEIGHT = 64;
	private int CHECKED_COLOR = Color.rgb(29, 118, 199); //选中蓝色
	private int UNCHECKED_COLOR = Color.GRAY;   //自然灰色(字体颜色)
	/**
	 * @description
	 * <p>TODO</p>
	 * @param context
	 */
	public ImageText(Context context) {
		super(context);
		this.context = context;
	}
	
	/**
	 * @description
	 * <p>TODO</p>
	 */
	public ImageText(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		
		LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = layoutInflater.inflate(R.layout.image_text_layout, this, true);
		imageView = (ImageView) findViewById(R.id.image_iamge_text);
		textView = (TextView) findViewById(R.id.text_iamge_text);
		
	}
	
	public void setImage(int id){
		if (imageView!=null) {
			imageView.setImageResource(id);
			setImageSize(DEFAULT_IMAGE_WIDTH, DEFAULT_IMAGE_HEIGHT);
		}
	}

	public void setText(String text){
		if (textView!=null) {
			textView.setText(text);
			textView.setTextColor(UNCHECKED_COLOR);
		}
	}
	public void setImageSize(int width,int height){
		if (imageView!=null) {
			android.view.ViewGroup.LayoutParams params = imageView.getLayoutParams();
			params.width = width;
			params.height = height;
			imageView.setLayoutParams(params);
		}
	}
	
	public void setChecked(int itemId) {
		if (textView!=null) {
			textView.setTextColor(CHECKED_COLOR);
		}
		int checkedDrawableId = -1;
		switch (itemId) {
		case Constant.BTN_FLAG_MESSAGE:
			checkedDrawableId = R.drawable.message_selected;
			break;
		case Constant.BTN_FLAG_CONTACTS:
			checkedDrawableId = R.drawable.contacts_selected;
			break;
		case Constant.BTN_FLAG_FIND:
			checkedDrawableId = R.drawable.news_selected;
			break;
		case Constant.BTN_FLAG_ME:
			checkedDrawableId = R.drawable.setting_selected;
			break;
		default:
			break;
		}
		
		if (imageView!=null) {
			imageView.setImageResource(checkedDrawableId);
		}
	}
	
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		return true;
	}
}
