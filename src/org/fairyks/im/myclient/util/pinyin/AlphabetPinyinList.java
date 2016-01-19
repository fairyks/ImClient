/**
 * Copyright  陈延军 All rights reserved.
 */
package org.fairyks.im.myclient.util.pinyin;

import org.fairyks.im.myclient.util.mapsort.HashList;
import org.fairyks.im.myclient.util.mapsort.KeySort;

import net.sourceforge.pinyin4j.PinyinHelper;

/**
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>公司名称 :陈延军 </p>
 * <p>项目名称 : ImClient</p>
 * <p>创建时间 : 2016年1月15日 下午3:26:02</p>
 * <p>类描述 :         </p>
 *
 *
 * @version 1.0.0
 * @author <a href=" ">陈延军</a>
 */

public class AlphabetPinyinList {
	
	private HashList<String,String> hashList=new HashList<String,String>(new KeySort<String,String>(){

		@Override
		public String getKey(String value) {
			return getFirstChar(value);
		}});
	
	public HashList<String, String> getHashList() {
		return hashList;
	}
	
	//获得字符串的首字母 首字符 转汉语拼音
	public  String getFirstChar(String value) {
		// 首字符
		char firstChar = value.charAt(0);
		// 首字母分类
		String first = null;
		// 是否是非汉字
		String[] print = PinyinHelper.toHanyuPinyinStringArray(firstChar);

		if (print == null) {

			// 将小写字母改成大写
			if ((firstChar >= 97 && firstChar <= 122)) {
				firstChar -= 32;
			}
			if (firstChar >= 65 && firstChar <= 90) {
				first = String.valueOf((char) firstChar);
			} else {
				// 认为首字符为数字或者特殊字符
				first = "#";
			}
		} else {
			// 如果是中文 分类大写字母
			first = String.valueOf((char)(print[0].charAt(0) -32));
		}
		if (first == null) {
			first = "?";
		}
		return first;
	}
}