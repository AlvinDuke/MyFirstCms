package top.duyt.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import top.duyt.dao.impl.BaseDao;
import top.duyt.dto.Page;
import top.duyt.model.User;

@Repository("userDao")
public class UserDao extends BaseDao<User> implements IUserDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<User> listUserByMultiParamsSql(String string, Object[] params,
			Map<String, Object> alias, Class<User> class1, boolean b) {
		
		return (List<User>) this.listByMultiParamsSql(string, params, alias, class1, b);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<User> findUserByMultiParamsSql(String string, Object[] params,
			Map<String, Object> alias, Class<User> class1, boolean b) {
		return (Page<User>) this.findByMultiParamsSql(string, params, alias, class1, b);
	}

}
