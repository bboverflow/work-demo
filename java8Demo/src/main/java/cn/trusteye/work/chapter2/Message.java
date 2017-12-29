package cn.trusteye.work.chapter2;

/**
 * @Author: rayman
 * @Description:
 * @Date: Create in 2017/12/26 11:01
 * @Modified By:
 */
public class Message {
    private String site_name;
    private String action_type;
    private Long capture_time;

    public Message(String site_name, String action_type, Long capture_time) {
        this.site_name = site_name;
        this.action_type = action_type;
        this.capture_time = capture_time;
    }

    public String getSite_name() {
        return site_name;
    }

    public void setSite_name(String site_name) {
        this.site_name = site_name;
    }

    public String getAction_type() {
        return action_type;
    }

    public void setAction_type(String action_type) {
        this.action_type = action_type;
    }

    public Long getCapture_time() {
        return capture_time;
    }

    public void setCapture_time(Long capture_time) {
        this.capture_time = capture_time;
    }

    @Override
    public String toString() {
        return "Message{" +
                "site_name='" + site_name + '\'' +
                ", action_type='" + action_type + '\'' +
                ", capture_time=" + capture_time +
                '}';
    }
}
