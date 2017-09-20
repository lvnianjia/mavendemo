package com.lnj.dao;

import java.util.List;
import java.util.Map;

import com.lnj.entry.Memp;
import com.lnj.jdb.zhujie.Select;

public interface MempDao {
	@Select(tablename="com.lnj.entry.Memp",sql="select * from Memp ")
	List<Memp> findAll(Map tableName);
	
	@Select(tablename="com.lnj.entry.Memp",sql="select * from Memp where sal>='${sal}'")
	List<Memp> findSal(Map tableName);
}
