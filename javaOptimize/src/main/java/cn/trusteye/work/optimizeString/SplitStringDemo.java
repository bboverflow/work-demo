package cn.trusteye.work.optimizeString;

import java.util.StringTokenizer;

public class SplitStringDemo {
    private static int COMPUTER_NUMBER=1000;
    public static void main(String[] args) {
        String orgStr = null;
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < COMPUTER_NUMBER; i++) {
            sb.append(i);
            sb.append(";");
        }
        orgStr = sb.toString();

        long begin = System.currentTimeMillis();
        testSplit(orgStr);
        long result = System.currentTimeMillis()-begin;
        System.out.println(result);
        System.out.println("==========================");
        begin = System.currentTimeMillis();
        testToken(orgStr);
        result = System.currentTimeMillis()-begin;
        System.out.println(result);
        System.out.println("==========================");
        begin = System.currentTimeMillis();
        testIndexof(orgStr);
        result = System.currentTimeMillis()-begin;
        System.out.println(result);

    }

    private static void testSplit(String orgStr) {
        for (int i = 0; i < COMPUTER_NUMBER; i++) {
            if (null==orgStr.split(";")) {
                return;
            }
        }
    }
    private static void testToken(String orgStr){
        StringTokenizer st = new StringTokenizer(orgStr, ";");
        for (int i = 0; i < COMPUTER_NUMBER; i++) {
            while (st.hasMoreTokens()) {
                st.nextToken();
            }
            st = new StringTokenizer(orgStr, ";");
        }
    }

    private static void testIndexof(String orgSrt) {
        String temp = orgSrt;
        for (int i = 0; i < COMPUTER_NUMBER; i++) {
            while (true) {
                String splitSrt = null;
                int j = temp.indexOf(";");
                if (j < 0) {
                    break;
                }
                splitSrt = temp.substring(0, j);
                temp = temp.substring(j + 1);
            }
            temp=orgSrt;
        }
    }
}
