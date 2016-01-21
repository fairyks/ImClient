/**
 * Copyright  陈延军 All rights reserved.
 */
package org.fairyks.im.myclient.service;

import org.fairyks.im.myclient.bean.Packet;
import org.fairyks.im.myclient.util.Constant;

import com.google.gson.Gson;

import android.content.Intent;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>公司名称 :陈延军 </p>
 * <p>项目名称 : MyServer</p>
 * <p>创建时间 : 2015年12月24日 下午1:40:20</p>
 * <p>类描述 :         </p>
 *
 *
 * @version 1.0.0
 * @author <a href=" ">陈延军</a>
 */

public class ChatClientHandler extends SimpleChannelInboundHandler<String> {
	
	/**
	 * <h4>  </h4>
	 * @param arg0
	 * @param arg1
	 * @throws Exception
	 * @throws 
	 */
	@Override
	protected void messageReceived(ChannelHandlerContext context, String message) throws Exception {
		Gson gson = new Gson();
		//解析message,根据不同类型,发送不同广播
		Packet packet = gson.fromJson(message, Packet.class);
		
		if (packet.getType().equals(Constant.IQ_SEARCH_FRIEND)) {
			Intent intent = new Intent("org.fairyks.searchFriendInfoBroadcast");
			intent.putExtra("searchResult", message);
			ConnectionManager.getLocalBroadcastManager().sendBroadcast(intent);
		}else if (packet.getType().equals(Constant.CHAT)) {
			Intent intent = new Intent("org.fairyks.chatMsessageBroadcast");
			intent.putExtra("message", message);
			ConnectionManager.getLocalBroadcastManager().sendBroadcast(intent);
		}
//		switch (packet.getType()) {
//		case Constant.PRESENCE_ONLINE:
//			
//			break;
//		case Constant.PRESENCE_OFFLINE:
//			
//			break;
//		case Constant.IQ_SEARCH_FRIEND:
//			break;
//		case Constant.IQ_ADD_FRIEND:
//			
//			break;
//		case Constant.IQ_DELETE_FRIEND:
//			
//			break;
//		case Constant.CHAT:
//			break;
//		case Constant.GROUP_CHAT:
//			break;
//		default:
//			break;
//		}
	}

	
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
