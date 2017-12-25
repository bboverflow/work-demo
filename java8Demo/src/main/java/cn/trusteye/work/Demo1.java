package cn.trusteye.work;

import java.io.File;
import java.io.FileFilter;

/**
 * @Author: rayman
 * @Description:
 * @Date: Create in 2017/12/25 17:56
 * @Modified By:
 */
public class Demo1 {
    public static void main(String[] args) {
        File[] hiddenFiles = new File(".").listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return file.isHidden();
            }
        });

        hiddenFiles = new File(".").listFiles(File::isHidden);
    }
}
