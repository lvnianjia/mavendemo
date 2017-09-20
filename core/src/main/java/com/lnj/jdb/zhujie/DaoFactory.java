package com.lnj.jdb.zhujie;

public class DaoFactory {
	/**
	 * 工厂设计模式
	 * 获取用户需要操作的类型（接口）
	 * 为这个类型整一个动态代理
	 * 将这个代理返回。
	 * @param class1
	 * @return
	 */
	public static Object getDao(Class<?> class1) {

		/**
		 * 接口不能被实例化
		 * cglib和jdk内置的动态代理最大的区别：
		 * cglib会去通过字节码技术帮你去实现一个具体的代理类
		 * 并且返回该代理类的对象
		 */
		ExecSqlProxy proxy = new ExecSqlProxy();
		return proxy.bind(class1);
	}

}
