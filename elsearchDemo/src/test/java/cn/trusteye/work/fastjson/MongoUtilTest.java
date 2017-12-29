package cn.trusteye.work.fastjson;

import cn.trusteye.work.mongo.MongoUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @Author: rayman
 * @Description:
 * @Date: Create in 2017/12/28 13:45
 * @Modified By:
 */
public class MongoUtilTest {
    MongoUtil mongoUtil;
    @Before
    public void setUp() throws Exception {
        mongoUtil = new MongoUtil();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getMessages() throws Exception {
        mongoUtil.getMessageByJson(1,100);
    }

}