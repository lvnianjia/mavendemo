package com.lnj.util;

import java.util.Map;

public class ErrorUtil {

	public static void doException(Exception e) {
		e.printStackTrace();
	}

	public static void doException(ReflectiveOperationException e, String string) {
		e.printStackTrace();
	}

	public static void doAssertIsMap(Object parm) {
		if(!(parm instanceof Map))
			throw new RuntimeException("不是Map");
	}

}
