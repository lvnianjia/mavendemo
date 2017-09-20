package com.lnj.jdb.zhujie;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class ExecSqlProxy implements MethodInterceptor {

	private Enhancer enhancer;

	public Object bind(Class<?> class1) {
		enhancer = new Enhancer();
		enhancer.setSuperclass(class1);
		enhancer.setCallback(this);
		Object result = enhancer.create();
		return result;
	}

	@Override
	public Object intercept(Object obj,
			Method method, Object[] args, MethodProxy proxy) throws Throwable {
		Select annotation = method.getAnnotation(Select.class);
		JdbcProxyHandler handler=SqlHandlerFactory.getInstance(method);
		if(handler!=null){//走select注解的逻辑
			return handler.doSelect(method,args);
		}
		return null;
		 
	}

	
}
