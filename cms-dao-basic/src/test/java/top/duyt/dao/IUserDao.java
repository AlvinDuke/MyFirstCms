package top.duyt.dao;

import java.util.List;
import java.util.Map;

import top.duyt.dao.IbaseDao;
import top.duyt.dto.Page;
import top.duyt.model.User;

public interface IUserDao extends IbaseDao<User> {

	public List<User> listByMultiParams(String string, Object[] params,
			Map<String, Object> alias);

	public Page<User> findByMultiParams(String string, Object[] params,
			Map<String, Object> alias);

	public List<User> listUserByMultiParamsSql(String string, Object[] params,
			Map<String, Object> alias, Class<User> class1, boolean b);

	public Page<User> findUserByMultiParamsSql(String string, Object[] params,
			Map<String, Object> alias, Class<User> class1, boolean b);

}
