package com.dubboclub.dk.commons.util;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Iterator;

import javax.imageio.ImageIO;

/**
 * 工具类-图片处理
 * 
 * @author changhf
 */
public class ImageUtil {
	/**
	 * 校验文件是否是图片，是:true，否:false
	 */
	public static boolean fileIsImage(File file) {
		InputStream is = null;
		BufferedReader reader = null;
		try {
			// 将文件转换成输入流
			is = new FileInputStream(file);
			// 用image IO读取文件，如果文件file不是图片，则为null
			BufferedImage image = ImageIO.read(is);
			if (image != null) { // 如果image不为空，则说明file文件是图片
				reader = new BufferedReader(new FileReader(file));
				String exits = null;
				while ((exits = reader.readLine()) != null) {
					exits = shiftD(exits);
					if (exits.indexOf("eval(") > 0 || exits.indexOf("<?php") > 0) {
						return false;
					}
				}
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (is != null) {
					is.close();
				}
				if (reader != null) {
					reader.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	/**
	 * 
	 * @param str
	 * @return
	 */
	public static String shiftD(String str) {
		int size = str.length();
		char[] chs = str.toCharArray();
		for (int i = 0; i < size; i++) {
			if (chs[i] <= 'Z' && chs[i] >= 'A') {
				chs[i] = (char) (chs[i] + 32);
			}
		}
		return new String(chs);
	}

	/**
	 * 提取上传文件类型名。如23424234.gif，返回"gif"
	 * 
	 * @param file
	 * @return
	 */
	public static String getFileType(String file) {
		return file.substring(file.lastIndexOf("."));
	}

	/**
	 * 检查图片类型
	 * 
	 * @param fileName
	 * @param fileTypes
	 * @return
	 */
	public static boolean checkFileType(String fileName, String[] fileTypes) {
		Iterator<String> type = Arrays.asList(fileTypes).iterator();
		while (type.hasNext()) {
			String ext = (String) type.next();
			if (fileName.toLowerCase().endsWith(ext)) {
				return true;
			}
		}
		return false;
	}
}
