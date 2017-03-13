package com.sxis.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sxis.model.BookBean;
import com.sxis.model.ReaderBean;



public interface ReaderDao {
	
	public List<ReaderBean> getAllReaderInfo();
	public List<ReaderBean> getNewestReaderInfo();
	public void saveReader(ReaderBean reader);
	public void updateReader(ReaderBean reader);
	public List<ReaderBean> getListByPage(@Param("selectName")String selectName, @Param("selectValue")String selectValue,@Param("currentPage")int currentPage,@Param("pageSize")int pageSize);
	public int getTotalReaderCount();
	public List<ReaderBean> getReaderById(@Param("readerId")String readerId);
	public void deleteReaderById(String id);
	
}
