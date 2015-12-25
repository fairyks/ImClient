/**
 * Copyright  陈延军 All rights reserved.
 */
package org.fairyks.im.myclient.service;

import android.os.AsyncTask;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;

/**
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>公司名称 :陈延军 </p>
 * <p>项目名称 : MyClient</p>
 * <p>创建时间 : 2015年12月25日 上午9:44:54</p>
 * <p>类描述 :         </p>
 *
 *
 * @version 1.0.0
 * @author <a href=" ">陈延军</a>
 */

public class SendMessage extends AsyncTask<String, String, String> {
	
	/**
	 * <h4>  </h4>
	 * @see android.os.AsyncTask#doInBackground(java.lang.Object[])
	 * @param params
	 * @return
	 * @throws 
	 */
	@Override
	protected String doInBackground(String... params) {

		try {
//			Channel channel = ConnectionManager.getConnectionManager().getChannel();
			// Sends the received message to the server.
			ChannelFuture lastWriteFuture = ConnectionManager.getConnectionManager().getChannel()
					.writeAndFlush(params[0] + "\r\n");
			// Wait until all messages are flushed before closing the channel.
			if (lastWriteFuture != null) {
				lastWriteFuture.sync();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
