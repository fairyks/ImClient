/**
 * Copyright  陈延军 All rights reserved.
 */
package org.fairyks.im.myclient.bean;

import java.util.List;

/**
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>公司名称 :陈延军 </p>
 * <p>项目名称 : ImClient</p>
 * <p>创建时间 : 2016年1月7日 下午3:40:19</p>
 * <p>类描述 :         </p>
 *
 *
 * @version 1.0.0
 * @author <a href=" ">陈延军</a>
 */

public class Packet {

	private String type;
	private String message;
	private String from;
	private String to;
	private String msgId;
	private List<User> list;
	private boolean loginFlag;
	
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * @return the from
	 */
	public String getFrom() {
		return from;
	}
	/**
	 * @param from the from to set
	 */
	public void setFrom(String from) {
		this.from = from;
	}
	/**
	 * @return the to
	 */
	public String getTo() {
		return to;
	}
	/**
	 * @param to the to to set
	 */
	public void setTo(String to) {
		this.to = to;
	}
	/**
	 * @return the msgId
	 */
	public String getMsgId() {
		return msgId;
	}
	/**
	 * @param msgId the msgId to set
	 */
	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}
	/**
	 * @return the list
	 */
	public List<User> getList() {
		return list;
	}
	/**
	 * @param list the list to set
	 */
	public void setList(List<User> list) {
		this.list = list;
	}
	/**
	 * @return the loginFlag
	 */
	public boolean isLoginFlag() {
		return loginFlag;
	}
	/**
	 * @param loginFlag the loginFlag to set
	 */
	public void setLoginFlag(boolean loginFlag) {
		this.loginFlag = loginFlag;
	}
	
}