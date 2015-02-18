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
import top.duyt.model.Group;
import top.duyt.model.GroupCategory;
import top.duyt.utils.CommonDbunitTestCase;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/beans.xml")
public class TestGroupDao extends CommonDbunitTestCase{

	@Inject
	private SessionFactory sessionFactory;
	@Inject
	private IGroupDao groupDao;
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
	
	@Test
	public void testUpdateCategorysOrders() throws IOException, DatabaseUnitException, SQLException{
		IDataSet ds = createDataSet("t_group_category.xml");
		ReplacementDataSet reds = new ReplacementDataSet(ds);
		reds.addReplacementObject("null", null);
		
		DatabaseOperation.CLEAN_INSERT.execute(connection, reds);
		
		List<GroupCategory> gcs = groupDao.listGroupCategoriesByGid(1);
		assertEquals(4, gcs.size());
		assertEquals(3, gcs.get(0).getCategory().getId());
		assertEquals(5, gcs.get(1).getCategory().getId());
		assertEquals(7, gcs.get(2).getCategory().getId());
		assertEquals(9, gcs.get(3).getCategory().getId());
		
	}
	
	@Test
	public void testListCateTreeByGid() throws IOException, DatabaseUnitException, SQLException{
		IDataSet ds = createDataSet("t_group_category.xml");
		ReplacementDataSet reds = new ReplacementDataSet(ds);
		reds.addReplacementObject("null", null);
		
		DatabaseOperation.CLEAN_INSERT.execute(connection, reds);
		
		List<CategoryTreeDto> cids = groupDao.listCateTreeByGid(1);
		assertEquals(4, cids.size());
		
		assertEquals(3, cids.get(0).getId());
		assertEquals(5, cids.get(1).getId());
		assertEquals(7, cids.get(2).getId());
		assertEquals(9, cids.get(3).getId());
		
		assertEquals(1, cids.get(0).getpId().intValue());
		assertEquals(9999, cids.get(1).getpId().intValue());
		assertEquals(5, cids.get(2).getpId().intValue());
		assertEquals(9999, cids.get(3).getpId().intValue());
		
	}
	
	@Test
	public void testListCateTreeByUid() throws IOException, DatabaseUnitException, SQLException{
		IDataSet ds = createDataSet("t_group_category.xml");
		ReplacementDataSet reds = new ReplacementDataSet(ds);
		reds.addReplacementObject("null", null);
		
		DatabaseOperation.CLEAN_INSERT.execute(connection, reds);
		
		List<CategoryTreeDto> cids = groupDao.listCateTreeByUid(2);
		assertEquals(3, cids.size());
		
		assertEquals(2, cids.get(0).getId());
		assertEquals(4, cids.get(1).getId());
		assertEquals(6, cids.get(2).getId());
		
		assertEquals(1, cids.get(0).getpId().intValue());
		assertEquals(1, cids.get(1).getpId().intValue());
		assertEquals(5, cids.get(2).getpId().intValue());
		
	}
	
	@Test
	public void testAddGroupCategory() throws IOException, DatabaseUnitException, SQLException{
		IDataSet ds = createDataSet("t_group_category.xml");
		ReplacementDataSet reds = new ReplacementDataSet(ds);
		reds.addReplacementObject("null", null);
		
		DatabaseOperation.CLEAN_INSERT.execute(connection, reds);
		
		GroupCategory gc =  groupDao.addGroupCategory(1, 2);
		assertNotNull(gc);
	}	
	
	@Test
	public void testDeleteGroupCategory() throws IOException, DatabaseUnitException, SQLException{
		IDataSet ds = createDataSet("t_group_category.xml");
		ReplacementDataSet reds = new ReplacementDataSet(ds);
		reds.addReplacementObject("null", null);
		
		DatabaseOperation.CLEAN_INSERT.execute(connection, reds);
		
		groupDao.deleteGroupCategory(1,3);
		
		GroupCategory gc = groupDao.loadGroupCategory(1, 3);
		assertNull(gc);
	}	
	
	@Test
	public void testGenerateCateTreeByGid() throws IOException, DatabaseUnitException, SQLException{
		IDataSet ds = createDataSet("t_group_category.xml");
		ReplacementDataSet reds = new ReplacementDataSet(ds);
		reds.addReplacementObject("null", null);
		
		DatabaseOperation.CLEAN_INSERT.execute(connection, reds);
		
		List<CategoryTreeDto> cts = groupDao.generateCateTreeByGid(1);
		assertEquals(7, cts.size());
		
	}
	
	@Test
	public void testGenerateCateTreeByUid() throws IOException, DatabaseUnitException, SQLException{
		IDataSet ds = createDataSet("t_group_category.xml");
		ReplacementDataSet reds = new ReplacementDataSet(ds);
		reds.addReplacementObject("null", null);
		
		DatabaseOperation.CLEAN_INSERT.execute(connection, reds);
		
		List<CategoryTreeDto> cts = groupDao.generateCateTreeByUid(2);
		assertEquals(5, cts.size());
		
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
