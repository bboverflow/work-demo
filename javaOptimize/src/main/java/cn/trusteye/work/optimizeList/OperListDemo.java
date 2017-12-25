package cn.trusteye.work.optimizeList;

import java.util.*;

public class OperListDemo {

    private static final int NUMBER=50000;

    public static void main(String[] args) {
        testBinarySearch();
        testAddHead();
        testScanList();
    }

    private static void testScanList() {
        int number = 1000000;
        List<String> elements = new ArrayList<String>(1000000);
        for (int i = 0; i < number; i++) {
            elements.add("helloworld");
        }


        System.out.println("遍历查找结果：");
        long start = System.currentTimeMillis();
        String temp;
        for (String s : elements) {
            temp = s;
        }
        System.out.println("foreach spend:"+(System.currentTimeMillis()-start));

        start = System.currentTimeMillis();
        Iterator<String> iterator = elements.iterator();
        while(iterator.hasNext()){
            temp = iterator.next();
        }
        System.out.println("iterator spend:"+(System.currentTimeMillis()-start));


        start = System.currentTimeMillis();
        for (int i = 0; i < number; i++) {
            temp = elements.get(i);
        }
        System.out.println("for spend:"+(System.currentTimeMillis()-start));

    }

    private static void testBinarySearch() {

        List values;
        Integer[] vals=new Integer[NUMBER];
        Random random=new Random();

        for(int i=0,currval=0;i<NUMBER;i++){
            vals[i]= Integer.valueOf(currval);
            currval+=random.nextInt(100)+1;
        }

        values= Arrays.asList(vals);


        System.out.println("二分查找法结果：");
        System.out.println("ArrayList消耗时间："+ binnarySearch(new ArrayList<Integer>(values)));
        System.out.println("LinkedList消耗时间："+ binnarySearch(new LinkedList<Integer>(values)));
        System.out.println("=============================================");
    }

    static long binnarySearch(List lst){
        long start=System.currentTimeMillis();
        for(int i=0;i<NUMBER;i++){
            int index=Collections.binarySearch(lst, lst.get(i));
            if(index!=i) {
                System.out.println("***错误***");
            }
        }
        return System.currentTimeMillis()-start;
    }

    private static void testAddHead(){
        System.out.println("头部插入结果：");
        System.out.println("ArrayList耗时："+addHead(new ArrayList()));
        System.out.println("LinkedList耗时："+addHead(new LinkedList()));
        System.out.println("====================================");
    }

    static long addHead(List list){

        long start=System.currentTimeMillis();
        Object o = new Object();
        for(int i=0;i<NUMBER;i++) {
            list.add(0, o);
        }
        return System.currentTimeMillis()-start;
    }
}
