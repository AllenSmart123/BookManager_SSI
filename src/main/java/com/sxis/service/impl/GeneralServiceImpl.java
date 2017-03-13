package com.sxis.service.impl;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sxis.dao.GeneralDao;
import com.sxis.service.GeneralService;

@Service("generalService")
public class  GeneralServiceImpl implements GeneralService {

   private static Logger log = LoggerFactory.getLogger(GeneralServiceImpl.class);

    @Autowired
	private GeneralDao generalDao;
	
    @Override
	public long executeInsertSql(String insertSql) throws SQLException {
		return generalDao.executeInsertSql(insertSql);
	}

	@Override
	public long executeDeleteSql(String deleteSql) throws SQLException {
		return generalDao.executeDeleteSql(deleteSql);
	}
	
	@Override
	public long executeUpdateSql(String updateSql) throws SQLException {
		return generalDao.executeUpdateSql(updateSql);
	}
	
	

}
