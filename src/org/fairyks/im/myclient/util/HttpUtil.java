/**
 * Copyright  陈延军 All rights reserved.
 */
package org.fairyks.im.myclient.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;

import android.util.Log;
import cz.msebera.android.httpclient.HttpStatus;

/**
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>公司名称 :陈延军 </p>
 * <p>项目名称 : ImClient</p>
 * <p>创建时间 : 2016年1月7日 下午2:01:03</p>
 * <p>类描述 :         </p>
 *
 *
 * @version 1.0.0
 * @author <a href=" ">陈延军</a>
 */

public class HttpUtil {
	

	public static String post(String url,String params){
		String response = "";
		try{
			HttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(url);
//			httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
			StringEntity stringEntity = new StringEntity(params.toString(),"UTF-8");
			stringEntity.setContentEncoding("UTF-8");
			stringEntity.setContentType("application/json");
			httpPost.setEntity(stringEntity);
			HttpResponse httpResponse = httpClient.execute(httpPost);
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity responseEntity = httpResponse.getEntity();
				response = EntityUtils.toString(responseEntity, "UTF-8");
		}
		} catch (Exception e) {
			e.printStackTrace();
			Log.e("error", e.getMessage());
		}
		return response;
	}
	
	public static String toJson(Object object){
		Gson gson = new Gson();
		return gson.toJson(object).toString();
	}
	  
}