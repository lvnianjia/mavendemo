package com.lnj.jdb.zhujie;

import java.lang.reflect.Method;

public interface JdbcProxyHandler {
	/**
	 * 这个方法只为select注解服务
	 * @param method
	 * @param args 
	 * @return
	 */
	Object doSelect(Method method, Object[] args);

}