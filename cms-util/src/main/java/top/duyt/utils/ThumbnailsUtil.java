package top.duyt.utils;

import java.io.IOException;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

public class ThumbnailsUtil {

	/**
	 * 按照指定的宽度或者高度进行图像文件缩/阔放,缩/阔放之后得到的图像文件横纵比保持不变
	 * 
	 * @param width
	 *            指定缩/阔放之后的图像宽度
	 * @param height
	 *            指定缩/阔放之后的图像高度
	 * @param oriFile
	 *            原始图像文件的磁盘绝对路径
	 * @param destFile
	 *            缩/阔放之后的图像文件磁盘绝对路径
	 * @throws IOException
	 */
	public static void specifyResize(int width, int height, String oriFile,
			String destFile) throws IOException {
		Thumbnails.of(oriFile).size(width, height).toFile(destFile);
	}

	/**
	 * 按照指定的比例进行图像文件缩/阔放
	 * 
	 * @param scale
	 *            缩/阔放的比例，缩小或为0.25f,阔放或为1.25f
	 * @param oriFile
	 *            原始图像文件的磁盘绝对路径
	 * @param destFile
	 *            缩/阔放之后的图像文件磁盘绝对路径
	 * @throws IOException
	 */
	public static void scaleResize(float scale, String oriFile, String destFile)
			throws IOException {
		Thumbnails.of(oriFile).scale(scale).toFile(destFile);
	}

	/**
	 * 按照指定的宽度或者高度进行图像文件缩/阔放,不维持横纵比
	 * 
	 * @param width
	 *            指定缩/阔放之后的图像宽度
	 * @param height
	 *            指定缩/阔放之后的图像高度
	 * @param oriFile
	 *            原始图像文件的磁盘绝对路径
	 * @param destFile
	 *            缩/阔放之后的图像文件磁盘绝对路径
	 * @throws IOException
	 */
	public static void forceResize(int width, int height, String oriFile,
			String destFile) throws IOException {
		Thumbnails.of(oriFile).size(width, height).keepAspectRatio(false)
				.toFile(destFile);
	}

	/**
	 * 按照指定的角度对图像进行旋转
	 * 
	 * @param rotate
	 *            旋转角度，正数：顺时针 负数：逆时针
	 * @param oriFile
	 *            原始图像文件的磁盘绝对路径
	 * @param destFile
	 *            缩/阔放之后的图像文件磁盘绝对路径
	 * @throws IOException
	 */
	public static void specifyRotate(double rotate, String oriFile,
			String destFile) throws IOException {
		Thumbnails.of(oriFile).rotate(rotate).scale(1).toFile(destFile);
	}

}
