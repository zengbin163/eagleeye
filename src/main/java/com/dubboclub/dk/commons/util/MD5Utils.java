package com.dubboclub.dk.commons.util;

import java.security.MessageDigest;

// TODO Auto-generated Javadoc
/** 
 * <p>文件名称: MD5Utils.java</p>
 * 
 * <p>文件功能: MD5加密工具类</p>
 *
 * <p>编程者:	lljqiu</p>
 * 
 * <p>初作时间: 2014年6月10日 下午4:46:09</p>
 * 
 * <p>版本: version 1.0 </p>
 *
 * <p>输入说明: </p>
 *
 * <p>输出说明: </p>
 *
 * <p>程序流程: </p>
 * 
 * <p>============================================</p>
 * <p>修改序号:</p>
 * <p>时间:	 </p>
 * <p>修改者:  </p>
 * <p>修改内容:  </p>
 * <p>============================================</p>
 */
public class MD5Utils {

    /**  十六进制下数字到字符的映射数组. */
    private final static String[] HEXDIGITS = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

    /**
     *  
     * Description：对字符串进行加密.
     *
     * @author name：lljqiu
     * <p>============================================</p>
     * Modified No： 
     * Modified By： 
     * Modified Date： 
     * Modified Description: 
     * <p>============================================</p>
     * @param originString the origin string
     * @return String
     */
    private static String encodeByMD5(String originString) {
        if (originString != null) {
            try {
                MessageDigest md = MessageDigest.getInstance("MD5");
                byte[] results = md.digest(originString.getBytes());
                String resultString = byteArrayToHexString(results);
                return resultString.toUpperCase();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    /**
     *  
     * Description：转换字节数组为16进制字串  .
     *
     * @author name：lljqiu
     * <p>============================================</p>
     * Modified No： 
     * Modified By： 
     * Modified Date： 
     * Modified Description: 
     * <p>============================================</p>
     * @param b  字节数组
     * @return String    十六进制字串
     */
    private static String byteArrayToHexString(byte[] b) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }

    /**
     *  
     * Description：将一个字节转化成16进制形式的字符串  .
     *
     * @author name：lljqiu
     * <p>============================================</p>
     * Modified No： 
     * Modified By： 
     * Modified Date： 
     * Modified Description: 
     * <p>============================================</p>
     * @param b the b
     * @return String
     */
    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0) {
            n = 256 + n;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return HEXDIGITS[d1] + HEXDIGITS[d2];
    }

    /**
     *  
     * Description：调用MD5对输入的字符串进行加密.
     *
     * @author name：lljqiu
     * <p>============================================</p>
     * Modified No： 
     * Modified By： 
     * Modified Date： 
     * Modified Description: 
     * <p>============================================</p>
     * @param inputString    用户输入的字符串
     * @return String    加密过后的字符串
     */
    public static String createEncryption(String inputString) {
        return encodeByMD5(inputString);
    }

    /**
     *   
     * 验证输入的密码是否正确  .
     *
     * @param password      真正的密码（加密后的真密码）
     * @param inputString   输入的字符串
     * @return true, if successful
     */
    public static boolean authenticatePassword(String password, String inputString) {
        return password.equals(encodeByMD5(inputString));
    }
}
