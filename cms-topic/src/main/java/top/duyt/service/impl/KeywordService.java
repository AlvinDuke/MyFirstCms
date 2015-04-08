package top.duyt.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import top.duyt.dao.IKeywordDao;
import top.duyt.model.Keyword;
import top.duyt.service.IKeywordService;
import top.duyt.utils.JSONUtil;

/**
 * 关键字的业务实现
 * 
 * @author Alvin Du
 * 
 */
@Service("keywordService")
public class KeywordService implements IKeywordService {

	private IKeywordDao keywordDao;

	@Override
	public List<Keyword> findKeywordByCondition(String condition) {
		return keywordDao.findKeywordByCondition(condition);
	}
	
	@Override
	public Object[] findKeywords(String condition) {
		return keywordDao.findKeywords(condition).toArray();
	}

	@Override
	public Keyword addKeyword(Keyword k) {
		return keywordDao.add(k);
	}
	
	public IKeywordDao getKeywordDao() {
		return keywordDao;
	}

	@Inject
	public void setKeywordDao(IKeywordDao keywordDao) {
		this.keywordDao = keywordDao;
	}

}
