/** <a href="http://www.cpupk.com/decompiler">Eclipse Class Decompiler</a> plugin, Copyright (c) 2017 Chen Chao. */
package com.baidu.ueditor.define;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.baidu.ueditor.Encoder;

/*     */
/*     */public class MultiState
/*     */implements State
/*     */{
	/*  19 */private boolean state = false;
	/*  20 */private String info = null;
	/*  21 */private Map<String, Long> intMap = new HashMap<String, Long>();
	/*  22 */private Map<String, String> infoMap = new HashMap<String, String>();
	/*  23 */private List<String> stateList = new ArrayList<String>();
	/*     */
	/*     */public MultiState(boolean state) {
		/*  26 */this.state = state;
		/*     */}
	/*     */
	/*     */public MultiState(boolean state, String info) {
		/*  30 */this.state = state;
		/*  31 */this.info = info;
		/*     */}
	/*     */
	/*     */public MultiState(boolean state, int infoKey) {
		/*  35 */this.state = state;
		/*  36 */this.info = AppInfo.getStateInfo(infoKey);
		/*     */}
	/*     */
	/*     */public boolean isSuccess()
	/*     */{
		/*  41 */return this.state;
		/*     */}
	/*     */
	/*     */public void addState(State state) {
		/*  45 */this.stateList.add(state.toJSONString());
		/*     */}
	/*     */
	/*     */public void putInfo(String name, String val)
	/*     */{
		/*  53 */this.infoMap.put(name, val);
		/*     */}
	/*     */
	/*     */public String toJSONString()
	/*     */{
		/*  59 */String stateVal = (isSuccess())
				? AppInfo.getStateInfo(0)
				: this.info;
		/*     */
		/*  61 */StringBuilder builder = new StringBuilder();
		/*     */
		/*  63 */builder.append("{\"state\": \"" + stateVal + "\"");
		/*     */
		/*  66 */Iterator<String> iterator = this.intMap.keySet().iterator();
		/*     */
		/*  68 */while (iterator.hasNext())
		/*     */{
			/*  70 */stateVal = (String) iterator.next();
			/*     */
			/*  72 */builder.append(",\"" + stateVal + "\": "
					+ this.intMap.get(stateVal));
			/*     */}
		/*     */
		/*  76 */iterator = this.infoMap.keySet().iterator();
		/*     */
		/*  78 */while (iterator.hasNext())
		/*     */{
			/*  80 */stateVal = (String) iterator.next();
			/*     */
			/*  82 */builder.append(",\"" + stateVal + "\": \""
					+ ((String) this.infoMap.get(stateVal)) + "\"");
			/*     */}
		/*     */
		/*  86 */builder.append(", list: [");
		/*     */
		/*  89 */iterator = this.stateList.iterator();
		/*     */
		/*  91 */while (iterator.hasNext())
		/*     */{
			/*  93 */builder.append(((String) iterator.next()) + ",");
			/*     */}
		/*     */
		/*  97 */if (this.stateList.size() > 0) {
			/*  98 */builder.deleteCharAt(builder.length() - 1);
			/*     */}
		/*     */
		/* 101 */builder.append(" ]}");
		/*     */
		/* 103 */return Encoder.toUnicode(builder.toString());
		/*     */}
	/*     */
	/*     */public void putInfo(String name, long val)
	/*     */{
		/* 109 */this.intMap.put(name, Long.valueOf(val));
		/*     */}
	/*     */
}