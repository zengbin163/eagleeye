package com.dubboclub.dk.commons.enums;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Random;

import javax.imageio.ImageIO;

import org.apache.commons.lang.StringUtils;

/**
 * @author 曾斌
 * @CreateTime 2016年11月16日上午9:18:00
 */
public class VertifyCodeUtils {
	public static final String VERTIFY_CODES_DEFAULT = "123456789qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM";
	public static final int IMG_WEIGHT_DEFAULT = 100;
	public static final int IMG_HEIGHT_DEFAULT = 75;

	private static Random RANDOM_DEFAULT = new Random();

	/**
	 * 生成随机验证码
	 * 
	 * @param size
	 *            验证码长度
	 * @return
	 */
	public static String generateVertifyCode(int size) {
		return generateVertifyCode(size, VERTIFY_CODES_DEFAULT);
	}

	/**
	 * 生成随机验证码
	 * 
	 * @param size
	 *            验证码长度
	 * @param sourceString
	 *            验证码源字符串
	 * @return
	 */
	public static String generateVertifyCode(int size, String sourceString) {
		if (size == 0) {
			return "";
		}
		if (StringUtils.isBlank(sourceString)) {
			sourceString = VERTIFY_CODES_DEFAULT;
		}
		int srcLenth = sourceString.length();
		Random rand = new Random(System.currentTimeMillis());
		StringBuilder verifyCode = new StringBuilder(size);
		for (int i = 0; i < size; i++) {
			verifyCode.append(sourceString.charAt(rand.nextInt(srcLenth - 1)));
		}
		return verifyCode.toString();
	}

	/**
	 * 生成随机验证码文件,并返回验证码值
	 * 
	 * @param w
	 *            图片的宽度
	 * @param h
	 *            图片的高度
	 * @param outputFile
	 *            图片输出文件
	 * @param verifySize
	 *            验证码长度
	 * @return
	 * @throws IOException
	 */
	public static String outputVerifyImage(int w, int h, File outputFile, int verifySize) throws IOException {
		String verifyCode = generateVertifyCode(verifySize);
		outputImage(w, h, outputFile, verifyCode);
		return verifyCode;
	}

	/**
	 * 输出随机验证码图片流,并返回验证码值
	 * 
	 * @param w
	 *            图片的宽度
	 * @param h
	 *            图片的高度
	 * @param os
	 *            图片输出流
	 * @param verifySize
	 *            验证码长度
	 * @return
	 * @throws IOException
	 */
	public static String outputVerifyImage(int w, int h, OutputStream os, int verifySize) throws IOException {
		String verifyCode = generateVertifyCode(verifySize);
		outputImage(w, h, os, verifyCode);
		return verifyCode;
	}

	/**
	 * 生成指定验证码图像文件
	 * 
	 * @param w
	 *            图片的宽度
	 * @param h
	 *            图片的高度
	 * @param outputFile
	 *            图片输出文件
	 * @param code
	 *            验证码
	 * @throws IOException
	 */
	public static void outputImage(int w, int h, File outputFile, String code) throws IOException {
		if (outputFile == null) {
			return;
		}
		File dir = outputFile.getParentFile();
		if (!dir.exists()) {
			dir.mkdirs();
		}
		try {
			outputFile.createNewFile();
			FileOutputStream fos = new FileOutputStream(outputFile);
			outputImage(w, h, fos, code);
			fos.close();
		} catch (IOException e) {
			throw e;
		}
	}

	/**
	 * 输出指定验证码图片流
	 * 
	 * @param w
	 *            图片的宽度
	 * @param h
	 *            图片的高度
	 * @param os
	 *            输出图片流
	 * @param code
	 *            验证码
	 * @throws IOException
	 */
	public static void outputImage(int w, int h, OutputStream os, String code) throws IOException {
		if (w == 0 || h == 0) {
			w = IMG_WEIGHT_DEFAULT;
			h = IMG_HEIGHT_DEFAULT;
		}
		int verifySize = code.length();
		BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		Random rand = new Random();
		Graphics2D g2 = image.createGraphics();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		Color[] colors = new Color[5];
		Color[] colorSpaces = new Color[] { Color.WHITE, Color.CYAN, Color.GRAY, Color.LIGHT_GRAY, Color.MAGENTA,
				Color.ORANGE, Color.PINK, Color.YELLOW };
		float[] fractions = new float[colors.length];
		for (int i = 0; i < colors.length; i++) {
			colors[i] = colorSpaces[rand.nextInt(colorSpaces.length)];
			fractions[i] = rand.nextFloat();
		}
		Arrays.sort(fractions);

		g2.setColor(Color.GRAY);// 设置边框色
		g2.fillRect(0, 0, w, h);

		Color c = getRandColor(1, 255, 255);
		g2.setColor(c);// 设置背景色
		g2.fillRect(0, 2, w, h - 4);

		// 绘制干扰线
		Random random = new Random();
		g2.setColor(getRandColor(255, 255, 255));// 设置线条的颜色
		for (int i = 0; i < 20; i++) {
			int x = random.nextInt(w - 1);
			int y = random.nextInt(h - 1);
			int xl = random.nextInt(6) + 1;
			int yl = random.nextInt(12) + 1;
			g2.drawLine(x, y, x + xl + 40, y + yl + 20);
		}

		// 添加噪点
		float yawpRate = 0.05f;// 噪声率
		int area = (int) (yawpRate * w * h);
		for (int i = 0; i < area; i++) {
			int x = random.nextInt(w);
			int y = random.nextInt(h);
			int rgb = getRandomIntColor();
			image.setRGB(x, y, rgb);
		}

		shear(g2, w, h, c);// 使图片扭曲

		g2.setColor(getRandColor(255, 255, 255));
		int fontSize = h - 4;
		Font font = new Font("Algerian", Font.ITALIC, fontSize);
		g2.setFont(font);
		char[] chars = code.toCharArray();
		for (int i = 0; i < verifySize; i++) {
			AffineTransform affine = new AffineTransform();
			affine.setToRotation(Math.PI / 4 * rand.nextDouble() * (rand.nextBoolean() ? 1 : -1),
					(w / verifySize) * i + fontSize / 2, h / 2);
			g2.setTransform(affine);
			g2.drawChars(chars, i, 1, ((w - 10) / verifySize) * i + 5, h / 2 + fontSize / 2 - 10);
		}

		g2.dispose();
		ImageIO.write(image, "jpg", os);
	}

	/**
	 * 生成0-maxR/G/B范围内的组合 随机颜色
	 * 
	 * @param rgbValue
	 *            RGB 颜色值
	 * @return
	 */
	private static Color getRandColor(int maxR, int maxG, int maxB) {
		if (maxR > 255 || maxR == 0) {
			maxR = 255;
		}
		if (maxG > 255 || maxG == 0) {
			maxG = 255;
		}
		if (maxB > 255 || maxB == 0) {
			maxB = 255;
		}
		int r = RANDOM_DEFAULT.nextInt(maxR);
		int g = RANDOM_DEFAULT.nextInt(maxG);
		int b = RANDOM_DEFAULT.nextInt(maxB);
		return new Color(r, g, b);
	}

	private static int getRandomIntColor() {
		int[] rgb = getRandomRgb();
		int color = 0;
		for (int c : rgb) {
			color = color << 8;
			color = color | c;
		}
		return color;
	}

	private static int[] getRandomRgb() {
		int[] rgb = new int[3];
		for (int i = 0; i < 3; i++) {
			rgb[i] = RANDOM_DEFAULT.nextInt(255);
		}
		return rgb;
	}

	private static void shear(Graphics g, int w1, int h1, Color color) {
		shearX(g, w1, h1, color);
		shearY(g, w1, h1, color);
	}

	private static void shearX(Graphics g, int w1, int h1, Color color) {

		int period = RANDOM_DEFAULT.nextInt(2);

		boolean borderGap = true;
		int frames = 1;
		int phase = RANDOM_DEFAULT.nextInt(2);

		for (int i = 0; i < h1; i++) {
			double d = (double) (period >> 1)
					* Math.sin((double) i / (double) period + (6.2831853071795862D * (double) phase) / (double) frames);
			g.copyArea(0, i, w1, 1, (int) d, 0);
			if (borderGap) {
				g.setColor(color);
				g.drawLine((int) d, i, 0, i);
				g.drawLine((int) d + w1, i, w1, i);
			}
		}

	}

	private static void shearY(Graphics g, int w1, int h1, Color color) {

		int period = RANDOM_DEFAULT.nextInt(40) + 10; // 50;

		boolean borderGap = true;
		int frames = 20;
		int phase = 7;
		for (int i = 0; i < w1; i++) {
			double d = (double) (period >> 1)
					* Math.sin((double) i / (double) period + (6.2831853071795862D * (double) phase) / (double) frames);
			g.copyArea(i, 0, 1, h1, 0, (int) d);
			if (borderGap) {
				g.setColor(color);
				g.drawLine(i, (int) d, i, 0);
				g.drawLine(i, (int) d + h1, i, h1);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		File dir = new File("F:tmp");
		int w = 200;
		int h = 100;
		String verifyCode = generateVertifyCode(4);
		File file = new File(dir, verifyCode + ".jpg");
		outputImage(w, h, file, verifyCode);
	}
}
