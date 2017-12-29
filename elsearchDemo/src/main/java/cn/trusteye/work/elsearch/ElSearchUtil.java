package cn.trusteye.work.elsearch;

import cn.trusteye.work.config.LogFileUtil;
import cn.trusteye.work.pojo.Message;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.List;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

/**
 * @Author: rayman
 * @Description:
 * @Date: Create in 2017/12/29 11:35
 * @Modified By:
 */
public class ElSearchUtil {
    private Logger logger = LogFileUtil.getLogger();
    private final String serverAddr = "192.168.88.239";
    private final int serverPort = 9300;

    private final String index = "metadata";
    private final String type = "deviceid";

    private TransportClient client;

    public ElSearchUtil() throws Exception{
        client = getTransportClient();
    }

    private GetResponse getPostDocument(TransportClient client) {
        return client.prepareGet("index", "type", "1").get();
    }

    /**
     * 创建document
     * @return
     * @throws IOException
     */
    private IndexResponse createPostDocument() throws IOException {
        return client.prepareIndex("index", "type", "1")
                .setSource(jsonBuilder()
                        .startObject()
                        .field("user", "kimchy")
                        .field("postDate", new Date())
                        .field("message", "trying out Elasticsearch")
                        .endObject()
                )
                .get();
    }

    private TransportClient getTransportClient() throws UnknownHostException {
        Settings settings = Settings.builder()
                .put("cluster.name", "es-cluster1")
                .build();

        return new PreBuiltTransportClient(settings)
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(serverAddr), serverPort));
    }


    public void insertBulk(List<Message> messages) {
        BulkRequestBuilder bulkRequest = client.prepareBulk();
        bulkRequest.add(client.prepareIndex(index, type).setSource(messages));
        bulkRequest.execute().actionGet();

/*
        Map<String ,Object> ret = new HashMap<>();
        for(Message message:messages){
            ret.put("id", message.getId());
            ret.put("src_ip", message.getSrc_ip());
            ret.put("dst_ip", message.getDst_ip());
            ret.put("partition_time",message.getPartition_time());
            ret.put("server_no", message.getServer_no());
            bulkRequest.add(client.prepareIndex(index, type).setSource(ret));
        }
*/
    }
}
