package top.duyt.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.apache.wicket.util.file.File;
import org.springframework.stereotype.Service;

import top.duyt.dao.IindexImgDao;
import top.duyt.dto.PageParamHolder;
import top.duyt.model.IndexImg;
import top.duyt.service.IindexImgService;

/**
 * 首页欢迎图片信息
 * 
 * @author Alvin Du
 * 
 */
@Service("indexImgService")
public class IndexImgService implements IindexImgService {

	private IindexImgDao indexImgDao;

	@Override
	public IndexImg addIndexImg(IndexImg ii) {
		Integer maxSort = indexImgDao.findMaxSort();
		ii.setSortNum(maxSort==null?1:maxSort++);
		return indexImgDao.add(ii);
	}

	@Override
	public void deleteIndexImg(int id) {
		IndexImg ii = indexImgDao.load(id);
		indexImgDao.delete(id);
		//删除缩略图,预览图
		new File(PageParamHolder.getRootPath() + ii.getAccessUrl().replace(PageParamHolder.getContextPath(), "") + "preview_" + ii.getNewName()).delete();
		new File(PageParamHolder.getRootPath() + ii.getAccessUrl().replace(PageParamHolder.getContextPath(), "") + "thumbnail_" + ii.getNewName()).delete();
	}

	@Override
	public List<IndexImg> listAllIndexImgs() {
		return indexImgDao.listAllIndexImgs();
	}

	public IindexImgDao getIndexImgDao() {
		return indexImgDao;
	}

	@Inject
	public void setIndexImgDao(IindexImgDao indexImgDao) {
		this.indexImgDao = indexImgDao;
	}

	@Override
	public IndexImg load(int id) {
		return indexImgDao.load(id);
	}

	@Override
	public void updateIndexImg(IndexImg img) {
		indexImgDao.update(img);
	}

	@Override
	public void updateOrders(String[] iids) {
		//先重置所有的图片顺序
		indexImgDao.resetOrders();
		//逐个更新顺序
		IndexImg tempI = null;
		for (int i = 0; i < iids.length; i++) {
			tempI = indexImgDao.load(Integer.valueOf(iids[i]));
			if (tempI!=null) {
				tempI.setSortNum(i+1);
				indexImgDao.update(tempI);
			}
		}
	}

	@Override
	public List<IndexImg> listIndexImgsBySort() {
		return indexImgDao.listIndexImgsBySort();
	}

	@Override
	public Integer findMaxSort() {
		return indexImgDao.findMaxSort();
	}

}
