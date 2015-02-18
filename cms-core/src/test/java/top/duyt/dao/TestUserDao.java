package top.duyt.dao;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import org.dbunit.DatabaseUnitException;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
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

import top.duyt.dao.IUserDao;
import top.duyt.dto.Page;
import top.duyt.dto.PageParamHolder;
import top.duyt.model.Group;
import top.duyt.model.Role;
import top.duyt.model.User;
import top.duyt.model.UserGroups;
import top.duyt.model.UserRoles;
import top.duyt.model.Emur.RoleType;
import top.duyt.utils.CommonDbunitTestCase;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/beans.xml")
public class TestUserDao extends CommonDbunitTestCase{

	@Inject
	private SessionFactory sessionFactory;
	@Inject
	private IUserDao userDao;
	/*@Inject
	private IRoleDao roleDao;
	@Inject
	private IGroupDao groupDao;*/
	
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
	public void testlistUserRoles() throws DatabaseUnitException, SQLException, IOException{
		IDataSet ds = createDataSet("t_datas.xml");
		DatabaseOperation.CLEAN_INSERT.execute(connection, ds);
		List<Role> us =  userDao.listUserRoles(1);
		assertEquals(1, us.size());
		assertEquals("超级管理员", us.get(0).getRoleName());
		assertEquals("1", us.get(0).getRoleNum());
		assertEquals(RoleType.ROLE_ADMIN, us.get(0).getRoleType());
	}
	
	@Test
	public void testListUserRoleIds() throws IOException, DatabaseUnitException, SQLException{
		IDataSet ds = createDataSet("t_datas.xml");
		DatabaseOperation.CLEAN_INSERT.execute(connection, ds);
		List<Integer> rids =  userDao.listUserRoleIds(1);
		
		assertEquals(1, rids.size());
		assertEquals(1, rids.get(0).intValue());
	}
	
	@Test
	public void testListUserGroups() throws IOException, DatabaseUnitException, SQLException{
		IDataSet ds = createDataSet("t_datas.xml");
		DatabaseOperation.CLEAN_INSERT.execute(connection, ds);
		List<Group> gs =  userDao.listUserGroups(2);
		
		assertEquals(1, gs.size());
		assertEquals("运营组", gs.get(0).getName());
	}
	
	@Test
	public void testListUserGroupIds() throws IOException, DatabaseUnitException, SQLException{
		IDataSet ds = createDataSet("t_datas.xml");
		DatabaseOperation.CLEAN_INSERT.execute(connection, ds);
		List<Integer> gs =  userDao.listUserGroupIds(2);
		
		assertEquals(1, gs.size());
		assertEquals(2, gs.get(0).intValue());
	}
	
	@Test
	public void testLoadUserRoles() throws IOException, DatabaseUnitException, SQLException{
		IDataSet ds = createDataSet("t_datas.xml");
		DatabaseOperation.CLEAN_INSERT.execute(connection, ds);
		UserRoles ur =  userDao.loadUserRoles(2,2);
		
		assertEquals(2, ur.getRole().getId());
		assertEquals("2", ur.getRole().getRoleNum());
		assertEquals("新闻管理员", ur.getRole().getRoleName());
		
		assertEquals(2, ur.getUser().getId());
		assertEquals("duyt2", ur.getUser().getUsername());
	}
	
	@Test
	public void testLoadUserGroups() throws IOException, DatabaseUnitException, SQLException{
		IDataSet ds = createDataSet("t_datas.xml");
		DatabaseOperation.CLEAN_INSERT.execute(connection, ds);
		UserGroups ug =  userDao.loadUserGroups(2,2);
		
		assertEquals(2, ug.getGroup().getId());
		assertEquals("运营组", ug.getGroup().getName());
		
		assertEquals(2, ug.getUser().getId());
		assertEquals("duyt2", ug.getUser().getUsername());
	}
	
	
	@Test
	public void testLoadUserByUsername() throws IOException, DatabaseUnitException, SQLException{
		IDataSet ds = createDataSet("t_datas.xml");
		DatabaseOperation.CLEAN_INSERT.execute(connection, ds);
		User u =  userDao.loadUserByUsername("duyt3");
		
		assertEquals(3, u.getId());
		assertEquals("duyt3", u.getUsername());
		assertEquals("nick3", u.getNickname());
	}
	
	/*@Test
	public void testLoadUserByRoleId() throws IOException, DatabaseUnitException, SQLException{
		IDataSet ds = createDataSet("t_datas.xml");
		DatabaseOperation.CLEAN_INSERT.execute(connection, ds);
		List<User> us =  userDao.listUserByRoleId(3);
		
		assertEquals(1, us.size());
		assertEquals("duyt3", us.get(0).getUsername());
	}*/
	
	//以下注释的代码，在整体运行的时候会卡主，但是单独运行没有问题，备注一下
	
	@Test
	public void testFindUsers() throws IOException, DatabaseUnitException, SQLException{
		IDataSet ds = createDataSet("t_datas.xml");
		DatabaseOperation.CLEAN_INSERT.execute(connection, ds);
		
		PageParamHolder.setPageSize(2);
		PageParamHolder.setOffSet(0);
		
		Page<User> us =  userDao.findUsers();
		
		assertNotNull(us);
		assertEquals(2, us.getDatas().size());
		assertEquals(2, us.getTotalPages());
		assertEquals(3, us.getTotalRecords());
		
	}
	
	/*@Test
	public void testDeleteUserRole() throws IOException, DatabaseUnitException, SQLException{
		IDataSet ds = createDataSet("t_datas.xml");
		DatabaseOperation.CLEAN_INSERT.execute(connection, ds);
		
		List<Integer> rids = userDao.listUserRoleIds(3);
		assertEquals(3, rids.size());
		
		userDao.deleteUserRole(3);
		rids = userDao.listUserRoleIds(3);
		assertEquals(0, rids.size());
	}*/
	
	/*@Test
	public void testDeleteUserGroup() throws IOException, DatabaseUnitException, SQLException{
		IDataSet ds = createDataSet("t_datas.xml");
		DatabaseOperation.CLEAN_INSERT.execute(connection, ds);
		
		List<Integer> gids = userDao.listUserGroupIds(3);
		assertEquals(3, gids.size());
		
		userDao.deleteUserGroup(3);
		gids = userDao.listUserGroupIds(3);
		assertEquals(0, gids.size());
	}*/
	
	/*@Test
	public void testAddUserRoles() throws IOException, DatabaseUnitException, SQLException{
		IDataSet ds = createDataSet("t_datas.xml");
		DatabaseOperation.CLEAN_INSERT.execute(connection, ds);
		Role r = roleDao.load(1);
		User u = userDao.load(1);
				
		UserRoles ur = userDao.AddUserRole(u, r);
		
		assertEquals(1,ur.getRole().getId());
		assertEquals(1,ur.getUser().getId());
	}
	
	@Test
	public void testAddUserGroups() throws IOException, DatabaseUnitException, SQLException{
		IDataSet ds = createDataSet("t_datas.xml");
		DatabaseOperation.CLEAN_INSERT.execute(connection, ds);
		Group g = groupDao.load(1);
		User u = userDao.load(1);
				
		UserGroups ug = userDao.AddUserGroup(u, g);
		
		assertEquals(1,ug.getGroup().getId());
		assertEquals(1,ug.getUser().getId());
	}*/
	
	/*@Test
	public void testLoadUserByRoleType() throws IOException, DatabaseUnitException, SQLException{
		IDataSet ds = createDataSet("t_datas.xml");
		DatabaseOperation.CLEAN_INSERT.execute(connection, ds);
		List<User> us =  userDao.loadUserByRoleType(RoleType.ROLE_PUBLISH);
		
		assertEquals(1, us.size());
		assertEquals("duyt2", us.get(0).getUsername());
	}
	
	@Test
	public void testLoadUserByGroupId() throws IOException, DatabaseUnitException, SQLException{
		IDataSet ds = createDataSet("t_datas.xml");
		DatabaseOperation.CLEAN_INSERT.execute(connection, ds);
		
		List<User> us =  userDao.loadUserByGroupId(3);
		assertEquals(1, us.size());
		assertEquals("duyt3", us.get(0).getUsername());
	}*/
	
	@After
	public void tearDown() throws DatabaseUnitException, SQLException, IOException{
		SessionHolder holder = (SessionHolder)  TransactionSynchronizationManager.getResource(sessionFactory);
		Session s = holder.getSession();
		s.flush();
		TransactionSynchronizationManager.unbindResource(sessionFactory); 
		this.resumeData();
	}
	
}
