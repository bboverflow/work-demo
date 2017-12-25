package cn.trusteye.work;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

/**
 * 保存汽车信息
 */
public class UpsertCarInfoApp {
    public static void main(String[] args) throws Exception {
        TransportClient client = getTransportClient();
        createPostDocument(client);
        getPostDocument(client);


/*
        IndexRequest indexRequest = new IndexRequest("car_shop", "cars", "1")
                .source(XContentFactory.jsonBuilder()
                        .startObject()
                        .field("brand", "宝马")
                        .field("name", "宝马320")
                        .field("price", 320000)
                        .field("produce_date", "2017-01-01")
                        .endObject());
        UpdateRequest updateRequest = new UpdateRequest("car_shop", "cars", "1")
                .doc(XContentFactory.jsonBuilder()
                        .startObject()
                        .field("price", 3120000).endObject());

        UpdateResponse updateResponse = client.update(updateRequest).get();
        System.out.println(updateResponse.getResult().getOp());
*/
    }

    public static GetResponse getPostDocument(TransportClient client) {
         return client.prepareGet("index", "type", "1").get();
    }

    public static IndexResponse createPostDocument(TransportClient client) throws IOException {
        return  client.prepareIndex("index", "type", "1")
                .setSource(jsonBuilder()
                        .startObject()
                        .field("user", "kimchy")
                        .field("postDate", new Date())
                        .field("message", "trying out Elasticsearch")
                        .endObject()
                )
                .get();
    }

    public static TransportClient getTransportClient() throws UnknownHostException {
        Settings settings = Settings.builder()
                .put("cluster.name", "es-cluster1")
                .build();

        return new PreBuiltTransportClient(settings)
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("192.168.88.239"), 9300));
    }


}
