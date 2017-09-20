package com.lnj.util;

import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.util.Map;
import java.util.regex.Pattern;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;

public class StringUtil {

	public static boolean isNotNull(String text) {
		return text!=null&&text.trim().length()>0;
	}
	/**
	 * 只能用于下划线命名转驼峰命名
	 * @param name
	 * @return
	 */
	public static String tuoFeng(String name) {
		String lowerCase = name.toLowerCase();
		char[] temp=new char[lowerCase.length()];
		for(int i=0;i<lowerCase.length();i++){
			char charAt = lowerCase.charAt(i);
			if(i>0&&lowerCase.charAt(i-1)=='_'){
				temp[i]=(char)(charAt-32);
				continue;
			}
			temp[i]=charAt;
		}
		String result = new String(temp);
		result=result.replace("_", "");
		return result;
	}
	/**
	 * 获得set方法
	 * @param name
	 * @return
	 */
	public static String toSetMethodName(String name) {
		name=tuoFeng(name);
		name=FirstUpper(name);
		return "set"+name;
	}
	/**
	 * 获得get方法
	 * @param name
	 * @return
	 */
	public static String toGetMethodName(String name) {
		name=tuoFeng(name);
		name=FirstUpper(name);
		return "get"+name;
	}

	/**
	 * 首字母大写（没有验证）
	 * @param name
	 * @return
	 */
	public static String FirstUpper(String name) {
		char charAt = name.charAt(0);
		return name.replaceFirst(String.valueOf(charAt),String.valueOf((char)(charAt-32) ));
	}
	
	public static String doSql(String sql, Map map) {
		
		try {
			Configuration configuration = new Configuration(Configuration.VERSION_2_3_23);
			StringTemplateLoader templateLoader = new StringTemplateLoader();
			templateLoader.putTemplate("sql", sql);
			configuration.setTemplateLoader(templateLoader);
			Template template = configuration.getTemplate("sql");
			configuration.setNumberFormat("####");
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			template.process(map, new OutputStreamWriter(stream));
			System.out.println(stream.toString());
			return stream.toString();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
	}
	
	public static void main(String[] args) {

	}
	/**
	 * 密码校验方法.
	 * @param str
	 * @return 如果密码校验通过则返回true.
	 */
		public static boolean checkPassWord(String str) {
		String str1="\\w{6,12}";
		return Pattern.matches(str1, str);
	}
	/**
	 * 账号验证的方法.
	 * @param str
	 * @return 如果账号校验通过则返回true.
	 */
	public static boolean checkUname(String str){
		String str2="\\w{3,12}";
		return Pattern.matches(str2, str);
	}

}
