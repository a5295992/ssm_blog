/** <a href="http://www.cpupk.com/decompiler">Eclipse Class Decompiler</a> plugin, Copyright (c) 2017 Chen Chao. */
package com.baidu.ueditor.define;
import java.util.HashMap;
import java.util.Map;

public final class AppInfo {
	
	public static final int SUCCESS = 0;
	public static final int MAX_SIZE = 1;
	public static final int PERMISSION_DENIED = 2;
	public static final int FAILED_CREATE_FILE = 3;
	public static final int IO_ERROR = 4;
	public static final int NOT_MULTIPART_CONTENT = 5;
	public static final int PARSE_REQUEST_ERROR = 6;
	public static final int NOTFOUND_UPLOAD_DATA = 7;
	public static final int NOT_ALLOW_FILE_TYPE = 8;
	
	public static final int INVALID_ACTION = 101;
	public static final int CONFIG_ERROR = 102;
	
	public static final int PREVENT_HOST = 201;
	public static final int CONNECTION_ERROR = 202;
	public static final int REMOTE_FAIL = 203;
	
	public static final int NOT_DIRECTORY = 301;
	public static final int NOT_EXIST = 302;
	
	public static final int ILLEGAL = 401;

	public static Map<Integer, String> info = new HashMap<Integer, String>(){/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

	{
		
		put( AppInfo.SUCCESS, "SUCCESS" );
		
		// 鏃犳晥鐨凙ction
		put( AppInfo.INVALID_ACTION, "\u65E0\u6548\u7684Action" );
		// 閰嶇疆鏂囦欢鍒濆鍖栧け璐�		put( AppInfo.CONFIG_ERROR, "\u914D\u7F6E\u6587\u4EF6\u521D\u59CB\u5316\u5931\u8D25" );
		// 鎶撳彇杩滅▼鍥剧墖澶辫触
		put( AppInfo.REMOTE_FAIL, "\u6293\u53D6\u8FDC\u7A0B\u56FE\u7247\u5931\u8D25" );
		
		// 琚樆姝㈢殑杩滅▼涓绘満
		put( AppInfo.PREVENT_HOST, "\u88AB\u963B\u6B62\u7684\u8FDC\u7A0B\u4E3B\u673A" );
		// 杩滅▼杩炴帴鍑洪敊
		put( AppInfo.CONNECTION_ERROR, "\u8FDC\u7A0B\u8FDE\u63A5\u51FA\u9519" );
		
		// "鏂囦欢澶у皬瓒呭嚭闄愬埗"
		put( AppInfo.MAX_SIZE, "\u6587\u4ef6\u5927\u5c0f\u8d85\u51fa\u9650\u5236" );
		// 鏉冮檺涓嶈冻锛�澶氭寚鍐欐潈闄�		put( AppInfo.PERMISSION_DENIED, "\u6743\u9650\u4E0D\u8DB3" );
		// 鍒涘缓鏂囦欢澶辫触
		put( AppInfo.FAILED_CREATE_FILE, "\u521B\u5EFA\u6587\u4EF6\u5931\u8D25" );
		// IO閿欒
		put( AppInfo.IO_ERROR, "IO\u9519\u8BEF" );
		// 涓婁紶琛ㄥ崟涓嶆槸multipart/form-data绫诲瀷
		put( AppInfo.NOT_MULTIPART_CONTENT, "\u4E0A\u4F20\u8868\u5355\u4E0D\u662Fmultipart/form-data\u7C7B\u578B" );
		// 瑙ｆ瀽涓婁紶琛ㄥ崟閿欒
		put( AppInfo.PARSE_REQUEST_ERROR, "\u89E3\u6790\u4E0A\u4F20\u8868\u5355\u9519\u8BEF" );
		// 鏈壘鍒颁笂浼犳暟鎹�		put( AppInfo.NOTFOUND_UPLOAD_DATA, "\u672A\u627E\u5230\u4E0A\u4F20\u6570\u636E" );
		// 涓嶅厑璁哥殑鏂囦欢绫诲瀷
		put( AppInfo.NOT_ALLOW_FILE_TYPE, "\u4E0D\u5141\u8BB8\u7684\u6587\u4EF6\u7C7B\u578B" );
		
		// 鎸囧畾璺緞涓嶆槸鐩綍
		put( AppInfo.NOT_DIRECTORY, "\u6307\u5B9A\u8DEF\u5F84\u4E0D\u662F\u76EE\u5F55" );
		// 鎸囧畾璺緞骞朵笉瀛樺湪
		put( AppInfo.NOT_EXIST, "\u6307\u5B9A\u8DEF\u5F84\u5E76\u4E0D\u5B58\u5728" );
		
		// callback鍙傛暟鍚嶄笉鍚堟硶
		put( AppInfo.ILLEGAL, "Callback\u53C2\u6570\u540D\u4E0D\u5408\u6CD5" );
		
	}};
	
	public static String getStateInfo ( int key ) {
		return AppInfo.info.get( key );
	}
	
}