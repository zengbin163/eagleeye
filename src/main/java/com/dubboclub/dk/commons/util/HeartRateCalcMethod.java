package com.dubboclub.dk.commons.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Alice
 *
 * @Date:2016年12月6日
 */
public class HeartRateCalcMethod {

    //最大心率：FCmax=220-实际年龄 
    private static int FCmax = 0;

    //储备心： 心率储备=最大心率FCmax-安静心率FCrepose 
    private static int FCreserve = 0;

    //休息区间：心率储备X（0-50%）+静止心率.
    private static String relaxRegion = "";

    //热身锻炼区间：心率储备X（50%-60%）+静止心率
    private static String warmupRegion = "";

    //燃烧脂肪区间：心率储备X（60%-70%）+静止心率
    private static String fatburnRegion = "";

    //耐力运动区间：心率储备X（70%-80%）+静止心率
    private static String enduranceRegion = "";

    //无氧运动区间：心率储备X（80%-90%）+静止心率
    private static String anaerobicRegion = "";

    //极限运动区间：心率储备X（90%-100%）+静止心率
    private static String extremeRegion = "";

    /**
     * 获取用户心率区域
     * @param fCrepose
     * @param birthday
     * @return
     */
    public static Map<String, Object> getheartRateRegions(int fCrepose, String birthday) {
        String defaultBirthday = "1990-01-01";
        if (StringUtils.isEmpty(birthday)) {
            birthday = defaultBirthday;
        }
        String birthdayYear = birthday.substring(0, 4);
        String currentYear = TimeUtils.getCurrentTime("yyyy");
        int age = Integer.parseInt(currentYear) - Integer.parseInt(birthdayYear);
        FCmax = 220 - age;
        FCreserve = FCmax - fCrepose;
        //计算休息区间
        int relaxRegionMin = FCreserve * 0 + fCrepose;
        int relaxRegionMax = FCreserve * 50 / 100 + fCrepose;
        relaxRegion = relaxRegionMin + "," + relaxRegionMax;
        //计算热身锻炼区间
        int warmupRegionMin = relaxRegionMax;
        int warmupRegionMax = FCreserve * 60 / 100 + fCrepose;
        warmupRegion = warmupRegionMin + "," + warmupRegionMax;
        //计算燃烧脂肪区间
        int fatburnRegionnMin = warmupRegionMax;
        int fatburnRegionnMax = FCreserve * 70 / 100 + fCrepose;
        fatburnRegion = fatburnRegionnMin + "," + fatburnRegionnMax;
        //计算耐力运动区间
        int enduranceRegionMin = fatburnRegionnMax;
        int enduranceRegionMax = FCreserve * 80 / 100 + fCrepose;
        enduranceRegion = enduranceRegionMin + "," + enduranceRegionMax;
        //计算无氧运动区间
        int anaerobicRegionMin = enduranceRegionMax;
        int anaerobicRegionMax = FCreserve * 90 / 100 + fCrepose;
        anaerobicRegion = anaerobicRegionMin + "," + anaerobicRegionMax;
        //计算极限运动区间
        int extremeRegionMin = anaerobicRegionMax;
        int extremeRegionMax = FCreserve * 100 / 100 + fCrepose;
        extremeRegion = extremeRegionMin + "," + extremeRegionMax;

        Map<String, Object> hrRegionMap = new HashMap<String, Object>();
        hrRegionMap.put("relaxRegion", relaxRegion);
        hrRegionMap.put("warmupRegion", warmupRegion);
        hrRegionMap.put("fatburnRegion", fatburnRegion);
        hrRegionMap.put("enduranceRegion", enduranceRegion);
        hrRegionMap.put("anaerobicRegion", anaerobicRegion);
        hrRegionMap.put("extremeRegion", extremeRegion);
        hrRegionMap.put("alarmHeartrate", FCmax);
        return hrRegionMap;
    }
}
