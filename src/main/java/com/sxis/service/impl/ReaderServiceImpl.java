package com.sxis.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sxis.dao.BookDao;
import com.sxis.dao.ReaderDao;
import com.sxis.model.BookBean;
import com.sxis.model.ReaderBean;
import com.sxis.service.BookService;
import com.sxis.service.ReaderService;

@Service("readerService")
public class ReaderServiceImpl implements ReaderService {
	
	@Autowired
	private ReaderDao readerDao;
	
	@Override
	public List<ReaderBean> getAllReaderInfo(){
		List<ReaderBean> blist = readerDao.getAllReaderInfo();
		if(blist != null){
			return blist;
		}
		return null;
	}
	@Override
	public int getTotalReaderCount(){
		return readerDao.getTotalReaderCount();
	}

	@Override
	public List<ReaderBean> getListByPage(String selectName,String selectValue, int currentPage, int pageSize) {
		return readerDao.getListByPage(selectName, selectValue, currentPage, pageSize);
	}

	@Override
	public List<ReaderBean> getReaderById(String readerId) {
		return readerDao.getReaderById(readerId);
	}
	@Override
	public List<ReaderBean> getNewestReaderInfo() {
		return readerDao.getNewestReaderInfo();
	}
	@Override
	public void saveReader(ReaderBean reader) {
		readerDao.saveReader(reader);
		
	}
	@Override
	public void updateReader(ReaderBean reader) {
		readerDao.updateReader(reader);
	}
	@Override
	public void deleteReaderByIds(String[] ids) {
		for (int i = 0; i < ids.length; i++) {
			readerDao.deleteReaderById(ids[i]);
		}
	}
}
