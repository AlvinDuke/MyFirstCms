package top.duyt.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import top.duyt.Exception.CmsException;
import top.duyt.dao.IGroupDao;
import top.duyt.dao.IUserDao;
import top.duyt.model.Category;
import top.duyt.model.CategoryTreeDto;
import top.duyt.model.Group;
import top.duyt.model.GroupCategory;
import top.duyt.model.User;
import top.duyt.service.IGroupService;
import top.duyt.dto.Page;

/**
 * 分组数据服务实现
 * @author Alvin Du
 *
 */
@Service("groupService")
public class GroupService implements IGroupService {

	private IGroupDao groupDao;
	private IUserDao userDao;

	@Override
	public void addGroup(Group group) {
		groupDao.add(group);
	}

	@Override
	public void deleteGroup(int gid) {
		List<User> users = userDao.listUserByGroupId(gid);
		if (users != null && users.size() > 0) {
			throw new CmsException("分组内含有用户，不可删除");
		}else{
			groupDao.delete(gid);
		}
	}

	@Override
	public List<Group> listAllGroups() {
		return groupDao.listAllGroups();
	}
	
	@Override
	public Page<Group> findGroups() {
		return groupDao.findGroups();
	}
	
	@Override
	public Group load(int gid) {
		return groupDao.load(gid);
	}

	@Override
	public void update(Group g) {
		groupDao.update(g);
	}

	public IGroupDao getGroupDao() {
		return groupDao;
	}

	@Inject
	public void setGroupDao(IGroupDao groupDao) {
		this.groupDao = groupDao;
	}

	public IUserDao getUserDao() {
		return userDao;
	}

	@Inject
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public List<GroupCategory> listGroupCategoriesByGid(Integer gid) {
		return groupDao.listGroupCategoriesByGid(gid);
	}

	@Override
	public List<CategoryTreeDto> listCateTreeByGid(Integer gid) {
		return groupDao.listCateTreeByGid(gid);
	}

	@Override
	public List<CategoryTreeDto> listCateTreeByUid(Integer uid) {
		return groupDao.listCateTreeByUid(uid);
	}

	@Override
	public GroupCategory addGroupCategory(Integer gid,Integer cid) {
		return groupDao.addGroupCategory(gid, cid);
	}

	@Override
	public void deleteGroupCategory(Integer gid, Integer cid) {
		groupDao.deleteGroupCategory(gid, cid);
	}

	@Override
	public GroupCategory loadGroupCategory(Integer gid, Integer cid) {
		return groupDao.loadGroupCategory(gid, cid);
	}

	@Override
	public List<CategoryTreeDto> generateCateTreeByGid(Integer gid) {
		return groupDao.generateCateTreeByGid(gid);
	}

	@Override
	public List<CategoryTreeDto> generateCateTreeByUid(Integer uid) {
		return groupDao.generateCateTreeByUid(uid);
	}

}
