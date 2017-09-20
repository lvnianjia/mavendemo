package com.lnj.util;

import java.lang.reflect.Method;

import com.lnj.util.ErrorUtil;

public class ReflectUtils {
/**
 * 	给一个对象取set值
 * @param obj
 * @param columnLabel
 * @param javaType
 * @param parm
 */

	public static void set(Object obj, 
			String columnLabel, String javaType, Object parm) {
	
		try {
			String name = StringUtil.toSetMethodName(columnLabel);
			Class<?> parameterType = Class.forName(javaType);
			Method method = obj.getClass().getMethod(name, parameterType);
			method.invoke(obj, parm);
		} catch (Exception e) {
			ErrorUtil.doException(e);
		} 
	}

}
