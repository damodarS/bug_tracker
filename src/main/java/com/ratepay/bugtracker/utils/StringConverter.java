package com.ratepay.bugtracker.utils;

import org.apache.commons.lang3.StringUtils;

public class StringConverter {

	public static String trimAndConvertToUpperCase(String str) {
		return StringUtils.isNotEmpty(str) ? StringUtils.upperCase(StringUtils.trim(str)) : StringUtils.EMPTY;
	}
}
