package cn.trusteye.work.optimizeString;

public class ComprehensiveDemo {
    private static long ta=0;
    private static final long MAX_LONG = 10000000000L;
    public static void main(String[] args) {
//        testTryCatch();
        testLocalVar();
    }

    private static void testLocalVar() {
        long start = System.currentTimeMillis();
        long a=0;
        for (long i = 0; i < MAX_LONG; i++) {
            a++;
        }
        System.out.println(System.currentTimeMillis()-start);
        start = System.currentTimeMillis();
        for (long i = 0; i < MAX_LONG; i++) {
            ta++;
        }
        System.out.println(System.currentTimeMillis()-start);
    }

    private static void testTryCatch() {
        long start = System.currentTimeMillis();
        int a=0;
        for (long i = 0; i < MAX_LONG; i++) {
            try {
                a++;
            } catch (Exception e) {

            }
        }
        System.out.println(System.currentTimeMillis()-start);
        start = System.currentTimeMillis();
        try {
            for (long i = 0; i < MAX_LONG; i++) {
                a++;
            }
        } catch (Exception e) {

        }
        System.out.println(System.currentTimeMillis()-start);
    }
}
