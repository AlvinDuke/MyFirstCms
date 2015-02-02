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

import top.duyt.model.Category;
import top.duyt.model.CategoryTreeDto;
import util.CommonDbunitTestCase;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/beans.xml")
public class TestCategoryDao extends CommonDbunitTestCase{

	@Inject
	private SessionFactory sessionFactory;
	@Inject
	private ICategoryDao categoryDao;
	
	
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
	
	/**
	 *  DBunit会把第一条数据作为该表的模板数据，依照第一条数据的插入字段进行数据插入，如果第一条数据中某个字段
	 *	没有设置值，那么后续的记录也不会查询该字段的值，哪怕后续的记录中有该字段也不会插入。
	 *	第一条数据的字段缺失，会影响插入测试数据，还会影响还原备份数据（DBunit根据表内容备份好的第一条记录某一个字段没有值，比如父类目的父ID字段为null，那么DBunit不会备份
	 *	该数据，所以备份好的第一条记录文件中没有该字段，进而导致还原表数据的时候，父类目id或有全部为null的情况）
	 *
	 *  此处测试的是含有外键关联的记录，所以在恢复表原始数据的时候有可能出现不能删除含有外键记录的情况
	 *  需要修改Mysql连接字符串为jdbc:mysql://localhost:3306/cms_alvin?user=root&password=root&sessionVariables=FOREIGN_KEY_CHECKS=0
	 * @throws DatabaseUnitException
	 * @throws SQLException
	 * @throws IOException
	 */
	@Test
	public void testlistCateByPcid() throws DatabaseUnitException, SQLException, IOException{
		IDataSet ds = createDataSet("t_category.xml");
		//未使用DTD对xml数据文件进行约束之前，用ReplacementDataSet进行测试
		
		//通过ReplacementDataSet进行数据的插入只能解决数据录入时候的问题，不能解决数据正确的还原
		ReplacementDataSet reds = new ReplacementDataSet(ds);
		reds.addReplacementObject("null", null);
		
		DatabaseOperation.CLEAN_INSERT.execute(connection, reds);
		List<Category> cates =  categoryDao.listCateByPcid(null);
		assertEquals(3, cates.size());
	}
	
	@Test
	public void testFindMaxOrdersUnderParent() throws IOException, DatabaseUnitException, SQLException{
		IDataSet ds = createDataSet("t_category.xml");
		ReplacementDataSet reds = new ReplacementDataSet(ds);
		reds.addReplacementObject("null", null);
		
		DatabaseOperation.CLEAN_INSERT.execute(connection, reds);
		Integer max =  categoryDao.findMaxOrdersUnderParent(2);
		
		assertEquals(null, max);
	}
	
	@Test
	public void testListAllCateTreeDto() throws IOException, DatabaseUnitException, SQLException{
		IDataSet ds = createDataSet("t_category.xml");
		ReplacementDataSet reds = new ReplacementDataSet(ds);
		reds.addReplacementObject("null", null);
		
		DatabaseOperation.CLEAN_INSERT.execute(connection, reds);
		List<CategoryTreeDto> ctds =  categoryDao.listAllCateTreeDto();
		
		assertEquals(12, ctds.size());
	}
	
	@Test
	public void testListCateTreeDtoByPid() throws IOException, DatabaseUnitException, SQLException{
		IDataSet ds = createDataSet("t_category.xml");
		ReplacementDataSet reds = new ReplacementDataSet(ds);
		reds.addReplacementObject("null", null);
		
		DatabaseOperation.CLEAN_INSERT.execute(connection, reds);
		List<CategoryTreeDto> ctds =  categoryDao.listCateTreeDtoByPid(1);
		
		assertEquals(3, ctds.size());
	}
	
	@Test
	public void testUpdateCategorysOrders() throws IOException, DatabaseUnitException, SQLException{
		IDataSet ds = createDataSet("t_category.xml");
		ReplacementDataSet reds = new ReplacementDataSet(ds);
		reds.addReplacementObject("null", null);
		
		DatabaseOperation.CLEAN_INSERT.execute(connection, reds);
		
		categoryDao.updateCategorysOrders(new Integer[]{9,5,1});
		List<Category> cs = categoryDao.listCateByPcid(0);
		assertEquals(9,cs.get(0).getId());
		assertEquals(5,cs.get(1).getId());
		assertEquals(1,cs.get(2).getId());
		
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
