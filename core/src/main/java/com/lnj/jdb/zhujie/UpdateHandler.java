package com.lnj.jdb.zhujie;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

import com.lnj.util.DbUitl;
import com.lnj.util.ErrorUtil;
import com.lnj.util.StringUtil;

public class UpdateHandler implements JdbcProxyHandler {

	private Update annotation;

	public UpdateHandler(Update update) {
		this.annotation=update;
	}

	@Override
	public Object doSelect(Method method, Object[] args) {

		Connection conn = DbUitl.getConn();
		PreparedStatement prepareStatement = null;
		boolean set = false;
		try {
			//断言，判断之后，不满足直接抛异常
			ErrorUtil.doAssertIsMap(args[0]);
			prepareStatement = conn.prepareStatement(StringUtil.doSql(annotation.sql(),(Map)args[0]));
			set = prepareStatement.execute();
			return 1;
		} catch (SQLException e) {
			ErrorUtil.doException(e);
		}
		finally {
			DbUitl.close(null, conn, prepareStatement);
		}
		return 0;
	}

}
