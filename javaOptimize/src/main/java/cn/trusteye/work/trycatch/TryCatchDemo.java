package cn.trusteye.work.trycatch;

/**
 *
 */
public class TryCatchDemo {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        int a=0;
        for (long i = 0; i < 100000000000L; i++) {
            try {
                a++;
            }catch (Exception e){

            }
        }
        System.out.println(System.currentTimeMillis()-start);


        System.out.println("===========================");
        start = System.currentTimeMillis();
        try {
            for (long i = 0; i < 100000000000L; i++) {
                a++;
            }
        } catch (Exception e) {

        }
        System.out.println(System.currentTimeMillis()-start);

    }
}
