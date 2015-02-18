package top.duyt.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;

public class CaptchaUtil {
	
	/**
	 * 
	/**
	 * 生成验证码图片到指定的输出流，并返回生成的验证码
	 * @param os 目标输出流
	 * @param width 绘制的宽度
	 * @param height 绘制的高度
	 * @param num 验证码个数
	 * @param ovalNum 干扰点个数
	 * @param lineNum 干扰线条数
	 * @return
	 * @throws IOException
	 */
	public static String generateCaptcha(OutputStream os,int width,int height,int num,int ovalNum,int lineNum) throws IOException{
		
		//生成的验证码
		StringBuilder captcha = new StringBuilder();
		
		//随机资源
		String srcStr = "壹贰叁肆伍陆柒捌玖拾甲乙丙丁戊己庚辛壬癸子丑寅卯辰巳午未申酉戌亥";
		
		BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		
		//获取画笔，填充背景
		Graphics g = bi.getGraphics();
		g.setColor(new Color(51,122,183));;
		g.fillRect(0, 0, width, height);
		
		//绘制边框
		g.setColor(new Color(204,204,204));
		g.drawRect(0, 0, width-1, height-1);
		
		//设置统一字体
		Random ran = new Random();
		Font font = new Font("微软雅黑", Font.BOLD, (int) (height*0.8));
		g.setFont(font);
		g.setColor(new Color(204,204,204));
		
		//逐个随机生成
		for (int i = 0; i < num; i++) {
			char curChar = srcStr.charAt(ran.nextInt(32));
			captcha.append(curChar);
			g.drawString(String.valueOf(curChar),i*(width/num), (int) (height*0.8));
		}
		
		//绘制干扰点
		for (int i = 0; i < ovalNum; i++) {
			g.drawOval(ran.nextInt(width), ran.nextInt(height), 1, 1);
		}
		
		//绘制干扰线
		for (int i = 0; i < lineNum; i++) {
			g.drawLine(0, ran.nextInt(height), width, ran.nextInt(height));
		}
		ImageIO.write(bi, "jpg", os);
		
		return captcha.toString();
	}
}
