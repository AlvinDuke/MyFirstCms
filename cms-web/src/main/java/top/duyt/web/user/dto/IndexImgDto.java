package top.duyt.web.user.dto;

public class IndexImgDto {

	/**
	 * 已上传的主页图片id
	 */
	private int imgId;

	/**
	 * 主标题
	 */
	private String mainTitle;

	/**
	 * 副标题
	 */
	private String subTitle;

	/**
	 * 连接
	 */
	private String link;

	/**
	 * 图像剪切起始的纵轴坐标
	 */
	private int cropedY;

	public IndexImgDto() {
	}

	public IndexImgDto(int imgId, String mainTitle, String subTitle,
			String link, int cropedY) {
		this.imgId = imgId;
		this.mainTitle = mainTitle;
		this.subTitle = subTitle;
		this.link = link;
		this.cropedY = cropedY;
	}

	public int getImgId() {
		return imgId;
	}

	public void setImgId(int imgId) {
		this.imgId = imgId;
	}

	public String getMainTitle() {
		return mainTitle;
	}

	public void setMainTitle(String mainTitle) {
		this.mainTitle = mainTitle;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public int getCropedY() {
		return cropedY;
	}

	public void setCropedY(int cropedY) {
		this.cropedY = cropedY;
	}

}
