/** <a href="http://www.cpupk.com/decompiler">Eclipse Class Decompiler</a> plugin, Copyright (c) 2017 Chen Chao. */
/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.along.security;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
/*import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
*/
import org.apache.log4j.Logger;

public class SessionDao extends EnterpriseCacheSessionDAO {
	private Logger logger  = org.apache.log4j.Logger.getLogger((SessionDao.class));

	public SessionDao() {
		super();
	}
	@Override
	protected void doUpdate(Session session) {
		
		logger.debug("do Update");
		super.doUpdate(session);
	}
}