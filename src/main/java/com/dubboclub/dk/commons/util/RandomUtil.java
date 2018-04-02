/*
 * @Project: GZJK
 * @Author: ysh
 * @Date: 2015年3月30日
 * @Copyright: 2000-2015 CMCC . All rights reserved.
 */
package com.dubboclub.dk.commons.util;

import java.util.concurrent.ThreadLocalRandom;

/** 
* @ClassName: RandomUtil 
* @Description: 随机数生成
* @author zengbin
* @date 2015年5月18日 下午19:59:07 
*/
public class RandomUtil {

    /**
     * 生产一个1~n的随机数
     * @param n
     * @return num 
     */
    public static int getOneNum(int n) {
        int num = ThreadLocalRandom.current().nextInt(n) + 1;
        return num;
    }

    /**
     * 获取一个在min和max之间的随机数
     * @param min 最小值
     * @param max 最大值
     * @return num
     */
    public static int getAreaNum(int min, int max) {
        int num = 0;
        num = min + (int) (Math.random() * ((max - min) + 1));
        return num;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 50; i++) {
            System.out.println(getAreaNum(0, 10));
        }
    }
}
