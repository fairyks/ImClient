/**
 * Copyright  陈延军 All rights reserved.
 */
package org.fairyks.im.myclient.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Button;

/**
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>公司名称 :陈延军 </p>
 * <p>项目名称 : ImClient</p>
 * <p>创建时间 : 2016年1月15日 下午3:36:40</p>
 * <p>类描述 :         </p>
 *
 *
 * @version 1.0.0
 * @author <a href=" ">陈延军</a>
 */

public class AlphabetView extends Button {

	// 字母列表
	private String[] alphabet = { "?", "#", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O",
			"P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };

	private Paint paint = new Paint();
	// 选择的索引
	private int selectIndex = -1;

	// 字母监听器
	private OnTouchalphabetListener onTouchalphabetListener;

	public void setOnTouchalphabetListener(OnTouchalphabetListener onTouchalphabetListener) {
		this.onTouchalphabetListener = onTouchalphabetListener;
	}

	public AlphabetView(Context context) {
		super(context);
	}

	public AlphabetView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public AlphabetView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public interface OnTouchalphabetListener {
		public void onTouchalphabetListener(String s);

		public void onTouchalphabetUP();
	}

	/**
	 * <h4>  </h4>
	 * @see android.widget.TextView#onDraw(android.graphics.Canvas)
	 * @param canvas
	 * @throws 
	 */
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		int height = getHeight();
		int width = getWidth();
		int interval = height / alphabet.length;

		for (int i = 0; i < alphabet.length; i++) {
			// 抗锯齿
			paint.setAntiAlias(true);
			// 默认粗体
			paint.setTypeface(Typeface.DEFAULT);
			// 白色
			paint.setColor(Color.WHITE);

			paint.setTextSize(40);
			if (i == selectIndex) {
				// 被选择的字母改变颜色和粗体
				paint.setColor(Color.parseColor("#3399ff"));
				paint.setFakeBoldText(true);
				paint.setTextSize(60);
			}
			// 计算字母的X坐标
			float xPos = width / 2 - paint.measureText(alphabet[i]) / 2;
			// 计算字母的Y坐标
			float yPos = interval * i + interval;
			canvas.drawText(alphabet[i], xPos, yPos, paint);
			paint.reset();
		}
	}

	/**
	* <h4>  </h4>
	* @see android.view.View#dispatchTouchEvent(android.view.MotionEvent)
	* @param event
	* @return
	* @throws 
	*/
	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		float y = event.getY();
		int index = (int) (y / getHeight() * alphabet.length);
		if (index >= 0 && index < alphabet.length) {
			switch (event.getAction()) {
			case MotionEvent.ACTION_MOVE:
				// 如果滑动改变
				if (selectIndex != index) {
					selectIndex = index;
					if (onTouchalphabetListener != null) {
						onTouchalphabetListener.onTouchalphabetListener(alphabet[selectIndex]);
					}

				}
				break;
			case MotionEvent.ACTION_DOWN:
				selectIndex = index;
				if (onTouchalphabetListener != null) {
					onTouchalphabetListener.onTouchalphabetListener(alphabet[selectIndex]);
				}

				break;
			case MotionEvent.ACTION_UP:
				if (onTouchalphabetListener != null) {
					onTouchalphabetListener.onTouchalphabetUP();
				}
				selectIndex = -1;
				break;
			}
		} else {
			selectIndex = -1;
			if (onTouchalphabetListener != null) {
				onTouchalphabetListener.onTouchalphabetUP();
			}
		}
		invalidate();
		return true;
	}

	/**
	 * <h4>  </h4>
	 * @see android.widget.TextView#onTouchEvent(android.view.MotionEvent)
	 * @param event
	 * @return
	 * @throws 
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		return super.onTouchEvent(event);
	}
}
