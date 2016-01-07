/**
 * Copyright  陈延军 All rights reserved.
 */
package org.fairyks.im.myclient.bean;

/**
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>公司名称 :陈延军 </p>
 * <p>项目名称 : ImClient</p>
 * <p>创建时间 : 2016年1月7日 下午4:35:38</p>
 * <p>类描述 :     用户信息类    </p>
 *
 *
 * @version 1.0.0
 * @author <a href=" ">陈延军</a>
 */

public class User {

	private String userName;
	private String plainPassword;
	private String encryptedPassword;
	private String nickName;
	private String creationdate;
	private String modificationDate;
	/**
	 * @description
	 * <p>TODO</p>
	 * @param userName
	 * @param plainPassword
	 * @param encryptedPassword
	 * @param nickName
	 * @param creationdate
	 * @param modificationDate
	 */
	public User(String userName, String plainPassword, String encryptedPassword, String nickName, String creationdate,
			String modificationDate) {
		super();
		this.userName = userName;
		this.plainPassword = plainPassword;
		this.encryptedPassword = encryptedPassword;
		this.nickName = nickName;
		this.creationdate = creationdate;
		this.modificationDate = modificationDate;
	}
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the plainPassword
	 */
	public String getPlainPassword() {
		return plainPassword;
	}
	/**
	 * @param plainPassword the plainPassword to set
	 */
	public void setPlainPassword(String plainPassword) {
		this.plainPassword = plainPassword;
	}
	/**
	 * @return the encryptedPassword
	 */
	public String getEncryptedPassword() {
		return encryptedPassword;
	}
	/**
	 * @param encryptedPassword the encryptedPassword to set
	 */
	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}
	/**
	 * @return the nickName
	 */
	public String getNickName() {
		return nickName;
	}
	/**
	 * @param nickName the nickName to set
	 */
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	/**
	 * @return the creationdate
	 */
	public String getCreationdate() {
		return creationdate;
	}
	/**
	 * @param creationdate the creationdate to set
	 */
	public void setCreationdate(String creationdate) {
		this.creationdate = creationdate;
	}
	/**
	 * @return the modificationDate
	 */
	public String getModificationDate() {
		return modificationDate;
	}
	/**
	 * @param modificationDate the modificationDate to set
	 */
	public void setModificationDate(String modificationDate) {
		this.modificationDate = modificationDate;
	}
	
}
