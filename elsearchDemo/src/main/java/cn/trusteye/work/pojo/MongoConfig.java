package cn.trusteye.work.pojo;

/**
 * @Author: rayman
 * @Description:
 * @Date: Create in 2017/12/29 17:24
 * @Modified By:
 */
public class MongoConfig {
    private String mongoAddr;
    private int mongoPort;
    private String mongoDbName;
    private String mongoUserName;
    private String mongoPassword;
    private String mongoCollection;

    public MongoConfig(String mongoAddr, int mongoPort, String mongoDbName, String mongoUserName, String mongoPassword, String mongoCollection) {
        this.mongoAddr = mongoAddr;
        this.mongoPort = mongoPort;
        this.mongoDbName = mongoDbName;
        this.mongoUserName = mongoUserName;
        this.mongoPassword = mongoPassword;
        this.mongoCollection = mongoCollection;
    }

    public MongoConfig() {
    }

    public String getMongoAddr() {
        return mongoAddr;
    }

    public void setMongoAddr(String mongoAddr) {
        this.mongoAddr = mongoAddr;
    }

    public int getMongoPort() {
        return mongoPort;
    }

    public void setMongoPort(int mongoPort) {
        this.mongoPort = mongoPort;
    }

    public String getMongoDbName() {
        return mongoDbName;
    }

    public void setMongoDbName(String mongoDbName) {
        this.mongoDbName = mongoDbName;
    }

    public String getMongoUserName() {
        return mongoUserName;
    }

    public void setMongoUserName(String mongoUserName) {
        this.mongoUserName = mongoUserName;
    }

    public String getMongoPassword() {
        return mongoPassword;
    }

    public void setMongoPassword(String mongoPassword) {
        this.mongoPassword = mongoPassword;
    }

    public String getMongoCollection() {
        return mongoCollection;
    }

    public void setMongoCollection(String mongoCollection) {
        this.mongoCollection = mongoCollection;
    }

    @Override
    public String toString() {
        return "MongoConfig{" +
                "mongoAddr='" + mongoAddr + '\'' +
                ", mongoPort=" + mongoPort +
                ", mongoDbName='" + mongoDbName + '\'' +
                ", mongoUserName='" + mongoUserName + '\'' +
                ", mongoPassword='" + mongoPassword + '\'' +
                ", mongoCollection='" + mongoCollection + '\'' +
                '}';
    }
}
