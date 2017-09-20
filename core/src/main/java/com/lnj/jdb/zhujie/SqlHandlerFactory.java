package com.lnj.jdb.zhujie;

import java.lang.reflect.Method;

public class SqlHandlerFactory {

	public static JdbcProxyHandler getInstance(Method method) {
		
		Object result=null;
		Select annotation=method.getAnnotation(Select.class);
		if(annotation!=null){
			return new FindMultyHandler(annotation);
		}
		
		SelectSingle selectSingle = method.getAnnotation(SelectSingle.class);
		if(selectSingle!=null){
			return new FindSingleHandler(selectSingle);
		}
		
		Update update = method.getAnnotation(Update.class);
		if(update!=null){
			return new UpdateHandler(update);
		}
		return null;
		
	}

}
