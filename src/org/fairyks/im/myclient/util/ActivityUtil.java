/**
 * Copyright  陈延军 All rights reserved.
 */
package org.fairyks.im.myclient.util;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;

/**
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>公司名称 :陈延军 </p>
 * <p>项目名称 : ImClient</p>
 * <p>创建时间 : 2016年1月11日 下午4:46:42</p>
 * <p>类描述 :         </p>
 *
 *
 * @version 1.0.0
 * @author <a href=" ">陈延军</a>
 */

public class ActivityUtil {

	private static List<Activity> fromRegisterToLoginActivity = new ArrayList<Activity>();

	/**
	 * @return the fromRegisterToLoginActivity
	 */
	public List<Activity> getFromRegisterToLoginActivity() {
		return fromRegisterToLoginActivity;
	}
	
	public static void addToRegisterList(Activity activity){
		fromRegisterToLoginActivity.add(activity);
	}
	
	public static void removeFromRegisterList(){
		for(Activity activity:fromRegisterToLoginActivity){
			activity.finish();
		}
	}
}
