package cn.trusteye.work;

import java.io.Serializable;
import java.util.Comparator;

import static cn.trusteye.utils.IPUtils.ipToLong;

class IPComparator implements Comparator<String>,Serializable{

    @Override
    public int compare(String strIP1, String strIP2) {
    	Long lIP1 = ipToLong(strIP1);
    	Long lIP2 = ipToLong(strIP2);
        //字符串的比较
        return  lIP1.compareTo(lIP2);
    }

}
