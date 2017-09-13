/** <a href="http://www.cpupk.com/decompiler">Eclipse Class Decompiler</a> plugin, Copyright (c) 2017 Chen Chao. */
package com.baidu.ueditor.define;
/**
 * 澶勭悊鐘舵�鎺ュ彛
 * @author hancong03@baidu.com
 *
 */
public interface State {
	
	public boolean isSuccess ();
	
	public void putInfo( String name, String val );
	
	public void putInfo ( String name, long val );
	
	public String toJSONString ();

}
