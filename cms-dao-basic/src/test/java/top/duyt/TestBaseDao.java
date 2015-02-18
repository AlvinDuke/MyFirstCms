package top.duyt;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.dbunit.DatabaseUnitException;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.hibernate.ObjectNotFoundException;
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

import top.duyt.dao.IUserDao;
import top.duyt.dto.Page;
import top.duyt.dto.PageParamHolder;
import top.duyt.model.User;
import top.duyt.utils.CommonDbunitTestCase;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/beans.xml")
public class TestBaseDao extends CommonDbunitTestCase{

	@Inject
	private SessionFactory sessionFactory;
	@Inject
	private IUserDao userDao;
	
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
	 * 测试新增
	 * @throws DatabaseUnitException
	 * @throws SQLException
	 * @throws IOException
	 */
	@Test
	public void testAdd() throws DatabaseUnitException, SQLException, IOException{
		IDataSet ds = createDataSet("t_user.xml");
		DatabaseOperation.CLEAN_INSERT.execute(connection, ds);
		User u = new User();
		u.setUsername("addName");
		User tempu = userDao.add(u);
		assertNotNull(u.getId());
		assertEquals(u.getId(), tempu.getId());
	}
	
	/**
	 * 测试删除
	 * @throws DatabaseUnitException
	 * @throws SQLException
	 * @throws IOException
	 */
	/*@Test
	public void testDelete() throws DatabaseUnitException, SQLException, IOException{
		IDataSet ds = createDataSet("t_user.xml");
		DatabaseOperation.CLEAN_INSERT.execute(connection, ds);
		userDao.delete(10);
		User u2  = userDao.load(10);
		assertNull(u2.getUsername());
		//assertEquals("duyt1", u2.getUsername());
	}*/
	
	/**
	 * 测试对象加载
	 * @throws IOException
	 * @throws DatabaseUnitException
	 * @throws SQLException
	 */
	@Test
	public void testload() throws IOException, DatabaseUnitException, SQLException{
		
		IDataSet ds = createDataSet("t_user.xml");
		DatabaseOperation.CLEAN_INSERT.execute(connection, ds);
		User u = userDao.load(1);
		
		assertNotNull(u);
		assertEquals(u.getId(), 1);
		assertEquals(u.getUsername(), "duyt1");
	}
	
	/**
	 * 测试hql加载列表
	 * @throws IOException
	 * @throws DatabaseUnitException
	 * @throws SQLException
	 */
	@Test
	public void testListByMultiParams() throws IOException, DatabaseUnitException, SQLException{
		IDataSet ds = createDataSet("t_user.xml");
		DatabaseOperation.CLEAN_INSERT.execute(connection, ds);
		
		Object[] params = {5,10};
		Map<String, Object> alias = new HashMap<String,Object>();
		alias.put("ids", Arrays.asList(5,7,9));
		List<User> us =  userDao.listByMultiParams("from User u where u.id>? and u.id<? and u.id in (:ids)", params, alias);
		assertEquals(us.size(), 2);
		List<User> tempUs = Arrays.asList(new User(7, "duyt7"),new User(9,"duyt9"));
		for (int i = 0; i < us.size(); i++) {
			assertEquals(us.get(i).getId(), tempUs.get(i).getId());
			assertEquals(us.get(i).getUsername(), tempUs.get(i).getUsername());
		}
		
	}
	
	/**
	 * 测试hql加载分页
	 * @throws IOException
	 * @throws DatabaseUnitException
	 * @throws SQLException
	 */
	@Test
	public void testFindByMultiParams() throws IOException, DatabaseUnitException, SQLException{
		IDataSet ds = createDataSet("t_user.xml");
		DatabaseOperation.CLEAN_INSERT.execute(connection, ds);
		
		PageParamHolder.setOffSet(0);
		PageParamHolder.setPageSize(3);
		PageParamHolder.setSort("u.id");
		PageParamHolder.setOrder("desc");
		
		Object[] params = {2,10};
		Map<String, Object> alias = new HashMap<String,Object>();
		alias.put("ids", Arrays.asList(3,5,7,9));
		Page<User> pus = userDao.findByMultiParams("from User u where u.id>=? and u.id<? and u.id in(:ids)", params, alias);
		
		List<User> tempUs = Arrays.asList(new User(9, "duyt9"),
										  new User(7, "duyt7"),
										  new User(5, "duyt5"),
										  new User(3, "duyt3"));
		
		PageParamHolder.removeAll();
		assertNotNull(pus);
		assertEquals(pus.getTotalRecords(), 4);
		assertEquals(pus.getTotalPages(), 2);
		assertEquals(pus.getOffSet(), 0);
		for (int i = 0; i < pus.getDatas().size(); i++) {
			assertEquals(pus.getDatas().get(i).getId(), tempUs.get(i).getId());
			assertEquals(pus.getDatas().get(i).getUsername(), tempUs.get(i).getUsername());
		}
	}
	
	/**
	 * 测试Sql加载列表
	 * @throws IOException
	 * @throws DatabaseUnitException
	 * @throws SQLException
	 */
	@Test
	public void testListByMultiParamsSql() throws IOException, DatabaseUnitException, SQLException{
		IDataSet ds = createDataSet("t_user.xml");
		DatabaseOperation.CLEAN_INSERT.execute(connection, ds);
		
		Object[] params = {5,10};
		Map<String, Object> alias = new HashMap<String,Object>();
		alias.put("ids", Arrays.asList(5,7,9));
		List<User> us =  userDao.listUserByMultiParamsSql("select * from t_user u where u.id>? and u.id<? and u.id in(:ids)", params, alias, User.class, true);
		assertEquals(us.size(), 2);
		List<User> tempUs = Arrays.asList(new User(7, "duyt7"),new User(9,"duyt9"));
		for (int i = 0; i < us.size(); i++) {
			assertEquals(us.get(i).getId(), tempUs.get(i).getId());
			assertEquals(us.get(i).getUsername(), tempUs.get(i).getUsername());
		}
		
	}
	
	/**
	 * 测试Sql加载分页
	 * @throws IOException
	 * @throws DatabaseUnitException
	 * @throws SQLException
	 */
	@Test
	public void testFindByMultiParamsSql() throws IOException, DatabaseUnitException, SQLException{
		IDataSet ds = createDataSet("t_user.xml");
		DatabaseOperation.CLEAN_INSERT.execute(connection, ds);
		
		PageParamHolder.setOffSet(0);
		PageParamHolder.setPageSize(3);
		PageParamHolder.setSort("u.id");
		PageParamHolder.setOrder("desc");
		
		Object[] params = {2,10};
		Map<String, Object> alias = new HashMap<String,Object>();
		alias.put("ids", Arrays.asList(3,5,7,9));
		Page<User> pus = userDao.findUserByMultiParamsSql("select * from t_user u where u.id>=? and u.id<? and u.id in(:ids)", params, alias, User.class, true);
		
		List<User> tempUs = Arrays.asList(new User(9, "duyt9"),
										  new User(7, "duyt7"),
										  new User(5, "duyt5"),
										  new User(3, "duyt3"));
		
		PageParamHolder.removeAll();
		assertNotNull(pus);
		assertEquals(pus.getTotalRecords(), 4);
		assertEquals(pus.getTotalPages(), 2);
		assertEquals(pus.getOffSet(), 0);
		for (int i = 0; i < pus.getDatas().size(); i++) {
			assertEquals(pus.getDatas().get(i).getId(), tempUs.get(i).getId());
			assertEquals(pus.getDatas().get(i).getUsername(), tempUs.get(i).getUsername());
		}
	}
	
	@After
	public void tearDown() throws FileNotFoundException, DatabaseUnitException, SQLException{
		SessionHolder holder = (SessionHolder)  TransactionSynchronizationManager.getResource(sessionFactory);
		Session s = holder.getSession();
		s.flush();
		TransactionSynchronizationManager.unbindResource(sessionFactory); 
		this.resumeData();
	}
	
}
