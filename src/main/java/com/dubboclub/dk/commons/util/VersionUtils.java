/*
 * Project: akso-base
 * 
 * File Created at 2016年12月13日
 * 
 * Copyright 2016 CMCC Corporation Limited.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * ZYHY Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license.
 */
package com.dubboclub.dk.commons.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Type VersionUtils.java
 * @Desc
 * @author Alice
 * @date 2016年12月13日 上午10:50:31
 * @version
 */
public class VersionUtils {
	/**
	 * 版本号校验
	 * 
	 * @param appVersion
	 * @return
	 */
	public static boolean isAppVersion(String appVersion) {
		Pattern regex = Pattern.compile("^\\d+\\.\\d+\\.\\d+$");
		Matcher matcher = regex.matcher(appVersion);
		return matcher.matches();
	}

	/**
	 * 版本比较
	 * 
	 * @param oldVersion
	 * @param newVersion
	 * @return
	 */
	public static boolean compareVersion(String oldVersion, String newVersion) {
		if (oldVersion == null) {
			return true;
		} else if (isAppVersion(oldVersion)) {
			int oldVersionInt = 0;
			int newVersionInt = 0;
			String oldSplitStr = null;
			String newSplitStr = null;
			while (true) {
				oldSplitStr = null;
				newSplitStr = null;
				int j = oldVersion.indexOf('.');
				int k = newVersion.indexOf('.');
				if (j < 0 || k < 0) {
					break;
				}
				oldSplitStr = oldVersion.substring(0, j);
				newSplitStr = newVersion.substring(0, k);
				oldVersionInt = Integer.parseInt(oldSplitStr);
				newVersionInt = Integer.parseInt(newSplitStr);
				if (newVersionInt > oldVersionInt) {
					return true;
				}
				oldVersion = oldVersion.substring(j + 1);
				newVersion = newVersion.substring(k + 1);
			}
			oldVersionInt = Integer.parseInt(oldVersion);
			newVersionInt = Integer.parseInt(newVersion);
			if (newVersionInt > oldVersionInt) {
				return true;
			}
			return false;
		} else {
			return false;
		}
	}

}

/**
 * Revision history
 * -------------------------------------------------------------------------
 * 
 * Date Author Note
 * -------------------------------------------------------------------------
 * 2016年12月13日 Alice creat
 */
