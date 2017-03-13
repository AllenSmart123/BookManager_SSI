package com.sxis.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sxis.model.BookBean;
import com.sxis.model.ReaderBean;

public interface ReaderService {
	public List<ReaderBean> getAllReaderInfo();
	public List<ReaderBean> getNewestReaderInfo();
	public List<ReaderBean> getListByPage(String selectName,String selectValue, int currentPage, int pageSize);
	public int getTotalReaderCount();
	public void saveReader(ReaderBean reader);
	public void updateReader(ReaderBean reader);
	public List<ReaderBean> getReaderById(String readerId);
	public void deleteReaderByIds(String[] ids);
}
