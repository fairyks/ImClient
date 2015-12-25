/**
 * Copyright  陈延军 All rights reserved.
 */
package org.fairyks.im.myclient.service;

import org.fairyks.im.myclient.util.GlobalVar;

import android.util.Log;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;

/**
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>公司名称 :陈延军 </p>
 * <p>项目名称 : MyClient</p>
 * <p>创建时间 : 2015年12月24日 下午3:23:20</p>
 * <p>类描述 :    通信服务     </p>
 *
 *
 * @version 1.0.0
 * @author <a href=" ">陈延军</a>
 */

public class CommunicationService implements Runnable {

	private static Channel channel = null;
	private static EventLoopGroup group = null;
	private static  CommunicationService communicationService = null;

	private CommunicationService() {
	}

	public static CommunicationService getCommunicationService() {
		if (communicationService != null) {
			return communicationService;
		} else {
			communicationService = new CommunicationService();
			return communicationService;
		}
	}

	/**
	 * 
	 * 方法描述 : 连接到通信服务器
	 */
	public void connect() {

		try {
			String host = GlobalVar.HOST;
			int port = GlobalVar.PORT;
			final SslContext sslContext = SslContext.newClientContext(InsecureTrustManagerFactory.INSTANCE);
			group = new NioEventLoopGroup();
			Bootstrap bootstrap = new Bootstrap();
			bootstrap.group(group).channel(NioSocketChannel.class).handler(new ChatClientInitializer(sslContext));
			channel = bootstrap.connect(host, port).sync().channel();
			ConnectionManager.getConnectionManager().setChannel(channel);
			//			channel = bootstrap.connect(GlobalVar.HOST, GlobalVar.PORT).sync().channel();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
//			group.shutdownGracefully();
		}
	}

	/**
	 * 
	 * 方法描述 : 发送消息
	 * @param message
	 */
	public static void sendMessage(final String message) {
		try {
			// Sends the received message to the server.
			ChannelFuture lastWriteFuture = channel.writeAndFlush(message + "\r\n");
			// Wait until all messages are flushed before closing the channel.
			if (lastWriteFuture != null) {
				lastWriteFuture.sync();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//			}).start();

	// If user typed the 'bye' command, wait until the server closes
	// the connection.
	//			if ("bye".equals(message.toLowerCase())) {
	//				channel.closeFuture().sync();
	//			}

	/**
	 * <h4>  </h4>
	 * @see java.lang.Runnable#run()
	 * @throws 
	 */
	@Override
	public void run() {

		try {
			final SslContext sslContext = SslContext.newClientContext(InsecureTrustManagerFactory.INSTANCE);
			group = new NioEventLoopGroup();
			Bootstrap bootstrap = new Bootstrap();
			bootstrap.group(group).channel(NioSocketChannel.class).handler(new ChatClientInitializer(sslContext));
			channel = bootstrap.connect(GlobalVar.HOST, GlobalVar.PORT).sync().channel();
		} catch (Exception e) {
			e.printStackTrace();
			Log.i("bug", e.getMessage().toString());
		} finally {
			//			group.shutdownGracefully();
		}
	}

	/**
	 * @param communicationService the communicationService to set
	 */
	public void setCommunicationService(CommunicationService communicationService) {
		this.communicationService = communicationService;
	}
	
}
