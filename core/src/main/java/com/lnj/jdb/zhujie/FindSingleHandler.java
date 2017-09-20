package com.lnj.jdb.zhujie;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Map;

import com.lnj.util.DbUitl;
import com.lnj.util.ErrorUtil;
import com.lnj.util.ReflectUtils;
import com.lnj.util.StringUtil;

public class FindSingleHandler implements JdbcProxyHandler {

	private SelectSingle annotation;

	public FindSingleHandler(SelectSingle selectSingle) {
		this.annotation=selectSingle;
	}

	@Override
	public Object doSelect(Method method, Object[] args) {

			Connection conn = DbUitl.getConn();
			PreparedStatement prepareStatement = null;
			ResultSet set = null;
			
			try {
				//断言，判断之后，不满足直接抛异常
				ErrorUtil.doAssertIsMap(args[0]);
				prepareStatement = conn.prepareStatement(StringUtil.doSql(annotation.sql(),(Map)args[0]));
				set = prepareStatement.executeQuery();
				//获取所有列信息
				ResultSetMetaData metaData = set.getMetaData();
				if(set.next()){
					/**
					 * 表的数据——》实体类中
					 * 表的字段——》对应实体类的属性名
					 * 要解决类型转换的问题
					 */
					try {
						Class<?> forName = Class.forName(annotation.tablename());
						//该对象必须有空参构造器
						Object newInstance = forName.newInstance();
						for(int i=1;i<=metaData.getColumnCount();i++){
							ReflectUtils.set(newInstance,metaData.getColumnLabel(i),
									metaData.getColumnClassName(i),set.getObject(metaData.getColumnLabel(i)));
						}
						return newInstance;
					} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
						ErrorUtil.doException(e,"请确保您的实体类有空参数构造");
					}
					
				}
			} catch (SQLException e) {
				ErrorUtil.doException(e);
			}
			finally {
				DbUitl.close(set, conn, prepareStatement);
			}
			return null;
		}



}
