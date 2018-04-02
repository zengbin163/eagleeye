package com.dubboclub.dk.commons.enums;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

/**
 * FTP工具类
 * @author zengbin
 *
 */
public class FtpUtils {
	private static FTPClient ftp;

	/**
	 * @param path 上传到ftp服务器哪个路径下
	 * @param addr 地址
	 * @param port 端口号
	 * @param username 用户名
	 * @param password 密码
	 * @return
	 * @throws Exception
	 */
	public static boolean connect(String path, String addr, int port, String username, String password) throws Exception {
		boolean result = false;
		ftp = new FTPClient();
		int reply = 0;
		ftp.connect(addr, port);
		ftp.login(username, password);
		ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
		reply = ftp.getReplyCode();
		if (!FTPReply.isPositiveCompletion(reply)) {
			ftp.disconnect();
			return result;
		}
		ftp.changeWorkingDirectory(path);
		result = true;
		return result;
	}
	
	/**
	 * @param file 上传的文件或文件夹
	 * @throws Exception
	 */
	public static void upload(File file) throws Exception {
		if (file.isDirectory()) {
			ftp.makeDirectory(file.getName());
			ftp.changeWorkingDirectory(file.getName());
			String[] files = file.list();
			for (int i = 0; i < files.length; i++) {
				File file1 = new File(file.getPath() + "\\" + files[i]);
				if (file1.isDirectory()) {
					upload(file1);
					ftp.changeToParentDirectory();
				} else {
					File file2 = new File(file.getPath() + "\\" + files[i]);
					FileInputStream input = new FileInputStream(file2);
					ftp.storeFile(file2.getName(), input);
					input.close();
				}
			}
		} else {
			File file2 = new File(file.getPath());
			FileInputStream input = new FileInputStream(file2);
			ftp.storeFile(file2.getName(), input);
			input.close();
		}
	}

	/**
	 * 下载文件
	 * @param ftpFile
	 * @param dstFile
	 * @throws IOException
	 */
	public void downLoad(String ftpFile, String dstFile) throws IOException {
		File file = new File(dstFile);
		FileOutputStream fos = new FileOutputStream(file);
		ftp.retrieveFile(ftpFile, fos);
		fos.close();
	}
	
}
