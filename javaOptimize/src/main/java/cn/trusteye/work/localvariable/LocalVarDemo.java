package cn.trusteye.work.localvariable;

public class LocalVarDemo {
    static final long NUMBER = 1000000000L;
    static int ta =0;

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        int a=0;
        for (int i = 0; i < NUMBER; i++) {
            a++;
        }
        System.out.println(System.currentTimeMillis()-start);

        start = System.currentTimeMillis();
        for (int i = 0; i < NUMBER; i++) {
            ta++;
        }
        System.out.println(System.currentTimeMillis()-start);
    }
}
