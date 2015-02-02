package top.duyt.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import top.duyt.testModel.User;
import top.duyt.utils.JSONUtil;

public class TestUtil {
	
	public static void main(String[] args) {
		User u = new User(1, "haha", 2);
		User u2 = new User(2, "heihei", 3);
		User u3 = new User(3, "houhou", 4);
		
		List<User> us = new ArrayList<User>();
		us.add(u);
		us.add(u2);
		us.add(u3);
		
		Map<Object , Object> umap = new HashMap<Object, Object>();
		umap.put(u.getId(), u);
		umap.put(u2.getId(), u2);
		
		System.out.println(JSONUtil.bean2JSON(umap));
	}
	
}
