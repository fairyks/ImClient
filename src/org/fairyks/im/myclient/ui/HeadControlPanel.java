/**
 * Copyright  陈延军 All rights reserved.
 */
package org.fairyks.im.myclient.ui;

import org.fairyks.im.myclient.activity.R;
import org.fairyks.im.myclient.util.Constant;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>公司名称 :陈延军 </p>
 * <p>项目名称 : ImClient</p>
 * <p>创建时间 : 2016年1月14日 下午3:33:38</p>
 * <p>类描述 :         </p>
 *
 *
 * @version 1.0.0
 * @author <a href=" ">陈延军</a>
 */

public class HeadControlPanel extends RelativeLayout {

	private Context context;
	private TextView midleTitle;
	private TextView rightTitle;
	private static final float MIDDLE_TITLE_SIZE = 20f; 
	private static final float RIGHT_TITLE_SIZE = 17f; 
	private static final int DEFAULT_BACKGROUND_COLOR = Color.rgb(21, 126, 203);
	
	/**
	 * @description
	 * <p>TODO</p>
	 * @param context
	 * @param attrs
	 */
	public HeadControlPanel(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	/**
	 * <h4>  </h4>
	 * @see android.view.View#onFinishInflate()
	 * @throws 
	 */
	@Override
	protected void onFinishInflate() {
		midleTitle = (TextView)findViewById(R.id.midle_title);
		rightTitle = (TextView)findViewById(R.id.right_title);
		setBackgroundColor(DEFAULT_BACKGROUND_COLOR);
	}
	public void initHeadPanel(){
		if(midleTitle != null){
			setMiddleTitle(Constant.FRAGMENT_FLAG_MESSAGE);
		}
	}
	public void setMiddleTitle(String title){
		midleTitle.setText(title);
		midleTitle.setTextSize(MIDDLE_TITLE_SIZE);
	}

}
