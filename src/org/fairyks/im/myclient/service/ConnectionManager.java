/**
 * Copyright  陈延军 All rights reserved.
 */
package org.fairyks.im.myclient.service;

import android.support.v4.content.LocalBroadcastManager;
import io.netty.channel.Channel;

/**
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>公司名称 :陈延军 </p>
 * <p>项目名称 : MyClient</p>
 * <p>创建时间 : 2015年12月25日 上午9:09:31</p>
 * <p>类描述 :         </p>
 *
 *
 * @version 1.0.0
 * @author <a href=" ">陈延军</a>
 */

public class ConnectionManager {

	private Channel channel;
	private static LocalBroadcastManager localBroadcastManager;
	
	private static ConnectionManager connectionManager = null;

	private ConnectionManager() {
	}

	public static ConnectionManager getConnectionManager() {
		if (connectionManager != null) {
			return connectionManager;
		} else {
			connectionManager = new ConnectionManager();
			return connectionManager;
		}
	}

	/**
	 * @return the channel
	 */
	public Channel getChannel() {
		return channel;
	}

	/**
	 * @param channel the channel to set
	 */
	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	/**
	 * @return the localBroadcastManager
	 */
	public static LocalBroadcastManager getLocalBroadcastManager() {
		return localBroadcastManager;
	}

	/**
	 * @param localBroadcastManager the localBroadcastManager to set
	 */
	public static void setLocalBroadcastManager(LocalBroadcastManager localBroadcastManager) {
		ConnectionManager.localBroadcastManager = localBroadcastManager;
	}
}
