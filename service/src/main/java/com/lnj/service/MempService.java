package com.lnj.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lnj.dao.MempDao;
import com.lnj.entry.Memp;
import com.lnj.jdb.zhujie.DaoFactory;

public class MempService {
	public List<Memp> findAll(){
		MempDao dao = (MempDao) DaoFactory.getDao(MempDao.class);
		List<Memp> result = dao.findAll(new HashMap());
		return result;
	}
	
	public List<Memp> findSal(int sal){
		MempDao dao = (MempDao) DaoFactory.getDao(MempDao.class);
		Map<String, Object> hashMap = new HashMap<String,Object>();
		hashMap.put("sal", sal);
		List<Memp> result = dao.findSal(hashMap);
		return result;
	}
}
