package com.questionnaire.service.impl;

import com.questionnaire.pojo.Ks;
import com.questionnaire.dao.KsDao;
import com.questionnaire.service.KsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service("ksService")
public class KsServiceImpl implements KsService {

	@Autowired
	private KsDao ksDao;

	/**
	*增加Ks
	*author:xjg
	*/
	@Override
	public Integer addKs(Ks ks) {
			ksDao.addKs(ks);
			return ks.getId();
	}

	/**
	*删除Ks
	*author:xjg
	*/
	@Override
	public boolean deleteKs(Ks ks)   {
			return ksDao.deleteKs(ks);
	}

	/**
	*更新Ks
	*author:xjg
	*/
	@Override
	public Integer updateKs(Ks ks)   {
			return ksDao.updateKs(ks);
	}

	/**
	*统计总数Ks
	*author:xjg
	*/
	@Override
	public Integer countKs(Ks ks)  {
			return ksDao.countKs(ks);
	}

	/**
	*查找Ks
	*author:xjg
	*/
	@Override
	public Ks findKs(Ks ks)  {
			return ksDao.findKs(ks);
	}

	/**
	*查找列表Ks
	*author:xjg
	*/
	@Override
	public List<Ks> findKsList(Ks ks) {
			return ksDao.findKsList(ks);
	}
}

