package com.h2h.springboot_elasticsearch.util;

import java.util.Comparator;
import java.util.Map;

import net.sourceforge.pinyin4j.PinyinHelper;

/**
 * 功能描述: 名字排序
 *
 * @auther: xiaobo
 * @date: 2018/5/25 16:23
 */
public class PinyinComparator implements Comparator<Map> {

    public int compare(Map m1, Map m2) {
        String o1 = m1.get("friendName").toString();
        String o2 = m2.get("friendName").toString();
        for (int i = 0; i < o1.length() && i < o2.length(); i++) {
            int codePoint1 = o1.charAt(i);   
            int codePoint2 = o2.charAt(i);   
            if (Character.isSupplementaryCodePoint(codePoint1) || Character.isSupplementaryCodePoint(codePoint2)) {   
                i++;  
            }   
            if (codePoint1 != codePoint2) {   
                if (Character.isSupplementaryCodePoint(codePoint1) || Character.isSupplementaryCodePoint(codePoint2)) {   
                    return codePoint1 - codePoint2;   
                }   
                String pinyin1 = pinyin((char) codePoint1);   
                String pinyin2 = pinyin((char) codePoint2);   
                if (pinyin1 != null && pinyin2 != null) {   
                // 两个字符都是汉字   
                    if (!pinyin1.equals(pinyin2)) {   
                        return pinyin1.compareTo(pinyin2);   
                    }   
                } else {   
                    return codePoint1 - codePoint2;   
                }   
            }   
            }   
             return o1.length() - o2.length();   
             }   
             /** * 字符的拼音，多音字就得到第一个拼音。不是汉字，就return null。 */   
            private static String pinyin(char c) {

                String[] pinyins = PinyinHelper.toHanyuPinyinStringArray(c);
                if (pinyins == null) {   
                    return null;   
                }
                if(c==26366){//曾
                    return pinyins[1];
                }
            return pinyins[0];   
            }

} 