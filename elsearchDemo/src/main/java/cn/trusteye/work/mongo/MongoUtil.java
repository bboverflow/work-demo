package cn.trusteye.work.mongo;

import cn.trusteye.work.config.LogFileUtil;
import cn.trusteye.work.pojo.Message;
import cn.trusteye.work.pojo.MongoConfig;
import com.alibaba.fastjson.JSON;
import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.apache.logging.log4j.Logger;
import org.bson.Document;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.json.Converter;
import org.bson.json.JsonWriterSettings;
import org.bson.json.StrictJsonWriter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

/**
 * @Author: rayman
 * @Description:
 * @Date: Create in 2017/12/28 13:22
 * @Modified By:
 */
public class MongoUtil {
    private Logger logger = LogFileUtil.getLogger();
    private String serverIP;
    private int port;
    private String DBName;
    private String userName;
    private String password;
    private String collectionName;
    private MongoDatabase mongoDatabase;


    public MongoUtil(MongoConfig mongoConfig) {
        this.serverIP = mongoConfig.getMongoAddr();
        this.port = mongoConfig.getMongoPort();
        this.DBName = mongoConfig.getMongoDbName();
        this.userName = mongoConfig.getMongoUserName();
        this.password = mongoConfig.getMongoPassword();
        this.collectionName = mongoConfig.getMongoCollection();

        mongoDatabase = getMongoDB();
    }

    public MongoUtil() {
        this(new MongoConfig("192.168.88.239",27017,"metadata","metadata","Tr_7_S0$","DEVICEID_1212"));
    }


    /**
     * 根据pojos的方式获取monggo database
     * @return MongoDatabase
     */
    /**
     * 经过认证，获取mongo client
     * @return
     */
    private MongoClient getMongoClient() {
        List<ServerAddress> addrs = new ArrayList<ServerAddress>();

        ServerAddress serverAddress1 = new ServerAddress(serverIP, port);
        addrs.add(serverAddress1);

        MongoCredential credential = MongoCredential.createScramSha1Credential(userName, DBName, password.toCharArray());
        List<MongoCredential> credentials = new ArrayList<>();
        credentials.add(credential);

        return new MongoClient(addrs, credentials);
    }

    /**
     * 采用pojos方式，设置自动codec
     * @return
     */
    private MongoDatabase getMongoDBByAutoPojos(){

        MongoClient mongoClient = getMongoClient();

        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));


        return mongoClient.getDatabase(DBName).withCodecRegistry(pojoCodecRegistry);
    }

    /**
     * 采用自定义codec
     * @return
     */
    private MongoDatabase getMongoDBByDefinePojos(){

        MongoClient mongoClient = getMongoClient();

        CodecRegistry customCodeRegisty = CodecRegistries.fromCodecs(new MessageCodec());
        CodecRegistry combineCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(), customCodeRegisty);

        return mongoClient.getDatabase(DBName).withCodecRegistry(combineCodecRegistry);
    }
    /**
     *
     */


    /**
     * 根据普通方式获取mongo database
     * @return
     */
    private MongoDatabase getMongoDB(){
        MongoClient mongoClient = getMongoClient();
        return mongoClient.getDatabase(DBName);
    }


    /**
     * 根据pojos的方式获取对象列表
     * @return 对象列表
     */
    public List<Message> getMessagesByPojos(){
        List<Message> messages = new ArrayList<>();
        Message message;
        String jsonString;

        MongoCollection<Message> collection = mongoDatabase.getCollection(collectionName,Message.class);
        MongoCursor<Message> mongoCursor  = collection.find().iterator();
        while (mongoCursor.hasNext()) {
            message =  mongoCursor.next();
            logger.info(message.toString());
        }
        return messages;
    }


    /**
     * 查询mongo，并通过转化BJSon来获取对象列表
     * @return
     */
    public List<Message> getMessageByJson(int skip,int limit){
        List<Message> messages = new ArrayList<>();
        JsonWriterSettings settings = getJsonWriterSettings();
        String jsonString;

        MongoCollection<Document> collection = mongoDatabase.getCollection(collectionName);
        MongoCursor<Document> mongoCursor  = collection.find().skip(skip).limit(limit).iterator();
        while (mongoCursor.hasNext()) {
            Document document =  mongoCursor.next();
            jsonString = document.toJson(settings);
            logger.info(jsonString);
            messages.add(JSON.parseObject(jsonString, Message.class));

        }
        return messages;
    }

    /**
     * 由于document.toJson()得到的是bjson格式，而不是json格式
     * 设置bjson转化规则,通过这些规则控制将document由bjson格式转化成普通json格式
     * @return
     */
    private JsonWriterSettings getJsonWriterSettings() {
        Converter<Long> converter = new Converter<Long>() {
            @Override
            public void convert(Long value, StrictJsonWriter writer) {
                writer.writeString("PARTITION_TIME", String.valueOf(value));
            }
        };

        JsonWriterSettings settings = JsonWriterSettings
                .builder()
                .int64Converter(((value, writer) -> writer.writeNumber(value.toString())))
                .dateTimeConverter(((value, writer) ->
                    {SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                    writer.writeString(sdf.format(new Date(value)));}))
                .objectIdConverter(((value, writer) -> writer.writeString(value.toString())))
                .build();

        return settings;
    }
}
