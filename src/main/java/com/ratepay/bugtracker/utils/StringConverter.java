package com.ratepay.bugtracker.utils;

import org.apache.commons.lang3.StringUtils;

public class StringConverter {

	public static String trimAndConvertToLowerCase(String str) {
		return StringUtils.isNotEmpty(str) ? StringUtils.lowerCase(StringUtils.trim(str)) : StringUtils.EMPTY;
	}
}
