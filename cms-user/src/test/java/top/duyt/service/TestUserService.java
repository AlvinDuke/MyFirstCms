package top.duyt.service;

import java.util.Arrays;

import javax.inject.Inject;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import top.duyt.dao.IGroupDao;
import top.duyt.dao.IRoleDao;
import top.duyt.dao.IUserDao;
import top.duyt.model.Group;
import top.duyt.model.Role;
import top.duyt.model.User;
import top.duyt.model.UserGroups;
import top.duyt.model.UserRoles;
import top.duyt.service.IUserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/testBeans.xml")
public class TestUserService {

	@Inject
	private IUserDao userDao;
	@Inject
	private IRoleDao roleDao;
	@Inject
	private IGroupDao groupDao;
	
	@Inject
	private IUserService userService;
	
	@Test
	public void testDeleteUser(){
		int uid = 1;
		userDao.delete(uid);
		expectLastCall();
		userDao.deleteUserRole(uid);
		expectLastCall();
		userDao.deleteUserGroup(uid);
		expectLastCall();
		replay(userDao);
		userService.deleteUser(uid);
		verify(userDao);
	}
	
	@Test
	public void testAddUser(){
		//通过spring整合easymockz之后，如果不指定生成方式是，默认是单例
		//之前对userdao的记录还是存在的，所以要先重置一下
		reset(userDao);
		
		//一下的测试不是绝对针对流程的测试，倾向于测试调用的正确性
		//如果对运行过程要求较高，那么就不能使用times和gt（动态参数来）测试
		//需要严格写好传入的参数和传入参数匹配的返回值
		User u = new User();
		u.setId(1);
		u.setUsername("testusername");
		expect(userDao.loadUserByUsername(u.getUsername())).andReturn(null);
		expect(userDao.add(u)).andReturn(u);

		Group group = new Group();
		UserGroups userGroups =  new UserGroups();
		expect(groupDao.load(gt(0))).andReturn(group).times(2);
		expect(userDao.AddUserGroup(u, group)).andReturn(userGroups).times(2);
		
		Role role = new Role();
		UserRoles userRoles = new UserRoles();
		expect(roleDao.load(gt(0))).andReturn(role).times(2);
		expect(userDao.AddUserRole(u, role)).andReturn(userRoles).times(2);
		
		replay(userDao,roleDao,groupDao);
		userService.addUser(u, Arrays.asList(1,2),Arrays.asList(1,2));
		verify(userDao,roleDao,groupDao);
	}
	
	@Test
	public void testUpdateUserStatus(){
		reset(userDao);
		User u = new User();
		u.setId(1);
		u.setStatus("0");
		expect(userDao.load(1)).andReturn(u);
		userDao.update(u);
		expectLastCall();
		replay(userDao);
		userService.updateUserStatus(1);
		verify(userDao);
		
		assertEquals("1", u.getStatus());
		
	}
	
}
