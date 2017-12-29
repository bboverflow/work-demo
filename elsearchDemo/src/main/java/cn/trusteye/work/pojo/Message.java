package cn.trusteye.work.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import org.bson.types.ObjectId;

import java.util.Date;


/**
 * @Author: rayman
 * @Description:
 * @Date: Create in 2017/12/28 12:01
 * @Modified By:
 */
public class Message {
    @JSONField(name = "_id")
    private String id;
    @JSONField(name = "SERVER_NO")
    private String server_no;
    @JSONField(name="SRC_IP")
    private long src_ip;
    @JSONField(name = "DST_IP")
    private long dst_ip;
    @JSONField(name = "PARTITION_TIME",format = "yyyy-MM-dd HH:mm:ss")
    private Date partition_time;

    public Message() {
    }

    public Message(String id, String server_no, long src_ip, long dst_ip, Date partition_time) {
        this.id = id;
        this.server_no = server_no;
        this.src_ip = src_ip;
        this.dst_ip = dst_ip;
        this.partition_time = partition_time;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getPartition_time() {
        return partition_time;
    }

    public void setPartition_time(Date partition_time) {
        this.partition_time = partition_time;
    }

    public String getServer_no() {
        return server_no;
    }

    public void setServer_no(String server_no) {
        this.server_no = server_no;
    }

    public long getSrc_ip() {
        return src_ip;
    }

    public void setSrc_ip(long src_ip) {
        this.src_ip = src_ip;
    }

    public long getDst_ip() {
        return dst_ip;
    }

    public void setDst_ip(long dst_ip) {
        this.dst_ip = dst_ip;
    }

    @Override
    public String toString() {
        return "Message{" +
                "server_no='" + server_no + '\'' +
                ", src_ip=" + src_ip +
                ", dst_ip=" + dst_ip +
                '}';
    }
}
