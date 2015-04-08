package top.duyt.service;

import java.util.List;

import top.duyt.model.Keyword;

public interface IKeywordService {

	/**
	 * 查找所有含有指定条件的关键词，返回关键字对象列表
	 * @param condition
	 * @return
	 */
	public List<Keyword> findKeywordByCondition(String condition);
	
	/**
	 * 查找关键字字中，拼音全拼中，或者拼音简拼中含有指定条件的关键字，只返回该关键字
	 * @param condition
	 * @return
	 */
	public Object[] findKeywords(String condition);
	
	/**
	 * 新增一个关键字
	 * @param k
	 * @return
	 */
	public Keyword addKeyword(Keyword k);
}
