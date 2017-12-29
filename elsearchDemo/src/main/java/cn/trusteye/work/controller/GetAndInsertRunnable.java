package cn.trusteye.work.controller;

import cn.trusteye.work.config.LogFileUtil;
import cn.trusteye.work.config.PropConfig;
import cn.trusteye.work.elsearch.ElSearchUtil;
import cn.trusteye.work.mongo.MongoUtil;
import cn.trusteye.work.pojo.Message;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * @Author: rayman
 * @Description:
 * @Date: Create in 2017/12/29 11:33
 * @Modified By:
 */
public class GetAndInsertRunnable implements Runnable {
    private Logger logger = LogFileUtil.getLogger();
    PropConfig config = PropConfig.getInstance();
    List<Message> messages;
    MongoUtil mongoUtil;
    ElSearchUtil elSearchUtil;

    @Override
    public void run() {
        mongoUtil = new MongoUtil(config.getMongoConfig());
        try {
            elSearchUtil = new ElSearchUtil();

            for (int i = 0; i < config.getHandleTimes(); i++) {
                messages = mongoUtil.getMessageByJson(i,config.getHandleNumEverytime());
                elSearchUtil.insertBulk(messages);
            }
        }catch (Exception e){
            logger.error(e);
        }
    }
}
