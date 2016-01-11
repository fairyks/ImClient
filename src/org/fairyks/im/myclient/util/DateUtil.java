/**
 * Copyright  陈延军 All rights reserved.
 */
package org.fairyks.im.myclient.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>公司名称 :陈延军 </p>
 * <p>项目名称 : ImClient</p>
 * <p>创建时间 : 2016年1月11日 下午3:42:41</p>
 * <p>类描述 :         </p>
 *
 *
 * @version 1.0.0
 * @author <a href=" ">陈延军</a>
 */

public class DateUtil {

	public static String dateToString(Date date){ 
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
	    String time = formatter.format(date); 
	    return time; 
	} 
}
