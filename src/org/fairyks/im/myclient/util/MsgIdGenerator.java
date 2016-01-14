/**
 * Copyright  陈延军 All rights reserved.
 */
package org.fairyks.im.myclient.util;

import java.util.UUID;

/**
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>公司名称 :陈延军 </p>
 * <p>项目名称 : ImClient</p>
 * <p>创建时间 : 2016年1月12日 下午1:29:11</p>
 * <p>类描述 :         </p>
 *
 *
 * @version 1.0.0
 * @author <a href=" ">陈延军</a>
 */

public class MsgIdGenerator {

	public static String getMessageId(){
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
}
