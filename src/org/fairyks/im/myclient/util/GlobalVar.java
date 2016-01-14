/**
 * Copyright  陈延军 All rights reserved.
 */
package org.fairyks.im.myclient.util;

/**
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>公司名称 :陈延军 </p>
 * <p>项目名称 : MyClient</p>
 * <p>创建时间 : 2015年12月24日 下午2:51:47</p>
 * <p>类描述 :         </p>
 *
 *
 * @version 1.0.0
 * @author <a href=" ">陈延军</a>
 */

public class GlobalVar {

	public static final String HOST = System.getProperty("host", "192.168.0.122");
	public static final int PORT = Integer.parseInt(System.getProperty("port","9888"));
}
