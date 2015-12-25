/**
 * Copyright  陈延军 All rights reserved.
 */
package org.fairyks.im.myclient.service;

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
	 * @see io.netty.channel.SimpleChannelInboundHandler#messageReceived(io.netty.channel.ChannelHandlerContext, java.lang.Object)
	 * @param arg0
	 * @param arg1
	 * @throws Exception
	 * @throws 
	 */
	@Override
	protected void messageReceived(ChannelHandlerContext ctx, String msg) throws Exception {
		Intent intent = new Intent("org.fairyks.im.myclient.broadcast");
		intent.putExtra("msg", msg);
		ConnectionManager.getLocalBroadcastManager().sendBroadcast(intent);
		
		
	}

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
