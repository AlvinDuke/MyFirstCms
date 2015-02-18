package top.duyt.service;

import java.util.List;

import top.duyt.model.Category;
import top.duyt.model.CategoryTreeDto;
import top.duyt.model.Group;
import top.duyt.model.GroupCategory;
import top.duyt.dto.Page;

public interface IGroupService {

	/**
	 * 添加一个新组
	 * @param group
	 */
	public void addGroup(Group group);
	
	/**
	 * 删除一个组
	 * @param gid
	 */
	public void deleteGroup(int gid);
	
	/**
	 * 查询一个组对象
	 * @param gid
	 * @return
	 */
	public Group load(int gid);
	
	/**
	 * 更新一个组
	 * @param g
	 */
	public void update(Group g);
	
	/**
	 * 列表出所有的组
	 */
	public List<Group> listAllGroups();
	
	/**
	 * 获取用户组分页列表
	 * @return
	 */
	public Page<Group> findGroups();
	
	/**
	 * 根据组id查找所有的组栏目
	 * @param gid
	 * @return
	 */
	public List<GroupCategory> listGroupCategoriesByGid(Integer gid);
	
	/**
	 * 根据组ID查找栏目树
	 * @param gid
	 * @return
	 */
	public List<CategoryTreeDto> listCateTreeByGid(Integer gid);
	
	/**
	 * 根据用户id查找栏目树
	 * @param uid
	 * @return
	 */
	public List<CategoryTreeDto> listCateTreeByUid(Integer uid);
	
	/**
	 * 添加一个组栏目关系
	 * @param g
	 * @param c
	 * @return
	 */
	public GroupCategory addGroupCategory(Integer gid,Integer cid);
	
	/**
	 * 删除一个组栏目关系
	 * @param id
	 */
	public void deleteGroupCategory(Integer gid,Integer cid);
	
	/**
	 * 查找一个组栏目关系
	 * @param gid
	 * @param cid
	 * @return
	 */
	public GroupCategory loadGroupCategory(Integer gid,Integer cid);
	
	/**
	 * 根据组ID获取栏目树完整信息，包括该组包含栏目的父栏目
	 * @param gid
	 * @return
	 */
	public List<CategoryTreeDto> generateCateTreeByGid(Integer gid);
	
	/**
	 * 根据用户ID获取栏目树完整信息，包括该组包含栏目的父栏目
	 * @paramid
	 * @return
	 */
	public List<CategoryTreeDto> generateCateTreeByUid(Integer uid);
	
}
