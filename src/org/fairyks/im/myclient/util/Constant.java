/**
 * Copyright  陈延军 All rights reserved.
 */
package org.fairyks.im.myclient.util;

/**
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>公司名称 :陈延军 </p>
 * <p>项目名称 : ImClient</p>
 * <p>创建时间 : 2016年1月14日 下午2:23:49</p>
 * <p>类描述 :         </p>
 *
 *
 * @version 1.0.0
 * @author <a href=" ">陈延军</a>
 */

public class Constant {

		//Button的标识
		public static final int BTN_FLAG_MESSAGE = 0x01;
		public static final int BTN_FLAG_CONTACTS = 0x01 << 1;
		public static final int BTN_FLAG_FIND = 0x01 << 2;
		public static final int BTN_FLAG_ME = 0x01 << 3;
		
		//Fragment的标识
		public static final String FRAGMENT_FLAG_MESSAGE = "消息"; 
		public static final String FRAGMENT_FLAG_CONTACTS = "通讯录"; 
		public static final String FRAGMENT_FLAG_FIND = "发现"; 
		public static final String FRAGMENT_FLAG_ME = "我"; 
		
		//消息协议
		public static final String IQ = "IQ";
		public static final String IQ_SEARCH_FRIEND = "SEARCHFRIEND";
		public static final String IQ_ADD_FRIEND = "ADDROSTER";
		public static final String IQ_DELETE_FRIEND = "DELETEROSTER";
		public static final String CHAT = "CHAT";
		public static final String GROUP_CHAT = "GROUPCHAT";
		public static final String PRESENCE = "PRESENCE";
		public static final String PRESENCE_ONLINE = "PRESENCEONLINE";
		public static final String PRESENCE_OFFLINE = "PRESENCEOFFLINE";
		
		
}
