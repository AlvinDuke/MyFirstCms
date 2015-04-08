package top.duyt.dao;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import org.dbunit.DatabaseUnitException;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ReplacementDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.orm.hibernate4.SessionHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import top.duyt.dto.Page;
import top.duyt.dto.PageParamHolder;
import top.duyt.model.Article;
import top.duyt.model.Attachment;
import top.duyt.model.Category;
import top.duyt.model.CategoryTreeDto;
import top.duyt.utils.CommonDbunitTestCase;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/beans_core.xml")
public class TestAttachmentDao extends CommonDbunitTestCase{

	@Inject
	private SessionFactory sessionFactory;
	@Inject
	private IAttachmentDao attachmentDao;
	
	/**
	 * 备份数据表
	 * @throws IOException 
	 * @throws SQLException 
	 * @throws FileNotFoundException 
	 * @throws DataSetException 
	 */
	@Before
	public void setUp() throws DataSetException, FileNotFoundException, SQLException, IOException{
		Session s = sessionFactory.openSession();
	    TransactionSynchronizationManager.bindResource(sessionFactory, new SessionHolder(s));
		this.backUpAllTable();
	}
	
	private void initData() throws IOException, DatabaseUnitException, SQLException{
		IDataSet ds = createDataSet("t_datas_core.xml");
		//未使用DTD对xml数据文件进行约束之前，用ReplacementDataSet进行测试
		
		//通过ReplacementDataSet进行数据的插入只能解决数据录入时候的问题，不能解决数据正确的还原
		ReplacementDataSet reds = new ReplacementDataSet(ds);
		reds.addReplacementObject("null", null);
		
		DatabaseOperation.CLEAN_INSERT.execute(connection, reds);
		
	}

	@Test
	public void testfindAttachByAid() throws DatabaseUnitException, SQLException, IOException{
		initData();
		List<Attachment> as =  attachmentDao.findAttachByAid(1);
		assertEquals(5, as.size());
	}
	
	@Test
	public void testfindAttachIdsByAid() throws DatabaseUnitException, SQLException, IOException{
		initData();
		List<String> as =  attachmentDao.findAttachIdsByAid(1);
		assertEquals(5, as.size());
	}
	
	@After
	public void tearDown() throws DatabaseUnitException, SQLException, IOException{
		SessionHolder holder = (SessionHolder)  TransactionSynchronizationManager.getResource(sessionFactory);
		Session s = holder.getSession();
		s.flush();
		TransactionSynchronizationManager.unbindResource(sessionFactory); 
		this.resumeData();
	}
	
}
